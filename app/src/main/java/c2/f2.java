package c2;

import android.content.Intent;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class f2 implements androidx.activity.result.b<androidx.activity.result.a> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f2986h;

    public f2(MainActivity mainActivity) {
        this.f2986h = mainActivity;
    }

    @Override // androidx.activity.result.b
    public void a(androidx.activity.result.a aVar) {
        String stringExtra;
        androidx.activity.result.a aVar2 = aVar;
        Intent intent = aVar2.f289i;
        if (aVar2.f288h == -1 && (stringExtra = intent.getStringExtra("RESULT_PATH")) != null) {
            y2 y2Var = new y2(MainActivity.Y(stringExtra), "Maps", 0L, false, 0L, 2);
            Iterator it = ((ArrayList) m2.f3052r).iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (((y2) it.next()).f3262i.equals(y2Var.f3262i)) {
                    z = true;
                }
            }
            if (!z) {
                ((ArrayList) m2.f3052r).add(y2Var);
            }
            this.f2986h.z.g(m2.f3052r, true);
            this.f2986h.z.a();
            MainActivity.isAutoCenterMapOnGps = false;
            MainActivity.J0 = false;
            MainActivity.initialCenterOnGpsPending = false;
            MainActivity.I0 = false;
            this.f2986h.y0();
        }
    }
}
