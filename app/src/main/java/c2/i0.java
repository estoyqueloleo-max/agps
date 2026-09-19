package c2;

import android.content.DialogInterface;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class i0 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3005h;

    public i0(MainActivity mainActivity) {
        this.f3005h = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        MainActivity mainActivity = this.f3005h;
        boolean z = MainActivity.I0;
        mainActivity.m0();
        dialogInterface.dismiss();
    }
}
