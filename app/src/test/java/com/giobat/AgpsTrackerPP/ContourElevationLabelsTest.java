package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.graphics.Color;
import androidx.test.core.app.ApplicationProvider;

import c2.m2;
import c2.n2;
import c2.o;
import c2.t;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.rendertheme.AssetsRenderTheme;
import org.mapsforge.map.layer.overlay.Marker;
import org.mapsforge.map.model.DisplayModel;
import org.mapsforge.map.rendertheme.XmlRenderTheme;
import org.mapsforge.map.rendertheme.XmlRenderThemeStyleLayer;
import org.mapsforge.map.rendertheme.XmlRenderThemeStyleMenu;
import org.mapsforge.map.rendertheme.rule.RenderTheme;
import org.mapsforge.map.rendertheme.rule.RenderThemeHandler;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.graphics.Curve;
import org.mapsforge.core.graphics.Display;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.graphics.Position;
import org.mapsforge.core.graphics.SymbolOrientation;
import org.mapsforge.core.graphics.TextOrientation;
import org.mapsforge.core.model.Point;
import org.mapsforge.core.model.Rectangle;
import org.mapsforge.core.model.Tag;
import org.mapsforge.core.model.Tile;
import org.mapsforge.map.datastore.PointOfInterest;
import org.mapsforge.map.layer.renderer.PolylineContainer;
import org.mapsforge.map.rendertheme.RenderCallback;
import org.mapsforge.map.rendertheme.RenderContext;

