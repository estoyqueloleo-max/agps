package com.giobat.AgpsTrackerPP;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.p;
import c2.v2;
import androidx.appcompat.app.AppCompatActivity;
import i.f;
import r.g;

/* JADX INFO: loaded from: classes.dex */
public class ActivityLicenseInfo extends AppCompatActivity {

    public class a extends WebViewClient {
        public a(ActivityLicenseInfo activityLicenseInfo) {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            webView.loadUrl(webResourceRequest.getUrl().toString());
            return false;
        }
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        String strA;
        super.onCreate(bundle);
        v2.e("Help", "License/Privacy Activity starts ");
        setContentView(R.layout.activity_info);
        String stringExtra = getIntent().getStringExtra("ASSET_FILE_NAME");
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if (stringExtra.equals("license")) {
            toolbar.setTitle(R.string.agps_tracker_license);
        } else if (stringExtra.equals("privacy_info")) {
            toolbar.setTitle(R.string.privacy);
        }
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        WebView webView = (WebView) findViewById(R.id.web_page);
        int iC = g.c(p.b());
        if (iC == 1) {
            strA = f.a(stringExtra, "_it.html");
        } else if (iC == 2) {
            strA = f.a(stringExtra, "_es.html");
        } else if (iC == 3) {
            strA = f.a(stringExtra, "_fr.html");
        } else if (iC != 4) {
            strA = iC != 6 ? f.a(stringExtra, ".html") : f.a(stringExtra, "_pt.html");
        } else {
            strA = f.a(stringExtra, "_ru.html");
        }
        webView.setWebViewClient(new a(this));
        webView.loadUrl("file:///android_asset/" + strA);
        v2.e("Help", "About Activity end ");
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        finish();
        return true;
    }
}
