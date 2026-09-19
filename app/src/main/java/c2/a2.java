package c2;

import android.content.Intent;
import android.net.Uri;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class a2 implements androidx.activity.result.b<androidx.activity.result.a> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f2956h;

    public a2(MainActivity mainActivity) {
        this.f2956h = mainActivity;
    }

    @Override // androidx.activity.result.b
    public void a(androidx.activity.result.a aVar) {
        androidx.activity.result.a aVar2 = aVar;
        Intent intent = aVar2.f289i;
        if (aVar2.f288h != -1) {
            return;
        }
        this.f2956h.n0((Uri) intent.getParcelableExtra("FILE_URI"), "GPXfileRD_DONE", 2);
        this.f2956h.invalidateOptionsMenu();
    }
}
