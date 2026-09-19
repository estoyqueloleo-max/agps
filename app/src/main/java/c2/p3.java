package c2;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p3 {
    public static void a(Context context, int i7, StringBuilder sb, String str) {
        if (context != null) {
            sb.append(context.getString(i7));
        } else {
            sb.append("id_").append(i7);
        }
        sb.append(str);
    }
}
