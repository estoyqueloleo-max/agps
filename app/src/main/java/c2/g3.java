package c2;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.giobat.AgpsTrackerPP.P2ActivitySettingStorage;

/* JADX INFO: loaded from: classes.dex */
public class g3 extends WebViewClient {
    public g3(P2ActivitySettingStorage p2ActivitySettingStorage) {
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        webView.loadUrl(webResourceRequest.getUrl().toString());
        return false;
    }
}
