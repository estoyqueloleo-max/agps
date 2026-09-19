package com.giobat.AgpsTrackerPP;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes.dex */
public class HtmlShowActivity extends AppCompatActivity {
    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.html_show_activity);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webView.setScrollBarStyle(0);
        webView.loadUrl("file:///android_asset/licenseMaps.html");
    }
}
