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
        byte bZ = MainActivity.M0.getModel().mapViewPosition.getZoomLevel();
        if (bZ != m2.f3054t) {
            byte b8 = AgpsApplication.B;
            if (b8 >= 6 && b8 <= 8) {
                m2.f3055u = true;
            }
            byte b9 = m2.f3054t;
            if (b9 == 6 || b9 == 8) {
                MainActivity.U0 = true;
            }
            v2.e("GPS-M", this.f3042h.f3064h + " New zoom level " + ((int) bZ));
            o.c(15);
            boolean z = MainActivity.M0.getModel().mapViewPosition.getZoomLevel() == 8;
            CopyOnWriteArrayList<Marker> copyOnWriteArrayList = AgpsApplication.x;
            if (copyOnWriteArrayList != null) {
                for (Marker bVar : copyOnWriteArrayList) {
                    bVar.setVisible(z);
                    bVar.requestRedraw();
                }
            }
            m2.f3054t = bZ;
            AgpsApplication.B = bZ;
        }
    }
}
