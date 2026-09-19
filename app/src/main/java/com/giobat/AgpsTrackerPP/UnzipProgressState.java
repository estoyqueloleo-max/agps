package com.giobat.AgpsTrackerPP;

/**
 * Tracks background zip extraction progress and cancellation state.
 */
public class UnzipProgressState {
    public static boolean isUnzipping = false;
    public static long unzipProgressBytes = 0;
    public static boolean cancelUnzip = false;

    public static boolean a(Object obj) {
        return obj == null || ((obj instanceof String) && ((String) obj).length() == 0);
    }
}
