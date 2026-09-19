package c2;

import android.content.DialogInterface;
import android.view.MenuItem;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class q1 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MenuItem f3119h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3120i;

    public q1(MainActivity mainActivity, MenuItem menuItem) {
        this.f3120i = mainActivity;
        this.f3119h = menuItem;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        MainActivity.f3621i1 = false;
        MainActivity.s0("USE_SD_MEM", false);
        this.f3119h.setChecked(false);
        this.f3120i.F();
        dialogInterface.dismiss();
    }
}
