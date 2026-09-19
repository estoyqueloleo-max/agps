package t5;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final double f18087a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final double f18088b;

    public c(s5.a aVar, s5.a aVar2, double d8, double d9, s5.a aVar3, s5.a aVar4, double d10, double d11, double d12) {
        this.f18087a = d8;
        this.f18088b = d9;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0051  */
    /* JADX WARN: Code duplicated, block: B:14:0x0054  */
    /* JADX WARN: Code duplicated, block: B:19:0x0068  */
    /* JADX WARN: Code duplicated, block: B:24:0x007e  */
    /* JADX WARN: Code duplicated, block: B:29:0x0094  */
    /* JADX WARN: Code duplicated, block: B:34:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:37:0x00af  */
    /* JADX WARN: Code duplicated, block: B:39:0x0170  */
    /* JADX WARN: Code duplicated, block: B:41:0x017f  */
    /* JADX WARN: Code duplicated, block: B:43:0x0187  */
    /* JADX WARN: Code duplicated, block: B:44:0x0194  */
    public static c a(s5.a aVar, s5.a aVar2, u5.a aVar3, Double d8, Double d9, s5.a aVar4, s5.a aVar5, double d10, double d11, double d12) {
        Double dValueOf;
        double dA;
        Double dValueOf2;
        double dDoubleValue;
        double dDoubleValue2;
        double d13;
        double d14;
        double d15;
        long jA;
        d dVar = new d();
        if (aVar3 == null) {
            if (d8 == null || d9 == null) {
                dValueOf = Double.valueOf(dVar.f18089a);
                dA = dVar.f18090b;
                dValueOf2 = Double.valueOf(dA);
            } else {
                dValueOf = d8;
                dValueOf2 = d9;
            }
            dDoubleValue = dValueOf.doubleValue();
            dDoubleValue2 = dValueOf2.doubleValue();
            d13 = aVar4.f17762i;
            d14 = aVar5.f17762i;
            d15 = 1.0d / dDoubleValue2;
            if (dDoubleValue <= 0.0d) {
                jA = 64;
            } else {
                jA = 0;
            }
            if (d15 >= 250.0d || d15 > 350.0d) {
                jA |= 128;
            }
            if (d13 >= -1.570621793869697d || d13 > 1.570621793869697d) {
                jA |= 16;
            }
            if (d14 >= -3.141592653589793d || d14 > 6.283185307179586d) {
                jA |= 32;
            }
            if (d12 >= 0.3d || d12 > 3.0d) {
                jA |= 256;
            }
            if (jA == 0) {
                dVar.f18089a = dDoubleValue;
                dVar.f18090b = dDoubleValue2;
                dVar.f18093e = 0.0d;
                dVar.f18094f = 0.0d;
                dVar.f18095g = 0.0d;
                dVar.f18096h = 0.0d;
                dVar.f18097i = 1.0d;
                double d16 = (dDoubleValue2 * 2.0d) - (dDoubleValue2 * dDoubleValue2);
                dVar.f18091c = d16;
                dVar.f18092d = (1.0d / (1.0d - d16)) - 1.0d;
                double d17 = (1.0d - dDoubleValue2) * dDoubleValue;
                double d18 = (dDoubleValue - d17) / (d17 + dDoubleValue);
                double d19 = d18 * d18;
                double d20 = d19 * d18;
                double d21 = d20 * d18;
                double d22 = d21 * d18;
                double d23 = d19 - d20;
                double d24 = d21 - d22;
                dVar.f18098j = (((81.0d * d24) / 64.0d) + ((5.0d * d23) / 4.0d) + (1.0d - d18)) * dDoubleValue;
                double d25 = d20 - d21;
                dVar.f18099k = ((((55.0d * d22) / 64.0d) + (((7.0d * d25) / 8.0d) + (d18 - d19))) * (dDoubleValue * 3.0d)) / 2.0d;
                dVar.f18100l = ((((3.0d * d24) / 4.0d) + d23) * (15.0d * dDoubleValue)) / 16.0d;
                dVar.m = ((((d22 * 11.0d) / 16.0d) + d25) * (35.0d * dDoubleValue)) / 48.0d;
                dVar.f18101n = ((dDoubleValue * 315.0d) * d24) / 512.0d;
                dVar.a(1.570621793869697d, 1.5707963267948966d);
                dVar.a(0.0d, 1.5707963267948966d);
                dVar.f18093e = d13;
                if (d14 > 3.141592653589793d) {
                    d14 -= 6.283185307179586d;
                }
                dVar.f18094f = d14;
                dVar.f18095g = d11;
                dVar.f18096h = d10;
                dVar.f18097i = d12;
            }
            if (jA == 0) {
                jA = dVar.a(aVar.f17762i, aVar2.f17762i);
            }
            if (jA != 0 || jA == 512) {
                return new c(aVar, aVar2, dVar.f18102o, dVar.f18103p, aVar4, aVar5, d10, d11, d12);
            }
            String strA = v5.a.a("Coord.TMConversionError");
            v5.a.d().severe(strA);
            throw new IllegalArgumentException(strA);
        }
        dValueOf = Double.valueOf(aVar3.a());
        dA = (aVar3.a() - aVar3.b()) / aVar3.a();
        dValueOf2 = Double.valueOf(dA);
        dDoubleValue = dValueOf.doubleValue();
        dDoubleValue2 = dValueOf2.doubleValue();
        d13 = aVar4.f17762i;
        d14 = aVar5.f17762i;
        d15 = 1.0d / dDoubleValue2;
        if (dDoubleValue <= 0.0d) {
            jA = 64;
        } else {
            jA = 0;
        }
        if (d15 >= 250.0d) {
            jA |= 128;
        } else {
            jA |= 128;
        }
        if (d13 >= -1.570621793869697d) {
            jA |= 16;
        } else {
            jA |= 16;
        }
        if (d14 >= -3.141592653589793d) {
            jA |= 32;
        } else {
            jA |= 32;
        }
        if (d12 >= 0.3d) {
            jA |= 256;
        } else {
            jA |= 256;
        }
        if (jA == 0) {
            dVar.f18089a = dDoubleValue;
            dVar.f18090b = dDoubleValue2;
            dVar.f18093e = 0.0d;
            dVar.f18094f = 0.0d;
            dVar.f18095g = 0.0d;
            dVar.f18096h = 0.0d;
            dVar.f18097i = 1.0d;
            double d110 = (dDoubleValue2 * 2.0d) - (dDoubleValue2 * dDoubleValue2);
            dVar.f18091c = d110;
            dVar.f18092d = (1.0d / (1.0d - d110)) - 1.0d;
            double d111 = (1.0d - dDoubleValue2) * dDoubleValue;
            double d112 = (dDoubleValue - d111) / (d111 + dDoubleValue);
            double d113 = d112 * d112;
            double d26 = d113 * d112;
            double d27 = d26 * d112;
            double d28 = d27 * d112;
            double d29 = d113 - d26;
            double d210 = d27 - d28;
            dVar.f18098j = (((81.0d * d210) / 64.0d) + ((5.0d * d29) / 4.0d) + (1.0d - d112)) * dDoubleValue;
            double d211 = d26 - d27;
            dVar.f18099k = ((((55.0d * d28) / 64.0d) + (((7.0d * d211) / 8.0d) + (d112 - d113))) * (dDoubleValue * 3.0d)) / 2.0d;
            dVar.f18100l = ((((3.0d * d210) / 4.0d) + d29) * (15.0d * dDoubleValue)) / 16.0d;
            dVar.m = ((((d28 * 11.0d) / 16.0d) + d211) * (35.0d * dDoubleValue)) / 48.0d;
            dVar.f18101n = ((dDoubleValue * 315.0d) * d210) / 512.0d;
            dVar.a(1.570621793869697d, 1.5707963267948966d);
            dVar.a(0.0d, 1.5707963267948966d);
            dVar.f18093e = d13;
            if (d14 > 3.141592653589793d) {
                d14 -= 6.283185307179586d;
            }
            dVar.f18094f = d14;
            dVar.f18095g = d11;
            dVar.f18096h = d10;
            dVar.f18097i = d12;
        }
        if (jA == 0) {
            jA = dVar.a(aVar.f17762i, aVar2.f17762i);
        }
        if (jA != 0) {
        }
        return new c(aVar, aVar2, dVar.f18102o, dVar.f18103p, aVar4, aVar5, d10, d11, d12);
    }
}
