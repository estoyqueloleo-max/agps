package c2;

import android.content.DialogInterface;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class o0 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3099h;

    public o0(MainActivity mainActivity) {
        this.f3099h = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        MainActivity mainActivity = this.f3099h;
        boolean z = MainActivity.I0;
        mainActivity.K();
        dialogInterface.dismiss();
    }
}
