package b7;

import c7.f;
import java.io.EOFException;
import java.io.InputStream;
import l3.vi2;
import z6.b;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2815a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2816b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2817c;

    public a(InputStream inputStream, b bVar) throws vi2 {
        try {
            int iA = e7.a.a(inputStream);
            this.f2815a = iA;
            if (iA != 9994) {
                throw new vi2("Invalid shape file code. Found " + this.f2815a + " but expected 9994.");
            }
            e7.a.a(inputStream);
            e7.a.a(inputStream);
            e7.a.a(inputStream);
            e7.a.a(inputStream);
            e7.a.a(inputStream);
            e7.a.a(inputStream);
            int iD = e7.a.d(inputStream);
            this.f2816b = iD;
            if (iD != 1000) {
                throw new vi2("Invalid shape file version. Found " + this.f2816b + " but expected 1000.");
            }
            int iD2 = e7.a.d(inputStream);
            int iA2 = f.a(iD2);
            this.f2817c = iA2;
            if (iA2 == 0) {
                throw new vi2("Invalid shape file. The header's shape type has the invalid code " + iD2 + ".");
            }
            e7.a.c(inputStream);
            e7.a.c(inputStream);
            e7.a.c(inputStream);
            e7.a.c(inputStream);
            e7.a.c(inputStream);
            e7.a.c(inputStream);
            e7.a.c(inputStream);
            e7.a.c(inputStream);
        } catch (Exception unused) {
            if (unused instanceof vi2) {
                throw (vi2) unused;
            }
            throw new vi2("Unexpected end of stream. The content is too short. It doesn't even have a complete header.");
        }
    }
}
