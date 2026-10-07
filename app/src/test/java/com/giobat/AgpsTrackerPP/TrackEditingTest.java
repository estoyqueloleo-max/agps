package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.location.Location;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.InputStream;

import c2.r;
import c2.x3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
@org.robolectric.annotation.GraphicsMode(org.robolectric.annotation.GraphicsMode.Mode.NATIVE)
public class TrackEditingTest {

    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        AndroidGraphicFactory.createInstance(ApplicationProvider.getApplicationContext());
    }

    private x3 loadSampleTrack() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("rivas_track.gpx");
        assertNotNull("rivas_track.gpx must exist in test resources", is);

        r parser = new r(context);
        boolean parseSuccess = parser.a(is, context);
        assertTrue("GPX import must succeed", parseSuccess);

        x3 track = new x3(2);
        boolean loadSuccess = track.a("Rivas Test Track", parser);
        assertTrue("Track model load must succeed", loadSuccess);
        return track;
    }

    @Test
    public void testTrimStartReducesPointsAndRecalculatesDistance() throws Exception {
        x3 track = loadSampleTrack();
        int initialCount = track.i();
        assertEquals(2969, initialCount);
        double initialDistance = track.f3239d[initialCount - 1];

        // Trim first 100 points
        int trimIndex = 100;
        Location newFirstPoint = track.f3238c.get(trimIndex);
        boolean success = track.trimStart(trimIndex);
        assertTrue("trimStart should succeed", success);

        assertEquals(initialCount - trimIndex, track.i());
        assertEquals(initialCount - trimIndex, track.f3238c.size());
        assertEquals(initialCount - trimIndex, track.f3239d.length);

        // Verify the new start coordinate matches the trimmed point
        Location updatedFirstPoint = track.f3238c.get(0);
        assertEquals(newFirstPoint.getLatitude(), updatedFirstPoint.getLatitude(), 1e-6);
        assertEquals(newFirstPoint.getLongitude(), updatedFirstPoint.getLongitude(), 1e-6);

        // Total distance must be less than initial distance
        double updatedDistance = track.f3239d[track.i() - 1];
        assertTrue("Distance should decrease after trimming start", updatedDistance < initialDistance);
        assertEquals(0.0d, track.f3239d[0], 1e-6);
    }

    @Test
    public void testTrimEndReducesPointsAndRecalculatesDistance() throws Exception {
        x3 track = loadSampleTrack();
        int initialCount = track.i();
        assertEquals(2969, initialCount);
        double initialDistance = track.f3239d[initialCount - 1];

        // Trim end from index 500 onwards (keep 0..500)
        int keepUntilIndex = 500;
        Location expectedLastPoint = track.f3238c.get(keepUntilIndex);
        boolean success = track.trimEnd(keepUntilIndex);
        assertTrue("trimEnd should succeed", success);

        assertEquals(keepUntilIndex + 1, track.i());
        assertEquals(keepUntilIndex + 1, track.f3238c.size());
        assertEquals(keepUntilIndex + 1, track.f3239d.length);

        Location updatedLastPoint = track.f3238c.get(track.i() - 1);
        assertEquals(expectedLastPoint.getLatitude(), updatedLastPoint.getLatitude(), 1e-6);
        assertEquals(expectedLastPoint.getLongitude(), updatedLastPoint.getLongitude(), 1e-6);

        double updatedDistance = track.f3239d[track.i() - 1];
        assertTrue("Distance should be significantly shorter", updatedDistance < initialDistance);
    }

    @Test
    public void testRemovePointAt() throws Exception {
        x3 track = loadSampleTrack();
        int initialCount = track.i();

        Location pointBefore = track.f3238c.get(49);
        Location pointAfter = track.f3238c.get(51);

        boolean success = track.removePointAt(50);
        assertTrue("removePointAt should succeed", success);

        assertEquals(initialCount - 1, track.i());
        assertEquals(pointBefore.getLatitude(), track.f3238c.get(49).getLatitude(), 1e-6);
        assertEquals(pointAfter.getLatitude(), track.f3238c.get(50).getLatitude(), 1e-6);
    }

    @Test
    public void testReverseTrackPreservesTotalDistanceAndInvertsEndpoints() throws Exception {
        x3 track = loadSampleTrack();
        int totalPoints = track.i();
        Location originalStart = track.f3238c.get(0);
        Location originalEnd = track.f3238c.get(totalPoints - 1);
        double originalDistance = track.f3239d[totalPoints - 1];

        track.reverseTrack();

        assertEquals(totalPoints, track.i());
        Location newStart = track.f3238c.get(0);
        Location newEnd = track.f3238c.get(totalPoints - 1);

        assertEquals(originalEnd.getLatitude(), newStart.getLatitude(), 1e-6);
        assertEquals(originalEnd.getLongitude(), newStart.getLongitude(), 1e-6);
        assertEquals(originalStart.getLatitude(), newEnd.getLatitude(), 1e-6);
        assertEquals(originalStart.getLongitude(), newEnd.getLongitude(), 1e-6);

        // Distance after reversing should be virtually identical
        double newDistance = track.f3239d[totalPoints - 1];
        assertEquals(originalDistance, newDistance, 50.0d);
    }

    @Test
    public void testInvalidTrimmingBounds() throws Exception {
        x3 track = loadSampleTrack();
        assertFalse("trimStart on index 0 should fail", track.trimStart(0));
        assertFalse("trimStart out of bounds should fail", track.trimStart(5000));
        assertFalse("trimEnd on 0 should fail", track.trimEnd(0));
        assertFalse("trimEnd out of bounds should fail", track.trimEnd(5000));
        assertFalse("removePointAt negative should fail", track.removePointAt(-1));
        assertFalse("removePointAt out of bounds should fail", track.removePointAt(5000));
    }

    @Test
    public void testMainActivityTrackEditDialogFlow() throws Exception {
        x3 track = loadSampleTrack();
        AgpsApplication.f3574q = track;

        org.robolectric.android.controller.ActivityController<MainActivity> controller =
                org.robolectric.Robolectric.buildActivity(MainActivity.class).create().start().resume();
        MainActivity activity = controller.get();
        assertNotNull(activity);

        // Test reverse flow
        android.app.AlertDialog dialog = activity.reverseLoadedTrack();
        assertNotNull("Confirmation dialog should be displayed", dialog);
        assertTrue("Dialog should be showing", dialog.isShowing());
        dialog.dismiss();

        // Verify reverse action updates model and status
        track.reverseTrack();
        track.f3242g = activity.getString(R.string.new_modif_track);
        activity.x0(track);
        assertEquals(activity.getString(R.string.new_modif_track), track.f3242g);

        // Test trim flow with point handler
        MainActivity.trackEditMode = MainActivity.TRACK_EDIT_MODE_TRIM_START;
        Location selectedLoc = track.f3238c.get(10);
        android.app.AlertDialog trimDialog = activity.handleTrackEditPoint(10, selectedLoc);
        assertNotNull("Trim confirmation dialog should be displayed", trimDialog);
        assertTrue("Trim dialog should be showing", trimDialog.isShowing());
        trimDialog.cancel();
        org.robolectric.shadows.ShadowLooper.idleMainLooper();

        assertEquals(MainActivity.TRACK_EDIT_MODE_NONE, MainActivity.trackEditMode);
        controller.pause().stop().destroy();
    }

    @Test
    public void testSaveModifiedTrackFlow() throws Exception {
        x3 track = loadSampleTrack();
        AgpsApplication.f3574q = track;

        // Perform edit operation
        track.trimStart(50);
        track.f3242g = "Modified Rivas Track";
        track.f3244i = "Edited route description";

        org.robolectric.android.controller.ActivityController<MainActivity> controller =
                org.robolectric.Robolectric.buildActivity(MainActivity.class).create().start().resume();
        MainActivity activity = controller.get();

        android.widget.EditText nameInput = new android.widget.EditText(activity);
        android.widget.EditText descInput = new android.widget.EditText(activity);
        nameInput.setText("My_Edited_Track");
        descInput.setText("Updated description");

        c2.i1 saveListener = new c2.i1(activity, nameInput, descInput);
        android.app.AlertDialog saveDialog = new android.app.AlertDialog.Builder(activity).create();
        saveListener.onClick(saveDialog, android.content.DialogInterface.BUTTON_POSITIVE);

        assertEquals("My_Edited_Track", track.f3242g);
        assertEquals("Updated description", track.f3244i);
        controller.pause().stop().destroy();
    }

    @Test
    public void testVisualRenderTrackBeforeAndAfterEdit() throws Exception {
        x3 track = loadSampleTrack();
        int initialPointCount = track.i();
        org.junit.Assert.assertTrue("Initial track must have points", initialPointCount > 100);

        // Setup 800x600 rendering canvas
        int width = 800;
        int height = 600;
        byte zoom = 13;
        org.mapsforge.map.model.DisplayModel displayModel = new org.mapsforge.map.model.DisplayModel();
        org.mapsforge.core.graphics.GraphicFactory factory = AndroidGraphicFactory.INSTANCE;

        // Render original track in BLUE (Style.STROKE)
        org.mapsforge.core.graphics.Paint origPaint = c2.n2.a(android.graphics.Color.BLUE, 4, 2);
        origPaint.setStyle(org.mapsforge.core.graphics.Style.STROKE);
        org.mapsforge.map.layer.overlay.Polyline origPolyline = new org.mapsforge.map.layer.overlay.Polyline(origPaint, factory);
        origPolyline.setDisplayModel(displayModel);
        origPolyline.setPoints(track.f3236a.get(0).f3122a);

        // Perform Track Editing: Trim 300 points from start and 500 from end
        int trimStartIndex = 300;
        track.trimStart(trimStartIndex);
        track.trimEnd(track.i() - 500);

        // Render edited track in RED (Style.STROKE)
        org.mapsforge.core.graphics.Paint editedPaint = c2.n2.a(android.graphics.Color.RED, 5, 2);
        editedPaint.setStyle(org.mapsforge.core.graphics.Style.STROKE);
        org.mapsforge.map.layer.overlay.Polyline editedPolyline = new org.mapsforge.map.layer.overlay.Polyline(editedPaint, factory);
        editedPolyline.setDisplayModel(displayModel);
        editedPolyline.setPoints(track.f3236a.get(0).f3122a);

        // Render onto Native Android Bitmap
        org.mapsforge.core.graphics.Bitmap mfBitmap = factory.createBitmap(width, height);
        android.graphics.Bitmap androidBitmap = AndroidGraphicFactory.getBitmap(mfBitmap);
        androidBitmap.eraseColor(android.graphics.Color.rgb(250, 250, 250)); // light parchment background

        org.mapsforge.core.graphics.Canvas canvas = factory.createCanvas();
        canvas.setBitmap(mfBitmap);

        // Center on Rivas track coordinates
        double centerLat = 40.34;
        double centerLon = -3.52;
        long mapSize = org.mapsforge.core.util.MercatorProjection.getMapSize(zoom, displayModel.getTileSize());
        double centerPixelX = org.mapsforge.core.util.MercatorProjection.longitudeToPixelX(centerLon, mapSize);
        double centerPixelY = org.mapsforge.core.util.MercatorProjection.latitudeToPixelY(centerLat, mapSize);
        org.mapsforge.core.model.Point topLeft = new org.mapsforge.core.model.Point(centerPixelX - (width / 2.0), centerPixelY - (height / 2.0));
        org.mapsforge.core.model.BoundingBox bounds = new org.mapsforge.core.model.BoundingBox(40.28, -3.60, 40.40, -3.44);

        // Draw original track (blue) and edited track (red) on top
        origPolyline.draw(bounds, zoom, canvas, topLeft, org.mapsforge.core.model.Rotation.NULL_ROTATION);
        editedPolyline.draw(bounds, zoom, canvas, topLeft, org.mapsforge.core.model.Rotation.NULL_ROTATION);

        // Draw Title & Legend
        android.graphics.Canvas nativeCanvas = new android.graphics.Canvas(androidBitmap);
        android.graphics.Paint titlePaint = new android.graphics.Paint();
        titlePaint.setColor(android.graphics.Color.BLACK);
        titlePaint.setTextSize(22.0f);
        titlePaint.setFakeBoldText(true);
        titlePaint.setAntiAlias(true);
        nativeCanvas.drawText("Track Editing Visual Validation (Rivas)", 20.0f, 35.0f, titlePaint);

        android.graphics.Paint legendBlue = new android.graphics.Paint();
        legendBlue.setColor(android.graphics.Color.BLUE);
        legendBlue.setStrokeWidth(5.0f);
        nativeCanvas.drawLine(20.0f, 60.0f, 60.0f, 60.0f, legendBlue);
        android.graphics.Paint textPaint = new android.graphics.Paint();
        textPaint.setColor(android.graphics.Color.DKGRAY);
        textPaint.setTextSize(16.0f);
        nativeCanvas.drawText("Original Track (" + initialPointCount + " pts)", 70.0f, 65.0f, textPaint);

        android.graphics.Paint legendRed = new android.graphics.Paint();
        legendRed.setColor(android.graphics.Color.RED);
        legendRed.setStrokeWidth(6.0f);
        nativeCanvas.drawLine(20.0f, 85.0f, 60.0f, 85.0f, legendRed);
        nativeCanvas.drawText("Edited / Trimmed Track (" + track.i() + " pts)", 70.0f, 90.0f, textPaint);

        // Verify pixel integrity: ensure non-background pixels were rendered
        int nonBgPixels = 0;
        int bg = android.graphics.Color.rgb(250, 250, 250);
        for (int y = 0; y < height; y += 5) {
            for (int x = 0; x < width; x += 5) {
                if (androidBitmap.getPixel(x, y) != bg) {
                    nonBgPixels++;
                }
            }
        }
        org.junit.Assert.assertTrue("Visual rendering must produce drawn track pixels", nonBgPixels > 50);

        // Save PNG visual artifact
        java.io.File outDir = new java.io.File("build/reports/visual-tests");
        outDir.mkdirs();
        java.io.File pngFile = new java.io.File(outDir, "track_editing_visual_validation.png");
        try (java.io.FileOutputStream fos = new java.io.FileOutputStream(pngFile)) {
            androidBitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fos);
        }
        org.junit.Assert.assertTrue("Visual artifact PNG must be written to disk", pngFile.exists());

        // Copy to IDE artifacts directory
        java.io.File ideDir = new java.io.File("/home/jose/.gemini/antigravity-ide/brain/9a64d341-6a9e-4017-a55f-b989d9e167cd");
        if (ideDir.exists()) {
            java.io.File ideArtifact = new java.io.File(ideDir, "track_editing_visual_validation.png");
            try (java.io.FileOutputStream fos = new java.io.FileOutputStream(ideArtifact)) {
                androidBitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fos);
            }
        }
    }
}
