package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import org.mapsforge.map.android.rotation.RotateView;
import org.mapsforge.core.util.Parameters;

public class MapRenderPreferences {

    public static final String PREF_NAME = "agps_map_render_prefs";
    public static final String KEY_THREADS = "pref_render_threads";
    public static final String KEY_HARDWARE_ACCEL = "pref_render_hardware_accel";
    public static final String KEY_CACHE_LABELS = "pref_render_cache_labels";
    // Map Sources preferences
    public static final String KEY_USE_LEGACY_SERVER = "pref_use_legacy_server";
    public static final String KEY_MAPS_URL = "pref_maps_url";
    public static final String KEY_DEMS_URL = "pref_dems_url";
    public static final String DEFAULT_MAPS_URL = "https://archive.org/download/agps-tracker-mapsforge-offline-maps/";
    public static final String DEFAULT_DEMS_URL = "https://archive.org/download/agps-tracker-nasa-srtm-dem/";

    public static final int THREADS_AUTO = 0;

    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static int getThreadsSetting(Context context) {
        return getPrefs(context).getInt(KEY_THREADS, THREADS_AUTO);
    }

    public static void setThreadsSetting(Context context, int threads) {
        getPrefs(context).edit().putInt(KEY_THREADS, threads).apply();
    }

    public static int calculateEffectiveThreads(int setting) {
        if (setting <= 0) {
            int cores = Runtime.getRuntime().availableProcessors();
            return Math.max(1, Math.min(4, cores));
        }
        return Math.max(1, Math.min(4, setting));
    }

    public static int getEffectiveThreadCount(Context context) {
        return calculateEffectiveThreads(getThreadsSetting(context));
    }

    public static boolean isHardwareAccelEnabled(Context context) {
        return getPrefs(context).getBoolean(KEY_HARDWARE_ACCEL, false);
    }

    public static void setHardwareAccelEnabled(Context context, boolean enabled) {
        getPrefs(context).edit().putBoolean(KEY_HARDWARE_ACCEL, enabled).apply();
    }

    public static boolean isCacheLabelsEnabled(Context context) {
        return getPrefs(context).getBoolean(KEY_CACHE_LABELS, true);
    }

    public static void setCacheLabelsEnabled(Context context, boolean enabled) {
        getPrefs(context).edit().putBoolean(KEY_CACHE_LABELS, enabled).apply();
    }

    public static void applySettings(Context context, RotateView rotateView) {
        int threads = getEffectiveThreadCount(context);
        Parameters.NUMBER_OF_THREADS = threads;
        Parameters.PARENT_TILES_RENDERING = Parameters.ParentTilesRendering.SPEED;
        Parameters.SQUARE_FRAME_BUFFER = true;
        Parameters.MAXIMUM_BUFFER_SIZE = 25000000;

        if (rotateView != null) {
            boolean hw = isHardwareAccelEnabled(context);
            rotateView.setLayerType(hw ? View.LAYER_TYPE_HARDWARE : View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    // --- Map Sources ---

    public static boolean isUseLegacyServer(Context context) {
        return getPrefs(context).getBoolean(KEY_USE_LEGACY_SERVER, false);
    }

    public static void setUseLegacyServer(Context context, boolean useLegacy) {
        getPrefs(context).edit().putBoolean(KEY_USE_LEGACY_SERVER, useLegacy).apply();
    }

    public static String getMapsUrl(Context context) {
        return getPrefs(context).getString(KEY_MAPS_URL, DEFAULT_MAPS_URL);
    }

    public static void setMapsUrl(Context context, String url) {
        getPrefs(context).edit().putString(KEY_MAPS_URL, url).apply();
    }

    public static String getDemUrl(Context context) {
        return getPrefs(context).getString(KEY_DEMS_URL, DEFAULT_DEMS_URL);
    }

    public static void setDemUrl(Context context, String url) {
        getPrefs(context).edit().putString(KEY_DEMS_URL, url).apply();
    }

    /**
     * Returns the effective maps download base URL respecting the legacy toggle.
     * When legacy mode is active, returns the original agps-tracker.cloud base URL.
     */
    public static String getEffectiveMapsBaseUrl(Context context) {
        if (isUseLegacyServer(context)) {
            return "http://agps-tracker.cloud/maps/";
        }
        return getMapsUrl(context);
    }

    /**
     * Returns the effective DEMs download base URL respecting the legacy toggle.
     * When legacy mode is active, returns the original agps-tracker.cloud base URL.
     */
    public static String getEffectiveDemBaseUrl(Context context) {
        if (isUseLegacyServer(context)) {
            return "http://agps-tracker.cloud/dems/";
        }
        return getDemUrl(context);
    }

    /**
     * Constructs the full HTTP download URL for a given resourceType and fileName.
     * Handles Archive.org subdirectory structure (e.g. "europe/spain/comunidad_de_madrid.map").
     */
    public static String buildDownloadUrl(Context context, String resourceType, String fileName) {
        if (isUseLegacyServer(context)) {
            return "http://agps-tracker.cloud/" + resourceType + "/" + fileName;
        }
        if ("dems".equals(resourceType)) {
            String demBase = getDemUrl(context);
            if (!demBase.endsWith("/")) {
                demBase = demBase + "/";
            }
            return demBase + fileName;
        }

        // Maps: Extract relative subfolder after "maps" (e.g., "maps/europe/spain" -> "europe/spain")
        String mapBase = getMapsUrl(context);
        if (!mapBase.endsWith("/")) {
            mapBase = mapBase + "/";
        }
        String relativeSubDir = "";
        int mapsIdx = resourceType != null ? resourceType.indexOf("maps") : -1;
        if (mapsIdx != -1) {
            String afterMaps = resourceType.substring(mapsIdx + "maps".length());
            while (afterMaps.startsWith("/")) {
                afterMaps = afterMaps.substring(1);
            }
            while (afterMaps.endsWith("/")) {
                afterMaps = afterMaps.substring(0, afterMaps.length() - 1);
            }
            relativeSubDir = afterMaps;
        }
        if (relativeSubDir.isEmpty()) {
            return mapBase + fileName;
        }
        return mapBase + relativeSubDir + "/" + fileName;
    }
}
