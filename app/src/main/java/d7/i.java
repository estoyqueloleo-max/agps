package d7;

import androidx.appcompat.widget.d0;
import java.io.InputStream;
import l3.vi2;

/* JADX INFO: loaded from: classes.dex */
public class i extends a {
    public i(c7.e eVar, int i7, InputStream inputStream, z6.b bVar) throws vi2 {
        super(eVar, i7, inputStream, bVar);
        int iA = d0.a(this.f4652c, 16, 2, 20);
        if (eVar.f3507b == iA) {
            return;
        }
        throw new vi2("Invalid MultiPoint shape header's content length. Expected " + iA + " 16-bit words (for " + this.f4652c + " points) but found " + this.f3501a.f3507b + ". " + c7.b.f3503a);
    }

    @Override // d7.a
    public String a() {
        return "MultiPoint";
    }
}
