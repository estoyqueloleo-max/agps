package d7;

import androidx.appcompat.widget.d0;
import java.io.InputStream;
import l3.vi2;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends e {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public double[] f4659g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public double[] f4660h;

    public f(c7.e eVar, int i7, InputStream inputStream, z6.b bVar) throws vi2 {
        super(eVar, i7, inputStream, bVar);
        int iA = d0.a(this.f4656d, 32, 2, d0.a(this.f4655c, 4, 2, 38));
        if (eVar.f3507b == iA) {
            e7.a.c(inputStream);
            e7.a.c(inputStream);
            this.f4659g = new double[this.f4656d];
            for (int i8 = 0; i8 < this.f4656d; i8++) {
                this.f4659g[i8] = e7.a.c(inputStream);
            }
            e7.a.c(inputStream);
            e7.a.c(inputStream);
            this.f4660h = new double[this.f4656d];
            for (int i9 = 0; i9 < this.f4656d; i9++) {
                this.f4660h[i9] = e7.a.c(inputStream);
            }
            return;
        }
        StringBuilder sbA = android.support.v4.media.b.a("Invalid ");
        sbA.append(b());
        sbA.append(" shape header's content length. ");
        sbA.append("Expected ");
        sbA.append(iA);
        sbA.append(" 16-bit words (for ");
        sbA.append(this.f4655c);
        sbA.append(" parts and ");
        sbA.append(this.f4656d);
        sbA.append(" points)");
        sbA.append(" but found ");
        sbA.append(this.f3501a.f3507b);
        sbA.append(". ");
        sbA.append(c7.b.f3503a);
        throw new vi2(sbA.toString());
    }
}
