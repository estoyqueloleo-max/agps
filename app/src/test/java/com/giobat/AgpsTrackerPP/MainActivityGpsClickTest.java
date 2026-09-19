package com.giobat.AgpsTrackerPP;

import static org.junit.Assert.*;

import android.location.Location;
import android.view.View;
import android.widget.TextView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class MainActivityGpsClickTest {

    @Test
    public void testClickShowGpsStatusAndToggle() {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).setup().get();
        
        Location loc = new Location("gps");
        loc.setLatitude(40.4168);
        loc.setLongitude(-3.7038);
        loc.setAltitude(650.0);
        loc.setAccuracy(10.0f);
        loc.setTime(System.currentTimeMillis());

        MainActivity.f3613a1 = loc;
        IntServLocGpsPP.F = loc;
        MainActivity.O0 = 3; // high quality

        // 1. Click GPS status button -> opens GPS lavagna
        activity.onClick_showGpsStatus(null);
        assertEquals(3, activity.B);
        View gpsLavagna = activity.findViewById(R.id.gps_data_lavagna);
        assertNotNull(gpsLavagna);
        assertEquals(View.VISIBLE, gpsLavagna.getVisibility());

        // Verify formatted coordinates in lavagna
        TextView tvGpsText = (TextView) activity.findViewById(R.id.lavagna_gps_text);
        assertNotNull(tvGpsText);
        String text = tvGpsText.getText().toString();
        assertTrue(text.contains("40.41680") || text.contains("40,41680"));
        assertTrue(text.contains("WGS84"));

        // 2. Click GPS status button again -> toggles and closes
        activity.onClick_showGpsStatus(null);
        assertEquals(4, activity.B);
        assertEquals(View.INVISIBLE, gpsLavagna.getVisibility());

        // 3. Open again and test closing via lavagna_gps_off
        activity.onClick_showGpsStatus(null);
        assertEquals(3, activity.B);
        assertEquals(View.VISIBLE, gpsLavagna.getVisibility());

        activity.onClick_lavagnaOff(null);
        assertEquals(4, activity.B);
        assertEquals(View.INVISIBLE, gpsLavagna.getVisibility());
    }

    @Test
    public void testGpsCoordinateEdgeCasesDoNotCrash() {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).setup().get();

        // Edge case: North Pole (MGRS / UTM special zones)
        Location poleLoc = new Location("gps");
        poleLoc.setLatitude(89.5);
        poleLoc.setLongitude(0.0);
        poleLoc.setAltitude(10.0);
        poleLoc.setAccuracy(5.0f);
        MainActivity.f3613a1 = poleLoc;
        IntServLocGpsPP.F = poleLoc;
        MainActivity.O0 = 3;

        String formatted = activity.p0();
        assertNotNull(formatted);
        assertTrue(formatted.contains("WGS84") || formatted.contains("89.50000"));

        // Edge case: Equator / Prime Meridian (0, 0)
        Location zeroLoc = new Location("gps");
        zeroLoc.setLatitude(0.0);
        zeroLoc.setLongitude(0.0);
        zeroLoc.setAltitude(0.0);
        MainActivity.f3613a1 = zeroLoc;
        IntServLocGpsPP.F = zeroLoc;

        formatted = activity.p0();
        assertNotNull(formatted);
        assertTrue(formatted.contains("WGS84"));
    }

    @Test
    public void testUIControlsInteractions() {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).setup().get();

        // Test statistics toggle
        boolean initialStats = activity.f3641c0;
        activity.onClick_showStatisticsOfCurrentTrack(null);
        assertEquals(!initialStats, activity.f3641c0);
        activity.onClick_showStatisticsOfCurrentTrack(null);
        assertEquals(initialStats, activity.f3641c0);

        // Test compass click does not crash
        activity.onCompassClick(null);
    }
}
