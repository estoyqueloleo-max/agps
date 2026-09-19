package c7;

import r.g;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f {
    public static int a(int i7) {
        for (int i8 : b()) {
            if (c(i8) == i7) {
                return i8;
            }
        }
        return 0;
    }

    public static int[] b() {
        return g.d(14);
    }

    public static /* synthetic */ int c(int i7) {
        if (i7 == 1) {
            return 0;
        }
        if (i7 == 2) {
            return 1;
        }
        if (i7 == 3) {
            return 3;
        }
        if (i7 == 4) {
            return 5;
        }
        if (i7 == 5) {
            return 8;
        }
        if (i7 == 6) {
            return 11;
        }
        if (i7 == 7) {
            return 13;
        }
        if (i7 == 8) {
            return 15;
        }
        if (i7 == 9) {
            return 18;
        }
        if (i7 == 10) {
            return 21;
        }
        if (i7 == 11) {
            return 23;
        }
        if (i7 == 12) {
            return 25;
        }
        if (i7 == 13) {
            return 28;
        }
        if (i7 == 14) {
            return 31;
        }
        return 0;
    }

    public static StringBuilder d(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static /* synthetic */ String e(int i7) {
        if (i7 == 1) {
            return "NULL";
        }
        if (i7 == 2) {
            return "POINT";
        }
        if (i7 == 3) {
            return "POLYLINE";
        }
        if (i7 == 4) {
            return "POLYGON";
        }
        if (i7 == 5) {
            return "MULTIPOINT";
        }
        if (i7 == 6) {
            return "POINT_Z";
        }
        if (i7 == 7) {
            return "POLYLINE_Z";
        }
        if (i7 == 8) {
            return "POLYGON_Z";
        }
        if (i7 == 9) {
            return "MULTIPOINT_Z";
        }
        if (i7 == 10) {
            return "POINT_M";
        }
        if (i7 == 11) {
            return "POLYLINE_M";
        }
        if (i7 == 12) {
            return "POLYGON_M";
        }
        if (i7 == 13) {
            return "MULTIPOINT_M";
        }
        return i7 == 14 ? "MULTIPATCH" : "null";
    }
}
