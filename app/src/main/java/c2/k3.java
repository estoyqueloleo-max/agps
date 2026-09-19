package c2;

import android.graphics.Canvas;
import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.core.model.Rectangle;
import org.mapsforge.core.model.Rotation;
import org.mapsforge.core.util.MercatorProjection;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.layer.overlay.Marker;

/* JADX INFO: loaded from: classes.dex */
public class k3 extends Marker {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Bitmap f3035o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public LatLong f3036p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f3037q;

    public k3(LatLong cVar, Bitmap bVar, int i7, int i8) {
        super(cVar, bVar, i7, i8);
        this.f3035o = bVar;
        this.f3036p = cVar;
        this.f3037q = 0.0f;
    }

    @Override
    public synchronized void draw(BoundingBox boundingBox, byte zoomLevel, org.mapsforge.core.graphics.Canvas canvas, Point topLeftPoint, Rotation rotation) {
        if (this.f3036p != null && this.f3035o != null) {
            long mapSize = MercatorProjection.getMapSize(zoomLevel, this.displayModel.getTileSize());
            double pixelX = MercatorProjection.longitudeToPixelX(this.f3036p.longitude, mapSize);
            double pixelY = MercatorProjection.latitudeToPixelY(this.f3036p.latitude, mapSize);
            int halfWidth = this.f3035o.getWidth() / 2;
            int halfHeight = this.f3035o.getHeight() / 2;
            int left = (int) ((pixelX - topLeftPoint.x) - halfWidth);
            int top = (int) ((pixelY - topLeftPoint.y) - halfHeight);
            Rectangle markerRect = new Rectangle(left, top, left + this.f3035o.getWidth(), top + this.f3035o.getHeight());
            Rectangle canvasRect = new Rectangle(0.0d, 0.0d, canvas.getWidth(), canvas.getHeight());
            if (canvasRect.intersects(markerRect)) {
                Canvas androidCanvas = AndroidGraphicFactory.getCanvas(canvas);
                androidCanvas.save();
                androidCanvas.rotate(this.f3037q, (float) (pixelX - topLeftPoint.x), (float) (pixelY - topLeftPoint.y));
                canvas.drawBitmap(this.f3035o, left, top);
                androidCanvas.restore();
            }
        }
    }

    public void o(Bitmap bVar) {
        setBitmap(bVar);
        this.f3035o = bVar;
    }

    public void p(LatLong cVar) {
        setLatLong(cVar);
        this.f3036p = cVar;
    }
}
