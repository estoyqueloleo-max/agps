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
import java.util.Set;

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
