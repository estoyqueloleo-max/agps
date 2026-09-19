package c2;

import android.os.Process;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class n1 implements Runnable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ x3 f3077h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ String f3078i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ boolean f3079j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3080k;

    public n1(MainActivity mainActivity, x3 x3Var, String str, boolean z) {
        this.f3080k = mainActivity;
        this.f3077h = x3Var;
        this.f3078i = str;
        this.f3079j = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        MainActivity.f3630r1 = true;
        this.f3077h.a(this.f3078i, this.f3080k.H);
        if (this.f3079j) {
            this.f3080k.B = 1;
            AgpsApplication.f3570l = true;
            if (this.f3077h.f3236a.size() > 0 && this.f3077h.f3236a.get(0).f3122a.size() > 0) {
                this.f3080k.M(this.f3077h.f3236a.get(0).f3122a.get(0), true, false);
                this.f3080k.x0(this.f3077h);
            }
        } else {
            this.f3080k.B = 2;
            int size = this.f3077h.f3236a.size() - 1;
            if (size >= 0) {
                int iD = this.f3077h.f3236a.get(size).d() - 1;
                if (iD < 0 || iD >= this.f3077h.f3236a.get(size).f3123b.size()) {
                    MainActivity.f3631s1 = true;
                    return;
                }
                this.f3080k.x0(AgpsApplication.f3575r);
                AgpsApplication.f3568j = true;
                AgpsApplication.f3576s = 3;
                AgpsApplication.f3575r.j(this.f3080k.getString(R.string.current_track), XmlPullParser.NO_NAMESPACE, XmlPullParser.NO_NAMESPACE);
                this.f3080k.z0();
            }
            this.f3080k.I();
            MainActivity.s0("REC_ON", true);
            MainActivity.U0 = true;
        }
        MainActivity.f3631s1 = true;
        this.f3080k.invalidateOptionsMenu();
    }
}
