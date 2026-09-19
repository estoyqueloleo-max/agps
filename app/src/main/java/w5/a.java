package w5;

import java.util.Objects;

/**
 * Standard null-check and equality helper (originally Kotlin Intrinsics).
 */
public class a {
    public static boolean a(Object obj, Object obj2) {
        return Objects.equals(obj, obj2);
    }

    public static void b(Object obj) {
        Objects.requireNonNull(obj);
    }

    public static void c(Object obj, String msg) {
        Objects.requireNonNull(obj, msg);
    }

    public static void d(Object obj, String paramName) {
        Objects.requireNonNull(obj, paramName);
    }
}
