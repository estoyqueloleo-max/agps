package c2;

import android.content.DialogInterface;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class d1 implements DialogInterface.OnClickListener {
    public d1(MainActivity mainActivity) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        MainActivity.s0("REC_ON", false);
        if (MainActivity.f3615c1.delete()) {
            v2.e("GPS-M", "OK, tmp file deleted");
        }
        dialogInterface.dismiss();
    }
}
