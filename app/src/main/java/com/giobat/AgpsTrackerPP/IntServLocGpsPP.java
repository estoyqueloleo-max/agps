package com.giobat.AgpsTrackerPP;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import androidx.appcompat.widget.d0;
import androidx.appcompat.widget.f0;
import c2.i3;
import c2.p;
import c2.r;
import c2.v2;
import c2.x3;
import c2.z;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class IntServLocGpsPP extends Service implements SensorEventListener {
    public static boolean A = false;
    public static double C;
    public static AudioManager I;
    public static MediaPlayer J;
    public static String K;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static LocationManager f3587y;
    public static OnNmeaMessageListener z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public p f3589i;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public FusedLocationProviderClient f3594o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public z f3595p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public SensorManager f3596q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public LocationCallback f3598s;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public double f3602w;
    public static int B = 0;
    public static long D = 0;
    public static int E = 5;
    public static Location F = new Location("FUSED_COPY");
    public static Location G = null;
    public static long H = 0;
    public static boolean L = false;
    public static boolean M = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Location f3588h = new Location("Dummy");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f3590j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f3591k = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f3592l = 0;
    public long m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3593n = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final IBinder f3597r = new d();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f3599t = false;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f3600u = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f3601v = 0;
    public final CharSequence x = "gpsService Notification";

    public class a extends LocationCallback {
        public a() {
        }

        @Override
        public void onLocationResult(LocationResult locationResult) {
            IntServLocGpsPP intServLocGpsPP = IntServLocGpsPP.this;
            if (locationResult != null) {
                Location location = locationResult.getLastLocation();
                intServLocGpsPP.c(location);
            }
        }
    }

    public class b implements OnNmeaMessageListener {
        public b() {
        }

        @Override // android.location.OnNmeaMessageListener
        public void onNmeaMessage(String str, long j7) {
            IntServLocGpsPP intServLocGpsPP = IntServLocGpsPP.this;
            Objects.requireNonNull(intServLocGpsPP);
            if (str == null || str.length() == 0) {
                return;
            }
            IntServLocGpsPP.M = true;
            try {
                String[] strArrSplit = str.split(",");
                if (strArrSplit[0].substring(3, 6).contentEquals("GGA")) {
                    v2.e("GPS-S", str);
                    if (strArrSplit.length < 15) {
                        v2.e("GPS-S", "NMEA GPGGA len error");
                        return;
                    }
                    if (MainActivity.f3613a1 == null) {
                        return;
                    }
                    boolean z = AgpsApplication.f3566h;
                    String str2 = strArrSplit[6];
                    boolean z7 = str2.equals("1") || str2.equals("2");
                    boolean z8 = strArrSplit[9].length() != 0;
                    boolean z9 = strArrSplit[1].length() >= 6;
                    if (strArrSplit[2].equals(XmlPullParser.NO_NAMESPACE) || strArrSplit[4].equals(XmlPullParser.NO_NAMESPACE) || strArrSplit[9].equals(XmlPullParser.NO_NAMESPACE)) {
                        return;
                    }
                    if (!z9) {
                        StringBuilder sbA = android.support.v4.media.b.a(" wrong TimeLen of");
                        sbA.append(strArrSplit[1]);
                        sbA.append("==> NMEA skipped!!");
                        v2.e("GPS-S", sbA.toString());
                        return;
                    }
                    if (Math.abs(j7 - IntServLocGpsPP.H) > 1000000) {
                        IntServLocGpsPP.L = true;
                        v2.e("GPS-S", "UTC Time discrepancy  timestamp=" + j7 + " lastTimeFused=" + IntServLocGpsPP.H + " lastTimeGoodAltitude=" + intServLocGpsPP.f3590j);
                    } else {
                        IntServLocGpsPP.L = false;
                    }
                    if (!z7) {
                        v2.e("GPS-S", "Nmea NOT a good quality =" + str2 + "==> NMEA skipped!!");
                        return;
                    }
                    if (!z8) {
                        v2.e("GPS-S", "Nmea NO Altitude ==> NMEA skipped!!");
                        return;
                    }
                    Location location = new Location("NMEA");
                    try {
                        double dA = intServLocGpsPP.a(strArrSplit[2]);
                        if (dA == -1.0d) {
                            return;
                        }
                        if (!strArrSplit[3].equals("N")) {
                            dA = -dA;
                        }
                        double dB = intServLocGpsPP.b(strArrSplit[4]);
                        if (dB == -1.0d) {
                            return;
                        }
                        if (!strArrSplit[5].equals("E")) {
                            dB = -dB;
                        }
                        location.setTime(j7);
                        location.setLatitude(dA);
                        location.setLongitude(dB);
                        double hdop = 1.0d;
                        if (strArrSplit.length > 8 && !strArrSplit[8].isEmpty()) {
                            try {
                                hdop = Double.parseDouble(strArrSplit[8]);
                            } catch (NumberFormatException ignored) {
                            }
                        }
                        Double dValueOf = Double.valueOf(hdop);
                        double rawAlt = 0.0d;
                        if (strArrSplit.length > 9 && !strArrSplit[9].isEmpty()) {
                            try {
                                rawAlt = Double.parseDouble(strArrSplit[9]);
                            } catch (NumberFormatException ignored) {
                            }
                        }
                        Double dValueOf2 = Double.valueOf(rawAlt);
                        double geoidSep = 0.0d;
                        if (strArrSplit.length > 11 && !strArrSplit[11].isEmpty()) {
                            try {
                                geoidSep = Double.parseDouble(strArrSplit[11]);
                            } catch (NumberFormatException ignored) {
                            }
                        }
                        Double dValueOf3 = Double.valueOf(geoidSep);
                        double dDoubleValue = dValueOf2.doubleValue() + dValueOf3.doubleValue();
                        intServLocGpsPP.f3602w = dDoubleValue;
                        location.setAltitude(dDoubleValue);
                        location.setAccuracy(((int) (dValueOf.doubleValue() * 4.0d)) + 10);
                        DecimalFormat decimalFormat = new DecimalFormat("##0.00000");
                        DecimalFormat decimalFormat2 = new DecimalFormat("##0.0");
                        String str3 = "NMEA: ts=" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.US).format(Long.valueOf(j7)) + " Qual=" + str2 + " HDOP=" + decimalFormat2.format(dValueOf) + " Geoid Alt=" + decimalFormat2.format(intServLocGpsPP.f3602w) + "m HGeoid=" + decimalFormat2.format(dValueOf3) + " EGM96=" + decimalFormat2.format(IntServLocGpsPP.C) + " Lat=" + decimalFormat.format(dA) + " Lon=" + decimalFormat.format(dB);
                        IntServLocGpsPP.K = str3;
                        v2.e("GPS-S", str3);
                        intServLocGpsPP.c(location);
                    } catch (Exception e8) {
                        androidx.appcompat.widget.d.c("NMEA GPGGA  ERROR: ", e8, "GPS-S");
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            try {
                new r(IntServLocGpsPP.this.getApplicationContext()).g(MainActivity.f3615c1, AgpsApplication.f3575r, "tmp", "1.8", "tmp", "---");
                v2.e("GPS-S", "Gpx periodic save done!");
            } catch (Exception e8) {
                d0.c("Gpx SaveAs error:", e8, MainActivity.f3625m1);
            }
        }
    }

    public class d extends Binder {
        public d() {
        }
    }

    public double a(String str) {
        Double dValueOf;
        Double dValueOf2;
        try {
            if (!str.substring(0, 1).equals("-")) {
                Double dValueOf3 = Double.valueOf(Double.parseDouble(str.substring(0, 2)));
                dValueOf = Double.valueOf(Double.parseDouble(str.substring(2)));
                dValueOf2 = dValueOf3;
            } else {
                if (!str.substring(3, 4).equals("-")) {
                    return -1.0d;
                }
                dValueOf2 = Double.valueOf(Double.parseDouble(str.substring(1, 3)) - 1.0d);
                dValueOf = Double.valueOf(Double.parseDouble(str.substring(4)));
            }
            return (dValueOf.doubleValue() / 60.0d) + dValueOf2.doubleValue();
        } catch (Exception e8) {
            androidx.appcompat.widget.d.c("NMEA GPGGA  ERROR Lat: ", e8, "GPS-S");
            return -1.0d;
        }
    }

    public double b(String str) {
        Double dValueOf;
        Double dValueOf2;
        try {
            if (!str.substring(0, 1).equals("-")) {
                Double dValueOf3 = Double.valueOf(Double.parseDouble(str.substring(0, 3)));
                dValueOf = Double.valueOf(Double.parseDouble(str.substring(3)));
                dValueOf2 = dValueOf3;
            } else {
                if (!str.substring(4, 5).equals("-")) {
                    return -1.0d;
                }
                dValueOf2 = Double.valueOf(Double.parseDouble(str.substring(1, 4)) - 1.0d);
                dValueOf = Double.valueOf(Double.parseDouble(str.substring(5)));
            }
            return (dValueOf.doubleValue() / 60.0d) + dValueOf2.doubleValue();
        } catch (Exception e8) {
            androidx.appcompat.widget.d.c("NMEA GPGGA  ERROR Lon: ", e8, "GPS-S");
            return -1.0d;
        }
    }

    /* JADX WARN: Code duplicated, block: B:125:0x045d  */
    /* JADX WARN: Code duplicated, block: B:126:0x0460  */
    /* JADX WARN: Code duplicated, block: B:129:0x0466  */
    /* JADX WARN: Code duplicated, block: B:130:0x046b  */
    /* JADX WARN: Code duplicated, block: B:132:0x0473  */
    /* JADX WARN: Code duplicated, block: B:134:0x047c  */
    /* JADX WARN: Code duplicated, block: B:136:0x0485  */
    /* JADX WARN: Code duplicated, block: B:140:0x0494  */
    /* JADX WARN: Code duplicated, block: B:142:0x04a2  */
    /* JADX WARN: Code duplicated, block: B:151:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v43, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v90 */
    /* JADX WARN: Type inference failed for: r0v91 */
    public final void c(Location location) {
        double d8;
        int i7;
        boolean z7;
        boolean z8;
        x3 x3Var;
        boolean z9;
        long jCurrentTimeMillis;
        int i8;
        MediaPlayer mediaPlayer;
        x3 x3Var2;
        Location location2 = new Location("dummy");
        boolean z10 = AgpsApplication.f3566h;
        location2.set(location);
        G.set(location2);
        this.f3591k++;
        StringBuilder sbA = android.support.v4.media.b.a("N=");
        sbA.append(this.f3591k);
        sbA.append(" Provider=");
        sbA.append(location2.getProvider());
        sbA.append("...................");
        v2.e("GPS-S", sbA.toString());
        if (location2.getProvider().equalsIgnoreCase("FUSED")) {
            DecimalFormat decimalFormat = new DecimalFormat("##0.00000");
            StringBuilder sbA2 = android.support.v4.media.b.a("Provider: FUSED. Lat=");
            sbA2.append(decimalFormat.format(location2.getLatitude()));
            sbA2.append(" Lon=");
            sbA2.append(decimalFormat.format(location2.getLongitude()));
            sbA2.append(" Alt=");
            sbA2.append((int) location2.getAltitude());
            sbA2.append(" Accu=");
            sbA2.append((int) location2.getAccuracy());
            sbA2.append("-----");
            v2.e("GPS-S", sbA2.toString());
            F.set(location2);
            long time = location2.getTime();
            H = time;
            if (time - D < 2000) {
                StringBuilder sbA3 = android.support.v4.media.b.a("==>");
                sbA3.append(this.f3591k);
                sbA3.append(":skipped fused: NMEA is running well");
                v2.e("GPS-S", sbA3.toString());
                return;
            }
        }
        if (location2.getProvider().equalsIgnoreCase("NMEA")) {
            D = location2.getTime();
        }
        if (location2.getTime() < this.f3590j) {
            StringBuilder sbA4 = android.support.v4.media.b.a("==>");
            sbA4.append(this.f3591k);
            sbA4.append(":skipped for bad Time");
            v2.e("GPS-S", sbA4.toString());
            return;
        }
        if (!location2.hasAltitude() || !location2.hasAccuracy()) {
            v2.e("GPS-S", "------------->  has no Altitude or Accuracy");
            MainActivity.f3613a1.set(location2);
            AgpsApplication.f3572o = false;
            E = 3;
            this.f3595p.c();
            return;
        }
        if (p.f3107e) {
            v2.e("GPS-S", " isEGMloading=true");
            MainActivity.f3613a1.set(location2);
            AgpsApplication.f3572o = false;
            E = 3;
            this.f3595p.c();
            return;
        }
        p pVar = this.f3589i;
        double latitude = location2.getLatitude();
        double longitude = location2.getLongitude();
        Objects.requireNonNull(pVar);
        if (latitude < -90.0d || latitude > 90.0d || longitude < -360.0d || longitude > 360.0d || !p.f3106d) {
            d8 = p.f3108f;
        } else {
            double d9 = 90.0d - latitude;
            if (longitude < 0.0d) {
                longitude += 360.0d;
            }
            int i9 = ((int) (longitude / 0.25d)) + 3;
            int i10 = ((int) (d9 / 0.25d)) + 3;
            short[][] sArr = pVar.f3109a;
            short s7 = sArr[i9][i10];
            int i11 = i10 + 1;
            short s8 = sArr[i9][i11];
            int i12 = i9 + 1;
            short s9 = sArr[i12][i10];
            double d10 = d9 % 0.25d;
            double d11 = ((((double) (s8 - s7)) * d10) / 0.25d) + ((double) s7);
            d8 = ((((longitude % 0.25d) * ((((((double) (sArr[i12][i11] - s9)) * d10) / 0.25d) + ((double) s9)) - d11)) / 0.25d) + d11) / 100.0d;
        }
        C = d8;
        if (d8 == p.f3108f) {
            v2.e("GPS-S", "----------------------------------------->  EGM96 invalid data");
            AgpsApplication.f3572o = false;
            E = 3;
            this.f3595p.c();
            return;
        }
        location2.setAltitude(location2.getAltitude() - C);
        if (location2.getAccuracy() > 30.0f) {
            StringBuilder sbA5 = android.support.v4.media.b.a("Poor Accuracy=");
            sbA5.append(location2.getAccuracy());
            v2.e("GPS-S", sbA5.toString());
            MainActivity.f3613a1.set(location2);
            AgpsApplication.f3572o = false;
            E = 3;
            this.f3595p.c();
            return;
        }
        z zVar = this.f3595p;
        Objects.requireNonNull(zVar);
        location2.setAltitude(zVar.a(location2.getAltitude()));
        Location location3 = new Location("Filter");
        location3.set(location2);
        int i13 = zVar.f3287c;
        if (i13 < 2) {
            zVar.f3285a[i13] = location3;
            i7 = 1;
            zVar.f3289e = true;
        } else {
            double latitude2 = (location2.getLatitude() * 0.5d) + (zVar.f3285a[1].getLatitude() * 0.3d) + (zVar.f3285a[0].getLatitude() * 0.2d);
            double longitude2 = (location2.getLongitude() * 0.5d) + (zVar.f3285a[1].getLongitude() * 0.3d) + (zVar.f3285a[0].getLongitude() * 0.2d);
            location3.setLatitude(latitude2);
            location3.setLongitude(longitude2);
            Location[] locationArr = zVar.f3285a;
            locationArr[0] = locationArr[1];
            locationArr[1] = location3;
            i7 = 1;
        }
        zVar.f3287c += i7;
        int iDistanceTo = (int) location3.distanceTo(MainActivity.f3613a1);
        if (iDistanceTo > 200) {
            v2.e("GPS-S", " ???? Large location change: " + iDistanceTo + "m");
            MainActivity.f3613a1.set(location2);
            this.f3595p.c();
        } else {
            MainActivity.f3613a1.set(location3);
        }
        boolean z11 = this.f3595p.f3289e;
        if (!location2.getProvider().equalsIgnoreCase("FUSED")) {
            this.f3590j = MainActivity.f3613a1.getTime();
        }
        int i14 = E;
        E = i14 - 1;
        if (i14 > 0) {
            StringBuilder sbA6 = android.support.v4.media.b.a(" Stabilisation CountDown");
            sbA6.append(E);
            v2.e("GPS-S", sbA6.toString());
            AgpsApplication.f3572o = false;
            Location location4 = MainActivity.f3613a1;
            if (location4 != null) {
                this.f3588h.set(location4);
                return;
            }
            return;
        }
        if (M && L && location2.getProvider().equalsIgnoreCase("FUSED")) {
            StringBuilder sbA7 = android.support.v4.media.b.a("==>");
            sbA7.append(this.f3591k);
            sbA7.append(":skipped fused: Time discrepancy");
            v2.e("GPS-S", sbA7.toString());
        } else if (AgpsApplication.f3575r != null && AgpsApplication.f3568j) {
            DecimalFormat decimalFormat2 = new DecimalFormat("###0.00");
            double dDistanceTo = MainActivity.f3613a1.distanceTo(this.f3588h);
            StringBuilder sbA8 = android.support.v4.media.b.a("Accuracy HIGH  =");
            sbA8.append(location2.getAccuracy());
            sbA8.append(" distance=");
            sbA8.append(decimalFormat2.format(dDistanceTo));
            v2.e("GPS-S", sbA8.toString());
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            if ((dDistanceTo > 5.0d && jCurrentTimeMillis2 - this.f3588h.getTime() >= 2000) || MainActivity.V0) {
                try {
                    v2.e("GPS-S", "*** VALID sample N." + AgpsApplication.f3569k + " added to current track");
                    MainActivity.V0 = false;
                    AgpsApplication.f3569k = AgpsApplication.f3569k + 1;
                    Location location5 = new Location("toStore");
                    location5.set(MainActivity.f3613a1);
                    AgpsApplication.f3575r.b(location5);
                    this.f3588h.set(location5);
                } catch (Exception e8) {
                    v2.e("GPS-S", "Exception " + e8);
                    v2.e("GPS-S", "<====================================================================================================>");
                }
                x3 x3Var3 = AgpsApplication.f3575r;
                if (x3Var3 != null && x3Var3.i() > 10 && (jCurrentTimeMillis2 - this.f3592l > 300000 || (((long) AgpsApplication.f3569k) - this.m > 20 && AgpsApplication.f3568j))) {
                    new Thread(new c()).start();
                    this.f3592l = jCurrentTimeMillis2;
                    this.m = AgpsApplication.f3569k;
                }
            }
        }
        if (!AgpsApplication.m || (x3Var2 = AgpsApplication.f3574q) == null || x3Var2.i() <= 1) {
            if (AgpsApplication.f3571n && (x3Var = AgpsApplication.f3575r) != null) {
                z8 = true;
                if (x3Var.i() <= 1) {
                    z7 = false;
                } else if (new i3(30.0d, MainActivity.F1, MainActivity.G1).a(AgpsApplication.f3575r, MainActivity.f3613a1)) {
                    z9 = false;
                    this.f3593n = 0;
                } else {
                    this.f3593n++;
                    z8 = true;
                }
                if (this.f3593n >= 3) {
                    AgpsApplication.f3572o = z8;
                } else {
                    AgpsApplication.f3572o = false;
                }
                if (!AgpsApplication.f3572o) {
                    this.f3601v = 0;
                    this.f3599t = false;
                    return;
                }
                jCurrentTimeMillis = System.currentTimeMillis();
                if (!this.f3599t) {
                    v2.e("GPS-S", "*# Follow Path Alarm Beep Start!!");
                    i8 = this.f3601v;
                    this.f3601v = i8 + 1;
                    if (i8 < 4) {
                        J.start();
                    }
                    this.f3599t = true;
                    this.f3600u = jCurrentTimeMillis;
                }
                if (jCurrentTimeMillis - this.f3600u > 5000) {
                    v2.e("GPS-S", "*# Stop Beep");
                    mediaPlayer = J;
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        J.stop();
                    }
                    this.f3599t = false;
                    return;
                }
                return;
            }
            z7 = false;
            z8 = true;
            this.f3593n = 0;
            if (this.f3593n >= 3) {
                AgpsApplication.f3572o = z8;
            } else {
                AgpsApplication.f3572o = false;
            }
            if (!AgpsApplication.f3572o) {
                this.f3601v = 0;
                this.f3599t = false;
                return;
            }
            jCurrentTimeMillis = System.currentTimeMillis();
            if (!this.f3599t) {
                if (jCurrentTimeMillis - this.f3600u > 5000) {
                    v2.e("GPS-S", "*# Stop Beep");
                    mediaPlayer = J;
                    if (mediaPlayer != null) {
                        J.stop();
                    }
                    this.f3599t = false;
                    return;
                }
                return;
            }
            v2.e("GPS-S", "*# Follow Path Alarm Beep Start!!");
            i8 = this.f3601v;
            this.f3601v = i8 + 1;
            if (i8 < 4) {
                J.start();
            }
            this.f3599t = true;
            this.f3600u = jCurrentTimeMillis;
        }
        if (new i3(30.0d).a(AgpsApplication.f3574q, MainActivity.f3613a1)) {
            this.f3593n = 0;
        } else {
            this.f3593n++;
        }
        z9 = false;
        z8 = true;
        if (this.f3593n >= 3) {
            AgpsApplication.f3572o = z8;
        } else {
            AgpsApplication.f3572o = false;
        }
        if (!AgpsApplication.f3572o) {
            this.f3601v = 0;
            this.f3599t = false;
            return;
        }
        jCurrentTimeMillis = System.currentTimeMillis();
        if (!this.f3599t) {
            if (jCurrentTimeMillis - this.f3600u > 5000) {
                v2.e("GPS-S", "*# Stop Beep");
                mediaPlayer = J;
                if (mediaPlayer != null) {
                    J.stop();
                }
                this.f3599t = false;
                return;
            }
            return;
        }
        v2.e("GPS-S", "*# Follow Path Alarm Beep Start!!");
        i8 = this.f3601v;
        this.f3601v = i8 + 1;
        if (i8 < 4) {
            J.start();
        }
        this.f3599t = true;
        this.f3600u = jCurrentTimeMillis;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i7) {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        v2.e("GPS-S", "onBind()");
        return this.f3597r;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        v2.e("GPS-S", "onDestroy()");
        A = false;
        SensorManager sensorManager = this.f3596q;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            this.f3596q = null;
        }
        if (this.f3594o != null && this.f3598s != null) {
            this.f3594o.removeLocationUpdates(this.f3598s);
        }
        OnNmeaMessageListener onNmeaMessageListener = z;
        if (onNmeaMessageListener != null && f3587y != null) {
            try {
                f3587y.removeNmeaListener(onNmeaMessageListener);
            } catch (Exception ignored) {
            }
            z = null;
        }
        try {
            stopForeground(true);
        } catch (Exception ignored) {
        }
        stopSelf();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        v2.e("GPS-S", "onTaskRemoved()");
        if (!AgpsApplication.f3568j) {
            v2.e("GPS-S", "No recording active, stopping service on task removed");
            if (this.f3594o != null && this.f3598s != null) {
                this.f3594o.removeLocationUpdates(this.f3598s);
            }
            onDestroy();
            stopSelf();
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        B++;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        v2.e("GPS-S", "Start service IntServLocGpsPP");
        A = true;
        Intent intent2 = new Intent(this, (Class<?>) MainActivity.class);
        intent2.setFlags(603979776);
        intent2.putExtra("STARTED_FROM_NOTIF", true);
        PendingIntent activity = PendingIntent.getActivity(getApplicationContext(), 0, intent2, 67108864);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("INT_SERV_CH_IC", this.x, 1);
            notificationChannel.setDescription("intServ task");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(-65536);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        } else {
            NotificationManagerCompat.from(this);
        }
        String string = getString(R.string.app_title);
        String string2 = getString(R.string.app_description);
        v2.e("GPS-S", "buildForegroundNotification()");
        NotificationCompat.Builder jVar = new NotificationCompat.Builder(getApplicationContext(), "INT_SERV_CH_IC")
            .setOngoing(true)
            .setContentTitle(string)
            .setContentText(string2)
            .setSmallIcon(R.drawable.hikerbw64)
            .setContentIntent(activity)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setColor(-65536);
        startForeground(1793, jVar.build());
        this.f3595p = new z();
        this.f3594o = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        this.f3598s = new a();
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(1000L);
            locationRequest.setFastestInterval(100L);
            locationRequest.setMaxWaitTime(10000L);
            v2.e("GPS-S", "starting LocationUpdates() ");
            try {
                this.f3594o.requestLocationUpdates(locationRequest, this.f3598s, Looper.myLooper());
            } catch (SecurityException e8) {
                MainActivity.f3625m1.a("Location permission lost. Could not request updates. " + e8);
            }
        } else {
            v2.e("GPS-S", "No Permissions: can't write log file");
        }
        this.f3589i = p.f3105c;
        this.f3589i.a(getResources().openRawResource(getResources().getIdentifier("ww15mgh", "raw", getPackageName())));
        Handler handler = new Handler(Looper.getMainLooper());
        LocationManager locationManager = (LocationManager) getSystemService("location");
        f3587y = locationManager;
        b bVar = new b();
        z = bVar;
        if (locationManager != null) {
            locationManager.addNmeaListener(bVar, handler);
        }
        SensorManager sensorManager = (SensorManager) getSystemService("sensor");
        this.f3596q = sensorManager;
        if (sensorManager != null) {
            Sensor defaultSensor = sensorManager.getDefaultSensor(1);
            Sensor defaultSensor2 = this.f3596q.getDefaultSensor(2);
            if (defaultSensor != null) {
                this.f3596q.registerListener(this, defaultSensor, 2);
            }
            if (defaultSensor2 != null) {
                this.f3596q.registerListener(this, defaultSensor2, 2);
            }
        }
        return 2;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks2
    public void onTrimMemory(int i7) {
        String str;
        super.onTrimMemory(i7);
        long jMaxMemory = Runtime.getRuntime().maxMemory();
        long j7 = Runtime.getRuntime().totalMemory();
        long jFreeMemory = Runtime.getRuntime().freeMemory();
        long j8 = (jMaxMemory - j7) + jFreeMemory;
        StringBuilder sbA = android.support.v4.media.b.a("onTrimMemory:  ");
        boolean z7 = MainActivity.I0;
        if (i7 == 5) {
            str = "TRIM_MEMORY_RUNNING_MODERATE";
        } else if (i7 == 10) {
            str = "TRIM_MEMORY_RUNNING_LOW";
        } else if (i7 == 15) {
            str = "TRIM_MEMORY_RUNNING_CRITICAL";
        } else if (i7 == 20) {
            str = "TRIM_MEMORY_UI_HIDDEN";
        } else if (i7 == 40) {
            str = "TRIM_MEMORY_BACKGROUND";
        } else if (i7 != 60) {
            str = i7 != 80 ? "?" : "TRIM_MEMORY_COMPLETE";
        } else {
            str = "TRIM_MEMORY_MODERATE";
        }
        sbA.append(str);
        sbA.append(" MaxM=");
        sbA.append(jMaxMemory);
        f0.c(sbA, " TotM=", j7, " FreeM=");
        sbA.append(jFreeMemory);
        sbA.append("TotFreeM=");
        sbA.append(j8);
        v2.e("GPS-S", sbA.toString());
    }
}
