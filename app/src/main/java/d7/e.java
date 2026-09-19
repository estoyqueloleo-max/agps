package d7;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
import l3.vi2;

/* JADX INFO: loaded from: classes.dex */
public abstract class e extends c7.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4655c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4656d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f4657e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c7.d[] f4658f;

    public e(c7.e eVar, int i7, InputStream inputStream, z6.b bVar) throws vi2 {
        super(eVar, i7);
        e7.a.c(inputStream);
        e7.a.c(inputStream);
        e7.a.c(inputStream);
        e7.a.c(inputStream);
        int iD = e7.a.d(inputStream);
        this.f4655c = iD;
        if (iD < 0) {
            StringBuilder sbA = android.support.v4.media.b.a("Invalid ");
            sbA.append(b());
            sbA.append(" shape number of parts. ");
            sbA.append("It should be a number greater than zero, but found ");
            sbA.append(this.f4655c);
            sbA.append(". ");
            sbA.append(c7.b.f3503a);
            throw new vi2(sbA.toString());
        }
        int iD2 = e7.a.d(inputStream);
        this.f4656d = iD2;
        if (iD2 < 0) {
            StringBuilder sbA2 = android.support.v4.media.b.a("Invalid ");
            sbA2.append(b());
            sbA2.append(" shape number of points. ");
            sbA2.append("It should be a number greater than zero, but found ");
            sbA2.append(this.f4656d);
            sbA2.append(". ");
            sbA2.append(c7.b.f3503a);
            throw new vi2(sbA2.toString());
        }
        if (this.f4655c > iD2) {
            StringBuilder sbA3 = android.support.v4.media.b.a("Invalid ");
            sbA3.append(b());
            sbA3.append(" shape number of parts. ");
            sbA3.append("It should be smaller or equal to the number of points (");
            sbA3.append(this.f4656d);
            sbA3.append("), but found ");
            sbA3.append(this.f4655c);
            sbA3.append(". ");
            sbA3.append(c7.b.f3503a);
            throw new vi2(sbA3.toString());
        }
        Objects.requireNonNull(bVar);
        if (this.f4656d > bVar.f19032b) {
            StringBuilder sbA4 = android.support.v4.media.b.a("Invalid ");
            sbA4.append(b());
            sbA4.append(" shape number of points. ");
            sbA4.append("The allowed maximum number of points was ");
            sbA4.append(bVar.f19032b);
            sbA4.append(" but found ");
            sbA4.append(this.f4656d);
            sbA4.append(". ");
            sbA4.append(c7.b.f3503a);
            throw new vi2(sbA4.toString());
        }
        this.f4657e = new int[this.f4655c];
        for (int i8 = 0; i8 < this.f4655c; i8++) {
            this.f4657e[i8] = e7.a.d(inputStream);
        }
        this.f4658f = new c7.d[this.f4656d];
        for (int i9 = 0; i9 < this.f4656d; i9++) {
            this.f4658f[i9] = new c7.d(e7.a.c(inputStream), e7.a.c(inputStream));
        }
    }

    public c7.d[] a(int i7) {
        int i8;
        if (i7 < 0 || i7 >= (i8 = this.f4655c)) {
            throw new RuntimeException("Invalid part " + i7 + ". Available parts [0:" + this.f4655c + "].");
        }
        int[] iArr = this.f4657e;
        int i9 = iArr[i7];
        int length = i7 < i8 + (-1) ? iArr[i7 + 1] : this.f4658f.length;
        if (i9 >= 0) {
            c7.d[] dVarArr = this.f4658f;
            if (i9 <= dVarArr.length) {
                if (length >= 0 && length <= dVarArr.length) {
                    return (c7.d[]) Arrays.copyOfRange(dVarArr, i9, length);
                }
                throw new RuntimeException("Malformed content. Part end (" + length + ") is out of range. Valid range of points is [0:" + this.f4658f.length + "].");
            }
        }
        throw new RuntimeException("Malformed content. Part start (" + i9 + ") is out of range. Valid range of points is [0:" + this.f4658f.length + "].");
    }

    public abstract String b();
}
