package p5;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class b implements c, Serializable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public double f17269h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public double f17270i;

    public b(double d8, double d9) {
        this.f17269h = d8;
        this.f17270i = d9;
    }

    @Override // p5.c
    public double getX() {
        return this.f17269h;
    }

    @Override // p5.c
    public double getY() {
        return this.f17270i;
    }

    public String toString() {
        StringBuilder sbA = android.support.v4.media.b.a("[");
        sbA.append(this.f17269h);
        sbA.append("/");
        sbA.append(this.f17270i);
        sbA.append("]");
        return sbA.toString();
    }
}
