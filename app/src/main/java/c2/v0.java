package c2;

import android.app.Dialog;
import android.view.View;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class v0 implements View.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Dialog f3200h;

    public v0(MainActivity mainActivity, Dialog dialog) {
        this.f3200h = dialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f3200h.dismiss();
    }
}
