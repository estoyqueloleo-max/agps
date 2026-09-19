package c2;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.giobat.AgpsTrackerPP.P2ActivityPrivacy;

/* JADX INFO: loaded from: classes.dex */
public class z2 extends WebViewClient {
    public z2(P2ActivityPrivacy p2ActivityPrivacy) {
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        webView.loadUrl(webResourceRequest.getUrl().toString());
        return false;
    }
}
