package d7;

import java.io.InputStream;
import java.util.Objects;
import l3.vi2;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends c7.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4652c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c7.d[] f4653d;

    public a(c7.e eVar, int i7, InputStream inputStream, z6.b bVar) throws vi2 {
        super(eVar, i7);
        e7.a.c(inputStream);
        e7.a.c(inputStream);
        e7.a.c(inputStream);
        e7.a.c(inputStream);
        this.f4652c = e7.a.d(inputStream);
        Objects.requireNonNull(bVar);
        int i8 = this.f4652c;
        if (i8 <= bVar.f19032b) {
            this.f4653d = new c7.d[i8];
            for (int i9 = 0; i9 < this.f4652c; i9++) {
                this.f4653d[i9] = new c7.d(e7.a.c(inputStream), e7.a.c(inputStream));
            }
            return;
        }
        StringBuilder sbA = android.support.v4.media.b.a("Invalid ");
        sbA.append(a());
        sbA.append(" shape number of points. ");
        sbA.append("The allowed maximum number of points was ");
        sbA.append(bVar.f19032b);
        sbA.append(" but found ");
        sbA.append(this.f4652c);
        sbA.append(". ");
        sbA.append(c7.b.f3503a);
        throw new vi2(sbA.toString());
    }

    public abstract String a();
}
