package com.giobat.AgpsTrackerPP;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import androidx.test.core.app.ApplicationProvider;
import c2.t2;
import java.io.File;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class ActivityFileDialogGpxJpgTest {

    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        MainActivity.f3624l1 = null; // Prevent onResume from accessing null SAF
    }

    private ActivityController<ActivityFileDialogGpxJpg> buildActivityController(int reqType) {
        Intent intent = new Intent(context, ActivityFileDialogGpxJpg.class);
        intent.putExtra("REQ_TYPE", reqType);
        intent.putExtra("PAGE_HEADER", "Test Tracks");
        return Robolectric.buildActivity(ActivityFileDialogGpxJpg.class, intent);
    }

    @Test
    public void testSingleClickSelectsGpxItemWithoutFinishing() {
        ActivityController<ActivityFileDialogGpxJpg> controller = buildActivityController(1003);
        ActivityFileDialogGpxJpg activity = controller.create().start().get();

        Uri dummyUri = Uri.parse("content://com.giobat.agps.test/test_route.gpx");
        t2 gpxItem = new t2("doc1", "test_route.gpx", "application/gpx+xml", "0", 1000L, 2048, null, dummyUri);

        activity.fileItemsList.clear();
        activity.fileItemsList.add(gpxItem);

        ListView listView = activity.fileListView;
        View rowView = new View(activity);

        // Single click at time T
        listView.getOnItemClickListener().onItemClick(listView, rowView, 0, 0);

        Assert.assertFalse("Single click must not finish activity", activity.isFinishing());
        Assert.assertEquals("Selected item position must be 0", 0, activity.selectedItemPosition);
        Assert.assertEquals("Compatible A position must be 0", 0, activity.A);
    }

    @Test
    public void testDoubleClickViaItemClickImportsGpxAndFinishes() {
        ActivityController<ActivityFileDialogGpxJpg> controller = buildActivityController(1003);
        ActivityFileDialogGpxJpg activity = controller.create().start().get();

        Uri dummyUri = Uri.parse("content://com.giobat.agps.test/test_route.gpx");
        t2 gpxItem = new t2("doc1", "test_route.gpx", "application/gpx+xml", "0", 1000L, 2048, null, dummyUri);

        activity.fileItemsList.clear();
        activity.fileItemsList.add(gpxItem);

        ListView listView = activity.fileListView;
        View rowView = new View(activity);

        // First click
        listView.getOnItemClickListener().onItemClick(listView, rowView, 0, 0);
        Assert.assertFalse("First click must not finish activity", activity.isFinishing());

        // Fast second click on the same item (within 500ms)
        listView.getOnItemClickListener().onItemClick(listView, rowView, 0, 0);

        Assert.assertTrue("Double click must finish activity to import route", activity.isFinishing());
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Assert.assertEquals("Result code must be RESULT_OK", Activity.RESULT_OK, shadowActivity.getResultCode());

        Intent resultIntent = shadowActivity.getResultIntent();
        Assert.assertNotNull("Result intent must not be null", resultIntent);
        Assert.assertEquals("test_route.gpx", resultIntent.getStringExtra("RESULT_PATH"));
        Assert.assertEquals(dummyUri, resultIntent.getParcelableExtra("FILE_URI"));
    }

    @Test
    public void testDoubleClickOnDifferentItemsDoesNotTriggerImport() {
        ActivityController<ActivityFileDialogGpxJpg> controller = buildActivityController(1003);
        ActivityFileDialogGpxJpg activity = controller.create().start().get();

        Uri uri1 = Uri.parse("content://com.giobat.agps.test/route1.gpx");
        Uri uri2 = Uri.parse("content://com.giobat.agps.test/route2.gpx");
        t2 item1 = new t2("doc1", "route1.gpx", "application/gpx+xml", "0", 1000L, 1024, null, uri1);
        t2 item2 = new t2("doc2", "route2.gpx", "application/gpx+xml", "0", 2000L, 2048, null, uri2);

        activity.fileItemsList.clear();
        activity.fileItemsList.add(item1);
        activity.fileItemsList.add(item2);

        ListView listView = activity.fileListView;
        View rowView0 = new View(activity);
        View rowView1 = new View(activity);

        // First click item 0
        listView.getOnItemClickListener().onItemClick(listView, rowView0, 0, 0);
        Assert.assertEquals(0, activity.selectedItemPosition);

        // Fast click item 1
        listView.getOnItemClickListener().onItemClick(listView, rowView1, 1, 0);

        Assert.assertFalse("Clicks on different items must not trigger double-click import", activity.isFinishing());
        Assert.assertEquals("Selection should now be item 1", 1, activity.selectedItemPosition);
    }

    @Test
    public void testOpenFileItemDirectlyImportsGpx() {
        ActivityController<ActivityFileDialogGpxJpg> controller = buildActivityController(1003);
        ActivityFileDialogGpxJpg activity = controller.create().start().get();

        Uri uri = Uri.parse("content://com.giobat.agps.test/trail.gpx");
        t2 item = new t2("docTrail", "trail.gpx", "application/gpx+xml", "0", 3000L, 4096, null, uri);
        activity.fileItemsList.clear();
        activity.fileItemsList.add(item);

        activity.openFileItem(0);

        Assert.assertTrue("openFileItem must finish activity", activity.isFinishing());
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Assert.assertEquals(Activity.RESULT_OK, shadowActivity.getResultCode());
        Assert.assertEquals("trail.gpx", shadowActivity.getResultIntent().getStringExtra("RESULT_PATH"));
    }

    @Test
    public void testDoubleTapOnJpgPhotoOpensPhotoViewer() {
        ActivityController<ActivityFileDialogGpxJpg> controller = buildActivityController(1007);
        ActivityFileDialogGpxJpg activity = controller.create().start().get();

        Uri photoUri = Uri.parse("content://com.giobat.agps.test/mountain.jpg");
        t2 photoItem = new t2("docPhoto", "mountain.jpg", "image/jpeg", "0", 4000L, 1048576, null, photoUri);
        activity.fileItemsList.clear();
        activity.fileItemsList.add(photoItem);

        activity.openFileItem(0);

        Assert.assertFalse("Photo viewing should not finish file dialog activity", activity.isFinishing());
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        Assert.assertNotNull("ActivityMyPhotoShow must be launched for photo", startedIntent);
        Assert.assertEquals(ActivityMyPhotoShow.class.getName(), startedIntent.getComponent().getClassName());
        Assert.assertEquals(photoUri, startedIntent.getData());
        Assert.assertEquals("mountain.jpg", startedIntent.getStringExtra("filePath"));
    }

    @Test
    public void testClickOnDirectoryItemNavigatesIntoDirectory() {
        ActivityController<ActivityFileDialogGpxJpg> controller = buildActivityController(1003);
        ActivityFileDialogGpxJpg activity = controller.create().start().get();

        Uri dirUri = Uri.parse("content://com.giobat.agps.test/subfolder");
        t2 dirItem = new t2("docDir", "subfolder", "vnd.android.document/directory", "0", 5000L, 0, null, dirUri);
        activity.fileItemsList.clear();
        activity.fileItemsList.add(dirItem);

        activity.fileListView.getOnItemClickListener().onItemClick(activity.fileListView, new View(activity), 0, 0);

        Assert.assertFalse("Clicking a directory must not finish the activity", activity.isFinishing());
        Assert.assertEquals(dirUri, activity.currentDirectoryUri);
    }
}
