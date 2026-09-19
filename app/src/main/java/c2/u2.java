package c2;

import java.util.concurrent.Semaphore;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.Layer;
import org.mapsforge.map.layer.Layers;
import org.mapsforge.map.layer.overlay.Marker;

/* JADX INFO: loaded from: classes.dex */
public class u2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Layers f3195a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Semaphore f3196b = new Semaphore(1, true);

    public u2(MapView mapView) {
        this.f3195a = mapView.getLayerManager().getLayers();
    }

    public void a(Layer aVar) {
        try {
            this.f3196b.acquire();
        } catch (Exception unused) {
        }
        try {
            this.f3195a.add(aVar, false);
        } catch (Exception unused2) {
        }
        this.f3196b.release();
    }

    public void b(Marker bVar) {
        try {
            this.f3196b.acquire();
        } catch (Exception unused) {
        }
        try {
            if (this.f3195a.contains(bVar)) {
                this.f3195a.remove(bVar, false);
            }
        } catch (Exception unused2) {
        }
        this.f3196b.release();
    }
}
