package com.giobat.AgpsTrackerPP;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import androidx.appcompat.widget.p;
import c2.v2;
import c2.z2;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes.dex */
public class P2ActivityPrivacy extends AppCompatActivity {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static boolean f3709v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static boolean f3710w = false;

    public void onClickLeft(View view) {
        f3710w = true;
        f3709v = false;
        finish();
    }

    public void onClickRight(View view) {
        f3710w = true;
        f3709v = true;
        finish();
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        v2.e("Help", "License/Privacy Activity starts ");
        setContentView(R.layout.p2_privacy_page);
        ((Button) findViewById(R.id.leftBtn)).setText(getString(R.string.reject));
        ((Button) findViewById(R.id.rightBtn)).setText(getString(R.string.accept));
        WebView webView = (WebView) findViewById(R.id.web_page_location);
        webView.setScrollBarStyle(0);
        webView.setScrollbarFadingEnabled(false);
        int iB = p.b();
        StringBuilder sbA = android.support.v4.media.b.a("privacy_info");
        sbA.append(p.c(iB));
        sbA.append(".html");
        String string = sbA.toString();
        webView.setWebViewClient(new z2(this));
        webView.loadUrl("file:///android_asset/" + string);
        Log.i("GPS-M", "File:" + string);
    }

    @Override // androidx.fragment.app.q, android.app.Activity
    public void onResume() {
        super.onResume();
    }
}
