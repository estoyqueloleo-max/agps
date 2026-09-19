package i;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f {
    public static String a(String str, String str2) {
        return str + str2;
    }

    public static /* synthetic */ String b(int i7) {
        if (i7 == 1) {
            return "UNKNOWN";
        }
        if (i7 == 2) {
            return "HORIZONTAL_DIMENSION";
        }
        if (i7 == 3) {
            return "VERTICAL_DIMENSION";
        }
        if (i7 == 4) {
            return "LEFT";
        }
        if (i7 == 5) {
            return "RIGHT";
        }
        if (i7 == 6) {
            return "TOP";
        }
        if (i7 == 7) {
            return "BOTTOM";
        }
        return i7 == 8 ? "BASELINE" : "null";
    }
}
