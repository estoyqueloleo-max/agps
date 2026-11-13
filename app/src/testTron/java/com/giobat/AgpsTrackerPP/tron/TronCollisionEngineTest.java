package com.giobat.AgpsTrackerPP.tron;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TronCollisionEngineTest {

    // Parque del Retiro / Madrid reference center
    private static final double CENTER_LAT = 40.4153;
    private static final double CENTER_LON = -3.6845;
    private static final double ARENA_RADIUS = 250.0; // 250 meters

    private ArenaGeofence arena;
    private TronCollisionEngine engine;

    @Before
    public void setUp() {
        arena = new ArenaGeofence(CENTER_LAT, CENTER_LON, ARENA_RADIUS);
        engine = new TronCollisionEngine(arena, 8.0, 1.0);
    }

    @Test
    public void testFirstPointAcceptedWithoutCollision() {
        CollisionResult result = engine.processNewLocation("player1", CENTER_LAT, CENTER_LON, 1000L);
        assertFalse("First point inside arena should not collide", result.hasCollided());
        assertEquals(CollisionResult.CollisionType.NONE, result.getType());

        LightTrail trail = engine.getTrail("player1");
        assertNotNull(trail);
        assertEquals(1, trail.getPoints().size());
    }

    @Test
    public void testPointOutsideArenaTriggersBoundaryCollision() {
        // Point ~1000 meters away (approx +0.01 deg lat is ~1110m)
        double outsideLat = CENTER_LAT + 0.01;
        double outsideLon = CENTER_LON;

        CollisionResult result = engine.processNewLocation("player1", outsideLat, outsideLon, 1000L);
        assertTrue("Point outside 250m arena should trigger collision", result.hasCollided());
        assertEquals(CollisionResult.CollisionType.ARENA_BOUNDARY, result.getType());
    }

    @Test
    public void testStraightLineMovementNoCollision() {
        // Walk 50 meters North in 5 steps of 10m (~0.00009 degrees per 10m)
        long time = 1000L;
        for (int i = 0; i < 5; i++) {
            double lat = CENTER_LAT + (i * 0.00009);
            CollisionResult result = engine.processNewLocation("player1", lat, CENTER_LON, time);
            assertFalse("Straight movement should have no collisions", result.hasCollided());
            time += 2000L;
        }

        LightTrail trail = engine.getTrail("player1");
        assertEquals(5, trail.getPoints().size());
        assertEquals(4, trail.getSegments().size());
        assertTrue("Trail total distance should be around 40m", trail.getTotalDistanceMeters() > 35.0);
    }

    @Test
    public void testSelfLoopTriggersSelfTrailCollision() {
        // Make a box: North 30m, East 30m, South 30m, West 30m (crossing the start)
        double step = 0.0003; // ~33m
        long time = 1000L;

        // P0: (0, 0)
        assertFalse(engine.processNewLocation("player1", CENTER_LAT, CENTER_LON, time).hasCollided());
        // P1: (30m N, 0)
        time += 5000L;
        assertFalse(engine.processNewLocation("player1", CENTER_LAT + step, CENTER_LON, time).hasCollided());
        // P2: (30m N, 30m E)
        time += 5000L;
        assertFalse(engine.processNewLocation("player1", CENTER_LAT + step, CENTER_LON + step, time).hasCollided());
        // P3: (0, 30m E)
        time += 5000L;
        assertFalse(engine.processNewLocation("player1", CENTER_LAT, CENTER_LON + step, time).hasCollided());
        // P4: (0, -10m W) -> Crosses the first segment between P0 and P1!
        time += 5000L;
        CollisionResult collision = engine.processNewLocation("player1", CENTER_LAT + (step * 0.5), CENTER_LON - (step * 0.2), time);

        assertTrue("Closing loop crossing own path must trigger self collision", collision.hasCollided());
        assertEquals(CollisionResult.CollisionType.SELF_TRAIL, collision.getType());
    }

    @Test
    public void testUTurnWithinImmunityBufferDoesNotTriggerFalseCollision() {
        // Start moving North 4 meters, then turn around and come back 3 meters
        // Total movement is 7 meters, within the 8.0m immunity buffer
        long time = 1000L;
        assertFalse(engine.processNewLocation("player1", CENTER_LAT, CENTER_LON, time).hasCollided());

        // 4 meters North (~0.000036 deg)
        time += 2000L;
        assertFalse(engine.processNewLocation("player1", CENTER_LAT + 0.000036, CENTER_LON, time).hasCollided());

        // 2 meters South (turning back, path overlap within immunity buffer)
        time += 2000L;
        CollisionResult result = engine.processNewLocation("player1", CENTER_LAT + 0.000018, CENTER_LON, time);
        assertFalse("U-turn within immunity buffer should not trigger false collision", result.hasCollided());
    }

    @Test
    public void testOpponentTrailCollisionCross() {
        // Player 1 walks North: from (0, -10m) to (0, +10m)
        long time = 1000L;
        engine.processNewLocation("player1", CENTER_LAT - 0.0001, CENTER_LON, time);
        engine.processNewLocation("player1", CENTER_LAT + 0.0001, CENTER_LON, time + 2000L);

        // Player 2 walks East across Player 1's path: from (-10m W, 0) to (+10m E, 0)
        engine.processNewLocation("player2", CENTER_LAT, CENTER_LON - 0.0001, time + 1000L);
        CollisionResult p2Collision = engine.processNewLocation("player2", CENTER_LAT, CENTER_LON + 0.0001, time + 3000L);

        assertTrue("Player 2 crossing Player 1's trail must trigger opponent collision", p2Collision.hasCollided());
        assertEquals(CollisionResult.CollisionType.OPPONENT_TRAIL, p2Collision.getType());
        assertEquals("player1", p2Collision.getCollidedWithPlayerId());
    }

    @Test
    public void testDynamicShrinkingArena() {
        // Arena starts with 100m radius, shrinks at 2m/s down to 20m, starting at t=10000L
        ArenaGeofence shrinkingArena = new ArenaGeofence(CENTER_LAT, CENTER_LON, 100.0, 20.0, 2.0, 10000L);
        engine.setArena(shrinkingArena);

        // Point is ~60m from center (~0.00054 deg)
        double testLat = CENTER_LAT + 0.00054;
        double testLon = CENTER_LON;

        // At t = 10000L (start), radius is 100m -> Point is inside
        CollisionResult r1 = engine.processNewLocation("player1", testLat, testLon, 10000L);
        assertFalse("At t=0s elapsed, radius is 100m, 60m point is safe", r1.hasCollided());

        // At t = 40000L (30s elapsed), radius = 100 - (30 * 2) = 40m -> Point (60m) is now outside!
        CollisionResult r2 = engine.processNewLocation("player1", testLat + 0.00001, testLon, 40000L);
        assertTrue("At t=30s, arena radius has shrunk past the player position", r2.hasCollided());
        assertEquals(CollisionResult.CollisionType.ARENA_BOUNDARY, r2.getType());
    }

    @Test
    public void testWithRealWorldRivasGpxTrackData() throws Exception {
        java.io.InputStream inputStream = getClass().getClassLoader().getResourceAsStream("rivas_track.gpx");
        assertNotNull("rivas_track.gpx must exist in test resources", inputStream);

        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(inputStream));
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<trkpt\\s+lat=\"([^\"]+)\"\\s+lon=\"([^\"]+)\"");

        java.util.List<double[]> points = new java.util.ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            java.util.regex.Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                double lat = Double.parseDouble(matcher.group(1));
                double lon = Double.parseDouble(matcher.group(2));
                points.add(new double[]{lat, lon});
            }
        }
        reader.close();

        assertTrue("Rivas track should have over 2000 points", points.size() > 2000);

        // Center arena at the trailhead of the Rivas trail (point index 2 where hike begins)
        double startLat = points.get(2)[0];
        double startLon = points.get(2)[1];
        ArenaGeofence wideArena = new ArenaGeofence(startLat, startLon, 15000.0);
        TronCollisionEngine realTrackEngine = new TronCollisionEngine(wideArena, 15.0, 1.0);

        // Feed points 2 to 102 (clean continuous trekking path along the trail)
        long timestamp = 1000L;
        for (int i = 2; i < 102; i++) {
            double[] pt = points.get(i);
            CollisionResult result = realTrackEngine.processNewLocation("hiker", pt[0], pt[1], timestamp);
            assertFalse("Clean trail path should not self-collide at point index " + i, result.hasCollided());
            timestamp += 2000L;
        }

        LightTrail trail = realTrackEngine.getTrail("hiker");
        assertEquals(100, trail.getPoints().size());
        assertTrue("Total trail distance should exceed 200 meters", trail.getTotalDistanceMeters() > 200.0);

        // Verify that an opponent crossing the real trail (across segment P2->P3) triggers an OPPONENT_TRAIL collision
        realTrackEngine.processNewLocation("rival", 40.365980, -3.490850, 300000L);
        CollisionResult opponentCross = realTrackEngine.processNewLocation("rival", 40.365980, -3.490650, 305000L);
        assertTrue("Opponent crossing the real GPX trail segment should trigger collision", opponentCross.hasCollided());
        assertEquals(CollisionResult.CollisionType.OPPONENT_TRAIL, opponentCross.getType());
        assertEquals("hiker", opponentCross.getCollidedWithPlayerId());
    }
}

