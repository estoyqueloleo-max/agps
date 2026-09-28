package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.view.View;
import androidx.test.core.app.ApplicationProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class ActivityMyPhotoShowTest {

    @Test
    public void testCalculateInSampleSizeForSmallImage() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = 800;
        options.outHeight = 600;

        int inSampleSize = ActivityMyPhotoShow.calculateInSampleSize(options, 2048, 2048);
        Assert.assertEquals("Small images should not be downsampled", 1, inSampleSize);
    }

    @Test
    public void testCalculateInSampleSizeForHighResolutionPhoto() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = 4032; // 12 MP photo width
        options.outHeight = 3024; // 12 MP photo height

        int inSampleSize = ActivityMyPhotoShow.calculateInSampleSize(options, 2048, 2048);
        Assert.assertTrue("High-res photos must have inSampleSize >= 2 to save RAM", inSampleSize >= 2);
    }

    @Test
    public void testCalculateInSampleSizeForUltraHighResolutionPhoto() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = 9248; // 64 MP photo width
        options.outHeight = 6936;

        int inSampleSize = ActivityMyPhotoShow.calculateInSampleSize(options, 2048, 2048);
        Assert.assertTrue("64MP photo should be aggressively downsampled", inSampleSize >= 4);
    }

    @Test
    public void testRotateBitmapNotNull() {
        Bitmap original = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Bitmap rotated = ActivityMyPhotoShow.z(original, 90.0f);
        Assert.assertNotNull(rotated);
        Assert.assertEquals(100, rotated.getWidth());
        Assert.assertEquals(100, rotated.getHeight());
    }

    @Test
    public void testDecodeSampledBitmapFromRealJpegUri() throws IOException {
        Context context = ApplicationProvider.getApplicationContext();
        File tempImageFile = File.createTempFile("sample_route_photo", ".jpg", context.getCacheDir());
        tempImageFile.deleteOnExit();

        Bitmap testBitmap = Bitmap.createBitmap(400, 300, Bitmap.Config.ARGB_8888);
        testBitmap.eraseColor(Color.BLUE);
        try (FileOutputStream outputStream = new FileOutputStream(tempImageFile)) {
            testBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
        }

        Uri imageUri = Uri.fromFile(tempImageFile);
        Bitmap decoded = ActivityMyPhotoShow.decodeSampledBitmapFromUri(context, imageUri, 2048, 2048);
        Assert.assertNotNull("Decoded bitmap must not be null when reading a valid JPEG URI", decoded);
        Assert.assertTrue(decoded.getWidth() > 0);
        Assert.assertTrue(decoded.getHeight() > 0);
    }

    @Test
    public void testActivityCreationWithRealImageRendersAndRotates() throws IOException {
        Context context = ApplicationProvider.getApplicationContext();
        File tempImageFile = File.createTempFile("photo_render_test", ".jpg", context.getCacheDir());
        tempImageFile.deleteOnExit();

        Bitmap testBitmap = Bitmap.createBitmap(300, 200, Bitmap.Config.ARGB_8888);
        testBitmap.eraseColor(Color.RED);
        try (FileOutputStream outputStream = new FileOutputStream(tempImageFile)) {
            testBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
        }

        Uri imageUri = Uri.fromFile(tempImageFile);
        Intent intent = new Intent();
        intent.setData(imageUri);
        intent.putExtra("FILE_URI", imageUri);
        intent.putExtra("filePath", tempImageFile.getAbsolutePath());

        ActivityMyPhotoShow activity = Robolectric.buildActivity(ActivityMyPhotoShow.class, intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();

        Assert.assertNotNull(activity);
        Assert.assertNotNull("Current bitmap in activity must be loaded and non-null", activity.currentBitmap);
        Assert.assertTrue(activity.currentBitmap.getWidth() > 0);
        Assert.assertTrue(activity.currentBitmap.getHeight() > 0);
        Assert.assertNotNull("photoImageView should have drawable attached", activity.photoImageView.getDrawable());

        // Test rotating photo
        activity.onClickRotate(null);
        Assert.assertEquals(90.0f, activity.currentRotationDegrees, 0.01f);
        Assert.assertNotNull(activity.currentBitmap);
        Assert.assertTrue(activity.currentBitmap.getWidth() > 0);
        Assert.assertTrue(activity.currentBitmap.getHeight() > 0);

        activity.finish();
    }

    @Test
    public void testActivityCreationWithSafUriDoesNotCrash() {
        Uri testSafUri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AGps/document/primary%3AGps%2FAgpsTrackerPhoto%2Fphoto_test.jpg");
        Intent intent = new Intent();
        intent.setData(testSafUri);
        intent.putExtra("FILE_URI", testSafUri);
        intent.putExtra("filePath", "photo_test.jpg");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        ActivityMyPhotoShow activity = Robolectric.buildActivity(ActivityMyPhotoShow.class, intent)
                .create()
                .start()
                .resume()
                .get();

        Assert.assertNotNull(activity);
        Assert.assertEquals("photo_test.jpg", activity.getTitle());
    }

    @Test
    public void testActivityCreationWithNullExtrasDoesNotCrash() {
        Intent emptyIntent = new Intent();
        ActivityMyPhotoShow activity = Robolectric.buildActivity(ActivityMyPhotoShow.class, emptyIntent)
                .create()
                .get();

        Assert.assertNotNull(activity);
    }

    @Test
    public void testPhotoViewCanvasRenderingAndMatrixValidity() throws IOException {
        Context context = ApplicationProvider.getApplicationContext();
        File tempImageFile = File.createTempFile("visual_canvas_test", ".jpg", context.getCacheDir());
        tempImageFile.deleteOnExit();

        // Create a 200x200 solid RED test bitmap and save it as JPEG
        Bitmap testBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        testBitmap.eraseColor(Color.RED);
        try (FileOutputStream outputStream = new FileOutputStream(tempImageFile)) {
            testBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
        }

        Uri imageUri = Uri.fromFile(tempImageFile);
        Intent intent = new Intent();
        intent.setData(imageUri);
        intent.putExtra("FILE_URI", imageUri);
        intent.putExtra("filePath", tempImageFile.getAbsolutePath());

        ActivityMyPhotoShow activity = Robolectric.buildActivity(ActivityMyPhotoShow.class, intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();

        Assert.assertNotNull(activity);
        Assert.assertNotNull("photoImageView should not be null", activity.photoImageView);

        // Perform measure & layout passes simulating screen resolution (1080 x 1920)
        int width = 1080;
        int height = 1920;
        activity.photoImageView.measure(
                View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        );
        activity.photoImageView.layout(0, 0, width, height);

        // 1. Matrix verification: matrix values MUST be valid finite numbers (never NaN or Infinity)
        Matrix matrix = activity.photoImageView.getImageMatrix();
        Assert.assertNotNull("ImageMatrix must not be null", matrix);
        float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);

        for (int i = 0; i < 9; i++) {
            Assert.assertFalse("Matrix value at index " + i + " must not be NaN", Float.isNaN(matrixValues[i]));
            Assert.assertFalse("Matrix value at index " + i + " must not be Infinite", Float.isInfinite(matrixValues[i]));
        }

        Assert.assertTrue("ScaleX must be positive", matrixValues[Matrix.MSCALE_X] > 0);
        Assert.assertTrue("ScaleY must be positive", matrixValues[Matrix.MSCALE_Y] > 0);

        // 2. Visual / Canvas Drawing verification:
        // Render the view directly onto a software Canvas and ensure no exceptions, NaN bounds or thread locks occur
        Bitmap canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(canvasBitmap);
        activity.photoImageView.draw(canvas);

        activity.finish();
    }

    @Test
    public void testPhotoViewZoomMaintainsFiniteMatrix() throws IOException {
        Context context = ApplicationProvider.getApplicationContext();
        File tempImageFile = File.createTempFile("visual_zoom_test", ".jpg", context.getCacheDir());
        tempImageFile.deleteOnExit();

        Bitmap testBitmap = Bitmap.createBitmap(400, 300, Bitmap.Config.ARGB_8888);
        testBitmap.eraseColor(Color.GREEN);
        try (FileOutputStream outputStream = new FileOutputStream(tempImageFile)) {
            testBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
        }

        Uri imageUri = Uri.fromFile(tempImageFile);
        Intent intent = new Intent();
        intent.setData(imageUri);
        intent.putExtra("FILE_URI", imageUri);
        intent.putExtra("filePath", tempImageFile.getAbsolutePath());

        ActivityMyPhotoShow activity = Robolectric.buildActivity(ActivityMyPhotoShow.class, intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();

        int width = 1080;
        int height = 1920;
        activity.photoImageView.measure(
                View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        );
        activity.photoImageView.layout(0, 0, width, height);

        // Zoom to 2.5x at the center
        activity.photoImageView.setZoom(2.5f, width / 2.0f, height / 2.0f);

        Matrix matrix = activity.photoImageView.getImageMatrix();
        float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);

        for (int i = 0; i < 9; i++) {
            Assert.assertFalse("Zoomed matrix value at index " + i + " must not be NaN", Float.isNaN(matrixValues[i]));
            Assert.assertFalse("Zoomed matrix value at index " + i + " must not be Infinite", Float.isInfinite(matrixValues[i]));
        }

        Assert.assertTrue("Zoomed scale must be higher than initial scale", matrixValues[Matrix.MSCALE_X] > 1.0f);

        // Draw zoomed view on canvas to ensure no drawing exceptions or crashes occur
        Bitmap canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(canvasBitmap);
        activity.photoImageView.draw(canvas);

        activity.finish();
    }
}
