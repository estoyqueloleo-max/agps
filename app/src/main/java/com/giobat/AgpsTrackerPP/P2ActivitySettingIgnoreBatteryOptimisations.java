package com.giobat.AgpsTrackerPP;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import androidx.appcompat.widget.d;
import androidx.appcompat.widget.p;
import c2.b3;
import c2.v2;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes.dex */
public class P2ActivitySettingIgnoreBatteryOptimisations extends AppCompatActivity {
    public void onClickSet(View view) {
        String packageName = getPackageName();
        PowerManager powerManager = (PowerManager) getSystemService("power");
        Intent intent = new Intent();
        if (!powerManager.isIgnoringBatteryOptimizations(packageName)) {
            intent.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
            intent.setData(Uri.parse("package:" + packageName));
            try {
                startActivity(intent);
            } catch (Exception e8) {
                d.c("askForIgnoreBatteryOptimisation error: ", e8, "GPS-I");
            }
        }
        powerManager.isIgnoringBatteryOptimizations(packageName);
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        v2.e("Help", "Ignore Battery Optimisations Setting");
        setContentView(R.layout.p2_setting_page);
        ((Button) findViewById(R.id.location_set_btn)).setText(getString(R.string.set));
        WebView webView = (WebView) findViewById(R.id.web_page_location);
        webView.setScrollBarStyle(0);
        webView.setScrollbarFadingEnabled(false);
        int iB = p.b();
        StringBuilder sbA = android.support.v4.media.b.a("ini_page3");
        sbA.append(p.c(iB));
        sbA.append(".html");
        String string = sbA.toString();
        webView.setWebViewClient(new b3(this));
        webView.loadUrl("file:///android_asset/" + string);
        Log.i("GPS-M", "File:" + string);
    }

    @Override // androidx.fragment.app.q, android.app.Activity
    public void onResume() {
        super.onResume();
        String packageName = getPackageName();
        PowerManager powerManager = (PowerManager) getSystemService("power");
        new Intent();
        if (powerManager.isIgnoringBatteryOptimizations(packageName)) {
            finish();
        }
    }
}
