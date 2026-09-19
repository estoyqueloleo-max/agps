package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.location.Location;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import c2.MovingAverageFilter;
import c2.q3;
import c2.r;
import c2.s;
import c2.x3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class TrackSegmentStatisticsTest {

    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void testEmptyAndSinglePointSegmentSafety() {
        q3 segment = new q3("Test Seg", "Desc", 0);
        // Empty segment should not throw any exception
        segment.a();
        segment.g();

        // Single point segment
        Location loc = new Location("gps");
        loc.setLatitude(40.4168);
        loc.setLongitude(-3.7038);
        loc.setAltitude(650.0);
        loc.setTime(1000000L);

        segment.f3123b.add(loc);
        segment.a();
        segment.g();

        s stats = segment.f3128g;
        assertNotNull(stats);
        assertEquals(650.0, stats.f3153b, 0.01);
        assertEquals(650.0, stats.f3155d, 0.01);
        assertEquals(0.0, stats.f3166p, 0.01);
    }

    @Test
    public void testAscentDescentAndDistance() {
        q3 segment = new q3("Mountain Trail", "Ascent and Descent", 0);

        long baseTime = 1700000000000L;
        // Point 1: 40.000, -3.000, 500m
        Location p1 = new Location("gps");
        p1.setLatitude(40.000);
        p1.setLongitude(-3.000);
        p1.setAltitude(500.0);
        p1.setTime(baseTime);

        // Point 2: 40.001, -3.000, 550m (+50m)
        Location p2 = new Location("gps");
        p2.setLatitude(40.001);
        p2.setLongitude(-3.000);
        p2.setAltitude(550.0);
        p2.setTime(baseTime + 60000);

        // Point 3: 40.002, -3.000, 520m (-30m)
        Location p3 = new Location("gps");
        p3.setLatitude(40.002);
        p3.setLongitude(-3.000);
        p3.setAltitude(520.0);
        p3.setTime(baseTime + 120000);

        segment.f3123b.add(p1);
        segment.f3123b.add(p2);
        segment.f3123b.add(p3);

        segment.a();
        s stats = segment.f3128g;

        // Total distance must be > 0 (roughly 220 meters for 0.002 degrees latitude)
        assertTrue("Distance should be around 220m, was: " + stats.f3166p, stats.f3166p > 200.0 && stats.f3166p < 250.0);

        // Max elevation and min elevation
        assertTrue("Max elevation should be >= 520, was: " + stats.f3153b, stats.f3153b >= 520.0);
        assertTrue("Min elevation should be <= 520, was: " + stats.f3155d, stats.f3155d <= 520.0);

        // Bounding box
        assertEquals(40.000, stats.f3163l, 0.0001); // latMin
        assertEquals(40.002, stats.f3161j, 0.0001); // latMax
        assertEquals(-3.000, stats.m, 0.0001);       // lonMin
        assertEquals(-3.000, stats.f3162k, 0.0001); // lonMax
    }

    @Test
    public void testMovingAverageFilterSafetyAndSmoothing() {
        // Null and empty checks
        MovingAverageFilter.b(null);
        MovingAverageFilter.b(new double[0]);
        MovingAverageFilter.b(new double[]{42.0});

        // Test with real noisy signal
        double[] noisy = new double[]{100.0, 100.0, 200.0, 100.0, 100.0, 100.0};
        MovingAverageFilter.b(noisy);

        // The spike at index 2 (200.0) should be smoothed down significantly
        assertTrue("Spike should be smoothed down below 200, was: " + noisy[2], noisy[2] < 160.0);
    }

    @Test
    public void testGpxTrackExportRoundTrip() throws Exception {
        x3 track = new x3(0);
        q3 seg = new q3("Segment 1", "Export test", 0);

        long t = 1700000000000L;
        Location loc1 = new Location("gps");
        loc1.setLatitude(40.416775);
        loc1.setLongitude(-3.703790);
        loc1.setAltitude(655.0);
        loc1.setTime(t);

        Location loc2 = new Location("gps");
        loc2.setLatitude(40.417000);
        loc2.setLongitude(-3.704000);
        loc2.setAltitude(660.0);
        loc2.setTime(t + 10000);

        seg.f3122a.add(new org.mapsforge.core.model.LatLong(loc1.getLatitude(), loc1.getLongitude()));
        seg.f3123b.add(loc1);
        seg.f3122a.add(new org.mapsforge.core.model.LatLong(loc2.getLatitude(), loc2.getLongitude()));
        seg.f3123b.add(loc2);
        track.f3236a.add(seg);

        File exportDir = new File(context.getCacheDir(), "gpx_test_export");
        exportDir.mkdirs();
        File exportedFile = new File(exportDir, "exported_track.gpx");

        r gpxHandler = new r(context);
        // Call export: g(file, track, appName, appVersion, trackName, description)
        gpxHandler.g(exportedFile, track, "AGPS-Tracker", " 1.8", "exported_track", "test export");

        assertTrue("Exported GPX file must exist", exportedFile.exists());
        assertTrue("Exported GPX file must not be empty", exportedFile.length() > 100);

        // Verify that our streaming parser can re-import this exported track cleanly
        try (FileInputStream fis = new FileInputStream(exportedFile)) {
            boolean readOk = gpxHandler.a(fis, context);
            assertTrue("Re-importing exported GPX must succeed", readOk);
            assertEquals(2, gpxHandler.f3137h); // 2 trackpoints read
            assertEquals(2, gpxHandler.f3132c.size());
        }
    }
}
