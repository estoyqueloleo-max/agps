package com.giobat.AgpsTrackerPP.plugin;

import android.content.Context;
import android.location.Location;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Interface contract for pluggable game and experimental extensions.
 * In community flavor, this is backed by a no-op implementation with 0 overhead.
 * In tron flavor, this is backed by the Tron Lightcycle challenge and P2P bridge.
 */
public interface GameExtension {
    /** Returns whether the game extension is enabled in this build flavor. */
    boolean isEnabled();

    /** Injects extension menu items into the action bar / options menu if applicable. */
    void onPrepareOptionsMenu(Menu menu, Context context);

    /** Handles selection of extension menu items. Returns true if handled. */
    boolean onOptionsItemSelected(Context context, MenuItem item);

    /** Receives real-time location updates from the foreground GPS tracking service. */
    void onLocationUpdate(Location location);
}

