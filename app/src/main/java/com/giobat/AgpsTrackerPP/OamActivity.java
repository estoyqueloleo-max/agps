package com.giobat.AgpsTrackerPP;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes.dex */
public class OamActivity extends AppCompatActivity {
    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_prova_o_a_m);
        Intent intent = getIntent();
        intent.getAction();
        Uri data = intent.getData();
        MainActivity.f3625m1.a(getResources().getString(R.string.download_in_backgroud));
        getIntent().getExtras();
        String strB = a2.e.b("http://", data.getHost(), data.getPath());
        String strY = MainActivity.Y(data.getPath());
        String strB2 = a2.e.b("Maps", "/", strY);
        DownloadManager downloadManager = (DownloadManager) getSystemService("download");
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(strB));
        request.setTitle(strY);
        request.setNotificationVisibility(1);
        request.setDestinationUri(Uri.fromFile(MainActivity.X(this, strB2, 2)));
        SFtpServerFileDialogActivity.z = downloadManager.enqueue(request);
        SFtpServerFileDialogActivity.A = true;
        MainActivity.f3625m1.a(getResources().getString(R.string.download_in_backgroud));
        finish();
    }

    @Override // f.e, androidx.fragment.app.q, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }
}
