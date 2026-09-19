package c2;

import android.content.Context;
import android.widget.TextView;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.layer.overlay.Marker;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static List<m> f3004a = new ArrayList();

    public i(Context context) {
        InputStream inputStream;
        z6.a aVar;
        z6.b bVar = new z6.b();
        bVar.f19032b = 60000;
        try {
            InputStream inputStreamOpenRawResource = context.getResources().openRawResource(context.getResources().getIdentifier("tm_world_borders_03", "raw", context.getPackageName()));
            z6.a aVar2 = new z6.a(inputStreamOpenRawResource, bVar);
            ((ArrayList) f3004a).clear();
            int i7 = 0;
            int i8 = 0;
            while (true) {
                c7.a aVarA = aVar2.a();
                if (aVarA == null) {
                    v2.e("GPS-M", "Total shapes read: " + i7);
                    inputStreamOpenRawResource.close();
                    return;
                }
                int iC = aVarA.f3502b - 1;
                if (iC != 1) {
                    if (iC == 3) {
                        d7.p pVar = (d7.p) aVarA;
                        int i9 = i8;
                        while (i8 < pVar.f4655c) {
                            c7.d[] dVarArrA = pVar.a(i8);
                            ArrayList<LatLong> arrayList = new ArrayList<>();
                            for (int i10 = i9; i10 < dVarArrA.length; i10++) {
                                arrayList.add(new LatLong(dVarArrA[i10].f3505b, dVarArrA[i10].f3504a));
                            }
                            double d8 = arrayList.get(i9).latitude;
                            double d9 = arrayList.get(i9).longitude;
                            double d10 = d8;
                            BoundingBox aVar3 = null;
                            double d11 = d9;
                            double d12 = d10;
                            for (LatLong cVar : arrayList) {
                                InputStream inputStream2 = inputStreamOpenRawResource;
                                z6.a aVar4 = aVar2;
                                double d13 = cVar.latitude;
                                d12 = d13 < d12 ? d13 : d12;
                                d10 = d13 > d10 ? d13 : d10;
                                double d14 = cVar.longitude;
                                d9 = d14 < d9 ? d14 : d9;
                                if (d14 > d11) {
                                    d11 = d14;
                                }
                                aVar3 = new BoundingBox(d12, d9, d10, d11);
                                inputStreamOpenRawResource = inputStream2;
                                aVar2 = aVar4;
                            }
                            ((ArrayList) f3004a).add(new m(arrayList, aVar3));
                            i8++;
                            i9 = 0;
                            inputStreamOpenRawResource = inputStreamOpenRawResource;
                            aVar2 = aVar2;
                        }
                    } else if (iC != 8) {
                        v2.e("GPS-M", "Read other type of shape.");
                    } else {
                        v2.e("GPS-M", "Multi Point");
                    }
                    inputStream = inputStreamOpenRawResource;
                    aVar = aVar2;
                } else {
                    inputStream = inputStreamOpenRawResource;
                    aVar = aVar2;
                    v2.e("GPS-M", "Single point");
                }
                i7++;
                i8 = 0;
                inputStreamOpenRawResource = inputStream;
                aVar2 = aVar;
            }
        } catch (Exception e8) {
            androidx.appcompat.widget.d0.c("AllCountries:", e8, MainActivity.f3625m1);
        }
    }

    public void a() {
        v2.e("GPS-M", "Showing Country borders");
        BoundingBox boundingBox = MainActivity.M0.getBoundingBox();
        int i7 = 0;
        for (m mVar : f3004a) {
            if (mVar.f3045b.intersects(boundingBox)) {
                o.f(mVar.f3044a, 3);
                i7++;
            }
        }
        StringBuilder sbA = new StringBuilder(" zoom=");
        sbA.append((int) AgpsApplication.B);
        sbA.append(" Nmostrati=");
        sbA.append(i7);
        v2.e("zxc", sbA.toString());
    }

    public void b(Context context) {
        if (m2.f3056v == null) {
            m2.h(context);
        }
        BoundingBox boundingBox = MainActivity.M0.getBoundingBox();
        for (r3 r3Var : m2.f3056v) {
            LatLong cVarC = r3Var.f3150b.getCenterPoint();
            if (boundingBox.contains(cVarC)) {
                String strB0 = MainActivity.b0(r3Var.f3149a);
                int length = r3Var.f3149a.split("/").length;
                int iLastIndexOf = strB0.lastIndexOf(46);
                String strSubstring = iLastIndexOf > 0 ? strB0.substring(0, iLastIndexOf) : "?";
                TextView textView = new TextView(context);
                textView.setGravity(17);
                if (length == 3) {
                    textView.setTextSize(12.0f);
                    if (AgpsApplication.B == 6) {
                        textView.setText(strSubstring);
                        Bitmap bVarB = n2.b(context, textView);
                        Marker bVar = new Marker(cVarC, bVarB, 0, (-bVarB.getHeight()) / 2);
                        MainActivity.f3626n1.a(bVar);
                        bVar.setVisible(true);
                        bVar.requestRedraw();
                    }
                } else {
                    textView.setTextSize(12.0f);
                    if (AgpsApplication.B == 8) {
                        textView.setText(strSubstring);
                        Bitmap bVarB2 = n2.b(context, textView);
                        Marker bVar2 = new Marker(cVarC, bVarB2, 0, (-bVarB2.getHeight()) / 2);
                        MainActivity.f3626n1.a(bVar2);
                        bVar2.setVisible(true);
                        bVar2.requestRedraw();
                    }
                }
            }
        }
    }
}
