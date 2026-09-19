package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.graphics.Style;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.layer.overlay.Polyline;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.InputStream;

import c2.n2;
import c2.q3;
import c2.r;
import c2.x3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class RivasTrackRealWorldTest {

    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        AndroidGraphicFactory.createInstance(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void testPolylinePaintIsStrokeNotFill() {
        // Style 2 represents STROKE for track lines
        Paint linePaint = n2.a(Color.BLUE, 3, 2);
        assertEquals("Track polyline paint MUST have Style.STROKE to avoid filled polygon bug",
                android.graphics.Paint.Style.STROKE, AndroidGraphicFactory.getPaint(linePaint).getStyle());

        // Style 1 represents FILL for polygon interiors
        Paint fillPaint = n2.a(Color.BLUE, 1, 1);
        assertEquals("Interior polygon paint MUST have Style.FILL",
                android.graphics.Paint.Style.FILL, AndroidGraphicFactory.getPaint(fillPaint).getStyle());

        // Test polyline creation
        Polyline polyline = new Polyline(linePaint, AndroidGraphicFactory.INSTANCE);
        assertEquals(android.graphics.Paint.Style.STROKE, AndroidGraphicFactory.getPaint(polyline.getPaintStroke()).getStyle());
    }

    @Test
    public void testRivasCircularTrackImportAndRemainingDistance() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("rivas_track.gpx");
        assertNotNull("rivas_track.gpx must exist in test resources", is);

        r parser = new r(context);
        boolean parseSuccess = parser.a(is, context);
        assertTrue("GPX import must succeed", parseSuccess);
        assertEquals("Track should have 2969 points", 2969, parser.f3137h);

        // Load into track model
        x3 track = new x3(2);
        boolean loadSuccess = track.a("Lagunas de Rivas", parser);
        assertTrue("Track model load must succeed", loadSuccess);

        assertNotNull("Track points list must be initialized", track.f3238c);
        assertEquals(2969, track.f3238c.size());

        assertNotNull("Cumulative distance array must be initialized", track.f3239d);
        assertEquals(2969, track.f3239d.length);

        double totalDistance = track.f3239d[2968];
        assertTrue("Total track distance should be ~17.2 km, was: " + totalDistance,
                totalDistance > 17000.0d && totalDistance < 17500.0d);

        // Case 1: User standing at start point (Velilla de San Antonio, 40.366066, -3.490689)
        Location userAtStart = new Location("gps");
        userAtStart.setLatitude(40.366066);
        userAtStart.setLongitude(-3.490689);

        x3.RemainingDistanceResult resStart = track.calculateRemainingDistance(userAtStart, false, -1);
        assertFalse("At start of circular track, destination should NOT be reached", resStart.isDestinationReached);
        assertTrue("Remaining distance should be around ~17.2 km, was: " + resStart.remainingDistanceMeters,
                resStart.remainingDistanceMeters > 17000.0d && resStart.remainingDistanceMeters <= totalDistance);
        assertEquals(0, resStart.closestWaypointIndex);

        // Case 2: User at halfway point (around index 1484)
        Location midwayLoc = track.f3238c.get(1484);
        Location userAtMid = new Location("gps");
        userAtMid.setLatitude(midwayLoc.getLatitude());
        userAtMid.setLongitude(midwayLoc.getLongitude());

        x3.RemainingDistanceResult resMid = track.calculateRemainingDistance(userAtMid, false, 1400);
        assertFalse("Midway through track, destination should NOT be reached", resMid.isDestinationReached);
        double midRemaining = resMid.remainingDistanceMeters;
        assertTrue("Remaining distance midway should be around 8-9 km, was: " + midRemaining,
                midRemaining > 7500.0d && midRemaining < 10000.0d);

        // Case 3: User finishes track and returns to start/end point after covering track
        Location userAtEnd = track.f3238c.get(2968);
        x3.RemainingDistanceResult resEnd = track.calculateRemainingDistance(userAtEnd, false, 2960);
        assertTrue("At end of track after following, destination SHOULD be reached", resEnd.isDestinationReached);
        assertTrue("Remaining distance at end should be near 0", resEnd.remainingDistanceMeters < 30.0d);
    }

    @Test
    public void testElevationProfileDataPreparationFromGpx() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("rivas_track.gpx");
        assertNotNull("rivas_track.gpx must exist in test resources", is);

        r parser = new r(context);
        boolean parseSuccess = parser.a(is, context);
        assertTrue("GPX import must succeed", parseSuccess);

        x3 track = new x3(2);
        boolean loadSuccess = track.a("Lagunas de Rivas", parser);
        assertTrue("Track model load must succeed", loadSuccess);

        // Test preparing elevation data points for graph plotting
        c2.y3.ElevationDataPoints points = c2.y3.prepareElevationDataPoints(track);
        assertNotNull("Elevation points result should not be null", points);
        assertEquals("Should have exactly the same number of data points as track points", 2969, points.pointCount);
        assertEquals(2969, points.gpsDataPoints.length);
        assertEquals(2969, points.demDataPoints.length);

        // First point checks
        p5.b firstPointGps = points.gpsDataPoints[0];
        p5.b firstPointDem = points.demDataPoints[0];
        assertEquals("First point X distance should be 0.0", 0.0d, firstPointGps.f17269h, 0.001d);
        assertEquals("First point DEM X distance should be 0.0", 0.0d, firstPointDem.f17269h, 0.001d);
        assertTrue("First point GPS altitude should be valid (> 500m for Madrid area)", firstPointGps.f17270i > 500.0d);

        // Last point checks
        p5.b lastPointGps = points.gpsDataPoints[2968];
        assertTrue("Last point X distance should be ~17.25 km", lastPointGps.f17269h > 17.0d && lastPointGps.f17269h < 17.5d);
        assertTrue("Last point altitude should be valid (> 500m)", lastPointGps.f17270i > 500.0d);

        // Verify distance strictly non-decreasing
        for (int i = 1; i < points.pointCount; i++) {
            assertTrue("Distance must be monotonically increasing at point " + i,
                    points.gpsDataPoints[i].f17269h >= points.gpsDataPoints[i - 1].f17269h);
            assertFalse("GPS altitude cannot be NaN", Double.isNaN(points.gpsDataPoints[i].f17270i));
            assertFalse("GPS distance cannot be NaN", Double.isNaN(points.gpsDataPoints[i].f17269h));
        }
    }

    @Test
    public void testElevationGraphRenderingWithFullRealWorldGpxTrack() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("rivas_track.gpx");
        assertNotNull("rivas_track.gpx must exist", is);
        r parser = new r(context);
        boolean parseOk = parser.a(is, context);
        assertTrue("GPX parse must succeed", parseOk);

        x3 track = new x3(2);
        boolean loadOk = track.a("Lagunas de Rivas", parser);
        assertTrue("Track load must succeed", loadOk);

        android.view.LayoutInflater inflater = android.view.LayoutInflater.from(context);
        android.view.View graphWindowView = inflater.inflate(com.giobat.AgpsTrackerPP.R.layout.ii_graph_window, null);
        assertNotNull(graphWindowView);

        c2.y3 elevationProfile = new c2.y3(context, graphWindowView, track, "Lagunas de Rivas", true);
        assertNotNull(elevationProfile);

        c2.y3.ElevationDataPoints points = c2.y3.prepareElevationDataPoints(track);
        assertNotNull(points);
        assertEquals(2969, points.pointCount);

        elevationProfile.renderElevationGraph(points.gpsDataPoints, points.demDataPoints, points.pointCount);

        com.jjoe64.graphview.GraphView graphView = graphWindowView.findViewById(com.giobat.AgpsTrackerPP.R.id.graph_elevation);
        assertNotNull(graphView);
        assertTrue("GraphView should have series added after render", graphView.f4474h.size() >= 2);

        // Verify canvas drawing completes without infinite loops, hangs or OOM on full 2969-point track
        android.graphics.Bitmap bitmap = android.graphics.Bitmap.createBitmap(400, 200, android.graphics.Bitmap.Config.ARGB_8888);
        android.graphics.Canvas canvas = new android.graphics.Canvas(bitmap);
        graphView.layout(0, 0, 400, 200);
        graphView.draw(canvas);
        assertNotNull("Canvas drawing should complete successfully", bitmap);
    }
}
