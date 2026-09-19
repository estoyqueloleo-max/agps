package c2;

import android.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import com.giobat.AgpsTrackerPP.SFtpServerFileDialogActivity;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class l3 implements AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ SFtpServerFileDialogActivity f3043h;

    public l3(SFtpServerFileDialogActivity sFtpServerFileDialogActivity) {
        this.f3043h = sFtpServerFileDialogActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i7, long j7) {
        String str;
        SFtpServerFileDialogActivity sFtpServerFileDialogActivity = this.f3043h;
        ListView listView = sFtpServerFileDialogActivity.f3737w;
        sFtpServerFileDialogActivity.f3729o.setVisibility(0);
        y2 y2Var = SFtpServerFileDialogActivity.f3722y.get(i7);
        if (y2Var.f3261h) {
            if (y2Var.f3262i.equals("⤴")) {
                String strR = MainActivity.R(y2Var.f3263j);
                sFtpServerFileDialogActivity.m = strR;
                MainActivity.K0 = strR;
            } else {
                String strA = y2Var.a();
                sFtpServerFileDialogActivity.m = strA;
                MainActivity.K0 = strA;
            }
            sFtpServerFileDialogActivity.c(sFtpServerFileDialogActivity.m);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(sFtpServerFileDialogActivity);
        builder.setTitle(sFtpServerFileDialogActivity.getResources().getString(R.string.ftp_downl_warning1));
        try {
            File[] externalFilesDirs = sFtpServerFileDialogActivity.getApplicationContext().getExternalFilesDirs(null);
            File externalFilesDir = (MainActivity.f3621i1 && externalFilesDirs.length == 2) ? externalFilesDirs[1] : sFtpServerFileDialogActivity.getApplicationContext().getExternalFilesDir(null);
            String str2 = sFtpServerFileDialogActivity.getResources().getString(R.string.file) + ": <b>" + y2Var.f3262i + "</b><br>" + sFtpServerFileDialogActivity.getResources().getString(R.string.size_of) + " " + y2.d(y2Var.f3265l.longValue()) + "<br><br>";
            if (MainActivity.f3621i1) {
                str = str2 + sFtpServerFileDialogActivity.getResources().getString(R.string.sd_mem) + "<br>";
            } else {
                str = str2 + sFtpServerFileDialogActivity.getResources().getString(R.string.internal_mem) + "<br>";
            }
            String str3 = str + sFtpServerFileDialogActivity.getResources().getString(R.string.sd_free_space) + " " + y2.d(externalFilesDir.getFreeSpace()) + "<br>\n\n " + sFtpServerFileDialogActivity.getResources().getString(R.string.network) + ": " + MainActivity.Z(sFtpServerFileDialogActivity) + "<br><br>";
            if (externalFilesDir.getFreeSpace() < y2Var.f3265l.longValue() * 2) {
                str3 = str3 + "\n" + sFtpServerFileDialogActivity.getResources().getString(R.string.not_enough_memory);
            }
            builder.setMessage(Html.fromHtml(str3, 63));
            builder.setNegativeButton(sFtpServerFileDialogActivity.getResources().getString(R.string.cancel), new m3(sFtpServerFileDialogActivity));
            if (externalFilesDir.getFreeSpace() < y2Var.f3265l.longValue() * 2) {
                sFtpServerFileDialogActivity.f3729o.setVisibility(8);
            } else {
                builder.setPositiveButton(sFtpServerFileDialogActivity.getResources().getString(R.string.ok), new n3(sFtpServerFileDialogActivity, y2Var));
            }
        } catch (Exception e8) {
            builder.setMessage(sFtpServerFileDialogActivity.getResources().getString(R.string.error) + ": " + e8);
            builder.setPositiveButton(sFtpServerFileDialogActivity.getResources().getString(R.string.ok), new o3(sFtpServerFileDialogActivity));
        }
        builder.show();
    }
}
