package com.giobat.AgpsTrackerPP;

import android.content.Intent;
import android.content.UriPermission;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import androidx.activity.result.c;
import androidx.appcompat.widget.d;
import androidx.appcompat.widget.p;
import c2.f3;
import c2.g3;
import c2.v2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class P2ActivitySettingStorage extends AppCompatActivity {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public androidx.activity.result.ActivityResultLauncher<Intent> f3712v;

    public void onClickSet(View view) {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        intent.addFlags(1);
        intent.addFlags(2);
        intent.addFlags(64);
        Uri uri = Uri.parse("content://com.android.externalstorage.documents/document/primary%3AAGPS-Tracker");
        if (Build.VERSION.SDK_INT >= 29) {
            intent.putExtra("android.provider.extra.INITIAL_URI", uri);
        }
        Iterator<UriPermission> it = getContentResolver().getPersistedUriPermissions().iterator();
        while (it.hasNext()) {
            getContentResolver().releasePersistableUriPermission(it.next().getUri(), 3);
        }
        this.f3712v.launch(intent);
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.p2_storage_page);
        ((Button) findViewById(R.id.storage_set_btn)).setText(getString(R.string.set));
        WebView webView = (WebView) findViewById(R.id.web_page_location_storage);
        webView.setScrollBarStyle(0);
        webView.setScrollbarFadingEnabled(false);
        int iB = p.b();
        StringBuilder sbA = android.support.v4.media.b.a("ini_page2");
        sbA.append(p.c(iB));
        sbA.append(".html");
        String string = sbA.toString();
        webView.setWebViewClient(new g3(this));
        webView.loadUrl("file:///android_asset/" + string);
        Log.i("GPS-M", "File:" + string);
        this.f3712v = registerForActivityResult(new androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult(), new f3(this));
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0034  */
    @Override // androidx.fragment.app.q, android.app.Activity
    public void onResume() {
        boolean zW;
        super.onResume();
        Iterator<UriPermission> it = getContentResolver().getPersistedUriPermissions().iterator();
        if (it.hasNext()) {
            UriPermission next = it.next();
            MainActivity.f3624l1 = next.getUri();
            if (next.isReadPermission() && next.isWritePermission()) {
                zW = w(MainActivity.f3624l1);
            } else {
                zW = false;
            }
        } else {
            zW = false;
        }
        if (zW) {
            finish();
        }
    }

    public final boolean w(Uri uri) {
        try {
            DocumentFile aVarC = DocumentFile.fromTreeUri(this, uri).createFile("application/*", "probeTest");
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(getContentResolver().openOutputStream(aVarC.getUri())));
            bufferedWriter.write("This is a probe test: writing");
            bufferedWriter.close();
            return aVarC.delete();
        } catch (Exception e8) {
            d.c("gpx dir probing not successful!!!: ", e8, "GPS-I");
            return false;
        }
    }
}
