package c2;

import com.giobat.AgpsTrackerPP.MainActivity;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class e2 implements androidx.activity.result.b<androidx.activity.result.a> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f2981h;

    public e2(MainActivity mainActivity) {
        this.f2981h = mainActivity;
    }

    @Override // androidx.activity.result.b
    public void a(androidx.activity.result.a aVar) {
        androidx.activity.result.a aVar2 = aVar;
        Objects.requireNonNull(aVar2);
        if (aVar2.f288h == -1) {
            MainActivity.s0("TERMS_OF_USE_ACCEPTED_PP", true);
            this.f2981h.L = false;
        } else {
            MainActivity.s0("TERMS_OF_USE_ACCEPTED_PP", false);
            MainActivity.L0 = true;
            this.f2981h.h0();
            this.f2981h.finishAffinity();
        }
    }
}
