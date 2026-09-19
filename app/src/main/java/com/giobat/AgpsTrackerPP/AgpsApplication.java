package com.giobat.AgpsTrackerPP;

import android.app.Application;
import android.content.Intent;
import c2.x3;
import java.util.concurrent.CopyOnWriteArrayList;

import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.layer.overlay.Marker;

/* JADX INFO: loaded from: classes.dex */
public class AgpsApplication extends Application {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f3566h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f3567i = true;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static boolean f3568j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static int f3569k = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static boolean f3570l = false;
    /* Follow Path & Track Back Navigation State */
    public static boolean isFollowLoadedTrackActive = false;
    public static boolean m = false;

    public static boolean isFollowReturnTrackActive = false;
    public static boolean f3571n = false;

    public static boolean isFollowPathAlarmTriggered = false;
    public static boolean f3572o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static Intent f3573p = null;

    public static x3 loadedReferenceTrack = null;
    public static x3 f3574q = null;

    public static x3 currentRecordedTrack = null;
    public static x3 f3575r = null;

    /** Progress waypoint index along follow track for remaining distance windowing */
    public static int lastMatchedFollowTrackIndex = -1;

    public static boolean isFollowPathActive() {
        return m || isFollowLoadedTrackActive || f3571n || isFollowReturnTrackActive;
    }

    public static boolean isFollowReverseActive() {
        return f3571n || isFollowReturnTrackActive;
    }

    public static x3 getActiveFollowTrack() {
        if ((m || isFollowLoadedTrackActive)) {
            return f3574q != null ? f3574q : loadedReferenceTrack;
        }
        if ((f3571n || isFollowReturnTrackActive)) {
            return f3575r != null ? f3575r : currentRecordedTrack;
        }
        return null;
    }

    public static void resetFollowTrackProgress() {
        lastMatchedFollowTrackIndex = -1;
    }

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static int f3576s = 1;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static String f3577t = "/home/giobat/maps";

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static String f3578u = "/home/giobat/dems";

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static int f3579v = -32768;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static CopyOnWriteArrayList<Marker> f3580w = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Marker> x = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static boolean f3581y = false;
    public static boolean z = false;
    public static int A = 4;
    public static byte B = 7;

    public static AgpsApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AndroidGraphicFactory.createInstance(this);
        MapRenderPreferences.applySettings(this, null);
        final Thread.UncaughtExceptionHandler defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                try {
                    android.util.Log.e("AGPS_CRASH", "FATAL CRASH in thread: " + thread.getName(), throwable);
                    java.io.StringWriter sw = new java.io.StringWriter();
                    java.io.PrintWriter pw = new java.io.PrintWriter(sw);
                    throwable.printStackTrace(pw);
                    String stackTrace = sw.toString();

                    java.io.File extDir = getExternalFilesDir(null);
                    if (extDir != null) {
                        java.io.File crashFile = new java.io.File(extDir, "crash.log");
                        java.io.FileWriter fw = new java.io.FileWriter(crashFile, false);
                        fw.write("CRASH AT: " + new java.util.Date().toString() + "\n");
                        fw.write(stackTrace);
                        fw.close();
                    }
                } catch (Exception ignored) {
                }
                if (defaultHandler != null) {
                    defaultHandler.uncaughtException(thread, throwable);
                }
            }
        });
    }
}
