package c2;

import android.content.DialogInterface;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class g1 implements DialogInterface.OnClickListener {
    public g1(MainActivity mainActivity) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        try {
            dialogInterface.dismiss();
        } catch (Exception unused) {
        }
    }
}
