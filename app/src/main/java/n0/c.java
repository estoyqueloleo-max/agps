package n0;

import android.content.Context;
import android.widget.EdgeEffect;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public EdgeEffect f16786a;

    public c(Context context) {
        this.f16786a = new EdgeEffect(context);
    }

    public boolean b() {
        return this.f16786a.isFinished();
    }

    public void e() {
        this.f16786a.finish();
    }

    public boolean c(float f7) {
        this.f16786a.onPull(f7);
        return true;
    }

    public boolean d(float f7, float f8) {
        this.f16786a.onPull(f7, f8);
        return true;
    }

    public void a(int i7, int i8) {
        this.f16786a.setSize(i7, i8);
    }
}
