package c2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import java.text.DecimalFormat;
import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.layer.overlay.Marker;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class o2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3100a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f3101b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f3102c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f3103d;

    public o2(Context context, Drawable drawable) {
        this.f3100a = false;
        this.f3103d = context;
        k3 k3Var = new k3(new LatLong(0.0d, 0.0d), AndroidGraphicFactory.convertToBitmap(drawable), 0, 0);
        this.f3101b = k3Var;
        k3Var.setVisible(false);
        k3Var.requestRedraw();
        this.f3102c = null;
    }

    public o2(Object obj) {
        this.f3100a = false;
        this.f3101b = obj;
        this.f3102c = null;
        this.f3103d = null;
    }

    public void a(LatLong cVar, float f7) {
        Drawable drawableB = ContextCompat.getDrawable((Context) this.f3103d, R.drawable.walking_freccia1);
        if (drawableB != null) {
            ((k3) this.f3101b).o(AndroidGraphicFactory.convertToBitmap(drawableB));
            ((k3) this.f3101b).p(new LatLong(cVar.latitude, cVar.longitude));
            Object obj = this.f3101b;
            ((k3) obj).f3037q = f7;
            ((k3) obj).requestRedraw();
        }
        Object obj2 = this.f3102c;
        if (((Marker) obj2) != null) {
            Marker bVar = (Marker) obj2;
            bVar.setVisible(false);
            bVar.requestRedraw();
        }
    }

    public void b(boolean z) {
        Object obj = this.f3101b;
        if (((k3) obj) != null) {
            k3 k3Var = (k3) obj;
            k3Var.setVisible(z);
            k3Var.requestRedraw();
        }
        Object obj2 = this.f3102c;
        if (((Marker) obj2) != null) {
            Marker bVar = (Marker) obj2;
            bVar.setVisible(z);
            bVar.requestRedraw();
        }
    }

    public void c(boolean z, Double d8, double d9, double d10, double d11, float f7) {
        DecimalFormat decimalFormat = new DecimalFormat("###0");
        DecimalFormat decimalFormat2 = new DecimalFormat("###0.00");
        DecimalFormat decimalFormat3 = new DecimalFormat("###0.0000");
        String str = decimalFormat3.format(d8);
        String str2 = decimalFormat3.format(d9);
        String str3 = decimalFormat.format(MainActivity.W(d10));
        String str4 = decimalFormat2.format(MainActivity.U(d11));
        StringBuilder sbA = new StringBuilder(" ");
        sbA.append(((Context) this.f3103d).getResources().getString(R.string.latitude_short));
        sbA.append("=");
        sbA.append(str);
        sbA.append("° ");
        sbA.append(((Context) this.f3103d).getResources().getString(R.string.longitude_short));
        sbA.append("=");
        sbA.append(str2);
        sbA.append("°");
        String string = sbA.toString();
        String str5 = ((Context) this.f3103d).getResources().getString(R.string.elevation_short) + "=" + str3 + MainActivity.y() + " " + ((Context) this.f3103d).getResources().getString(R.string.distance_short) + "=" + str4 + MainActivity.x();
        Drawable drawableB = ContextCompat.getDrawable((Context) this.f3103d, R.drawable.walking_freccia1);
        if (drawableB != null) {
            ((k3) this.f3101b).o(AndroidGraphicFactory.convertToBitmap(drawableB));
            ((k3) this.f3101b).p(new LatLong(d8.doubleValue(), d9));
            Object obj = this.f3101b;
            ((k3) obj).f3037q = f7;
            ((k3) obj).requestRedraw();
        }
        if (z) {
            LatLong cVar = new LatLong(d8.doubleValue(), d9);
            String strB = str5 + "\n" + string;
            TextView textView = new TextView((Context) this.f3103d);
            textView.setBackground(ContextCompat.getDrawable((Context) this.f3103d, R.drawable.balloon_overlay_unfocused));
            textView.setGravity(17);
            textView.setMaxEms(20);
            textView.setTextSize(14.0f);
            textView.setText(strB);
            Bitmap bVarB = n2.b((Context) this.f3103d, textView);
            Marker bVar = (Marker) this.f3102c;
            if (bVar != null) {
                MainActivity.f3626n1.b(bVar);
            }
            if (strB.equals(XmlPullParser.NO_NAMESPACE)) {
                return;
            }
            Marker bVar2 = new Marker(cVar, bVarB, 0, (-bVarB.getHeight()) / 2);
            this.f3102c = bVar2;
            bVar2.setVisible(true);
            bVar2.requestRedraw();
            MainActivity.f3626n1.a((Marker) this.f3102c);
        }
    }
}
