package t5;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f18104a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final double f18105b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final double f18106c;

    public e(s5.a aVar, s5.a aVar2, String str, double d8, double d9) {
        this.f18104a = str;
        this.f18105b = d8;
        this.f18106c = d9;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0048  */
    /* JADX WARN: Code duplicated, block: B:72:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:74:0x01b9  */
    public static e a(s5.a aVar, s5.a aVar2, u5.a aVar3) {
        double d8 = 0.0d;
        double d9 = 0.0d;
        String str = "gov.nasa.worldwind.avkey.North";
        double d10;
        String str2;
        double d11;
        double d12 = 6378137.0d;
        double d13 = 0.0033528106647474805d;
        b bVar = new b();
        if (aVar3 != null) {
            double dA = aVar3.a();
            double dA2 = (aVar3.a() - aVar3.b()) / aVar3.a();
            double d14 = 1.0d / dA2;
            if (dA > 0.0d && d14 >= 250.0d && d14 <= 350.0d) {
                d12 = dA;
                d13 = dA2;
            }
        }
        double d15 = aVar.f17762i;
        double d16 = aVar2.f17762i;
        long j7 = 1;
        if (d15 >= -1.5707963267948966d && d15 <= 1.5707963267948966d && ((d15 >= 0.0d || d15 <= -1.2566370614359172d) && (d15 < 0.0d || d15 >= 1.2566370614359172d))) {
            if (d16 < -3.141592653589793d || d16 > 6.283185307179586d) {
                j7 = 2;
            } else {
                if (d15 < 0.0d) {
                    d10 = -1.4157155848011311d;
                    str2 = "gov.nasa.worldwdind.avkey.South";
                } else {
                    d10 = 1.4157155848011311d;
                    str2 = "gov.nasa.worldwind.avkey.North";
                }
                double d17 = 1.0d / d13;
                long j8 = d12 <= 0.0d ? 64L : 0L;
                if (d17 < 250.0d || d17 > 350.0d) {
                    j8 |= 128;
                }
                if (d10 < -1.5707963267948966d || d10 > 1.5707963267948966d) {
                    j8 |= 4;
                }
                if (j8 == 0) {
                    bVar.f18074a = d12;
                    bVar.f18081h = d12 * 2.0d;
                    if (d10 < 0.0d) {
                        bVar.f18077d = 1.0d;
                        bVar.f18082i = -d10;
                        d11 = -0.0d;
                    } else {
                        bVar.f18077d = 0.0d;
                        bVar.f18082i = d10;
                        d11 = 0.0d;
                    }
                    bVar.f18083j = d11;
                    bVar.f18084k = 0.0d;
                    bVar.f18085l = 0.0d;
                    double dSqrt = Math.sqrt((d13 * 2.0d) - (d13 * d13));
                    bVar.f18075b = dSqrt;
                    bVar.f18076c = dSqrt / 2.0d;
                    if (Math.abs(Math.abs(bVar.f18082i) - 1.5707963267948966d) > 1.0E-10d) {
                        double dSin = bVar.f18075b * Math.sin(bVar.f18082i);
                        double dPow = Math.pow((1.0d - dSin) / (dSin + 1.0d), bVar.f18076c);
                        bVar.f18080g = bVar.f18074a * (Math.cos(bVar.f18082i) / Math.sqrt(1.0d - (dSin * dSin)));
                        bVar.f18078e = Math.tan(0.7853981633974483d - (bVar.f18082i / 2.0d)) / dPow;
                    } else {
                        double d18 = bVar.f18075b;
                        double d19 = d18 + 1.0d;
                        double d20 = 1.0d - d18;
                        bVar.f18079f = Math.sqrt(Math.pow(d20, d20) * Math.pow(d19, d19));
                    }
                }
                bVar.a(0.0d, bVar.f18083j);
                Math.abs(bVar.f18086n * 2.0d);
                bVar.a(d15, d16);
                d8 = bVar.m + 2000000.0d;
                d9 = "gov.nasa.worldwdind.avkey.South".equals(str2) ? 2000000.0d - bVar.f18086n : bVar.f18086n + 2000000.0d;
                str = str2;
                j7 = 0;
            }
            if (j7 == 0) {
                return new e(aVar, aVar2, str, d8, d9);
            }
            String strA = v5.a.a("Coord.UPSConversionError");
            v5.a.d().severe(strA);
            throw new IllegalArgumentException(strA);
        }
        d8 = 0.0d;
        d9 = 0.0d;
        str = "gov.nasa.worldwind.avkey.North";
        if (j7 == 0) {
            return new e(aVar, aVar2, str, d8, d9);
        }
        String strA2 = v5.a.a("Coord.UPSConversionError");
        v5.a.d().severe(strA2);
        throw new IllegalArgumentException(strA2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("gov.nasa.worldwind.avkey.North".equals(this.f18104a) ? "N" : "S");
        sb.append(" ");
        sb.append(this.f18105b);
        sb.append("E");
        sb.append(" ");
        sb.append(this.f18106c);
        sb.append("N");
        return sb.toString();
    }
}
