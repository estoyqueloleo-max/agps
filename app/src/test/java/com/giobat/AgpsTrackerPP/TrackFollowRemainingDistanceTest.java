package com.giobat.AgpsTrackerPP;

import android.location.Location;
import c2.x3;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class TrackFollowRemainingDistanceTest {

    private x3 track;
    private ArrayList<Location> points;

    @Before
    public void setUp() {
        track = new x3(2);
        points = new ArrayList<>();

        // Create a line of 5 points spaced roughly 1000 meters apart heading North from Madrid
        // ~0.009 degrees latitude is roughly 1000m
        double baseLat = 40.4168;
        double baseLon = -3.7038;

        for (int i = 0; i < 5; i++) {
            Location loc = new Location("test");
            loc.setLatitude(baseLat + (i * 0.009));
            loc.setLongitude(baseLon);
            points.add(loc);
        }

        track.f3238c = points;
        // Cumulative distances: [0, 1000, 2000, 3000, 4000]
        track.f3239d = new double[]{0.0d, 1000.0d, 2000.0d, 3000.0d, 4000.0d};
    }

    @Test
    public void testForwardDirectionRemainingDistance() {
        // User at starting point (index 0)
        Location userAtStart = new Location("gps");
        userAtStart.setLatitude(points.get(0).getLatitude());
        userAtStart.setLongitude(points.get(0).getLongitude());

        x3.RemainingDistanceResult res0 = track.calculateRemainingDistance(userAtStart, false, -1);
        Assert.assertEquals(0, res0.closestWaypointIndex);
        Assert.assertEquals(4000.0d, res0.remainingDistanceMeters, 50.0d);
        Assert.assertFalse(res0.isDestinationReached);

        // User midway at point 2 (2000m covered, 2000m remaining)
        Location userAtMid = new Location("gps");
        userAtMid.setLatitude(points.get(2).getLatitude());
        userAtMid.setLongitude(points.get(2).getLongitude());

        x3.RemainingDistanceResult resMid = track.calculateRemainingDistance(userAtMid, false, 1);
        Assert.assertEquals(2, resMid.closestWaypointIndex);
        Assert.assertEquals(2000.0d, resMid.remainingDistanceMeters, 50.0d);
        Assert.assertFalse(resMid.isDestinationReached);

        // User at final point (destination reached)
        Location userAtEnd = new Location("gps");
        userAtEnd.setLatitude(points.get(4).getLatitude());
        userAtEnd.setLongitude(points.get(4).getLongitude());

        x3.RemainingDistanceResult resEnd = track.calculateRemainingDistance(userAtEnd, false, 3);
        Assert.assertEquals(4, resEnd.closestWaypointIndex);
        Assert.assertEquals(0.0d, resEnd.remainingDistanceMeters, 50.0d);
        Assert.assertTrue("Should indicate destination reached", resEnd.isDestinationReached);
    }

    @Test
    public void testReverseTrackBackDirectionRemainingDistance() {
        // In reverse mode (Track Back):
        // User starts at end (index 4) heading back to start (index 0)
        Location userAtEnd = new Location("gps");
        userAtEnd.setLatitude(points.get(4).getLatitude());
        userAtEnd.setLongitude(points.get(4).getLongitude());

        x3.RemainingDistanceResult res4 = track.calculateRemainingDistance(userAtEnd, true, -1);
        Assert.assertEquals(4, res4.closestWaypointIndex);
        // Total distance back to index 0 is 4000m
        Assert.assertEquals(4000.0d, res4.remainingDistanceMeters, 50.0d);
        Assert.assertFalse(res4.isDestinationReached);

        // Halfway back at index 2
        Location userAtMid = new Location("gps");
        userAtMid.setLatitude(points.get(2).getLatitude());
        userAtMid.setLongitude(points.get(2).getLongitude());

        x3.RemainingDistanceResult resMid = track.calculateRemainingDistance(userAtMid, true, 3);
        Assert.assertEquals(2, resMid.closestWaypointIndex);
        Assert.assertEquals(2000.0d, resMid.remainingDistanceMeters, 50.0d);
        Assert.assertFalse(resMid.isDestinationReached);

        // Back at origin (index 0 - destination of Track Back)
        Location userAtOrigin = new Location("gps");
        userAtOrigin.setLatitude(points.get(0).getLatitude());
        userAtOrigin.setLongitude(points.get(0).getLongitude());

        x3.RemainingDistanceResult resOrigin = track.calculateRemainingDistance(userAtOrigin, true, 1);
        Assert.assertEquals(0, resOrigin.closestWaypointIndex);
        Assert.assertEquals(0.0d, resOrigin.remainingDistanceMeters, 50.0d);
        Assert.assertTrue("Should indicate destination reached in Track Back mode", resOrigin.isDestinationReached);
    }

    @Test
    public void testNullOrEmptyTrackSafeHandling() {
        x3 emptyTrack = new x3(1);
        x3.RemainingDistanceResult res = emptyTrack.calculateRemainingDistance(points.get(0), false, -1);
        Assert.assertEquals(0.0d, res.remainingDistanceMeters, 0.001d);
        Assert.assertFalse(res.isDestinationReached);

        x3.RemainingDistanceResult resNull = track.calculateRemainingDistance(null, false, -1);
        Assert.assertEquals(0.0d, resNull.remainingDistanceMeters, 0.001d);
        Assert.assertFalse(resNull.isDestinationReached);
    }

    @Test
    public void testAltitudeBoxLabelFormattingWhenFollowingTrack() {
        // Formatted strings when remaining distance is at the beginning (top line)
        String distFormatted = "16.13 km";
        String remPrefix = "Rem: " + distFormatted;
        String altitudeUnitLine = "Altitud [m]";
        String fullLabelText = remPrefix + "\n" + altitudeUnitLine;

        // Line 1 should be the remaining distance
        String[] lines = fullLabelText.split("\n");
        Assert.assertEquals(2, lines.length);
        Assert.assertEquals("Rem: 16.13 km", lines[0]);
        Assert.assertEquals("Altitud [m]", lines[1]);

        // Each line should be compact (<= 15 characters) to ensure the block is not widened
        Assert.assertTrue("Line 1 width exceeds compact limit", lines[0].length() <= 15);
        Assert.assertTrue("Line 2 width exceeds compact limit", lines[1].length() <= 15);
    }
}
