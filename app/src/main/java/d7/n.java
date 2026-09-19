package d7;

import java.io.InputStream;
import java.util.Objects;
import l3.vi2;

/* JADX INFO: loaded from: classes.dex */
public class n extends b {
    public n(c7.e eVar, int i7, InputStream inputStream, z6.b bVar) throws vi2 {
        super(eVar, i7, inputStream, bVar);
        Objects.requireNonNull(bVar);
        if (this.f3501a.f3507b == 18) {
            e7.a.c(inputStream);
            e7.a.c(inputStream);
        } else {
            StringBuilder sbA = android.support.v4.media.b.a("Invalid PointZ shape header's content length. Expected 18 16-bit words but found ");
            sbA.append(this.f3501a.f3507b);
            sbA.append(". ");
            sbA.append(c7.b.f3503a);
            throw new vi2(sbA.toString());
        }
    }
}
