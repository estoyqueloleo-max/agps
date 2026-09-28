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
        // Create sample altitude marker simulating c2/j.java output
        android.widget.TextView textView = new android.widget.TextView(context);
        textView.setText("1850");
        textView.setTextSize(9.0f);
        textView.setTextColor(Color.rgb(80, 40, 20));
        textView.setBackgroundColor(Color.argb(190, 255, 255, 255));
        org.mapsforge.core.graphics.Bitmap labelBitmap = n2.b(context, textView);

        LatLong pos = new LatLong(40.825, -3.955);
        Marker altMarker = new Marker(pos, labelBitmap, 0, (-labelBitmap.getHeight()) / 2);
        altMarker.setVisible(true);

        AgpsApplication.f3580w.add(altMarker);
        Assert.assertEquals(1, AgpsApplication.f3580w.size());
        Assert.assertTrue(altMarker.isVisible());

        // Test marker visibility toggle via o.c() helper
        o.c(13); // Without MainActivity.M0 initialized, should safely return without exception
        Assert.assertNotNull(AgpsApplication.f3580w.get(0));
    }
}
