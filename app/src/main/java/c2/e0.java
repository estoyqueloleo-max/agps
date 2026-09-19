package c2;

import android.location.Location;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.core.model.Tile;
import org.mapsforge.core.util.MercatorProjection;

/* JADX INFO: loaded from: classes.dex */
public class e0 {

    public e0() {
    }

    public e0(MainActivity mainActivity) {
    }

    public void a(Object obj) {
        Location location = (Location) obj;
        if (location != null && MainActivity.f3613a1 != null) {
            MainActivity.f3613a1.set(location);
            v2.e("GPS-M", "Got last known location");
        }
    }

    public static int d(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new RuntimeException("Cannot deserialize null byte buffer.");
        }
        if (byteBuffer.array().length < 4) {
            throw new RuntimeException("Cannot deserialize. Byte buffer must have at least 4 bytes.");
        }
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.position(0);
        return byteBuffer.getInt();
    }

    public static long e(byte b8, int i7) {
        return MercatorProjection.getMapSize(b8, i7);
    }

    public static Point f(LatLong cVar, long j7) {
        return MercatorProjection.getPixelAbsolute(cVar, j7);
    }

    public static Point g(LatLong cVar, long j7) {
        return MercatorProjection.getPixelRelative(cVar, j7, 0.0d, 0.0d);
    }

    public static Point h(LatLong cVar, long j7, double d8, double d9) {
        return MercatorProjection.getPixelRelative(cVar, j7, d8, d9);
    }

    public static Point i(LatLong cVar, Tile jVar) {
        return MercatorProjection.getPixelRelativeToTile(cVar, jVar);
    }

    public static double j(double d8, long j7) {
        return MercatorProjection.latitudeToPixelY(d8, j7);
    }

    public static int k(double d8, byte b8) {
        return MercatorProjection.latitudeToTileY(d8, b8);
    }

    public static double l(double d8, long j7) {
        return MercatorProjection.longitudeToPixelX(d8, j7);
    }

    public static int m(double d8, byte b8) {
        return MercatorProjection.longitudeToTileX(d8, b8);
    }

    public static double n(double d8, long j7) {
        return MercatorProjection.pixelXToLongitude(d8, j7);
    }

    public static double o(double d8, long j7) {
        return MercatorProjection.pixelYToLatitude(d8, j7);
    }

    public static double q(long j7, byte b8) {
        return MercatorProjection.tileXToLongitude(j7, b8);
    }

    public static double r(long j7, byte b8) {
        return MercatorProjection.tileYToLatitude(j7, b8);
    }
}
