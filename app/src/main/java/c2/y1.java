package c2;

import com.giobat.AgpsTrackerPP.MainActivity;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class y1 implements androidx.activity.result.b<androidx.activity.result.a> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3260h;

    public y1(MainActivity mainActivity) {
        this.f3260h = mainActivity;
    }

    @Override // androidx.activity.result.b
    public void a(androidx.activity.result.a aVar) {
        androidx.activity.result.a aVar2 = aVar;
        Objects.requireNonNull(aVar2);
        if (aVar2.f288h != -1) {
            return;
        }
        try {
            MainActivity.z(this.f3260h);
        } catch (Exception e8) {
            androidx.appcompat.widget.d0.c("Resolver error = ", e8, MainActivity.f3625m1);
        }
    }
}
