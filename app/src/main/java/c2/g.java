package c2;

import android.content.DialogInterface;
import android.widget.EditText;
import com.giobat.AgpsTrackerPP.ActivityInternalFileDialog;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class g implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ String f2988h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ EditText f2989i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ File f2990j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ ActivityInternalFileDialog f2991k;

    public g(ActivityInternalFileDialog activityInternalFileDialog, String str, EditText editText, File file) {
        this.f2991k = activityInternalFileDialog;
        this.f2988h = str;
        this.f2989i = editText;
        this.f2990j = file;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        if (!this.f2990j.renameTo(MainActivity.X(this.f2991k.getApplicationContext(), MainActivity.a0(this.f2988h) + "/" + ((Object) this.f2989i.getText()) + ".map", 2))) {
            ActivityInternalFileDialog activityInternalFileDialog = this.f2991k;
            activityInternalFileDialog.A(activityInternalFileDialog.getResources().getString(R.string.error_rename_file));
        }
        ActivityInternalFileDialog activityInternalFileDialog2 = this.f2991k;
        activityInternalFileDialog2.z(activityInternalFileDialog2.f3555y);
        dialogInterface.dismiss();
    }
}
