package androidx.appcompat.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class p {

    public static final String GITHUB_PAGES_BASE_URL = "https://estoyqueloleo-max.github.io/agps/";
    public static final String OPENANDROMAPS_URL = "https://openandromaps.org/en/downloads";

    public p() {
    }

    public static int b() {
        String language = Locale.getDefault().getLanguage();
        if ("it".equalsIgnoreCase(language)) {
            return 2;
        }
        if ("es".equalsIgnoreCase(language)) {
            return 3;
        }
        if ("fr".equalsIgnoreCase(language)) {
            return 4;
        }
        if ("ru".equalsIgnoreCase(language)) {
            return 5;
        }
        if ("pt".equalsIgnoreCase(language)) {
            return 7;
        }
        return 1;
    }

    public static String c(int languageCode) {
        if (languageCode == 0) {
            return "";
        }
        int languageIndex = languageCode - 1;
        if (languageIndex == 1) {
            return "_it";
        }
        if (languageIndex == 2) {
            return "_es";
        }
        if (languageIndex == 3) {
            return "_fr";
        }
        if (languageIndex != 4) {
            return languageIndex != 6 ? "" : "_pt";
        }
        return "_ru";
    }

    public static InputConnection d(InputConnection inputConnection, EditorInfo editorInfo, View view) {
        return inputConnection;
    }

    public static void f(View view, float elevation) {
        view.setElevation(elevation);
    }

    public static String resolveDocumentationUrl(int targetPageCode) {
        return resolveDocumentationUrl(targetPageCode, Locale.getDefault().getLanguage());
    }

    public static String resolveDocumentationUrl(int targetPageCode, String languageCode) {
        String languageSuffix = "";
        if ("es".equalsIgnoreCase(languageCode)) {
            languageSuffix = "_es";
        } else if ("it".equalsIgnoreCase(languageCode)) {
            languageSuffix = "_it";
        }

        switch (targetPageCode) {
            case 3: // Help/Info (Offline Maps & DEM user guide)
                return GITHUB_PAGES_BASE_URL + "help" + languageSuffix + ".html";
            case 2: // Overview & Features
                return GITHUB_PAGES_BASE_URL + "index" + languageSuffix + ".html";
            case 4: // OpenAndroMaps downloads
                return OPENANDROMAPS_URL;
            case 5: // Storage help (legacy Android)
            case 6: // Scoped Storage help (Android 10+)
                return GITHUB_PAGES_BASE_URL + "help" + languageSuffix + ".html#geographic_maps";
            default:
                return GITHUB_PAGES_BASE_URL;
        }
    }

    public void e(Context context, String url) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            MainActivity.f3625m1.a("openUrlInBrowser error:" + e);
        }
    }

    public p(Context context, int targetPageCode) {
        b();
        try {
            String targetUrl = resolveDocumentationUrl(targetPageCode);
            e(context, targetUrl);
        } catch (Exception e) {
            d0.c("CallBrowser: ", e, MainActivity.f3625m1);
        }
    }
}
