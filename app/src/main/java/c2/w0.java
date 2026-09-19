package c2;

import android.app.Dialog;
import android.view.View;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class w0 implements View.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Dialog f3218h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3219i;

    public w0(MainActivity mainActivity, Dialog dialog) {
        this.f3219i = mainActivity;
        this.f3218h = dialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AgpsApplication.f3568j = true;
        this.f3219i.q0();
        this.f3218h.dismiss();
    }
}
