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
}

