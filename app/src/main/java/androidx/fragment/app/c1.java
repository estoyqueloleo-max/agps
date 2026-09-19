package androidx.fragment.app;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c1 {
    public static /* synthetic */ String a(int i7) {
        if (i7 == 1) {
            return "NONE";
        }
        if (i7 == 2) {
            return "ADDING";
        }
        return i7 == 3 ? "REMOVING" : "null";
    }

    public static /* synthetic */ String b(int i7) {
        if (i7 == 1) {
            return "BOTTOM_CENTER";
        }
        if (i7 == 2) {
            return "BOTTOM_LEFT";
        }
        if (i7 == 3) {
            return "BOTTOM_RIGHT";
        }
        if (i7 == 4) {
            return "TOP_CENTER";
        }
        if (i7 == 5) {
            return "TOP_LEFT";
        }
        return i7 == 6 ? "TOP_RIGHT" : "null";
    }
}
