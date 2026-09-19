package q5;

import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f17465a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f17466b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f17467c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ImageView.ScaleType f17468d;

    public f(float f7, float f8, float f9, ImageView.ScaleType scaleType) {
        this.f17465a = f7;
        this.f17466b = f8;
        this.f17467c = f9;
        this.f17468d = scaleType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return w5.a.a(Float.valueOf(this.f17465a), Float.valueOf(fVar.f17465a)) && w5.a.a(Float.valueOf(this.f17466b), Float.valueOf(fVar.f17466b)) && w5.a.a(Float.valueOf(this.f17467c), Float.valueOf(fVar.f17467c)) && this.f17468d == fVar.f17468d;
    }

    public int hashCode() {
        int iHashCode = (Float.hashCode(this.f17467c) + ((Float.hashCode(this.f17466b) + (Float.hashCode(this.f17465a) * 31)) * 31)) * 31;
        ImageView.ScaleType scaleType = this.f17468d;
        return iHashCode + (scaleType == null ? 0 : scaleType.hashCode());
    }

    public String toString() {
        StringBuilder sbA = android.support.v4.media.b.a("ZoomVariables(scale=");
        sbA.append(this.f17465a);
        sbA.append(", focusX=");
        sbA.append(this.f17466b);
        sbA.append(", focusY=");
        sbA.append(this.f17467c);
        sbA.append(", scaleType=");
        sbA.append(this.f17468d);
        sbA.append(')');
        return sbA.toString();
    }
}
