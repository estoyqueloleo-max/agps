package g0;

import android.os.LocaleList;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f5277a;

    static {
        new LocaleList(new Locale[0]);
    }

    public boolean equals(Object obj) {
        return (obj instanceof c) && this.f5277a.equals(((c) obj).f5277a);
    }

    public int hashCode() {
        return this.f5277a.hashCode();
    }

    public String toString() {
        return this.f5277a.toString();
    }
}
