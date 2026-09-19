package c7;

import c2.e0;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import l3.vi2;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3506a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3507b;

    public e(InputStream inputStream, z6.b bVar) throws vi2, a7.a {
        int iD;
        byte[] bArr = e7.a.f5090a;
        synchronized (e7.a.class) {
            try {
                if (inputStream.read(e7.a.f5090a, 0, 4) != 4) {
                    throw new a7.a();
                }
                iD = e0.d(e7.a.f5091b);
            } catch (IOException unused) {
                throw new a7.a();
            }
        }
        this.f3506a = iD;
        Objects.requireNonNull(bVar);
        if (this.f3506a == bVar.f19031a) {
            this.f3507b = e7.a.a(inputStream);
            return;
        }
        StringBuilder sbA = android.support.v4.media.b.a("Invalid record number. Expected ");
        sbA.append(bVar.f19031a);
        sbA.append(" but found ");
        sbA.append(this.f3506a);
        sbA.append(".");
        throw new vi2(sbA.toString());
    }
}
