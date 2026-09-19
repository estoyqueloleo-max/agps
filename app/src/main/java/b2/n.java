package b2;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n {
    public static void a(StringBuilder sb, char c8, String str, String str2) {
        sb.append(c8);
        sb.append(str);
        Log.e(str2, sb.toString());
    }

    public static /* synthetic */ String b(int i7) {
        if (i7 == 1) {
            return "FILL";
        }
        return i7 == 2 ? "STROKE" : "null";
    }
}
