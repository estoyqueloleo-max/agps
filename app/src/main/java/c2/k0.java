package c2;

import android.os.Process;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class k0 implements Runnable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3033h;

    public k0(MainActivity mainActivity) {
        this.f3033h = mainActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        try {
            this.f3033h.H.g(MainActivity.f3628p1, AgpsApplication.f3575r, "tmp", "1.8", "tmp", XmlPullParser.NO_NAMESPACE);
            v2.e("GPS-M", "Gpx periodic save done!");
        } catch (Exception e8) {
            androidx.appcompat.widget.d0.c("Gpx SaveAs error:", e8, MainActivity.f3625m1);
        }
    }
}
