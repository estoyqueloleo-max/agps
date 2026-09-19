package d7;

import java.io.InputStream;
import java.util.Objects;
import l3.vi2;

/* JADX INFO: loaded from: classes.dex */
public class m extends b {
    public m(c7.e eVar, int i7, InputStream inputStream, z6.b bVar) throws vi2 {
        super(eVar, i7, inputStream, bVar);
        Objects.requireNonNull(bVar);
        if (this.f3501a.f3507b == 10) {
            return;
        }
        StringBuilder sbA = android.support.v4.media.b.a("Invalid Point shape header's content length. Expected 10 16-bit words but found ");
        sbA.append(this.f3501a.f3507b);
        sbA.append(". ");
        sbA.append(c7.b.f3503a);
        throw new vi2(sbA.toString());
    }
}
