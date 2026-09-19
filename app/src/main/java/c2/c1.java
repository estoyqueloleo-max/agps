package c2;

import android.content.DialogInterface;
import android.net.Uri;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class c1 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f2968h;

    public c1(MainActivity mainActivity) {
        this.f2968h = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        MainActivity mainActivity = this.f2968h;
        Uri uri = MainActivity.f3616d1;
        Objects.requireNonNull(mainActivity);
        v2.e("GPS-M", "resume recording");
        if (MainActivity.f3615c1.exists()) {
            mainActivity.n0(uri, "GPXfileRD_DONE", 3);
        }
        dialogInterface.dismiss();
    }
}
