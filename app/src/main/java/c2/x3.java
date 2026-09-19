package c2;

import android.location.Location;
import android.net.Uri;
import android.os.Process;
import android.util.Log;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class x3 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static double f3235q;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public double[] f3239d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public double[] f3241f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f3242g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f3243h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f3244i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List<h3> f3247l;
    public Uri m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f3248n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3249o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f3250p;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3246k = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CopyOnWriteArrayList<q3> f3236a = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<a4> f3237b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<Location> f3238c = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Vector<Long> f3240e = new Vector<>();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public s f3245j = new s();

    public class b implements Runnable {
        public b(a aVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            x3 x3Var;
            double d8;
            double d9;
            boolean z;
            String str;
            int i7;
            double d10;
            double d11;
            double d12;
            double d13;
            double d14;
            boolean z7;
            n nVar;
            Process.setThreadPriority(10);
            v2.e("GPS-M", "BatchGpsDem+++++++++++++++++++++++++++");
            x3 x3Var2 = x3.this;
            // NOTE: The synchronized block has been removed from around the DEM I/O loop.
            // The heavy DEM tile reading and statistics accumulation below does NOT need
            // synchronization because it only reads from CopyOnWriteArrayList (f3236a)
            // and writes to local variables. The final write-back to x3 fields is guarded below.
            try {
                    Iterator<q3> it = x3Var2.f3236a.iterator();
                    long j7 = 0;
                    int i8 = 0;
                    double d15 = 0.0d;
                    double d16 = 0.0d;
                    double d17 = 0.0d;
                    double d18 = 0.0d;
                    double d19 = 0.0d;
                    double d20 = 0.0d;
                    double d21 = 0.0d;
                    double d22 = 0.0d;
                    double d23 = 0.0d;
                    double d24 = 0.0d;
                    int i9 = 0;
                    double d25 = 0.0d;
                    long j8 = 0;
                    while (it.hasNext()) {
                        Iterator<q3> it2 = it;
                        q3 next = it.next();
                        int iD = i9 + next.d();
                        if (next.d() > 1) {
                            next.a();
                            s sVar = next.f3128g;
                            x3Var2.f3236a.get(0).f3123b.get(0).getAltitude();
                            Objects.requireNonNull(sVar);
                            if (i8 == 0) {
                                double d26 = sVar.f3161j;
                                double d27 = sVar.f3162k;
                                double d28 = sVar.f3163l;
                                double d29 = sVar.m;
                                double d30 = sVar.f3153b;
                                d17 = sVar.f3155d;
                                d19 = d27;
                                d20 = d28;
                                d21 = d29;
                                d15 = sVar.f3154c;
                                d18 = d26;
                                d22 = d30;
                                d16 = sVar.f3156e;
                            }
                            try {
                                d24 += sVar.f3166p;
                                j8 += sVar.f3167q;
                                j7 += sVar.f3152a;
                                double d31 = sVar.f3161j;
                                if (d18 < d31) {
                                    d18 = d31;
                                }
                                double d32 = sVar.f3162k;
                                if (d19 < d32) {
                                    d19 = d32;
                                }
                                double d33 = sVar.f3163l;
                                if (d20 > d33) {
                                    d20 = d33;
                                }
                                double d34 = sVar.m;
                                if (d21 > d34) {
                                    d21 = d34;
                                }
                                double d35 = sVar.f3153b;
                                if (d22 < d35) {
                                    d22 = d35;
                                }
                                double d36 = sVar.f3155d;
                                if (d17 > d36) {
                                    d17 = d36;
                                }
                                double d37 = sVar.f3154c;
                                if (d15 < d37) {
                                    d15 = d37;
                                }
                                double d38 = sVar.f3156e;
                                if (d16 > d38) {
                                    d16 = d38;
                                }
                                d23 += sVar.f3157f;
                                d25 += sVar.f3158g;
                                i8++;
                            } catch (Throwable th) {
                                th = th;
                                x3Var = x3Var2;
                                throw th;
                            }
                        } else {
                            x3Var2 = x3Var2;
                        }
                        i9 = iD;
                        it = it2;
                        x3Var2 = x3Var2;
                    }
                    x3Var = x3Var2;
                    try {
                        s sVar2 = x3Var.f3245j;
                        sVar2.f3157f = d23;
                        sVar2.f3158g = d25;
                        sVar2.f3153b = d22;
                        sVar2.f3155d = d17;
                        sVar2.f3161j = d18;
                        sVar2.f3162k = d19;
                        sVar2.f3163l = d20;
                        sVar2.m = d21;
                        sVar2.f3166p = d24;
                        sVar2.f3152a = j7;
                        sVar2.f3167q = j8;
                        if (x3Var.f3249o == 2) {
                            v2.e("GPS-M", ">>>LoadedTrk Statistics d=" + d24 + "  t=" + j7 + " nSegm=" + x3Var.f3236a.size() + " nSamples =" + i9);
                        } else {
                            v2.e("GPS-M", ">>>CurrentTrk Statistics d=" + d24 + "  t=" + j7 + " nSegm=" + x3Var.f3236a.size() + " nSamples =" + i9);
                        }
                        x3Var.h();
                        x3 x3Var3 = x3.this;
                        Objects.requireNonNull(x3Var3);
                        MainActivity.f3614b1 = true;
                        x3Var3.f3241f = new double[x3Var3.i()];
                        Iterator<q3> it3 = x3Var3.f3236a.iterator();
                        String strA = XmlPullParser.NO_NAMESPACE;
                        int i10 = 0;
                        double d39 = 0.0d;
                        double d40 = 0.0d;
                        double d41 = 0.0d;
                        double d42 = 0.0d;
                        int i11 = 0;
                        while (it3.hasNext()) {
                            q3 next2 = it3.next();
                            Iterator<q3> it4 = it3;
                            if (next2.d() > 1) {
                                long time = next2.f3123b.get(0).getTime();
                                int size = next2.f3123b.size();
                                double d43 = d39;
                                next2.f3124c = new double[size];
                                Iterator<Location> it5 = next2.f3123b.iterator();
                                n nVar2 = null;
                                int i12 = 0;
                                while (true) {
                                    if (!it5.hasNext()) {
                                        d8 = d40;
                                        d9 = d41;
                                        z = true;
                                        break;
                                    }
                                    Location next3 = it5.next();
                                    Iterator<Location> it6 = it5;
                                    d8 = d40;
                                    n nVar3 = nVar2;
                                    d9 = d41;
                                    org.mapsforge.core.model.LatLong cVar = new org.mapsforge.core.model.LatLong(next3.getLatitude(), next3.getLongitude());
                                    Iterator it7 = ((ArrayList) o.f3084p).iterator();
                                    while (true) {
                                        if (!it7.hasNext()) {
                                            z7 = false;
                                            nVar = nVar3;
                                            break;
                                        } else {
                                            nVar = (n) it7.next();
                                            if (nVar.f3072b.contains(cVar)) {
                                                z7 = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (!z7) {
                                        z = false;
                                        break;
                                    }
                                    next2.f3124c[i12] = nVar.b(cVar);
                                    nVar2 = nVar;
                                    i12++;
                                    it5 = it6;
                                    d40 = d8;
                                    d41 = d9;
                                }
                                if (z) {
                                    double[] dArr = next2.f3124c;
                                    d10 = dArr[0];
                                    MovingAverageFilter.b(dArr);
                                    Location location = new Location("My provider");
                                    double d44 = 0.0d;
                                    double d45 = 0.0d;
                                    double d46 = 0.0d;
                                    int i13 = 0;
                                    d11 = d10;
                                    while (i13 < size) {
                                        int i14 = i11;
                                        Location location2 = next2.f3123b.get(i13);
                                        String str2 = strA;
                                        boolean z8 = i13 == size + (-1);
                                        int i15 = size;
                                        double d47 = next2.f3124c[i13];
                                        if (i13 == 0) {
                                            location.set(location2);
                                            d44 = d47;
                                        }
                                        location2.distanceTo(location);
                                        location2.getTime();
                                        location.getTime();
                                        location.set(location2);
                                        double d48 = d47 - d44;
                                        if (d48 > 2.0d || d48 < -2.0d || z8) {
                                            d44 = d47;
                                        } else {
                                            d48 = 0.0d;
                                        }
                                        double d49 = next2.f3124c[i13];
                                        if (d49 < d11) {
                                            d11 = d49;
                                        }
                                        if (d49 > d10) {
                                            d10 = d49;
                                        }
                                        if (d48 > 0.0d) {
                                            d45 += d48;
                                        } else {
                                            d46 += d48;
                                        }
                                        i13++;
                                        i11 = i14;
                                        strA = str2;
                                        size = i15;
                                    }
                                    str = strA;
                                    i7 = i11;
                                    d14 = d45;
                                    d13 = d46;
                                    d12 = d42;
                                } else {
                                    str = strA;
                                    i7 = i11;
                                    d10 = AgpsApplication.f3579v;
                                    d11 = d10;
                                    d12 = d42;
                                    d13 = d11;
                                    d14 = d13;
                                }
                                s sVar3 = next2.f3128g;
                                sVar3.f3154c = d10;
                                sVar3.f3156e = d11;
                                sVar3.f3159h = d14;
                                sVar3.f3160i = d13;
                                sVar3.f3165o = next2.c(time);
                                int i16 = 0;
                                i11 = i7;
                                while (true) {
                                    double[] dArr2 = next2.f3124c;
                                    if (i16 >= dArr2.length) {
                                        break;
                                    }
                                    x3Var3.f3241f[i11] = dArr2[i16];
                                    i16++;
                                    i11++;
                                }
                                s sVar4 = next2.f3128g;
                                x3Var3.f3236a.get(0).f3123b.get(0).getAltitude();
                                Objects.requireNonNull(sVar4);
                                int i17 = i10 + 1;
                                if (i10 == 0) {
                                    d39 = sVar4.f3154c;
                                    d40 = sVar4.f3156e;
                                } else {
                                    d39 = d43;
                                    d40 = d8;
                                }
                                double d50 = sVar4.f3154c;
                                d41 = AgpsApplication.f3579v;
                                if (d50 != d41) {
                                    double d51 = sVar4.f3156e;
                                    if (d51 != d41) {
                                        if (d39 < d50) {
                                            d39 = d50;
                                        }
                                        if (d40 > d51) {
                                            d40 = d51;
                                        }
                                        d42 = d12 != d41 ? d12 + sVar4.f3159h : d41;
                                        if (d9 != d41) {
                                            d41 = d9 + sVar4.f3160i;
                                        }
                                        strA = x3Var3.f3236a.size() != 1 ? str + sVar4.f3165o + "\n" : str;
                                        i10 = i17;
                                    }
                                }
                                d39 = d41;
                                d40 = d39;
                                d42 = d40;
                                break;
                            }
                            it3 = it4;
                        }
                        s sVar5 = x3Var3.f3245j;
                        sVar5.f3159h = d42;
                        sVar5.f3160i = d41;
                        sVar5.f3154c = d39;
                        sVar5.f3156e = d40;
                        x3Var3.h();
                        MainActivity.f3614b1 = false;
                    } catch (Throwable th2) {
                        throw new RuntimeException(th2);
                    }
            } catch (Throwable th3) {
                x3Var = x3Var2;
            }
        }
    }

    public x3() {
        this(0);
    }

    public x3(int i7) {
        this.f3242g = XmlPullParser.NO_NAMESPACE;
        this.f3243h = XmlPullParser.NO_NAMESPACE;
        this.f3244i = XmlPullParser.NO_NAMESPACE;
        this.f3242g = "?";
        this.f3243h = XmlPullParser.NO_NAMESPACE;
        this.f3244i = XmlPullParser.NO_NAMESPACE;
        j(XmlPullParser.NO_NAMESPACE, XmlPullParser.NO_NAMESPACE, XmlPullParser.NO_NAMESPACE);
        this.f3247l = new ArrayList();
        this.f3249o = i7;
        if (i7 == 2) {
            int i8 = MainActivity.f3633u1;
        } else {
            int i9 = MainActivity.f3632t1;
        }
    }

    public static String g(String str) {
        String[] strArrSplit = str.split("/");
        return strArrSplit[strArrSplit.length - 1];
    }

    public boolean a(String str, r rVar) {
        Log.i("GPS-M", "%%%%%%%%%  Load Track Start %%%%%%%%%%%");
        this.f3236a.clear();
        this.m = rVar.f3138i;
        this.f3242g = str;
        this.f3244i = rVar.f3131b;
        int size = rVar.f3132c.size();
        if (rVar.f3135f.size() == 0 || rVar.f3136g.size() == 0) {
            return false;
        }
        j(this.f3242g, rVar.f3135f.get(0), rVar.f3136g.get(0));
        if (size != 0) {
            for (int i7 = 0; i7 < size; i7++) {
                try {
                    Location location = rVar.f3132c.get(i7);
                    boolean zBooleanValue = rVar.f3134e.get(i7).booleanValue();
                    b(location);
                    int iE = e();
                    if (zBooleanValue && i7 != size - 1) {
                        if (iE < rVar.f3135f.size() && iE < rVar.f3136g.size()) {
                            v2.e("GPS-M", "============> Loading new Track. New Segment n=" + iE);
                            j(this.f3242g, rVar.f3135f.get(iE), rVar.f3136g.get(iE));
                        }
                        v2.e("GPS-M", "Error size of segment vector strings-it.xml-it.xml");
                        break;
                    }
                } catch (Exception unused) {
                    return false;
                }
            }
            this.f3237b.addAll(rVar.f3133d);
            for (q3 q3Var : this.f3236a) {
                int size2 = q3Var.f3123b.size();
                if (size2 > 0) {
                    n(q3Var.f3123b.get(size2 - 1), q3Var.f3127f);
                }
            }
        }
        l();
        k();
        c();
        return true;
    }

    // Not synchronized: all collections here (f3236a, f3123b, f3122a) are CopyOnWriteArrayList,
    // so individual add() calls are already thread-safe. Removing 'synchronized' prevents the
    // LocationService thread from blocking behind the long-running BatchGpsDem lock.
    public void b(Location location) {
        Location location2 = new Location("dummy");
        location2.set(location);
        if (d().f3123b.size() == 0) {
            o(location2, this.f3236a.size());
        }
        q3 q3VarD = d();
        q3VarD.f3123b.add(location2);
        q3VarD.f3122a.add(new org.mapsforge.core.model.LatLong(location2.getLatitude(), location2.getLongitude()));
    }

    public void c() {
        this.f3240e.clear();
        this.f3238c.clear();
        this.f3239d = new double[i()];
        double d8 = 0.0d;
        int i7 = 0;
        for (q3 q3Var : this.f3236a) {
            Location location = new Location("My provider");
            if (q3Var.f3123b.size() > 0) {
                this.f3238c.addAll(q3Var.f3123b);
                int i8 = 0;
                double d9 = 0.0d;
                while (true) {
                    if (i8 >= q3Var.f3123b.size()) {
                        break;
                    }
                    boolean z = i8 == q3Var.f3123b.size() - 1;
                    Location location2 = q3Var.f3123b.get(i8);
                    double altitude = q3Var.f3123b.get(i8).getAltitude();
                    if (i8 == 0) {
                        location.set(location2);
                        d9 = altitude;
                    }
                    double dDistanceTo = location2.distanceTo(location);
                    double d10 = d8;
                    int time = ((int) (location2.getTime() / 1000.0d)) - ((int) (location.getTime() / 1000.0d));
                    location.set(location2);
                    double d11 = altitude - d9;
                    if (d11 > 2.0d || d11 < -2.0d || z) {
                        d9 = altitude;
                    }
                    d8 = d10 + dDistanceTo;
                    if (i7 < i()) {
                        this.f3239d[i7] = d8;
                        i7++;
                    }
                    i8++;
                }
                this.f3240e.add(Long.valueOf(this.f3238c.size() - 1));
            }
        }
    }

    public q3 d() {
        if (this.f3236a.size() <= 0) {
            return null;
        }
        CopyOnWriteArrayList<q3> copyOnWriteArrayList = this.f3236a;
        return copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1);
    }

    public int e() {
        return this.f3236a.size();
    }

    public boolean f(long j7) {
        if (this.f3236a.size() == 0) {
            return false;
        }
        boolean z = false;
        for (q3 q3Var : this.f3236a) {
            if (q3Var.f3123b.size() > 1) {
                long time = q3Var.f3123b.get(0).getTime();
                CopyOnWriteArrayList<Location> copyOnWriteArrayList = q3Var.f3123b;
                long time2 = copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1).getTime();
                if (j7 >= time && j7 <= time2) {
                    z = true;
                }
            }
        }
        return z;
    }

    public void h() {
        String strSubstring;
        String string;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        q3 q3Var = this.f3236a.get(0);
        this.f3245j.f3165o = XmlPullParser.NO_NAMESPACE;
        String str6 = q3Var.f3125d;
        if (str6 == null || str6.equals(XmlPullParser.NO_NAMESPACE) || q3Var.f3125d.equals("tmp") || q3Var.f3125d.indexOf(".gpx") <= 0) {
            strSubstring = XmlPullParser.NO_NAMESPACE;
        } else {
            String str7 = q3Var.f3125d;
            strSubstring = str7.substring(0, str7.indexOf(".gpx"));
        }
        if (!this.f3244i.equals(XmlPullParser.NO_NAMESPACE) && !this.f3244i.equals("---")) {
            String strA = this.f3244i + "\n";
            s sVar = this.f3245j;
            sVar.f3165o = (sVar.f3165o != null ? sVar.f3165o : "") + strA;
        }
        String str8 = q3Var.f3126e;
        if (str8 != null && str8.length() != 0 && !q3Var.f3126e.equals(strSubstring) && !q3Var.f3126e.equals(this.f3243h) && !q3Var.f3126e.equals("...") && !q3Var.f3126e.equals("tmp")) {
            s sVar2 = this.f3245j;
            sVar2.f3165o = (sVar2.f3165o != null ? sVar2.f3165o : "") + q3Var.f3126e + "\n";
        }
        if (this.f3236a.size() > 1) {
            int i7 = 0;
            string = XmlPullParser.NO_NAMESPACE;
            for (q3 q3Var2 : this.f3236a) {
                i7++;
                StringBuilder sbD = c7.f.d(string, "\n---------> ");
                sbD.append(MainActivity.Z0.getResources().getString(R.string.segment));
                sbD.append(" #");
                sbD.append(i7);
                sbD.append(": <---------");
                String string2 = sbD.toString();
                if (this.f3236a.size() > 1 && (str5 = q3Var2.f3126e) != null && str5.length() > 0 && !q3Var2.f3126e.equals(q3Var2.f3125d) && !q3Var2.f3126e.equals("tmp")) {
                    StringBuilder sbD2 = c7.f.d(string2, "\n");
                    sbD2.append(q3Var2.f3126e);
                    string2 = sbD2.toString();
                }
                StringBuilder sbD3 = c7.f.d(string2, "\n");
                sbD3.append(q3Var2.f3128g.f3165o);
                string = sbD3.toString();
            }
        } else {
            string = XmlPullParser.NO_NAMESPACE;
        }
        long time = this.f3236a.get(0).f3123b.get(0).getTime();
        StringBuilder sb3 = new StringBuilder();
        s sVar3 = this.f3245j;
        sb3.append(sVar3.f3165o);
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);
        Locale locale = Locale.US;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", locale);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm", locale);
        DecimalFormat decimalFormat = new DecimalFormat("###0.00");
        DecimalFormat decimalFormat2 = new DecimalFormat("###0.0");
        s sVar4 = this.f3245j;
        long j7 = sVar4.f3152a;
        double d8 = j7 != 0 ? (sVar4.f3166p / 1000.0d) / (j7 / 3600.0d) : 0.0d;
        Location location = this.f3236a.get(0).f3123b.get(0);
        double d9 = d8;
        double dB = q3.b(new org.mapsforge.core.model.LatLong(location.getLatitude(), location.getLongitude()));
        String str9 = dB == ((double) AgpsApplication.f3579v) ? "---" : numberFormat.format(MainActivity.W(dB));
        StringBuilder sb4 = new StringBuilder();
        p3.a(MainActivity.Z0, R.string.start_date, sb4, " ");
        sb4.append(simpleDateFormat.format(Long.valueOf(time)));
        sb4.append("\n");
        p3.a(MainActivity.Z0, R.string.start_time, sb4, " ");
        sb4.append(simpleDateFormat2.format(Long.valueOf(time)));
        sb4.append("\n");
        sb4.append(MainActivity.Z0.getString(R.string.initial_altitude));
        sb4.append(numberFormat.format(MainActivity.W(location.getAltitude())));
        sb4.append("(dem=");
        sb4.append(str9);
        sb4.append(")");
        sb4.append(MainActivity.y());
        sb4.append("\n");
        String string3 = e() == 1 ? sb4.toString() : XmlPullParser.NO_NAMESPACE;
        s sVar5 = this.f3245j;
        double d10 = sVar5.f3154c;
        double d11 = AgpsApplication.f3579v;
        if (d10 == d11 || sVar5.f3156e == d11) {
            str = "---";
            str2 = str;
            str3 = str2;
            str4 = str3;
        } else {
            String str10 = numberFormat.format(MainActivity.W(d10));
            String str11 = numberFormat.format(MainActivity.W(this.f3245j.f3156e));
            str2 = numberFormat.format(MainActivity.W(this.f3245j.f3159h));
            str4 = numberFormat.format(MainActivity.W(-this.f3245j.f3160i));
            str3 = str11;
            str = str10;
        }
        StringBuilder sbA = android.support.v4.media.b.a(string3);
        p3.a(MainActivity.Z0, R.string.distance2, sbA, " ");
        sbA.append(decimalFormat.format(MainActivity.U(this.f3245j.f3166p / 1000.0d)));
        sbA.append(MainActivity.x());
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.total_time, sbA, " ");
        sbA.append(q3.e(this.f3245j.f3167q));
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.walk_time, sbA, " ");
        sbA.append(q3.e(this.f3245j.f3152a));
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.min_elevation, sbA, " ");
        sbA.append(numberFormat.format(MainActivity.W(this.f3245j.f3155d)));
        sbA.append("(");
        sbA.append(str3);
        sbA.append(")");
        sbA.append(MainActivity.y());
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.max_elevation, sbA, " ");
        sbA.append(numberFormat.format(MainActivity.W(this.f3245j.f3153b)));
        sbA.append("(");
        sbA.append(str);
        sbA.append(")");
        sbA.append(MainActivity.y());
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.ascent, sbA, " ");
        sbA.append(numberFormat.format(MainActivity.W(this.f3245j.f3157f)));
        sbA.append("(");
        sbA.append(str2);
        sbA.append(")");
        sbA.append(MainActivity.y());
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.descent, sbA, " ");
        sbA.append(numberFormat.format(-MainActivity.W(this.f3245j.f3158g)));
        sbA.append("(");
        sbA.append(str4);
        sbA.append(")");
        sbA.append(MainActivity.y());
        sbA.append("\n");
        p3.a(MainActivity.Z0, R.string.ave_speed, sbA, ": ");
        sbA.append(decimalFormat2.format(MainActivity.U(d9)));
        sbA.append(MainActivity.x());
        sbA.append("/h \n");
        sb3.append(sbA.toString());
        sb3.append(string);
        sVar3.f3165o = sb3.toString();
        s sVar6 = this.f3245j;
        NumberFormat numberFormat2 = NumberFormat.getInstance();
        numberFormat2.setMaximumFractionDigits(0);
        new SimpleDateFormat("dd-MMM-yyyy", locale);
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("HH:mm", locale);
        new DecimalFormat("###0.00");
        DecimalFormat decimalFormat3 = new DecimalFormat("###0.0");
        s sVar7 = this.f3245j;
        long j8 = sVar7.f3152a;
        double d12 = j8 != 0 ? (sVar7.f3166p / 1000.0d) / (j8 / 3600.0d) : 0.0d;
        Location location2 = MainActivity.f3613a1;
        if (location2 != null && location2.getAltitude() != AgpsApplication.f3579v) {
            f3235q = MainActivity.W(MainActivity.f3613a1.getAltitude() - this.f3236a.get(0).f3123b.get(0).getAltitude());
        }
        sVar6.f3164n = MainActivity.Z0.getResources().getString(R.string.start_time) + " " + simpleDateFormat3.format(Long.valueOf(time)) + "\n" + MainActivity.Z0.getResources().getString(R.string.initial_altitude) + " " + numberFormat2.format(MainActivity.W(this.f3236a.get(0).f3123b.get(0).getAltitude())) + MainActivity.y() + "\n" + MainActivity.Z0.getResources().getString(R.string.difference_in_height) + " " + numberFormat2.format(MainActivity.W(f3235q)) + MainActivity.y() + "\n" + MainActivity.Z0.getResources().getString(R.string.ave_speed) + ": " + decimalFormat3.format(MainActivity.U(d12)) + MainActivity.x() + "/h \n";
    }

    public int i() {
        int size = 0;
        if (this.f3236a.size() == 0) {
            return 0;
        }
        Iterator<q3> it = this.f3236a.iterator();
        while (it.hasNext()) {
            size += it.next().f3123b.size();
        }
        return size;
    }

    public void j(String str, String str2, String str3) {
        try {
            if (MainActivity.W0 != null) {
                MainActivity.W0.acquire();
            }
        } catch (Exception unused) {
        }
        this.f3236a.add(new q3(str, str2, e() + 1));
        if (MainActivity.W0 != null) {
            MainActivity.W0.release();
        }
    }

    public void k() {
        new Thread(new b(null)).start();
    }

    public void l() {
        q3 q3Var = this.f3236a.get(0);
        q3Var.g();
        s sVar = q3Var.f3128g;
        double d8 = sVar.f3163l;
        double d9 = sVar.f3161j;
        double d10 = sVar.m;
        double d11 = sVar.f3162k;
        for (q3 q3Var2 : this.f3236a) {
            q3Var2.g();
            s sVar2 = q3Var2.f3128g;
            double d12 = sVar2.f3163l;
            if (d12 < d8) {
                d8 = d12;
            }
            double d13 = sVar2.m;
            if (d13 < d10) {
                d10 = d13;
            }
            double d14 = sVar2.f3161j;
            if (d14 > d9) {
                d9 = d14;
            }
            double d15 = sVar2.f3162k;
            if (d15 > d11) {
                d11 = d15;
            }
        }
        s sVar3 = this.f3245j;
        sVar3.f3163l = d8;
        sVar3.f3161j = d9;
        sVar3.m = d10;
        sVar3.f3162k = d11;
    }

    public void m() {
        int iE = e();
        v2.e("GPS-M", "% Waypoint: End Of Current Segment #" + iE);
        new DecimalFormat("###0");
        new DecimalFormat("###0.0000");
        if (d().f3123b.size() == 0) {
            return;
        }
        String endStr = (MainActivity.Z0 != null) ? MainActivity.Z0.getResources().getString(R.string.end) : "End";
        this.f3237b.add(new a4(endStr + " #" + iE, XmlPullParser.NO_NAMESPACE, d().f3123b.get(d().f3123b.size() - 1), 2));
        MainActivity.U0 = true;
    }

    public void n(Location location, int i7) {
        v2.e("GPS-M", "%  Waypoint: End Of Segment #" + i7 + "ns= " + this.f3236a.get(i7 - 1).d());
        StringBuilder sb = new StringBuilder();
        String endStr = (MainActivity.Z0 != null) ? MainActivity.Z0.getResources().getString(R.string.end) : "End";
        sb.append(endStr);
        sb.append(" #");
        sb.append(i7);
        this.f3237b.add(new a4(sb.toString(), XmlPullParser.NO_NAMESPACE, location, 2));
        MainActivity.U0 = true;
    }

    public void o(Location location, int i7) {
        v2.e("GPS-M", "%  Waypoint: Start Of Segment #" + i7);
        new DecimalFormat("###0");
        new DecimalFormat("###0.0000");
        String startStr = (MainActivity.Z0 != null) ? MainActivity.Z0.getResources().getString(R.string.start) : "Start";
        this.f3237b.add(new a4(startStr + " #" + i7, XmlPullParser.NO_NAMESPACE, location, 1));
        MainActivity.U0 = true;
    }

    /**
     * Data class holding the calculated remaining distance, distance to track,
     * matched waypoint index, and destination status.
     */
    public static class RemainingDistanceResult {
        public final double remainingDistanceMeters;
        public final double distanceToTrackMeters;
        public final int matchedIndex;
        public final int closestWaypointIndex;
        public final boolean isDestinationReached;

        public RemainingDistanceResult(double remainingDistanceMeters, double distanceToTrackMeters, int matchedIndex, boolean isDestinationReached) {
            this.remainingDistanceMeters = remainingDistanceMeters;
            this.distanceToTrackMeters = distanceToTrackMeters;
            this.matchedIndex = matchedIndex;
            this.closestWaypointIndex = matchedIndex;
            this.isDestinationReached = isDestinationReached;
        }
    }

    /**
     * Calculates the remaining distance along this track from the user's current GPS position.
     * Takes direction into account (normal forward vs reverse Track Back mode) and uses windowed progress
     * to prevent distance jumping when tracks cross themselves, loop, or overlap.
     */
    public RemainingDistanceResult calculateRemainingDistance(Location userLocation, boolean isReverseMode, int lastMatchedIndex) {
        if (this.f3238c == null || this.f3238c.isEmpty() || this.f3239d == null || this.f3239d.length == 0) {
            c();
        }
        if (userLocation == null || this.f3238c == null || this.f3238c.isEmpty() || this.f3239d == null || this.f3239d.length == 0) {
            return new RemainingDistanceResult(0.0d, 0.0d, 0, false);
        }

        int totalPoints = this.f3238c.size();
        double totalTrackDistance = this.f3239d[Math.min(this.f3239d.length - 1, totalPoints - 1)];

        int startIndex = 0;
        int endIndex = totalPoints - 1;

        // Windowed search around current progress
        if (lastMatchedIndex >= 0 && lastMatchedIndex < totalPoints) {
            if (!isReverseMode) {
                startIndex = Math.max(0, lastMatchedIndex - 10);
                endIndex = Math.min(totalPoints - 1, lastMatchedIndex + 60);
            } else {
                startIndex = Math.max(0, lastMatchedIndex - 60);
                endIndex = Math.min(totalPoints - 1, lastMatchedIndex + 10);
            }
        } else {
            // Initial match (lastMatchedIndex < 0): prioritize start of route (especially on circular routes)
            if (!isReverseMode) {
                int initialWindow = Math.min(totalPoints - 1, Math.max(50, totalPoints / 10));
                double startMinDist = Double.MAX_VALUE;
                for (int i = 0; i <= initialWindow; i++) {
                    double dist = userLocation.distanceTo(this.f3238c.get(i));
                    if (dist < startMinDist) {
                        startMinDist = dist;
                    }
                }
                if (startMinDist < 150.0d) {
                    startIndex = 0;
                    endIndex = initialWindow;
                }
            } else {
                int initialWindow = Math.max(0, totalPoints - Math.max(50, totalPoints / 10));
                double endMinDist = Double.MAX_VALUE;
                for (int i = totalPoints - 1; i >= initialWindow; i--) {
                    double dist = userLocation.distanceTo(this.f3238c.get(i));
                    if (dist < endMinDist) {
                        endMinDist = dist;
                    }
                }
                if (endMinDist < 150.0d) {
                    startIndex = initialWindow;
                    endIndex = totalPoints - 1;
                }
            }
        }

        double minDistanceToTrack = Double.MAX_VALUE;
        int closestIndex = lastMatchedIndex >= 0 ? lastMatchedIndex : 0;

        for (int i = startIndex; i <= endIndex; i++) {
            Location pt = this.f3238c.get(i);
            double dist = userLocation.distanceTo(pt);
            if (dist < minDistanceToTrack) {
                minDistanceToTrack = dist;
                closestIndex = i;
            }
        }

        // Global fallback if user moved far away from window
        if (minDistanceToTrack > 100.0d && (startIndex > 0 || endIndex < totalPoints - 1)) {
            for (int i = 0; i < totalPoints; i++) {
                Location pt = this.f3238c.get(i);
                double dist = userLocation.distanceTo(pt);
                if (dist < minDistanceToTrack) {
                    minDistanceToTrack = dist;
                    closestIndex = i;
                }
            }
        }

        double coveredDistance = this.f3239d[Math.min(closestIndex, this.f3239d.length - 1)];
        double remainingDistance;
        boolean reachedDestination;

        if (!isReverseMode) {
            remainingDistance = Math.max(0.0d, totalTrackDistance - coveredDistance);
            reachedDestination = (closestIndex >= totalPoints - 5 && remainingDistance < 30.0d)
                    || (coveredDistance > totalTrackDistance * 0.75d && userLocation.distanceTo(this.f3238c.get(totalPoints - 1)) < 30.0d);
        } else {
            remainingDistance = coveredDistance;
            reachedDestination = (closestIndex <= 4 && remainingDistance < 30.0d)
                    || (coveredDistance < totalTrackDistance * 0.25d && userLocation.distanceTo(this.f3238c.get(0)) < 30.0d);
        }

        return new RemainingDistanceResult(remainingDistance, minDistanceToTrack, closestIndex, reachedDestination);
    }
}

