package c2;

import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.LatLong;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3071a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BoundingBox f3072b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public o f3073c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public File f3074d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RandomAccessFile f3075e;

    public n(String str, BoundingBox aVar) {
        this.f3074d = null;
        this.f3075e = null;
        this.f3071a = str;
        this.f3072b = aVar;
        this.f3073c = new o(MainActivity.Z0, this.f3071a);
        try {
            File fileX = MainActivity.X(MainActivity.Z0.getApplicationContext(), "Dems/" + this.f3071a, 1);
            this.f3074d = fileX;
            if (fileX != null) {
                this.f3075e = new RandomAccessFile(this.f3074d, "r");
            }
        } catch (IOException e8) {
            this.f3074d = null;
            this.f3075e = null;
            v2.e("GPS-M", "Error reading dems:" + e8);
        }
    }

    public static String a(LatLong cVar) {
        int iFloor;
        String str;
        int iFloor2;
        String str2;
        if (Math.floor(cVar.latitude) >= 0.0d) {
            iFloor = (int) Math.floor(cVar.latitude);
            str = "N";
        } else {
            iFloor = (int) Math.floor((-cVar.latitude) + 1.0d);
            str = "S";
        }
        if (Math.floor(cVar.longitude) >= 0.0d) {
            iFloor2 = (int) Math.floor(cVar.longitude);
            str2 = "E";
        } else {
            iFloor2 = (int) Math.floor((-cVar.longitude) + 1.0d);
            str2 = "W";
        }
        DecimalFormat decimalFormat = new DecimalFormat("00");
        DecimalFormat decimalFormat2 = new DecimalFormat("000");
        StringBuilder sbA = android.support.v4.media.b.a(str);
        sbA.append(decimalFormat.format(iFloor));
        sbA.append(str2);
        sbA.append(decimalFormat2.format(iFloor2));
        return sbA.toString();
    }

    public synchronized int b(LatLong cVar) {
        short sReverseBytes;
        short sReverseBytes2;
        BoundingBox aVar = this.f3072b;
        if (aVar != null && aVar.contains(cVar)) {
            File file = this.f3074d;
            if (file == null) {
                return AgpsApplication.f3579v;
            }
            try {
                long length = file.length() - 1;
                int iFloor = (int) Math.floor((this.f3072b.maxLatitude - cVar.latitude) / this.f3073c.f3090f);
                int iFloor2 = (int) Math.floor((cVar.longitude - this.f3072b.minLongitude) / this.f3073c.f3089e);
                boolean zMatches = this.f3073c.f3085a.matches("I");
                long j7 = (iFloor2 * 2) + (this.f3073c.f3095k * iFloor);
                this.f3075e.seek(j7);
                short sReverseBytes3 = this.f3075e.readShort();
                short sReverseBytes4 = this.f3075e.readShort();
                long j8 = j7 + ((long) this.f3073c.f3095k);
                if (j8 < length) {
                    this.f3075e.seek(j8);
                    sReverseBytes = this.f3075e.readShort();
                    sReverseBytes2 = this.f3075e.readShort();
                } else {
                    sReverseBytes = sReverseBytes3;
                    sReverseBytes2 = sReverseBytes4;
                }
                if (zMatches) {
                    sReverseBytes3 = Short.reverseBytes(sReverseBytes3);
                    sReverseBytes4 = Short.reverseBytes(sReverseBytes4);
                    sReverseBytes = Short.reverseBytes(sReverseBytes);
                    sReverseBytes2 = Short.reverseBytes(sReverseBytes2);
                }
                double[] dArr = {sReverseBytes3, sReverseBytes4, sReverseBytes, sReverseBytes2};
                for (int i7 = 0; i7 < 4; i7++) {
                    double d8 = dArr[i7];
                    int i8 = AgpsApplication.f3579v;
                    if (d8 == i8) {
                        return i8;
                    }
                }
                return c(dArr, cVar, iFloor, iFloor2);
            } catch (Exception e8) {
                MainActivity.f3625m1.a("DEM Exception: " + e8);
                return AgpsApplication.f3579v;
            }
        }
        return AgpsApplication.f3579v;
    }

    public int c(double[] dArr, LatLong cVar, int i7, int i8) {
        double d8 = cVar.latitude;
        BoundingBox aVar = this.f3072b;
        double d9 = aVar.maxLatitude;
        o oVar = this.f3073c;
        double d10 = oVar.f3090f;
        double d11 = (-(d8 - (d9 - (((double) i7) * d10)))) / d10;
        double d12 = cVar.longitude;
        double d13 = aVar.minLongitude;
        double d14 = oVar.f3089e;
        double d15 = (d12 - ((((double) i8) * d14) + d13)) / d14;
        double d16 = ((dArr[2] - dArr[0]) * d11) + dArr[0];
        double d17 = ((dArr[3] - dArr[1]) * d11) + dArr[1];
        double d18 = ((dArr[1] - dArr[0]) * d15) + dArr[0];
        double d19 = d17 * d15;
        return (int) (((((((dArr[3] - dArr[2]) * d15) + dArr[2]) * d11) + ((1.0d - d11) * d18)) + (d19 + ((1.0d - d15) * d16))) / 2.0d);
    }
}
