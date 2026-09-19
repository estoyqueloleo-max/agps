package c2;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.giobat.AgpsTrackerPP.P2ActivitySettingIgnoreBatteryOptimisations;

/* JADX INFO: loaded from: classes.dex */
public class b3 extends WebViewClient {
    public b3(P2ActivitySettingIgnoreBatteryOptimisations p2ActivitySettingIgnoreBatteryOptimisations) {
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        webView.loadUrl(webResourceRequest.getUrl().toString());
        return false;
    }
}
