package androidx.lifecycle;

import android.content.Intent;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.util.ArrayList;

public class d0 implements androidx.activity.result.b<androidx.activity.result.a> {
    public d0() {
    }

    public d0(MainActivity mainActivity) {
    }

    public static double l(int i7) {
        return ((double) i7) / 1000000.0d;
    }

    public static double m(double d8) {
        if (!Double.isNaN(d8) && d8 >= -90.0d && d8 <= 90.0d) {
            return d8;
        }
        throw new IllegalArgumentException("invalid latitude: " + d8);
    }

    public static double n(double d8) {
        if (!Double.isNaN(d8) && d8 >= -180.0d && d8 <= 180.0d) {
            return d8;
        }
        throw new IllegalArgumentException("invalid longitude: " + d8);
    }

    @Override
    public void a(androidx.activity.result.a aVar) {
        if (aVar == null || aVar.f288h != -1 || aVar.f289i == null) {
            return;
        }
        Intent intent = aVar.f289i;
        String stringExtra = intent.getStringExtra("RESULT_PATH");
        if (stringExtra == null) return;
        AgpsApplication.f3581y = true;
        MainActivity.U0 = true;
        if (c2.o.f3084p != null) {
            for (c2.n demItem : c2.o.f3084p) {
                String demPath = "Dems/" + demItem.f3071a;
                if (stringExtra.equals(demPath)) {
                    if (MainActivity.M0 != null && demItem.f3072b != null) {
                        MainActivity.M0.setCenter(demItem.f3072b.getCenterPoint());
                    }
                    break;
                }
            }
        }
    }
}
