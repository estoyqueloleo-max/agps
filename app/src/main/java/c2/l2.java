package c2;

import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.util.concurrent.CopyOnWriteArrayList;
import org.mapsforge.map.layer.overlay.Marker;
import org.mapsforge.map.model.common.Observer;

/* JADX INFO: loaded from: classes.dex */
public class l2 implements Observer {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ m2 f3042h;

    public l2(m2 m2Var) {
        this.f3042h = m2Var;
    }

    @Override
    public void onChange() {
        byte newZoomLevel = MainActivity.M0.getModel().mapViewPosition.getZoomLevel();
        if (newZoomLevel != m2.f3054t) {
            byte currentAppZoom = AgpsApplication.B;
            if (currentAppZoom >= 6 && currentAppZoom <= 8) {
                m2.f3055u = true;
            }
            byte previousZoom = m2.f3054t;
            if (previousZoom == 6 || previousZoom == 8) {
                MainActivity.U0 = true;
            }
            v2.e("GPS-M", this.f3042h.f3064h + " New zoom level " + ((int) newZoomLevel));
            o.c(13);
            boolean isOverviewZoom = MainActivity.M0.getModel().mapViewPosition.getZoomLevel() == 8;
            CopyOnWriteArrayList<Marker> overviewMarkers = AgpsApplication.x;
            if (overviewMarkers != null) {
                for (Marker overviewMarker : overviewMarkers) {
                    overviewMarker.setVisible(isOverviewZoom);
                    overviewMarker.requestRedraw();
                }
            }
            m2.f3054t = newZoomLevel;
            AgpsApplication.B = newZoomLevel;
        }
    }
}
