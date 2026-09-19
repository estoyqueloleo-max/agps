package c2;

import android.content.Context;
import android.net.Uri;
import com.giobat.AgpsTrackerPP.ActivityMyPhotoShow;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class h3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2999a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public org.mapsforge.core.model.LatLong f3000b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v3 f3001c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Uri f3002d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f3003e;

    public h3(Uri uri, String str, org.mapsforge.core.model.LatLong cVar, int i7) {
        this.f2999a = str;
        this.f3000b = cVar;
        this.f3001c = null;
        this.f3002d = uri;
    }

    public h3(Uri uri, Context context) {
        this.f3002d = uri;
        try {
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(this.f3002d);
            u0.a aVar = new u0.a(inputStreamOpenInputStream);
            inputStreamOpenInputStream.close();
            double[] dArrK = aVar.k();
            if (dArrK != null && dArrK.length == 2) {
                v2.e("GPS-I", "Photo :  " + this.f3002d.getPath());
                this.f3000b = new org.mapsforge.core.model.LatLong(dArrK[0], dArrK[1]);
                aVar.q();
                String strF = aVar.f("UserComment");
                this.f2999a = strF;
                if (strF == null) {
                    this.f2999a = XmlPullParser.NO_NAMESPACE;
                }
                this.f3003e = ActivityMyPhotoShow.y(aVar);
                return;
            }
            v2.e("GPS-M", "Photo LatLon= ??? ");
        } catch (Exception e8) {
            androidx.appcompat.widget.d.c("PhotoPoint: ", e8, "GPS-I");
        }
    }
}
