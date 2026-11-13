package com.giobat.AgpsTrackerPP.plugin;

import android.content.Context;
import android.location.Location;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Community revival baseline implementation: pure no-op.
 * Contains 0 game logic, adding no overhead or extra menus to the outdoor navigation app.
 */
public class GameExtensionImpl implements GameExtension {

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu, Context context) {
        // No-op for community revival baseline
    }

    @Override
    public boolean onOptionsItemSelected(Context context, MenuItem item) {
        return false;
    }

    @Override
    public void onLocationUpdate(Location location) {
        // No-op
    }
}

