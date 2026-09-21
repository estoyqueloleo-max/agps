package c2;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.layer.overlay.Marker;
import org.mapsforge.map.layer.overlay.Polyline;

/* JADX INFO: loaded from: classes.dex */
public class j implements Callable<String> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public org.mapsforge.core.model.LatLong f3017c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f3018d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public o f3019e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<List<u>> f3015a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<t> f3016b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f3020f = new int[16];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int[] f3021g = new int[16];

    public j(org.mapsforge.core.model.LatLong cVar, Context context, o oVar) {
        this.f3018d = context;
        this.f3017c = cVar;
        this.f3019e = oVar;
    }

    /* JADX WARN: Code duplicated, block: B:141:0x0257 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:142:0x0249 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:62:0x0193  */
    /* JADX WARN: Code duplicated, block: B:64:0x0196  */
    /* JADX WARN: Code duplicated, block: B:71:0x01b0 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:74:0x01b5  */
    public final void a(short[][] sArr, int i7, int i8, short s7) {
        char c8 = 0;
        char c9 = 0;
        int i9 = 0;
        short s8 = 0;
        int i10 = 0;
        int i11 = 0;
        short s9 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        short s10 = 0;
        int i17 = 0;
        short s11 = 0;
        short s12 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        short s13 = 2;
        short s14 = 1;
        short s15 = 0;
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, i7, i8);
        for (int i21 = 0; i21 < i8; i21++) {
            for (int i22 = 0; i22 < i7; i22++) {
                if (sArr[i22][i21] > s7) {
                    bArr[i22][i21] = 1;
                } else {
                    bArr[i22][i21] = 0;
                }
            }
        }
        int i23 = i7 - 1;
        int i24 = i8 - 1;
        short[][] sArr2 = (short[][]) Array.newInstance((Class<?>) short.class, i23, i24);
        int i25 = 0;
        while (true) {
            c8 = 3;
            if (i25 >= i24) {
                break;
            }
            int i26 = 0;
            while (i26 < i23) {
                short[] sArr3 = sArr2[i26];
                int i27 = i25 + 1;
                int i28 = (bArr[i26][i25] << 3) + (bArr[i26][i27] << 2);
                i26++;
                sArr3[i25] = (short) (i28 + (bArr[i26][i27] << 1) + bArr[i26][i25]);
            }
            i25++;
        }
        int[] iArr = this.f3020f;
        iArr[0] = 0;
        int[] iArr2 = this.f3021g;
        iArr2[0] = 0;
        iArr[1] = 3;
        char c10 = 4;
        iArr2[1] = 4;
        iArr[2] = 2;
        iArr2[2] = 3;
        iArr[3] = 2;
        iArr2[3] = 4;
        iArr[4] = 1;
        iArr2[4] = 2;
        int i29 = 5;
        iArr[5] = 5;
        iArr2[5] = 0;
        int i30 = 6;
        iArr[6] = 1;
        iArr2[6] = 3;
        iArr[7] = 1;
        iArr2[7] = 4;
        iArr[8] = 1;
        iArr2[8] = 4;
        iArr[9] = 1;
        iArr2[9] = 3;
        iArr[10] = 6;
        iArr2[10] = 0;
        iArr[11] = 1;
        iArr2[11] = 2;
        iArr[12] = 2;
        iArr2[12] = 4;
        iArr[13] = 2;
        iArr2[13] = 3;
        iArr[14] = 3;
        iArr2[14] = 4;
        short s16 = 15;
        iArr[15] = 0;
        iArr2[15] = 0;
        int outerPass = 0;
        while (outerPass++ < 10) {
            int i31 = 0;
            for (int i32 = 0; i32 < i23; i32++) {
                for (int i33 = 0; i33 < i24; i33++) {
                    if (sArr2[i32][i33] != 0 && sArr2[i32][i33] != s16) {
                            int i35 = this.f3020f[sArr2[i32][i33]];
                            int i36 = this.f3021g[sArr2[i32][i33]];
                            if (i35 == 0) {
                                sArr2[i32][i33] = 0;
                            } else {
                                int i34 = i31 + 1;
                                ArrayList arrayList = new ArrayList();
                                if (i35 == i29 || i35 == i30) {
                                    int i37 = i33;
                                    i9 = i32;
                                    s16 = 15;
                                    if (c(i32, i37, i35, sArr2, s7)) {
                                        i10 = i37;
                                        sArr2[i9][i10] = s13;
                                        i11 = 4;
                                    } else {
                                        i10 = i37;
                                        sArr2[i9][i10] = s14;
                                        i11 = s13;
                                    }
                                    i35 = s14;
                                } else {
                                    sArr2[i32][i33] = s15;
                                    i10 = i33;
                                    i9 = i32;
                                    s16 = 15;
                                    i11 = i36;
                                }
                                arrayList.add(new u(i9, i10, i35));
                                arrayList.add(new u(i9, i10, i11));
                                int i38 = i11;
                                int i39 = i10;
                                int i40 = i9;
                                short s17 = 0;
                                int traceSafety = 0;
                                while (i38 != 0 && ++traceSafety < 100000) {
                                    if (i38 != 0) {
                                        if (i38 != s14) {
                                            if (i38 != s13) {
                                                if (i38 != 3) {
                                                    if (i38 != 4) {
                                                        s9 = s17;
                                                    } else if (i39 > 0) {
                                                        i39--;
                                                        s9 = s13;
                                                    }
                                                    i12 = i40;
                                                    i13 = i39;
                                                    i14 = this.f3020f[sArr2[i12][i13]];
                                                    int i41 = this.f3021g[sArr2[i12][i13]];
                                                    if (i14 == 0) {
                                                        if (i14 == 5 && i14 != 6) {
                                                            if (s9 != i41) {
                                                                i14 = i41;
                                                            }
                                                            sArr2[i12][i13] = 0;
                                                            i20 = i14;
                                                            i16 = i13;
                                                            s10 = s9;
                                                            i17 = i10;
                                                            s11 = s13;
                                                            i15 = i12;
                                                            s12 = 1;
                                                        } else if (i12 > 0 || i12 >= sArr2.length - s13 || i13 <= 0 || i13 >= sArr2[0].length - s13) {
                                                            i33 = i10;
                                                            s13 = s13;
                                                            c9 = 4;
                                                            c8 = 3;
                                                            s8 = 1;
                                                            s15 = 0;
                                                            sArr2[i12][i13] = 0;
                                                            break;
                                                        } else {
                                                            i15 = i12;
                                                            i16 = i13;
                                                            s10 = s9;
                                                            i17 = i10;
                                                            if (!c(i12, i13, i14, sArr2, s7)) {
                                                                s11 = 2;
                                                                s12 = 1;
                                                                if (s10 == 1) {
                                                                    sArr2[i15][i16] = 1;
                                                                    i20 = s11;
                                                                } else if (s10 == 2) {
                                                                    sArr2[i15][i16] = 1;
                                                                    i20 = s12;
                                                                } else {
                                                                    i18 = 3;
                                                                    if (s10 == 3) {
                                                                        i19 = 4;
                                                                        sArr2[i15][i16] = 4;
                                                                        i20 = i19;
                                                                    } else if (s10 == 4) {
                                                                        sArr2[i15][i16] = 4;
                                                                        i20 = i18;
                                                                    } else {
                                                                        i20 = i41;
                                                                    }
                                                                }
                                                            } else if (s10 == 1) {
                                                                s11 = 2;
                                                                sArr2[i15][i16] = 2;
                                                                s12 = 1;
                                                                i19 = 4;
                                                                i20 = i19;
                                                            } else {
                                                                s11 = 2;
                                                                if (s10 == 2) {
                                                                    sArr2[i15][i16] = 8;
                                                                    s12 = 1;
                                                                    i18 = 3;
                                                                    i20 = i18;
                                                                } else if (s10 == 3) {
                                                                    sArr2[i15][i16] = 8;
                                                                    s12 = 1;
                                                                    i20 = s11;
                                                                } else if (s10 == 4) {
                                                                    sArr2[i15][i16] = 2;
                                                                    s12 = 1;
                                                                    i20 = s12;
                                                                } else {
                                                                    s12 = 1;
                                                                    i20 = i41;
                                                                }
                                                            }
                                                        }
                                                        arrayList.add(new u(i15, i16, i20));
                                                        i40 = i15;
                                                        i10 = i17;
                                                        s13 = s11;
                                                        i38 = i20;
                                                        s17 = s10;
                                                        int i42 = i16;
                                                        s14 = s12;
                                                        i39 = i42;
                                                    }
                                                } else if (i40 < i23 - 1) {
                                                    i40++;
                                                    s9 = s14;
                                                    i12 = i40;
                                                    i13 = i39;
                                                    i14 = this.f3020f[sArr2[i12][i13]];
                                                    int i43 = this.f3021g[sArr2[i12][i13]];
                                                    if (i14 == 0) {
                                                        i33 = i10;
                                                        s13 = s13;
                                                        c9 = 4;
                                                        c8 = 3;
                                                        s8 = 1;
                                                        s15 = 0;
                                                        sArr2[i12][i13] = 0;
                                                        break;
                                                    }
                                                }
                                            } else if (i39 < i24 - 1) {
                                                i39++;
                                                s9 = 4;
                                                i12 = i40;
                                                i13 = i39;
                                                i14 = this.f3020f[sArr2[i12][i13]];
                                                int i44 = this.f3021g[sArr2[i12][i13]];
                                                if (i14 == 0) {
                                                    i33 = i10;
                                                    s13 = s13;
                                                    c9 = 4;
                                                    c8 = 3;
                                                    s8 = 1;
                                                    s15 = 0;
                                                    sArr2[i12][i13] = 0;
                                                    break;
                                                }
                                            }
                                        } else if (i40 > 0) {
                                            i40--;
                                            s9 = 3;
                                            i12 = i40;
                                            i13 = i39;
                                            i14 = this.f3020f[sArr2[i12][i13]];
                                            int i45 = this.f3021g[sArr2[i12][i13]];
                                            if (i14 == 0) {
                                                i33 = i10;
                                                s13 = s13;
                                                c9 = 4;
                                                c8 = 3;
                                                s8 = 1;
                                                s15 = 0;
                                                sArr2[i12][i13] = 0;
                                                break;
                                            }
                                        }
                                    }
                                    i33 = i10;
                                    s13 = s13;
                                    s8 = s14;
                                    c9 = 4;
                                    c8 = 3;
                                    s15 = 0;
                                }
                                this.f3015a.add(arrayList);
                                i31 = i34;
                            }
                        }
                    }
                }
            short s18 = s16;
            short s19 = s13;
            short s20 = s14;
            short s21 = s15;
            char c11 = c8;
            char c12 = c10;
            if (i31 <= 0) {
                return;
            }
            s13 = s19;
            s14 = s20;
            c10 = c12;
            c8 = c11;
            s15 = s21;
            s16 = s18;
            i30 = 6;
            i29 = 5;
        }
    }

    public final org.mapsforge.core.model.LatLong b(u uVar, short[][] sArr, org.mapsforge.core.model.BoundingBox aVar, int i7) {
        double dAbs = 0.0d;
        double d8 = 0.0d;
        double d9 = 0.0d;
        double dAbs2 = 0.0d;
        int i8 = uVar.f3192c;
        double d10 = 0.0d;
        if (i8 != 1) {
            if (i8 == 2) {
                double d11 = aVar.minLongitude;
                int i9 = uVar.f3191b + 1;
                o oVar = this.f3019e;
                double d12 = d11 + (((double) i9) * oVar.f3089e);
                int i10 = uVar.f3190a;
                short s7 = sArr[i10][i9];
                short s8 = sArr[i10 + 1][i9];
                double d13 = oVar.f3090f;
                double denom = (double) (s8 - s7);
                double d14 = (denom == 0.0d) ? (0.5d * d13) : ((((double) (i7 - s7)) * d13) / denom);
                d8 = aVar.maxLatitude - (((double) i10) * d13);
                d9 = d12;
                dAbs2 = Math.abs(d14);
            } else if (i8 == 3) {
                double d15 = aVar.maxLatitude;
                int i11 = uVar.f3190a + 1;
                o oVar2 = this.f3019e;
                d10 = d15 - (((double) i11) * oVar2.f3090f);
                short[] sArr2 = sArr[i11];
                int i12 = uVar.f3191b;
                short s9 = sArr2[i12];
                short s10 = sArr[i11][i12 + 1];
                double d16 = oVar2.f3089e;
                double denom = (double) (s10 - s9);
                double frac = (denom == 0.0d) ? (0.5d * d16) : Math.abs((((double) (i7 - s9)) * d16) / denom);
                dAbs = frac + (((double) i12) * d16) + aVar.minLongitude;
            } else if (i8 != 4) {
                dAbs = 0.0d;
            } else {
                double d17 = aVar.minLongitude;
                int i13 = uVar.f3191b;
                o oVar3 = this.f3019e;
                d9 = d17 + (((double) i13) * oVar3.f3089e);
                int i14 = uVar.f3190a;
                short s11 = sArr[i14][i13];
                short s12 = sArr[i14 + 1][i13];
                double d18 = oVar3.f3090f;
                double denom = (double) (s12 - s11);
                double d19 = (denom == 0.0d) ? (0.5d * d18) : ((((double) (i7 - s11)) * d18) / denom);
                d8 = aVar.maxLatitude - (((double) i14) * d18);
                dAbs2 = Math.abs(d19);
            }
            if (i8 == 2 || i8 == 4) {
                d10 = d8 - dAbs2;
                dAbs = d9;
            }
        } else {
            double d20 = aVar.maxLatitude;
            int i15 = uVar.f3190a;
            o oVar4 = this.f3019e;
            double d21 = d20 - (((double) i15) * oVar4.f3090f);
            short[] sArr3 = sArr[i15];
            int i16 = uVar.f3191b;
            short s13 = sArr3[i16];
            short s14 = sArr[i15][i16 + 1];
            double d22 = oVar4.f3089e;
            double denom = (double) (s14 - s13);
            double frac = (denom == 0.0d) ? (0.5d * d22) : Math.abs((((double) (i7 - s13)) * d22) / denom);
            dAbs = frac + (((double) i16) * d22) + aVar.minLongitude;
            d10 = d21;
        }
        if (Double.isNaN(d10) || Double.isInfinite(d10)) {
            d10 = aVar.minLatitude;
        }
        if (Double.isNaN(dAbs) || Double.isInfinite(dAbs)) {
            dAbs = aVar.minLongitude;
        }
        d10 = Math.max(-90.0d, Math.min(90.0d, d10));
        dAbs = Math.max(-180.0d, Math.min(180.0d, dAbs));
        return new org.mapsforge.core.model.LatLong(d10, dAbs);
    }

    public final boolean c(int i7, int i8, int i9, short[][] sArr, int i10) {
        int i11 = i7 + 1;
        int i12 = sArr[i7][i8] + sArr[i11][i8];
        int i13 = i8 + 1;
        int i14 = ((i12 + sArr[i7][i13]) + sArr[i11][i13]) / 4;
        if (i9 == 5) {
            return i14 > i10;
        }
        return i9 == 6 && i14 <= i10;
    }

    @Override // java.util.concurrent.Callable
    public String call() {
        v2.e("GPS-M", "Contour Task started");
        org.mapsforge.core.model.BoundingBox aVarB = o.b(this.f3017c);
        o oVar = this.f3019e;
        Objects.requireNonNull(oVar);
        double d8 = aVarB.minLatitude;
        double d9 = aVarB.minLongitude;
        org.mapsforge.core.model.BoundingBox aVar = oVar.f3096l;
        double d10 = aVar.minLatitude;
        if (d10 > d8) {
            d8 = d10;
        }
        double d11 = aVar.minLongitude;
        if (d11 > d9) {
            d9 = d11;
        }
        org.mapsforge.core.model.LatLong cVar = new org.mapsforge.core.model.LatLong((((double) ((int) ((d8 % 1.0d) * 1200.0d))) * 8.333333333333334E-4d) + ((double) ((int) d8)), (((double) ((int) ((d9 % 1.0d) * 1200.0d))) * 8.333333333333334E-4d) + ((double) ((int) d9)));
        double d12 = aVarB.maxLatitude;
        double d13 = aVarB.maxLongitude;
        org.mapsforge.core.model.BoundingBox aVar2 = oVar.f3096l;
        double d14 = aVar2.maxLatitude;
        if (d14 < d12) {
            d12 = d14;
        }
        double d15 = aVar2.maxLongitude;
        if (d15 < d13) {
            d13 = d15;
        }
        org.mapsforge.core.model.LatLong cVar2 = new org.mapsforge.core.model.LatLong((((double) ((int) ((d12 % 1.0d) * 1200.0d))) * 8.333333333333334E-4d) + ((double) ((int) d12)), (((double) ((int) ((d13 % 1.0d) * 1200.0d))) * 8.333333333333334E-4d) + ((double) ((int) d13)));
        org.mapsforge.core.model.BoundingBox aVar3 = new org.mapsforge.core.model.BoundingBox(cVar.latitude, cVar.longitude, cVar2.latitude, cVar2.longitude);
        o.d(aVar3, 1);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(MainActivity.X(this.f3018d, "Dems/" + this.f3019e.f3086b, 1), "r");
            o oVar2 = this.f3019e;
            int iFloor = (int) Math.floor((oVar2.f3096l.maxLatitude - aVar3.maxLatitude) / oVar2.f3090f);
            int iFloor2 = ((int) Math.floor((aVar3.maxLatitude - aVar3.minLatitude) / this.f3019e.f3090f)) + 1;
            double d16 = aVar3.minLongitude;
            o oVar3 = this.f3019e;
            int iFloor3 = (int) Math.floor((d16 - oVar3.f3096l.minLongitude) / oVar3.f3089e);
            int iFloor4 = ((int) Math.floor((aVar3.maxLongitude - aVar3.minLongitude) / this.f3019e.f3089e)) + 1;
            short[][] sArr = (short[][]) Array.newInstance((Class<?>) short.class, iFloor2, iFloor4);
            short s7 = 10000;
            short s8 = -10000;
            boolean zMatches = this.f3019e.f3085a.matches("I");
            int i7 = 0;
            int i8 = iFloor;
            while (i8 < iFloor + iFloor2) {
                short[][] sArr2 = sArr;
                randomAccessFile.seek((iFloor3 * 2) + (this.f3019e.f3095k * i8));
                for (int i9 = 0; i9 < iFloor4; i9++) {
                    short sReverseBytes = randomAccessFile.readShort();
                    if (zMatches) {
                        sReverseBytes = Short.reverseBytes(sReverseBytes);
                    }
                    sArr2[i7][i9] = sReverseBytes;
                    if (sReverseBytes != this.f3019e.m) {
                        if (sReverseBytes < s7) {
                            s7 = sReverseBytes;
                        } else if (sReverseBytes > s8) {
                            s8 = sReverseBytes;
                        }
                    }
                }
                i7++;
                i8++;
                sArr = sArr2;
            }
            short[][] sArr3 = sArr;
            v2.e("GPS-M", "DEM  File  Loaded:" + MainActivity.b0(this.f3019e.f3086b));
            int i10 = (s7 - (s7 % 20)) + (-20);
            int i11 = (s8 - (s8 % 20)) + 20;
            this.f3016b.clear();
            while (i11 >= i10) {
                this.f3015a.clear();
                short[][] sArr4 = sArr3;
                a(sArr4, iFloor2, iFloor4, (short) i11);
                this.f3016b.clear();
                for (List<u> list : this.f3015a) {
                    t tVar = new t(i11);
                    Iterator<u> it = list.iterator();
                    while (it.hasNext()) {
                        tVar.f3171a.add(b(it.next(), sArr4, aVar3, i11));
                    }
                    this.f3016b.add(tVar);
                }
                if (i11 == i10) {
                    e(this.f3019e.f3086b, i11);
                } else {
                    d(this.f3019e.f3086b, i11);
                }
                i11 -= 20;
                sArr3 = sArr4;
            }
            randomAccessFile.close();
            u2 u2Var = MainActivity.f3626n1;
            Objects.requireNonNull(u2Var);
            // Acquire semaphore only to protect any Layers list mutation (none here),
            // then release BEFORE calling redrawLayers() to avoid deadlock with the
            // UI thread which also waits on this semaphore.
            try {
                u2Var.f3196b.acquire();
            } catch (Exception unused) {
            }
            u2Var.f3196b.release();
            try {
                MainActivity.M0.getLayerManager().redrawLayers();
            } catch (Exception unused2) {
            }
        } catch (IOException e8) {
            if (MainActivity.f3625m1 != null) {
                MainActivity.f3625m1.a("showWindowIsolines:" + e8);
            }
        }
        o.c(15);
        v2.e("GPS-M", "Contour Task ended");
        return "OK";
    }

    public final void d(String demName, int elevation) {
        org.mapsforge.core.graphics.Paint minorContourPaint = n2.a(Color.argb(180, 140, 85, 45), 2, 2);
        org.mapsforge.core.graphics.Paint majorContourPaint = n2.a(Color.argb(230, 80, 40, 20), 3, 2);
        for (int i = 0; i < this.f3016b.size(); i++) {
            t contour = this.f3016b.get(i);
            boolean isMajor = (contour.f3172b % 100 == 0);
            Polyline polyline = new Polyline(isMajor ? majorContourPaint : minorContourPaint, AndroidGraphicFactory.INSTANCE);
            polyline.setPoints(contour.f3171a);
            o.demOverlayLayers.add(polyline);
            MainActivity.f3626n1.a(polyline);

            if (isMajor) {
                int pointCount = contour.f3171a.size();
                int pointIdx = 0;
                double accumulatedDistance = 0.0d;
                while (pointIdx < pointCount - 1) {
                    LatLong pt1 = contour.f3171a.get(pointIdx);
                    int nextIdx = pointIdx + 1;
                    LatLong pt2 = contour.f3171a.get(nextIdx);
                    Objects.requireNonNull(pt1);
                    double dLat = Math.toRadians(pt2.latitude - pt1.latitude);
                    double dLon = Math.toRadians(pt2.longitude - pt1.longitude);
                    double sinHalfLat = Math.sin(dLat / 2.0d);
                    double sinHalfLon = Math.sin(dLon / 2.0d);
                    double a = (sinHalfLat * sinHalfLat) + (sinHalfLon * sinHalfLon * Math.cos(Math.toRadians(pt1.latitude)) * Math.cos(Math.toRadians(pt2.latitude)));
                    accumulatedDistance += Math.atan2(Math.sqrt(a), Math.sqrt(1.0d - a)) * 2.0d * 6378137.0d;
                    if (accumulatedDistance > 1000.0d) {
                        LatLong labelPosition = contour.f3171a.get(pointIdx);
                        int altValue = (int) contour.f3172b;
                        Context context = this.f3018d;
                        String altText = Integer.toString(altValue);
                        TextView textView = new TextView(context);
                        textView.setGravity(17);
                        textView.setTextSize(9.0f);
                        textView.setTypeface(textView.getTypeface(), 1);
                        textView.setTextColor(Color.rgb(80, 40, 20));
                        textView.setText(altText);
                        Bitmap labelBitmap = n2.b(context, textView);
                        Marker altMarker = new Marker(labelPosition, labelBitmap, 0, (-labelBitmap.getHeight()) / 2);
                        altMarker.setVisible(false);
                        altMarker.requestRedraw();
                        AgpsApplication.f3580w.add(altMarker);
                        MainActivity.f3626n1.a(altMarker);
                        accumulatedDistance = 0.0d;
                    }
                    pointIdx = nextIdx;
                }
            }
        }
    }

    public final void e(String demName, int elevation) {
        org.mapsforge.core.graphics.Paint contourPaint = n2.a(Color.argb(200, 110, 65, 30), 2, 2);
        for (int i = 0; i < this.f3016b.size(); i++) {
            Polyline polyline = new Polyline(contourPaint, AndroidGraphicFactory.INSTANCE);
            polyline.setPoints(this.f3016b.get(i).f3171a);
            o.demOverlayLayers.add(polyline);
            MainActivity.f3626n1.a(polyline);
        }
        StringBuilder sbA = android.support.v4.media.b.a("FINE CONTOUR ------------------>   dem Name=");
        boolean z = MainActivity.I0;
        String[] strArrSplit = demName.split("/");
        sbA.append(strArrSplit[strArrSplit.length - 1]);
        sbA.append(" Elevation=");
        sbA.append(elevation);
        v2.e("GPS-M", sbA.toString());
    }
}
