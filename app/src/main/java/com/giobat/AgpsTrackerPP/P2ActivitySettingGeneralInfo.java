package com.giobat.AgpsTrackerPP;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import androidx.appcompat.widget.p;
import c2.a3;
import c2.v2;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes.dex */
public class P2ActivitySettingGeneralInfo extends AppCompatActivity {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static boolean f3711v = false;

    public void onClickSet(View view) {
        f3711v = true;
        finish();
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        v2.e("GPS-I", "General Info ");
        setContentView(R.layout.p2_setting_page);
        ((Button) findViewById(R.id.location_set_btn)).setText(getString(R.string.ok));
        WebView webView = (WebView) findViewById(R.id.web_page_location);
        webView.setScrollBarStyle(0);
        webView.setScrollbarFadingEnabled(false);
        int iB = p.b();
        StringBuilder sbA = android.support.v4.media.b.a("ini_page5");
        sbA.append(p.c(iB));
        sbA.append(".html");
        String string = sbA.toString();
        webView.setWebViewClient(new a3(this));
        webView.loadUrl("file:///android_asset/" + string);
        Log.i("GPS-M", "File:" + string);
    }

    @Override // androidx.fragment.app.q, android.app.Activity
    public void onResume() {
        super.onResume();
    }
}
