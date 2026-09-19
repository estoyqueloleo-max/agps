package d7;

import androidx.appcompat.widget.d0;
import java.io.InputStream;
import l3.vi2;

/* JADX INFO: loaded from: classes.dex */
public class h extends a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double[] f4668e;

    public h(c7.e eVar, int i7, InputStream inputStream, z6.b bVar) throws vi2 {
        super(eVar, i7, inputStream, bVar);
        int iA = d0.a(this.f4652c, 24, 2, 28);
        if (eVar.f3507b == iA) {
            e7.a.c(inputStream);
            e7.a.c(inputStream);
            this.f4668e = new double[this.f4652c];
            for (int i8 = 0; i8 < this.f4652c; i8++) {
                this.f4668e[i8] = e7.a.c(inputStream);
            }
            return;
        }
        throw new vi2("Invalid MultiPointM shape header's content length. Expected " + iA + " 16-bit words (for " + this.f4652c + " points) but found " + this.f3501a.f3507b + ". " + c7.b.f3503a);
    }

    @Override // d7.a
    public String a() {
        return "MultiPointM";
    }
}
