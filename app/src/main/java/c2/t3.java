package c2;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.widget.TextView;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.layer.overlay.Marker;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class t3 extends Marker {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static Marker f3185s;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final a4 f3186o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Context f3187p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3188q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f3189r;

    public t3(Context context, int i7, a4 a4Var) {
        super(new LatLong(a4Var.f2959c.getLatitude(), a4Var.f2959c.getLongitude()), AndroidGraphicFactory.convertToBitmap(context.getResources().getDrawable(i7, context.getTheme())), 0, 0);
        this.f3188q = false;
        this.f3187p = context;
        this.f3189r = 0;
        if (a4Var.f2960d == 3) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(i7, context.getTheme());
            this.f3189r = bitmapDrawable.getBitmap().getHeight();
        }
        int i8 = (-this.f3189r) / 2;
        setVerticalOffset(i8);
        this.f3186o = a4Var;
    }

    @Override
    public boolean onLongPress(LatLong cVar, Point gVar, Point gVar2) {
        double dM = gVar.x + ((double) getHorizontalOffset());
        double dN = gVar.y + ((double) getVerticalOffset());
        double width = (getBitmap().getWidth() / 2) * 2;
        double height = (getBitmap().getHeight() / 2) * 2;
        double dAbs = Math.abs(dM - gVar2.x);
        double dAbs2 = Math.abs(dN - gVar2.y);
        if (dAbs >= width || dAbs2 >= height) {
            return false;
        }
        MainActivity.f3618f1 = this.f3186o;
        Intent intent = new Intent(this.f3187p, (Class<?>) MainActivity.class);
        intent.setAction("POI_LONG_PRESSED_POS");
        LocalBroadcastManager.getInstance(this.f3187p).sendBroadcast(intent);
        return true;
    }

    @Override
    public boolean onTap(LatLong cVar, Point gVar, Point gVar2) {
        double d8;
        double dM = gVar.x + ((double) getHorizontalOffset());
        double dN = gVar.y + ((double) getVerticalOffset());
        double width = (getBitmap().getWidth() / 2) * 2;
        double height = (getBitmap().getHeight() / 2) * 2;
        double dAbs = Math.abs(dM - gVar2.x);
        double dAbs2 = Math.abs(dN - gVar2.y);
        Locale locale = Locale.US;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", locale);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm", locale);
        if (dAbs >= width || dAbs2 >= height) {
            if (this.f3188q) {
                this.f3188q = false;
                if (f3185s != null) {
                    MainActivity.f3626n1.b(f3185s);
                }
            }
            return false;
        }
        a4 a4Var = this.f3186o;
        String str = a4Var.f2957a;
        String strA = str + "\n" + this.f3186o.f2958b + "\n" + (a4Var.f2959c.getTime() != 0 ? this.f3187p.getString(R.string.date) + simpleDateFormat.format(Long.valueOf(this.f3186o.f2959c.getTime())) + "\n" + this.f3187p.getString(R.string.time_h) + simpleDateFormat2.format(Long.valueOf(this.f3186o.f2959c.getTime())) : "----");
        a4 a4Var2 = this.f3186o;
        Location location = a4Var2.f2959c;
        x3 x3Var = AgpsApplication.f3575r;
        Iterator<a4> it = x3Var.f3237b.iterator();
        do {
            if (!it.hasNext()) {
                x3Var = AgpsApplication.f3574q;
                Iterator<a4> it2 = x3Var.f3237b.iterator();
                do {
                    if (!it2.hasNext()) {
                        x3Var = null;
                        break;
                    }
                } while (it2.next() != a4Var2);
            }
        } while (it.next() != a4Var2);
        if (x3Var == null) {
            d8 = -1.0d;
        } else {
            x3Var.c();
            double dDistanceTo = 1.0E7d;
            if (x3Var.i() > 1) {
                int i7 = 0;
                int i8 = 0;
                for (Location location2 : x3Var.f3238c) {
                    if (location2.distanceTo(location) < dDistanceTo) {
                        dDistanceTo = location2.distanceTo(location);
                        i7 = i8;
                    }
                    i8++;
                }
                if (dDistanceTo < 100.0d) {
                    d8 = x3Var.f3239d[i7] / 1000.0d;
                } else {
                    d8 = -1.0d;
                }
            } else {
                d8 = -1.0d;
            }
        }
        StringBuilder sbA = android.support.v4.media.b.a(strA);
        Double dValueOf = Double.valueOf(d8);
        Double dValueOf2 = Double.valueOf(this.f3186o.f2959c.getLatitude());
        double longitude = this.f3186o.f2959c.getLongitude();
        double altitude = this.f3186o.f2959c.getAltitude();
        DecimalFormat decimalFormat = new DecimalFormat("###0");
        DecimalFormat decimalFormat2 = new DecimalFormat("###0.00");
        DecimalFormat decimalFormat3 = new DecimalFormat("###0.0000");
        String str2 = decimalFormat3.format(dValueOf2);
        String str3 = decimalFormat3.format(longitude);
        String str4 = decimalFormat.format(MainActivity.W(altitude));
        StringBuilder sbA2 = androidx.activity.result.d.a("\n", dValueOf.doubleValue() != -1.0d ? this.f3187p.getString(R.string.distance) + "=" + decimalFormat2.format(dValueOf) + MainActivity.x() : "----", "\n");
        sbA2.append(this.f3187p.getResources().getString(R.string.latitude_short));
        sbA2.append("=");
        sbA2.append(str2);
        sbA2.append("°\n");
        sbA2.append(this.f3187p.getResources().getString(R.string.longitude_short));
        sbA2.append("=");
        sbA2.append(str3);
        sbA2.append("°\n");
        sbA2.append(this.f3187p.getResources().getString(R.string.elevation_short));
        sbA2.append("=");
        sbA2.append(str4);
        sbA2.append(MainActivity.y());
        sbA.append(sbA2.toString());
        String string = sbA.toString();
        LatLong cVar2 = new LatLong(this.f3186o.f2959c.getLatitude(), this.f3186o.f2959c.getLongitude());
        this.f3188q = true;
        TextView textView = new TextView(this.f3187p);
        Resources resources = this.f3187p.getResources();
        textView.setBackground(resources.getDrawable(R.drawable.balloon_overlay_unfocused, null));
        textView.setGravity(3);
        textView.setMaxEms(20);
        textView.setTextSize(12.0f);
        textView.setText(string);
        org.mapsforge.core.graphics.Bitmap bVarB = n2.b(this.f3187p, textView);
        if (f3185s != null) {
            MainActivity.f3626n1.b(f3185s);
        }
        if (!string.equals(XmlPullParser.NO_NAMESPACE)) {
            f3185s = new Marker(cVar2, bVarB, 0, (-bVarB.getHeight()) / 2);
            MainActivity.f3626n1.a(f3185s);
        }
        return true;
    }
}
