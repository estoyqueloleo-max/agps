package c2;

import android.content.DialogInterface;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class l0 implements DialogInterface.OnClickListener {
    public l0(MainActivity mainActivity) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        dialogInterface.dismiss();
    }
}
