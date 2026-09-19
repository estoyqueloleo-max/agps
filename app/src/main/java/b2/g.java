package b2;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g {
    public static void a(StringBuilder sb, String str, String str2, String str3, String str4) {
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
    }

    public static /* synthetic */ String b(int i7) {
        if (i7 == 1) {
            return "meet";
        }
        return i7 == 2 ? "slice" : "null";
    }

    public static /* synthetic */ String c(int i7) {
        if (i7 == 1) {
            return "NON_SO";
        }
        if (i7 == 2) {
            return "SI";
        }
        return i7 == 3 ? "NO" : "null";
    }

    public static /* synthetic */ String d(int i7) {
        if (i7 == 1) {
            return "BLACK";
        }
        if (i7 == 2) {
            return "BLUE";
        }
        if (i7 == 3) {
            return "GREEN";
        }
        if (i7 == 4) {
            return "RED";
        }
        if (i7 == 5) {
            return "TRANSPARENT";
        }
        return i7 == 6 ? "WHITE" : "null";
    }
}
