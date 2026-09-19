package o5;

import java.text.NumberFormat;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public NumberFormat[] f17120a = new NumberFormat[2];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f f17121b;

    public String a(double d8, boolean z) {
        NumberFormat[] numberFormatArr = this.f17120a;
        if (numberFormatArr[z ? 1 : 0] == null) {
            numberFormatArr[z ? 1 : 0] = NumberFormat.getNumberInstance();
            f fVar = this.f17121b;
            double dA = z ? fVar.a(false) : fVar.b(false);
            f fVar2 = this.f17121b;
            double dC = dA - (z ? fVar2.c(false) : fVar2.d(false));
            if (dC < 0.1d) {
                this.f17120a[z ? 1 : 0].setMaximumFractionDigits(6);
            } else if (dC < 1.0d) {
                this.f17120a[z ? 1 : 0].setMaximumFractionDigits(4);
            } else if (dC < 20.0d) {
                this.f17120a[z ? 1 : 0].setMaximumFractionDigits(3);
            } else if (dC < 100.0d) {
                this.f17120a[z ? 1 : 0].setMaximumFractionDigits(1);
            } else {
                this.f17120a[z ? 1 : 0].setMaximumFractionDigits(0);
            }
        }
        return this.f17120a[z ? 1 : 0].format(d8);
    }
}
