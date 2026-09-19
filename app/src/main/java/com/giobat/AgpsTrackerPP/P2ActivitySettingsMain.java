package com.giobat.AgpsTrackerPP;

import android.content.Intent;
import android.content.UriPermission;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.result.d;
import androidx.appcompat.widget.p;
import androidx.appcompat.app.AppCompatActivity;
import i.f;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class P2ActivitySettingsMain extends AppCompatActivity {
    public Button A;
    public boolean B = true;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Button f3713v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Button f3714w;
    public Button x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Button f3715y;
    public Button z;

    public void onClickGo(View view) {
        setResult(-1, getIntent());
        finish();
    }

    public void onClickSetGeneralInfo(View view) {
        startActivity(new Intent(this, (Class<?>) P2ActivitySettingGeneralInfo.class));
    }

    public void onClickSetIgnoreBatteryOpt(View view) {
        startActivity(new Intent(this, (Class<?>) P2ActivitySettingIgnoreBatteryOptimisations.class));
    }

    public void onClickSetLocation(View view) {
        startActivity(new Intent(this, (Class<?>) P2ActivitySettingLocation.class));
    }

    public void onClickSetPrivacy(View view) {
        startActivity(new Intent(this, (Class<?>) P2ActivityPrivacy.class));
    }

    public void onClickSetStorage(View view) {
        startActivity(new Intent(this, (Class<?>) P2ActivitySettingStorage.class));
    }

    public void onClickVideoStorage(View view) {
        new p(this, Build.VERSION.SDK_INT >= 29 ? 6 : 5);
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.p2_activity_settings_main);
        TextView textView = (TextView) findViewById(R.id.init_title);
        StringBuilder sbA = android.support.v4.media.b.a("\n");
        sbA.append(getString(R.string.initial_settings));
        sbA.append("\n");
        textView.setText(sbA.toString());
        this.f3713v = (Button) findViewById(R.id.localisation_btn);
        this.f3714w = (Button) findViewById(R.id.storage_btn);
        this.x = (Button) findViewById(R.id.ignore_battery_optimisations_btn);
        this.f3715y = (Button) findViewById(R.id.privacy_btn);
        this.z = (Button) findViewById(R.id.general_info_btn);
        this.A = (Button) findViewById(R.id.go_btn);
        ((Button) findViewById(R.id.video_storage_link)).setText(getString(R.string.initialisation_video));
    }

    /* JADX WARN: Code duplicated, block: B:13:0x005b  */
    @Override // androidx.fragment.app.q, android.app.Activity
    public void onResume() {
        boolean z = false;
        super.onResume();
        this.B = true;
        String string = getString(R.string.location_permission);
        if (b0.a.a(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            this.f3713v.setText(f.a("✔ ", string));
        } else {
            this.B = false;
            this.f3713v.setText(string);
        }
        String string2 = getString(R.string.gpx_and_photo_storage);
        Iterator<UriPermission> it = getContentResolver().getPersistedUriPermissions().iterator();
        if (it.hasNext()) {
            UriPermission next = it.next();
            MainActivity.f3624l1 = next.getUri();
            z = next.isReadPermission() && next.isWritePermission();
        }
        if (z) {
            StringBuilder sbA = d.a("✔ ", string2, ":\n");
            sbA.append(MainActivity.f3624l1.getLastPathSegment());
            this.f3714w.setText(sbA.toString());
        } else {
            this.B = false;
            this.f3714w.setText(string2);
        }
        String string3 = getString(R.string.ignore_battery_optimisations);
        String packageName = getPackageName();
        PowerManager powerManager = (PowerManager) getSystemService("power");
        new Intent();
        if (powerManager.isIgnoringBatteryOptimizations(packageName)) {
            this.x.setText(f.a("✔ ", string3));
        } else {
            this.B = false;
            this.x.setText(string3);
        }
        String string4 = getString(R.string.privacy);
        if (!P2ActivityPrivacy.f3710w) {
            this.B = false;
            this.f3715y.setText(string4);
        } else if (P2ActivityPrivacy.f3709v) {
            StringBuilder sbA2 = d.a("✔ ", string4, " ");
            sbA2.append(getString(R.string.accepted));
            this.f3715y.setText(sbA2.toString());
        } else {
            StringBuilder sbD = c7.f.d(string4, " ");
            sbD.append(getString(R.string.not_accepted));
            this.f3715y.setText(sbD.toString());
            this.B = false;
        }
        String string5 = getString(R.string.general_info);
        if (P2ActivitySettingGeneralInfo.f3711v) {
            this.z.setText(f.a("✔ ", string5));
        } else {
            this.B = false;
            this.z.setText(string5);
        }
        if (!this.B) {
            this.A.setVisibility(4);
        } else {
            this.A.setVisibility(0);
            this.A.setText(getString(R.string.go_app));
        }
    }
}
