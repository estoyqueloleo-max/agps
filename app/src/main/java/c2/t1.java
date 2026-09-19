package c2;

import android.content.Context;
import android.content.DialogInterface;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class t1 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Context f3178h;

    public t1(MainActivity mainActivity, Context context) {
        this.f3178h = context;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        new androidx.appcompat.widget.p(this.f3178h, 4);
        dialogInterface.dismiss();
    }
}
