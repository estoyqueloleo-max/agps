package com.giobat.AgpsTrackerPP;

import android.app.AlertDialog;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import androidx.appcompat.widget.p;
import c2.c3;
import c2.d3;
import c2.e3;
import c2.v2;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class P2ActivitySettingLocation extends AppCompatActivity {
    public void onClickSet(View view) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        MainActivity.P0 = 12345;
        if (arrayList.isEmpty()) {
            return;
        }
        androidx.core.app.ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 200);
        StringBuilder sbA = android.support.v4.media.b.a("permission request=");
        sbA.append(arrayList.size());
        Log.i("GPS-M", sbA.toString());
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        boolean z = false;
        super.onCreate(bundle);
        v2.e("GPS-I", "License/Privacy Activity starts ");
        setContentView(R.layout.p2_setting_page);
        ((Button) findViewById(R.id.location_set_btn)).setText(getString(R.string.set));
        WebView webView = (WebView) findViewById(R.id.web_page_location);
        webView.setScrollBarStyle(0);
        webView.setScrollbarFadingEnabled(false);
        int iB = p.b();
        StringBuilder sbA = android.support.v4.media.b.a("ini_page1");
        sbA.append(p.c(iB));
        sbA.append(".html");
        String string = sbA.toString();
        webView.setWebViewClient(new c3(this));
        webView.loadUrl("file:///android_asset/" + string);
        Log.i("GPS-M", "File:" + string);
        try {
            z = ((LocationManager) getSystemService("location")).isProviderEnabled("gps");
        } catch (Exception unused) {
        }
        if (z) {
            return;
        }
        try {
            if (((LocationManager) getSystemService("location")).isProviderEnabled("gps")) {
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.gps_disabled_tobe_enabled)).setCancelable(false).setPositiveButton("Enable GPS", new d3(this));
            builder.setNegativeButton(getString(R.string.cancel), new e3(this));
            builder.create().show();
        } catch (Exception unused2) {
        }
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i7, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i7, strArr, iArr);
        v2.e("GPS-M", "permission?" + i7 + "--" + iArr.length);
        if (i7 == 200) {
            if (iArr.length <= 0) {
                Log.i("GPS-M", "no result?");
                return;
            }
            for (int i8 = 0; i8 < strArr.length; i8++) {
                if (strArr[i8].equals("android.permission.ACCESS_FINE_LOCATION")) {
                    MainActivity.P0 = iArr[i8];
                }
            }
        }
    }

    @Override // androidx.fragment.app.q, android.app.Activity
    public void onResume() {
        super.onResume();
        if (MainActivity.P0 == 0) {
            finish();
        }
    }
}
