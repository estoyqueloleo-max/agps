package c2;

import android.content.DialogInterface;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class m0 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3046h;

    public m0(MainActivity mainActivity) {
        this.f3046h = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        MainActivity.L0 = true;
        MainActivity mainActivity = this.f3046h;
        mainActivity.h0();
        dialogInterface.dismiss();
    }
}
