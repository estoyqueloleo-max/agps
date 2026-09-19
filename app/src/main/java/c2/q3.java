package c2;

import android.location.Location;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class q3 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double[] f3124c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3125d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3126e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3127f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public org.mapsforge.map.layer.overlay.Polyline f3129h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CopyOnWriteArrayList<org.mapsforge.core.model.LatLong> f3122a = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CopyOnWriteArrayList<Location> f3123b = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public s f3128g = new s();

    public q3(String str, String str2, int i7) {
        this.f3127f = i7;
        this.f3125d = str;
        this.f3126e = str2;
        new Location("My provider");
        this.f3129h = null;
    }

    public static double b(org.mapsforge.core.model.LatLong latLong) {
        n matchingDemTile = null;
        if (o.f3084p != null) {
            for (n demTile : o.f3084p) {
                if (demTile != null && demTile.f3072b != null && demTile.f3072b.contains(latLong)) {
                    matchingDemTile = demTile;
                    break;
                }
            }
        }
        double demElevation = AgpsApplication.f3579v;
        if (matchingDemTile != null) {
            demElevation = matchingDemTile.b(latLong);
        }
        return demElevation;
    }

    public static String e(long j7) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        long j8 = j7 / 3600;
        numberFormat.setMinimumFractionDigits(0);
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setMinimumIntegerDigits(2);
        return numberFormat.format((int) j8) + ":" + numberFormat.format((int) ((j7 - (3600 * j8)) / 60));
    }

    public static String f(long j7) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        long j8 = j7 / 3600;
        long j9 = j7 - (3600 * j8);
        long j10 = j9 / 60;
        numberFormat.setMinimumFractionDigits(0);
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setMinimumIntegerDigits(2);
        return numberFormat.format((int) j8) + ":" + numberFormat.format((int) j10) + ":" + numberFormat.format((int) (j9 - (60 * j10)));
    }

    public void a() {
        if (this.f3123b == null || this.f3123b.isEmpty()) {
            return;
        }
        if (this.f3123b.size() == 1) {
            Location loc = this.f3123b.get(0);
            this.f3128g.f3153b = loc.getAltitude();
            this.f3128g.f3155d = loc.getAltitude();
            this.f3128g.f3161j = loc.getLatitude();
            this.f3128g.f3163l = loc.getLatitude();
            this.f3128g.f3162k = loc.getLongitude();
            this.f3128g.m = loc.getLongitude();
            this.f3128g.f3166p = 0.0d;
            this.f3128g.f3152a = 0;
            this.f3128g.f3167q = 0;
            return;
        }
        q3 q3Var = this;
        Location location = q3Var.f3123b.get(0);
        long time = location.getTime();
        double altitude = location.getAltitude();
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        double[] dArr = new double[q3Var.f3123b.size()];
        Iterator<Location> it = q3Var.f3123b.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            dArr[i7] = it.next().getAltitude();
            i7++;
        }
        z.b(dArr);
        z.b(dArr);
        z.b(dArr);
        Location location2 = new Location("My provider");
        int size = q3Var.f3123b.size();
        double d8 = altitude;
        double d9 = d8;
        double d10 = longitude;
        double d11 = d10;
        double d12 = 0.0d;
        double d13 = 0.0d;
        double d14 = 0.0d;
        double d15 = 0.0d;
        int i8 = 0;
        double d16 = latitude;
        double d17 = d16;
        double d18 = 0.0d;
        while (true) {
            if (i8 >= size) {
                v2.e("GPS-M", "-------Segment statistics");
                s sVar = this.f3128g;
                sVar.f3153b = d8;
                sVar.f3154c = 0.0d;
                sVar.f3155d = d9;
                sVar.f3156e = 0.0d;
                sVar.f3157f = d14;
                sVar.f3158g = d15;
                sVar.f3161j = d17;
                sVar.f3162k = d11;
                sVar.f3163l = d16;
                sVar.m = d10;
                this.f3128g.f3167q = (this.f3123b.get(this.f3123b.size() - 1).getTime() - this.f3123b.get(0).getTime()) / 1000;
                s sVar2 = this.f3128g;
                sVar2.f3152a = (long) d18;
                sVar2.f3166p = d12;
                sVar2.f3165o = c(time);
                return;
            }
            Location location3 = q3Var.f3123b.get(i8);
            boolean z = i8 == size + (-1);
            double d19 = dArr[i8];
            if (i8 == 0) {
                location2.set(location3);
                d13 = d19;
            }
            double d20 = d10;
            double dDistanceTo = location3.distanceTo(location2);
            double d21 = d17;
            int time2 = ((int) (location3.getTime() / 1000.0d)) - ((int) (location2.getTime() / 1000.0d));
            location2.set(location3);
            double d22 = d19 - d13;
            if (d22 > 2.0d || d22 < -2.0d || z) {
                d13 = d19;
            } else {
                d22 = 0.0d;
            }
            double d23 = time2;
            if (time2 > 30 && dDistanceTo / ((double) time2) < 0.2d) {
                d23 = 0.0d;
            }
            d12 += dDistanceTo;
            d18 += d23;
            double latitude2 = location3.getLatitude();
            double longitude2 = location3.getLongitude();
            double d24 = dArr[i8];
            if (latitude2 < d16) {
                d16 = latitude2;
            }
            if (latitude2 <= d21) {
                latitude2 = d21;
            }
            if (longitude2 < d20) {
                d20 = longitude2;
            }
            if (longitude2 > d11) {
                d11 = longitude2;
            }
            if (d24 < d9) {
                d9 = d24;
            }
            if (d24 > d8) {
                d8 = d24;
            }
            if (d22 > 0.0d) {
                d14 += d22;
            } else {
                d15 += d22;
            }
            i8++;
            q3Var = this;
            d17 = latitude2;
            dArr = dArr;
            location2 = location2;
            size = size;
            d10 = d20;
        }
    }

    public final String c(long j7) {
        String str;
        String str2;
        String str3;
        String str4;
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);
        Locale locale = Locale.US;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", locale);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm", locale);
        DecimalFormat decimalFormat = new DecimalFormat("###0.00");
        DecimalFormat decimalFormat2 = new DecimalFormat("###0.0");
        s sVar = this.f3128g;
        long j8 = sVar.f3152a;
        double d8 = j8 != 0 ? (sVar.f3166p / 1000.0d) / (j8 / 3600.0d) : 0.0d;
        double d9 = sVar.f3154c;
        int i7 = AgpsApplication.f3579v;
        if (d9 == i7 || sVar.f3156e == i7) {
            str = "---";
            str2 = str;
            str3 = str2;
            str4 = str3;
        } else {
            str = numberFormat.format(MainActivity.W(d9));
            str2 = numberFormat.format(MainActivity.W(this.f3128g.f3156e));
            str3 = numberFormat.format(MainActivity.W(this.f3128g.f3159h));
            str4 = numberFormat.format(MainActivity.W(-this.f3128g.f3160i));
        }
        Location location = this.f3123b.get(0);
        double d10 = d8;
        String str5 = str4;
        double dB = b(new org.mapsforge.core.model.LatLong(location.getLatitude(), location.getLongitude()));
        String str6 = dB == ((double) AgpsApplication.f3579v) ? "---" : numberFormat.format(MainActivity.W(dB));
        StringBuilder sbA = android.support.v4.media.b.a("\n");
        p3.a(MainActivity.Z0, R.string.start_date, sbA, " ");
        sbA.append(simpleDateFormat.format(Long.valueOf(j7)));
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.start_time, sbA, " ");
        sbA.append(simpleDateFormat2.format(Long.valueOf(j7)));
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.initial_altitude, sbA, " ");
        sbA.append(numberFormat.format(MainActivity.W(this.f3123b.get(0).getAltitude())));
        sbA.append("(");
        sbA.append(str6);
        sbA.append(")");
        sbA.append(MainActivity.y());
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.distance2, sbA, " ");
        sbA.append(decimalFormat.format(MainActivity.U(this.f3128g.f3166p / 1000.0d)));
        sbA.append(MainActivity.x());
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.total_time, sbA, " ");
        sbA.append(e(this.f3128g.f3167q));
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.walk_time, sbA, " ");
        sbA.append(e(this.f3128g.f3152a));
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.min_elevation, sbA, " ");
        sbA.append(numberFormat.format(MainActivity.W(this.f3128g.f3155d)));
        sbA.append("(");
        sbA.append(str2);
        sbA.append(")");
        sbA.append(MainActivity.y());
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.max_elevation, sbA, " ");
        sbA.append(numberFormat.format(MainActivity.W(this.f3128g.f3153b)));
        sbA.append("(");
        sbA.append(str);
        sbA.append(")");
        sbA.append(MainActivity.y());
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.ascent, sbA, " ");
        sbA.append(numberFormat.format(MainActivity.W(this.f3128g.f3157f)));
        sbA.append("(");
        sbA.append(str3);
        sbA.append(")");
        sbA.append(MainActivity.y());
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.descent, sbA, " ");
        sbA.append(numberFormat.format(-MainActivity.W(this.f3128g.f3158g)));
        sbA.append("(");
        sbA.append(str5);
        sbA.append(")");
        sbA.append(MainActivity.y());
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.ave_speed, sbA, ": ");
        sbA.append(decimalFormat2.format(MainActivity.U(d10)));
        sbA.append(MainActivity.x());
        sbA.append("/h");
        return sbA.toString();
    }

    public int d() {
        return this.f3122a.size();
    }

    public void g() {
        if (this.f3123b == null || this.f3123b.isEmpty()) {
            return;
        }
        Location location = this.f3123b.get(0);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        this.f3123b.size();
        double d8 = longitude;
        double d9 = d8;
        double d10 = latitude;
        for (Location location2 : this.f3123b) {
            double latitude2 = location2.getLatitude();
            double longitude2 = location2.getLongitude();
            if (latitude2 < latitude) {
                latitude = latitude2;
            }
            if (latitude2 > d10) {
                d10 = latitude2;
            }
            if (longitude2 < d8) {
                d8 = longitude2;
            }
            if (longitude2 > d9) {
                d9 = longitude2;
            }
        }
        s sVar = this.f3128g;
        sVar.f3163l = latitude;
        sVar.m = d8;
        sVar.f3161j = d10;
        sVar.f3162k = d9;
    }
}
