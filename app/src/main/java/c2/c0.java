package c2;

import android.app.AlertDialog;
import android.util.Log;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c0 {
    public static AlertDialog.Builder a(MainActivity mainActivity, int i7, AlertDialog.Builder builder) {
        return builder.setTitle(mainActivity.getApplication().getResources().getString(i7));
    }

    public static void b(int i7, String str, int i8, String str2) {
        StringBuilder sb = new StringBuilder(i7);
        sb.append(str);
        sb.append(i8);
        Log.w(str2, sb.toString());
    }
}
