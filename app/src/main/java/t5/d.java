package t5;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f18089a = 6378137.0d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double f18090b = 0.0033528106647474805d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double f18091c = 0.00669437999014138d;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public double f18092d = 0.0067394967565869d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f18093e = 0.0d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public double f18094f = 0.0d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public double f18095g = 0.0d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public double f18096h = 0.0d;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public double f18097i = 1.0d;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public double f18098j = 6367449.1458008d;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public double f18099k = 16038.508696861d;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public double f18100l = 16.832613334334d;
    public double m = 0.021984404273757d;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public double f18101n = 3.1148371319283E-5d;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public double f18102o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public double f18103p;

    public long a(double d8, double d9) {
        long j7 = (d8 < -1.570621793869697d || d8 > 1.570621793869697d) ? 1L : 0L;
        double d10 = d9 > 3.141592653589793d ? d9 - 6.283185307179586d : d9;
        double d11 = this.f18094f;
        if (d10 < d11 - 1.5707963267948966d || d10 > d11 + 1.5707963267948966d) {
            double d12 = d10 < 0.0d ? d10 + 6.283185307179586d : d10;
            double d13 = d11 < 0.0d ? d11 + 6.283185307179586d : d11;
            if (d12 < d13 - 1.5707963267948966d || d12 > d13 + 1.5707963267948966d) {
                j7 |= 2;
            }
        }
        if (j7 != 0) {
            return j7;
        }
        double d14 = d10 - d11;
        if (Math.abs(d14) > 0.15707963267948966d) {
            j7 = 512 | j7;
        }
        if (d14 > 3.141592653589793d) {
            d14 -= 6.283185307179586d;
        }
        if (d14 < -3.141592653589793d) {
            d14 += 6.283185307179586d;
        }
        if (Math.abs(d14) < 2.0E-10d) {
            d14 = 0.0d;
        }
        double dSin = Math.sin(d8);
        double dCos = Math.cos(d8);
        double d15 = dCos * dCos;
        double d16 = d15 * dCos;
        double d17 = d16 * d15;
        double d18 = d17 * d15;
        double dTan = Math.tan(d8);
        double d19 = dTan * dTan;
        double d20 = d19 * dTan * dTan;
        double d21 = d20 * dTan * dTan;
        long j8 = j7;
        double d22 = this.f18092d * d15;
        double d23 = d22 * d22;
        double d24 = d23 * d22;
        double d25 = d24 * d22;
        double d26 = d14;
        double dSqrt = this.f18089a / Math.sqrt(1.0d - (Math.pow(Math.sin(d8), 2.0d) * this.f18091c));
        double dSin2 = (Math.sin(d8 * 8.0d) * this.f18101n) + (((Math.sin(d8 * 4.0d) * this.f18100l) + ((this.f18098j * d8) - (Math.sin(d8 * 2.0d) * this.f18099k))) - (Math.sin(d8 * 6.0d) * this.m));
        double d27 = this.f18098j;
        double d28 = this.f18093e;
        double dSin3 = dSin2 - ((Math.sin(this.f18093e * 8.0d) * this.f18101n) + (((Math.sin(this.f18093e * 4.0d) * this.f18100l) + ((d27 * d28) - (Math.sin(d28 * 2.0d) * this.f18099k))) - (Math.sin(this.f18093e * 6.0d) * this.m)));
        double d29 = this.f18097i;
        double d30 = dSin * dSqrt;
        double d31 = (((d23 * 4.0d) + ((9.0d * d22) + (5.0d - d19))) * ((d30 * d16) * d29)) / 24.0d;
        double d32 = 58.0d * d19;
        double d33 = (((((88.0d * d25) + (((324.0d * d24) + ((445.0d * d23) + (((270.0d * d22) + ((61.0d - d32) + d20)) - ((330.0d * d19) * d22)))) - ((680.0d * d19) * d23))) - ((600.0d * d19) * d24)) - ((192.0d * d19) * d25)) * ((d30 * d17) * d29)) / 720.0d;
        double d34 = ((((543.0d * d20) + (1385.0d - (3111.0d * d19))) - d21) * ((d30 * d18) * d29)) / 40320.0d;
        this.f18103p = (Math.pow(d26, 8.0d) * d34) + (Math.pow(d26, 6.0d) * d33) + (Math.pow(d26, 4.0d) * d31) + (Math.pow(d26, 2.0d) * (((d30 * dCos) * d29) / 2.0d)) + this.f18095g + (dSin3 * d29);
        double d35 = this.f18097i;
        double d36 = (((((4.0d * d24) + ((13.0d * d23) + (((14.0d * d22) + ((5.0d - (18.0d * d19)) + d20)) - (d32 * d22)))) - ((64.0d * d19) * d23)) - ((24.0d * d19) * d24)) * ((dSqrt * d17) * d35)) / 120.0d;
        this.f18102o = (Math.pow(d26, 7.0d) * (((((d20 * 179.0d) + (61.0d - (d19 * 479.0d))) - d21) * ((dSqrt * d18) * d35)) / 5040.0d)) + (Math.pow(d26, 5.0d) * d36) + (Math.pow(d26, 3.0d) * ((((1.0d - d19) + d22) * ((dSqrt * d16) * d35)) / 6.0d)) + (d26 * dCos * dSqrt * d35) + this.f18096h;
        return j8;
    }
}
