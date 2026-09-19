package c2;

import android.location.Location;

/* JADX INFO: loaded from: classes.dex */
public class i3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f3012a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3014c;

    public i3(double d8) {
        this.f3013b = 10000000;
        this.f3014c = 100000000;
        this.f3012a = d8;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:47:0x00f5  */
    public boolean a(x3 x3Var, Location location) {
        double d8 = 0.0d;
        double d9 = 0.0d;
        float fDistanceTo;
        double d10;
        int i7 = 0;
        for (q3 q3Var : x3Var.f3236a) {
            int i8 = 1;
            int i9 = i7 + 1;
            if (i9 <= this.f3013b) {
                int i10 = 0;
                for (Location location2 : q3Var.f3123b) {
                    i10++;
                    if (i10 >= this.f3014c) {
                        break;
                    }
                    if (((int) location2.distanceTo(location)) < this.f3012a) {
                        return true;
                    }
                }
                int i11 = 0;
                while (i11 < q3Var.f3122a.size() - i8 && i11 <= this.f3014c) {
                    Location location3 = q3Var.f3123b.get(i11);
                    int i12 = i11 + 1;
                    Location location4 = q3Var.f3123b.get(i12);
                    double longitude = location3.getLongitude();
                    double latitude = location3.getLatitude();
                    double longitude2 = location4.getLongitude();
                    double latitude2 = location4.getLatitude();
                    double longitude3 = location.getLongitude();
                    double latitude3 = location.getLatitude();
                    if (longitude == longitude2) {
                        d8 = longitude;
                    } else {
                        if (latitude == latitude2) {
                            i9 = i9;
                            d8 = longitude3;
                            i12 = i12;
                            d9 = latitude;
                        } else {
                            double d11 = (latitude - latitude2) / (longitude - longitude2);
                            double d12 = latitude - (d11 * longitude);
                            double d13 = (((longitude3 * d11) + latitude3) - d12) / (2.0d * d11);
                            latitude3 = (d11 * d13) + d12;
                            d8 = d13;
                            d9 = latitude3;
                        }
                        if (longitude >= longitude2) {
                            longitude = longitude2;
                            longitude2 = longitude;
                        }
                        if (latitude >= latitude2) {
                            latitude2 = latitude;
                            latitude = latitude2;
                        }
                        if (d8 >= longitude || d8 > longitude2 || d9 < latitude || d9 > latitude2) {
                            fDistanceTo = -1.0f;
                        } else {
                            Location location5 = new Location("M");
                            location5.setAltitude(location.getAltitude());
                            location5.setLatitude(d9);
                            location5.setLongitude(d8);
                            fDistanceTo = location.distanceTo(location5);
                        }
                        d10 = fDistanceTo;
                        if (d10 == -1.0d && d10 < this.f3012a) {
                            return true;
                        }
                        i8 = 1;
                        i11 = i12;
                        i9 = i9;
                    }
                    d9 = latitude3;
                    if (longitude >= longitude2) {
                        longitude = longitude2;
                        longitude2 = longitude;
                    }
                    if (latitude >= latitude2) {
                        latitude2 = latitude;
                        latitude = latitude2;
                    }
                    if (d8 >= longitude) {
                        fDistanceTo = -1.0f;
                    } else {
                        fDistanceTo = -1.0f;
                    }
                    d10 = fDistanceTo;
                    if (d10 == -1.0d) {
                    }
                    i8 = 1;
                    i11 = i12;
                    i9 = i9;
                }
            }
            i7 = i9;
        }
        return false;
    }

    public i3(double d8, int i7, int i8) {
        v2.e("GPS-M", "PointNearTrack:  segm=" + i7 + " pntIdx=" + i8);
        this.f3012a = d8;
        this.f3013b = i7;
        this.f3014c = i8;
    }
}
