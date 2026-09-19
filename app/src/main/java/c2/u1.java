package c2;

import android.content.DialogInterface;
import android.view.MenuItem;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class u1 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MenuItem f3193h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3194i;

    public u1(MainActivity mainActivity, MenuItem menuItem) {
        this.f3194i = mainActivity;
        this.f3193h = menuItem;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        this.f3193h.setChecked(false);
        MainActivity.f3623k1 = false;
        MainActivity.s0("USE_OSM_MAPS", false);
        this.f3194i.invalidateOptionsMenu();
        dialogInterface.dismiss();
    }
}
