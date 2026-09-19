package c2;

import android.app.Dialog;
import android.view.View;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class x0 implements View.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Dialog f3228h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3229i;

    public x0(MainActivity mainActivity, Dialog dialog) {
        this.f3229i = mainActivity;
        this.f3228h = dialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f3229i.n0(MainActivity.f3629q1, "GPXfileRD_DONE", 4);
        this.f3228h.dismiss();
    }
}
