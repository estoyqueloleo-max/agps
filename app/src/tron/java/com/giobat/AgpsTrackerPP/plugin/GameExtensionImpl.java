package com.giobat.AgpsTrackerPP.plugin;

import android.content.Context;
import android.location.Location;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Tron edition implementation: activates Tron Lightcycle challenge and P2P bridge.
 */
public class GameExtensionImpl implements GameExtension {
    public static final int MENU_TRON_ID = 9991;

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu, Context context) {
        if (menu != null && menu.findItem(MENU_TRON_ID) == null) {
            menu.add(Menu.NONE, MENU_TRON_ID, 15, "🏍️ Tron Lightcycle")
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        }
    }

    @Override
    public boolean onOptionsItemSelected(Context context, MenuItem item) {
        if (item != null && item.getItemId() == MENU_TRON_ID) {
            android.content.Intent intent = new android.content.Intent(context, com.giobat.AgpsTrackerPP.tron.TronArenaActivity.class);
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void onLocationUpdate(Location location) {
        // Will feed TronCollisionEngine and Pingo P2P bridge in subsequent phases
    }
}

