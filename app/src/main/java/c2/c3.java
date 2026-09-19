package c2;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.giobat.AgpsTrackerPP.P2ActivitySettingLocation;

/* JADX INFO: loaded from: classes.dex */
public class c3 extends WebViewClient {
    public c3(P2ActivitySettingLocation p2ActivitySettingLocation) {
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        webView.loadUrl(webResourceRequest.getUrl().toString());
        return false;
    }
}
