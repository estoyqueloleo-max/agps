package com.giobat.AgpsTrackerPP;

import android.location.Location;
import androidx.test.core.app.ApplicationProvider;
import c2.q3;
import c2.x3;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ServiceController;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class OffRouteAudibleAlertTest {

    private ServiceController<IntServLocGpsPP> serviceController;
    private IntServLocGpsPP service;

    @Before
    public void setUp() {
        serviceController = Robolectric.buildService(IntServLocGpsPP.class);
        service = serviceController.get();

        AgpsApplication.m = false;
        AgpsApplication.isFollowLoadedTrackActive = false;
        AgpsApplication.f3571n = false;
        AgpsApplication.isFollowReturnTrackActive = false;
        AgpsApplication.f3572o = false;
        AgpsApplication.isFollowPathAlarmTriggered = false;
        AgpsApplication.f3574q = null;
        AgpsApplication.f3575r = null;
        MainActivity.f3613a1 = new Location("TEST_GPS");
    }

    @After
    public void tearDown() {
        if (service != null && service.offRouteAudioPlayer != null) {
            service.offRouteAudioPlayer.release();
        }
        AgpsApplication.m = false;
        AgpsApplication.isFollowLoadedTrackActive = false;
        AgpsApplication.f3571n = false;
        AgpsApplication.isFollowReturnTrackActive = false;
        AgpsApplication.f3572o = false;
    }

    private x3 createReferenceTrack(double startLat, double startLon) {
        x3 track = new x3();
        q3 segment = new q3("Segment 1", "desc", 0);
        for (int i = 0; i < 5; i++) {
            double lat = startLat + (i * 0.0001);
            double lon = startLon;
            Location loc = new Location("WP");
            loc.setLatitude(lat);
            loc.setLongitude(lon);
            segment.f3123b.add(loc);
            segment.f3122a.add(new org.mapsforge.core.model.LatLong(lat, lon));
        }
        track.f3236a.add(segment);
        return track;
    }

    @Test
    public void testNoAlarmWhenFollowInactive() {
        // GPS location far away
        MainActivity.f3613a1.setLatitude(40.0);
        MainActivity.f3613a1.setLongitude(-3.0);

        service.checkFollowTrackDeviation();

        Assert.assertFalse("Alarm flag should be false when not following any track", AgpsApplication.f3572o);
        Assert.assertEquals(0, service.consecutiveOffRouteReadings);
        Assert.assertEquals(0, service.offRouteAlarmBeepCount);
    }

    @Test
    public void testNoAlarmWhenOnRoute() {
        AgpsApplication.isFollowLoadedTrackActive = true;
        AgpsApplication.m = true;
        AgpsApplication.f3574q = createReferenceTrack(40.4168, -3.7038);

        // Position directly on the first waypoint of the track
        MainActivity.f3613a1.setLatitude(40.4168);
        MainActivity.f3613a1.setLongitude(-3.7038);

        service.checkFollowTrackDeviation();

        Assert.assertFalse("Alarm should not trigger when on track", AgpsApplication.f3572o);
        Assert.assertEquals(0, service.consecutiveOffRouteReadings);
    }

    @Test
    public void testAlarmTriggersAfterThreeConsecutiveDeviations() {
        AgpsApplication.isFollowLoadedTrackActive = true;
        AgpsApplication.m = true;
        AgpsApplication.f3574q = createReferenceTrack(40.4168, -3.7038);

        // Position far away (>1 km away)
        MainActivity.f3613a1.setLatitude(40.4500);
        MainActivity.f3613a1.setLongitude(-3.7038);

        // First off-route reading
        service.checkFollowTrackDeviation();
        Assert.assertEquals(1, service.consecutiveOffRouteReadings);
        Assert.assertFalse("Alarm should not trigger on 1st deviation reading", AgpsApplication.f3572o);

        // Second off-route reading
        service.checkFollowTrackDeviation();
        Assert.assertEquals(2, service.consecutiveOffRouteReadings);
        Assert.assertFalse("Alarm should not trigger on 2nd deviation reading", AgpsApplication.f3572o);

        // Third off-route reading -> threshold reached (>= 3)
        service.checkFollowTrackDeviation();
        Assert.assertEquals(3, service.consecutiveOffRouteReadings);
        Assert.assertTrue("Alarm should trigger on 3rd consecutive deviation", AgpsApplication.f3572o);
        Assert.assertTrue("isFollowPathAlarmTriggered should be true", AgpsApplication.isFollowPathAlarmTriggered);
        Assert.assertTrue("isOffRouteAlarmActive should be true", service.isOffRouteAlarmActive);
        Assert.assertEquals("Beep count should increment to 1", 1, service.offRouteAlarmBeepCount);
        Assert.assertNotNull("offRouteAudioPlayer should be initialized", service.offRouteAudioPlayer);
    }

    @Test
    public void testAlarmResetsWhenReturningToRoute() {
        AgpsApplication.isFollowLoadedTrackActive = true;
        AgpsApplication.m = true;
        AgpsApplication.f3574q = createReferenceTrack(40.4168, -3.7038);

        // Deviate 3 times
        MainActivity.f3613a1.setLatitude(40.4500);
        MainActivity.f3613a1.setLongitude(-3.7038);
        for (int i = 0; i < 3; i++) {
            service.checkFollowTrackDeviation();
        }
        Assert.assertTrue(AgpsApplication.f3572o);

        // Now move back on-route
        MainActivity.f3613a1.setLatitude(40.4168);
        MainActivity.f3613a1.setLongitude(-3.7038);
        service.checkFollowTrackDeviation();

        Assert.assertFalse("Alarm should reset when returning on track", AgpsApplication.f3572o);
        Assert.assertFalse(AgpsApplication.isFollowPathAlarmTriggered);
        Assert.assertEquals(0, service.consecutiveOffRouteReadings);
        Assert.assertEquals(0, service.offRouteAlarmBeepCount);
        Assert.assertFalse(service.isOffRouteAlarmActive);
    }

    @Test
    public void testAlarmWithReturnTrack() {
        AgpsApplication.isFollowReturnTrackActive = true;
        AgpsApplication.f3571n = true;
        AgpsApplication.f3575r = createReferenceTrack(40.4168, -3.7038);

        // Deviate far away
        MainActivity.f3613a1.setLatitude(40.4500);
        MainActivity.f3613a1.setLongitude(-3.7038);

        for (int i = 0; i < 3; i++) {
            service.checkFollowTrackDeviation();
        }

        Assert.assertTrue("Alarm should trigger for return track deviation", AgpsApplication.f3572o);
        Assert.assertEquals(1, service.offRouteAlarmBeepCount);
    }

    @Test
    public void testOffRouteAudioPlayerLifecycle() {
        OffRouteAudioPlayer player = new OffRouteAudioPlayer(ApplicationProvider.getApplicationContext());
        // Safe execution without crashing
        player.playAlert();
        player.stopAlert();
        player.release();
    }
}
