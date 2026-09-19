package c0;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import androidx.core.content.res.ResourcesCompat;

/* JADX INFO: loaded from: classes.dex */
public class e {
    public static final ThreadLocal<TypedValue> f2845a = new ThreadLocal<>();

    public static Typeface a(Context context, int id) {
        return ResourcesCompat.getFont(context, id);
    }

    public static class c {
    }
}
