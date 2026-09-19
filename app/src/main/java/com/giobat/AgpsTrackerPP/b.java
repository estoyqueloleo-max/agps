package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.widget.d;
import c2.v2;
import c2.y2;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpProgressMonitor;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.Callable;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class b implements Callable<Integer> {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static boolean f3748o = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f3749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f3750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public y2 f3751c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public y2 f3752d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f3753e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ChannelSftp f3755g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f3758j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f3759k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f3760l;
    public String m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Context f3761n;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f3754f = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3756h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3757i = 1;

    public class a implements SftpProgressMonitor {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3764c = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f3763b = 0;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f3762a = 1000;

        public a() {
            b.this.f3749a = 0.0d;
            b.this.f3750b = 0L;
        }

        @Override // com.jcraft.jsch.SftpProgressMonitor
        public boolean count(long j7) {
            long j8 = this.f3763b + j7;
            this.f3763b = j8;
            b bVar = b.this;
            bVar.f3750b = j8;
            double d8 = (j8 * 100) / this.f3762a;
            bVar.f3749a = d8;
            boolean z = bVar.f3756h;
            if (!z) {
                bVar.f3757i = 2;
            }
            int i7 = (int) d8;
            if (i7 != this.f3764c) {
                this.f3764c = i7;
            }
            return !z;
        }

        @Override // com.jcraft.jsch.SftpProgressMonitor
        public void end() {
            b bVar = b.this;
            if (bVar.f3756h) {
                bVar.f3757i = 4;
            } else {
                bVar.f3757i = 3;
            }
            bVar.f3756h = false;
        }

        @Override // com.jcraft.jsch.SftpProgressMonitor
        public void init(int i7, String str, String str2, long j7) {
            v2.e("GPS-M", "STARTING: " + i7 + " " + str + " -> " + str2 + " total: " + j7);
            this.f3762a = j7;
            this.f3763b = 0L;
            b bVar = b.this;
            bVar.f3749a = 0.0d;
            bVar.f3750b = 0L;
            bVar.f3756h = false;
        }
    }

    public b(Context context) {
        Math.random();
        Math.random();
        this.f3761n = context;
    }

    public static String c(File file, String str) {
        v2.e("GPS-M", "Searching in demsList.txt file=" + str);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.endsWith(str)) {
                    return line.substring(2);
                }
            }
            return "NOT_FOUND";
        } catch (Exception unused) {
            v2.e("GPS-M", "Can't read file: " + file);
            return "NOT_FOUND";
        }
    }

    public final void a(String str, String str2, String str3) throws JSchException {
        Session session = new JSch().getSession(str, str2, 22);
        Properties properties = new Properties();
        properties.put("StrictHostKeyChecking", "no");
        session.setTimeout(10000);
        session.setConfig(properties);
        session.setPassword(str3);
        session.connect();
        Channel channelOpenChannel = session.openChannel("sftp");
        channelOpenChannel.connect();
        this.f3755g = (ChannelSftp) channelOpenChannel;
    }

    public final void b(y2 y2Var, y2 y2Var2) throws Exception {
        this.f3750b = 0L;
        StringBuilder sbA = android.support.v4.media.b.a("==> sFTP download started: ");
        sbA.append(y2Var.f3262i);
        v2.e("GPS-M", sbA.toString());
        Context context = this.f3761n;
        StringBuilder sbA2 = android.support.v4.media.b.a("GpxTemp/");
        sbA2.append(y2Var2.f3262i);
        File fileX = MainActivity.X(context, sbA2.toString(), 3);
        this.f3755g.get(y2Var.a(), new BufferedOutputStream(new FileOutputStream(fileX)), this.f3753e);
        Long lValueOf = Long.valueOf(this.f3753e.f3762a);
        y2Var.f3265l = lValueOf;
        if (y2Var2.m != 3) {
            if (lValueOf.longValue() == fileX.length()) {
                try {
                    MainActivity.L(fileX, MainActivity.X(this.f3761n, y2Var2.a(), this.f3752d.m));
                } catch (Exception unused) {
                    StringBuilder sbA3 = android.support.v4.media.b.a("Can't copy file:");
                    sbA3.append(y2Var2.a());
                    v2.e("GPS-M", sbA3.toString());
                }
            }
            fileX.delete();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v30, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v38, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v7 */
    @Override // java.util.concurrent.Callable
    public Integer call() {
        String str = "";
        String strEquals = "";
        String str2 = "OK";
        this.f3753e = new a();
        this.f3749a = 0.0d;
        this.f3750b = 0L;
        this.f3757i = 2;
        v2.e("GPS-M", "==> sFTP onHandleIntent");
        int i7 = 1;
        if ("ACTION_SFTP_CONNECT".equals(this.f3758j)) {
            f3748o = false;
            v2.e("GPS-M", "==> SFTP Connection started");
            try {
                boolean z = AgpsApplication.f3566h;
                a(BuildConfig.SFTP_USER, BuildConfig.SFTP_HOST, BuildConfig.SFTP_PASS);
                ChannelSftp channelSftp = this.f3755g;
                if (channelSftp != null && channelSftp.isConnected()) {
                    v2.e("GPS-M", "==> SFTP Connection OK");
                    LocalBroadcastManager.getInstance(this.f3761n).sendBroadcast(new Intent("SFTP_OP_RESULT").putExtra("STATUS", "OK").putExtra("OP", "ACTION_SFTP_CONNECT"));
                }
            } catch (Exception e8) {
                v2.e("GPS-M", "==> SFTP Connection NOT OK: " + e8);
                LocalBroadcastManager.getInstance(this.f3761n).sendBroadcast(new Intent("SFTP_OP_RESULT").putExtra("STATUS", "ERROR").putExtra("STATUS", "ACTION_SFTP_CONNECT"));
            }
        } else if ("ACTION_SFTP_CHANGE_DIR".equals(this.f3758j)) {
            String str3 = this.f3759k;
            v2.e("GPS-M", "===> SFTP Change Dir To: " + str3);
            try {
                ChannelSftp channelSftp2 = this.f3755g;
                if (channelSftp2 == null || !channelSftp2.isConnected()) {
                    boolean z7 = AgpsApplication.f3566h;
                    a(BuildConfig.SFTP_USER, BuildConfig.SFTP_HOST, BuildConfig.SFTP_PASS);
                }
                ChannelSftp channelSftp3 = this.f3755g;
                if (channelSftp3 != null && channelSftp3.isConnected()) {
                    this.f3755g.cd(str3);
                    SFtpServerFileDialogActivity.x = this.f3755g.ls(str3);
                    v2.e("GPS-M", "===> SFTP Dir Size: " + String.valueOf(SFtpServerFileDialogActivity.x.size()));
                    LocalBroadcastManager.getInstance(this.f3761n).sendBroadcast(new Intent("SFTP_OP_RESULT").putExtra("STATUS", "OK").putExtra("OP", "ACTION_SFTP_CHANGE_DIR"));
                }
            } catch (Exception e9) {
                v2.e("GPS-M", "===> SFTP getDirViaFtp error:" + e9);
                LocalBroadcastManager.getInstance(this.f3761n).sendBroadcast(new Intent("SFTP_OP_RESULT").putExtra("STATUS", "ERROR").putExtra("OP", "ACTION_SFTP_CHANGE_DIR"));
            }
            Vector vector = SFtpServerFileDialogActivity.x;
        } else if ("SFTP_DOWNLOAD".equals(this.f3758j)) {
            String str4 = this.f3759k;
            String str5 = this.f3760l;
            String str6 = this.m;
            this.f3751c = new y2(str6, str4, 0L, false, 0L, 6);
            boolean z8 = MainActivity.I0;
            int iLastIndexOf = str6.lastIndexOf(46);
            this.f3752d = new y2(str6, str5, 0L, false, 0L, ActivityInternalFileDialog.y(iLastIndexOf > 0 ? str6.substring(iLastIndexOf + 1) : XmlPullParser.NO_NAMESPACE));
        } else if ("SFTP_SD_DEM".equals(this.f3758j)) {
            String str7 = this.f3759k;
            String str8 = this.f3760l;
            this.f3751c = new y2("TBD", str7, 0L, false, 0L, 6);
            this.f3752d = new y2(str8, "GpxTemp", 0L, false, 0L, 3);
            this.f3754f = 1L;
            try {
                ChannelSftp channelSftp4 = this.f3755g;
                if (channelSftp4 == null || !channelSftp4.isConnected()) {
                    boolean z9 = AgpsApplication.f3566h;
                    a(BuildConfig.SFTP_USER, BuildConfig.SFTP_HOST, BuildConfig.SFTP_PASS);
                }
                ChannelSftp channelSftp5 = this.f3755g;
                if (channelSftp5 != null && channelSftp5.isConnected()) {
                    boolean z10 = AgpsApplication.f3566h;
                    y2 y2Var = new y2("demsList.txt", "/home/giobat", 0L, false, 0L, 4);
                    y2 y2Var2 = new y2("demsList.txt", "Lists", 0L, false, 0L, 4);
                    this.f3755g.cd(y2Var.f3263j);
                    Vector vectorLs = this.f3755g.ls(y2Var.f3262i);
                    SFtpServerFileDialogActivity.x = vectorLs;
                    y2Var.f3265l = Long.valueOf(((ChannelSftp.LsEntry) vectorLs.get(0)).getAttrs().getSize());
                    File fileX = MainActivity.X(this.f3761n, y2Var2.a(), 4);
                    if (y2Var.f3265l.longValue() != fileX.length()) {
                        b(y2Var, y2Var2);
                        v2.e("GPS-M", "File  Downloaded: " + y2Var);
                    }
                    this.f3754f = 2L;
                    v2.e("GPS-M", "Starting search of " + this.f3752d.f3262i);
                    String strC = c(fileX, this.f3752d.f3262i);
                    StringBuilder sb = new StringBuilder();
                    str = "==>  Dem Name = ";
                    sb.append("==>  Dem Name = ");
                    sb.append(strC);
                    v2.e("GPS-M", sb.toString());
                    try {
                        if (strC.equals("NOT_FOUND")) {
                            throw new FileNotFoundException("Dem file not found");
                        }
                        this.f3752d.f3263j = "GpxTemp";
                        String str9 = "?";
                        if (strC.contains("SRTMGL1")) {
                            str9 = "SRTM1";
                        } else if (strC.contains("SRTMGL3")) {
                            str9 = "SRTM3";
                        }
                        String str10 = str9 + "/" + strC;
                        y2 y2Var3 = this.f3751c;
                        y2Var3.f3262i = str10;
                        b(y2Var3, this.f3752d);
                        try {
                            if (this.f3752d.e(this.f3761n)) {
                                v2.e("GPS-M", "Unzip OK!!!\n  DEM Loaded");
                                if (MainActivity.X(this.f3761n, this.f3752d.a(), this.f3752d.m).delete()) {
                                    v2.e("GPS-M", "unzip Ok, zipped file deleted.");
                                    str2 = "OK";
                                }
                                LocalBroadcastManager.getInstance(this.f3761n).sendBroadcast(new Intent("SFTP_OP_RESULT").putExtra("STATUS", str2).putExtra("OP", "SFTP_SD_DEM").putExtra("FNAME", this.f3752d.f3262i));
                                this.f3754f = 3L;
                            } else {
                                v2.e("GPS-M", "Unzip failed!!!\n  DEM NOT Loaded");
                                LocalBroadcastManager.getInstance(this.f3761n).sendBroadcast(new Intent("SFTP_OP_RESULT").putExtra("STATUS", "NOT_OK").putExtra("OP", "SFTP_SD_DEM").putExtra("FNAME", this.f3752d.f3262i));
                                this.f3754f = 3L;
                            }
                        } catch (Exception ex) {
                            v2.e("GPS-M", "Unzip failed!!!\n  DEM NOT Loaded: " + ex);
                        }
                    } catch (Exception e11) {
                        v2.e("GPS-M", "===> SFTP Search and Download error: " + e11);
                        this.f3753e.end();
                        LocalBroadcastManager.getInstance(this.f3761n).sendBroadcast(new Intent("SFTP_OP_RESULT").putExtra("STATUS", "ERROR").putExtra("OP", "SFTP_SD_DEM"));
                    }
                }
            } catch (Exception e12) {
                v2.e("GPS-M", "===> SFTP Search and Download error: " + e12);
                LocalBroadcastManager.getInstance(this.f3761n).sendBroadcast(new Intent("SFTP_OP_RESULT").putExtra("STATUS", "ERROR").putExtra("OP", "SFTP_SD_DEM"));
            }
            i7 = 1;
        }
        return Integer.valueOf(i7);
    }

    public boolean d() {
        return this.f3757i == 2;
    }
}
