package com.giobat.AgpsTrackerPP;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import c2.l3;
import c2.m2;
import c2.v2;
import c2.y2;
import com.google.android.material.snackbar.Snackbar;
import com.jcraft.jsch.ChannelSftp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class SFtpServerFileDialogActivity extends Activity {
    public static boolean A = false;
    public static Vector x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static ArrayList<y2> f3722y;
    public static long z;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TextView f3723h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<HashMap<String, Object>> f3724i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public SimpleAdapter f3726k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public b f3727l;
    public String m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ProgressBar f3728n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ProgressBar f3729o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public TextView f3730p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public TextView f3731q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public TextView f3732r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public View f3733s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public com.giobat.AgpsTrackerPP.b f3734t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public com.giobat.AgpsTrackerPP.b f3735u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f3737w;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f3725j = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f3736v = true;

    public class a implements Runnable {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Handler f3738h;

        public a(Handler handler) {
            this.f3738h = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (com.giobat.AgpsTrackerPP.b.f3748o) {
                v2.e("GPS-M", "Connected");
                com.giobat.AgpsTrackerPP.b.f3748o = false;
                SFtpServerFileDialogActivity sFtpServerFileDialogActivity = SFtpServerFileDialogActivity.this;
                sFtpServerFileDialogActivity.f3723h.setText(sFtpServerFileDialogActivity.getText(R.string.select_map_to_dwld));
            }
            this.f3738h.postDelayed(this, 500L);
        }
    }

    public class b extends BroadcastReceiver {
        public b(Context context, a aVar) {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String string;
            String string2;
            String str;
            int i7;
            long j7;
            if (intent.getExtras() == null || (string = intent.getExtras().getString("OP")) == null || (string2 = intent.getExtras().getString("STATUS")) == null) {
                return;
            }
            v2.e("GPS-M", "==> SFTP Broadcast ricevuto! " + string + " -" + string2);
            if (string.equals("ACTION_SFTP_CONNECT") && string2.equals("OK")) {
                com.giobat.AgpsTrackerPP.b.f3748o = true;
                v2.e("GPS-M", "==> sFTP Connect");
                SFtpServerFileDialogActivity sFtpServerFileDialogActivity = SFtpServerFileDialogActivity.this;
                sFtpServerFileDialogActivity.c(sFtpServerFileDialogActivity.m);
            }
            if (string.equals("ACTION_SFTP_CHANGE_DIR") && string2.equals("OK")) {
                v2.e("GPS-M", "==> sFTP showRemoteDir");
                SFtpServerFileDialogActivity sFtpServerFileDialogActivity2 = SFtpServerFileDialogActivity.this;
                Vector vector = SFtpServerFileDialogActivity.x;
                Objects.requireNonNull(sFtpServerFileDialogActivity2);
                Vector vector2 = SFtpServerFileDialogActivity.x;
                if (vector2 == null || vector2.size() == 0) {
                    str = string2;
                } else {
                    sFtpServerFileDialogActivity2.f3737w.invalidate();
                    SFtpServerFileDialogActivity.f3722y.clear();
                    sFtpServerFileDialogActivity2.f3724i.clear();
                    v2.e("GPS-M", "===> showRemoteDir: " + MainActivity.K0 + "- Nfiles=" + SFtpServerFileDialogActivity.x.size());
                    boolean zEquals = MainActivity.K0.equals(AgpsApplication.f3577t);
                    long j8 = 0;
                    int i8 = R.drawable.folder;
                    if (!zEquals) {
                        y2 y2Var = new y2("⤴", MainActivity.K0, 0L, true, 0L, 6);
                        SFtpServerFileDialogActivity.f3722y.add(y2Var);
                        sFtpServerFileDialogActivity2.a(y2Var, R.drawable.folder);
                    }
                    TreeMap treeMap = new TreeMap();
                    TreeMap treeMap2 = new TreeMap();
                    int i9 = 0;
                    while (true) {
                        int i10 = i9;
                        if (i10 >= SFtpServerFileDialogActivity.x.size()) {
                            break;
                        }
                        if (((ChannelSftp.LsEntry) SFtpServerFileDialogActivity.x.get(i10)).getAttrs().isDir()) {
                            String filename = ((ChannelSftp.LsEntry) SFtpServerFileDialogActivity.x.get(i10)).getFilename();
                            if (!filename.equals(".") && !filename.equals("..")) {
                                treeMap = treeMap;
                                treeMap.put(filename, new y2(filename, MainActivity.K0, Long.valueOf(j8), true, 0L, 6));
                            }
                            i7 = i10;
                        } else {
                            string2 = string2;
                            treeMap2 = treeMap2;
                            i8 = i8;
                            i7 = i10;
                            String filename2 = ((ChannelSftp.LsEntry) SFtpServerFileDialogActivity.x.get(i7)).getFilename();
                            String lowerCase = filename2.toLowerCase();
                            String str2 = sFtpServerFileDialogActivity2.f3725j;
                            if (str2 != null) {
                                if (lowerCase.endsWith(str2.toLowerCase())) {
                                    treeMap2 = treeMap2;
                                    treeMap2.put(filename2, new y2(filename2, MainActivity.K0, 0L, false, ((ChannelSftp.LsEntry) SFtpServerFileDialogActivity.x.get(i7)).getAttrs().getSize(), 6));
                                }
                                j7 = 0;
                            } else {
                                treeMap2 = treeMap2;
                                j7 = 0;
                                treeMap2.put(filename2, new y2(filename2, MainActivity.K0, 0L, false, ((ChannelSftp.LsEntry) SFtpServerFileDialogActivity.x.get(i7)).getAttrs().getSize(), 6));
                            }
                            i9 = i7 + 1;
                            i8 = i8;
                            string2 = string2;
                            j8 = j7;
                        }
                        treeMap2 = treeMap2;
                        j7 = 0;
                        i9 = i7 + 1;
                        i8 = i8;
                        string2 = string2;
                        j8 = j7;
                    }
                    str = string2;
                    int i11 = i8;
                    if (sFtpServerFileDialogActivity2.f3736v) {
                        SFtpServerFileDialogActivity.f3722y.addAll(m2.f3053s);
                    }
                    SFtpServerFileDialogActivity.f3722y.addAll(treeMap.tailMap(XmlPullParser.NO_NAMESPACE).values());
                    SFtpServerFileDialogActivity.f3722y.addAll(treeMap2.tailMap(XmlPullParser.NO_NAMESPACE).values());
                    sFtpServerFileDialogActivity2.f3726k = new SimpleAdapter(sFtpServerFileDialogActivity2, sFtpServerFileDialogActivity2.f3724i, R.layout.file_dialog_ftp_row, new String[]{"key", "image", "size"}, new int[]{R.id.fd_rowtext, R.id.fd_rowimage, R.id.fdrow_date});
                    v2.e("GPS-M", "===> showRemoteDir 2: ");
                    if (sFtpServerFileDialogActivity2.f3736v) {
                        Iterator<y2> it = m2.f3053s.iterator();
                        while (it.hasNext()) {
                            sFtpServerFileDialogActivity2.a(it.next(), R.drawable.ic_map_black_36dp);
                        }
                    }
                    Iterator it2 = treeMap.tailMap(XmlPullParser.NO_NAMESPACE).values().iterator();
                    while (it2.hasNext()) {
                        sFtpServerFileDialogActivity2.a((y2) it2.next(), i11);
                    }
                    Iterator it3 = treeMap2.tailMap(XmlPullParser.NO_NAMESPACE).values().iterator();
                    while (it3.hasNext()) {
                        sFtpServerFileDialogActivity2.a((y2) it3.next(), R.drawable.ic_map_black_36dp);
                    }
                    StringBuilder sbA = android.support.v4.media.b.a("===> showRemoteDir 3: ");
                    sbA.append(sFtpServerFileDialogActivity2.f3724i.size());
                    sbA.append(" ");
                    sbA.append(sFtpServerFileDialogActivity2.f3726k.getCount());
                    v2.e("GPS-M", sbA.toString());
                    sFtpServerFileDialogActivity2.f3728n.setProgress(0);
                    sFtpServerFileDialogActivity2.f3729o.setVisibility(8);
                    sFtpServerFileDialogActivity2.f3737w.setAdapter((ListAdapter) sFtpServerFileDialogActivity2.f3726k);
                    sFtpServerFileDialogActivity2.f3737w.setSelection(0);
                    sFtpServerFileDialogActivity2.f3726k.notifyDataSetChanged();
                    sFtpServerFileDialogActivity2.f3737w.setOnItemClickListener(new l3(sFtpServerFileDialogActivity2));
                    sFtpServerFileDialogActivity2.f3736v = false;
                }
                SFtpServerFileDialogActivity sFtpServerFileDialogActivity3 = SFtpServerFileDialogActivity.this;
                sFtpServerFileDialogActivity3.f3723h.setText(sFtpServerFileDialogActivity3.getText(R.string.select_map_to_dwld));
                Vector vector3 = SFtpServerFileDialogActivity.x;
            } else {
                str = string2;
            }
            if (string.equals("SFTP_DOWNLOAD") && str.equals("OK")) {
                v2.e("GPS-M", "==> sFTP download ended");
                SFtpServerFileDialogActivity.this.f3729o.setVisibility(8);
            }
        }
    }

    public static void d(Context context, String resourceType, String fileName, int destinationDirType) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
        String downloadUrl = MapRenderPreferences.buildDownloadUrl(context, resourceType, fileName);
        v2.e("GPS-M", "Enqueueing map download: " + downloadUrl);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        request.setTitle(fileName);
        request.setNotificationVisibility(1);
        request.setDestinationUri(Uri.fromFile(MainActivity.X(context, fileName, destinationDirType)));
        z = downloadManager.enqueue(request);
        A = true;
    }

    public void AbortFileOp(View view) {
        ((DownloadManager) getSystemService("download")).remove(z);
        this.f3729o.setVisibility(8);
    }

    public final void a(y2 y2Var, int i7) {
        String strD = y2.d(y2Var.f3265l.longValue());
        HashMap<String, Object> map = new HashMap<>();
        map.put("key", y2Var.f3262i);
        map.put("size", strD);
        map.put("image", Integer.valueOf(i7));
        this.f3724i.add(map);
    }

    public final void b(String str) {
        Snackbar.make(findViewById(R.id.my_snack_coordinator_layout), str, Snackbar.LENGTH_SHORT).show();
        v2.e("GPS-M", "Snack:" + str);
    }

    public boolean c(String str) {
        v2.e("GPS-M", "Change Dir");
        com.giobat.AgpsTrackerPP.b bVar = this.f3735u;
        bVar.f3758j = "ACTION_SFTP_CHANGE_DIR";
        bVar.f3759k = str;
        bVar.f3760l = XmlPullParser.NO_NAMESPACE;
        bVar.m = XmlPullParser.NO_NAMESPACE;
        Future futureSubmit = Executors.newSingleThreadExecutor().submit(this.f3735u);
        try {
            v2.e("GPS-M", "Change Dir ");
            futureSubmit.get();
            v2.e("GPS-M", "Change Dir Dopo");
            return true;
        } catch (Exception e8) {
            b("Change Dir Error:" + e8);
            return false;
        }
    }

    public void onClickGoBack(View view) {
        m2.f3055u = true;
        setResult(-1, getIntent());
        finish();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.file_dialog_ftp_main);
        this.f3737w = (ListView) findViewById(R.id.list_ftp);
        setResult(0, getIntent());
        this.f3723h = (TextView) findViewById(R.id.ftp_archive_title);
        this.f3725j = getIntent().getStringExtra("FORMAT_FILTER");
        String str = AgpsApplication.f3577t;
        this.m = str;
        MainActivity.K0 = str;
        f3722y = new ArrayList<>();
        this.f3724i = new ArrayList<>();
        v2.e("GPS-M", "==> sFTP File Dialog started");
        this.f3734t = new com.giobat.AgpsTrackerPP.b(this);
        this.f3735u = new com.giobat.AgpsTrackerPP.b(this);
        f3722y.clear();
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.ftp_progress_bar);
        this.f3728n = progressBar;
        progressBar.setScaleY(3.0f);
        IntentFilter intentFilter = new IntentFilter("SFTP_OP_RESULT");
        this.f3727l = new b(this, null);
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(this).registerReceiver(this.f3727l, intentFilter);
        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.sftp_connect_progress);
        this.f3729o = progressBar2;
        progressBar2.setVisibility(0);
        this.f3729o.isIndeterminate();
        this.f3736v = true;
        this.f3731q = (TextView) findViewById(R.id.progressValue);
        this.f3730p = (TextView) findViewById(R.id.progressValPercent);
        this.f3732r = (TextView) findViewById(R.id.progressTitle);
        this.f3733s = findViewById(R.id.progress_window_downld);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new a(handler), 500L);
        this.f3726k = new SimpleAdapter(this, this.f3724i, R.layout.file_dialog_ftp_row, new String[]{"key", "image", "size"}, new int[]{R.id.fd_rowtext, R.id.fd_rowimage, R.id.fdrow_date});
        String str2 = this.m;
        this.f3723h.setText(getText(R.string.connection_to_server));
        com.giobat.AgpsTrackerPP.b bVar = this.f3734t;
        bVar.f3758j = "ACTION_SFTP_CONNECT";
        bVar.f3759k = str2;
        bVar.f3760l = XmlPullParser.NO_NAMESPACE;
        bVar.m = XmlPullParser.NO_NAMESPACE;
        Future futureSubmit = Executors.newSingleThreadExecutor().submit(this.f3734t);
        try {
            v2.e("GPS-M", "Connect prima");
            futureSubmit.get();
            v2.e("GPS-M", "Connect Dopo");
        } catch (Exception e8) {
            b("Connection Error" + e8);
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(this).unregisterReceiver(this.f3727l);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
    }
}
