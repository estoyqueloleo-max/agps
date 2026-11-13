package com.giobat.AgpsTrackerPP.tron;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.location.Location;
import android.widget.Button;
import android.widget.TextView;

import com.giobat.AgpsTrackerPP.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class TronArenaActivityRobolectricTest {

    private ActivityController<TronArenaActivity> controller;
    private TronArenaActivity activity;

    @Before
    public void setUp() {
        // Ensure AndroidGraphicFactory instance exists for Mapsforge views
        try {
            AndroidGraphicFactory.createInstance(org.robolectric.RuntimeEnvironment.getApplication());
        } catch (Exception ignored) {
        }
        controller = Robolectric.buildActivity(TronArenaActivity.class);
        activity = controller.setup().get();
    }

    @org.junit.After
    public void tearDown() {
        if (controller != null) {
            controller.pause().stop().destroy();
            controller = null;
        }
    }

    @Test
    public void testLayoutInflationAndInitialState() {
        assertNotNull("Activity should not be null", activity);

        TextView tvTimer = activity.findViewById(R.id.tron_tv_timer);
        TextView tvSpeed = activity.findViewById(R.id.tron_tv_speed);
        TextView tvRadius = activity.findViewById(R.id.tron_tv_radius);
        TextView tvStatus = activity.findViewById(R.id.tron_tv_status);
        Button btnStart = activity.findViewById(R.id.btn_tron_start);

        assertNotNull(tvTimer);
        assertNotNull(tvSpeed);
        assertNotNull(tvRadius);
        assertNotNull(tvStatus);
        assertNotNull(btnStart);

        assertEquals("00:00", tvTimer.getText().toString());
        assertEquals("0.0 km/h", tvSpeed.getText().toString());
        assertEquals("250 m", tvRadius.getText().toString());
        assertEquals("READY", tvStatus.getText().toString());
        assertEquals("START", btnStart.getText().toString());
    }

    @Test
    public void testStartAndPauseMatchTransitions() {
        Button btnStart = activity.findViewById(R.id.btn_tron_start);
        TextView tvStatus = activity.findViewById(R.id.tron_tv_status);

        // Click Start
        btnStart.performClick();
        assertEquals("ACTIVE", tvStatus.getText().toString());
        assertEquals("PAUSE", btnStart.getText().toString());

        // Click Pause
        btnStart.performClick();
        assertEquals("PAUSED", tvStatus.getText().toString());
        assertEquals("RESUME", btnStart.getText().toString());
    }

    @Test
    public void testShrinkRingToggle() {
        Button btnShrinkToggle = activity.findViewById(R.id.btn_tron_shrink_toggle);
        assertEquals("RING: OFF", btnShrinkToggle.getText().toString());

        btnShrinkToggle.performClick();
        assertEquals("RING: ON", btnShrinkToggle.getText().toString());

        btnShrinkToggle.performClick();
        assertEquals("RING: OFF", btnShrinkToggle.getText().toString());
    }

    @Test
    public void testLocationUpdateUpdatesSpeedAndTrail() {
        Button btnStart = activity.findViewById(R.id.btn_tron_start);
        TextView tvSpeed = activity.findViewById(R.id.tron_tv_speed);
        btnStart.performClick();

        Location location = new Location("gps");
        location.setLatitude(40.4153);
        location.setLongitude(-3.6845);
        location.setSpeed(4.5f); // ~16.2 km/h

        activity.onLocationChanged(location);
        assertTrue("Speed text should reflect ~16.2 km/h", tvSpeed.getText().toString().contains("16.2"));
    }

    @Test
    public void testCollisionTriggersDerezzedState() {
        Button btnStart = activity.findViewById(R.id.btn_tron_start);
        TextView tvStatus = activity.findViewById(R.id.tron_tv_status);
        btnStart.performClick();

        // Feed coordinate way outside the arena (10km away)
        Location outOfBounds = new Location("gps");
        outOfBounds.setLatitude(40.5000);
        outOfBounds.setLongitude(-3.6845);

        activity.onLocationChanged(outOfBounds);
        assertEquals("DEREZZED!", tvStatus.getText().toString());
    }

    @Test
    public void testP2pHudViewsPresenceAndInitialState() {
        TextView tvP2pMatch = activity.findViewById(R.id.tron_tv_p2p_match);
        TextView tvP2pOpponent = activity.findViewById(R.id.tron_tv_p2p_opponent);
        TextView tvP2pStatus = activity.findViewById(R.id.tron_tv_p2p_status);
        Button btnP2pLobby = activity.findViewById(R.id.btn_tron_p2p);

        assertNotNull(tvP2pMatch);
        assertNotNull(tvP2pOpponent);
        assertNotNull(tvP2pStatus);
        assertNotNull(btnP2pLobby);

        assertTrue(tvP2pMatch.getText().toString().contains("ROOM:"));
        assertTrue(tvP2pOpponent.getText().toString().contains("RIVAL:"));
        assertEquals("LOBBY", btnP2pLobby.getText().toString());
    }

    @Test
    public void testIncomingOpponentPositionPacketUpdatesHud() {
        Button btnStart = activity.findViewById(R.id.btn_tron_start);
        TextView tvP2pOpponent = activity.findViewById(R.id.tron_tv_p2p_opponent);
        btnStart.performClick();

        android.content.Intent opponentIntent = new android.content.Intent(PingoBridgeManager.ACTION_TRON_RECEIVE_PACKET);
        opponentIntent.putExtra(PingoBridgeManager.EXTRA_MATCH_ID, "TRON-1");
        opponentIntent.putExtra(PingoBridgeManager.EXTRA_SENDER_PEER_ID, "peer_alice");
        opponentIntent.putExtra(PingoBridgeManager.EXTRA_PAYLOAD,
                "{\"type\":\"tron_pos\",\"lat\":40.4154,\"lng\":-3.6844,\"speed\":18.0,\"timestamp\":1731526400000}");

        org.robolectric.RuntimeEnvironment.getApplication().sendBroadcast(opponentIntent);
        org.robolectric.shadows.ShadowLooper.runUiThreadTasksIncludingDelayedTasks();

        assertTrue("Opponent HUD should display received speed", tvP2pOpponent.getText().toString().contains("18"));
    }

    @Test
    public void testIncomingOpponentDerezzedTriggersVictoryState() {
        Button btnStart = activity.findViewById(R.id.btn_tron_start);
        TextView tvStatus = activity.findViewById(R.id.tron_tv_status);
        btnStart.performClick();

        android.content.Intent opponentDerezzedIntent = new android.content.Intent(PingoBridgeManager.ACTION_TRON_RECEIVE_PACKET);
        opponentDerezzedIntent.putExtra(PingoBridgeManager.EXTRA_MATCH_ID, "TRON-1");
        opponentDerezzedIntent.putExtra(PingoBridgeManager.EXTRA_SENDER_PEER_ID, "peer_alice");
        opponentDerezzedIntent.putExtra(PingoBridgeManager.EXTRA_PAYLOAD,
                "{\"type\":\"tron_derezzed\",\"reason\":\"COLLISION_OPPONENT_TRAIL\"}");

        org.robolectric.RuntimeEnvironment.getApplication().sendBroadcast(opponentDerezzedIntent);
        org.robolectric.shadows.ShadowLooper.runUiThreadTasksIncludingDelayedTasks();

        assertEquals("VICTORY!", tvStatus.getText().toString());
    }
}

