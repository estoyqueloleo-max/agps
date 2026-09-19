package androidx.appcompat.widget;

import c2.v2;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d {
    public static int a(String str) {
        if ("never".equals(str)) {
            return 1;
        }
        if ("always".equals(str)) {
            return 2;
        }
        if ("ifspace".equals(str)) {
            return 3;
        }
        throw new IllegalArgumentException(("Invalid value for Display: " + str));
    }

    public static int b(int i7, int i8, int i9, int i10) {
        return i7 + i8 + i9 + i10;
    }

    public static void c(String str, Exception exc, String str2) {
        v2.e(str2, str + exc);
    }

    public static /* synthetic */ int d(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("pad")) {
            return 1;
        }
        if (str.equals("reflect")) {
            return 2;
        }
        if (str.equals("repeat")) {
            return 3;
        }
        throw new IllegalArgumentException("No enum constant com.caverock.androidsvg.SVG.GradientSpread.".concat(str));
    }
}
