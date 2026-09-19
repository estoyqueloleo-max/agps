package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.d;
import androidx.core.content.FileProvider;
import c2.h3;
import c2.v;
import c2.v2;
import c2.w;
import c2.x3;
import com.jcraft.jsch.SftpATTRS;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class ListViewWithCheckboxActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final /* synthetic */ int f3607v = 0;

    public class a implements AdapterView.OnItemClickListener {
        public a(ListViewWithCheckboxActivity listViewWithCheckboxActivity) {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i7, long j7) {
            w wVar = (w) adapterView.getAdapter().getItem(i7);
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.list_view_item_checkbox);
            if (wVar.f3214a) {
                checkBox.setChecked(false);
                wVar.f3214a = false;
            } else {
                checkBox.setChecked(true);
                wVar.f3214a = true;
            }
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ List f3608h;

        public class a implements Runnable {

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            public final /* synthetic */ File f3610h;

            public a(File file) {
                this.f3610h = file;
            }

            @Override // java.lang.Runnable
            public void run() {
                ListViewWithCheckboxActivity listViewWithCheckboxActivity = ListViewWithCheckboxActivity.this;
                File file = this.f3610h;
                int i7 = ListViewWithCheckboxActivity.f3607v;
                Objects.requireNonNull(listViewWithCheckboxActivity);
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("vnd.android.cursor.dir/email");
                Uri uriB = FileProvider.getUriForFile(listViewWithCheckboxActivity, listViewWithCheckboxActivity.getPackageName() + ".fileprovider", file);
                StringBuilder sbA = android.support.v4.media.b.a("AGPS-Tracker: ");
                sbA.append(file.getName());
                intent.putExtra("android.intent.extra.SUBJECT", sbA.toString());
                intent.putExtra("android.intent.extra.STREAM", uriB);
                intent.addFlags(1);
                listViewWithCheckboxActivity.startActivity(Intent.createChooser(intent, listViewWithCheckboxActivity.getResources().getString(R.string.how_share_file)));
            }
        }

        public b(List list) {
            this.f3608h = list;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int size = this.f3608h.size();
            ArrayList<Uri> arrayList = new ArrayList();
            arrayList.add(AgpsApplication.f3574q.m);
            boolean z = false;
            for (int i7 = 0; i7 < size; i7++) {
                w wVar = (w) this.f3608h.get(i7);
                if (wVar.f3214a) {
                    arrayList.add(wVar.f3217d);
                }
            }
            boolean z7 = MainActivity.I0;
            try {
                for (File file : MainActivity.X(MainActivity.Z0.getApplicationContext(), "GpxTemp", 3).listFiles()) {
                    file.delete();
                }
            } catch (Exception unused) {
            }
            String str = AgpsApplication.f3574q.f3242g;
            int iIndexOf = str.indexOf(".");
            if (iIndexOf == -1) {
                ((Queue) MainActivity.f3625m1.f3170i).add("Something wrong in this track name");
                return;
            }
            File fileX = MainActivity.X(ListViewWithCheckboxActivity.this.getApplicationContext(), "ZipTemp/" + (str.substring(0, iIndexOf) + ".zip"), 3);
            Context applicationContext = ListViewWithCheckboxActivity.this.getApplicationContext();
            try {
                ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(fileX)));
                byte[] bArr = new byte[4096];
                for (Uri uri : arrayList) {
                    v2.e("GPS-M", "Zip Adding: " + x3.g(uri.getPath()));
                    String strG = x3.g(uri.getPath());
                    MainActivity.f3625m1.a("Zipping: " + strG);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(applicationContext.getApplicationContext().getContentResolver().openInputStream(uri), 4096);
                    zipOutputStream.putNextEntry(new ZipEntry(strG.substring(strG.lastIndexOf("/") + 1)));
                    while (true) {
                        int i8 = bufferedInputStream.read(bArr, 0, 4096);
                        if (i8 == -1) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, i8);
                    }
                    bufferedInputStream.close();
                }
                zipOutputStream.close();
                z = true;
            } catch (Exception e8) {
                e8.printStackTrace();
            }
            if (z) {
                new Thread(new a(fileX)).start();
                ListViewWithCheckboxActivity.this.finish();
            }
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ListViewWithCheckboxActivity.this.finish();
        }
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_list_view_with_checkbox);
        setTitle(getString(R.string.zip_and_share_title));
        ListView listView = (ListView) findViewById(R.id.list_view_with_checkbox);
        x3 x3Var = AgpsApplication.f3574q;
        ArrayList arrayList = new ArrayList();
        try {
            MainActivity.X0.acquire(1);
            v2.e("GPS-I", "Show photos of track");
            for (h3 h3Var : (List<h3>) (List<?>) MainActivity.T1) {
                if (x3Var.f(h3Var.f3003e)) {
                    arrayList.add(h3Var);
                    v2.e("GPS-I", "-> foto: " + h3Var.f3002d.toString());
                }
            }
        } catch (Exception e8) {
            d.c("Error:", e8, "GPS-M");
        }
        MainActivity.X0.release();
        int size = arrayList.size() + 1;
        Uri[] uriArr = new Uri[size];
        try {
            uriArr[0] = x3Var.m;
            Iterator it = arrayList.iterator();
            int i7 = 1;
            while (it.hasNext()) {
                int i8 = i7 + 1;
                uriArr[i7] = ((h3) it.next()).f3002d;
                i7 = i8;
            }
        } catch (Exception e9) {
            d.c("Exception: ", e9, "GPS-M");
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i9 = 1; i9 < size; i9++) {
            String strG = x3.g(uriArr[i9].getPath());
            w wVar = new w();
            wVar.f3214a = true;
            wVar.f3215b = strG;
            Uri uri = uriArr[i9];
            Bitmap bitmapW = null;
            try {
                int iX = ActivityMyPhotoShow.x(this, uri);
                bitmapW = ActivityMyPhotoShow.w(this, uri);
                if (iX != 0) {
                    bitmapW = ActivityMyPhotoShow.z(bitmapW, iX);
                }
            } catch (Exception unused) {
            }
            wVar.f3216c = bitmapW;
            wVar.f3217d = uriArr[i9];
            arrayList2.add(wVar);
        }
        v vVar = new v(getApplicationContext(), arrayList2);
        vVar.notifyDataSetChanged();
        listView.setAdapter((ListAdapter) vVar);
        ((TextView) findViewById(R.id.list_view_msg)).setText(getString(R.string.zip_and_share_msg));
        ((TextView) findViewById(R.id.list_view_gpx)).setText(getResources().getString(R.string.gpx_file) + ":\n ->" + AgpsApplication.f3574q.f3242g);
        TextView textView = (TextView) findViewById(R.id.list_view_msg2);
        String string = getString(R.string.zip_and_share_msg2);
        if (vVar.getCount() > 0) {
            textView.setText(string);
        }
        listView.setOnItemClickListener(new a(this));
        ((Button) findViewById(R.id.list_zip)).setOnClickListener(new b(arrayList2));
        ((Button) findViewById(R.id.list_cancel)).setOnClickListener(new c());
    }
}
