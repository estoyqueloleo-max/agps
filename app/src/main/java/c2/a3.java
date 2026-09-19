package c2;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.giobat.AgpsTrackerPP.P2ActivitySettingGeneralInfo;

/* JADX INFO: loaded from: classes.dex */
public class a3 extends WebViewClient {
    public a3(P2ActivitySettingGeneralInfo p2ActivitySettingGeneralInfo) {
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        webView.loadUrl(webResourceRequest.getUrl().toString());
        return false;
    }
}
