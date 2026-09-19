package c2;

import android.os.Process;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class f1 implements Runnable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ String f2984h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MainActivity f2985i;

    public f1(MainActivity mainActivity, String str) {
        this.f2985i = mainActivity;
        this.f2984h = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        MainActivity.f3630r1 = true;
        Process.setThreadPriority(10);
        x3 x3Var = new x3(1);
        StringBuilder sbA = android.support.v4.media.b.a("Loading track ");
        sbA.append(this.f2984h);
        v2.e("GPS-M", sbA.toString());
        x3Var.a(this.f2984h, this.f2985i.H);
        int i7 = x3Var.i();
        AgpsApplication.f3569k = i7;
        if (i7 > 0) {
            this.f2985i.B = 2;
            int size = x3Var.f3236a.size() - 1;
            if (size >= 0) {
                int iD = x3Var.f3236a.get(size).d() - 1;
                if (iD < 0 || iD >= x3Var.f3236a.get(size).f3123b.size()) {
                    StringBuilder sbA2 = android.support.v4.media.b.a("888-?? ");
                    sbA2.append(this.f2984h);
                    v2.e("GPS-M", sbA2.toString());
                    MainActivity.f3631s1 = true;
                    return;
                }
                StringBuilder sbA3 = android.support.v4.media.b.a("888-ok ");
                sbA3.append(this.f2984h);
                v2.e("GPS-M", sbA3.toString());
                AgpsApplication.f3575r = x3Var;
                this.f2985i.x0(x3Var);
                if (MainActivity.S0) {
                    AgpsApplication.f3568j = true;
                    AgpsApplication.f3576s = 3;
                    AgpsApplication.f3575r.j(this.f2985i.getString(R.string.current_track), XmlPullParser.NO_NAMESPACE, XmlPullParser.NO_NAMESPACE);
                    this.f2985i.z0();
                }
                this.f2985i.I();
                MainActivity.s0("REC_ON", true);
                MainActivity.U0 = true;
            }
        } else {
            MainActivity mainActivity = this.f2985i;
            mainActivity.w0(mainActivity.getResources().getString(R.string.empty_track_no_gpx));
        }
        MainActivity.f3631s1 = true;
    }
}
