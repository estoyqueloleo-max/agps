package d7;

import androidx.appcompat.widget.d0;
import java.io.InputStream;
import java.util.Objects;
import l3.vi2;

/* JADX INFO: loaded from: classes.dex */
public class g extends c7.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4661c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4662d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f4663e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f4664f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c7.d[] f4665g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public double[] f4666h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public double[] f4667i;

    public g(c7.e eVar, int i7, InputStream inputStream, z6.b bVar) throws vi2 {
        super(eVar, i7);
        int i8;
        int i9;
        Objects.requireNonNull(bVar);
        int iA = d0.a(this.f4662d, 32, 2, d0.a(this.f4661c, 8, 2, 38));
        if (this.f3501a.f3507b != iA) {
            throw new vi2("Invalid MultiPatch shape header's content length. Expected " + iA + " 16-bit words (for " + this.f4661c + " parts and " + this.f4662d + " points) but found " + this.f3501a.f3507b + ". " + c7.b.f3503a);
        }
        e7.a.c(inputStream);
        e7.a.c(inputStream);
        e7.a.c(inputStream);
        e7.a.c(inputStream);
        int iD = e7.a.d(inputStream);
        this.f4661c = iD;
        if (iD < 0) {
            throw new vi2("Invalid MultiPatch shape number of parts. It should be a number greater than zero, but found " + this.f4661c + ". " + c7.b.f3503a);
        }
        int iD2 = e7.a.d(inputStream);
        this.f4662d = iD2;
        if (iD2 < 0) {
            throw new vi2("Invalid MultiPatch shape number of points. It should be a number greater than zero, but found " + this.f4662d + ". " + c7.b.f3503a);
        }
        int i10 = this.f4661c;
        if (i10 > iD2) {
            throw new vi2("Invalid MultiPatch shape number of parts. It should be smaller or equal to the number of points (" + this.f4662d + "), but found " + this.f4661c + ". " + c7.b.f3503a);
        }
        if (iD2 > bVar.f19032b) {
            throw new vi2("Invalid MultiPatch shape number of points. The allowed maximum number of points was " + bVar.f19032b + " but found " + this.f4662d + ". " + c7.b.f3503a);
        }
        this.f4663e = new int[i10];
        int i11 = 0;
        while (true) {
            i8 = this.f4661c;
            if (i11 >= i8) {
                break;
            }
            this.f4663e[i11] = e7.a.d(inputStream);
            i11++;
        }
        this.f4664f = new int[i8];
        for (int i12 = 0; i12 < this.f4661c; i12++) {
            int iD3 = e7.a.d(inputStream);
            int[] iArrA = c7.c.a();
            int length = iArrA.length;
            int i13 = 0;
            while (true) {
                if (i13 >= length) {
                    i9 = 0;
                    break;
                }
                i9 = iArrA[i13];
                if ((i9 - 1) == iD3) {
                    break;
                } else {
                    i13++;
                }
            }
            if (i9 == 0) {
                throw new vi2("Invalid MultiPatch shape part type. Part type code found was " + iD3 + ". " + c7.b.f3503a);
            }
            this.f4664f[i12] = i9;
        }
        this.f4665g = new c7.d[this.f4662d];
        for (int i14 = 0; i14 < this.f4662d; i14++) {
            this.f4665g[i14] = new c7.d(e7.a.c(inputStream), e7.a.c(inputStream));
        }
        e7.a.c(inputStream);
        e7.a.c(inputStream);
        this.f4666h = new double[this.f4662d];
        for (int i15 = 0; i15 < this.f4662d; i15++) {
            this.f4666h[i15] = e7.a.c(inputStream);
        }
        e7.a.c(inputStream);
        e7.a.c(inputStream);
        this.f4667i = new double[this.f4662d];
        for (int i16 = 0; i16 < this.f4662d; i16++) {
            this.f4667i[i16] = e7.a.c(inputStream);
        }
    }
}
