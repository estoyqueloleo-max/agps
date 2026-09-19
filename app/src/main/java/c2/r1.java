package c2;

import android.content.DialogInterface;
import android.view.MenuItem;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class r1 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MenuItem f3146h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3147i;

    public r1(MainActivity mainActivity, MenuItem menuItem) {
        this.f3147i = mainActivity;
        this.f3146h = menuItem;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        MainActivity.f3621i1 = true;
        MainActivity.s0("USE_SD_MEM", true);
        this.f3146h.setChecked(true);
        this.f3147i.F();
        dialogInterface.dismiss();
    }
}
