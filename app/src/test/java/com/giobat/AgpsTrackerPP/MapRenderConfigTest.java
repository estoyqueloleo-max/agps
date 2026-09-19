package com.giobat.AgpsTrackerPP;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class MapRenderConfigTest {

    @Test
    public void testOptimalThreadsCalculation() {
        int autoThreads = MapRenderPreferences.calculateEffectiveThreads(MapRenderPreferences.THREADS_AUTO);
        Assert.assertTrue("Auto threads should be >= 1", autoThreads >= 1);
        Assert.assertTrue("Auto threads should be <= 4", autoThreads <= 4);

        Assert.assertEquals(1, MapRenderPreferences.calculateEffectiveThreads(1));
        Assert.assertEquals(2, MapRenderPreferences.calculateEffectiveThreads(2));
        Assert.assertEquals(4, MapRenderPreferences.calculateEffectiveThreads(4));
        Assert.assertEquals(4, MapRenderPreferences.calculateEffectiveThreads(10)); // Capped at 4
    }

    @Test
    public void testPreferencesPersistence() {
        Context context = ApplicationProvider.getApplicationContext();

        // Test threads persistence
        MapRenderPreferences.setThreadsSetting(context, 2);
        Assert.assertEquals(2, MapRenderPreferences.getThreadsSetting(context));
        Assert.assertEquals(2, MapRenderPreferences.getEffectiveThreadCount(context));

        // Test hardware acceleration persistence
        MapRenderPreferences.setHardwareAccelEnabled(context, false);
        Assert.assertFalse(MapRenderPreferences.isHardwareAccelEnabled(context));
        MapRenderPreferences.setHardwareAccelEnabled(context, true);
        Assert.assertTrue(MapRenderPreferences.isHardwareAccelEnabled(context));

        // Test cache labels persistence
        MapRenderPreferences.setCacheLabelsEnabled(context, false);
        Assert.assertFalse(MapRenderPreferences.isCacheLabelsEnabled(context));
        MapRenderPreferences.setCacheLabelsEnabled(context, true);
        Assert.assertTrue(MapRenderPreferences.isCacheLabelsEnabled(context));
    }

    @Test
    public void testBuildDownloadUrlForArchiveOrg() {
        Context context = ApplicationProvider.getApplicationContext();

        // With default Archive.org settings:
        MapRenderPreferences.setUseLegacyServer(context, false);

        // Subfolder path from directory traversal: "maps/europe/spain" -> "europe/spain/comunidad_de_madrid.map"
        String url1 = MapRenderPreferences.buildDownloadUrl(context, "maps/europe/spain", "comunidad_de_madrid.map");
        Assert.assertEquals(
            "https://archive.org/download/agps-tracker-mapsforge-offline-maps/europe/spain/comunidad_de_madrid.map",
            url1
        );

        // Full legacy path from m2: "/home/giobat/maps/europe/spain"
        String url2 = MapRenderPreferences.buildDownloadUrl(context, "/home/giobat/maps/europe/spain", "comunidad_de_madrid.map");
        Assert.assertEquals(
            "https://archive.org/download/agps-tracker-mapsforge-offline-maps/europe/spain/comunidad_de_madrid.map",
            url2
        );

        // Root map: "maps" -> "spain.map" (or top level)
        String url3 = MapRenderPreferences.buildDownloadUrl(context, "maps", "africa.map");
        Assert.assertEquals(
            "https://archive.org/download/agps-tracker-mapsforge-offline-maps/africa.map",
            url3
        );

        // DEMs: "dems" -> "N40W004.SRTMGL1.hgt.zip"
        String urlDem = MapRenderPreferences.buildDownloadUrl(context, "dems", "N40W004.SRTMGL1.hgt.zip");
        Assert.assertEquals(
            "https://archive.org/download/agps-tracker-nasa-srtm-dem/N40W004.SRTMGL1.hgt.zip",
            urlDem
        );

        // Legacy toggle enabled:
        MapRenderPreferences.setUseLegacyServer(context, true);
        String legacyUrl = MapRenderPreferences.buildDownloadUrl(context, "maps/europe/spain", "comunidad_de_madrid.map");
        Assert.assertEquals(
            "http://agps-tracker.cloud/maps/europe/spain/comunidad_de_madrid.map",
            legacyUrl
        );
    }
}
