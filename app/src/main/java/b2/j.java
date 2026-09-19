package b2;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j {
    public static /* synthetic */ String a(int i7) {
        if (i7 == 1) {
            return "px";
        }
        if (i7 == 2) {
            return "em";
        }
        if (i7 == 3) {
            return "ex";
        }
        if (i7 == 4) {
            return "in";
        }
        if (i7 == 5) {
            return "cm";
        }
        if (i7 == 6) {
            return "mm";
        }
        if (i7 == 7) {
            return "pt";
        }
        if (i7 == 8) {
            return "pc";
        }
        return i7 == 9 ? "percent" : "null";
    }

    public static /* synthetic */ String b(int i7) {
        if (i7 == 1) {
            return "noAltitude";
        }
        if (i7 == 2) {
            return "low";
        }
        if (i7 == 3) {
            return "high";
        }
        return i7 == 4 ? "wait" : "null";
    }

    public static /* synthetic */ int c(String str) {
        Objects.requireNonNull(str, "Name is null");
        if (str.equals("px")) {
            return 1;
        }
        if (str.equals("em")) {
            return 2;
        }
        if (str.equals("ex")) {
            return 3;
        }
        if (str.equals("in")) {
            return 4;
        }
        if (str.equals("cm")) {
            return 5;
        }
        if (str.equals("mm")) {
            return 6;
        }
        if (str.equals("pt")) {
            return 7;
        }
        if (str.equals("pc")) {
            return 8;
        }
        if (str.equals("percent")) {
            return 9;
        }
        throw new IllegalArgumentException("No enum constant com.caverock.androidsvg.SVG.Unit.".concat(str));
    }
}
