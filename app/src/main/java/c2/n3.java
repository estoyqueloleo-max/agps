package c2;

import android.content.DialogInterface;
import com.giobat.AgpsTrackerPP.R;
import com.giobat.AgpsTrackerPP.SFtpServerFileDialogActivity;

/* JADX INFO: loaded from: classes.dex */
public class n3 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ y2 f3082h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ SFtpServerFileDialogActivity f3083i;

    public n3(SFtpServerFileDialogActivity sFtpServerFileDialogActivity, y2 y2Var) {
        this.f3083i = sFtpServerFileDialogActivity;
        this.f3082h = y2Var;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        SFtpServerFileDialogActivity sFtpServerFileDialogActivity = this.f3083i;
        y2 y2Var = this.f3082h;
        sFtpServerFileDialogActivity.f3729o.setVisibility(4);
        String str = y2Var.f3263j;
        String str2 = y2Var.f3262i;
        sFtpServerFileDialogActivity.f3732r.setText(sFtpServerFileDialogActivity.getResources().getString(R.string.downloading_map) + ": " + str2.substring(0, str2.indexOf(".")));
        sFtpServerFileDialogActivity.f3730p.setText("0.0");
        sFtpServerFileDialogActivity.f3731q.setText("0.0");
        SFtpServerFileDialogActivity.d(sFtpServerFileDialogActivity, str.substring(str.indexOf("maps")), str2, 2);
        m2.f3055u = true;
        sFtpServerFileDialogActivity.setResult(-1, sFtpServerFileDialogActivity.getIntent());
        sFtpServerFileDialogActivity.finish();
        dialogInterface.dismiss();
    }
}
