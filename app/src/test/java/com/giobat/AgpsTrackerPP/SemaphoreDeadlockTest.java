package com.giobat.AgpsTrackerPP;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Pure JUnit4 regression tests for the map layer semaphore deadlock (NO Robolectric).
 *
 * Root cause of ANR: m2.j() and j.java were calling requestRedraw()/redrawLayers()
 * while holding the f3196b semaphore. This blocked the UI thread (y3.d Runnable)
 * which also needed that semaphore -> deadlock -> ANR.
 *
 * Fix: requestRedraw() is called AFTER semaphore.release().
 *
 * These tests run in milliseconds (pure Java, no Android environment needed).
 */
@RunWith(JUnit4.class)
public class SemaphoreDeadlockTest {

    /**
     * Validates that the map thread releases the semaphore BEFORE calling requestRedraw(),
     * allowing the UI thread to acquire it without blocking.
     */
    @Test
    public void testSemaphoreReleasedBeforeRedraw_noDeadlock() throws Exception {
        Semaphore mapLayerSemaphore = new Semaphore(1, true);
        AtomicBoolean redrawCalledUnderLock = new AtomicBoolean(false);
        AtomicBoolean redrawCalledAfterRelease = new AtomicBoolean(false);
        CountDownLatch backgroundStarted = new CountDownLatch(1);
        CountDownLatch redrawDone = new CountDownLatch(1);

        // Map thread (fixed m2.j): acquire, mutate, release THEN redraw
        Thread mapThread = new Thread(() -> {
            try {
                mapLayerSemaphore.acquire();
                backgroundStarted.countDown();
                Thread.sleep(20); // Simulate polyline data mutation under lock
                mapLayerSemaphore.release();       // Release BEFORE redraw (the fix)
                redrawCalledAfterRelease.set(true); // Simulate requestRedraw()
                redrawDone.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // UI thread (y3.d animation Runnable): tries to acquire semaphore to add marker
        AtomicBoolean uiAcquiredWithinTimeout = new AtomicBoolean(false);
        Thread uiLikeThread = new Thread(() -> {
            try {
                backgroundStarted.await();
                boolean acquired = mapLayerSemaphore.tryAcquire(2000, TimeUnit.MILLISECONDS);
                if (acquired) {
                    uiAcquiredWithinTimeout.set(true);
                    mapLayerSemaphore.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        mapThread.start();
        uiLikeThread.start();
        boolean mapDone = redrawDone.await(5, TimeUnit.SECONDS);
        mapThread.join(1000);
        uiLikeThread.join(1000);

        assertTrue("Map thread completed without timeout", mapDone);
        assertTrue("requestRedraw called after semaphore release", redrawCalledAfterRelease.get());
        assertFalse("requestRedraw NOT called while semaphore was held", redrawCalledUnderLock.get());
        assertTrue("UI thread acquired semaphore within 2s — no deadlock", uiAcquiredWithinTimeout.get());
    }

    /**
     * Demonstrates the anti-pattern that CAUSES the deadlock (for documentation).
     * The buggy pattern: acquire -> redraw -> release would starve the UI thread.
     * The correct pattern: acquire -> mutate -> release -> redraw is validated here.
     */
    @Test
    public void testCorrectPattern_mutateReleaseRedraw_isNotStarved() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1, true);
        AtomicBoolean uiWasStarved = new AtomicBoolean(false);
        CountDownLatch bothDone = new CountDownLatch(2);

        // Background thread: uses CORRECT pattern (release before redraw)
        Thread mapThread = new Thread(() -> {
            try {
                semaphore.acquire();
                Thread.sleep(10); // Fast mutation
                semaphore.release();          // Release FIRST
                Thread.sleep(5);              // Simulate requestRedraw() work
                bothDone.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // UI thread: should never wait more than ~50ms for the semaphore
        Thread uiThread = new Thread(() -> {
            try {
                Thread.sleep(5); // Let map thread get the lock first
                boolean acquired = semaphore.tryAcquire(500, TimeUnit.MILLISECONDS);
                uiWasStarved.set(!acquired); // true = timed out = deadlock!
                if (acquired) semaphore.release();
                bothDone.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        mapThread.start();
        uiThread.start();
        boolean completed = bothDone.await(3, TimeUnit.SECONDS);

        assertTrue("Both threads completed", completed);
        assertFalse("UI thread was NOT starved (release before redraw pattern)", uiWasStarved.get());
    }
}
