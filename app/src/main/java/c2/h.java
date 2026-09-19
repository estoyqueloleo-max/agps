package c2;

import android.content.DialogInterface;
import com.giobat.AgpsTrackerPP.ActivityInternalFileDialog;

/* JADX INFO: loaded from: classes.dex */
public class h implements DialogInterface.OnClickListener {
    public h(ActivityInternalFileDialog activityInternalFileDialog) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        dialogInterface.dismiss();
    }
}
