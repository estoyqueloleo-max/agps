package c2;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.graphics.GraphicFactory;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.layer.overlay.Marker;
import org.mapsforge.map.layer.overlay.Polygon;
import org.mapsforge.map.layer.overlay.Polyline;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static List<n> f3084p = new ArrayList();
    /** Active DEM overlay layers (polygons, polylines, contours) to preserve during map refresh */
    public static final List<org.mapsforge.map.layer.Layer> demOverlayLayers = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3085a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f3086b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3087c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3088d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f3089e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public double f3090f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public double f3091g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public double f3092h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public double f3093i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public double f3094j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3095k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public BoundingBox f3096l;
    public short m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Context f3097n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f3098o;

    public o(Context context, String str) {
        String str2;
        this.f3097n = context;
        this.f3098o = str;
        if (str.equals(XmlPullParser.NO_NAMESPACE) || (str2 = this.f3098o) == null || !str2.substring(str2.length() - 4, str2.length()).equalsIgnoreCase(".hgt")) {
            return;
        }
        String upperCase = str2.toUpperCase();
        this.f3086b = upperCase;
        long length = MainActivity.X(this.f3097n, "Dems/" + upperCase, 1).length();
        BoundingBox aVarA = a(upperCase);
        this.f3085a = "M";
        boolean z = false;
        if (length != 25934402) {
            if (length == 2884802) {
                this.f3087c = 1201;
                this.f3088d = 1201;
            }
            if (z) {
                int i7 = (int) aVarA.minLongitude;
                int i8 = (int) aVarA.maxLatitude;
                int i9 = this.f3087c;
                this.f3089e = 1.0d / ((double) i9);
                this.f3090f = 1.0d / ((double) this.f3088d);
                this.f3095k = i9 * 2;
                this.m = Short.MIN_VALUE;
                this.f3091g = i8;
                this.f3092h = i8 - 1;
                this.f3093i = i7 + 1;
                this.f3094j = i7;
                LatLong cVar = new LatLong(this.f3092h, this.f3094j);
                LatLong cVar2 = new LatLong(this.f3091g, this.f3093i);
                this.f3096l = new BoundingBox(cVar.latitude, cVar.longitude, cVar2.latitude, cVar2.longitude);
            }
        }
        this.f3087c = 3601;
        this.f3088d = 3601;
        z = true;
        if (z) {
            int i10 = (int) aVarA.minLongitude;
            int i11 = (int) aVarA.maxLatitude;
            int i12 = this.f3087c;
            this.f3089e = 1.0d / ((double) i12);
            this.f3090f = 1.0d / ((double) this.f3088d);
            this.f3095k = i12 * 2;
            this.m = Short.MIN_VALUE;
            this.f3091g = i11;
            this.f3092h = i11 - 1;
            this.f3093i = i10 + 1;
            this.f3094j = i10;
            LatLong cVar3 = new LatLong(this.f3092h, this.f3094j);
            LatLong cVar4 = new LatLong(this.f3091g, this.f3093i);
            this.f3096l = new BoundingBox(cVar3.latitude, cVar3.longitude, cVar4.latitude, cVar4.longitude);
        }
    }

    public static BoundingBox a(String str) {
        String strSubstring = str.substring(0, 3);
        String strSubstring2 = str.substring(3, 7);
        int i7 = Integer.parseInt(strSubstring.substring(1, 3));
        if (strSubstring.substring(0, 1).equalsIgnoreCase("S")) {
            i7 = -i7;
        }
        int i8 = Integer.parseInt(strSubstring2.substring(1, 4));
        if (strSubstring2.substring(0, 1).equalsIgnoreCase("W")) {
            i8 = -i8;
        }
        LatLong cVar = new LatLong(i7, i8);
        LatLong cVar2 = new LatLong(i7 + 1, i8 + 1);
        return new BoundingBox(cVar.latitude, cVar.longitude, cVar2.latitude, cVar2.longitude);
    }

    public static BoundingBox b(LatLong cVar) {
        double dCos = ((double) ((int) ((1.0d / Math.cos((cVar.latitude * 6.28d) / 360.0d)) * 0.04d * 1200.0d))) * 8.333333333333334E-4d;
        double d8 = cVar.latitude;
        double d9 = (((double) ((int) ((d8 % 1.0d) * 1200.0d))) * 8.333333333333334E-4d) + ((double) ((int) d8));
        double d10 = cVar.longitude;
        double d11 = (((double) ((int) ((d10 % 1.0d) * 1200.0d))) * 8.333333333333334E-4d) + ((double) ((int) d10));
        LatLong cVar2 = new LatLong(d9 - 0.04d, d11 - dCos);
        LatLong cVar3 = new LatLong(d9 + 0.04d, d11 + dCos);
        return new BoundingBox(cVar2.latitude, cVar2.longitude, cVar3.latitude, cVar3.longitude);
    }

    public static void c(int minZoom) {
        if (MainActivity.M0 == null || MainActivity.M0.getModel() == null
                || MainActivity.M0.getModel().mapViewPosition == null) {
            return;
        }
        boolean isVisible = MainActivity.M0.getModel().mapViewPosition.getZoomLevel() >= minZoom;
        CopyOnWriteArrayList<Marker> markers = AgpsApplication.f3580w;
        if (markers != null) {
            for (Marker marker : markers) {
                marker.setVisible(isVisible);
                marker.requestRedraw();
            }
        }
    }

    public static void d(BoundingBox box, int level) {
        ArrayList<LatLong> borderPoints = new ArrayList<>();
        borderPoints.add(new LatLong(box.minLatitude, box.minLongitude));
        borderPoints.add(new LatLong(box.maxLatitude, box.minLongitude));
        borderPoints.add(new LatLong(box.maxLatitude, box.maxLongitude));
        borderPoints.add(new LatLong(box.minLatitude, box.maxLongitude));
        borderPoints.add(new LatLong(box.minLatitude, box.minLongitude));
        f(borderPoints, level);
    }

    public static void e(Context context) {
        AgpsApplication.f3580w.clear();
        demOverlayLayers.clear();
        for (n demFile : f3084p) {
            BoundingBox box = demFile.f3072b;
            LatLong center = new LatLong(box.minLatitude + 0.5d, box.minLongitude + 0.5d);
            String label = n.a(new LatLong(center.latitude, center.longitude));
            TextView textView = new TextView(context);
            textView.setText(label);
            textView.setTextSize(12.0f);
            textView.setGravity(17);
            textView.setBackgroundColor(Color.argb(160, 200, 200, 200));
            Bitmap bitmap = n2.b(context, textView);
            Marker marker = new Marker(center, bitmap, 0, (-bitmap.getHeight()) / 2);
            MainActivity.f3626n1.a(marker);
            marker.setVisible(true);
            marker.requestRedraw();
            AgpsApplication.f3580w.add(marker);
            d(demFile.f3072b, 1);
        }
        c(9);
        if (MainActivity.M0 != null && MainActivity.M0.getLayerManager() != null) {
            try {
                MainActivity.M0.getLayerManager().redrawLayers();
            } catch (Exception ignored) {
            }
        }
    }

    public static void f(List<LatLong> points, int level) {
        Paint strokePaint;
        Paint fillPaint;
        if (level == 1) {
            strokePaint = n2.a(Color.argb(250, 88, 146, 117), 3, 2);
            fillPaint = n2.a(Color.argb(30, 88, 146, 117), 1, 1);
        } else if (level == 2) {
            strokePaint = n2.a(Color.argb(250, 0, 0, 0), 4, 2);
            fillPaint = n2.a(Color.argb(120, 76, 150, 0), 3, 1);
        } else {
            strokePaint = n2.a(Color.argb(255, 100, 100, 100), 2, 2);
            fillPaint = n2.a(Color.argb(30, 255, 178, 102), 1, 1);
        }
        GraphicFactory graphicFactory = AndroidGraphicFactory.INSTANCE;
        if (level != 3) {
            Polygon polygon = new Polygon(fillPaint, strokePaint, graphicFactory);
            polygon.setPoints(points);
            demOverlayLayers.add(polygon);
            MainActivity.f3626n1.a(polygon);
        }
        Polyline polyline = new Polyline(strokePaint, graphicFactory);
        polyline.setPoints(points);
        demOverlayLayers.add(polyline);
        MainActivity.f3626n1.a(polyline);
    }

    public static void g(Context context) {
        for (n demItem : (List<n>) (List<?>) f3084p) {
            RandomAccessFile randomAccessFile = demItem.f3075e;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                    demItem.f3075e = null;
                } catch (Exception unused) {
                }
            }
        }
        ((ArrayList) f3084p).clear();
        AgpsApplication.f3580w.clear();
        demOverlayLayers.clear();
        File fileX = MainActivity.X(context, "Dems", 2);
        File[] fileArrListFiles = (fileX == null || !fileX.isDirectory()) ? null : fileX.listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (file.getName().endsWith(".hgt")) {
                    ((ArrayList) f3084p).add(new n(file.getName(), a(file.getName().substring(0, file.getName().length() - 4))));
                }
            }
        }
    }
}
