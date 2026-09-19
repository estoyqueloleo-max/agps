package z6;

import c7.e;
import c7.f;
import d7.h;
import d7.i;
import d7.j;
import d7.k;
import d7.l;
import d7.m;
import d7.n;
import d7.o;
import d7.p;
import d7.q;
import d7.r;
import d7.s;
import d7.t;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.util.Objects;
import l3.vi2;
import r.g;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public BufferedInputStream f19027a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f19028b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b7.a f19029c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f19030d;

    public a(InputStream inputStream, b bVar) throws vi2 {
        if (inputStream == null) {
            throw new RuntimeException("Must specify a non-null input stream to read from.");
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        this.f19027a = bufferedInputStream;
        this.f19028b = bVar;
        this.f19030d = false;
        this.f19029c = new b7.a(bufferedInputStream, bVar);
    }

    public c7.a a() throws vi2 {
        if (this.f19030d) {
            return null;
        }
        b bVar = this.f19028b;
        bVar.f19031a++;
        try {
            e eVar = new e(this.f19027a, bVar);
            try {
                int iD = e7.a.d(this.f19027a);
                Objects.requireNonNull(this.f19028b);
                int iA = f.a(iD);
                if (iA == 0) {
                    throw new vi2("Invalid shape type '" + iD + "'. The shape type can be forced using the additional constructor with ValidationRules.");
                }
                Objects.requireNonNull(this.f19028b);
                if (!g.b(this.f19029c.f2817c, iA)) {
                    throw new vi2("Invalid shape type '" + f.e(iA) + "'. All included shapes must have the same type as the one specified on the file header (" + f.e(this.f19029c.f2817c) + "). This validation can be disabled using the additional constructor with ValidationRules.");
                }
                try {
                    switch (g.c(iA)) {
                        case 0:
                            return new k(eVar, iA, this.f19027a, this.f19028b);
                        case 1:
                            return new m(eVar, iA, this.f19027a, this.f19028b);
                        case 2:
                            return new s(eVar, iA, this.f19027a, this.f19028b);
                        case 3:
                            return new p(eVar, iA, this.f19027a, this.f19028b);
                        case 4:
                            return new i(eVar, iA, this.f19027a, this.f19028b);
                        case 5:
                            return new n(eVar, iA, this.f19027a, this.f19028b);
                        case 6:
                            return new t(eVar, iA, this.f19027a, this.f19028b);
                        case 7:
                            return new q(eVar, iA, this.f19027a, this.f19028b);
                        case 8:
                            return new j(eVar, iA, this.f19027a, this.f19028b);
                        case 9:
                            return new l(eVar, iA, this.f19027a, this.f19028b);
                        case 10:
                            return new r(eVar, iA, this.f19027a, this.f19028b);
                        case 11:
                            return new o(eVar, iA, this.f19027a, this.f19028b);
                        case 12:
                            return new h(eVar, iA, this.f19027a, this.f19028b);
                        case 13:
                            return new d7.g(eVar, iA, this.f19027a, this.f19028b);
                        default:
                            throw new vi2("Unexpected shape type '" + f.e(iA) + "'");
                    }
                } catch (Exception unused) {
                    if (unused instanceof vi2) {
                        throw (vi2) unused;
                    }
                    throw new vi2("Unexpected end of stream. The data is too short for the last shape (" + f.e(iA) + ") that was being read.");
                }
            } catch (Exception unused2) {
                if (unused2 instanceof vi2) {
                    throw (vi2) unused2;
                }
                throw new vi2("Unexpected end of stream. The data is too short for the shape that was being read.");
            }
        } catch (a7.a unused3) {
            this.f19030d = true;
            return null;
        }
    }
}
