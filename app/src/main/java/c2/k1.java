package c2;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import com.giobat.AgpsTrackerPP.SFtpServerFileDialogActivity;
import com.google.android.material.snackbar.Snackbar;
import java.io.File;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
public class k1 extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3034a;

    public k1(MainActivity mainActivity) {
        this.f3034a = mainActivity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.DOWNLOAD_COMPLETE")) {
            DownloadManager downloadManager = (DownloadManager) this.f3034a.getSystemService("download");
            DownloadManager.Query query = new DownloadManager.Query();
            long longExtra = intent.getLongExtra("extra_download_id", -1L);
            if (longExtra != SFtpServerFileDialogActivity.z) {
                return;
            }
            query.setFilterById(longExtra);
            Cursor cursorQuery = downloadManager.query(query);
            if (cursorQuery.moveToFirst()) {
                try {
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex("title"));
                    String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("local_uri"));
                    int i7 = cursorQuery.getInt(cursorQuery.getColumnIndex("status"));
                    if (i7 == 1) {
                        cursorQuery.close();
                        return;
                    }
                    if (i7 == 2) {
                        v2.e("GPS-M", "Download running");
                        cursorQuery.close();
                        return;
                    }
                    if (i7 == 4) {
                        cursorQuery.close();
                        return;
                    }
                    if (i7 == 16) {
                        SFtpServerFileDialogActivity.A = false;
                        v2.e("GPS-M", "Error:" + cursorQuery.getInt(cursorQuery.getColumnIndex("reason")));
                        ((Queue) MainActivity.f3625m1.f3170i).add("Error: Download has failed");
                        cursorQuery.close();
                        return;
                    }
                    String path = Uri.parse(string2).getPath();
                    cursorQuery.getColumnIndex("status");
                    if (!path.contains(context.getPackageName())) {
                        cursorQuery.close();
                        return;
                    }
                    SFtpServerFileDialogActivity.A = false;
                    if (path.substring(path.lastIndexOf(46, path.length() - 1)).equalsIgnoreCase(".zip")) {
                        new Thread(new z3(new File(path), context, "map")).start();
                    } else {
                        File file = new File(path);
                        String parent = file.getParent();
                        String name = file.getName();
                        File file2 = new File(parent + "/Maps/" + name.substring(0, name.length() - 4) + ".osm.map");
                        if (file.renameTo(file2)) {
                            v2.e("GPS-M", "OK File downloaded:" + file.getName());
                        } else {
                            v2.e("GPS-M", "Can't rename File downloaded:" + file.getName());
                        }
                        if (MainActivity.f3613a1 != null) {
                            org.mapsforge.core.model.LatLong cVar = new org.mapsforge.core.model.LatLong(MainActivity.f3613a1.getLatitude(), MainActivity.f3613a1.getLongitude());
                            if (file2.exists() && m2.f(cVar, file2.getPath())) {
                                this.f3034a.onClick_focusMyPosition(null);
                            }
                        }
                    }
                    Snackbar.make(this.f3034a.findViewById(R.id.my_snack_coordinator_layout), this.f3034a.getApplicationContext().getString(R.string.download_complete) + ": " + string.substring(0, string.length() - 4), Snackbar.LENGTH_SHORT).show();
                } catch (Exception e8) {
                    androidx.appcompat.widget.d.c("Exception : ", e8, "GPS-M");
                }
            }
            cursorQuery.close();
        }
    }
}
