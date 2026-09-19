package c2;

import android.content.Intent;
import android.net.Uri;
import com.giobat.AgpsTrackerPP.ActivityMyPhotoShow;
import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.layer.overlay.Marker;

/* JADX INFO: loaded from: classes.dex */
public class u3 extends Marker {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ v3 f3197o;

    public u3(v3 v3Var, LatLong cVar, Bitmap bVar, int i7, int i8) {
        super(cVar, bVar, i7, i8);
        this.f3197o = v3Var;
    }

    @Override
    public boolean onTap(LatLong cVar, Point gVar, Point gVar2) {
        double dM = gVar.x + ((double) getHorizontalOffset());
        double dN = gVar.y + ((double) getVerticalOffset());
        double width = getBitmap().getWidth() / 2;
        double height = getBitmap().getHeight() / 2;
        double dAbs = Math.abs(dM - gVar2.x);
        double dAbs2 = Math.abs(dN - gVar2.y);
        if (dAbs >= width || dAbs2 >= height) {
            return false;
        }
        v2.e("GPS-M", "onTap thumbnail");
        v3 photoMarker = this.f3197o;
        Uri photoUri = photoMarker.f3211o.f3002d;
        Intent intent = new Intent(photoMarker.f3212p, (Class<?>) ActivityMyPhotoShow.class);
        intent.setData(photoUri);
        intent.putExtra("FILE_URI", photoUri);
        String photoTitle = (photoMarker.f3211o.f2999a != null && !photoMarker.f3211o.f2999a.isEmpty())
                ? photoMarker.f3211o.f2999a : photoUri.getLastPathSegment();
        intent.putExtra("filePath", photoTitle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        photoMarker.f3212p.startActivity(intent);
        return true;
    }
}
