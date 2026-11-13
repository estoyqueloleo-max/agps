package com.giobat.AgpsTrackerPP.tron;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class PingoBridgeManagerTest {

    private Context context;
    private PingoBridgeManager bridgeManager;

    @Before
    public void setUp() {
        context = RuntimeEnvironment.getApplication();
        bridgeManager = new PingoBridgeManager(context);
        bridgeManager.setActiveMatchId("TRON-ALPHA");
    }

    @Test
    public void testPositionPayloadParsing() {
        AtomicBoolean positionReceived = new AtomicBoolean(false);
        AtomicReference<String> opponentId = new AtomicReference<>();

        bridgeManager.setListener(new TronP2PListener() {
            @Override
            public void onOpponentPositionReceived(String peerId, double latitude, double longitude, float speedKmh, long timestamp) {
                positionReceived.set(true);
                opponentId.set(peerId);
                assertEquals(40.4153, latitude, 0.0001);
                assertEquals(-3.6845, longitude, 0.0001);
                assertEquals(15.5f, speedKmh, 0.1f);
                assertEquals(1731526400000L, timestamp);
            }

            @Override
            public void onOpponentDerezzed(String peerId, String reason) {}

            @Override
            public void onArenaSyncReceived(double centerLatitude, double centerLongitude, double radiusMeters) {}

            @Override
            public void onPingoStatusChanged(boolean isInstalled, boolean isConnected) {}
        });

        String payload = "{\"type\":\"tron_pos\",\"lat\":40.4153,\"lng\":-3.6845,\"speed\":15.5,\"timestamp\":1731526400000}";
        bridgeManager.processPayload("peer_bob", payload);

        assertTrue(positionReceived.get());
        assertEquals("peer_bob", opponentId.get());
    }

    @Test
    public void testDerezzedPayloadParsing() {
        AtomicBoolean derezzedReceived = new AtomicBoolean(false);
        AtomicReference<String> derezzedReason = new AtomicReference<>();

        bridgeManager.setListener(new TronP2PListener() {
            @Override
            public void onOpponentPositionReceived(String peerId, double latitude, double longitude, float speedKmh, long timestamp) {}

            @Override
            public void onOpponentDerezzed(String peerId, String reason) {
                derezzedReceived.set(true);
                derezzedReason.set(reason);
            }

            @Override
            public void onArenaSyncReceived(double centerLatitude, double centerLongitude, double radiusMeters) {}

            @Override
            public void onPingoStatusChanged(boolean isInstalled, boolean isConnected) {}
        });

        String payload = "{\"type\":\"tron_derezzed\",\"reason\":\"COLLISION_SELF_TRAIL\"}";
        bridgeManager.processPayload("peer_alice", payload);

        assertTrue(derezzedReceived.get());
        assertEquals("COLLISION_SELF_TRAIL", derezzedReason.get());
    }

    @Test
    public void testArenaSyncPayloadParsing() {
        AtomicBoolean syncReceived = new AtomicBoolean(false);

        bridgeManager.setListener(new TronP2PListener() {
            @Override
            public void onOpponentPositionReceived(String peerId, double latitude, double longitude, float speedKmh, long timestamp) {}

            @Override
            public void onOpponentDerezzed(String peerId, String reason) {}

            @Override
            public void onArenaSyncReceived(double centerLatitude, double centerLongitude, double radiusMeters) {
                syncReceived.set(true);
                assertEquals(40.4153, centerLatitude, 0.0001);
                assertEquals(-3.6845, centerLongitude, 0.0001);
                assertEquals(150.0, radiusMeters, 0.1);
            }

            @Override
            public void onPingoStatusChanged(boolean isInstalled, boolean isConnected) {}
        });

        String payload = "{\"type\":\"tron_arena_sync\",\"centerLat\":40.4153,\"centerLng\":-3.6845,\"radius\":150.0}";
        bridgeManager.processPayload("host_peer", payload);

        assertTrue(syncReceived.get());
    }

    @Test
    public void testMatchScopingFilterRejectsMismatchedMatch() {
        AtomicBoolean invoked = new AtomicBoolean(false);

        bridgeManager.setListener(new TronP2PListener() {
            @Override
            public void onOpponentPositionReceived(String peerId, double latitude, double longitude, float speedKmh, long timestamp) {
                invoked.set(true);
            }

            @Override
            public void onOpponentDerezzed(String peerId, String reason) {}

            @Override
            public void onArenaSyncReceived(double centerLatitude, double centerLongitude, double radiusMeters) {}

            @Override
            public void onPingoStatusChanged(boolean isInstalled, boolean isConnected) {}
        });

        Intent mismatchedIntent = new Intent(PingoBridgeManager.ACTION_TRON_RECEIVE_PACKET);
        mismatchedIntent.putExtra(PingoBridgeManager.EXTRA_MATCH_ID, "OTHER_MATCH_99");
        mismatchedIntent.putExtra(PingoBridgeManager.EXTRA_SENDER_PEER_ID, "peer_alice");
        mismatchedIntent.putExtra(PingoBridgeManager.EXTRA_PAYLOAD, "{\"type\":\"tron_pos\",\"lat\":40.0,\"lng\":-3.0}");

        bridgeManager.handleIncomingIntent(mismatchedIntent);
        assertFalse("Packet from another match must be rejected to protect match isolation", invoked.get());
    }

    @Test
    public void testOpponentTargetFilterRejectsMismatchedOpponent() {
        AtomicBoolean invoked = new AtomicBoolean(false);
        bridgeManager.setTargetOpponentPeerId("designated_opponent");

        bridgeManager.setListener(new TronP2PListener() {
            @Override
            public void onOpponentPositionReceived(String peerId, double latitude, double longitude, float speedKmh, long timestamp) {
                invoked.set(true);
            }

            @Override
            public void onOpponentDerezzed(String peerId, String reason) {}

            @Override
            public void onArenaSyncReceived(double centerLatitude, double centerLongitude, double radiusMeters) {}

            @Override
            public void onPingoStatusChanged(boolean isInstalled, boolean isConnected) {}
        });

        Intent unrelatedPeerIntent = new Intent(PingoBridgeManager.ACTION_TRON_RECEIVE_PACKET);
        unrelatedPeerIntent.putExtra(PingoBridgeManager.EXTRA_MATCH_ID, "TRON-ALPHA");
        unrelatedPeerIntent.putExtra(PingoBridgeManager.EXTRA_SENDER_PEER_ID, "random_agenda_contact");
        unrelatedPeerIntent.putExtra(PingoBridgeManager.EXTRA_PAYLOAD, "{\"type\":\"tron_pos\",\"lat\":40.0,\"lng\":-3.0}");

        bridgeManager.handleIncomingIntent(unrelatedPeerIntent);
        assertFalse("Packet from a peer other than designated opponent must be dropped", invoked.get());
    }

    @Test
    public void testOpponentTargetFilterAcceptsMatchingOpponent() {
        AtomicBoolean invoked = new AtomicBoolean(false);
        bridgeManager.setTargetOpponentPeerId("designated_opponent");

        bridgeManager.setListener(new TronP2PListener() {
            @Override
            public void onOpponentPositionReceived(String peerId, double latitude, double longitude, float speedKmh, long timestamp) {
                invoked.set(true);
            }

            @Override
            public void onOpponentDerezzed(String peerId, String reason) {}

            @Override
            public void onArenaSyncReceived(double centerLatitude, double centerLongitude, double radiusMeters) {}

            @Override
            public void onPingoStatusChanged(boolean isInstalled, boolean isConnected) {}
        });

        Intent matchingIntent = new Intent(PingoBridgeManager.ACTION_TRON_RECEIVE_PACKET);
        matchingIntent.putExtra(PingoBridgeManager.EXTRA_MATCH_ID, "TRON-ALPHA");
        matchingIntent.putExtra(PingoBridgeManager.EXTRA_SENDER_PEER_ID, "designated_opponent");
        matchingIntent.putExtra(PingoBridgeManager.EXTRA_PAYLOAD, "{\"type\":\"tron_pos\",\"lat\":40.0,\"lng\":-3.0}");

        bridgeManager.handleIncomingIntent(matchingIntent);
        assertTrue("Packet from designated opponent must be accepted", invoked.get());
    }
}

