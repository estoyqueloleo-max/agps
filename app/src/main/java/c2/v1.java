package c2;

import android.content.DialogInterface;
import android.view.MenuItem;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;

/* JADX INFO: loaded from: classes.dex */
public class v1 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MenuItem f3201h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3202i;

    public v1(MainActivity mainActivity, MenuItem menuItem) {
        this.f3202i = mainActivity;
        this.f3201h = menuItem;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        if (this.f3201h.isChecked()) {
            MainActivity.f3623k1 = true;
            MainActivity.s0("USE_OSM_MAPS", true);
            this.f3202i.w0(this.f3202i.getApplicationContext().getString(R.string.oam_maps_enabled));
        }
        this.f3202i.invalidateOptionsMenu();
        dialogInterface.dismiss();
    }
}
