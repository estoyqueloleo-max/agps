package com.giobat.AgpsTrackerPP;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
}
