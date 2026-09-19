package t5;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final long[][] f18066g = {new long[]{0, 9, 25, 25, 800000, 800000}, new long[]{1, 0, 17, 25, 2000000, 800000}, new long[]{24, 9, 25, 15, 800000, 1300000}, new long[]{25, 0, 9, 15, 2000000, 1300000}};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final double[][] f18067h = {new double[]{2.0d, 1100000.0d, -72.0d, -80.5d, 0.0d}, new double[]{3.0d, 2000000.0d, -64.0d, -72.0d, 2000000.0d}, new double[]{4.0d, 2800000.0d, -56.0d, -64.0d, 2000000.0d}, new double[]{5.0d, 3700000.0d, -48.0d, -56.0d, 2000000.0d}, new double[]{6.0d, 4600000.0d, -40.0d, -48.0d, 4000000.0d}, new double[]{7.0d, 5500000.0d, -32.0d, -40.0d, 4000000.0d}, new double[]{9.0d, 6400000.0d, -24.0d, -32.0d, 6000000.0d}, new double[]{10.0d, 7300000.0d, -16.0d, -24.0d, 6000000.0d}, new double[]{11.0d, 8200000.0d, -8.0d, -16.0d, 8000000.0d}, new double[]{12.0d, 9100000.0d, 0.0d, -8.0d, 8000000.0d}, new double[]{13.0d, 0.0d, 8.0d, 0.0d, 0.0d}, new double[]{15.0d, 800000.0d, 16.0d, 8.0d, 0.0d}, new double[]{16.0d, 1700000.0d, 24.0d, 16.0d, 0.0d}, new double[]{17.0d, 2600000.0d, 32.0d, 24.0d, 2000000.0d}, new double[]{18.0d, 3500000.0d, 40.0d, 32.0d, 2000000.0d}, new double[]{19.0d, 4400000.0d, 48.0d, 40.0d, 4000000.0d}, new double[]{20.0d, 5300000.0d, 56.0d, 48.0d, 4000000.0d}, new double[]{21.0d, 6200000.0d, 64.0d, 56.0d, 6000000.0d}, new double[]{22.0d, 7000000.0d, 72.0d, 64.0d, 6000000.0d}, new double[]{23.0d, 7900000.0d, 84.5d, 72.0d, 6000000.0d}};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f18068a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public u5.a f18069b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f18070c = XmlPullParser.NO_NAMESPACE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f18071d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f18072e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f18073f;

    public a(u5.a aVar) {
        this.f18068a = "WE";
        this.f18069b = aVar;
        if (aVar != null) {
            double dA = aVar.a();
            double dA2 = (aVar.a() - aVar.b()) / aVar.a();
            String str = this.f18068a;
            if (dA > 0.0d && dA2 != 0.0d) {
                double d8 = 1.0d / dA2;
                if (d8 < 250.0d || d8 > 350.0d) {
                    return;
                }
                this.f18068a = str;
            }
        }
    }

    public final long a(String str, Double d8, Double d9, long j7) {
        int i7;
        double d10;
        double d11;
        long j8;
        long[] jArr = new long[3];
        long j9 = ("gov.nasa.worldwind.avkey.North".equals(str) || "gov.nasa.worldwdind.avkey.South".equals(str)) ? 0L : 512L;
        if (d8.doubleValue() < 0.0d || d8.doubleValue() > 4000000.0d) {
            j9 |= 64;
        }
        if (d9.doubleValue() < 0.0d || d9.doubleValue() > 4000000.0d) {
            j9 |= 128;
        }
        if (j7 < 0 || j7 > 5) {
            j9 |= 8;
        }
        long j10 = j9;
        if (j10 == 0) {
            double dPow = Math.pow(10.0d, 5 - j7);
            Double dValueOf = Double.valueOf(d(d8.doubleValue() / dPow) * dPow);
            Double dValueOf2 = Double.valueOf(d(d9.doubleValue() / dPow) * dPow);
            if ("gov.nasa.worldwind.avkey.North".equals(str)) {
                if (dValueOf.doubleValue() >= 2000000.0d) {
                    jArr[0] = 25;
                } else {
                    jArr[0] = 24;
                }
                int i8 = ((int) jArr[0]) - 22;
                long[][] jArr2 = f18066g;
                i7 = (int) jArr2[i8][1];
                d10 = jArr2[i8][4];
                d11 = jArr2[i8][5];
            } else {
                if (dValueOf.doubleValue() >= 2000000.0d) {
                    jArr[0] = 1;
                } else {
                    jArr[0] = 0;
                }
                long[][] jArr3 = f18066g;
                i7 = (int) jArr3[(int) jArr[0]][1];
                d10 = jArr3[(int) jArr[0]][4];
                d11 = jArr3[(int) jArr[0]][5];
            }
            jArr[2] = (int) ((dValueOf2.doubleValue() - d11) / 100000.0d);
            if (jArr[2] > 7) {
                j8 = 1;
                jArr[2] = jArr[2] + 1;
            } else {
                j8 = 1;
            }
            if (jArr[2] > 13) {
                jArr[2] = jArr[2] + j8;
            }
            jArr[1] = i7 + ((int) ((dValueOf.doubleValue() - d10) / 100000.0d));
            if (dValueOf.doubleValue() < 2000000.0d) {
                if (jArr[1] > 11) {
                    jArr[1] = jArr[1] + 3;
                }
                if (jArr[1] > 20) {
                    jArr[1] = jArr[1] + 2;
                }
            } else {
                if (jArr[1] > 2) {
                    jArr[1] = jArr[1] + 2;
                }
                if (jArr[1] > 7) {
                    jArr[1] = jArr[1] + 1;
                }
                if (jArr[1] > 11) {
                    jArr[1] = jArr[1] + 3;
                }
            }
            c(0L, jArr, dValueOf.doubleValue(), dValueOf2.doubleValue(), j7);
        }
        return j10;
    }

    /* JADX WARN: Code duplicated, block: B:57:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:59:0x0107  */
    /* JADX WARN: Code duplicated, block: B:60:0x010a  */
    /* JADX WARN: Code duplicated, block: B:63:0x0115 A[LOOP:0: B:61:0x010c->B:63:0x0115, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:66:0x0120  */
    /* JADX WARN: Code duplicated, block: B:69:0x0135  */
    /* JADX WARN: Code duplicated, block: B:72:0x0142  */
    /* JADX WARN: Code duplicated, block: B:80:0x0163  */
    public final long b(long j7, double d8, double d9, double d10, long j8) {
        long j9 = 0;
        double d11 = 0.0d;
        long j10 = 0;
        long j11 = 0;
        double d12 = 0.0d;
        double d13 = 0.0d;
        double d14 = 0.0d;
        long j12 = 0;
        long j13 = 0;
        long[] jArr = new long[3];
        double dPow = Math.pow(10.0d, 5 - j8);
        double d15 = d(d9 / dPow) * dPow;
        double d16 = d(d10 / dPow) * dPow;
        long j14 = j7 % 6;
        if (j14 == 0) {
            j14 = 6;
        }
        long j15 = (this.f18068a.compareTo("CC") == 0 || this.f18068a.compareTo("CD") == 0 || this.f18068a.compareTo("BR") == 0 || this.f18068a.compareTo("BN") == 0) ? 0L : 1L;
        if (j14 == 1 || j14 == 4) {
            j9 = 0;
            this.f18071d = 0L;
        } else {
            if (j14 == 2 || j14 == 5) {
                j13 = 9;
            } else {
                if (j14 == 3 || j14 == 6) {
                    j13 = 18;
                }
                j9 = 0;
            }
            this.f18071d = j13;
            j9 = 0;
        }
        long j16 = j14 % 2;
        if (j15 == 1) {
            d11 = j16 == j9 ? 500000.0d : 0.0d;
        } else {
            d11 = j16 == j9 ? 1500000.0d : 1000000.0d;
        }
        this.f18072e = d11;
        double d17 = 57.29577951308232d * d8;
        if (d17 < 72.0d || d17 >= 84.5d) {
            if (d17 <= -80.5d || d17 >= 72.0d) {
                j10 = 1;
            } else {
                j11 = (long) f18067h[(int) (((d8 + 1.3962634015954636d) / 0.13962634015954636d) + 1.0E-12d)][0];
            }
            jArr[0] = this.f18073f;
            if (j10 == 0) {
                if (d16 == 1.0E7d) {
                    d12 = d16 - 1.0d;
                } else {
                    d12 = d16;
                }
                while (d12 >= 2000000.0d) {
                    d12 -= 2000000.0d;
                }
                d13 = d12 + this.f18072e;
                if (d13 >= 2000000.0d) {
                    d13 -= 2000000.0d;
                }
                jArr[2] = (long) (d13 / 100000.0d);
                if (jArr[2] > 7) {
                    jArr[2] = jArr[2] + 1;
                }
                if (jArr[2] > 13) {
                    jArr[2] = jArr[2] + 1;
                }
                if (jArr[0] != 21 && j7 == 31 && d15 == 500000.0d) {
                    d14 = d15 - 1.0d;
                } else {
                    d14 = d15;
                }
                j12 = this.f18071d;
                jArr[1] = (((long) (d14 / 100000.0d)) - 1) + j12;
                if (j12 == 9 && jArr[1] > 13) {
                    jArr[1] = jArr[1] + 1;
                }
                c(j7, jArr, d15, d16, j8);
            }
            return j10;
        }
        j11 = 23;
        this.f18073f = j11;
        j10 = 0;
        jArr[0] = this.f18073f;
        if (j10 == 0) {
            if (d16 == 1.0E7d) {
                d12 = d16 - 1.0d;
            } else {
                d12 = d16;
            }
            while (d12 >= 2000000.0d) {
                d12 -= 2000000.0d;
            }
            d13 = d12 + this.f18072e;
            if (d13 >= 2000000.0d) {
                d13 -= 2000000.0d;
            }
            jArr[2] = (long) (d13 / 100000.0d);
            if (jArr[2] > 7) {
                jArr[2] = jArr[2] + 1;
            }
            if (jArr[2] > 13) {
                jArr[2] = jArr[2] + 1;
            }
            if (jArr[0] != 21) {
                d14 = d15;
            } else {
                d14 = d15;
            }
            j12 = this.f18071d;
            jArr[1] = (((long) (d14 / 100000.0d)) - 1) + j12;
            if (j12 == 9) {
                jArr[1] = jArr[1] + 1;
            }
            c(j7, jArr, d15, d16, j8);
        }
        return j10;
    }

    public final long c(long j7, long[] jArr, double d8, double d9, long j8) {
        String strSubstring;
        this.f18070c = j7 != 0 ? String.format("%02d", Long.valueOf(j7)) : "  ";
        for (int i7 = 0; i7 < 3; i7++) {
            if (jArr[i7] < 0 || jArr[i7] > 26) {
                return 256L;
            }
            this.f18070c += "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int) jArr[i7]);
        }
        double dPow = Math.pow(10.0d, 5 - j8);
        double d10 = d8 % 100000.0d;
        if (d10 >= 99999.5d) {
            d10 = 99999.0d;
        }
        String string = Integer.valueOf((int) (d10 / dPow)).toString();
        if (string.length() > j8) {
            string = string.substring(0, ((int) j8) - 1);
        } else {
            int length = string.length();
            for (int i8 = 0; i8 < j8 - ((long) length); i8++) {
                string = ("0" + string);
            }
        }
        this.f18070c = v0.a.a(new StringBuilder(), this.f18070c, " ", string);
        double d11 = d9 % 100000.0d;
        String string2 = Integer.valueOf((int) ((d11 >= 99999.5d ? 99999.0d : d11) / dPow)).toString();
        if (string2.length() > j8) {
            strSubstring = string2.substring(0, ((int) j8) - 1);
        } else {
            int length2 = string2.length();
            for (int i9 = 0; i9 < j8 - ((long) length2); i9++) {
                string2 = ("0" + string2);
            }
            strSubstring = string2;
        }
        this.f18070c = v0.a.a(new StringBuilder(), this.f18070c, " ", strSubstring);
        return 0L;
    }

    public final double d(double d8) {
        double dFloor = Math.floor(d8);
        double d9 = d8 - dFloor;
        long j7 = (long) dFloor;
        if (d9 > 0.5d || (d9 == 0.5d && j7 % 2 == 1)) {
            j7++;
        }
        return j7;
    }
}
