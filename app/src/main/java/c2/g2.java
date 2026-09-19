package c2;

import android.os.Process;
import android.view.View;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.IntServLocGpsPP;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class g2 implements Runnable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ x3 f2992h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ String f2993i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ MainActivity f2994j;

    public g2(MainActivity mainActivity, x3 x3Var, String str) {
        this.f2994j = mainActivity;
        this.f2992h = x3Var;
        this.f2993i = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        MainActivity.f3630r1 = true;
        this.f2992h.a(this.f2993i, this.f2994j.H);
        this.f2994j.x0(this.f2992h);
        MainActivity mainActivity = this.f2994j;
        Objects.requireNonNull(mainActivity);
        AgpsApplication.f3569k = AgpsApplication.f3575r.i();
        View viewFindViewById = mainActivity.findViewById(R.id.finestra2);
        View viewFindViewById2 = mainActivity.findViewById(R.id.finestra3);
        if (AgpsApplication.f3569k > 0) {
            viewFindViewById.setVisibility(0);
            viewFindViewById2.setVisibility(0);
        }
        mainActivity.B = 2;
        AgpsApplication.f3575r.j(mainActivity.getString(R.string.current_track), XmlPullParser.NO_NAMESPACE, XmlPullParser.NO_NAMESPACE);
        MainActivity.s0("REC_ON", true);
        AgpsApplication.f3576s = 3;
        MainActivity.V0 = true;
        IntServLocGpsPP.E = 5;
        MainActivity.U0 = true;
        mainActivity.I();
        AgpsApplication.f3568j = true;
        MainActivity.f3631s1 = true;
        this.f2994j.invalidateOptionsMenu();
    }
}
