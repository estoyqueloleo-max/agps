package com.giobat.AgpsTrackerPP;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.UriPermission;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.os.RemoteException;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import org.mapsforge.map.android.rotation.RotateView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.d0;
import androidx.core.content.FileProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import r.g;
import c2.a2;
import c2.a4;
import c2.b1;
import c2.AddGpxTrackResultCallback;
import androidx.documentfile.provider.DocumentFile;
import c2.b4;
import c2.c0;
import c2.c1;
import c2.d1;
import c2.d2;
import c2.e0;
import c2.e1;
import c2.e2;
import c2.f0;
import c2.f1;
import c2.f2;
import c2.g0;
import c2.g2;
import c2.h0;
import c2.h1;
import c2.h2;
import c2.h3;
import c2.i0;
import c2.i1;
import c2.i2;
import c2.j0;
import c2.j1;
import c2.j2;
import c2.j3;
import c2.k0;
import c2.k1;
import c2.k2;
import c2.l0;
import c2.l1;
import c2.m0;
import c2.m1;
import c2.m2;
import c2.n0;
import c2.n1;
import c2.o0;
import c2.p0;
import c2.p2;
import c2.q0;
import c2.q1;
import c2.q3;
import c2.r1;
import c2.s0;
import c2.s1;
import c2.s3;
import c2.t0;
import c2.t1;
import c2.t2;
import c2.u1;
import c2.u2;
import c2.v0;
import c2.v1;
import c2.v2;
import c2.w0;
import c2.w1;
import c2.w2;
import c2.x0;
import c2.x1;
import c2.x3;
import c2.y0;
import c2.y1;
import c2.y2;
import c2.y3;
import c2.z0;
import c2.z1;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.mapsforge.map.android.view.MapView;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    public static double E1;
    public static int F1;
    public static int G1;
    public static org.mapsforge.core.model.LatLong lastCenteredMapPosition;
    public static org.mapsforge.core.model.LatLong I1;
    public static String K0;
    public static float N1;
    public static boolean Q0;
    public static SharedPreferences R0;
    public static Semaphore W0;
    public static org.mapsforge.core.graphics.Bitmap W1;
    public static Semaphore X0;
    public static y3 Y0;
    public static Context Z0;
    public static Uri Z1;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    public static File f3615c1;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    public static Uri f3616d1;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    public static c2.i f3617e1;

    /* JADX INFO: renamed from: f1, reason: collision with root package name */
    public static a4 f3618f1;

    /* JADX INFO: renamed from: l1, reason: collision with root package name */
    public static Uri f3624l1;

    /* JADX INFO: renamed from: m1, reason: collision with root package name */
    public static s3 f3625m1;

    /* JADX INFO: renamed from: n1, reason: collision with root package name */
    public static u2 f3626n1;

    /* JADX INFO: renamed from: p1, reason: collision with root package name */
    public static File f3628p1;

    /* JADX INFO: renamed from: q1, reason: collision with root package name */
    public static Uri f3629q1;

    /* JADX INFO: renamed from: t1, reason: collision with root package name */
    public static int f3632t1;

    /* JADX INFO: renamed from: u1, reason: collision with root package name */
    public static int f3633u1;

    /* JADX INFO: renamed from: v1, reason: collision with root package name */
    public static int f3634v1;

    /* JADX INFO: renamed from: w1, reason: collision with root package name */
    public static int f3635w1;
    public androidx.activity.result.c<Intent> A0;
    public androidx.activity.result.c<Intent> B0;
    public SensorManager C;
    public androidx.activity.result.c<Intent> C0;
    public c2.p D;
    public androidx.activity.result.c<Intent> D0;
    public AdView E;
    public androidx.activity.result.c<Intent> E0;
    public androidx.activity.result.c<Intent> F0;
    public p2 G;
    public j3 G0;
    public c2.r H;
    public View H0;
    public ProgressBar J;
    public b4 K;
    public Object field_R;
    public long U;
    public Menu Z;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public x3 f3640b0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public Sensor f3645g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public Sensor f3646h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public Sensor f3647i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public TextView f3648j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public TextView f3649k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public TextView f3650l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public ProgressBar f3651m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public View f3652n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public Uri f3653o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public File f3654p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public Uri f3655q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public Location f3656r0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public BroadcastReceiver f3659u0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public String f3663w0;
    public String x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public View f3664y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public androidx.activity.result.c<Intent> f3665y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public androidx.activity.result.c<Intent> f3666z0;
    public static boolean initialCenterOnGpsPending = true;
    public static boolean I0 = true;
    public static boolean isAutoCenterMapOnGps = true;
    public static boolean J0 = true;
    public static Location lastCenteredLocation = null;
    /** Minimum distance in meters before triggering a map recenter to avoid tile re-render jitter */
    public static final float MIN_DISTANCE_METERS_FOR_MAP_RECENTER = 3.0f;
    public static boolean L0 = false;
    public static MapView M0 = null;
    public static int N0 = 0;
    public static int O0 = 4;
    public static int P0 = 12345;
    public static boolean S0 = true;
    public static boolean T0 = false;
    public static boolean U0 = true;
    public static boolean V0 = false;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    public static Location f3613a1 = null;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    public static boolean f3614b1 = false;

    /* JADX INFO: renamed from: g1, reason: collision with root package name */
    public static boolean f3619g1 = false;

    /* JADX INFO: renamed from: h1, reason: collision with root package name */
    public static boolean f3620h1 = false;

    /* JADX INFO: renamed from: i1, reason: collision with root package name */
    public static boolean f3621i1 = false;

    /* JADX INFO: renamed from: j1, reason: collision with root package name */
    public static boolean f3622j1 = false;

    /* JADX INFO: renamed from: k1, reason: collision with root package name */
    public static boolean f3623k1 = false;

    /* JADX INFO: renamed from: o1, reason: collision with root package name */
    public static boolean f3627o1 = false;

    /* JADX INFO: renamed from: r1, reason: collision with root package name */
    public static boolean f3630r1 = false;

    /* JADX INFO: renamed from: s1, reason: collision with root package name */
    public static boolean f3631s1 = false;

    /* JADX INFO: renamed from: x1, reason: collision with root package name */
    public static boolean f3636x1 = true;

    /* JADX INFO: renamed from: y1, reason: collision with root package name */
    public static boolean f3637y1 = false;

    /* JADX INFO: renamed from: z1, reason: collision with root package name */
    public static Random f3638z1 = new Random();
    public static double A1 = 45.07774d;
    public static double B1 = 7.5163d;
    public static double C1 = 1000.0d;
    public static int D1 = 0;
    public static boolean H1 = true;
    public static int J1 = 4;
    public static int K1 = -1;
    public static int L1 = -1;
    public static boolean M1 = false;
    public static boolean O1 = true;
    public static String P1 = "WIFI";
    public static String Q1 = "MOBILE";
    public static String R1 = "NO Internet";
    public static String S1 = "---";
    public static List<h3> T1 = new ArrayList();
    public static org.mapsforge.map.layer.overlay.Marker U1 = null;
    public static org.mapsforge.core.model.LatLong V1 = null;
    public static String X1 = "UNZIP_MSG";
    public static String Y1 = "UNZIP_ERROR";

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f3660v = "lastGpx.gpx";

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String f3662w = "tempGpx.gpx";
    public long x = 0;
    public m2 z = null;
    public Handler A = null;
    public int B = 4;
    public boolean F = false;
    public boolean I = true;
    public boolean L = true;
    public LocalBroadcastManager M = null;
    public BroadcastReceiver N = new k();
    public Runnable O = new a();
    public int P = 1;
    public boolean Q = false;
    public int S = 0;
    public boolean T = false;
    public boolean V = false;
    public boolean W = false;
    public ServiceConnection X = new b();
    public int Y = 0;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public boolean f3639a0 = true;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public boolean f3641c0 = false;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public boolean f3642d0 = false;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public float[] f3643e0 = {1.0f, 0.0f, 0.0f};

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public float[] f3644f0 = {1.0f, 0.0f, 0.0f};

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f3657s0 = 0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public int f3658t0 = 0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public boolean f3661v0 = false;

    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java.lang.Runnable
        public void run() {
            x3 x3Var;
            MapView mapView;
            MainActivity mainActivity = MainActivity.this;
            mainActivity.A.postDelayed(mainActivity.O, 1000L);
            MainActivity mainActivity2 = MainActivity.this;
            Objects.requireNonNull(mainActivity2);
            mainActivity2.U = System.currentTimeMillis();
            mainActivity2.x++;
            int i7 = 0;
            if (mainActivity2.F) {
                mainActivity2.F = false;
                mainActivity2.V = false;
                View adView = mainActivity2.findViewById(R.id.adView);
                if (adView != null) {
                    adView.setVisibility(8);
                }
            }
            Button button = (Button) mainActivity2.findViewById(R.id.no_ads_btn);
            if (mainActivity2.V) {
                button.setVisibility(0);
            } else {
                button.setVisibility(8);
            }
            if (mainActivity2.P < 8) {
                StringBuilder sbA = android.support.v4.media.b.a("Step=");
                sbA.append(mainActivity2.P);
                v2.e("GPS-M", sbA.toString());
            }
            if (!AgpsApplication.m && !AgpsApplication.f3571n) {
                ((TextView) mainActivity2.findViewById(R.id.label1)).setText(mainActivity2.getString(R.string.altitude_box) + " [" + MainActivity.y() + "]");
            }
            View viewFindViewById = mainActivity2.findViewById(R.id.compass_view);
            if (AgpsApplication.f3566h && MainActivity.O1) {
                viewFindViewById.setVisibility(0);
            } else {
                viewFindViewById.setVisibility(4);
            }
            if (mainActivity2.L) {
                return;
            }
            int iA = androidx.core.content.ContextCompat.checkSelfPermission(mainActivity2, "android.permission.ACCESS_FINE_LOCATION");
            MainActivity.P0 = iA;
            if (iA != 0) {
                MainActivity.P0 = 12345;
                mainActivity2.L = true;
                mainActivity2.D0.a(new Intent(mainActivity2, (Class<?>) P2ActivitySettingsMain.class), null);
                mainActivity2.A0();
                return;
            }
            if (mainActivity2.P == 1) {
                com.google.android.gms.common.GoogleApiAvailability googleApiAvailability = com.google.android.gms.common.GoogleApiAvailability.getInstance();
                int iD = googleApiAvailability.isGooglePlayServicesAvailable(mainActivity2);
                if (iD != com.google.android.gms.common.ConnectionResult.SUCCESS) {
                    try {
                        if (googleApiAvailability.isUserResolvableError(iD)) {
                            googleApiAvailability.getErrorDialog(mainActivity2, iD, 9000).show();
                        }
                    } catch (Exception unused) {
                    }
                }
                mainActivity2.P = 2;
            }
            if (mainActivity2.P == 2) {
                v2.e("GPS-M", "AGPS-Tracker++ Version :: 1.8.98");
                try {
                    File[] fileArrListFiles = MainActivity.X(MainActivity.Z0.getApplicationContext(), "Logs", 7).listFiles();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    for (int i8 = 0; i8 < fileArrListFiles.length; i8++) {
                        if (((int) (jCurrentTimeMillis - fileArrListFiles[i8].lastModified())) / 86400000 > 10) {
                            fileArrListFiles[i8].delete();
                        }
                    }
                } catch (Exception unused2) {
                }
                try {
                    File[] fileArrListFiles2 = MainActivity.X(MainActivity.Z0.getApplicationContext(), XmlPullParser.NO_NAMESPACE, 8).listFiles();
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    for (int i9 = 0; i9 < fileArrListFiles2.length; i9++) {
                        if (fileArrListFiles2[i9].getName().endsWith(".jpg") && ((int) (jCurrentTimeMillis2 - fileArrListFiles2[i9].lastModified())) / 86400000 > 1) {
                            fileArrListFiles2[i9].delete();
                        }
                    }
                } catch (Exception unused3) {
                }
                if (v2.f3203h) {
                    Toast.makeText(mainActivity2, "*** Log Enabled *** ", 0).show();
                }
                mainActivity2.P = 3;
            }
            if (mainActivity2.P == 3) {
                StringBuilder sbA2 = android.support.v4.media.b.a("Step=");
                sbA2.append(mainActivity2.P);
                v2.e("GPS-M", sbA2.toString());
                mainActivity2.D = c2.p.f3105c;
                mainActivity2.D.a(mainActivity2.getResources().openRawResource(mainActivity2.getResources().getIdentifier("ww15mgh", "raw", mainActivity2.getPackageName())));
                Intent intent = mainActivity2.getIntent();
                String action = intent.getAction();
                Log.i("GPS-M", "Main : Called with Action intent = " + action);
                if (action != null && action.equals("android.intent.action.VIEW")) {
                    Uri data = intent.getData();
                    if (data != null) {
                        v2.e("GPS-M", "viewTrackFileLaunchedByACTION_VIEW : Called with URI = " + data + " Scheme=" + data.getScheme());
                        String strS = mainActivity2.S(data);
                        if (strS != null) {
                            v2.e("GPS-M", "viewTrackFileLaunchedByACTION_VIEW Chosen path = " + strS);
                            if (mainActivity2.B()) {
                                if (strS.endsWith("gpx")) {
                                    DocumentFile aVarF = DocumentFile.fromTreeUri(mainActivity2, MainActivity.f3624l1);
                                    if (mainActivity2.i0(strS, MainActivity.f3624l1) == null) {
                                        v2.e("GPS-M", " ACTION_VIEW gpx file imported: " + strS);
                                        try {
                                            mainActivity2.D(data, aVarF.createFile("*/*", strS));
                                            MainActivity.f3625m1.a(strS + " " + mainActivity2.getApplication().getString(R.string.imported));
                                        } catch (Exception e9) {
                                            androidx.appcompat.widget.d.c("File copy error: ", e9, "GPS-M");
                                        }
                                    } else {
                                        v2.e("GPS-M", " ACTION_VIEW gpx file  " + strS + "already exists, not imported:");
                                        MainActivity.f3625m1.a(mainActivity2.getApplication().getString(R.string.already_exists_not_imported));
                                    }
                                    mainActivity2.n0(data, "GPXfileRD_DONE", 2);
                                    MainActivity.I0 = false;
                                } else if (strS.endsWith("zip")) {
                                    mainActivity2.C0(data);
                                }
                            }
                        }
                    }
                } else if (MainActivity.O("REC_ON", false) && MainActivity.f3615c1.exists() && MainActivity.f3615c1.length() > 1400 && MainActivity.S0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity2);
                    builder.setCancelable(false);
                    long jCurrentTimeMillis3 = System.currentTimeMillis();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.US);
                    StringBuilder sbA3 = android.support.v4.media.b.a("Saved_");
                    sbA3.append(simpleDateFormat.format(Long.valueOf(jCurrentTimeMillis3)));
                    sbA3.append(".gpx");
                    String string = sbA3.toString();
                    builder.setMessage(mainActivity2.getResources().getString(R.string.resume_rec_text) + "\n" + string + "\n\n" + mainActivity2.getResources().getString(R.string.resume_recording));
                    builder.setTitle(mainActivity2.getResources().getString(R.string.resume_rec_title));
                    if (!MainActivity.f3615c1.exists()) {
                        builder.setPositiveButton(mainActivity2.getResources().getString(R.string.ok), new c1(mainActivity2));
                        builder.setNegativeButton(mainActivity2.getResources().getString(R.string.cancel), new d1(mainActivity2));
                        builder.show();
                    } else if (mainActivity2.B()) {
                        try {
                            mainActivity2.D(Uri.fromFile(MainActivity.f3615c1), DocumentFile.fromTreeUri(mainActivity2, MainActivity.f3624l1).createFile("*/*", string));
                        } catch (Exception e10) {
                            androidx.appcompat.widget.d.c("File copy error: ", e10, "GPS-M");
                        }
                        builder.setPositiveButton(mainActivity2.getResources().getString(R.string.ok), new c1(mainActivity2));
                        builder.setNegativeButton(mainActivity2.getResources().getString(R.string.cancel), new d1(mainActivity2));
                        builder.show();
                    }
                }
                mainActivity2.P = 4;
                if (!((LocationManager) mainActivity2.getSystemService("location")).isProviderEnabled("gps")) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(mainActivity2);
                    builder2.setMessage("GPS is disabled. Enable it?").setCancelable(false).setPositiveButton("Enable GPS", new c2.a1(mainActivity2));
                    builder2.setNegativeButton("Cancel", new b1(mainActivity2));
                    builder2.create().show();
                }
            }
            if (mainActivity2.P == 4) {
                try {
                    v2.e("GPS-M", "GetLastLocation");
                    LocationManager lm = (LocationManager) mainActivity2.getSystemService("location");
                    if (lm != null) {
                        Location loc = lm.getLastKnownLocation("gps");
                        if (loc == null) {
                            loc = lm.getLastKnownLocation("network");
                        }
                        if (loc != null && MainActivity.f3613a1 != null) {
                            MainActivity.f3613a1.set(loc);
                            v2.e("GPS-M", "Got last known location");
                        }
                    }
                } catch (SecurityException e11) {
                    MainActivity.f3625m1.a("Location client: " + e11);
                }
                mainActivity2.z0();
                mainActivity2.P = 5;
            }
            if (mainActivity2.P == 5) {
                mainActivity2.P = 8;
            }
            if (mainActivity2.P < 8) {
                return;
            }
            if (MainActivity.L0) {
                mainActivity2.h0();
            }
            if (MainActivity.f3613a1 == null) {
                v2.e("GPS-M", "Step 5 mainMyLocation= null");
                return;
            }
            v2.e("GPS-M", "Step 8---------------------------------------------");
            s3 s3Var = MainActivity.f3625m1;
            String str = ((Queue) s3Var.f3170i).size() > 0 ? (String) ((Queue) s3Var.f3170i).poll() : XmlPullParser.NO_NAMESPACE;
            if (!str.equals(XmlPullParser.NO_NAMESPACE)) {
                mainActivity2.w0(str);
            }
            if (b0.a.a(mainActivity2, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                v2.e("GPS-M", "Step=8: locaion permission not granted");
            }
            org.mapsforge.core.model.LatLong cVar = new org.mapsforge.core.model.LatLong(MainActivity.f3613a1.getLatitude(), MainActivity.f3613a1.getLongitude());
            if (MainActivity.S0 && !mainActivity2.T && MainActivity.f3613a1.getAltitude() == 0.0d && MainActivity.f3613a1.getLongitude() == 0.0d) {
                StringBuilder sbA4 = android.support.v4.media.b.a("...looking for initial localisation lat=");
                sbA4.append(MainActivity.f3613a1.getLatitude());
                sbA4.append("lon=");
                sbA4.append(MainActivity.f3613a1.getLongitude());
                v2.e("GPS-M", sbA4.toString());
                if (mainActivity2.I) {
                    mainActivity2.I = false;
                    mainActivity2.w0(mainActivity2.getResources().getString(R.string.agps_initialisation));
                    return;
                }
                return;
            }
            if (!mainActivity2.T && (MainActivity.f3613a1.getAltitude() != 0.0d || MainActivity.f3613a1.getLongitude() != 0.0d)) {
                if (mainActivity2.M(cVar, true, true)) {
                    AgpsApplication.B = (byte) 17;
                }
                mainActivity2.N();
                mainActivity2.T = true;
            }
            mainActivity2.v0();
            if (MainActivity.O0 != 4) {
                if (MainActivity.initialCenterOnGpsPending || MainActivity.I0) {
                    mainActivity2.centerMapOnCurrentLocation(true);
                    MainActivity.initialCenterOnGpsPending = false;
                    MainActivity.I0 = false;
                } else if ((MainActivity.isAutoCenterMapOnGps || MainActivity.J0) && (mapView = MainActivity.M0) != null) {
                    org.mapsforge.core.model.LatLong currentMapCenter = mapView.getModel().mapViewPosition.getCenter();
                    org.mapsforge.core.model.LatLong lastCenter = MainActivity.lastCenteredMapPosition != null ? MainActivity.lastCenteredMapPosition : MainActivity.I1;
                    if (lastCenter == null || currentMapCenter.equals(lastCenter)) {
                        mainActivity2.centerMapOnCurrentLocation(false);
                    } else {
                        MainActivity.isAutoCenterMapOnGps = false;
                        MainActivity.J0 = false;
                    }
                }
            }
            x3 x3Var2 = AgpsApplication.f3575r;
            if (x3Var2 != null && x3Var2.i() > 0) {
                int i10 = MainActivity.N0;
                MainActivity.N0 = i10 + 1;
                if (i10 % 5 == 0) {
                    AgpsApplication.f3575r.k();
                }
            }
            mainActivity2.H0();
            mainActivity2.z.l(MainActivity.f3613a1);
            String str2 = XmlPullParser.NO_NAMESPACE;
            x3 x3Var3 = AgpsApplication.f3575r;
            if (x3Var3 != null && x3Var3.i() > 0) {
                str2 = mainActivity2.f3641c0 ? AgpsApplication.f3575r.f3245j.f3165o : AgpsApplication.f3575r.f3245j.f3164n;
            }
            ((TextView) mainActivity2.findViewById(R.id.rec_curr_txt)).setText(str2);
            if (AgpsApplication.f3568j) {
                mainActivity2.Q = !mainActivity2.Q;
                View viewFindViewById2 = mainActivity2.findViewById(R.id.track_recorder_button);
                if (mainActivity2.Q) {
                    viewFindViewById2.setBackground(b0.a.c.b(mainActivity2, R.drawable.icon_image_recording_add_red_circle));
                } else {
                    viewFindViewById2.setBackground(b0.a.c.b(mainActivity2, R.drawable.icon_image_recording_button));
                }
                if (AgpsApplication.f3575r != null) {
                    ProgressBar progressBar = (ProgressBar) mainActivity2.findViewById(R.id.rec_progress_bar);
                    if (MainActivity.O0 == 3) {
                        int i11 = IntServLocGpsPP.E;
                        if (i11 >= 0) {
                            progressBar.setProgress(((5 - i11) * 20) - 10);
                        } else {
                            progressBar.setProgress(100);
                        }
                        if (AgpsApplication.f3575r.i() > 0) {
                            mainActivity2.findViewById(R.id.rec_scroll).setVisibility(0);
                        }
                    }
                    View viewFindViewById3 = mainActivity2.findViewById(R.id.point_of_interest_button);
                    if (AgpsApplication.f3575r.i() > 0) {
                        viewFindViewById3.setVisibility(0);
                    } else {
                        viewFindViewById3.setVisibility(4);
                    }
                }
            } else {
                mainActivity2.findViewById(R.id.track_recorder_button).setBackground(b0.a.c.b(mainActivity2, R.drawable.icon_image_recording_button));
                mainActivity2.findViewById(R.id.point_of_interest_button).setVisibility(4);
            }
            View viewFindViewById4 = mainActivity2.findViewById(R.id.current_track_graph_button);
            View viewFindViewById5 = mainActivity2.findViewById(R.id.follow_path_return_checkbox);
            View viewFindViewById6 = mainActivity2.findViewById(R.id.current_track_statistics);
            View viewFindViewById7 = mainActivity2.findViewById(R.id.current_track_color);
            x3 x3Var4 = AgpsApplication.f3575r;
            if (x3Var4 == null || x3Var4.i() <= 1) {
                viewFindViewById4.setVisibility(4);
                viewFindViewById5.setVisibility(4);
                viewFindViewById6.setVisibility(4);
                viewFindViewById7.setVisibility(4);
            } else {
                viewFindViewById4.setVisibility(0);
                viewFindViewById5.setVisibility(0);
                viewFindViewById6.setVisibility(0);
                viewFindViewById7.setVisibility(0);
            }
            View viewFindViewById8 = mainActivity2.findViewById(R.id.focus_my_loaded_track);
            x3 x3Var5 = AgpsApplication.f3574q;
            if (x3Var5 == null || x3Var5.i() <= 0) {
                viewFindViewById8.setVisibility(4);
            } else {
                viewFindViewById8.setVisibility(0);
            }
            if (AgpsApplication.f3570l && (x3Var = AgpsApplication.f3574q) != null && x3Var.i() > 1) {
                AgpsApplication.B = mainActivity2.z.m(AgpsApplication.f3574q);
                AgpsApplication.f3570l = false;
                MainActivity.isAutoCenterMapOnGps = false;
                MainActivity.J0 = false;
            }
            mainActivity2.f0();
            mainActivity2.updateLavagnaVisibility();
            mainActivity2.G0();
            View viewFindViewById9 = mainActivity2.findViewById(R.id.follow_path_monitor);
            if (viewFindViewById9 != null) {
                viewFindViewById9.setVisibility(8);
            }
            View viewWarnings = mainActivity2.findViewById(R.id.follow_path_warnings);
            if (viewWarnings != null) {
                viewWarnings.setVisibility(8);
            }
            boolean isFollowActive = AgpsApplication.m || AgpsApplication.f3571n;
            TextView tvAltitudeLabel = (TextView) mainActivity2.findViewById(R.id.label1);
            if (!isFollowActive) {
                AgpsApplication.f3572o = false;
                if (tvAltitudeLabel != null) {
                    tvAltitudeLabel.setText(mainActivity2.getString(R.string.altitude_box) + " [" + MainActivity.y() + "]");
                    tvAltitudeLabel.setTextColor(0xff000000);
                }
            } else {
                String altitudeUnitLine = mainActivity2.getString(R.string.altitude) + " [" + MainActivity.y() + "]";
                if (AgpsApplication.f3572o) {
                    String alarmBaseString = mainActivity2.getResources().getString(R.string.followPathAlarm);
                    String alarmMsg = AgpsApplication.f3567i ? (alarmBaseString + " >30m") : (alarmBaseString + " >100ft");
                    if (tvAltitudeLabel != null) {
                        tvAltitudeLabel.setText(alarmMsg + "\n" + altitudeUnitLine);
                        tvAltitudeLabel.setTextColor(0xffd32f2f);
                    }
                } else {
                    x3 activeTrack = AgpsApplication.f3571n ? AgpsApplication.f3575r : AgpsApplication.f3574q;
                    if (activeTrack != null && MainActivity.f3613a1 != null && activeTrack.i() > 0) {
                        boolean isReverseMode = AgpsApplication.f3571n;
                        x3.RemainingDistanceResult result = activeTrack.calculateRemainingDistance(
                                MainActivity.f3613a1, isReverseMode, AgpsApplication.lastMatchedFollowTrackIndex);
                        AgpsApplication.lastMatchedFollowTrackIndex = result.closestWaypointIndex;
                        if (result.isDestinationReached) {
                            String reachedStr = mainActivity2.getResources().getString(R.string.follow_path_destination_reached);
                            if (tvAltitudeLabel != null) {
                                tvAltitudeLabel.setText(reachedStr + "\n" + altitudeUnitLine);
                                tvAltitudeLabel.setTextColor(0xff2e7d32);
                            }
                        } else {
                            String distFormatted;
                            if (AgpsApplication.f3567i) {
                                if (result.remainingDistanceMeters >= 1000.0d) {
                                    distFormatted = String.format(java.util.Locale.US, "%.2f km", result.remainingDistanceMeters / 1000.0d);
                                } else {
                                    distFormatted = String.format(java.util.Locale.US, "%.0f m", result.remainingDistanceMeters);
                                }
                            } else {
                                double miles = result.remainingDistanceMeters * 0.000621371d;
                                if (miles >= 0.5d) {
                                    distFormatted = String.format(java.util.Locale.US, "%.2f mi", miles);
                                } else {
                                    distFormatted = String.format(java.util.Locale.US, "%.0f ft", result.remainingDistanceMeters * 3.28084d);
                                }
                            }
                            String remStr = mainActivity2.getResources().getString(R.string.follow_path_remaining, distFormatted);
                            if (tvAltitudeLabel != null) {
                                tvAltitudeLabel.setText(remStr + "\n" + altitudeUnitLine);
                                tvAltitudeLabel.setTextColor(0xff1565c0);
                            }
                        }
                    } else {
                        if (tvAltitudeLabel != null) {
                            String statusPrefix = AgpsApplication.f3571n ? "Track Back..." : "Follow Path...";
                            tvAltitudeLabel.setText(statusPrefix + "\n" + altitudeUnitLine);
                            tvAltitudeLabel.setTextColor(0xff555555);
                        }
                    }
                }
            }
            mainActivity2.I();
            AgpsApplication.B = MainActivity.M0.getModel().mapViewPosition.getZoomLevel();
            TextView textView2 = (TextView) mainActivity2.findViewById(R.id.iso_load);
            boolean z = m2.f3047l;
            textView2.setText(XmlPullParser.NO_NAMESPACE);
            if (m2.f3047l) {
                textView2.setVisibility(0);
                mainActivity2.S = 8;
            } else {
                int i12 = mainActivity2.S;
                mainActivity2.S = i12 - 1;
                if (i12 <= 0) {
                    textView2.setVisibility(4);
                }
            }
            boolean z7 = AgpsApplication.f3566h;
            mainActivity2.D0();
            if (mainActivity2.z.f3061e.d()) {
                mainActivity2.f3652n0.setVisibility(0);
                mainActivity2.f3651m0.setIndeterminate(false);
                mainActivity2.E0();
            } else if (SFtpServerFileDialogActivity.A) {
                mainActivity2.f3652n0.setVisibility(0);
                mainActivity2.f3651m0.setIndeterminate(false);
                mainActivity2.Q(mainActivity2, SFtpServerFileDialogActivity.z);
            } else if (UnzipProgressState.isUnzipping) {
                mainActivity2.f3652n0.setVisibility(0);
                mainActivity2.f3651m0.setIndeterminate(true);
                mainActivity2.I0("unzipping", UnzipProgressState.unzipProgressBytes);
            } else {
                mainActivity2.f3652n0.setVisibility(8);
            }
            ProgressBar progressBar2 = (ProgressBar) mainActivity2.findViewById(R.id.lavagna_track_progress_bar);
            Button button2 = (Button) mainActivity2.findViewById(R.id.lavagna_track_elev_profile);
            if (MainActivity.f3614b1) {
                progressBar2.setVisibility(0);
                if (mainActivity2.B == 1) {
                    button2.setVisibility(4);
                }
            } else {
                progressBar2.setVisibility(8);
                if (mainActivity2.B == 1) {
                    button2.setVisibility(0);
                }
            }
            String strV = MainActivity.V();
            v2.e("GPS-M", "Periodic End T=" + (System.currentTimeMillis() - mainActivity2.U) + "ms " + strV);
            MainActivity.setMapZoomLevel(AgpsApplication.B);
            if (MainActivity.f3637y1) {
                byte b8 = AgpsApplication.B;
                MainActivity.f3637y1 = false;
                mainActivity2.z = new m2(mainActivity2, mainActivity2.getApplication(), false);
                MainActivity.U0 = true;
                mainActivity2.j0(b8);
            }
            View viewFindViewById10 = mainActivity2.findViewById(R.id.zoom_buttons);
            if (MainActivity.f3636x1) {
                viewFindViewById10.setVisibility(0);
            } else {
                viewFindViewById10.setVisibility(8);
            }
            if (MainActivity.f3630r1) {
                mainActivity2.J.setVisibility(0);
                MainActivity.f3630r1 = false;
            }
            if (MainActivity.f3631s1) {
                mainActivity2.J.setVisibility(8);
                MainActivity.f3631s1 = false;
                MainActivity.f3630r1 = false;
            }
        }
    }

    public class b implements ServiceConnection {
        public b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MainActivity mainActivity = MainActivity.this;
            Objects.requireNonNull((IntServLocGpsPP.d) iBinder);
            Objects.requireNonNull(mainActivity);
            MainActivity.this.W = true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MainActivity.this.W = false;
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ com.giobat.AgpsTrackerPP.b f3669h;

        public c(MainActivity mainActivity, com.giobat.AgpsTrackerPP.b bVar) {
            this.f3669h = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            v2.e("GPS-M", "getListFilesFromServer Start");
            Process.setThreadPriority(10);
            com.giobat.AgpsTrackerPP.b bVar = this.f3669h;
            Objects.requireNonNull(bVar);
            try {
                boolean z = AgpsApplication.f3566h;
                bVar.a(BuildConfig.SFTP_USER, BuildConfig.SFTP_HOST, BuildConfig.SFTP_PASS);
                ChannelSftp channelSftp = bVar.f3755g;
                if (channelSftp != null && channelSftp.isConnected()) {
                    for (ChannelSftp.LsEntry lsEntry : (java.util.Vector<ChannelSftp.LsEntry>) bVar.f3755g.ls("/home/giobat")) {
                        String filename = lsEntry.getFilename();
                        boolean z7 = AgpsApplication.f3566h;
                        if (filename.equals("mapsList.txt")) {
                            File fileX = MainActivity.X(bVar.f3761n, "Lists/mapsList.txt", 4);
                            if (!fileX.exists() || lsEntry.getAttrs().getSize() != fileX.length()) {
                                bVar.f3755g.get("/home/giobat/mapsList.txt", new BufferedOutputStream(new FileOutputStream(fileX)), bVar.f3753e);
                                v2.e("GPS-M", "==--> FTP ok maps " + filename + "dwldOK");
                            }
                        } else if (filename.equals("demsList.txt")) {
                            File fileX2 = MainActivity.X(bVar.f3761n, "Lists/demsList.txt", 4);
                            if (!fileX2.exists() || lsEntry.getAttrs().getSize() != fileX2.length()) {
                                bVar.f3755g.get("/home/giobat/demsList.txt", new BufferedOutputStream(new FileOutputStream(fileX2)), bVar.f3753e);
                                v2.e("GPS-M", "==--> FTP ok dems " + filename);
                            }
                        }
                    }
                    bVar.f3755g.cd("/home/giobat");
                }
            } catch (Exception e8) {
                androidx.appcompat.widget.d.c("==> FTP download Error/Cancel: ", e8, "GPS-M");
            }
            v2.e("GPS-M", "getListFilesFromServer End");
        }
    }

    public class PurchaseStatusListener extends p2 {
        public PurchaseStatusListener(Context context) {
            super(context);
        }

        @Override // c2.p2
        public void a(int i7) {
            AdView adView;
            v2.e("GPS-B", "onPurchaseStatusChange OVERRIDE: " + b2.g.c(i7));
            if (i7 == 3) {
                MainActivity.this.V = true;
                v2.e("GPS-B", "Setting button Visible");
                new Thread(new com.giobat.AgpsTrackerPP.a(this)).start();
            } else {
                if (i7 != 2 || (adView = MainActivity.this.E) == null) {
                    return;
                }
                adView.destroy();
                MainActivity.this.E.setVisibility(8);
                v2.e("GPS-B", "--> AdView gone");
            }
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            v2.e("GPS-M", "start------------------------------------------------------");
            MainActivity.this.f3642d0 = true;
            MainActivity.Y0.a();
            MainActivity.this.f3642d0 = false;
            v2.e("GPS-M", "stop--------------------------------------------------------------");
        }
    }

    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            v2.e("GPS-M", "start------------------------------------------------------");
            MainActivity.this.f3642d0 = true;
            MainActivity.Y0.a();
            MainActivity.this.f3642d0 = false;
            v2.e("GPS-M", "stop--------------------------------------------------------------");
        }
    }

    public class g implements DialogInterface.OnClickListener {
        public g(MainActivity mainActivity) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            dialogInterface.dismiss();
        }
    }

    public class h implements DialogInterface.OnClickListener {
        public h() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            MainActivity mainActivity = MainActivity.this;
            String packageName = mainActivity.getPackageName();
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + packageName));
            try {
                mainActivity.startActivity(intent);
            } catch (Exception e8) {
                d0.c("gotoAppSettings error:", e8, MainActivity.f3625m1);
            }
            dialogInterface.dismiss();
        }
    }

    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            try {
                MainActivity.this.H.g(MainActivity.f3628p1, AgpsApplication.f3575r, "tmp", "1.8", "tmp", XmlPullParser.NO_NAMESPACE);
                v2.e("GPS-M", "Gpx periodic save done!");
            } catch (Exception e8) {
                androidx.appcompat.widget.d.c("Gpx SaveAs error:", e8, "GPS-M");
            }
        }
    }

    public class j implements Runnable {
        public j() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            MainActivity.this.g0();
        }
    }

    public class k extends BroadcastReceiver {
        public k() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z;
            if (intent.getAction() == null) {
                return;
            }
            if (intent.getAction().equals("RECEIVE_ACTION_FROM_SERVICE")) {
                if (intent.getExtras() == null) {
                    return;
                }
                String string = intent.getExtras().getString("DATA");
                v2.e("GPS-M", "Received action from Service: " + string);
                if (string.equals("TRIM")) {
                    v2.e("GPS-M", "Richiesta di TRIM!!!");
                    return;
                } else {
                    if (string.equals("WAKEUP")) {
                        v2.e("GPS-M", "Richiesta di WAKE UP");
                        return;
                    }
                    return;
                }
            }
            boolean zEquals = intent.getAction().equals("GPXfileRD_DONE");
            String string2 = XmlPullParser.NO_NAMESPACE;
            if (!zEquals && !intent.getAction().equals("GPXfileRD_DONE_ADD")) {
                if (intent.getAction().equals("POI_ON_MAP")) {
                    double doubleExtra = intent.getDoubleExtra("LATIT", 0.0d);
                    double doubleExtra2 = intent.getDoubleExtra("LONGIT", 0.0d);
                    int iP = MainActivity.this.P(new org.mapsforge.core.model.LatLong(doubleExtra, doubleExtra2));
                    Location location = new Location("Dummy");
                    location.setLatitude(doubleExtra);
                    location.setLongitude(doubleExtra2);
                    location.setAltitude(iP);
                    MainActivity.this.w(location, true, 3);
                    return;
                }
                if (intent.getAction().equals("POI_ON_TRACK")) {
                    double doubleExtra3 = intent.getDoubleExtra("LATIT", 0.0d);
                    double doubleExtra4 = intent.getDoubleExtra("LONGIT", 0.0d);
                    double doubleExtra5 = intent.getDoubleExtra("ALTIT", 0.0d);
                    Location location2 = new Location("Dummy");
                    location2.setLatitude(doubleExtra3);
                    location2.setLongitude(doubleExtra4);
                    location2.setAltitude(doubleExtra5);
                    MainActivity mainActivity = MainActivity.this;
                    boolean z7 = MainActivity.I0;
                    mainActivity.w(location2, true, 2);
                    return;
                }
                if (intent.getAction().equals("POI_LONG_PRESSED_POS")) {
                    try {
                        a4 a4Var = MainActivity.f3618f1;
                        if (a4Var == null || a4Var.f2960d != 3) {
                            return;
                        }
                        MainActivity.this.o0(a4Var);
                        return;
                    } catch (Exception e8) {
                        e8.printStackTrace();
                        d0.c("Broadcast receive error: ", e8, MainActivity.f3625m1);
                        return;
                    }
                }
                if (intent.getAction().equals("UNZIP_CHECK")) {
                    if (intent.hasExtra(MainActivity.X1)) {
                        string2 = intent.getExtras().getString(MainActivity.X1);
                    }
                    Boolean boolValueOf = Boolean.FALSE;
                    if (intent.hasExtra(MainActivity.Y1)) {
                        boolValueOf = Boolean.valueOf(intent.getExtras().getBoolean(MainActivity.Y1));
                    }
                    MainActivity mainActivity2 = MainActivity.this;
                    boolValueOf.booleanValue();
                    Objects.requireNonNull(mainActivity2);
                    v2.e("GPS-I", "-->Unzip Dialog");
                    Uri uri = MainActivity.Z1;
                    String string3 = mainActivity2.getApplication().getResources().getString(R.string.unzip_gpx_title);
                    AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity2);
                    builder.setMessage(string2).setTitle(string3);
                    v2.e("GPS-I", "-->Unzip Dialog ok");
                    builder.setPositiveButton(R.string.ok, new i2(mainActivity2, uri)).setNegativeButton(R.string.cancel, new h2(mainActivity2));
                    builder.create();
                    builder.show();
                    return;
                }
                return;
            }
            Log.i("GPS-M", "GPXfileRD_DONE  %%%%%%%%%%%%%%%%%%%%");
            int i7 = intent.hasExtra("TRACK_TYPE") ? intent.getExtras().getInt("TRACK_TYPE") : 2;
            if (intent.hasExtra("filename")) {
                string2 = intent.getExtras().getString("filename");
            }
            if (intent.hasExtra("resultOK")) {
                Bundle extras = intent.getExtras();
                Objects.requireNonNull(extras);
                z = extras.getBoolean("resultOK");
            } else {
                z = false;
            }
            Log.i("GPS-M", "GPX read broadcast received. File mName=" + string2 + " ResultOK=" + z);
            MainActivity.f3631s1 = true;
            if (!z) {
                MainActivity.this.w0(MainActivity.this.getResources().getString(R.string.cant_read_file) + " " + string2);
                return;
            }
            if (!intent.getAction().equals("GPXfileRD_DONE")) {
                if (intent.getAction().equals("GPXfileRD_DONE_ADD")) {
                    MainActivity mainActivity3 = MainActivity.this;
                    x3 x3Var = AgpsApplication.f3574q;
                    Objects.requireNonNull(mainActivity3);
                    new Thread(new x1(mainActivity3, x3Var)).start();
                    return;
                }
                return;
            }
            if (i7 == 1) {
                x3 x3Var2 = new x3(1);
                AgpsApplication.f3575r = x3Var2;
                MainActivity mainActivity4 = MainActivity.this;
                Objects.requireNonNull(mainActivity4);
                new Thread(new n1(mainActivity4, x3Var2, string2, false)).start();
                return;
            }
            if (i7 == 2) {
                x3 x3Var3 = new x3(2);
                AgpsApplication.f3574q = x3Var3;
                MainActivity mainActivity5 = MainActivity.this;
                Objects.requireNonNull(mainActivity5);
                new Thread(new n1(mainActivity5, x3Var3, string2, true)).start();
                return;
            }
            if (i7 == 3) {
                MainActivity mainActivity6 = MainActivity.this;
                Objects.requireNonNull(mainActivity6);
                new Thread(new f1(mainActivity6, string2)).start();
            } else {
                if (i7 != 4) {
                    return;
                }
                x3 x3Var4 = new x3(1);
                AgpsApplication.f3575r = x3Var4;
                MainActivity mainActivity7 = MainActivity.this;
                Objects.requireNonNull(mainActivity7);
                mainActivity7.runOnUiThread(new g2(mainActivity7, x3Var4, string2));
            }
        }
    }

    public class l implements Runnable {

        public final String f3677h;
        public final DocumentFile f3678i;
        public final x3 f3679j;

        public l(String str, DocumentFile aVar, x3 x3Var) {
            this.f3677h = str;
            this.f3678i = aVar;
            this.f3679j = x3Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                MainActivity.f3630r1 = true;
                Process.setThreadPriority(10);
                v2.e("GPS-M", "Start WriteGpxBatch saveToDocFileAs: " + this.f3677h);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.H.f(this.f3678i, this.f3679j, mainActivity.getString(R.string.app_title), "1.8", this.f3677h);
                MainActivity.f3625m1.a(MainActivity.this.getResources().getString(R.string.resume_rec_text) + " " + this.f3678i.getName());
            } catch (Exception e8) {
                androidx.appcompat.widget.d.c("GPX File Write Exception", e8, "GPS-M");
                boolean z = MainActivity.I0;
                MainActivity.this.w0("GPX File Write Exception" + e8);
            }
            MainActivity.f3631s1 = true;
        }
    }

    public class m implements DialogInterface.OnClickListener {
        public m(MainActivity mainActivity) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            dialogInterface.dismiss();
        }
    }

    public class n implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Location f3681h;

        public n(Location location) {
            this.f3681h = location;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            MainActivity mainActivity = MainActivity.this;
            Location location = this.f3681h;
            Objects.requireNonNull(mainActivity);
            DecimalFormat decimalFormat = new DecimalFormat("###0.0000");
            decimalFormat.format(location.getLatitude());
            decimalFormat.format(location.getLongitude());
            String str = ((Object) mainActivity.getResources().getText(R.string.my_position_coordinates)) + ":\n" + mainActivity.p0();
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", str);
            intent.putExtra("android.intent.extra.SUBJECT", mainActivity.getResources().getText(R.string.my_position_coordinates));
            intent.setType("text/plain");
            mainActivity.startActivity(Intent.createChooser(intent, XmlPullParser.NO_NAMESPACE));
            dialogInterface.dismiss();
        }
    }

    public class o implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ TextView f3683h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ TextView f3684i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ a4 f3685j;

        public o(MainActivity mainActivity, TextView textView, TextView textView2, a4 a4Var) {
            this.f3683h = textView;
            this.f3684i = textView2;
            this.f3685j = a4Var;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            String strValueOf = String.valueOf(this.f3683h.getText());
            String strValueOf2 = String.valueOf(this.f3684i.getText());
            a4 a4Var = this.f3685j;
            a4Var.f2957a = strValueOf;
            a4Var.f2958b = strValueOf2;
            dialogInterface.dismiss();
        }
    }

    public class p implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ a4 f3686h;

        public p(MainActivity mainActivity, a4 a4Var) {
            this.f3686h = a4Var;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            x3 x3Var = AgpsApplication.f3574q;
            if (x3Var != null && x3Var.i() > 1) {
                boolean z = false;
                for (int i8 = 0; i8 < AgpsApplication.f3574q.f3237b.size(); i8++) {
                    if (AgpsApplication.f3574q.f3237b.get(i8).f2957a.equals(this.f3686h.f2957a)) {
                        AgpsApplication.f3574q.f3237b.remove(i8);
                        MainActivity.U0 = true;
                        z = true;
                    }
                }
                if (!z) {
                    for (int i9 = 0; i9 < AgpsApplication.f3575r.f3237b.size(); i9++) {
                        if (AgpsApplication.f3575r.f3237b.get(i9).f2957a.equals(this.f3686h.f2957a)) {
                            AgpsApplication.f3575r.f3237b.remove(i9);
                            MainActivity.U0 = true;
                        }
                    }
                }
            }
            dialogInterface.dismiss();
        }
    }

    public class q implements DialogInterface.OnClickListener {
        public q(MainActivity mainActivity) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            dialogInterface.dismiss();
        }
    }

    public class DialogGpxCoordListener implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ EditText f3687h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ EditText f3688i;

        public DialogGpxCoordListener(EditText editText, EditText editText2) {
            this.f3687h = editText;
            this.f3688i = editText2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            MainActivity.this.f3663w0 = this.f3687h.getText().toString();
            MainActivity.this.x0 = this.f3688i.getText().toString();
            try {
                MainActivity.V1 = new org.mapsforge.core.model.LatLong(Float.valueOf(MainActivity.this.f3663w0).floatValue(), Float.valueOf(MainActivity.this.x0).floatValue());
                Resources resources = MainActivity.this.getResources();
                Resources.Theme theme = MainActivity.this.getTheme();
                MainActivity.W1 = org.mapsforge.map.android.graphics.AndroidGraphicFactory.convertToBitmap(resources.getDrawable(R.drawable.ic_push_pin_red_32dp, theme));
                v2.e("GPS-M", "%  Waypoint: User");
                MainActivity.U0 = true;
                MainActivity.this.f3661v0 = true;
            } catch (Exception e8) {
                androidx.appcompat.widget.d.c("Error:", e8, "GPS-M");
                MainActivity.this.f3661v0 = false;
            }
            dialogInterface.dismiss();
        }
    }

    public class s implements DialogInterface.OnClickListener {
        public s() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            MainActivity.this.f3661v0 = true;
            MainActivity.U0 = true;
            dialogInterface.dismiss();
        }
    }

    public class t implements DialogInterface.OnClickListener {
        public t() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            MainActivity.this.f3661v0 = true;
            MainActivity.V1 = null;
            MainActivity.U0 = true;
            dialogInterface.dismiss();
        }
    }

    public class u implements DialogInterface.OnDismissListener {
        public u() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            MainActivity mainActivity = MainActivity.this;
            if (mainActivity.f3661v0) {
                return;
            }
            mainActivity.G();
        }
    }

    public class v implements Runnable {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Uri f3693h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ String f3694i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ String f3695j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final /* synthetic */ int f3696k;

        public v(Uri uri, String str, String str2, int i7) {
            this.f3693h = uri;
            this.f3694i = str;
            this.f3695j = str2;
            this.f3696k = i7;
        }

        @Override // java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            MainActivity.f3630r1 = true;
            MainActivity mainActivity = MainActivity.this;
            mainActivity.H = new c2.r(mainActivity.getApplicationContext());
            MainActivity.this.H.d(this.f3693h, this.f3694i, this.f3695j, this.f3696k);
            MainActivity.U0 = true;
            MainActivity.f3631s1 = true;
        }
    }

    public static void A(MainActivity mainActivity, String str, String str2, x3 x3Var) {
        Objects.requireNonNull(mainActivity);
        try {
            DocumentFile aVarF = DocumentFile.fromTreeUri(mainActivity, f3624l1);
            DocumentFile aVarI0 = mainActivity.i0(str, f3624l1);
            if (aVarI0 != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                builder.setTitle(mainActivity.getResources().getString(R.string.file_exists)).setMessage(mainActivity.getResources().getString(R.string.replace)).setCancelable(true).setNegativeButton(mainActivity.getResources().getString(R.string.no), new z0(mainActivity)).setPositiveButton(mainActivity.getResources().getString(R.string.yes), new y0(mainActivity, str2, aVarI0, aVarF, str, x3Var));
                builder.create().show();
            } else {
                v2.e("GPS-M", "Save new track Start: " + str2);
                mainActivity.J0(aVarF.createFile("application/*", str), str2, x3Var);
                U0 = true;
                f3630r1 = true;
            }
        } catch (Exception e8) {
            d0.c("SaveGpx Error: ", e8, f3625m1);
        }
    }

    public static void L(File file, File file2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        byte[] bArr = new byte[4096];
        while (true) {
            int i7 = fileInputStream.read(bArr);
            if (i7 <= 0) {
                fileInputStream.close();
                fileOutputStream.close();
                return;
            }
            fileOutputStream.write(bArr, 0, i7);
        }
    }

    public static SharedPreferences getPrefs() {
        if (R0 == null && AgpsApplication.instance != null) {
            R0 = AgpsApplication.instance.getSharedPreferences("MainActivity", 0);
        }
        return R0;
    }

    public static boolean O(String str, boolean z) {
        SharedPreferences prefs = getPrefs();
        if (prefs == null) {
            return z;
        }
        return prefs.getBoolean(str, z);
    }

    public static String R(String str) {
        int iLastIndexOf = str.lastIndexOf("/");
        return (iLastIndexOf == -1 || iLastIndexOf == 0) ? "/" : str.substring(0, str.lastIndexOf("/"));
    }

    public static int T(String str, int i7) {
        SharedPreferences prefs = getPrefs();
        if (prefs == null) {
            return i7;
        }
        return prefs.getInt(str, i7);
    }

    public static double U(double d8) {
        return AgpsApplication.f3567i ? d8 : d8 * 0.621371d;
    }

    public static String V() {
        Runtime runtime = Runtime.getRuntime();
        long jFreeMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1048576;
        long jMaxMemory = (runtime.maxMemory() / 1048576) - jFreeMemory;
        u2 u2Var = f3626n1;
        Objects.requireNonNull(u2Var);
        try {
            u2Var.f3196b.acquire();
        } catch (Exception unused) {
        }
        int size = 0;
        try {
            size = u2Var.f3195a.size();
        } catch (Exception unused2) {
        }
        u2Var.f3196b.release();
        return "Memory Used=" + jFreeMemory + " Heap=" + jMaxMemory + " Layers=" + size;
    }

    public static double W(double d8) {
        return AgpsApplication.f3567i ? d8 : d8 * 3.28084d;
    }

    public static File X(Context context, String str, int i7) {
        if (i7 == 0) {
            return null;
        }
        int i8 = i7 - 1;
        if (i8 != 0 && i8 != 1) {
            if (i8 == 2 || i8 == 3 || i8 == 4) {
                return e0(context, str, 1);
            }
            if (i8 != 6) {
                if (i8 != 7) {
                    return null;
                }
                return e0(context, str, 3);
            }
        }
        return e0(context, str, 2);
    }

    public static String Y(String str) {
        return (str == null || str.length() == 0) ? XmlPullParser.NO_NAMESPACE : str.substring(str.lastIndexOf("/") + 1);
    }

    public static String Z(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return R1;
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities == null) {
            return R1;
        }
        if (networkCapabilities.hasTransport(1)) {
            return P1;
        }
        return networkCapabilities.hasTransport(0) ? Q1 : S1;
    }

    public static String a0(String str) {
        return (str == null || str.length() == 0 || str.lastIndexOf("/") == -1) ? XmlPullParser.NO_NAMESPACE : str.substring(0, str.lastIndexOf("/"));
    }

    public static String b0(String str) {
        String[] strArrSplit = str.split("/");
        return strArrSplit[strArrSplit.length - 1];
    }

    public static String c0(String str, String str2) {
        SharedPreferences prefs = getPrefs();
        if (prefs == null) {
            return str2;
        }
        return prefs.getString(str, str2);
    }

    public static Bitmap d0(Context context, Uri uri) {
        try {
            Bitmap bitmapT = new u0.a(context.getContentResolver().openInputStream(uri)).t();
            if (bitmapT != null) {
                return bitmapT;
            }
            try {
                return ActivityMyPhotoShow.w(context, uri);
            } catch (Exception unused) {
                ((Queue) f3625m1.f3170i).add("Thumbnail not available");
                return null;
            }
        } catch (IOException unused2) {
            ((Queue) f3625m1.f3170i).add("Thumbnail not available");
            return null;
        }
    }

    public static File e0(Context context, String str, int i7) {
        File[] externalFilesDirs = context.getApplicationContext().getExternalFilesDirs(null);
        File externalFilesDir = (f3621i1 && externalFilesDirs.length == 2) ? externalFilesDirs[1] : context.getApplicationContext().getExternalFilesDir(null);
        if (i7 == 0) {
            return new File(context.getApplicationContext().getFilesDir(), str);
        }
        int i8 = i7 - 1;
        if (i8 == 0) {
            return new File(context.getApplicationContext().getFilesDir(), str);
        }
        if (i8 == 1) {
            return new File(externalFilesDir, str);
        }
        if (i8 == 2) {
            return new File(context.getExternalCacheDir(), str);
        }
        v2.e("GPS-M", "Wrong storage type !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return null;
    }

    public static void s0(String str, boolean z) {
        SharedPreferences prefs = getPrefs();
        if (prefs != null) {
            SharedPreferences.Editor editorEdit = prefs.edit();
            editorEdit.putBoolean(str, z);
            editorEdit.apply();
        }
    }

    public static void t0(String str, int i7) {
        SharedPreferences prefs = getPrefs();
        if (prefs != null) {
            SharedPreferences.Editor editorEdit = prefs.edit();
            editorEdit.putInt(str, i7);
            editorEdit.apply();
        }
    }

    public static void setMapZoomLevel(byte b8) {
        MapView mapView = M0;
        if (mapView == null || b8 > 20 || b8 < 2) {
            return;
        }
        mapView.setZoomLevel(b8);
        AgpsApplication.B = b8;
    }

    public static String x() {
        return AgpsApplication.f3567i ? "km" : "mi";
    }

    public static String y() {
        return AgpsApplication.f3567i ? "m" : "ft";
    }

    public static void z(MainActivity mainActivity) {
        Objects.requireNonNull(mainActivity);
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        int iQ = 0;
        builder.setCancelable(false);
        View viewInflate = mainActivity.getLayoutInflater().inflate(R.layout.photo_dialog, (ViewGroup) null);
        builder.setTitle(mainActivity.getResources().getString(R.string.new_photo));
        builder.setView(viewInflate);
        try {
            Bitmap bitmapW = ActivityMyPhotoShow.w(mainActivity, mainActivity.f3655q0);
            if (bitmapW != null) {
                ImageView imageView = (ImageView) viewInflate.findViewById(R.id.photo_thumbnail);
                imageView.setImageBitmap(bitmapW);
                try {
                    iQ = new u0.a(mainActivity.f3654p0).q();
                } catch (Exception unused) {
                }
                imageView.setRotation(iQ);
            }
            EditText editText = (EditText) viewInflate.findViewById(R.id.photo_description);
            mainActivity.getContentResolver();
            builder.setPositiveButton(mainActivity.getResources().getString(R.string.ok), new e1(mainActivity, editText));
            builder.setNegativeButton(mainActivity.getResources().getString(R.string.cancel), new c2.g1(mainActivity));
            builder.show();
        } catch (Exception unused2) {
        }
    }

    public void A0() {
        Handler handler = this.A;
        if (handler != null) {
            handler.removeCallbacks(this.O);
        }
    }

    public void AbortFileOp(View view) {
        this.z.f3061e.f3756h = true;
    }

    public boolean B() {
        boolean z;
        Iterator<UriPermission> it = getContentResolver().getPersistedUriPermissions().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            UriPermission next = it.next();
            if (next.isReadPermission() && next.isWritePermission()) {
                f3624l1 = next.getUri();
                z = true;
                break;
            }
        }
        if (!z) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            c0.a(this, R.string.open_gpx_dir_title, builder.setMessage(getApplication().getResources().getString(R.string.open_gpx_dir_msg))).setPositiveButton(R.string.ok, new j2(this));
            AlertDialog alertDialogCreate = builder.create();
            alertDialogCreate.show();
            ((TextView) alertDialogCreate.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
        }
        try {
            DocumentFile aVarC = DocumentFile.fromTreeUri(this, f3624l1).createFile("application/*", "probeTest");
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(getContentResolver().openOutputStream(aVarC.getUri())));
            bufferedWriter.write("This is a probe test: writing");
            bufferedWriter.close();
            return aVarC.delete();
        } catch (Exception e8) {
            androidx.appcompat.widget.d.c("gpx dir probing not successful!!!: ", e8, "GPS-I");
            return false;
        }
    }

    public boolean B0(File file, Location location, String str) {
        if (file != null && location != null) {
            try {
                u0.a aVar = new u0.a(file);
                aVar.N(location);
                if (str == null) {
                    str = "...";
                }
                aVar.M("UserComment", str);
                aVar.I();
                return true;
            } catch (IOException e8) {
                f3625m1.a("Exif Error:" + e8);
            }
        }
        return false;
    }

    public boolean C(File file, Uri uri) {
        if (file == null || uri == null) {
            return false;
        }
        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = getContentResolver().openOutputStream(uri)) {
            if (out == null) {
                return false;
            }
            byte[] buf = new byte[8192];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            return true;
        } catch (Exception e) {
            f3625m1.a("copyFile2Uri: " + e);
            return false;
        }
    }

    public void C0(Uri uri) {
        boolean z;
        Z1 = uri;
        b4 b4Var = this.K;
        b4Var.f2967c = XmlPullParser.NO_NAMESPACE;
        try {
            ZipInputStream zipInputStream = new ZipInputStream(getContentResolver().openInputStream(uri));
            v2.e("GPS-I", "Unzipping : " + uri.getPath());
            z = false;
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    break;
                }
                String name = nextEntry.getName();
                b4Var.f2967c += "\n • " + nextEntry.getName();
                if (!name.endsWith(".gpx") && !name.endsWith(".jpg")) {
                    z = true;
                }
                v2.e("GPS-I", "In this ZIP: " + nextEntry.getName() + " Size=" + nextEntry.getSize());
                String name2 = nextEntry.getName();
                getContentResolver();
                DocumentFile aVarF = DocumentFile.fromTreeUri(this, f3624l1);
                DocumentFile aVarE = aVarF != null ? aVarF.findFile("AgpsTrackerPhoto") : null;
                if (name2.endsWith(".jpg")) {
                    if (aVarE != null && aVarE.findFile(name2) != null) {
                        b4Var.f2967c += "\n ---->" + getString(R.string.file_exists) + "\n";
                    }
                } else if (!name2.endsWith(".gpx")) {
                    b4Var.f2967c += "\n" + getString(R.string.unzip_gpx_msg_error) + "\n";
                    z = true;
                } else if (aVarF != null && aVarF.findFile(name2) != null) {
                    b4Var.f2967c += "\n ---->" + getString(R.string.file_exists) + "\n";
                }
            }
            zipInputStream.close();
        } catch (Exception e8) {
            androidx.appcompat.widget.d.c("Unzip exception: ", e8, "GPS-I");
            z = true;
        }
        if (z) {
            f3625m1.a(getResources().getString(R.string.error) + ":" + getResources().getString(R.string.unzip_gpx_msg_error));
            f3631s1 = true;
            return;
        }
        StringBuilder sbA = new StringBuilder(getApplication().getResources().getString(R.string.unzip_gpx_msg) + "\n");
        sbA.append(this.K.f2967c);
        String string = sbA.toString();
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.setAction("UNZIP_CHECK");
        intent.putExtra(X1, string);
        intent.putExtra(Y1, z);
        v2.e("GPS-I", "-->LocalBroadcast UNZIP_CHECK");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void D(Uri uri, DocumentFile aVar) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(getContentResolver().openOutputStream(aVar.getUri())));
        InputStream inputStreamOpenInputStream = getContentResolver().openInputStream(uri);
        Objects.requireNonNull(inputStreamOpenInputStream);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpenInputStream));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                bufferedReader.close();
                bufferedWriter.close();
                return;
            }
            bufferedWriter.write(line);
        }
    }

    public void D0() {
        if (O1) {
            ((CompassView) findViewById(R.id.compass_view)).postInvalidate();
        }
    }

    public void E(File file) {
        if (file.exists()) {
            StringBuilder sbA = android.support.v4.media.b.a("Dir already exists: ");
            sbA.append(file.getName());
            v2.e("GPS-M", sbA.toString());
        } else if (file.mkdirs()) {
            StringBuilder sbA2 = android.support.v4.media.b.a("Dir created: ");
            sbA2.append(file.getName());
            v2.e("GPS-M", sbA2.toString());
        } else {
            StringBuilder sbA3 = android.support.v4.media.b.a("Dir creation Error: ");
            sbA3.append(file.getName());
            v2.e("GPS-M", sbA3.toString());
        }
    }

    public void E0() {
        DecimalFormat decimalFormat = new DecimalFormat("##0.0");
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.ftp_progress_bar);
        progressBar.setScaleY(3.0f);
        com.giobat.AgpsTrackerPP.b bVar = this.z.f3061e;
        int i7 = (int) (bVar.d() ? bVar.f3754f : 99L);
        if (i7 == 0) {
            getResources().getString(R.string.search_dem);
            progressBar.setProgress(0);
        } else if (i7 == 1) {
            progressBar.setProgress(0);
            getResources().getString(R.string.search_dem);
        } else if (i7 == 2) {
            getResources().getString(R.string.downloading_dem);
            progressBar.setProgress((int) this.z.f3061e.f3749a);
        } else {
            if (i7 != 3) {
                if (i7 != 99) {
                    this.f3650l0.setText("???");
                    return;
                }
                return;
            }
            getResources().getString(R.string.dem_loaded);
        }
        y2 y2Var = this.z.f3061e.f3752d;
        String str = y2Var != null ? y2Var.f3262i : "?";
        this.f3650l0.setText(getResources().getString(R.string.downloading_dem) + ": " + str.substring(0, str.indexOf(".")));
        StringBuilder sb = new StringBuilder();
        sb.append(decimalFormat.format(this.z.f3061e.f3749a));
        sb.append("%");
        this.f3648j0.setText(sb.toString());
        this.f3649k0.setText(y2.d(this.z.f3061e.f3750b));
    }

    public final void F() {
        File[] externalFilesDirs = getApplicationContext().getExternalFilesDirs(null);
        File file = new File(getApplicationContext().getExternalFilesDir(null), "Maps");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (f3621i1 && externalFilesDirs.length == 2) {
            File file2 = new File(externalFilesDirs[1], "Maps");
            if (!file2.exists()) {
                file2.mkdirs();
            }
        }
        File file3 = new File(getApplicationContext().getExternalFilesDir(null), "Dems");
        if (!file3.exists()) {
            file3.mkdirs();
        }
        File file4 = new File(getApplicationContext().getExternalFilesDir(null), "Logs");
        if (!file4.exists()) {
            file4.mkdirs();
        }
        if (f3621i1 && externalFilesDirs.length == 2) {
            File file5 = new File(externalFilesDirs[1], "Dems");
            if (!file5.exists()) {
                file5.mkdirs();
            }
        }
        E(X(this, "GpxTemp", 3));
        E(X(this, "ZipTemp", 3));
        E(X(this, "Lists", 4));
        E(X(this, "World", 5));
        try {
            File oldMapsDir = new File("/storage/emulated/0/Android/data/com.giobat.AgpsTrackerPP/files/Maps");
            if (oldMapsDir.exists() && oldMapsDir.isDirectory()) {
                File[] oldFiles = oldMapsDir.listFiles();
                if (oldFiles != null && oldFiles.length > 0) {
                    for (File oldMap : oldFiles) {
                        File target = new File(file, oldMap.getName());
                        if (!target.exists() && oldMap.canRead()) {
                            try {
                                MainActivity.L(oldMap, target);
                            } catch (Exception ignored) {
                            }
                        }
                    }
                }
            }
        } catch (Throwable ignored) {
        }
    }

    public void F0(int i7, String str, long j7) {
        if (i7 == 0) {
            this.f3651m0.setIndeterminate(true);
            this.f3650l0.setText(str);
            this.f3648j0.setText(XmlPullParser.NO_NAMESPACE);
            this.f3649k0.setText(XmlPullParser.NO_NAMESPACE);
            return;
        }
        this.f3651m0.setIndeterminate(false);
        DecimalFormat decimalFormat = new DecimalFormat("##0.0");
        this.f3651m0.setScaleY(3.0f);
        this.f3651m0.setProgress(i7);
        this.f3652n0.setVisibility(0);
        this.f3650l0.setText(str);
        this.f3648j0.setText(decimalFormat.format(i7) + "%");
        this.f3649k0.setText(y2.d(j7));
    }

    public final void G() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewInflate = getLayoutInflater().inflate(R.layout.input_lat_lon_dialog, (ViewGroup) null);
        builder.setTitle(getString(R.string.marker_red_pin));
        builder.setView(viewInflate);
        builder.setMessage(R.string.marker_red_pin_msg);
        EditText editText = (EditText) viewInflate.findViewById(R.id.latitude_val);
        EditText editText2 = (EditText) viewInflate.findViewById(R.id.longitude_val);
        if (V1 != null) {
            editText.setText(this.f3663w0);
            editText2.setText(this.x0);
        }
        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogGpxCoordListener(editText, editText2));
        builder.setNegativeButton(getResources().getString(R.string.cancel), new s());
        builder.setNeutralButton(getResources().getString(R.string.delete), new t());
        builder.setOnDismissListener(new u());
        builder.show();
    }

    public void G0() {
        String str;
        double d8;
        float f7;
        double d9;
        ScalebarView scalebarView = (ScalebarView) findViewById(R.id.custom_drawable_scale_view);
        TextView textView = (TextView) findViewById(R.id.scale_text_value);
        MapView mapView = M0;
        byte bZ = 0;
        String str2 = XmlPullParser.NO_NAMESPACE;
        if (mapView != null) {
            Objects.requireNonNull(scalebarView);
            if (mapView.getModel().mapViewPosition.getZoomLevel() > 1) {
                org.mapsforge.core.model.LatLong cVarA = mapView.getMapViewProjection().fromPixels(0.0d, ScalebarView.f3743n / 2);
                org.mapsforge.core.model.LatLong cVarA2 = mapView.getMapViewProjection().fromPixels(ScalebarView.m, ScalebarView.f3743n / 2);
                if (cVarA == null || cVarA2 == null) {
                    str2 = "?";
                } else {
                    Location location = new Location("l1");
                    Location location2 = new Location("l2");
                    location.setLatitude(cVarA.latitude);
                    location.setLongitude(cVarA.longitude);
                    location2.setLatitude(cVarA2.latitude);
                    location2.setLongitude(cVarA2.longitude);
                    double dDistanceTo = location.distanceTo(location2);
                    double d10 = ((double) 0.25f) * dDistanceTo;
                    if (AgpsApplication.f3567i) {
                        if (d10 > 1000.0d) {
                            d9 = d10 / 1000.0d;
                            str = "km";
                        } else {
                            str = "m";
                            d9 = d10;
                        }
                        int i7 = 1;
                        do {
                            i7 *= 10;
                        } while (i7 <= d9);
                        int i8 = i7 / 10;
                        f7 = ((int) (d9 / ((double) i8))) * i8;
                        ScalebarView.f3741k = (int) ((d10 / d9) * (((double) f7) / dDistanceTo) * ((double) ScalebarView.m));
                    } else {
                        double d11 = d10 * 3.28084d;
                        double d12 = dDistanceTo * 3.28084d;
                        if (d11 > 5280.0d) {
                            d8 = d11 / 5280.0d;
                            str = "mi";
                        } else {
                            str = "ft";
                            d8 = d11;
                        }
                        int i9 = 1;
                        do {
                            i9 *= 10;
                        } while (i9 <= d8);
                        int i10 = i9 / 10;
                        f7 = ((int) (d8 / ((double) i10))) * i10;
                        ScalebarView.f3741k = (int) ((d11 / d8) * (((double) f7) / d12) * ((double) ScalebarView.m));
                    }
                    str2 = String.format(Locale.US, "%.0f %s", Float.valueOf(f7), str);
                    scalebarView.invalidate();
                }
            }
            bZ = M0.getModel().mapViewPosition.getZoomLevel();
        }
        textView.setText(str2);
        ((TextView) findViewById(R.id.zoom_level)).setText(String.valueOf((int) bZ));
    }

    public final void H(int i7) {
        this.B = 4;
        View viewFindViewById = findViewById(R.id.data);
        this.G0 = new j3();
        View viewInflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.change_colors_dialog, (ViewGroup) null, false);
        this.H0 = viewInflate;
        j3 j3Var = this.G0;
        j3Var.f3026d = viewInflate;
        PopupWindow popupWindow = new PopupWindow(j3Var.f3026d, -2, -2);
        j3Var.f3025c = popupWindow;
        popupWindow.showAsDropDown(viewFindViewById, 10, j3Var.f3026d.getHeight() + (-viewFindViewById.getHeight()) + 10);
        SeekBar seekBar = (SeekBar) j3Var.f3026d.findViewById(R.id.RedSeekBar);
        SeekBar seekBar2 = (SeekBar) j3Var.f3026d.findViewById(R.id.GreenSeekBar);
        SeekBar seekBar3 = (SeekBar) j3Var.f3026d.findViewById(R.id.BlueSeekBar);
        SeekBar seekBar4 = (SeekBar) j3Var.f3026d.findViewById(R.id.SizeSeekBar);
        if (i7 == 1) {
            j3Var.f3023a = f3632t1;
            j3Var.f3024b = f3634v1;
        } else {
            j3Var.f3023a = f3633u1;
            j3Var.f3024b = f3635w1;
        }
        seekBar.setProgress((j3Var.f3023a & 16711680) >>> 16);
        seekBar2.setProgress((j3Var.f3023a & 65280) >>> 8);
        seekBar3.setProgress(j3Var.f3023a & 255);
        seekBar4.setProgress(j3Var.f3024b);
        j3Var.f3026d.findViewById(R.id.color_line).setBackgroundColor((-16777216) | j3Var.f3023a);
        w2 w2Var = new w2(i7, j3Var);
        w2 w2Var2 = new w2(i7, j3Var);
        w2 w2Var3 = new w2(i7, j3Var);
        w2 w2Var4 = new w2(i7, j3Var);
        seekBar.setOnSeekBarChangeListener(w2Var);
        seekBar2.setOnSeekBarChangeListener(w2Var2);
        seekBar3.setOnSeekBarChangeListener(w2Var3);
        seekBar4.setOnSeekBarChangeListener(w2Var4);
    }

    public final void H0() {
        String string;
        Location location;
        View viewFindViewById = findViewById(R.id.finestra2);
        View viewFindViewById2 = findViewById(R.id.finestra3);
        x3 x3Var = AgpsApplication.f3575r;
        if (x3Var == null || x3Var.i() <= 0) {
            viewFindViewById.setVisibility(4);
            viewFindViewById2.setVisibility(4);
        } else {
            viewFindViewById.setVisibility(0);
            viewFindViewById2.setVisibility(0);
        }
        DecimalFormat decimalFormat = new DecimalFormat("###0");
        DecimalFormat decimalFormat2 = new DecimalFormat("##0.00");
        if (!AgpsApplication.m && !AgpsApplication.f3571n) {
            ((TextView) findViewById(R.id.label1)).setText(getString(R.string.altitude_box) + " [" + y() + "]");
        }
        TextView textView = (TextView) findViewById(R.id.label31);
        textView.setText(getString(R.string.distance));
        TextView textView2 = (TextView) findViewById(R.id.label32);
        StringBuilder sbA = android.support.v4.media.b.a(" [");
        sbA.append(x());
        sbA.append("]");
        textView2.setText(sbA.toString());
        if (O0 != 3 || (location = f3613a1) == null || location.getAltitude() == AgpsApplication.f3579v) {
            string = "---";
        } else {
            double dW = W(f3613a1.getAltitude());
            StringBuilder sbA2 = android.support.v4.media.b.a(XmlPullParser.NO_NAMESPACE);
            sbA2.append(decimalFormat.format(dW));
            string = sbA2.toString();
        }
        if (f3613a1 != null) {
            int iP = P(new org.mapsforge.core.model.LatLong(f3613a1.getLatitude(), f3613a1.getLongitude()));
            this.Y = iP;
            String str = iP != AgpsApplication.f3579v ? decimalFormat.format(W(iP)) : " --- ";
            findViewById(R.id.dataAlt0).setVisibility(0);
            View viewFindViewById3 = findViewById(R.id.dataAlt1);
            TextView textView3 = (TextView) findViewById(R.id.dataAlt01);
            TextView textView4 = (TextView) findViewById(R.id.dataAlt02);
            TextView textView5 = (TextView) findViewById(R.id.dataAlt11);
            TextView textView6 = (TextView) findViewById(R.id.dataAlt12);
            int iC = r.g.c(AgpsApplication.A);
            if (iC == 0) {
                viewFindViewById3.setVisibility(4);
                textView4.setText(str);
                textView3.setText("dem");
            } else if (iC == 1) {
                viewFindViewById3.setVisibility(4);
                textView4.setText(string);
                textView3.setText("gps");
            } else if (iC == 2) {
                viewFindViewById3.setVisibility(0);
                textView4.setText(string);
                textView3.setText("gps");
                textView6.setText(str);
                textView5.setText("dem");
            } else if (iC == 3) {
                viewFindViewById3.setVisibility(4);
                if (this.Y != AgpsApplication.f3579v) {
                    textView4.setText(str);
                    textView3.setText("dem");
                } else {
                    textView4.setText(string);
                    textView3.setText("gps");
                }
            }
            x3 x3Var2 = AgpsApplication.f3575r;
            if (x3Var2 == null || x3Var2.f3236a.size() <= 0) {
                return;
            }
            Iterator<q3> it = AgpsApplication.f3575r.f3236a.iterator();
            double d8 = 0.0d;
            while (it.hasNext()) {
                d8 += it.next().f3128g.f3166p;
            }
            double dU = U(d8 / 1000.0d);
            E1 = dU;
            String str2 = decimalFormat2.format(dU);
            String str3 = decimalFormat2.format(U(AgpsApplication.f3575r.d().f3128g.f3166p / 1000.0d));
            TextView textView7 = (TextView) findViewById(R.id.data30);
            textView7.setVisibility(0);
            TextView textView8 = (TextView) findViewById(R.id.data31);
            if (AgpsApplication.f3575r.f3236a.size() == 1) {
                textView8.setVisibility(4);
                textView7.setText(str2);
            } else {
                textView8.setVisibility(0);
                textView7.setText(str3);
                textView8.setText(str2);
            }
            x3 x3Var3 = AgpsApplication.f3575r;
            long j7 = 0;
            long j8 = 0;
            long time = 0;
            long time2 = 0;
            for (q3 q3Var : x3Var3.f3236a) {
                int size = q3Var.f3123b.size();
                if (size > 0) {
                    time = q3Var.f3123b.get(size - 1).getTime();
                    time2 = time - q3Var.f3123b.get(0).getTime();
                    j8 += time2;
                }
            }
            if (x3Var3.d() != null) {
                if (AgpsApplication.f3568j && x3Var3.d().d() > 0) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - time;
                    j8 += jCurrentTimeMillis;
                    x3Var3.f3250p = (time2 + jCurrentTimeMillis) / 1000;
                }
                j7 = j8 / 1000;
            }
            String strF = q3.f(j7);
            String strF2 = q3.f(AgpsApplication.f3575r.f3250p);
            TextView textView9 = (TextView) findViewById(R.id.data20);
            TextView textView10 = (TextView) findViewById(R.id.data21);
            textView.setVisibility(0);
            if (AgpsApplication.f3575r.f3236a.size() == 1) {
                textView9.setText(strF);
                textView10.setVisibility(4);
            } else {
                textView9.setText(strF2);
                textView10.setText(strF);
                textView10.setVisibility(0);
            }
        }
    }

    public void I() {
        Button button = (Button) findViewById(R.id.rec_start_stop);
        int iC = r.g.c(AgpsApplication.f3576s);
        if (iC == 0) {
            button.setText(getResources().getString(R.string.rec_start));
        } else {
            if (iC != 2) {
                return;
            }
            button.setText(getResources().getString(R.string.rec_pause));
        }
    }

    public void I0(String str, long j7) {
        new DecimalFormat("##0.0");
        this.f3651m0.setScaleY(3.0f);
        this.f3652n0.setVisibility(0);
        this.f3650l0.setText(str);
        this.f3648j0.setText(XmlPullParser.NO_NAMESPACE);
        this.f3649k0.setText(y2.d(j7));
    }

    public final void J() {
        AgpsApplication.m = true;
        AgpsApplication.isFollowLoadedTrackActive = true;
        ((CheckBox) findViewById(R.id.follow_path_return_checkbox)).setChecked(false);
        AgpsApplication.f3571n = false;
        AgpsApplication.isFollowReturnTrackActive = false;
        AgpsApplication.resetFollowTrackProgress();
        if (IntServLocGpsPP.I == null) {
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            IntServLocGpsPP.I = audioManager;
            audioManager.adjustStreamVolume(3, 0, 1);
            IntServLocGpsPP.J = MediaPlayer.create(getApplicationContext(), R.raw.pringtone);
        }
    }

    public final void J0(DocumentFile aVar, String str, x3 x3Var) {
        v2.e("GPS-M", "Start UI thread of  writeGpxBatch: " + str);
        runOnUiThread(new l(str, aVar, x3Var));
    }

    public final void K() {
        AgpsApplication.f3571n = true;
        AgpsApplication.isFollowReturnTrackActive = true;
        ((CheckBox) findViewById(R.id.lavagna_track_follow_path_checkbox)).setChecked(false);
        AgpsApplication.m = false;
        AgpsApplication.isFollowLoadedTrackActive = false;
        AgpsApplication.resetFollowTrackProgress();
        F1 = AgpsApplication.f3575r.f3236a.size();
        G1 = AgpsApplication.f3575r.d().f3122a.size();
        if (IntServLocGpsPP.I == null) {
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            IntServLocGpsPP.I = audioManager;
            audioManager.adjustStreamVolume(3, 0, 1);
            IntServLocGpsPP.J = MediaPlayer.create(getApplicationContext(), R.raw.pringtone);
        }
    }

    public boolean M(org.mapsforge.core.model.LatLong cVar, boolean z, boolean z7) {
        if (cVar == null) {
            return false;
        }
        v2.e("GPS-M", "--> findAndShowMap");
        boolean zB = this.z.b(cVar, z);
        if (this.z.f3059c == null) {
            this.z.a();
        }
        y0();
        if (z7 && !zB) {
            this.z.d(cVar);
            int size = m2.f3048n.size();
            int size2 = ((ArrayList) m2.f3052r).size();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.you_need_first_map_title);
            if (size == 0) {
                builder.setMessage(R.string.you_need_first_map);
            } else if (size2 == 0) {
                builder.setMessage(R.string.you_need_maps_no_bounding_maps);
            }
            builder.setNegativeButton(R.string.no, new j0(this)).setPositiveButton(R.string.yes, new i0(this));
            builder.create().show();
        }
        return zB;
    }

    public void N() {
        centerMapOnCurrentLocation(false);
    }

    public void centerMapOnCurrentLocation(boolean force) {
        Location location = f3613a1;
        if (location != null && location.getLongitude() != 0.0d && f3613a1.getLongitude() != 0.0d && this.z != null) {
            double latitude = f3613a1.getLatitude();
            double longitude = f3613a1.getLongitude();
            byte targetZoom = AgpsApplication.B;
            MapView mapView = M0;
            if (mapView != null) {
                boolean shouldRecenter = force || lastCenteredLocation == null;
                if (!shouldRecenter) {
                    float distanceMoved = location.distanceTo(lastCenteredLocation);
                    byte currentZoom = mapView.getModel().mapViewPosition.getZoomLevel();
                    if (distanceMoved >= MIN_DISTANCE_METERS_FOR_MAP_RECENTER || currentZoom != targetZoom) {
                        shouldRecenter = true;
                    }
                }
                if (shouldRecenter) {
                    mapView.setCenter(new org.mapsforge.core.model.LatLong(latitude, longitude));
                    setMapZoomLevel(targetZoom);
                    if (lastCenteredLocation == null) {
                        lastCenteredLocation = new Location(location);
                    } else {
                        lastCenteredLocation.set(location);
                    }
                }
            }
        }
        if (M0 != null && M0.getModel() != null && M0.getModel().mapViewPosition != null) {
            lastCenteredMapPosition = M0.getModel().mapViewPosition.getCenter();
            I1 = lastCenteredMapPosition;
        }
    }

    public int P(org.mapsforge.core.model.LatLong cVar) {
        boolean z;
        c2.n nVar;
        Iterator it = ((ArrayList) c2.o.f3084p).iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                nVar = null;
                break;
            }
            nVar = (c2.n) it.next();
            if (nVar.f3072b.contains(cVar)) {
                z = true;
                break;
            }
        }
        double dB = AgpsApplication.f3579v;
        if (z) {
            dB = nVar.b(cVar);
        }
        return (int) dB;
    }

    public void Q(Context context, long j7) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(j7);
        Cursor cursorQuery = downloadManager.query(query);
        if (cursorQuery.moveToFirst()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("title"));
            cursorQuery.getString(cursorQuery.getColumnIndex("local_uri"));
            int i7 = cursorQuery.getInt(cursorQuery.getColumnIndex("status"));
            if (i7 == 2) {
                long j8 = cursorQuery.getLong(cursorQuery.getColumnIndex("total_size"));
                if (j8 >= 0) {
                    long j9 = cursorQuery.getLong(cursorQuery.getColumnIndex("bytes_so_far"));
                    StringBuilder sbD = c7.f.d(getResources().getString(R.string.download_in_backgroud), ": ");
                    sbD.append(string.substring(0, string.indexOf(".")));
                    F0((int) ((100 * j9) / j8), sbD.toString(), j9);
                    return;
                }
                return;
            }
            if (i7 == 1) {
                StringBuilder sbD2 = c7.f.d(getResources().getString(R.string.download_in_backgroud), ": ");
                sbD2.append(getApplicationContext().getString(R.string.waiting_to_start));
                F0(0, sbD2.toString(), 0L);
                return;
            } else if (i7 == 4) {
                StringBuilder sbD3 = c7.f.d(getResources().getString(R.string.download_in_backgroud), ": ");
                sbD3.append(getApplicationContext().getString(R.string.paused));
                F0(0, sbD3.toString(), 0L);
                return;
            } else if (i7 == 8) {
                F0(100, XmlPullParser.NO_NAMESPACE, 100L);
                return;
            }
        }
        F0(0, XmlPullParser.NO_NAMESPACE, 0L);
    }

    public String S(Uri uri) {
        String string = null;
        if (uri == null || uri.getScheme() == null) {
            return null;
        }
        if (uri.getScheme().equals("file")) {
            return uri.getLastPathSegment();
        }
        if (!uri.getScheme().equals("content")) {
            return null;
        }
        Cursor cursorQuery = getContentResolver().query(uri, null, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_display_name"));
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
        if (cursorQuery == null) {
            return string;
        }
        cursorQuery.close();
        return string;
    }

    public void dummyAction(View view) {
    }

    public final void f0() {
        int iC = r.g.c(this.B);
        if (iC == 0) {
            TextView textView = (TextView) findViewById(R.id.lavagna_track_titolo);
            TextView textView2 = (TextView) findViewById(R.id.lavagna_track_text);
            x3 x3Var = AgpsApplication.f3574q;
            if (x3Var == null || x3Var.i() <= 1) {
                return;
            }
            textView.setText(AgpsApplication.f3574q.f3242g);
            textView2.setText(AgpsApplication.f3574q.f3245j.f3165o);
            return;
        }
        if (iC != 2) {
            return;
        }
        TextView textView3 = (TextView) findViewById(R.id.lavagna_gps_titolo);
        TextView textView4 = (TextView) findViewById(R.id.lavagna_gps_text);
        if (textView3 != null) {
            textView3.setText(getString(R.string.user_position));
        }
        String strP0 = p0();
        if (IntServLocGpsPP.K == null) {
            IntServLocGpsPP.K = "\nNmea: no msg.";
        }
        if (v2.f3203h) {
            StringBuilder sbD = c7.f.d(strP0, "\n");
            sbD.append(IntServLocGpsPP.K);
            sbD.append("\nAzimut=");
            sbD.append((int) N1);
            sbD.append("°");
            strP0 = sbD.toString();
        }
        if (textView4 != null) {
            textView4.setText(strP0);
        }
    }

    public void g0() {
        try {
            X0.acquire();
            v2.e("GPS-I", "Load all photos in table");
            DocumentFile rootDir = DocumentFile.fromTreeUri(this, f3624l1);
            DocumentFile photoDir = rootDir != null ? rootDir.findFile("AgpsTrackerPhoto") : null;
            DocumentFile[] aVarArrJ = photoDir != null ? photoDir.listFiles() : new DocumentFile[0];
            if (T1.size() == 0 || T1.size() != aVarArrJ.length) {
                T1.clear();
                for (DocumentFile aVar : aVarArrJ) {
                    T1.add(new h3(aVar.getUri(), this));
                }
            }
        } catch (Exception e8) {
            androidx.appcompat.widget.d.c("loadPhotoPointsTable:", e8, "GPS-M");
        }
        X0.release();
    }

    public void h0() {
        s0("REC_ON", false);
        AgpsApplication.f3568j = false;
        AgpsApplication.m = false;
        AgpsApplication.f3571n = false;
        if (!AgpsApplication.f3568j && !AgpsApplication.m && !AgpsApplication.f3571n && IntServLocGpsPP.A) {
            if (this.W) {
                unbindService(this.X);
            }
            Intent intent = AgpsApplication.f3573p;
            if (intent != null) {
                stopService(intent);
            }
            AgpsApplication.f3573p = null;
        }
        v2.e("GPS-M", "  +++++++++++++AGPS-Tracker terminated normally! ++++++++++++++++++");
        if (f3615c1.exists()) {
            f3615c1.delete();
        }
        finishAndRemoveTask();
        System.exit(0);
    }

    public DocumentFile i0(String str, Uri uri) {
        for (t2 t2Var : ActivityFileDialogGpxJpg.y(this, DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri)))) {
            if (t2Var.f3179a.equals(str)) {
                v2.e("GPS-M", "File exists in Gpx directory: " + t2Var.f3179a);
                return t2Var.f3183e;
            }
        }
        return null;
    }

    public void j0(byte b8) {
        this.z.b(M0.getModel().mapViewPosition.getCenter(), true);
        if (this.z.f3059c == null) {
            this.z.a();
        }
        AgpsApplication.B = b8;
        isAutoCenterMapOnGps = true;
        J0 = true;
    }

    public final void k0(int i7, String str) {
        Intent intent = new Intent(getBaseContext(), (Class<?>) ActivityInternalFileDialog.class);
        intent.putExtra("START_PATH", str);
        intent.putExtra("CAN_SELECT_DIR", true);
        if (i7 == 1001) {
            String string = getResources().getString(R.string.maps_downloaded);
            intent.putExtra("FORMAT_FILTER", new String[]{"map"});
            intent.putExtra("PAGE_HEADER", string);
            intent.putExtra("REQ_TYPE", i7);
            this.E0.a(intent, null);
            return;
        }
        if (i7 != 1002) {
            return;
        }
        Intent intent2 = new Intent(getBaseContext(), (Class<?>) ActivityInternalFileDialog.class);
        intent2.putExtra("START_PATH", str);
        intent2.putExtra("CAN_SELECT_DIR", true);
        String string2 = getResources().getString(R.string.your_dem_archive);
        intent2.putExtra("FORMAT_FILTER", new String[]{"hgt"});
        intent2.putExtra("PAGE_HEADER", string2);
        intent2.putExtra("REQ_TYPE", i7);
        this.F0.a(intent2, null);
    }

    public final void l0(int i7) {
        Intent intent = new Intent(getBaseContext(), (Class<?>) ActivityFileDialogGpxJpg.class);
        intent.putExtra("CAN_SELECT_DIR", true);
        AgpsApplication.m = false;
        if (i7 == 1003) {
            intent.putExtra("PAGE_HEADER", getResources().getString(R.string.gpx_tracks));
            CheckBox checkBox = (CheckBox) findViewById(R.id.lavagna_track_follow_path_checkbox);
            checkBox.setVisibility(0);
            checkBox.setChecked(false);
            intent.putExtra("REQ_TYPE", i7);
            this.A0.a(intent, null);
            new Thread(new j()).start();
            return;
        }
        if (i7 != 1008) {
            if (i7 == 1007) {
                intent.putExtra("PAGE_HEADER", getResources().getString(R.string.your_photos));
                intent.putExtra("REQ_TYPE", i7);
                startActivity(intent);
                return;
            }
            return;
        }
        intent.putExtra("PAGE_HEADER", getResources().getString(R.string.add_track));
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.lavagna_track_follow_path_checkbox);
        checkBox2.setVisibility(0);
        checkBox2.setChecked(false);
        intent.putExtra("REQ_TYPE", i7);
        this.B0.a(intent, null);
    }

    public final void m0() {
        Intent intent = new Intent(getBaseContext(), (Class<?>) SFtpServerFileDialogActivity.class);
        boolean z = AgpsApplication.f3566h;
        intent.putExtra("IP", BuildConfig.SFTP_HOST);
        intent.putExtra("USER_NAME", BuildConfig.SFTP_USER);
        intent.putExtra("PASSWD", BuildConfig.SFTP_PASS);
        intent.putExtra("START_PATH", AgpsApplication.f3577t);
        intent.putExtra("FORMAT_FILTER", "map");
        intent.putExtra("PAGE_HEADER", getResources().getString(R.string.maps_archive_server));
        startActivity(intent);
    }

    public void n0(Uri uri, String str, int i7) {
        String strS = S(uri);
        if (strS == null) {
            return;
        }
        new Thread(new v(uri, str, strS, i7)).start();
    }

    public void o0(a4 a4Var) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewInflate = getLayoutInflater().inflate(R.layout.editor_wayp_dialog, (ViewGroup) null);
        builder.setView(viewInflate);
        builder.setTitle(getResources().getString(R.string.poi));
        TextView textView = (TextView) viewInflate.findViewById(R.id.wp_dia_name);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.wp_dia_desc);
        textView.setText(a4Var.f2957a);
        textView2.setText(a4Var.f2958b);
        builder.setPositiveButton(getResources().getString(R.string.update), new o(this, textView, textView2, a4Var));
        builder.setNeutralButton(getResources().getString(R.string.delete), new p(this, a4Var));
        builder.setNegativeButton(getResources().getString(R.string.cancel), new q(this));
        builder.create().show();
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i7) {
        if (sensor == this.f3645g0 || sensor == this.f3647i0) {
            L1 = i7;
        }
        if (sensor == this.f3646h0) {
            K1 = i7;
        }
    }

    public void onClickZoomIn(View view) {
        byte b8 = (byte) (AgpsApplication.B + 1);
        AgpsApplication.B = b8;
        setMapZoomLevel(b8);
        this.f3658t0 = 0;
        if (AgpsApplication.B >= 20) {
            this.f3657s0++;
        }
        if (this.f3657s0 == 5) {
            s0("LOG_ENABLED", true);
            w0("Logging enabled");
            v2.f3203h = true;
        }
    }

    public void onClickZoomOut(View view) {
        byte b8 = (byte) (AgpsApplication.B - 1);
        AgpsApplication.B = b8;
        setMapZoomLevel(b8);
        this.f3657s0 = 0;
        if (AgpsApplication.B <= 2) {
            this.f3658t0++;
        }
        if (this.f3658t0 == 5) {
            s0("LOG_ENABLED", false);
            w0("Logging disabled");
            v2.f3203h = false;
        }
    }

    public void onClick_GraphWindowsOff(View view) {
        findViewById(R.id.graph_window).setVisibility(4);
        y3 y3Var = Y0;
        if (y3Var != null) {
            y3Var.b(findViewById(R.id.graph_window));
        }
    }

    public void onClick_InputLanLon(View view) {
        G();
    }

    public void onClick_NoAds(View view) {
        if (view != null) {
            view.setVisibility(8);
        }
        w0("This app has all features unlocked.");
    }

    public void onClick_StopDownload(View view) {
        if (SFtpServerFileDialogActivity.A) {
            ((DownloadManager) getSystemService("download")).remove(SFtpServerFileDialogActivity.z);
            SFtpServerFileDialogActivity.A = false;
        } else if (this.z.f3061e.d()) {
            this.z.f3061e.f3756h = true;
        } else if (UnzipProgressState.isUnzipping) {
            UnzipProgressState.cancelUnzip = true;
        }
    }

    public void onClick_TakePhoto(View view) {
        v2.e("GPS-M", "TakePhoto");
        if (!AgpsApplication.z) {
            v2.e("GPS-M", "TakePhoto: Error has no camera.");
            return;
        }
        if (!B()) {
            ((Queue) f3625m1.f3170i).add("Can't take photo: something wrong in your directory permissions");
            return;
        }
        getContentResolver();
        Uri uriZ = ActivityFileDialogGpxJpg.z(this, f3624l1, "AgpsTrackerPhoto");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (uriZ == null) {
            v2.e("GPS-M", "TakePhoto: Error, uriWorkingDir is null.");
            return;
        }
        StringBuilder sbA = android.support.v4.media.b.a("-->Taking photo, uriWorkingDocDir :");
        sbA.append(uriZ.getLastPathSegment());
        v2.e("GPS-M", sbA.toString());
        if (f3613a1 == null) {
            v2.e("GPS-M", "Take photo failed: no valid location found");
            return;
        }
        this.f3656r0 = new Location(f3613a1);
        try {
            try {
                this.f3654p0 = X(this, "P" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()) + ".jpg", 8);
                StringBuilder sb = new StringBuilder();
                sb.append("Take photo: cache file ");
                sb.append(this.f3654p0.getName());
                v2.e("GPS-M", sb.toString());
                this.f3655q0 = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", this.f3654p0);
                v2.e("GPS-M", "Take photo: photoUri: " + this.f3655q0.toString());
                intent.putExtra("output", this.f3655q0);
                intent.addFlags(3);
                this.f3665y0.a(intent, null);
            } catch (Exception e8) {
                f3625m1.a("Take photo: Error occurred while creating the File: " + e8);
            }
        } catch (Exception e9) {
            d0.c("TakePhoto error:", e9, f3625m1);
        }
    }

    public void onClick_changeLocSettings(View view) {
        try {
            startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        } catch (Exception e8) {
            d0.c("onClick_changeLocSettings error:", e8, f3625m1);
        }
    }

    public void onClick_colorDismiss(View view) {
        j3 j3Var = this.G0;
        Objects.requireNonNull(j3Var);
        t0("CURRENT_TRACK_COLOR", f3632t1);
        t0("LOADED_TRACK_COLOR", f3633u1);
        t0("CURRENT_TRACK_SIZE", f3634v1);
        t0("LOADED_TRACK_SIZE", f3635w1);
        j3Var.f3025c.dismiss();
    }

    public void onClick_currentTrackSetColor(View view) {
        H(1);
    }

    public void onClick_focusMyPosition(View view) {
        if (f3613a1 != null) {
            this.z.b(new org.mapsforge.core.model.LatLong(f3613a1.getLatitude(), f3613a1.getLongitude()), false);
            if (this.z.f3059c == null) {
                this.z.a();
            }
            AgpsApplication.B = (byte) 17;
            centerMapOnCurrentLocation(true);
            isAutoCenterMapOnGps = true;
            J0 = true;
        }
    }

    public void onClick_focusOnLoadedTrack(View view) {
        isAutoCenterMapOnGps = false;
        J0 = false;
        x3 x3Var = AgpsApplication.f3574q;
        if (x3Var == null || x3Var.i() <= 1) {
            return;
        }
        this.B = 1;
        CheckBox checkBox = (CheckBox) findViewById(R.id.lavagna_track_follow_path_checkbox);
        checkBox.setVisibility(0);
        if (AgpsApplication.m) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        M(AgpsApplication.f3574q.f3236a.get(0).f3122a.get(0), false, true);
        x3 x3Var2 = AgpsApplication.f3574q;
        if (x3Var2 != null && x3Var2.i() > 1) {
            AgpsApplication.f3574q.k();
        }
        AgpsApplication.B = this.z.m(AgpsApplication.f3574q);
    }

    public void onClick_followPathMonitorLoadedTrack(View view) {
        if (!((CheckBox) view).isChecked()) {
            AgpsApplication.m = false;
            return;
        }
        if (!this.f3639a0) {
            J();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(getResources().getString(R.string.follow_path_monitor_title));
        builder.setMessage(getResources().getString(R.string.loaded_path_monitor_text));
        builder.setPositiveButton(getResources().getString(R.string.ok), new n0(this));
        builder.show();
        this.f3639a0 = false;
    }

    public void onClick_lavagnaOff(View view) {
        this.B = 4;
        updateLavagnaVisibility();
    }

    public void onClick_loadedTrackSetColor(View view) {
        H(2);
    }

    public void onClick_loadedTrackShareData(View view) {
        x3 x3Var = AgpsApplication.f3574q;
        if (x3Var == null || x3Var.i() <= 0) {
            return;
        }
        x3 x3Var2 = AgpsApplication.f3574q;
        String str = x3Var2.f3242g;
        String str2 = x3Var2.f3245j.f3165o;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.putExtra("android.intent.extra.TEXT", str2);
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.share)));
    }

    public void onClick_newPointOfInterest(View view) {
        if (AgpsApplication.f3575r.i() <= 1) {
            w0(getResources().getString(R.string.rec_not_started));
        } else if (f3613a1 == null || O0 != 3) {
            w0(getResources().getString(R.string.gps_not_precise));
        } else {
            new DecimalFormat("##0.00").format(E1);
            w(f3613a1, false, 1);
        }
    }

    public void onClick_recorderClear(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(getResources().getString(R.string.clear_current_track));
        builder.setMessage(getResources().getString(R.string.warning_reset_current_track));
        builder.setPositiveButton(getResources().getString(R.string.ok), new p0(this));
        builder.setNegativeButton(getResources().getString(R.string.cancel), new q0(this));
        builder.show();
    }

    public void onClick_recorderSave(View view) {
        x3 x3Var = AgpsApplication.f3575r;
        if (x3Var == null) {
            return;
        }
        if (x3Var.i() <= 1) {
            w0(getResources().getString(R.string.empty_track_no_gpx));
            return;
        }
        if (B()) {
            this.f3640b0 = AgpsApplication.f3575r;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            View viewInflate = getLayoutInflater().inflate(R.layout.gpx_file_save_dialog, (ViewGroup) null);
            builder.setTitle(getResources().getString(R.string.save_track));
            builder.setView(viewInflate);
            EditText editText = (EditText) viewInflate.findViewById(R.id.gpx_filename);
            EditText editText2 = (EditText) viewInflate.findViewById(R.id.gpx_description);
            long jCurrentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.US);
            StringBuilder sbA = android.support.v4.media.b.a("T");
            sbA.append(simpleDateFormat.format(Long.valueOf(jCurrentTimeMillis)));
            editText.setText(sbA.toString());
            builder.setPositiveButton(getResources().getString(R.string.ok), new c2.r0(this, editText, editText2));
            builder.setNegativeButton(getResources().getString(R.string.cancel), new s0(this));
            builder.show();
        }
    }

    public void onClick_recorderStartStop(View view) {
        boolean z = !AgpsApplication.f3568j;
        this.f3641c0 = false;
        if (z) {
            File file = f3628p1;
            if (file.exists() && file.canRead() && file.length() > 2000) {
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.rec_new_continue_dialog);
                Button button = (Button) dialog.findViewById(R.id.cancelBtn);
                Button button2 = (Button) dialog.findViewById(R.id.continueBtn);
                button2.setText(getResources().getString(R.string.continue_button));
                Button button3 = (Button) dialog.findViewById(R.id.newBtn);
                button3.setText(getResources().getString(R.string.new_track));
                ((TextView) dialog.findViewById(R.id.title)).setText(Z0.getResources().getString(R.string.start_recording_title));
                button.setOnClickListener(new v0(this, dialog));
                button3.setOnClickListener(new w0(this, dialog));
                button2.setOnClickListener(new x0(this, dialog));
                dialog.show();
                return;
            }
            AgpsApplication.f3568j = true;
            q0();
        } else {
            AgpsApplication.f3568j = false;
            AgpsApplication.f3576s = 1;
            s0("REC_ON", false);
            AgpsApplication.f3575r.m();
            new Thread(new i()).start();
        }
        I();
    }

    public void onClick_recorderViewOff(View view) {
        this.B = 4;
    }

    public void onClick_recorderViewOn(View view) {
        int iA = b0.a.a(this, "android.permission.ACCESS_FINE_LOCATION");
        P0 = iA;
        if (iA != 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.review_settings));
            builder.setMessage(getResources().getString(R.string.geoloc_authorisation_insufficient));
            builder.setNegativeButton(getResources().getString(R.string.no), new g(this));
            builder.setPositiveButton(getResources().getString(R.string.review_settings), new h());
            builder.show();
            return;
        }
        this.B = 2;
        N0 = 0;
        CheckBox checkBox = (CheckBox) findViewById(R.id.follow_path_return_checkbox);
        if (AgpsApplication.f3571n) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
    }

    public void onClick_returnPathMonitor(View view) {
        if (!((CheckBox) view).isChecked()) {
            AgpsApplication.f3571n = false;
            return;
        }
        if (!H1) {
            K();
            return;
        }
        H1 = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(getResources().getString(R.string.return_path_monitor_title));
        builder.setMessage(getResources().getString(R.string.return_path_text));
        builder.setPositiveButton(getResources().getString(R.string.ok), new o0(this));
        builder.show();
    }

    public void onClick_sendMyPosition(View view) {
        if (f3613a1 == null) {
            return;
        }
        Location location = new Location("Dummy");
        location.set(f3613a1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.do_you_want_to_send_position));
        builder.setNegativeButton(getResources().getString(R.string.no), new m(this));
        builder.setPositiveButton(getResources().getString(R.string.yes), new n(location));
        builder.show();
    }

    public void onClick_showElevationProfileOfCurrentTrack(View view) {
        View viewFindViewById = findViewById(R.id.graph_window);
        viewFindViewById.setVisibility(0);
        Y0 = new y3(this, viewFindViewById, AgpsApplication.f3575r, getResources().getString(R.string.current_track), true);
        runOnUiThread(new f());
    }

    public void onClick_showElevationProfileOfLoadedTrack(View view) {
        if (this.f3642d0) {
            return;
        }
        View viewFindViewById = findViewById(R.id.graph_window);
        viewFindViewById.setVisibility(0);
        x3 x3Var = AgpsApplication.f3574q;
        Y0 = new y3(this, viewFindViewById, x3Var, x3Var.f3242g, true);
        runOnUiThread(new e());
    }

    public void onClick_showGpsStatus(View view) {
        if (this.B == 3) {
            this.B = 4;
        } else {
            this.B = 3;
        }
        updateLavagnaVisibility();
        f0();
    }

    public void onClick_showStatisticsOfCurrentTrack(View view) {
        this.f3641c0 = !this.f3641c0;
        N0 = 0;
    }

    public void onClick_showWalking(View view) {
        y3 y3Var = Y0;
        if (y3Var == null || !y3Var.f3273h || y3Var.f3278n) {
            return;
        }
        y3Var.f3272g.b(true);
        y3Var.f3278n = true;
        y3Var.f3277l = 0;
        int i7 = y3Var.f3275j;
        if (i7 > 100) {
            y3Var.m = i7 / 100;
        } else {
            y3Var.m = 1.0f;
        }
        y3Var.f3279o = new Handler(Looper.getMainLooper());
        y3Var.f3280p.run();
    }

    public void onClick_skipLocSettings(View view) {
        findViewById(R.id.location_not_available).setVisibility(4);
    }

    public void onClick_zipAndShare(View view) {
        startActivity(new Intent(getBaseContext(), (Class<?>) ListViewWithCheckboxActivity.class));
    }

    public void onCompassClick(View view) {
        if (O1) {
            M1 = !M1;
            StringBuilder sbA = android.support.v4.media.b.a("Compass accuracy: Gravity=");
            sbA.append(L1);
            sbA.append("Magnetic=");
            sbA.append(K1);
            v2.e("GPS-M", sbA.toString());
        }
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        long j7;
        super.onCreate(bundle);
        try {
            R0 = getPreferences(0);
            Z0 = getApplicationContext();
            setContentView(R.layout.main_activity);
            MapRenderPreferences.applySettings(this, (RotateView) findViewById(R.id.mapsforge_rotview));
            setRequestedOrientation(1);
            v2.e("GPS-M", "********************************************************************");
            v2.e("GPS-M", "********************************************************************");
            v2.e("GPS-M", "*******************   AGPS TRACKER     *****************************");
            v2.e("GPS-M", "********************************************************************");
            v2.e("GPS-M", "********************************************************************");
            f3625m1 = new s3(0);
            if (f3613a1 == null) {
                f3613a1 = new Location("INTERNAL");
            }
            if (IntServLocGpsPP.G == null) {
                IntServLocGpsPP.G = new Location("Demo");
            }
            F();
            R0 = getPreferences(0);
            v2.f3203h = O("LOG_ENABLED", false);
            Q0 = O("RATE_THIS_APP", true);
            if (T0) {
                S0 = false;
            }
            if (S0) {
                T0 = true;
            }
            v2.e("GPS-M", "-->onCreate");
            File fileX = X(this, "GpxTemp/" + this.f3660v, 3);
            f3628p1 = fileX;
            f3629q1 = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", fileX);
            StringBuilder sbA = new StringBuilder("GpxTemp/");
            sbA.append(this.f3662w);
            File fileX2 = X(this, sbA.toString(), 3);
            f3615c1 = fileX2;
            f3616d1 = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", fileX2);
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
            M0 = (MapView) findViewById(R.id.mapsforge);
            this.z = new m2(this, getApplication(), false);
            f3626n1 = new u2(M0);
            try {
                if (androidx.core.content.ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                    LocationManager lm = (LocationManager) getSystemService("location");
                    if (lm != null) {
                        Location loc = lm.getLastKnownLocation("gps");
                        if (loc == null) {
                            loc = lm.getLastKnownLocation("network");
                        }
                        if (loc != null && f3613a1 != null) {
                            f3613a1.set(loc);
                            M0.setCenter(new org.mapsforge.core.model.LatLong(loc.getLatitude(), loc.getLongitude()));
                            this.z.b(new org.mapsforge.core.model.LatLong(loc.getLatitude(), loc.getLongitude()), false);
                        }
                    }
                }
                if (this.z.f3059c == null) {
                    this.z.a();
                }
            } catch (Throwable tMap) {
                v2.e("GPS-M", "Initial map layer setup: " + tMap);
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                j7 = getPackageManager().getPackageInfo(getPackageName(), 0).firstInstallTime;
            } catch (Exception e8) {
                d0.c("getInstallTime:  ", e8, f3625m1);
                j7 = 0;
            }
            long j8 = (jCurrentTimeMillis - j7) / 86400000;

            this.f3649k0 = (TextView) findViewById(R.id.progressValue);
            this.f3648j0 = (TextView) findViewById(R.id.progressValPercent);
            this.f3650l0 = (TextView) findViewById(R.id.progressTitle);
            this.f3652n0 = findViewById(R.id.progress_window_downld);
            this.f3651m0 = (ProgressBar) findViewById(R.id.ftp_progress_bar);
            W0 = new Semaphore(1);
            X0 = new Semaphore(1);
            new Semaphore(1);
            if (AgpsApplication.f3575r == null) {
                AgpsApplication.f3575r = new x3(1);
            }
            if (AgpsApplication.f3574q == null) {
                AgpsApplication.f3574q = new x3(2);
            }
            new Thread(new c(this, new com.giobat.AgpsTrackerPP.b(this))).start();
            AgpsApplication.z = getPackageManager().hasSystemFeature("android.hardware.camera.any");
            ImageButton imageButton = (ImageButton) findViewById(R.id.photo_image_button);
            if (AgpsApplication.z) {
                imageButton.setVisibility(0);
            } else {
                imageButton.setVisibility(4);
            }
            v2.e("GPS-M", "Local country=" + getResources().getConfiguration().getLocales().get(0).getCountry());
            this.f3659u0 = new k1(this);
            try {
                if (android.os.Build.VERSION.SDK_INT >= 33) {
                    registerReceiver(this.f3659u0, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"), 2);
                } else {
                    registerReceiver(this.f3659u0, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
                }
            } catch (Throwable tReceiver) {
                v2.e("GPS-M", "registerReceiver error: " + tReceiver);
            }
            this.H = new c2.r(this);
            this.E = (AdView) findViewById(R.id.adView);
            this.G = new PurchaseStatusListener(this);
            this.f3665y0 = o(new d.c(), new y1(this));
            this.f3666z0 = o(new d.c(), new z1(this));
            this.A0 = o(new d.c(), new a2(this));
            this.B0 = o(new d.c(), new AddGpxTrackResultCallback(this));
            this.C0 = o(new d.c(), new d2(this));
            this.D0 = o(new d.c(), new e2(this));
            this.E0 = o(new d.c(), new f2(this));
            this.F0 = o(new d.c(), new androidx.lifecycle.d0(this));
            this.J = (ProgressBar) findViewById(R.id.progress_bar_generic);
            this.K = new b4();
            f3632t1 = T("CURRENT_TRACK_COLOR", -65281);
            f3633u1 = T("LOADED_TRACK_COLOR", -16776961);
            f3634v1 = T("CURRENT_TRACK_SIZE", 6);
            f3635w1 = T("LOADED_TRACK_SIZE", 8);
        } catch (Throwable t) {
            t.printStackTrace();
            android.util.Log.e("AGPS_CRASH", "FATAL in MainActivity.onCreate", t);
            try {
                File extDir = getExternalFilesDir(null);
                if (extDir != null) {
                    File crashFile = new File(extDir, "crash.log");
                    java.io.FileWriter fw = new java.io.FileWriter(crashFile, false);
                    java.io.PrintWriter pw = new java.io.PrintWriter(fw);
                    pw.write("CRASH IN ONCREATE:\n");
                    t.printStackTrace(pw);
                    pw.close();
                }
            } catch (Exception ignored) {
            }
            new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Error initializing A-GPS Tracker")
                .setMessage(t.getClass().getSimpleName() + ": " + t.getMessage())
                .setPositiveButton("OK", null)
                .show();
        }
    }

    public <I, O> androidx.activity.result.c<I> o(final d.a<I, O> aVar, final androidx.activity.result.b<O> bVar) {
        final androidx.activity.result.ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult(),
            new androidx.activity.result.ActivityResultCallback<androidx.activity.result.ActivityResult>() {
                @Override
                public void onActivityResult(androidx.activity.result.ActivityResult result) {
                    if (result != null) {
                        bVar.a((O) new androidx.activity.result.a(result.getResultCode(), result.getData()));
                    }
                }
            }
        );
        return new androidx.activity.result.c<I>() {
            @Override
            public void a(I input, Object options) {
                launcher.launch((Intent) input);
            }
        };
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        this.Z = menu;
        AgpsApplication.f3566h = O("SHOW_CMP", true);
        menu.findItem(R.id.show_compass).setChecked(AgpsApplication.f3566h);
        if (!O1) {
            menu.findItem(R.id.show_compass).setVisible(false);
            menu.findItem(R.id.show_orientation_arrow).setVisible(false);
        }
        boolean zO = O("METERS", true);
        AgpsApplication.f3567i = zO;
        if (zO) {
            menu.findItem(R.id.meters).setChecked(true);
        } else {
            menu.findItem(R.id.feets).setChecked(true);
        }
        int iT = T(null, 0);
        if (iT == 0) {
            menu.findItem(R.id.elev_auto).setChecked(true);
            AgpsApplication.A = 4;
        } else if (iT == 1) {
            menu.findItem(R.id.elev_both).setChecked(true);
            AgpsApplication.A = 3;
        } else if (iT == 2) {
            menu.findItem(R.id.elev_gps).setChecked(true);
            AgpsApplication.A = 2;
        } else if (iT == 3) {
            menu.findItem(R.id.elev_dems).setChecked(true);
            AgpsApplication.A = 1;
        }
        return true;
    }

    @Override // f.e, androidx.fragment.app.q, android.app.Activity
    public void onDestroy() {
        AdView adView = this.E;
        if (adView != null) {
            adView.destroy();
            v2.e("GPS-M", "-->AdView destroy  .........");
        }
        v2.e("GPS-M", "-->onDestroy  .........");
        BroadcastReceiver broadcastReceiver = this.f3659u0;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            this.f3659u0 = null;
        }
        if (this.M != null) {
            this.M.unregisterReceiver(this.N);
            this.M = null;
        }
        if (M0 != null) {
            u2 u2Var = f3626n1;
            Objects.requireNonNull(u2Var);
            try {
                u2Var.f3196b.acquire();
            } catch (Exception unused) {
            }
            try {
                u2Var.f3195a.clear(false);
            } catch (Exception unused2) {
            }
            u2Var.f3196b.release();
        }
        m2 m2Var = this.z;
        if (m2Var != null) {
            m2Var.f3060d.destroy();
        }
        org.mapsforge.map.android.graphics.AndroidGraphicFactory.clearResourceMemoryCache();
        SensorManager sensorManager = this.C;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            this.C = null;
        }
        if (this.W) {
            try {
                unbindService(this.X);
            } catch (Exception ignored) {
            }
        }
        this.W = false;
        if (!AgpsApplication.f3568j && AgpsApplication.f3573p != null) {
            try {
                stopService(AgpsApplication.f3573p);
            } catch (Exception ignored) {
            }
            AgpsApplication.f3573p = null;
        }
        for (c2.n nVar : c2.o.f3084p) {
            RandomAccessFile randomAccessFile = nVar.f3075e;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                    nVar.f3075e = null;
                } catch (Exception unused3) {
                }
            }
        }
        super.onDestroy();
    }

    @Override // f.e, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i7, KeyEvent keyEvent) {
        if (i7 != 4) {
            return super.onKeyDown(i7, keyEvent);
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(268435456);
        startActivity(intent);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean z = false;
        x3 x3Var;
        if (menuItem.getItemId() == R.id.version) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            c0.a(this, R.string.app_title, builder.setMessage(getApplication().getResources().getString(R.string.app_version) + ": 1.8.98")).setPositiveButton(R.string.ok, new l1(this));
            builder.create();
            builder.show();
            return true;
        }
        if (menuItem.getItemId() == R.id.gpx_load_from_your_folder) {
            y3 y3Var = Y0;
            if (y3Var != null && y3Var.f3273h) {
                y3Var.b(findViewById(R.id.graph_window));
            }
            if (B()) {
                l0(1003);
            }
            return true;
        }
        if (menuItem.getItemId() == R.id.gpx_add) {
            y3 y3Var2 = Y0;
            if (y3Var2 != null && y3Var2.f3273h) {
                y3Var2.b(findViewById(R.id.graph_window));
            }
            if (B()) {
                l0(1008);
            }
            invalidateOptionsMenu();
            return true;
        }
        if (menuItem.getItemId() == R.id.gpx_import_from_downloads) {
            y3 y3Var3 = Y0;
            if (y3Var3 != null && y3Var3.f3273h) {
                y3Var3.b(findViewById(R.id.graph_window));
            }
            if (B()) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                c0.a(this, R.string.import_track, builder2.setMessage(getApplication().getResources().getString(R.string.import_gpx_or_zip_txt))).setPositiveButton(R.string.ok, new h0(this)).setNegativeButton(R.string.cancel, new g0(this));
                AlertDialog alertDialogCreate = builder2.create();
                alertDialogCreate.show();
                ((TextView) alertDialogCreate.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
            }
            invalidateOptionsMenu();
            return true;
        }
        if (menuItem.getItemId() == R.id.new_waypoint_on_track) {
            x3 x3Var2 = AgpsApplication.f3574q;
            if (x3Var2 == null || x3Var2.i() <= 1) {
                w0(getResources().getString(R.string.no_loaded_track));
                return true;
            }
            f3620h1 = true;
            f3619g1 = true;
            new Location("dummyprovider");
            Snackbar.make(this.f3664y, getResources().getString(R.string.poi_touch_the_track), Snackbar.LENGTH_SHORT).show();
            return true;
        }
        if (menuItem.getItemId() == R.id.new_waypoint_on_map) {
            x3 x3Var3 = AgpsApplication.f3574q;
            if (x3Var3 == null || x3Var3.i() <= 1) {
                w0(getResources().getString(R.string.no_loaded_track));
                return true;
            }
            f3619g1 = true;
            f3620h1 = false;
            new Location("dummyprovider");
            Snackbar.make(this.f3664y, getResources().getString(R.string.poi_touch_the_map), Snackbar.LENGTH_SHORT).show();
            return true;
        }
        if (menuItem.getItemId() == R.id.modify_waypoiny) {
            AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
            c0.a(this, R.string.app_title, builder3.setMessage(getApplication().getResources().getString(R.string.modify_waypoint_instruction))).setPositiveButton(R.string.ok, new m1(this));
            builder3.create();
            builder3.show();
            return true;
        }
        if (menuItem.getItemId() == R.id.gpx_save_modified) {
            x3 x3Var4 = AgpsApplication.f3574q;
            if (x3Var4 == null || x3Var4.i() < 1) {
                w0(getResources().getString(R.string.no_loaded_track));
                return true;
            }
            y3 y3Var4 = Y0;
            if (y3Var4 != null && y3Var4.f3273h) {
                y3Var4.b(findViewById(R.id.graph_window));
            }
            AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
            builder4.setCancelable(false);
            View viewInflate = getLayoutInflater().inflate(R.layout.gpx_file_save_dialog, (ViewGroup) null);
            builder4.setTitle(getResources().getString(R.string.save_modified_track));
            builder4.setView(viewInflate);
            builder4.setPositiveButton(getResources().getString(R.string.ok), new i1(this, (EditText) viewInflate.findViewById(R.id.gpx_filename), (EditText) viewInflate.findViewById(R.id.gpx_description)));
            builder4.setNegativeButton(getResources().getString(R.string.cancel), new j1(this));
            builder4.show();
            return true;
        }
        if (menuItem.getItemId() == R.id.photo_list_your_folder) {
            if (B()) {
                l0(1007);
            }
            return true;
        }
        if (menuItem.getItemId() == R.id.clear_loaded_track) {
            v2.e("GPS-M", "------------Reset Loaded Track--------------");
            invalidateOptionsMenu();
            CheckBox checkBox = (CheckBox) findViewById(R.id.lavagna_track_follow_path_checkbox);
            checkBox.setVisibility(4);
            checkBox.setChecked(false);
            AgpsApplication.m = false;
            AgpsApplication.f3574q = new x3(2);
            U0 = true;
            this.B = 4;
            y3 y3Var5 = Y0;
            if (y3Var5 != null && y3Var5.f3273h) {
                y3Var5.b(findViewById(R.id.graph_window));
            }
            return true;
        }
        if (menuItem.getItemId() == R.id.menu_exit) {
            AlertDialog.Builder builder5 = new AlertDialog.Builder(this);
            builder5.setTitle(getResources().getString(R.string.really_exit));
            if (AgpsApplication.f3568j && (x3Var = AgpsApplication.f3575r) != null) {
                x3Var.m();
                new Thread(new k0(this)).start();
            }
            builder5.setNegativeButton(getResources().getString(R.string.no), new l0(this));
            builder5.setPositiveButton(getResources().getString(R.string.yes), new m0(this));
            builder5.show();
            return true;
        }
        int i7 = 3;
        if (menuItem.getItemId() == R.id.help_info) {
            new androidx.appcompat.widget.p(this, i7);
            return true;
        }
        if (menuItem.getItemId() == R.id.tracker_license) {
            Intent intent = new Intent(this, (Class<?>) ActivityLicenseInfo.class);
            intent.putExtra("ASSET_FILE_NAME", "license");
            startActivity(intent);
            return true;
        }
        if (menuItem.getItemId() == R.id.privacy) {
            Intent intent2 = new Intent(this, (Class<?>) ActivityLicenseInfo.class);
            intent2.putExtra("ASSET_FILE_NAME", "privacy_info");
            startActivity(intent2);
            return true;
        }
        if (menuItem.getItemId() == R.id.support) {
            StringBuilder sbA = androidx.activity.result.d.a(" ", getApplication().getResources().getString(R.string.app_version) + " 1.8.98, ", "  ");
            sbA.append(Build.MANUFACTURER);
            sbA.append("-");
            sbA.append(Build.MODEL);
            sbA.append(", Android v.");
            sbA.append(Build.VERSION.SDK_INT);
            try {
                startActivity(new Intent("android.intent.action.SENDTO", Uri.parse("mailto:giobat.mailbox@gmail.com?subject=AGPS-TrackerOm: " + sbA.toString())));
            } catch (ActivityNotFoundException unused) {
                w0("Mail account not configured");
            }
            return true;
        }
        if (menuItem.getItemId() == R.id.licenses_info) {
            startActivity(new Intent(this, (Class<?>) ActivityLicenseInfo.class));
            return true;
        }
        if (menuItem.getItemId() == R.id.download_maps) {
            String strZ = Z(this);
            if (strZ.equals(P1) || strZ.equals(Q1)) {
                org.mapsforge.core.model.LatLong cVarU = null;
                if (M0 != null && M0.getModel() != null && M0.getModel().mapViewPosition != null) {
                    cVarU = M0.getModel().mapViewPosition.getCenter();
                }
                if (f3613a1 != null) {
                    cVarU = new org.mapsforge.core.model.LatLong(f3613a1.getLatitude(), f3613a1.getLongitude());
                }
                if (cVarU != null && this.z != null) {
                    this.z.d(cVarU);
                }
                m0();
            } else {
                w0(getResources().getString(R.string.need_internet));
            }
            return true;
        }
        if (menuItem.getItemId() == R.id.select_maps) {
            menuItem.setChecked(true);
            k0(1001, "Maps");
            return true;
        }
        if (menuItem.getItemId() == R.id.dems_list) {
            k0(1002, "Dems");
            return true;
        }
        if (menuItem.getItemId() == R.id.show_compass) {
            if (menuItem.isChecked()) {
                menuItem.setChecked(false);
            } else {
                menuItem.setChecked(true);
            }
            boolean zIsChecked = menuItem.isChecked();
            AgpsApplication.f3566h = zIsChecked;
            s0("SHOW_CMP", zIsChecked);
            return true;
        }
        if (menuItem.getItemId() == R.id.show_orientation_arrow) {
            AlertDialog.Builder builder6 = new AlertDialog.Builder(this);
            builder6.setCancelable(false);
            View viewInflate2 = getLayoutInflater().inflate(R.layout.orientation_dialog, (ViewGroup) null);
            builder6.setView(viewInflate2);
            boolean zO = O("ORIENTATION_ARROW", false);
            f3627o1 = zO;
            if (zO) {
                ((RadioButton) viewInflate2.findViewById(R.id.orientation_arrow)).setChecked(true);
                ((RadioButton) viewInflate2.findViewById(R.id.blue_circle)).setChecked(false);
            } else {
                ((RadioButton) viewInflate2.findViewById(R.id.orientation_arrow)).setChecked(false);
                ((RadioButton) viewInflate2.findViewById(R.id.blue_circle)).setChecked(true);
            }
            ((TextView) viewInflate2.findViewById(R.id.works_if_calibrated_msg)).setText(getString(R.string.arrow_works_if_calibrated));
            c0.a(this, R.string.orientation_arrow, builder6.setMessage(XmlPullParser.NO_NAMESPACE)).setPositiveButton(R.string.ok, new f0(this));
            builder6.create().show();
            return true;
        }
        if (menuItem.getItemId() == R.id.meters) {
            menuItem.setChecked(true);
            s0("METERS", true);
            G0();
            return true;
        }
        if (menuItem.getItemId() == R.id.feets) {
            menuItem.setChecked(true);
            s0("METERS", false);
            G0();
            return true;
        }
        if (menuItem.getItemId() == R.id.photo_show_photo_points) {
            if (B()) {
                try {
                    DocumentFile photoDir = DocumentFile.fromTreeUri(this, f3624l1);
                    DocumentFile sub = photoDir != null ? photoDir.findFile("AgpsTrackerPhoto") : null;
                    DocumentFile[] files = sub != null ? sub.listFiles() : new DocumentFile[0];
                    runOnUiThread(new h1(this, files, M0.getBoundingBox()));
                } catch (Exception e8) {
                    d0.c("showCurrentWindowPhotoPoints:", e8, f3625m1);
                }
            }
            return true;
        }
        if (menuItem.getItemId() == R.id.elev_auto) {
            menuItem.setChecked(true);
            AgpsApplication.A = 4;
            t0(null, 0);
            return true;
        }
        if (menuItem.getItemId() == R.id.elev_both) {
            menuItem.setChecked(true);
            AgpsApplication.A = 3;
            t0(null, 1);
            return true;
        }
        if (menuItem.getItemId() == R.id.elev_dems) {
            menuItem.setChecked(true);
            AgpsApplication.A = 1;
            t0(null, 3);
            return true;
        }
        if (menuItem.getItemId() == R.id.elev_gps) {
            menuItem.setChecked(true);
            AgpsApplication.A = 2;
            t0(null, 2);
            return true;
        }
        if (menuItem.getItemId() == R.id.use_sd) {
            if (menuItem.isChecked()) {
                menuItem.setChecked(false);
                f3621i1 = false;
                s0("USE_SD_MEM", false);
                F();
            } else {
                File[] externalFilesDirs = getApplicationContext().getExternalFilesDirs(null);
                if (externalFilesDirs.length == 2) {
                    try {
                        File file = new File(externalFilesDirs[1], "Test");
                        if (!file.exists()) {
                            file.mkdir();
                        }
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, "testFile"));
                            byte[] bytes = "Test".getBytes();
                            fileOutputStream.write(bytes, 0, bytes.length);
                            fileOutputStream.close();
                            z = true;
                        } catch (Exception e9) {
                            String str = "SD Test: write file failed:" + e9;
                            w0(str);
                            v2.e("GPS-M", str);
                            z = false;
                        }
                    } catch (Exception e10) {
                        String str2 = "SD Test: create dir failed:" + e10;
                        w0(str2);
                        v2.e("GPS-M", str2);
                    }
                } else {
                    z = false;
                }
                if (z) {
                    AlertDialog.Builder builder7 = new AlertDialog.Builder(this);
                    c0.a(this, R.string.use_sd_dialog_title, builder7.setMessage(Html.fromHtml(getApplication().getResources().getString(R.string.use_sd_msg), 0))).setPositiveButton(R.string.continue_button, new r1(this, menuItem)).setNegativeButton(R.string.cancel, new q1(this, menuItem));
                    AlertDialog alertDialogCreate2 = builder7.create();
                    alertDialogCreate2.show();
                    ((TextView) alertDialogCreate2.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
                } else {
                    w0(getApplication().getString(R.string.sd_not_supported));
                }
            }
            return true;
        }
        if (menuItem.getItemId() == R.id.map_elevate) {
            menuItem.setChecked(!menuItem.isChecked());
            if (menuItem.isChecked()) {
                f3622j1 = true;
                s0("USE_ELEVATE_STYLE", true);
                new c2.q(this).show();
            }
            return true;
        }
        if (menuItem.getItemId() == R.id.map_default) {
            menuItem.setChecked(!menuItem.isChecked());
            if (menuItem.isChecked()) {
                f3622j1 = false;
                s0("USE_ELEVATE_STYLE", false);
                f3637y1 = true;
            }
            return true;
        }
        if (menuItem.getItemId() == R.id.enable_use_of_oam_maps) {
            menuItem.setChecked(!menuItem.isChecked());
            if (menuItem.isChecked()) {
                AlertDialog.Builder builder8 = new AlertDialog.Builder(this);
                c0.a(this, R.string.enable_oam_maps_title, builder8.setMessage(Html.fromHtml(getApplication().getResources().getString(R.string.enable_oam_menu_msg), 0))).setPositiveButton(R.string.continue_button, new v1(this, menuItem)).setNegativeButton(R.string.cancel, new u1(this, menuItem));
                AlertDialog alertDialogCreate3 = builder8.create();
                alertDialogCreate3.show();
                ((TextView) alertDialogCreate3.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
            } else {
                f3623k1 = false;
                s0("USE_OSM_MAPS", false);
                invalidateOptionsMenu();
            }
            return true;
        }
        if (menuItem.getItemId() == R.id.download_oam_map) {
            AlertDialog.Builder builder9 = new AlertDialog.Builder(this);
            c0.a(this, R.string.load_oam_maps, builder9.setMessage(Html.fromHtml(getApplication().getResources().getString(R.string.oam_download_msg), 0))).setPositiveButton(R.string.continue_button, new t1(this, this)).setNegativeButton(R.string.cancel, new s1(this));
            AlertDialog alertDialogCreate4 = builder9.create();
            alertDialogCreate4.show();
            ((TextView) alertDialogCreate4.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
            return true;
        }
        if (menuItem.getItemId() == R.id.menu_map_render_settings) {
            showMapRenderSettingsDialog();
            return true;
        }
        if (menuItem.getItemId() != R.id.change_gpx_dir) {
            if (menuItem.getItemId() != R.id.licenses_maps) {
                return super.onOptionsItemSelected(menuItem);
            }
            startActivity(new Intent(this, (Class<?>) HtmlShowActivity.class));
            return true;
        }
        AlertDialog.Builder builder10 = new AlertDialog.Builder(this);
        c0.a(this, R.string.change_gpx_dir_title, builder10.setMessage(getApplication().getResources().getString(R.string.change_gpx_dir_msg))).setPositiveButton(R.string.ok, new c2.d0(this)).setNegativeButton(R.string.cancel, new k2(this));
        AlertDialog alertDialogCreate5 = builder10.create();
        alertDialogCreate5.show();
        ((TextView) alertDialogCreate5.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
        return true;
    }

    private void showMapRenderSettingsDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_map_render_settings, null);

        RadioGroup radioGroupThreads = dialogView.findViewById(R.id.radio_group_threads);
        RadioButton radioAuto = dialogView.findViewById(R.id.radio_thread_auto);
        RadioButton radio1 = dialogView.findViewById(R.id.radio_thread_1);
        RadioButton radio2 = dialogView.findViewById(R.id.radio_thread_2);
        RadioButton radio4 = dialogView.findViewById(R.id.radio_thread_4);
        CheckBox checkHw = dialogView.findViewById(R.id.checkbox_hardware_accel);
        CheckBox checkLabels = dialogView.findViewById(R.id.checkbox_cache_labels);

        // Map Sources views
        CheckBox checkLegacyServer = dialogView.findViewById(R.id.checkbox_use_legacy_server);
        android.widget.EditText editMapsUrl = dialogView.findViewById(R.id.edit_maps_url);
        android.widget.EditText editDemsUrl = dialogView.findViewById(R.id.edit_dems_url);

        int currentThreads = MapRenderPreferences.getThreadsSetting(this);
        if (currentThreads == 1) {
            radio1.setChecked(true);
        } else if (currentThreads == 2) {
            radio2.setChecked(true);
        } else if (currentThreads == 4) {
            radio4.setChecked(true);
        } else {
            radioAuto.setChecked(true);
        }

        checkHw.setChecked(MapRenderPreferences.isHardwareAccelEnabled(this));
        checkLabels.setChecked(MapRenderPreferences.isCacheLabelsEnabled(this));

        // Populate Map Sources fields from preferences
        boolean useLegacy = MapRenderPreferences.isUseLegacyServer(this);
        checkLegacyServer.setChecked(useLegacy);
        editMapsUrl.setText(MapRenderPreferences.getMapsUrl(this));
        editDemsUrl.setText(MapRenderPreferences.getDemUrl(this));
        editMapsUrl.setEnabled(!useLegacy);
        editDemsUrl.setEnabled(!useLegacy);

        // Toggle URL inputs enabled state based on legacy checkbox
        checkLegacyServer.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editMapsUrl.setEnabled(!isChecked);
            editDemsUrl.setEnabled(!isChecked);
        });

        new AlertDialog.Builder(this)
            .setTitle(R.string.map_render_settings_title)
            .setView(dialogView)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int selectedThreads = MapRenderPreferences.THREADS_AUTO;
                    int checkedId = radioGroupThreads.getCheckedRadioButtonId();
                    if (checkedId == R.id.radio_thread_1) {
                        selectedThreads = 1;
                    } else if (checkedId == R.id.radio_thread_2) {
                        selectedThreads = 2;
                    } else if (checkedId == R.id.radio_thread_4) {
                        selectedThreads = 4;
                    }
                    MapRenderPreferences.setThreadsSetting(MainActivity.this, selectedThreads);
                    MapRenderPreferences.setHardwareAccelEnabled(MainActivity.this, checkHw.isChecked());
                    MapRenderPreferences.setCacheLabelsEnabled(MainActivity.this, checkLabels.isChecked());

                    // Save Map Sources preferences
                    MapRenderPreferences.setUseLegacyServer(MainActivity.this, checkLegacyServer.isChecked());
                    String mapsUrlText = editMapsUrl.getText().toString().trim();
                    String demsUrlText = editDemsUrl.getText().toString().trim();
                    if (!mapsUrlText.isEmpty()) {
                        MapRenderPreferences.setMapsUrl(MainActivity.this, mapsUrlText);
                    }
                    if (!demsUrlText.isEmpty()) {
                        MapRenderPreferences.setDemUrl(MainActivity.this, demsUrlText);
                    }

                    RotateView rotateView = findViewById(R.id.mapsforge_rotview);
                    MapRenderPreferences.applySettings(MainActivity.this, rotateView);

                    Toast.makeText(MainActivity.this, R.string.map_render_settings_applied, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            })
            .show();
    }

    @Override // androidx.fragment.app.q, android.app.Activity
    public void onPause() {
        v2.e("GPS-M", "-->OnPause");
        super.onPause();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0086  */
    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        MenuItem menuItemFindItem = menu.findItem(R.id.gpx_edit);
        x3 x3Var = AgpsApplication.f3574q;
        if (x3Var == null || x3Var.i() <= 1) {
            menuItemFindItem.setVisible(false);
        } else {
            menuItemFindItem.setTitle(getApplication().getString(R.string.edit_gpx) + " " + AgpsApplication.f3574q.f3242g);
            menuItemFindItem.setVisible(true);
        }
        MenuItem menuItemFindItem2 = menu.findItem(R.id.use_sd);
        try {
            File[] externalFilesDirs = getExternalFilesDirs(null);
            if (externalFilesDirs.length == 2) {
                String externalStorageState = Environment.getExternalStorageState(externalFilesDirs[1]);
                v2.e("GPS-M", "SD ExternalStorageState=" + externalStorageState);
                if (Environment.isExternalStorageRemovable(externalFilesDirs[1]) && !Environment.isExternalStorageEmulated(externalFilesDirs[1]) && "mounted".equals(externalStorageState)) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
        } catch (Exception unused) {
        }
        if (z) {
            menuItemFindItem2.setVisible(true);
            if (f3621i1) {
                menuItemFindItem2.setChecked(true);
            } else {
                menuItemFindItem2.setChecked(false);
            }
        } else {
            menuItemFindItem2.setVisible(false);
        }
        MenuItem menuItemFindItem3 = menu.findItem(R.id.map_default);
        MenuItem menuItemFindItem4 = menu.findItem(R.id.map_elevate);
        if (f3622j1) {
            menuItemFindItem4.setChecked(true);
        } else {
            menuItemFindItem3.setChecked(true);
        }
        MenuItem menuItemFindItem5 = menu.findItem(R.id.enable_use_of_oam_maps);
        if (f3623k1) {
            menuItemFindItem5.setChecked(true);
            menu.findItem(R.id.download_oam_map).setVisible(true);
        } else {
            menuItemFindItem5.setChecked(false);
            menu.findItem(R.id.download_oam_map).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void onRadioButtonClicked(View view) {
        if (((RadioButton) view).isChecked()) {
            int id = view.getId();
            if (id == R.id.blue_circle) {
                f3627o1 = false;
            } else if (id == R.id.orientation_arrow) {
                f3627o1 = true;
            }
        }
        s0("ORIENTATION_ARROW", f3627o1);
        U0 = true;
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i7, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i7, strArr, iArr);
        Log.i("GPS-M", "permission?" + i7 + "--" + iArr.length);
        if (i7 != 200) {
            Log.i("GPS-M", " No multiple permission?");
            return;
        }
        if (iArr.length <= 0) {
            Log.i("GPS-M", "no result?");
            return;
        }
        for (int i8 = 0; i8 < strArr.length; i8++) {
            if (strArr[i8].equals("android.permission.ACCESS_FINE_LOCATION")) {
                P0 = iArr[i8];
            }
        }
        this.L = false;
    }

    @Override // androidx.fragment.app.q, android.app.Activity
    public void onResume() {
        super.onResume();
        v2.e("GPS-M", "-->onResume()");
        if (!O("TERMS_OF_USE_ACCEPTED_PP", false)) {
            s0("REL_NOTES_DONE_1p4", true);
            this.L = true;
            if (this.D0 != null) {
                this.D0.a(new Intent(this, (Class<?>) P2ActivitySettingsMain.class), null);
            }
            return;
        }
        f3627o1 = O("ORIENTATION_ARROW", false);
        f3621i1 = O("USE_SD_MEM", false);
        f3622j1 = O("USE_ELEVATE_STYLE", false);
        c0("ELEVATE_STYLE", "elv-hiking");
        f3623k1 = O("USE_OSM_MAPS", false);
        if (!O("REL_NOTES_DONE_1p4", false)) {
            s0("REL_NOTES_DONE_1p4", true);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            c0.a(this, R.string.rel_notes_title, builder.setMessage(getApplication().getResources().getString(R.string.rel_notes_msg))).setPositiveButton(R.string.ok, new w1(this));
            AlertDialog alertDialogCreate = builder.create();
            alertDialogCreate.show();
            ((TextView) alertDialogCreate.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
            return;
        }
        if (this.M == null) {
            this.M = LocalBroadcastManager.getInstance(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("RECEIVE_ACTION_FROM_SERVICE");
            intentFilter.addAction("GPXfileRD_DONE");
            intentFilter.addAction("GPXfileRD_DONE_ADD");
            intentFilter.addAction("POI_ON_MAP");
            intentFilter.addAction("POI_ON_TRACK");
            intentFilter.addAction("POI_LONG_PRESSED_POS");
            intentFilter.addAction("UNZIP_CHECK");
            this.M.registerReceiver(this.N, intentFilter);
        }
        U0 = true;
        c2.o.g(this);
        f3617e1 = new c2.i(this);
        SensorManager sensorManager = (SensorManager) getSystemService("sensor");
        this.C = sensorManager;
        if (sensorManager != null) {
            this.f3645g0 = sensorManager.getDefaultSensor(1);
            this.f3647i0 = this.C.getDefaultSensor(9);
            Sensor defaultSensor = this.C.getDefaultSensor(2);
            this.f3646h0 = defaultSensor;
            Sensor sensor = this.f3645g0;
            if (sensor == null || defaultSensor == null) {
                O1 = false;
            } else {
                Sensor sensor2 = this.f3647i0;
                if (sensor2 != null) {
                    this.C.registerListener(this, sensor2, 3);
                } else {
                    this.C.registerListener(this, sensor, 3);
                }
                this.C.registerListener(this, this.f3646h0, 3);
            }
        }
        this.L = false;
        AdView adView = this.E;
        if (adView != null) {
            adView.resume();
            v2.e("GPS-M", "-->adView resume");
        }
        isAutoCenterMapOnGps = true;
        J0 = true;
        this.f3641c0 = false;
        A0();
        this.A = new Handler(Looper.getMainLooper());
        this.O.run();
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1 || sensorEvent.sensor.getType() == 9) {
            for (int i7 = 0; i7 < 3; i7++) {
                float[] fArr = this.f3643e0;
                fArr[i7] = (sensorEvent.values[i7] * 0.19999999f) + (fArr[i7] * 0.8f);
            }
            L1 = sensorEvent.accuracy;
        } else {
            if (sensorEvent.sensor.getType() != 2) {
                return;
            }
            for (int i8 = 0; i8 < 3; i8++) {
                float[] fArr2 = this.f3644f0;
                fArr2[i8] = (sensorEvent.values[i8] * 0.19999999f) + (fArr2[i8] * 0.8f);
            }
            K1 = sensorEvent.accuracy;
        }
        float[] fArr3 = new float[9];
        if (SensorManager.getRotationMatrix(fArr3, null, this.f3643e0, this.f3644f0)) {
            float[] fArr4 = new float[3];
            SensorManager.getOrientation(fArr3, fArr4);
            float f7 = N1;
            float degrees = (float) Math.toDegrees(fArr4[0]);
            N1 = degrees;
            if (Math.signum(f7) == Math.signum(degrees)) {
                N1 = (degrees * 0.19999999f) + (f7 * 0.8f);
            } else if (Math.signum(degrees) != Math.signum(f7)) {
                float fAbs = Math.abs(f7) + Math.abs(degrees);
                if (fAbs >= 360.0f - fAbs) {
                    Math.signum(f7);
                }
            }
            ((MapView) findViewById(R.id.mapsforge)).setRotation(-(M1 ? N1 : 0.0f));
        }
    }

    @Override // f.e, androidx.fragment.app.q, android.app.Activity
    public void onStop() {
        super.onStop();
        v2.e("GPS-M", "-->onStop  .........");
        AdView adView = this.E;
        if (adView != null) {
            adView.pause();
        }
        SensorManager sensorManager = this.C;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            this.C = null;
        }
        y3 y3Var = Y0;
        if (y3Var != null && y3Var.f3273h) {
            y3Var.b(findViewById(R.id.graph_window));
        }
        A0();
        if (this.P > 5) {
            this.P = 5;
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i7) {
        super.onTrimMemory(i7);
        if (i7 != 20 || this.z == null) {
            return;
        }
        U0 = true;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0036  */
    public String p0() {
        try {
            int i7;
            String string;
            String str;
            t5.a aVar;
            long j7;
            double d8;
            double d9;
            int length;
            int i8;
            String strValueOf;
            String strA;
            int i9;
            long j8;
            long jA = 0;
            DecimalFormat decimalFormat = new DecimalFormat("###0.00000");
            if (f3613a1 == null || IntServLocGpsPP.F == null || (i7 = O0) == 4) {
                return "---";
            }
        if (i7 == 3) {
            string = XmlPullParser.NO_NAMESPACE;
        } else if (i7 == 2) {
            string = getString(R.string.status_low_accuracy);
        } else if (i7 == 1) {
            string = getString(R.string.status_no_altitude);
        } else {
            string = XmlPullParser.NO_NAMESPACE;
        }
        String str2 = decimalFormat.format(f3613a1.getLatitude());
        String str3 = decimalFormat.format(f3613a1.getLongitude());
        StringBuilder sbA = android.support.v4.media.b.a(string);
        sbA.append(getString(R.string.latitude));
        sbA.append(" ");
        sbA.append(str2);
        sbA.append("°\n");
        sbA.append(getString(R.string.longitude));
        sbA.append(" ");
        String strA2 = sbA.toString() + str3 + "°";
        double latitude = f3613a1.getLatitude();
        double longitude = f3613a1.getLongitude();
        s5.a aVarA = s5.a.a(latitude);
        s5.a aVarA2 = s5.a.a(longitude);
        t5.a aVar2 = new t5.a(null);
        double d10 = aVarA.f17762i;
        double d11 = aVarA2.f17762i;
        aVar2.f18070c = XmlPullParser.NO_NAMESPACE;
        long j9 = (d10 < -1.5707963267948966d || d10 > 1.5707963267948966d) ? 1L : 0L;
        if (d11 < -3.141592653589793d || d11 > 6.283185307179586d) {
            j9 = 2;
        }
        long j10 = j9;
        if (j10 == 0) {
            if (d10 < -1.3962634015954636d || d10 > 1.4660765716752369d) {
                str = "---";
                aVar = aVar2;
                try {
                    t5.e eVarA = t5.e.a(s5.a.d(d10), s5.a.d(d11), aVar.f18069b);
                    jA = aVar.a(eVarA.f18104a, Double.valueOf(eVarA.f18105b), Double.valueOf(eVarA.f18106c), 5);
                } catch (Exception unused) {
                    j8 = 8192;
                }
            } else {
                try {
                    t5.f fVarA = t5.f.a(s5.a.d(d10), s5.a.d(d11), aVar2.f18069b);
                    str = "---";
                    aVar = aVar2;
                    try {
                        jA = aVar2.b(fVarA.f18108b, d10, fVarA.f18109c, fVarA.f18110d, 5);
                    } catch (Exception unused2) {
                        j10 = 4096;
                    }
                } catch (Exception unused3) {
                    str = "---";
                    aVar = aVar2;
                }
            }
            j8 = j10 | jA;
            j10 = j8;
            j7 = 0;
        } else {
            str = "---";
            aVar = aVar2;
            j7 = 0;
        }
        if (j10 != j7) {
            String strA3 = v5.a.a("Coord.MGRSConversionError");
            v5.a.d().severe(strA3);
            throw new IllegalArgumentException(strA3);
        }
        String str4 = aVar.f18070c;
        if (str4 == null) {
            String strA4 = v5.a.a("nullValue.StringIsNull");
            v5.a.d().severe(strA4);
            throw new IllegalArgumentException(strA4);
        }
        if (str4.length() == 0) {
            String strA5 = v5.a.a("generic.StringIsEmpty");
            v5.a.d().severe(strA5);
            throw new IllegalArgumentException(strA5);
        }
        String strB = strA2 + "\n\nMGRS: " + str4;
        c2.l lVar = new c2.l();
        double latitude2 = f3613a1.getLatitude();
        double longitude2 = f3613a1.getLongitude();
        Math.pow(4.054423713375902E13d, 0.5d);
        double dSqrt = Math.sqrt(1.0d - Math.pow(0.996647189296812d, 2.0d));
        double d12 = dSqrt * dSqrt;
        double d13 = d12 / (1.0d - d12);
        if (latitude2 < -90.0d || latitude2 > 90.0d || longitude2 < -180.0d || longitude2 >= 180.0d) {
            throw new IllegalArgumentException("Legal ranges: latitude [-90,90], longitude [-180,180).");
        }
        double d14 = (3.141592653589793d * latitude2) / 180.0d;
        Math.pow(1.0d - Math.pow(Math.sin(d14) * dSqrt, 2.0d), 1.5d);
        double dPow = 6378137.0d / Math.pow(1.0d - Math.pow(Math.sin(d14) * dSqrt, 2.0d), 0.5d);
        double d15 = ((longitude2 - ((((double) (longitude2 < 0.0d ? ((int) ((longitude2 + 180.0d) / 6.0d)) + 1 : ((int) (longitude2 / 6.0d)) + 31)) * 6.0d) - 183.0d)) * 3600.0d) / 10000.0d;
        double dSin = ((Math.sin(8.0d * d14) * 3.12705E-4d) + (((Math.sin(4.0d * d14) * 16.83261333d) + ((6367449.146d * d14) - (Math.sin(2.0d * d14) * 16038.42955d))) - (Math.sin(6.0d * d14) * 0.021984404d))) * 0.9996d;
        double dPow2 = (((Math.pow(4.84814E-6d, 2.0d) * (Math.cos(d14) * (Math.sin(d14) * dPow))) * 0.9996d) * 1.0E8d) / 2.0d;
        double dPow3 = ((Math.pow(Math.cos(d14), 4.0d) * Math.pow(d13, 2.0d) * 4.0d) + (Math.pow(Math.cos(d14), 2.0d) * 9.0d * d13) + (5.0d - Math.pow(Math.tan(d14), 2.0d))) * ((Math.pow(Math.cos(d14), 3.0d) * (Math.sin(d14) * (Math.pow(4.84814E-6d, 4.0d) * dPow))) / 24.0d) * 0.9996d * 1.0E16d;
        double dCos = Math.cos(d14) * dPow * 4.84814E-6d * 0.9996d * 10000.0d;
        double dPow4 = ((Math.pow(Math.cos(d14), 2.0d) * d13) + (1.0d - Math.pow(Math.tan(d14), 2.0d))) * (dPow / 6.0d) * Math.pow(Math.cos(d14) * 4.84814E-6d, 3.0d) * 0.9996d * 1.0E12d;
        Math.pow(4.84814E-6d * d15, 6.0d);
        Math.sin(d14);
        Math.pow(Math.cos(d14), 5.0d);
        Math.pow(Math.tan(d14), 2.0d);
        Math.pow(Math.tan(d14), 4.0d);
        Math.pow(Math.cos(d14), 2.0d);
        Math.pow(Math.sin(d14), 2.0d);
        if (longitude2 < 0.0d) {
            d8 = (longitude2 + 180.0d) / 6.0d;
            d9 = 1.0d;
        } else {
            d8 = longitude2 / 6.0d;
            d9 = 31.0d;
        }
        String strValueOf2 = String.valueOf((int) (d8 + d9));
        if (strValueOf2.length() == 1) {
            strValueOf2 = ("0" + strValueOf2);
        }
        c2.l.a aVar3 = new c2.l.a(lVar);
        int i10 = (int) latitude2;
        if (i10 >= 0) {
            int length2 = aVar3.f3040c.length;
            length = 0;
            while (true) {
                if (length < length2) {
                    int[] iArr = aVar3.f3041d;
                    if (i10 == iArr[length]) {
                        i8 = -1;
                        break;
                    } else if (i10 > iArr[length]) {
                        length++;
                    } else {
                        i8 = -1;
                        length--;
                        break;
                    }
                } else {
                    i8 = -1;
                    length = -2;
                    break;
                }
            }
        } else {
            int length3 = aVar3.f3038a.length;
            length = 0;
            while (true) {
                if (length < length3) {
                    int[] iArr2 = aVar3.f3039b;
                    if (i10 == iArr2[length]) {
                        i8 = -1;
                        break;
                    } else if (i10 < iArr2[length]) {
                        i8 = -1;
                        length--;
                        break;
                    } else {
                        length++;
                    }
                } else {
                    i8 = -1;
                    length = -2;
                    break;
                }
            }
        }
        if (length == i8) {
            length = 0;
        }
        if (i10 >= 0) {
            if (length == -2) {
                length = aVar3.f3040c.length - 1;
            }
            strValueOf = String.valueOf(aVar3.f3040c[length]);
        } else {
            if (length == -2) {
                length = aVar3.f3038a.length - 1;
            }
            strValueOf = String.valueOf(aVar3.f3038a[length]);
        }
        double dPow5 = (Math.pow(d15, 3.0d) * dPow4) + (dCos * d15) + 500000.0d;
        double dPow6 = (Math.pow(d15, 4.0d) * dPow3) + (dPow2 * d15 * d15) + dSin;
        if (latitude2 < 0.0d) {
            dPow6 += 1.0E7d;
        }
        String[] strArrSplit = (strValueOf2 + " " + strValueOf + " " + ((int) dPow5) + " " + ((int) dPow6)).split(" ");
        StringBuilder sbD = new StringBuilder(strB + "\n\nUTM " + (getString(R.string.zone) + strArrSplit[0] + strArrSplit[1] + "\n\t" + getString(R.string.east) + " " + strArrSplit[2] + " " + getString(R.string.north) + " " + strArrSplit[3]) + "\n\n");
        sbD.append(getString(R.string.accuracy));
        sbD.append(":");
        String string2 = sbD.toString();
        if (f3613a1 == null || IntServLocGpsPP.F == null || !((i9 = O0) == 3 || i9 == 2)) {
            D1 = 1000;
            strA = (string2 + str);
        } else {
            float accuracy = (IntServLocGpsPP.F.getAccuracy() + f3613a1.getAccuracy()) / 2.0f;
            if (accuracy < 10.0f) {
                accuracy = 10.0f;
            }
            D1 = (int) accuracy;
            DecimalFormat decimalFormat2 = new DecimalFormat("###0");
            StringBuilder sbA2 = android.support.v4.media.b.a(string2);
            sbA2.append(decimalFormat2.format(W(D1)));
            sbA2.append(y());
            strA = sbA2.toString();
        }
        return (strA + "\nGeodetic Ref.System WGS84");
    } catch (Throwable t) {
        v2.e("GPS-M", "Error in p0 coordinate formatting: " + t);
        if (f3613a1 != null) {
            return String.format(Locale.US, "Lat: %.5f°\nLon: %.5f°\nAlt: %.1f m\nWGS84",
                    f3613a1.getLatitude(), f3613a1.getLongitude(), f3613a1.getAltitude());
        }
        return "---";
    }
}

    public void q0() {
        AgpsApplication.f3575r = new x3(1);
        s0("REC_ON", true);
        AgpsApplication.f3576s = 3;
        V0 = true;
        IntServLocGpsPP.E = 5;
        ((TextView) findViewById(R.id.data20)).setText("00:00:00");
        U0 = true;
        I();
    }

    public void updateLavagnaVisibility() {
        View viewFindViewById = findViewById(R.id.gps_data_lavagna);
        View viewFindViewById2 = findViewById(R.id.loaded_track_lavagna);
        View viewFindViewById3 = findViewById(R.id.recorder_frame);
        int iC = r.g.c(this.B);
        if (iC == 0) {
            viewFindViewById.setVisibility(4);
            viewFindViewById2.setVisibility(0);
            viewFindViewById3.setVisibility(4);
        } else if (iC == 1) {
            viewFindViewById.setVisibility(4);
            viewFindViewById2.setVisibility(4);
            viewFindViewById3.setVisibility(0);
        } else if (iC != 2) {
            viewFindViewById.setVisibility(4);
            viewFindViewById2.setVisibility(4);
            viewFindViewById3.setVisibility(4);
        } else {
            viewFindViewById.setVisibility(0);
            viewFindViewById2.setVisibility(4);
            viewFindViewById3.setVisibility(4);
        }
    }

    public final void v0() {
        int i7;
        MyGpsButtonView myGpsButtonView = (MyGpsButtonView) findViewById(R.id.button_gps_info);
        Location location = f3613a1;
        if (location == null) {
            i7 = 4;
        } else if (location.hasAltitude() && f3613a1.hasAccuracy()) {
            i7 = f3613a1.getAccuracy() > 30.0f ? 2 : 3;
        } else {
            i7 = 1;
        }
        if (i7 != J1) {
            StringBuilder sbA = android.support.v4.media.b.a("Location quality changed from ");
            sbA.append(b2.j.b(J1));
            sbA.append(" to ");
            sbA.append(b2.j.b(i7));
            v2.e("GPS-M", sbA.toString());
        }
        J1 = i7;
        int iC = r.g.c(i7);
        if (iC == 0) {
            myGpsButtonView.setBackground(androidx.core.content.ContextCompat.getDrawable(this, R.drawable.button_gps_yellow));
            O0 = 1;
            return;
        }
        if (iC == 1) {
            myGpsButtonView.setBackground(androidx.core.content.ContextCompat.getDrawable(this, R.drawable.button_gps_yellow));
            O0 = 2;
            return;
        }
        if (iC == 2) {
            myGpsButtonView.setBackground(androidx.core.content.ContextCompat.getDrawable(this, R.drawable.button_gps));
            O0 = 3;
        } else if (iC != 3) {
            myGpsButtonView.setBackground(androidx.core.content.ContextCompat.getDrawable(this, R.drawable.button_gps_red));
            O0 = 4;
        } else if (f3613a1 == null) {
            myGpsButtonView.setBackground(androidx.core.content.ContextCompat.getDrawable(this, R.drawable.button_gps_yellow));
            O0 = 4;
        } else {
            myGpsButtonView.setBackground(androidx.core.content.ContextCompat.getDrawable(this, R.drawable.button_gps_yellow));
            O0 = 2;
        }
    }

    public final void w(Location location, boolean z, int i7) {
        String string;
        String str;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        LayoutInflater layoutInflater = getLayoutInflater();
        Location location2 = new Location("Dummy");
        location2.set(location);
        View viewInflate = layoutInflater.inflate(R.layout.new_poi_dialog, (ViewGroup) null);
        if (i7 == 1) {
            builder.setTitle(getResources().getString(R.string.new_poi));
        } else if (i7 == 3) {
            builder.setTitle(getResources().getString(R.string.new_waypoint_free));
        } else if (i7 == 2) {
            builder.setTitle(getResources().getString(R.string.new_waypoint_on_track));
        }
        builder.setView(viewInflate);
        EditText editText = (EditText) viewInflate.findViewById(R.id.poi_name);
        if (z) {
            string = "POI";
        } else {
            StringBuilder sbA = android.support.v4.media.b.a("POI-");
            sbA.append(String.valueOf(AgpsApplication.f3575r.f3246k));
            string = sbA.toString();
        }
        String str2 = string;
        editText.setHint(str2);
        EditText editText2 = (EditText) viewInflate.findViewById(R.id.poi_description);
        DecimalFormat decimalFormat = new DecimalFormat("###0");
        DecimalFormat decimalFormat2 = new DecimalFormat("###0.0000");
        Locale locale = Locale.US;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", locale);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm", locale);
        editText2.setText(XmlPullParser.NO_NAMESPACE);
        if (location.getTime() != 0) {
            str = getString(R.string.date) + simpleDateFormat.format(Long.valueOf(location.getTime())) + "\n" + getString(R.string.time_h) + simpleDateFormat2.format(Long.valueOf(location.getTime()));
        } else {
            str = "----";
        }
        ((TextView) viewInflate.findViewById(R.id.poi_data)).setText((getString(R.string.distance) + "=" + new DecimalFormat("##0.00").format(E1) + x()) + "\n" + str + "\n" + getString(R.string.latitude_short) + "=" + decimalFormat2.format(location.getLatitude()) + "° \n" + getString(R.string.longitude_short) + "=" + decimalFormat2.format(location.getLongitude()) + "°\n" + getString(R.string.altitude_short) + "=" + decimalFormat.format(W(location.getAltitude())) + y());
        builder.setPositiveButton(getResources().getString(R.string.ok), new t0(this, editText, str2, z, editText2, location2));
        builder.setNegativeButton(getResources().getString(R.string.cancel), new c2.DialogDismissListener(this));
        builder.show();
    }

    public final void w0(String str) {
        if (str == null) {
            return;
        }
        View viewFindViewById = findViewById(R.id.my_snack_coordinator_layout);
        this.f3664y = viewFindViewById;
        Snackbar snackbarJ = Snackbar.make(viewFindViewById, str, Snackbar.LENGTH_SHORT);
        snackbarJ.getView().setBackgroundTintList(ColorStateList.valueOf(-10510688));
        snackbarJ.show();
        v2.e("GPS-M", "Snack:" + str);
    }

    public void x0(x3 x3Var) {
        g0();
        try {
            X0.acquire(1);
            v2.e("GPS-I", "Show photos of track");
            x3Var.f3247l.clear();
            for (h3 h3Var : T1) {
                if (x3Var.f(h3Var.f3003e)) {
                    x3Var.f3247l.add(h3Var);
                    v2.e("GPS-I", "-> foto: " + h3Var.f3002d.toString());
                }
            }
            U0 = true;
        } catch (Exception e8) {
            androidx.appcompat.widget.d.c("Error:", e8, "GPS-M");
        }
        X0.release();
    }

    public void y0() {
        String strA;
        if (m2.f3052r.size() == 0) {
            strA = getResources().getString(R.string.no_current_maps);
        } else {
            strA = getResources().getString(R.string.current_map) + " ";
            boolean z = true;
            for (y2 y2Var : m2.f3052r) {
                if (z) {
                    z = false;
                } else {
                    strA = (strA + " + ");
                }
                StringBuilder sbA = new StringBuilder(strA);
                sbA.append(y2Var.f3262i);
                strA = sbA.toString();
            }
        }
        w0(strA);
    }

    public void z0() {
        if (IntServLocGpsPP.A) {
            return;
        }
        String packageName = getPackageName();
        PowerManager powerManager = (PowerManager) getSystemService("power");
        Intent intent = new Intent();
        if (!powerManager.isIgnoringBatteryOptimizations(packageName)) {
            intent.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
            intent.setData(Uri.parse("package:" + packageName));
            try {
                startActivity(intent);
            } catch (Exception e8) {
                androidx.appcompat.widget.d.c("askForIgnoreBatteryOptimisation error: ", e8, "GPS-I");
            }
        }
        if (b0.a.a(this, "android.permission.ACCESS_FINE_LOCATION") == 0 && AgpsApplication.f3573p == null) {
            AgpsApplication.f3573p = new Intent(this, (Class<?>) IntServLocGpsPP.class);
            v2.e("GPS-M", "starting IntentServiceLocGps -------------");
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(AgpsApplication.f3573p);
            } else {
                startService(AgpsApplication.f3573p);
            }
            this.W = bindService(AgpsApplication.f3573p, this.X, 1);
            StringBuilder sbA = android.support.v4.media.b.a("starting IntentServiceLocGps ------------BIND=");
            sbA.append(this.W);
            v2.e("GPS-M", sbA.toString());
        }
    }
}
