package t5;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f18107a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18108b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final double f18109c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final double f18110d;

    public f(s5.a aVar, s5.a aVar2, int i7, String str, double d8, double d9, s5.a aVar3) {
        this.f18107a = str;
        this.f18108b = i7;
        this.f18109c = d8;
        this.f18110d = d9;
    }

    /* JADX WARN: Code duplicated, block: B:121:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:123:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:16:0x004b  */
    public static f a(s5.a aVar, s5.a aVar2, u5.a aVar3) {
        int i7;
        long j7;
        String str;
        double d8;
        double d9;
        String str2;
        double d10;
        double d11;
        double d12 = 0.0d;
        double d13 = 0.0d;
        long j8;
        double d14 = 6378137.0d;
        double d15 = 0.0033528106647474805d;
        double d16 = 0.0d;
        if (aVar3 != null) {
            double dA = aVar3.a();
            double dA2 = (aVar3.a() - aVar3.b()) / aVar3.a();
            double d17 = 1.0d / dA2;
            long j9 = dA <= 0.0d ? 128L : 0L;
            if (d17 < 250.0d || d17 > 350.0d) {
                j9 |= 256;
            }
            if (j9 == 0) {
                d14 = dA;
                d15 = dA2;
            }
        }
        double d18 = aVar.f17762i;
        double d19 = aVar2.f17762i;
        long j10 = (d18 < -1.43116998663535d || d18 > 1.5009831567151233d) ? 1L : 0L;
        if (d19 < -3.141592653589793d || d19 > 6.283185307179586d) {
            j10 |= 2;
        }
        long j11 = j10;
        if (j11 != 0) {
            i7 = 0;
            j7 = 0;
            str = null;
            d8 = d16;
            d9 = d8;
        } else {
            if (d19 < 0.0d) {
                d19 += 6.283185307279586d;
            }
            long j12 = (long) ((d18 * 180.0d) / 3.141592653589793d);
            double d20 = (180.0d * d19) / 3.141592653589793d;
            long j13 = (long) d20;
            long j14 = (long) (d19 < 3.141592653589793d ? (d20 / 6.0d) + 31.0d : (d20 / 6.0d) - 29.0d);
            long j15 = j14 <= 60 ? j14 : 1L;
            if (j12 > 55 && j12 < 64 && j13 > -1 && j13 < 3) {
                j15 = 31;
            }
            if (j12 > 55 && j12 < 64 && j13 > 2 && j13 < 12) {
                j15 = 32;
            }
            if (j12 > 71 && j13 > -1 && j13 < 9) {
                j15 = 31;
            }
            if (j12 > 71 && j13 > 8 && j13 < 21) {
                j15 = 33;
            }
            if (j12 > 71 && j13 > 20 && j13 < 33) {
                j15 = 35;
            }
            if (j12 > 71 && j13 > 32 && j13 < 42) {
                j15 = 37;
            }
            long j16 = j15;
            if (j11 == 0) {
                long j17 = 6 * j16;
                d16 = ((j16 >= 31 ? j17 - 183 : j17 + 177) * 3.141592653589793d) / 180.0d;
                int i8 = (int) j16;
                if (d18 < 0.0d) {
                    str2 = "gov.nasa.worldwdind.avkey.South";
                    d10 = 1.0E7d;
                } else {
                    str2 = "gov.nasa.worldwind.avkey.North";
                    d10 = 0.0d;
                }
                try {
                    s5.a aVarD = s5.a.d(d18);
                    s5.a aVarD2 = s5.a.d(d19);
                    Double dValueOf = Double.valueOf(d14);
                    d11 = 0.0d;
                    try {
                        c cVarA = c.a(aVarD, aVarD2, aVar3, dValueOf, Double.valueOf(d15), s5.a.d(0.0d), s5.a.d(d16), 500000.0d, d10, 0.9996d);
                        d13 = cVarA.f18087a;
                        try {
                            d12 = cVarA.f18088b;
                            if (d13 < 100000.0d || d13 > 900000.0d) {
                                j11 = 4;
                            }
                            if (d12 < 0.0d || d12 > 1.0E7d) {
                                j8 = j11 | 8;
                                j11 = j8;
                            }
                        } catch (Exception unused) {
                            d12 = 0.0d;
                            j8 = 512;
                        }
                    } catch (Exception unused2) {
                        d12 = d11;
                        d13 = d12;
                        j8 = 512;
                        j11 = j8;
                        j7 = 0;
                        d8 = d13;
                        i7 = i8;
                        str = str2;
                        d9 = d12;
                        if (j11 == j7) {
                            return new f(aVar, aVar2, i7, str, d8, d9, s5.a.d(d16));
                        }
                        String strA = v5.a.a("Coord.UTMConversionError");
                        v5.a.d().severe(strA);
                        throw new IllegalArgumentException(strA);
                    }
                } catch (Exception unused3) {
                    d11 = 0.0d;
                }
                j7 = 0;
                d8 = d13;
                i7 = i8;
                str = str2;
                d9 = d12;
            } else {
                d16 = 0.0d;
                i7 = 0;
                j7 = 0;
                str = null;
                d8 = d16;
                d9 = d8;
            }
        }
        if (j11 == j7) {
            return new f(aVar, aVar2, i7, str, d8, d9, s5.a.d(d16));
        }
        String strA2 = v5.a.a("Coord.UTMConversionError");
        v5.a.d().severe(strA2);
        throw new IllegalArgumentException(strA2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f18108b);
        sb.append(" ");
        sb.append("gov.nasa.worldwind.avkey.North".equals(this.f18107a) ? "N" : "S");
        sb.append(" ");
        sb.append(this.f18109c);
        sb.append("E");
        sb.append(" ");
        sb.append(this.f18110d);
        sb.append("N");
        return sb.toString();
    }
}
