package c2;

import android.content.DialogInterface;
import android.content.Intent;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class a1 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f2955h;

    public a1(MainActivity mainActivity) {
        this.f2955h = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        this.f2955h.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        dialogInterface.dismiss();
    }
}
