package g0;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static boolean a() {
        if (Build.VERSION.SDK_INT < 31) {
            String str = Build.VERSION.CODENAME;
            if (!(!"REL".equals(str) && str.compareTo("S") >= 0)) {
                return false;
            }
        }
        return true;
    }
}
