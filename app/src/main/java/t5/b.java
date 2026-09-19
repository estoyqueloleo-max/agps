package t5;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f18074a = 6378137.0d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double f18075b = 0.08181919084262188d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double f18076c = 0.040909595421311d;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public double f18077d = 0.0d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f18078e = 1.0d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public double f18079f = 1.0033565552493d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public double f18080g = 6378137.0d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public double f18081h = 1.2756274E7d;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public double f18082i = 1.5707963267948966d;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public double f18083j = 0.0d;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public double f18084k = 0.0d;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public double f18085l = 0.0d;
    public double m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public double f18086n;

    public long a(double d8, double d9) {
        double d10;
        double d11;
        double d12;
        double d13;
        long j7 = (d8 < -1.5707963267948966d || d8 > 1.5707963267948966d) ? 1L : 0L;
        if (d8 < 0.0d && this.f18077d == 0.0d) {
            j7 |= 1;
        }
        if (d8 > 0.0d && this.f18077d == 1.0d) {
            j7 |= 1;
        }
        if (d9 < -3.141592653589793d || d9 > 6.283185307179586d) {
            j7 |= 2;
        }
        if (j7 == 0) {
            if (Math.abs(Math.abs(d8) - 1.5707963267948966d) < 1.0E-10d) {
                this.m = 0.0d;
                this.f18086n = 0.0d;
            } else {
                if (this.f18077d != 0.0d) {
                    d11 = d9 * (-1.0d);
                    d10 = (-1.0d) * d8;
                } else {
                    d10 = d8;
                    d11 = d9;
                }
                double d14 = d11 - this.f18083j;
                if (d14 > 3.141592653589793d) {
                    d14 -= 6.283185307179586d;
                }
                if (d14 < -3.141592653589793d) {
                    d14 += 6.283185307179586d;
                }
                double dSin = this.f18075b * Math.sin(d10);
                double dTan = Math.tan(0.7853981633974483d - (d10 / 2.0d)) / Math.pow((1.0d - dSin) / (dSin + 1.0d), this.f18076c);
                if (Math.abs(Math.abs(this.f18082i) - 1.5707963267948966d) > 1.0E-10d) {
                    d12 = this.f18080g * dTan;
                    d13 = this.f18078e;
                } else {
                    d12 = this.f18081h * dTan;
                    d13 = this.f18079f;
                }
                double d15 = d12 / d13;
                if (this.f18077d != 0.0d) {
                    this.m = -((Math.sin(d14) * d15) - this.f18084k);
                    this.f18086n = (Math.cos(d14) * d15) + this.f18085l;
                } else {
                    this.m = (Math.sin(d14) * d15) + this.f18084k;
                }
                this.f18086n = (Math.cos(d14) * (-d15)) + this.f18085l;
            }
        }
        return j7;
    }
}
