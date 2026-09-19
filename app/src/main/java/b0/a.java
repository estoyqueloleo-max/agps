package b0;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static int a(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission);
    }

    public static class c {
        public static Drawable b(Context context, int id) {
            return ContextCompat.getDrawable(context, id);
        }
    }
}
