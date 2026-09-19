package c2;

import android.content.DialogInterface;
import com.giobat.AgpsTrackerPP.SFtpServerFileDialogActivity;

/* JADX INFO: loaded from: classes.dex */
public class o3 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ SFtpServerFileDialogActivity f3104h;

    public o3(SFtpServerFileDialogActivity sFtpServerFileDialogActivity) {
        this.f3104h = sFtpServerFileDialogActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        this.f3104h.f3729o.setVisibility(8);
        dialogInterface.dismiss();
    }
}
