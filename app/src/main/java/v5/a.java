package v5;

import i.f;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f18471a = a.class.getPackage().getName() + ".MessageStrings";

    static {
        Integer numValueOf;
        r5.a aVar = r5.a.f17635c;
        synchronized (r5.a.class) {
            synchronized (r5.a.class) {
                try {
                    String strA = r5.a.a("gov.nasa.worldwind.avkey.MaxMessageRepeat");
                    numValueOf = null;
                    if (strA != null) {
                        try {
                            numValueOf = Integer.valueOf(Integer.parseInt(strA));
                        } catch (NumberFormatException unused) {
                            d().log(Level.SEVERE, "Configuration.ConversionError", strA);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            int maxRepeat = numValueOf != null ? numValueOf.intValue() : 10;
        }
    }

    public static String a(String str) {
        if (str == null) return "";
        try {
            return ResourceBundle.getBundle(f18471a, Locale.getDefault()).getString(str);
        } catch (Throwable unused) {
            try {
                return ResourceBundle.getBundle("gov.nasa.worldwind.util.MessageStrings", Locale.getDefault()).getString(str);
            } catch (Throwable ignored) {
                return str;
            }
        }
    }

    public static String b(String str, String str2) {
        return str2 != null ? str2 : a(str);
    }

    public static String c(String str, Object... objArr) {
        if (str == null) return "";
        try {
            return MessageFormat.format(str, objArr);
        } catch (Exception e8) {
            return str;
        }
    }

    public static Logger d() {
        return e("gov.nasa.worldwind");
    }

    public static Logger e(String str) {
        return Logger.getLogger(str);
    }
}