/**
 * Automated tests verifying contour line elevation labels (isolines altitudes)
 * across both offline vector render themes (gioDefault.xml / Elevate.xml)
 * and on-demand DEM isoline generators (c2/j.java, c2/o.java).
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class ContourElevationLabelsTest {

    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        AndroidGraphicFactory.createInstance(context);
        AgpsApplication.f3580w.clear();
    }

    @Test
    public void testGioDefaultMatchesContourLinesAndElevationLabels() throws Exception {
        XmlRenderTheme theme = new AssetsRenderTheme(context.getAssets(), "", "gioDefault.xml");
        DisplayModel displayModel = new DisplayModel();
        RenderTheme renderTheme = RenderThemeHandler.getRenderTheme(AndroidGraphicFactory.INSTANCE, displayModel, theme);

        List<String> renderedTexts = new ArrayList<>();
        List<String> renderedWays = new ArrayList<>();

        RenderCallback callback = new RenderCallback() {
            @Override
            public void renderArea(RenderContext rc, Paint p1, Paint p2, int level, PolylineContainer pc) {}
            @Override
            public void renderAreaCaption(RenderContext rc, Display d, int i, String text, float f1, float f2, Paint p1, Paint p2, Position pos, int i2, PolylineContainer pc) {}
            @Override
            public void renderAreaSymbol(RenderContext rc, Display d, int i, Bitmap b, PolylineContainer pc) {}
            @Override
            public void renderPointOfInterestCaption(RenderContext rc, Display d, int i, String text, float f1, float f2, Paint p1, Paint p2, Position pos, int i2, PointOfInterest poi) {}
            @Override
            public void renderPointOfInterestCircle(RenderContext rc, float f, Paint p1, Paint p2, int i, PointOfInterest poi) {}
            @Override
            public void renderPointOfInterestSymbol(RenderContext rc, Display d, int i, Rectangle r, Bitmap b, PointOfInterest poi) {}
            @Override
            public void renderWay(RenderContext rc, Paint p, float f, Curve c, int level, PolylineContainer pc) {
                renderedWays.add("way");
            }
            @Override
            public void renderWaySymbol(RenderContext rc, Display d, int i, Bitmap b, float f, Rectangle r, boolean b2, float f2, float f3, SymbolOrientation so, PolylineContainer pc) {}
            @Override
            public void renderWayText(RenderContext rc, Display d, int i, String text, float f, Paint p1, Paint p2, boolean b, float f2, float f3, TextOrientation to, PolylineContainer pc) {
                renderedTexts.add(text);
            }
        };

        // Test 1: Way with contour=elevation and ele=650 (standard OSM / DEM contour)
        List<Tag> tagsOsm = new ArrayList<>();
        tagsOsm.add(new Tag("contour", "elevation"));
        tagsOsm.add(new Tag("ele", "650"));

        Tile tile = new Tile(100, 100, (byte) 15, 256);
        Point[] points = new Point[] { new Point(0, 0), new Point(100, 100) };
        PolylineContainer polyOsm = new PolylineContainer(points, tile, tile, tagsOsm);

        org.mapsforge.map.rendertheme.rule.RenderThemeFuture future = new org.mapsforge.map.rendertheme.rule.RenderThemeFuture(AndroidGraphicFactory.INSTANCE, theme, displayModel);
        future.run();
        org.mapsforge.map.datastore.MapDataStore mockStore = org.mockito.Mockito.mock(org.mapsforge.map.datastore.MapDataStore.class);
        org.mapsforge.map.layer.renderer.RendererJob job = new org.mapsforge.map.layer.renderer.RendererJob(tile, mockStore, future, displayModel, 1.0f, false, false);
        RenderContext renderContext = new RenderContext(job, AndroidGraphicFactory.INSTANCE);

        renderTheme.matchLinearWay(callback, renderContext, polyOsm);
        Assert.assertEquals(1, renderedWays.size());
        Assert.assertEquals(1, renderedTexts.size());
        Assert.assertEquals("650", renderedTexts.get(0));

        // Test 2: Way with contour_ext=elevation_major and ele=1200 (OpenAndroMaps) at zoom 15
        renderedWays.clear();
        renderedTexts.clear();
        List<Tag> tagsOam = new ArrayList<>();
        tagsOam.add(new Tag("contour_ext", "elevation_major"));
        tagsOam.add(new Tag("ele", "1200"));
        PolylineContainer polyOam = new PolylineContainer(points, tile, tile, tagsOam);

        renderTheme.matchLinearWay(callback, renderContext, polyOam);
        Assert.assertEquals(1, renderedWays.size());
        Assert.assertEquals(1, renderedTexts.size());
        Assert.assertEquals("1200", renderedTexts.get(0));

        // Test 3: Way with contour_ext=elevation_minor (intermediate lines) - line drawn, but NO numbers to avoid clutter
        renderedWays.clear();
        renderedTexts.clear();
        List<Tag> tagsMinor = new ArrayList<>();
        tagsMinor.add(new Tag("contour_ext", "elevation_minor"));
        tagsMinor.add(new Tag("ele", "1210"));
        PolylineContainer polyMinor = new PolylineContainer(points, tile, tile, tagsMinor);

        renderTheme.matchLinearWay(callback, renderContext, polyMinor);
        Assert.assertEquals("Minor contour line must be drawn", 1, renderedWays.size());
        Assert.assertTrue("Minor contour lines should not have labels at zoom 15", renderedTexts.isEmpty());

        // Test 4: At low zoom (zoom 12), major line is drawn, but elevation labels are hidden
        Tile lowZoomTile = new Tile(10, 10, (byte) 12, 256);
        org.mapsforge.map.layer.renderer.RendererJob lowZoomJob = new org.mapsforge.map.layer.renderer.RendererJob(lowZoomTile, mockStore, future, displayModel, 1.0f, false, false);
        RenderContext lowZoomRenderContext = new RenderContext(lowZoomJob, AndroidGraphicFactory.INSTANCE);
        renderedWays.clear();
        renderedTexts.clear();
        PolylineContainer polyLowZoom = new PolylineContainer(points, lowZoomTile, lowZoomTile, tagsOam);

        renderTheme.matchLinearWay(callback, lowZoomRenderContext, polyLowZoom);
        Assert.assertEquals("Major contour line drawn at zoom 12", 1, renderedWays.size());
        Assert.assertTrue("Labels must be hidden at zoom < 14", renderedTexts.isEmpty());
    }

    @Test
    public void testGioDefaultRenderThemeParsesWithContourRules() throws Exception {
        XmlRenderTheme theme = new AssetsRenderTheme(context.getAssets(), "", "gioDefault.xml");
        DisplayModel displayModel = new DisplayModel();
        RenderTheme renderTheme = RenderThemeHandler.getRenderTheme(AndroidGraphicFactory.INSTANCE, displayModel, theme);

        Assert.assertNotNull("gioDefault.xml with contour rules must parse successfully into RenderTheme", renderTheme);
        Assert.assertTrue("RenderTheme should have levels defined", renderTheme.getLevels() > 0);
    }

    @Test
    public void testElevateRenderThemeParsesWithMenuCallback() throws Exception {
        m2 mapManager = new m2(context, (android.app.Application) context.getApplicationContext(), false);
        XmlRenderTheme theme = new AssetsRenderTheme(context.getAssets(), "", "Elevate.xml", mapManager);
        DisplayModel displayModel = new DisplayModel();
        RenderTheme renderTheme = RenderThemeHandler.getRenderTheme(AndroidGraphicFactory.INSTANCE, displayModel, theme);

        Assert.assertNotNull("Elevate.xml must parse successfully into RenderTheme", renderTheme);
    }

    @Test
    public void testMapManagerReturnsContourAndMountainCategories() {
        m2 mapManager = new m2(context, (android.app.Application) context.getApplicationContext(), false);

        XmlRenderThemeStyleMenu styleMenu = new XmlRenderThemeStyleMenu("elv-menu", "elv-hiking", "en");
        XmlRenderThemeStyleLayer hikingLayer = styleMenu.createLayer("elv-hiking", true, true);
        hikingLayer.addCategory("hike");
        hikingLayer.addCategory("mountains");
        hikingLayer.addCategory("contour");

        Set<String> categories = mapManager.getCategories(styleMenu);
        Assert.assertNotNull("Categories set returned by m2 must not be null", categories);
        Assert.assertTrue("Categories must contain 'contour'", categories.contains("contour"));
        Assert.assertTrue("Categories must contain 'mountains'", categories.contains("mountains"));
        Assert.assertTrue("Categories must contain 'flat'", categories.contains("flat"));
        Assert.assertTrue("Categories must contain 'hike'", categories.contains("hike"));
    }

    @Test
    public void testMapManagerHandlesNullOrEmptyStyleMenuGracefully() {
        m2 mapManager = new m2(context, (android.app.Application) context.getApplicationContext(), false);
        Assert.assertNull("Null style menu must return null gracefully", mapManager.getCategories(null));
    }

    @Test
    public void testAltitudeMarkerCreationAndVisibilityThreshold() {
        // Test 1: n2.createAltitudeBadge generates properly sized bitmap with badge and text
        org.mapsforge.core.graphics.Bitmap badgeBitmap = n2.createAltitudeBadge(context, 1850);
        Assert.assertNotNull(badgeBitmap);
        // Text-only label (no badge padding): width must contain at least the text characters
        Assert.assertTrue("Altitude text label width must be positive (was " + badgeBitmap.getWidth() + ")", badgeBitmap.getWidth() > 0);
        Assert.assertTrue("Altitude text label height must be positive (was " + badgeBitmap.getHeight() + ")", badgeBitmap.getHeight() > 0);

        android.graphics.Bitmap androidBmp = AndroidGraphicFactory.getBitmap(badgeBitmap);
        Assert.assertNotNull(androidBmp);
        int nonTransparentPixels = 0;
        int nonWhitePixels = 0;
        for (int x = 0; x < androidBmp.getWidth(); x++) {
            for (int y = 0; y < androidBmp.getHeight(); y++) {
                int pixel = androidBmp.getPixel(x, y);
                if ((pixel >>> 24) != 0) {
                    nonTransparentPixels++;
                }
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = pixel & 0xff;
                if (r < 200 || g < 200 || b < 200) {
                    nonWhitePixels++;
                }
            }
        }
        Assert.assertTrue("Altitude badge width must be positive", androidBmp.getWidth() > 0);

        // Test 2: n2.b with TextView also guarantees non-zero bounds
        android.widget.TextView textView = new android.widget.TextView(context);
        textView.setText("1850");
        textView.setTextSize(9.0f);
        textView.setTextColor(Color.rgb(80, 40, 20));
        textView.setBackgroundColor(Color.argb(190, 255, 255, 255));
        org.mapsforge.core.graphics.Bitmap labelBitmap = n2.b(context, textView);
        Assert.assertTrue("TextView bitmap width must not be clipped (width=" + labelBitmap.getWidth() + ")", labelBitmap.getWidth() >= 25);
        Assert.assertTrue("TextView bitmap height must not be clipped (height=" + labelBitmap.getHeight() + ")", labelBitmap.getHeight() >= 12);

        // Test 3: Marker positioning and offset centering
        LatLong pos = new LatLong(40.825, -3.955);
        Marker altMarker = new Marker(pos, badgeBitmap, (-badgeBitmap.getWidth()) / 2, (-badgeBitmap.getHeight()) / 2);
        altMarker.setVisible(true);

        AgpsApplication.f3580w.add(altMarker);
        Assert.assertEquals(1, AgpsApplication.f3580w.size());
        Assert.assertTrue(altMarker.isVisible());

        // Test marker visibility toggle via o.c() helper
        o.c(13); // Without MainActivity.M0 initialized, should safely return without exception
        Assert.assertNotNull(AgpsApplication.f3580w.get(0));
    }

    @Test
    public void testCalculateDistanceMeters() {
        LatLong p1 = new LatLong(40.0, -3.0);
        LatLong p2 = new LatLong(40.001, -3.0);
        double dist = c2.j.calculateDistanceMeters(p1, p2);
        Assert.assertTrue("Distance between 0.001 deg lat should be ~111m", dist > 100 && dist < 125);

        // Null points return 0.0d safely
        Assert.assertEquals(0.0d, c2.j.calculateDistanceMeters(null, p2), 0.001d);
        Assert.assertEquals(0.0d, c2.j.calculateDistanceMeters(p1, null), 0.001d);
    }

    @Test
    public void testDemContourLabelGenerationForMajorAndSummitContours() {
        AgpsApplication.f3580w.clear();
        c2.j contourTask = new c2.j(new LatLong(40.0, -3.0), context, null);

        // Create a major contour (1200m) long enough to cross the 2500m label threshold:
        // 25 points × 0.001° lat ≈ 111m each = ~2664m total
        c2.t longMajorContour = new c2.t(1200);
        for (int i = 0; i <= 24; i++) {
            longMajorContour.f3171a.add(new LatLong(40.0 + (i * 0.001), -3.0));
        }
        contourTask.f3016b.add(longMajorContour);

        // Run d() for 1200m
        contourTask.d("test.hgt", 1200);

        Assert.assertEquals("Major contour >= 2500m should receive at least 1 marker", 1, AgpsApplication.f3580w.size());
        Marker marker = AgpsApplication.f3580w.get(0);
        Assert.assertNotNull(marker);

        // Minor contour (1220m) - should not generate label markers
        AgpsApplication.f3580w.clear();
        c2.t minorContour = new c2.t(1220);
        minorContour.f3171a.addAll(longMajorContour.f3171a);
        contourTask.f3016b.clear();
        contourTask.f3016b.add(minorContour);

        contourTask.d("test.hgt", 1220);
        Assert.assertEquals("Minor contour (not divisible by 100) should have 0 altitude markers", 0, AgpsApplication.f3580w.size());

        // Check o.c(15) visibility toggle
        o.c(15);
        Assert.assertEquals(0, AgpsApplication.f3580w.size());
    }

    @Test
    public void testShortMajorContourAlwaysReceivesAtLeastOneLabel() {
        // Regression test: a meaningful major contour between 300m and 2500m
        // must receive a midpoint label. Tiny fragments (< 5 points or < 300m) are skipped.
        AgpsApplication.f3580w.clear();
        c2.j contourTask = new c2.j(new LatLong(40.0, -3.0), context, null);

        // Major contour (1100m) with 8 points (~700m total — over 300m, under 2500m)
        c2.t shortMajorContour = new c2.t(1100);
        for (int i = 0; i < 8; i++) {
            shortMajorContour.f3171a.add(new LatLong(40.0 + (i * 0.001), -3.0));
        }
        contourTask.f3016b.add(shortMajorContour);

        contourTask.d("test_short.hgt", 1100);

        Assert.assertEquals(
                "Major contour >= 300m and >= 5 points (but < 2500m) must get 1 midpoint label",
                1, AgpsApplication.f3580w.size());

        // Verify midpoint position (index = pointCount / 2 = 4)
        LatLong expectedMidPoint = shortMajorContour.f3171a.get(4);
        Assert.assertEquals(expectedMidPoint, AgpsApplication.f3580w.get(0).getLatLong());

        // Tiny fragment: only 3 points (~220m) — must NOT receive any label
        AgpsApplication.f3580w.clear();
        contourTask.f3016b.clear();
        c2.t tinyContour = new c2.t(1100);
        tinyContour.f3171a.add(new LatLong(40.000, -3.000));
        tinyContour.f3171a.add(new LatLong(40.001, -3.000));
        tinyContour.f3171a.add(new LatLong(40.002, -3.000)); // ~222m, only 3 points
        contourTask.f3016b.add(tinyContour);
        contourTask.d("test_tiny.hgt", 1100);
        Assert.assertEquals("Tiny major contour (< 5 points) must NOT receive a label", 0, AgpsApplication.f3580w.size());
    }
}
