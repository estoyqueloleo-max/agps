package c2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import com.jjoe64.graphview.GraphView;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class y3 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public p5.b[] f3268c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public p5.b[] f3269d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public p5.b[] f3270e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final GraphView f3271f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final o2 f3272g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public x3 f3274i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3275j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Resources f3276k;
    public float m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Handler f3279o;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public p5.d<p5.b> f3267b = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3273h = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f3277l = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3278n = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Runnable f3280p = new d();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f3281q = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3266a = 0;

    public class a implements p5.f.b {
        public a(y3 y3Var) {
        }
    }

    public class b implements p5.e {
        public b() {
        }

        @Override // p5.e
        public void a(p5.g gVar, p5.c cVar) {
            y3.this.c(cVar);
        }
    }

    public class c implements p5.e {
        public c() {
        }

        @Override // p5.e
        public void a(p5.g gVar, p5.c cVar) {
            y3.this.c(cVar);
        }
    }

    public class d implements Runnable {
        public d() {
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0046  */
        /* JADX WARN: Code duplicated, block: B:16:0x0054  */
        /* JADX WARN: Code duplicated, block: B:17:0x005d  */
        @Override // java.lang.Runnable
        public void run() {
            p5.b[] bVarArr;
            int i7;
            y3 y3Var = y3.this;
            y3Var.f3279o.postDelayed(y3Var.f3280p, 100L);
            y3 y3Var2 = y3.this;
            int i8 = y3Var2.f3277l;
            y3Var2.f3281q = i8;
            int i9 = (int) (i8 + y3Var2.m);
            y3Var2.f3277l = i9;
            if (i9 > y3Var2.f3275j - 1) {
                y3Var2.f3279o.removeCallbacks(y3Var2.f3280p);
                y3Var2.f3278n = false;
                return;
            }
            int iC = AgpsApplication.A - 1;
            if (iC == 0) {
                bVarArr = y3Var2.f3269d;
                i7 = y3Var2.f3277l;
                if (bVarArr[i7].f17270i == 0.0d) {
                    y3Var2.f3270e[0] = y3Var2.f3268c[i7];
                } else {
                    y3Var2.f3270e[0] = bVarArr[i7];
                }
            } else if (iC == 1) {
                y3Var2.f3270e[0] = y3Var2.f3268c[y3Var2.f3277l];
            } else if (iC == 2 || iC == 3) {
                bVarArr = y3Var2.f3269d;
                i7 = y3Var2.f3277l;
                if (bVarArr[i7].f17270i == 0.0d) {
                    y3Var2.f3270e[0] = y3Var2.f3268c[i7];
                } else {
                    y3Var2.f3270e[0] = bVarArr[i7];
                }
            }
            y3Var2.f3267b.k(y3Var2.f3270e);
            org.mapsforge.core.model.LatLong cVar = new org.mapsforge.core.model.LatLong(y3Var2.f3274i.f3238c.get(y3Var2.f3277l).getLatitude(), y3Var2.f3274i.f3238c.get(y3Var2.f3277l).getLongitude());
            float fBearingTo = y3Var2.f3277l > 0 ? y3Var2.f3274i.f3238c.get(y3Var2.f3281q).bearingTo(y3Var2.f3274i.f3238c.get(y3Var2.f3277l)) : 0.0f;
            o2 o2Var = y3Var2.f3272g;
            if (o2Var != null) {
                u2 u2Var = MainActivity.f3626n1;
                Objects.requireNonNull(u2Var);
                try {
                    u2Var.f3196b.acquire();
                } catch (Exception unused) {
                }
                try {
                    o2Var.a(cVar, fBearingTo);
                    Object obj = o2Var.f3101b;
                    if (((k3) obj) != null && !u2Var.f3195a.contains((k3) obj)) {
                        u2Var.f3195a.add((k3) o2Var.f3101b, false);
                    }
                } catch (Exception unused2) {
                }
                u2Var.f3196b.release();
            }
        }
    }

    public y3(Context context, View view, x3 x3Var, String str, boolean z) {
        this.f3274i = x3Var;
        Resources resources = context.getResources();
        Resources.Theme theme = context.getTheme();
        this.f3272g = new o2(context, resources.getDrawable(R.drawable.walking_freccia1, theme));
        GraphView graphView = (GraphView) view.findViewById(R.id.graph_elevation);
        this.f3271f = graphView;
        ((TextView) view.findViewById(R.id.graph_txt)).setText(str);
        graphView.setVisibility(0);
        this.f3275j = 1;
        this.f3276k = context.getResources();
    }

    public void a() {
        GraphView graphView = this.f3271f;
        if (graphView != null) {
            graphView.f4474h.clear();
            graphView.c(false, false);
            graphView.setVisibility(View.VISIBLE);
        }

        final x3 currentTrack = this.f3274i;
        if (currentTrack == null) {
            return;
        }

        // Run heavy calculations off the UI thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ElevationDataPoints points = prepareElevationDataPoints(currentTrack);
                    if (points == null) {
                        return;
                    }

                    // Once background processing is finished, update UI on main thread
                    Handler mainHandler = new Handler(android.os.Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            renderElevationGraph(points.gpsDataPoints, points.demDataPoints, points.pointCount);
                        }
                    });
                } catch (Exception exception) {
                    v2.e("GPS-M", "Error preparing elevation profile: " + exception.getMessage());
                }
            }
        }).start();
    }

    public static class ElevationDataPoints {
        public final p5.b[] gpsDataPoints;
        public final p5.b[] demDataPoints;
        public final int pointCount;

        public ElevationDataPoints(p5.b[] gpsDataPoints, p5.b[] demDataPoints, int pointCount) {
            this.gpsDataPoints = gpsDataPoints;
            this.demDataPoints = demDataPoints;
            this.pointCount = pointCount;
        }
    }

    public static ElevationDataPoints prepareElevationDataPoints(x3 track) {
        if (track == null) {
            return null;
        }
        track.c();
        int pointCount = track.f3238c != null ? track.f3238c.size() : 0;
        if (pointCount < 2) {
            return null;
        }

        p5.b[] gpsDataPoints = new p5.b[pointCount];
        p5.b[] demDataPoints = new p5.b[pointCount];
        double[] cachedDemElevations = track.f3241f;
        boolean hasCachedDem = cachedDemElevations != null && cachedDemElevations.length >= pointCount;

        for (int pointIndex = 0; pointIndex < pointCount; pointIndex++) {
            Location location = track.f3238c.get(pointIndex);
            double demElevationValue;

            if (hasCachedDem) {
                demElevationValue = cachedDemElevations[pointIndex];
            } else {
                demElevationValue = location.getAltitude();
            }

            double distanceKm = (track.f3239d != null && track.f3239d.length > pointIndex)
                    ? track.f3239d[pointIndex] / 1000.0d
                    : 0.0d;

            gpsDataPoints[pointIndex] = new p5.b(MainActivity.U(distanceKm), MainActivity.W(location.getAltitude()));

            if (demElevationValue == AgpsApplication.f3579v) {
                demElevationValue = 0.0d;
            }
            demDataPoints[pointIndex] = new p5.b(MainActivity.U(distanceKm), MainActivity.W(demElevationValue));
        }

        return new ElevationDataPoints(gpsDataPoints, demDataPoints, pointCount);
    }

    public void renderElevationGraph(p5.b[] gpsPoints, p5.b[] demPoints, int totalPoints) {
        try {
            if (this.f3271f == null || gpsPoints == null || demPoints == null || totalPoints < 2) {
                return;
            }

            this.f3275j = totalPoints;
            this.f3273h = true;
            this.f3268c = gpsPoints;
            this.f3269d = demPoints;

            MainActivity.f3614b1 = false;
            p5.d<p5.b> gpsSeries = new p5.d<>(this.f3268c);
            p5.f<p5.b> demSeries = new p5.f<>(this.f3269d);
            demSeries.f17278j.f17281a = 3.0f;
            gpsSeries.f17257c = "gps";
            gpsSeries.f17258d = -16776961;
            demSeries.f17258d = Color.rgb(0, 150, 0);
            demSeries.f17257c = "dem";

            this.f3271f.getGridLabelRenderer().f4504q = this.f3276k.getString(R.string.elev_graph_x) + "[" + MainActivity.x() + "]";
            this.f3271f.getGridLabelRenderer().f4505r = this.f3276k.getString(R.string.elev_graph_y) + "[" + MainActivity.y() + "]";

            // Compute min/max viewport safely to prevent 0-range division
            int elevationSourceMode = AgpsApplication.A - 1;
            double minX = gpsSeries.g();
            double maxX = gpsSeries.a();

            double minY;
            double maxY;
            if (elevationSourceMode == 0) { // DEM only
                minY = demSeries.e();
                maxY = demSeries.d();
            } else if (elevationSourceMode == 1) { // GPS only
                minY = gpsSeries.e();
                maxY = gpsSeries.d();
            } else { // Both or Auto
                minY = Math.min(gpsSeries.e(), demSeries.e());
                maxY = Math.max(gpsSeries.d(), demSeries.d());
            }

            if (maxX - minX < 0.1d) {
                maxX = minX + 1.0d;
            } else {
                maxX = maxX + 0.1d;
            }

            if (maxY - minY < 10.0d) {
                maxY = maxY + 20.0d;
                minY = Math.max(0.0d, minY - 10.0d);
            } else {
                maxY = maxY + 50.0d;
                minY = Math.max(0.0d, minY - 50.0d);
            }

            o5.f viewportX = this.f3271f.getViewport();
            viewportX.f17150s = true;
            viewportX.f17148q = 3;
            viewportX.f17137e.f17126b = maxX;
            viewportX.f17137e.f17125a = minX;

            o5.f viewportY = this.f3271f.getViewport();
            viewportY.f17151t = true;
            viewportY.f17149r = 3;
            viewportY.f17137e.f17127c = maxY;
            viewportY.f17137e.f17128d = minY;

            viewportX.f17141i = true;
            viewportX.f17140h = true;

            int activeIndex = Math.min(Math.max(0, this.f3266a), this.f3274i.f3238c.size() - 1);
            double currentDistanceKm = (this.f3274i.f3239d != null && this.f3274i.f3239d.length > activeIndex)
                    ? this.f3274i.f3239d[activeIndex] / 1000.0d
                    : 0.0d;
            double currentAltitude = this.f3274i.f3238c.get(activeIndex).getAltitude();

            p5.b[] cursorPoints = {new p5.b(MainActivity.U(currentDistanceKm), MainActivity.W(currentAltitude))};
            this.f3270e = cursorPoints;
            p5.d<p5.b> cursorSeries = new p5.d<>(cursorPoints);
            this.f3267b = cursorSeries;
            cursorSeries.f17271j.f17276b = 12.0f;
            cursorSeries.f17271j.f17275a = true;
            cursorSeries.f17258d = -256;

            if (elevationSourceMode == 0) {
                this.f3271f.a(demSeries);
            } else if (elevationSourceMode == 1) {
                this.f3271f.a(gpsSeries);
            } else {
                this.f3271f.a(gpsSeries);
                this.f3271f.a(demSeries);
            }
            this.f3271f.a(this.f3267b);

            // Segment boundary markers
            try {
                if (this.f3274i.f3240e != null && this.f3274i.f3240e.size() > 1) {
                    int segmentCount = this.f3274i.f3240e.size() - 1;
                    p5.b[] segmentPoints = new p5.b[segmentCount];
                    for (int segmentIdx = 0; segmentIdx < segmentCount; segmentIdx++) {
                        int trackPointIdx = this.f3274i.f3240e.get(segmentIdx).intValue();
                        if (trackPointIdx < this.f3274i.f3239d.length && trackPointIdx < this.f3274i.f3238c.size()) {
                            double segmentDistance = MainActivity.U(this.f3274i.f3239d[trackPointIdx] / 1000.0d);
                            double segmentAltitude = MainActivity.W(this.f3274i.f3238c.get(trackPointIdx).getAltitude());
                            segmentPoints[segmentIdx] = new p5.b(segmentDistance, segmentAltitude);
                        } else {
                            segmentPoints[segmentIdx] = new p5.b(0.0d, 0.0d);
                        }
                    }
                    p5.f<p5.b> segmentSeries = new p5.f<>(segmentPoints);
                    segmentSeries.f17258d = -65536;
                    segmentSeries.f17280l = new a(this);
                    this.f3271f.a(segmentSeries);
                }
            } catch (Exception segmentException) {
                v2.e("GPS-M", "ElevationGraphView segment markers: " + segmentException.getMessage());
            }

            gpsSeries.f17261g = new b();
            demSeries.f17261g = new c();

            this.f3271f.c(false, false);
        } catch (Exception renderException) {
            v2.e("GPS-M", "ElevationGraphView render: " + renderException.getMessage());
        }
    }

    public boolean b(View view) {
        if (this.f3278n) {
            return false;
        }
        if (view.getVisibility() == 0) {
            view.setVisibility(4);
        }
        this.f3272g.b(false);
        GraphView graphView = this.f3271f;
        if (graphView != null) {
            graphView.f4474h.clear();
            graphView.c(false, false);
        }
        this.f3273h = false;
        return true;
    }

    public void c(p5.c cVar) {
        v2.e("GPS-M", "OnDataPointListener");
        double x = !AgpsApplication.f3567i ? cVar.getX() / 0.621371d : cVar.getX();
        StringBuilder sbA = android.support.v4.media.b.a("OnDataPointListener");
        sbA.append(this.f3275j);
        v2.e("GPS-M", sbA.toString());
        for (int i7 = 0; i7 < this.f3275j; i7++) {
            x3 x3Var = this.f3274i;
            if (x <= x3Var.f3239d[i7] / 1000.0d) {
                this.f3266a = i7;
                double latitude = x3Var.f3238c.get(i7).getLatitude();
                double longitude = this.f3274i.f3238c.get(this.f3266a).getLongitude();
                double altitude = this.f3274i.f3238c.get(this.f3266a).getAltitude();
                x3 x3Var2 = this.f3274i;
                double[] dArr = x3Var2.f3239d;
                int i8 = this.f3266a;
                double d8 = dArr[i8] / 1000.0d;
                this.f3272g.c(true, Double.valueOf(latitude), longitude, altitude, d8, i8 < this.f3275j - 1 ? x3Var2.f3238c.get(i8).bearingTo(this.f3274i.f3238c.get(this.f3266a + 1)) : 0.0f);
                this.f3272g.b(true);
                this.f3270e[0] = new p5.b(MainActivity.U(d8), MainActivity.W(altitude));
                this.f3267b.k(this.f3270e);
                return;
            }
        }
    }
}
