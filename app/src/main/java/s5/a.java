package s5;

/* JADX INFO: loaded from: classes.dex */
public class a implements Comparable<a> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final double f17761h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final double f17762i;

    public a(double d8, double d9) {
        this.f17761h = d8;
        this.f17762i = d9;
    }

    public static a a(double d8) {
        return new a(d8, 0.017453292519943295d * d8);
    }

    public static a d(double d8) {
        return new a(57.29577951308232d * d8, d8);
    }

    @Override // java.lang.Comparable
    public int compareTo(a aVar) {
        a aVar2 = aVar;
        if (aVar2 == null) {
            String strA = v5.a.a("nullValue.AngleIsNull");
            v5.a.d().severe(strA);
            throw new IllegalArgumentException(strA);
        }
        double d8 = this.f17761h;
        double d9 = aVar2.f17761h;
        if (d8 < d9) {
            return -1;
        }
        return d8 > d9 ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && a.class == obj.getClass() && ((a) obj).f17761h == this.f17761h;
    }

    public int hashCode() {
        double d8 = this.f17761h;
        long jDoubleToLongBits = d8 != 0.0d ? Double.doubleToLongBits(d8) : 0L;
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    public final String toString() {
        return Double.toString(this.f17761h) + (char) 176;
    }
}
