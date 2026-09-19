package c2;

import android.location.Location;
import android.os.Process;
import c2.x3.b;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class x1 implements Runnable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ x3 f3230h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3231i;

    public x1(MainActivity mainActivity, x3 x3Var) {
        this.f3231i = mainActivity;
        this.f3230h = x3Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        if (this.f3230h.i() > 1) {
            x3 x3Var = this.f3230h;
            r rVar = this.f3231i.H;
            Objects.requireNonNull(x3Var);
            x3Var.f3242g = MainActivity.Z0.getString(R.string.new_modif_track);
            int size = rVar.f3132c.size();
            if (rVar.f3135f.size() != 0 && rVar.f3136g.size() != 0) {
                x3Var.j(x3Var.f3242g, rVar.f3135f.get(0), rVar.f3136g.get(0));
                if (size != 0) {
                    int i7 = 0;
                    for (int i8 = 0; i8 < size; i8++) {
                        try {
                            Location location = rVar.f3132c.get(i8);
                            boolean zBooleanValue = rVar.f3134e.get(i8).booleanValue();
                            x3Var.b(location);
                            if (zBooleanValue) {
                                if (i8 != size - 1) {
                                    i7++;
                                    x3Var.j(x3Var.f3242g, rVar.f3135f.get(i7), rVar.f3136g.get(i7));
                                }
                                x3Var.n(rVar.f3132c.get(i8), x3Var.d().f3127f);
                            }
                        } catch (Exception unused) {
                        }
                    }
                    x3Var.f3237b.addAll(rVar.f3133d);
                    x3Var.l();
                    new Thread(x3Var.new b(null)).start();
                } else {
                    x3Var.l();
                    new Thread(x3Var.new b(null)).start();
                }
            }
            this.f3231i.B = 1;
            MainActivity.U0 = true;
            AgpsApplication.f3570l = true;
            x3 x3Var2 = this.f3230h;
            if (x3Var2 != null && x3Var2.f3236a.size() > 0 && this.f3230h.f3236a.get(0).f3122a.size() > 0) {
                this.f3231i.M(this.f3230h.f3236a.get(0).f3122a.get(0), true, false);
                this.f3231i.x0(this.f3230h);
            }
            this.f3231i.invalidateOptionsMenu();
        }
    }
}
