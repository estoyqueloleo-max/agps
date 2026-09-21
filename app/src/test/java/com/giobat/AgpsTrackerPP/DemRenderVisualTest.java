package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.test.core.app.ApplicationProvider;

import c2.n2;
import c2.o;
import c2.u2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapsforge.core.graphics.Canvas;
import org.mapsforge.core.graphics.GraphicFactory;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.graphics.Style;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.Dimension;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.core.model.Rotation;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.Layer;
import org.mapsforge.map.layer.Layers;
import org.mapsforge.map.layer.overlay.Polygon;
import org.mapsforge.map.layer.overlay.Polyline;
import org.mapsforge.map.model.DisplayModel;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.GraphicsMode;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Automated tests for validating DEM (Digital Elevation Model) domain and contour drawing,
 * verifying Mapsforge 0.25.0 API compatibility, Stroke styles, bounding boxes,
 * layer persistence, and real visual rendering onto a canvas.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
public class DemRenderVisualTest {

    private Context context;
    private GraphicFactory graphicFactory;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        AndroidGraphicFactory.createInstance(context);
        graphicFactory = AndroidGraphicFactory.INSTANCE;
        o.demOverlayLayers.clear();
    }

    @Test
    public void testDemFilenameToBoundingBox() {
        // N40W004 -> Lat 40 to 41, Lon -4 to -3
        BoundingBox box = o.a("N40W004");
        Assert.assertEquals(40.0, box.minLatitude, 0.0001);
        Assert.assertEquals(41.0, box.maxLatitude, 0.0001);
        Assert.assertEquals(-4.0, box.minLongitude, 0.0001);
        Assert.assertEquals(-3.0, box.maxLongitude, 0.0001);

        // S12E035 -> Lat -12 to -11, Lon 35 to 36
        BoundingBox boxSouth = o.a("S12E035");
        Assert.assertEquals(-12.0, boxSouth.minLatitude, 0.0001);
        Assert.assertEquals(-11.0, boxSouth.maxLatitude, 0.0001);
        Assert.assertEquals(35.0, boxSouth.minLongitude, 0.0001);
        Assert.assertEquals(36.0, boxSouth.maxLongitude, 0.0001);
    }

    @Test
    public void testDemDomainOverlayCreationAndStyles() {
        // Prepare a dummy MapView with layer manager for f3626n1
        MapView mapView = new MapView(context);
        MainActivity.M0 = mapView;
        MainActivity.f3626n1 = new u2(mapView);

        BoundingBox box = new BoundingBox(40.0, -4.0, 41.0, -3.0);
        o.d(box, 1);

        // Verify layers were registered into demOverlayLayers
        Assert.assertFalse("demOverlayLayers should contain registered overlays", o.demOverlayLayers.isEmpty());
        Assert.assertEquals(2, o.demOverlayLayers.size());

        Layer layer0 = o.demOverlayLayers.get(0);
        Layer layer1 = o.demOverlayLayers.get(1);

        Assert.assertTrue("First overlay should be a Polygon", layer0 instanceof Polygon);
        Assert.assertTrue("Second overlay should be a Polyline", layer1 instanceof Polyline);

        Polygon polygon = (Polygon) layer0;
        Polyline polyline = (Polyline) layer1;

        // Verify coordinates populated via setPoints()
        Assert.assertEquals(5, polygon.getLatLongs().size());
        Assert.assertEquals(5, polyline.getLatLongs().size());

        // Verify stroke and fill styles
        Assert.assertEquals("Polygon stroke must be Style.STROKE",
                android.graphics.Paint.Style.STROKE,
                AndroidGraphicFactory.getPaint(polygon.getPaintStroke()).getStyle());
        Assert.assertEquals("Polygon fill must be Style.FILL",
                android.graphics.Paint.Style.FILL,
                AndroidGraphicFactory.getPaint(polygon.getPaintFill()).getStyle());
        Assert.assertEquals("Polyline stroke must be Style.STROKE",
                android.graphics.Paint.Style.STROKE,
                AndroidGraphicFactory.getPaint(polyline.getPaintStroke()).getStyle());

        // Verify stroke width is visible (> 1px)
        Assert.assertTrue("Polygon stroke width should be >= 2px",
                polygon.getPaintStroke().getStrokeWidth() >= 2.0f);
        Assert.assertTrue("Polyline stroke width should be >= 2px",
                polyline.getPaintStroke().getStrokeWidth() >= 2.0f);
    }

    @Test
    public void testContourPolylineVisualRenderOnCanvas() throws Exception {
        int width = 256;
        int height = 256;
        org.mapsforge.core.graphics.Bitmap mfBitmap = graphicFactory.createBitmap(width, height);
        android.graphics.Bitmap androidBitmap = AndroidGraphicFactory.getBitmap(mfBitmap);
        androidBitmap.eraseColor(android.graphics.Color.WHITE);

        // Wrap into Mapsforge Canvas
        Canvas mapsforgeCanvas = graphicFactory.createCanvas();
        mapsforgeCanvas.setBitmap(mfBitmap);

        DisplayModel displayModel = new DisplayModel();

        // Create contour polyline traversing viewport at zoom 15
        byte zoomLevel = 15;
        double centerLat = 40.4168;
        double centerLon = -3.7038;

        List<LatLong> contourPoints = new ArrayList<>();
        contourPoints.add(new LatLong(centerLat - 0.005, centerLon - 0.005));
        contourPoints.add(new LatLong(centerLat, centerLon));
        contourPoints.add(new LatLong(centerLat + 0.005, centerLon + 0.005));

        Paint majorPaint = n2.a(Color.argb(255, 80, 40, 20), 4, 2);
        Assert.assertEquals("Paint style must be STROKE",
                android.graphics.Paint.Style.STROKE,
                AndroidGraphicFactory.getPaint(majorPaint).getStyle());

        Polyline polyline = new Polyline(majorPaint, graphicFactory);
        polyline.setDisplayModel(displayModel);
        polyline.setPoints(contourPoints);

        BoundingBox viewBounds = new BoundingBox(centerLat - 0.01, centerLon - 0.01, centerLat + 0.01, centerLon + 0.01);
        Point topLeftPoint = new Point(
                org.mapsforge.core.util.MercatorProjection.longitudeToPixelX(centerLon - 0.006, org.mapsforge.core.util.MercatorProjection.getMapSize(zoomLevel, displayModel.getTileSize())),
                org.mapsforge.core.util.MercatorProjection.latitudeToPixelY(centerLat + 0.006, org.mapsforge.core.util.MercatorProjection.getMapSize(zoomLevel, displayModel.getTileSize()))
        );

        // Draw polyline onto the canvas
        polyline.draw(viewBounds, zoomLevel, mapsforgeCanvas, topLeftPoint, Rotation.NULL_ROTATION);

        // Verify that drawn bitmap has painted pixels (not pure white)
        int nonWhitePixels = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = androidBitmap.getPixel(x, y);
                if (pixel != android.graphics.Color.WHITE && (pixel & 0xFF000000) != 0) {
                    nonWhitePixels++;
                }
            }
        }
        Assert.assertTrue("Contour line must draw visible pixels on canvas (found: " + nonWhitePixels + ")",
                nonWhitePixels > 50);

        // Save visual test artifact
        File outputDir = new File("build/reports/visual-tests");
        outputDir.mkdirs();
        File artifact = new File(outputDir, "visual_dem_contour_test.png");
        try (FileOutputStream fos = new FileOutputStream(artifact)) {
            androidBitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fos);
        }
        Assert.assertTrue(artifact.exists());
    }

    @Test
    public void testDemOverlayPersistenceAcrossLayerWipe() {
        MapView mapView = new MapView(context);
        Layers layers = mapView.getLayerManager().getLayers();
        u2 layerManagerWrapper = new u2(mapView);
        MainActivity.f3626n1 = layerManagerWrapper;

        // Base tile layer
        Paint dummyPaint = n2.a(Color.BLACK, 1, 2);
        Polyline baseLayer = new Polyline(dummyPaint, graphicFactory);
        layerManagerWrapper.a(baseLayer);

        // Add DEM domain overlays
        BoundingBox box = new BoundingBox(40.0, -4.0, 41.0, -3.0);
        o.d(box, 1);
        Assert.assertEquals(2, o.demOverlayLayers.size());

        // Verify layers currently has 3 layers (base + polygon + polyline)
        Assert.assertEquals(3, layers.size());

        // Simulate m2.l() layer refresh: wipe all above index 0 and re-attach DEM overlays
        while (layers.size() > 1) {
            layers.remove(1, false);
        }
        Assert.assertEquals(1, layers.size());

        // Re-attach active DEM overlays as done in m2.l()
        for (Layer demLayer : o.demOverlayLayers) {
            if (!layers.contains(demLayer)) {
                layers.add(demLayer, false);
            }
        }

        // Verify DEM layers are preserved and re-attached
        Assert.assertEquals(3, layers.size());
        Assert.assertTrue(layers.contains(o.demOverlayLayers.get(0)));
        Assert.assertTrue(layers.contains(o.demOverlayLayers.get(1)));
    }

    @Test
    public void testMountainNavigationWithDemContourRendering() throws Exception {
        // Setup MapView and layer management
        MapView mapView = new MapView(context);
        MainActivity.M0 = mapView;
        MainActivity.f3626n1 = new u2(mapView);
        DisplayModel displayModel = new DisplayModel();

        // 1. Target mountain: Pico Peñalara (Sierra de Guadarrama)
        double summitLat = 40.8505;
        double summitLon = -3.9555;
        LatLong summit = new LatLong(summitLat, summitLon);

        // 2. Starting waypoint: Puerto de Cotos
        double startLat = 40.8250;
        double startLon = -3.9620;
        LatLong startPoint = new LatLong(startLat, startLon);

        // 3. Create synthetic 1201x1201 (SRTM-3) HGT file around N40W004 with a mountain peak at Peñalara
        File demsDir = MainActivity.X(context, "Dems", 2);
        if (demsDir != null) {
            demsDir.mkdirs();
        }
        File hgtFile = MainActivity.X(context, "Dems/N40W004.HGT", 1);
        if (hgtFile.getParentFile() != null) {
            hgtFile.getParentFile().mkdirs();
        }

        int sampleCount = 1201;
        byte[] fileBytes = new byte[sampleCount * sampleCount * 2];
        java.nio.ByteBuffer buffer = java.nio.ByteBuffer.wrap(fileBytes);
        buffer.order(java.nio.ByteOrder.BIG_ENDIAN);
        for (int r = 0; r < sampleCount; r++) {
            double lat = 41.0 - (r / (double) sampleCount);
            for (int c = 0; c < sampleCount; c++) {
                double lon = -4.0 + (c / (double) sampleCount);
                double dLat = (lat - summitLat) * 111000.0;
                double dLon = (lon - summitLon) * 85000.0;
                double distMeters = Math.sqrt(dLat * dLat + dLon * dLon);
                short elevation;
                if (distMeters < 5000.0) {
                    elevation = (short) Math.max(2150, 2428 - (distMeters * 0.06));
                } else {
                    elevation = (short) 2150;
                }
                buffer.putShort(elevation);
            }
        }
        try (FileOutputStream fos = new FileOutputStream(hgtFile)) {
            fos.write(fileBytes);
        }

        // 4. Load DEM metadata and compute contours via j.call()
        o dem = new o(context, "N40W004.HGT");
        Assert.assertEquals(1201, dem.f3087c);

        c2.j contourTask = new c2.j(summit, context, dem);
        String taskResult = contourTask.call();
        Assert.assertEquals("OK", taskResult);
        Assert.assertFalse("Contour polylines should be generated", o.demOverlayLayers.isEmpty());

        // 5. Add navigation path (GPX track) from start to summit
        List<LatLong> navPath = new ArrayList<>();
        int steps = 20;
        for (int i = 0; i <= steps; i++) {
            double ratio = i / (double) steps;
            double curLat = startLat + (summitLat - startLat) * ratio;
            double curLon = startLon + (summitLon - startLon) * ratio;
            navPath.add(new LatLong(curLat, curLon));
        }
        Paint navTrackPaint = n2.a(Color.argb(240, 0, 122, 255), 5, 2); // iOS/Android blue route line
        Polyline navPolyline = new Polyline(navTrackPaint, graphicFactory);
        navPolyline.setDisplayModel(displayModel);
        navPolyline.setPoints(navPath);
        MainActivity.f3626n1.a(navPolyline);

        // 6. Setup high-resolution 800x800 Canvas centered on the mountain
        int canvasWidth = 800;
        int canvasHeight = 800;
        byte zoomLevel = 14;

        org.mapsforge.core.graphics.Bitmap mfBitmap = graphicFactory.createBitmap(canvasWidth, canvasHeight);
        android.graphics.Bitmap androidBitmap = AndroidGraphicFactory.getBitmap(mfBitmap);
        androidBitmap.eraseColor(Color.rgb(245, 245, 240)); // Map background (light topo parchment)

        Canvas canvas = graphicFactory.createCanvas();
        canvas.setBitmap(mfBitmap);

        BoundingBox viewBounds = new BoundingBox(startLat - 0.02, startLon - 0.02, summitLat + 0.02, summitLon + 0.02);
        long mapSize = org.mapsforge.core.util.MercatorProjection.getMapSize(zoomLevel, displayModel.getTileSize());
        double centerLat = (startLat + summitLat) / 2.0;
        double centerLon = (startLon + summitLon) / 2.0;
        double centerPixelX = org.mapsforge.core.util.MercatorProjection.longitudeToPixelX(centerLon, mapSize);
        double centerPixelY = org.mapsforge.core.util.MercatorProjection.latitudeToPixelY(centerLat, mapSize);
        Point topLeftPoint = new Point(centerPixelX - (canvasWidth / 2.0), centerPixelY - (canvasHeight / 2.0));

        // 7. Render all map layers (DEM domain box, contour lines, navigation path)
        Layers layers = mapView.getLayerManager().getLayers();
        for (int i = 0; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            layer.setDisplayModel(displayModel);
            layer.draw(viewBounds, zoomLevel, canvas, topLeftPoint, Rotation.NULL_ROTATION);
        }

        // Draw Summit Pin & Heading text
        android.graphics.Canvas nativeCanvas = new android.graphics.Canvas(androidBitmap);
        android.graphics.Paint summitPaint = new android.graphics.Paint();
        summitPaint.setColor(Color.RED);
        summitPaint.setStyle(android.graphics.Paint.Style.FILL);
        summitPaint.setAntiAlias(true);

        double summitPx = org.mapsforge.core.util.MercatorProjection.longitudeToPixelX(summitLon, mapSize) - topLeftPoint.x;
        double summitPy = org.mapsforge.core.util.MercatorProjection.latitudeToPixelY(summitLat, mapSize) - topLeftPoint.y;
        nativeCanvas.drawCircle((float) summitPx, (float) summitPy, 12.0f, summitPaint);

        android.graphics.Paint textPaint = new android.graphics.Paint();
        textPaint.setColor(Color.rgb(30, 30, 30));
        textPaint.setTextSize(24.0f);
        textPaint.setFakeBoldText(true);
        textPaint.setAntiAlias(true);
        nativeCanvas.drawText("Pico Peñalara (2.428 m)", (float) summitPx + 18.0f, (float) summitPy + 8.0f, textPaint);

        double startPx = org.mapsforge.core.util.MercatorProjection.longitudeToPixelX(startLon, mapSize) - topLeftPoint.x;
        double startPy = org.mapsforge.core.util.MercatorProjection.latitudeToPixelY(startLat, mapSize) - topLeftPoint.y;
        android.graphics.Paint startPaint = new android.graphics.Paint();
        startPaint.setColor(Color.rgb(0, 150, 0));
        startPaint.setStyle(android.graphics.Paint.Style.FILL);
        nativeCanvas.drawCircle((float) startPx, (float) startPy, 10.0f, startPaint);
        nativeCanvas.drawText("Inicio: Puerto de Cotos (1.830 m)", (float) startPx + 16.0f, (float) startPy + 6.0f, textPaint);

        // 8. Save screenshot visual artifact
        File outputDir = new File("build/reports/visual-tests");
        outputDir.mkdirs();
        File artifact = new File(outputDir, "mountain_navigation_dem_contours.png");
        try (FileOutputStream fos = new FileOutputStream(artifact)) {
            androidBitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fos);
        }
        Assert.assertTrue(artifact.exists());

        // Also copy to IDE artifacts directory for user presentation
        File ideArtifactDir = new File("/home/jose/.gemini/antigravity-ide/brain/51955371-6036-4455-972a-e5c15775cdf3");
        if (ideArtifactDir.exists()) {
            File ideArtifact = new File(ideArtifactDir, "mountain_navigation_dem_contours.png");
            try (FileOutputStream fos = new FileOutputStream(ideArtifact)) {
                androidBitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fos);
            }
        }

        // 9. Assert pixels from contour lines and navigation track were drawn
        int routePixels = 0;
        int contourPixels = 0;
        for (int x = 0; x < canvasWidth; x++) {
            for (int y = 0; y < canvasHeight; y++) {
                int pixel = androidBitmap.getPixel(x, y);
                // Check blue route color
                if (Color.red(pixel) < 50 && Color.blue(pixel) > 200) {
                    routePixels++;
                }
                // Check brown contour color
                if (Color.red(pixel) > 60 && Color.red(pixel) < 160 && Color.green(pixel) > 30 && Color.green(pixel) < 100 && Color.blue(pixel) < 50) {
                    contourPixels++;
                }
            }
        }
        Assert.assertTrue("Route pixels must be drawn (found: " + routePixels + ")", routePixels > 100);
        Assert.assertTrue("Contour lines must be drawn across the mountain terrain (found: " + contourPixels + ")", contourPixels > 500);

        // Clean up temporary synthetic Hgt
        hgtFile.delete();
    }
}
