package c2;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import c2.m2.b;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.MapRenderPreferences;
import com.giobat.AgpsTrackerPP.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import org.mapsforge.core.graphics.GraphicFactory;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.datastore.MultiMapDataStore;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.model.MapViewPosition;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class x2 extends TileRendererLayer {
    public Context A;
    public m2 B;

    public class a implements DialogInterface.OnClickListener {
        public a(x2 x2Var) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            dialogInterface.dismiss();
        }
    }

    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ String f3232h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ LatLong f3233i;

        public b(String str, LatLong cVar) {
            this.f3232h = str;
            this.f3233i = cVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            m2 m2Var = x2.this.B;
            String str = this.f3232h;
            m2Var.f3067k = this.f3233i;
            IntentFilter intentFilter = new IntentFilter("SFTP_OP_RESULT");
            if (m2Var.f3066j == null) {
                m2Var.f3066j = m2Var.new b(m2Var.f3057a, null);
                LocalBroadcastManager.getInstance(m2Var.f3057a).registerReceiver(m2Var.f3066j, intentFilter);
            }
            v2.e("GPS-M", "==> sFTP service started");
            String str2 = AgpsApplication.f3578u;
            com.giobat.AgpsTrackerPP.b bVar = m2Var.f3061e;
            if (bVar != null) {
                bVar.f3758j = "SFTP_SD_DEM";
                bVar.f3759k = str2;
                bVar.f3760l = str;
                bVar.m = XmlPullParser.NO_NAMESPACE;
                Executors.newSingleThreadExecutor().submit(m2Var.f3061e);
            }
            dialogInterface.dismiss();
        }
    }

    public x2(TileCache tileCache, MultiMapDataStore mapDataStore, MapViewPosition mapViewPosition, GraphicFactory graphicFactory, Context context, m2 m2Var) {
        super(tileCache, mapDataStore, mapViewPosition, false, true, MapRenderPreferences.isCacheLabelsEnabled(context), graphicFactory);
        this.A = context;
        this.B = m2Var;
    }

    @Override
    public boolean onLongPress(LatLong cVar, Point gVar, Point gVar2) {
        boolean z;
        boolean z7;
        boolean z8;
        if (MainActivity.f3619g1) {
            onTap(cVar, gVar, gVar2);
            return true;
        }
        com.giobat.AgpsTrackerPP.b bVar = this.B.f3061e;
        if (bVar != null && bVar.d()) {
            return false;
        }
        LatLong cVar2 = new LatLong(cVar.latitude, cVar.longitude);
        Iterator it = ((ArrayList) o.f3084p).iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (((n) it.next()).f3072b.contains(cVar2)) {
                z = true;
                break;
            }
        }
        if (!z) {
            Context context = this.A;
            boolean z9 = AgpsApplication.f3566h;
            File fileX = MainActivity.X(context, "Lists/demsList.txt", 4);
            String str = n.a(cVar2) + ".SRTMGL1.hgt.zip";
            String strC = com.giobat.AgpsTrackerPP.b.c(fileX, str);
            v2.e("GPS-M", "==> Full Dem Name = " + strC);
            if (strC.equals("NOT_FOUND")) {
                MainActivity.f3625m1.a(this.A.getString(R.string.no_dem_for_this_area));
                return false;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this.A);
            builder.setTitle(this.A.getString(R.string.dem_not_found));
            builder.setMessage(this.A.getResources().getString(R.string.do_you_want_to_load_dem));
            builder.setNegativeButton(this.A.getResources().getString(R.string.no), new a(this));
            builder.setPositiveButton(this.A.getResources().getString(R.string.yes), new b(str, cVar2));
            builder.show();
            return true;
        }
        Context context2 = this.A;
        LatLong cVar3 = new LatLong(cVar.latitude, cVar.longitude);
        try {
            BoundingBox aVarB = o.b(cVar3);
            LatLong cVar4 = new LatLong(aVarB.maxLatitude, aVarB.minLongitude);
            LatLong cVar5 = new LatLong(aVarB.maxLatitude, aVarB.maxLongitude);
            LatLong cVar6 = new LatLong(aVarB.minLatitude, aVarB.maxLongitude);
            LatLong cVar7 = new LatLong(aVarB.minLatitude, aVarB.minLongitude);
            ArrayList arrayList = new ArrayList();
            Iterator it2 = ((ArrayList) o.f3084p).iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z7 = false;
                    break;
                }
                if (((n) it2.next()).f3072b.contains(cVar3)) {
                    z7 = true;
                    break;
                }
            }
            if (z7) {
                for (n nVar : (List<n>) (List<?>) o.f3084p) {
                    if (nVar.f3072b.contains(cVar4) || nVar.f3072b.contains(cVar5) || nVar.f3072b.contains(cVar6) || nVar.f3072b.contains(cVar7)) {
                        if (arrayList.size() != 0) {
                            Iterator it3 = arrayList.iterator();
                            z8 = false;
                            while (it3.hasNext()) {
                                if (((String) it3.next()).matches(nVar.f3071a)) {
                                    z8 = true;
                                }
                            }
                        } else {
                            z8 = false;
                        }
                        if (!z8) {
                            arrayList.add(nVar.f3071a);
                        }
                    }
                }
                LatLong cVar8 = new LatLong(0.0d, 0.0d);
                Iterator it4 = arrayList.iterator();
                boolean z10 = true;
                while (it4.hasNext()) {
                    o oVar = new o(context2, (String) it4.next());
                    if (z10) {
                        double d8 = cVar3.latitude;
                        double d9 = oVar.f3091g - d8;
                        double d10 = oVar.f3090f;
                        double d11 = (d8 - (d9 % d10)) + d10;
                        double d12 = cVar3.longitude;
                        z10 = false;
                        cVar8 = new LatLong(d11, d12 - ((d12 - oVar.f3094j) % oVar.f3089e));
                    }
                    if (AgpsApplication.B < 11) {
                        break;
                    }
                    Executors.newSingleThreadExecutor().submit(new FutureTask(new j(cVar8, context2, oVar)));
                    MainActivity.f3625m1.a(context2.getResources().getString(R.string.preparing_contour));
                }
            }
        } catch (Exception e8) {
            androidx.appcompat.widget.d0.c("AllContours:", e8, MainActivity.f3625m1);
        }
        return true;
    }

    @Override
    public boolean onTap(LatLong cVar, Point gVar, Point gVar2) {
        Location location = new Location("dummyprovider");
        location.setLatitude(cVar.latitude);
        location.setLongitude(cVar.longitude);
        v2.e("GPS-I", "-->onTap");
        int i7 = 0;
        double dDistanceTo = 1.0E7d;
        if (!MainActivity.f3619g1) {
            MainActivity.f3636x1 = !MainActivity.f3636x1;
            y3 y3Var = MainActivity.Y0;
            if (y3Var == null) {
                return false;
            }
            x3 x3Var = y3Var.f3274i;
            x3Var.c();
            if (MainActivity.Y0.f3273h && x3Var.i() > 1) {
                double dDistanceTo2 = 1.0E7d;
                int i8 = 0;
                for (Location location2 : x3Var.f3238c) {
                    if (location2.distanceTo(location) < dDistanceTo2) {
                        dDistanceTo2 = location2.distanceTo(location);
                        i7 = i8;
                    }
                    i8++;
                }
                Location location3 = x3Var.f3238c.get(i7);
                double latitude = location3.getLatitude();
                double longitude = location3.getLongitude();
                double altitude = location3.getAltitude();
                double d8 = x3Var.f3239d[i7] / 1000.0d;
                float fBearingTo = i7 < x3Var.f3238c.size() - 1 ? location3.bearingTo(x3Var.f3238c.get(i7 + 1)) : 0.0f;
                MainActivity.Y0.f3272g.b(true);
                MainActivity.Y0.f3272g.c(true, Double.valueOf(latitude), longitude, altitude, d8, fBearingTo);
                y3 y3Var2 = MainActivity.Y0;
                y3Var2.f3266a = i7;
                y3Var2.a();
            }
            return true;
        }
        v2.e("GPS-I", "-->isNewPoi ");
        if (MainActivity.f3620h1) {
            x3 x3Var2 = AgpsApplication.f3574q;
            if (x3Var2 != null && x3Var2.i() > 1) {
                AgpsApplication.f3574q.c();
            }
            x3 x3Var3 = AgpsApplication.f3574q;
            if (x3Var3 != null && x3Var3.i() > 1) {
                int i9 = 0;
                int i10 = 0;
                for (Location location4 : AgpsApplication.f3574q.f3238c) {
                    if (location4.distanceTo(location) < dDistanceTo) {
                        dDistanceTo = location4.distanceTo(location);
                        i9 = i10;
                    }
                    i10++;
                }
                Location location5 = AgpsApplication.f3574q.f3238c.get(i9);
                Intent intent = new Intent(this.A, (Class<?>) MainActivity.class);
                intent.setAction("POI_ON_TRACK");
                intent.putExtra("LATIT", location5.getLatitude());
                intent.putExtra("LONGIT", location5.getLongitude());
                intent.putExtra("ALTIT", location5.getAltitude());
                MainActivity.f3619g1 = false;
                v2.e("GPS-I", "-->LocalBroadcast NEW_POI_ON_TRACK_Loaded");
                LocalBroadcastManager.getInstance(this.A).sendBroadcast(intent);
            }
        } else {
            Intent intent2 = new Intent(this.A, (Class<?>) MainActivity.class);
            intent2.setAction("POI_ON_MAP");
            intent2.putExtra("LATIT", location.getLatitude());
            intent2.putExtra("LONGIT", location.getLongitude());
            intent2.putExtra("ALTIT", location.getAltitude());
            MainActivity.f3619g1 = false;
            v2.e("GPS-I", "-->LocalBroadcast NEW_POI_ON_MAP_LoadedTrack");
            LocalBroadcastManager.getInstance(this.A).sendBroadcast(intent2);
        }
        return true;
    }
}
