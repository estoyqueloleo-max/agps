package androidx.appcompat.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class p {

    public p() {
    }

    public static int b() {
        byte b8;
        String language = Locale.getDefault().getLanguage();
        int iHashCode = language.hashCode();
        if (iHashCode != 3241) {
            if (iHashCode != 3246) {
                if (iHashCode != 3276) {
                    if (iHashCode != 3371) {
                        if (iHashCode != 3588) {
                            if (iHashCode == 3651 && language.equals("ru")) {
                                b8 = 3;
                            } else {
                                b8 = -1;
                            }
                        } else if (language.equals("pt")) {
                            b8 = 4;
                        } else {
                            b8 = -1;
                        }
                    } else if (language.equals("it")) {
                        b8 = 0;
                    } else {
                        b8 = -1;
                    }
                } else if (language.equals("fr")) {
                    b8 = 2;
                } else {
                    b8 = -1;
                }
            } else if (language.equals("es")) {
                b8 = 1;
            } else {
                b8 = -1;
            }
        } else if (language.equals("en")) {
            b8 = 5;
        } else {
            b8 = -1;
        }
        if (b8 == 0) {
            return 2;
        }
        if (b8 == 1) {
            return 3;
        }
        if (b8 == 2) {
            return 4;
        }
        if (b8 != 3) {
            return b8 != 4 ? 1 : 7;
        }
        return 5;
    }

    public static String c(int i7) {
        if (i7 == 0) {
            return "";
        }
        int i8 = i7 - 1;
        if (i8 == 1) {
            return "_it";
        }
        if (i8 == 2) {
            return "_es";
        }
        if (i8 == 3) {
            return "_fr";
        }
        if (i8 != 4) {
            return i8 != 6 ? "" : "_pt";
        }
        return "_ru";
    }

    public static InputConnection d(InputConnection inputConnection, EditorInfo editorInfo, View view) {
        return inputConnection;
    }

    public static void f(View view, float f7) {
        view.setElevation(f7);
    }

    public void e(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e8) {
            MainActivity.f3625m1.a("openUrlInBrowser error:" + e8);
        }
    }

    public p(Context context, int i7) {
        b();
        try {
            e(context, i7 == 3 ? "https://agps-tracker.com/wp/?page_id=952" : i7 == 2 ? "https://agps-tracker.com/wp/?page_id=856&lang=en#video" : i7 == 4 ? "https://openandromaps.org/en/downloads" : i7 == 5 ? "https://agps-tracker.com/wp/?page_id=1343&lang=en" : i7 == 6 ? "https://agps-tracker.com/wp/?page_id=1349&lang=en" : "");
        } catch (Exception e8) {
            d0.c("CallBrowser: ", e8, MainActivity.f3625m1);
        }
    }
}
