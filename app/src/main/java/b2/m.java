package b2;

import org.xml.sax.Attributes;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m {
    public static int a(String str) {
        if ("auto".equals(str)) {
            return 3;
        }
        if ("center".equals(str)) {
            return 2;
        }
        if ("below".equals(str)) {
            return 3;
        }
        if ("below_left".equals(str)) {
            return 4;
        }
        if ("below_right".equals(str)) {
            return 5;
        }
        if ("above".equals(str)) {
            return 6;
        }
        if ("above_left".equals(str)) {
            return 7;
        }
        if ("above_right".equals(str)) {
            return 8;
        }
        if ("left".equals(str)) {
            return 9;
        }
        if ("right".equals(str)) {
            return 10;
        }
        throw new IllegalArgumentException(("Invalid value for Position: " + str));
    }
}
