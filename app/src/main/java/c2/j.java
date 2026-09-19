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
        while (true) {
            int i31 = s15;
            int i32 = i31;
            while (i32 < i23) {
                int i33 = s15;
                while (i33 < i24) {
                    if (sArr2[i32][i33] != 0) {
                        if (sArr2[i32][i33] != s16) {
                            int i34 = i31 + 1;
                            ArrayList arrayList = new ArrayList();
                            int i35 = this.f3020f[sArr2[i32][i33]];
                            int i36 = this.f3021g[sArr2[i32][i33]];
                            if (i35 == 0) {
                                arrayList = arrayList;
                                i33 = i33;
                                s13 = s13;
                                s15 = s15;
                                c9 = 4;
                                c8 = 3;
                                s16 = 15;
                                i9 = i32;
                                s8 = s14;
                            } else {
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
                                while (true) {
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
                                                        if (i14 == 5) {
                                                        }
                                                        if (i12 > 0) {
                                                        }
                                                        i33 = i10;
                                                        s13 = s13;
                                                        c9 = 4;
                                                        c8 = 3;
                                                        s8 = 1;
                                                        s15 = 0;
                                                        sArr2[i12][i13] = 0;
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
                                                    if (i14 == 5) {
                                                    }
                                                    if (i12 > 0) {
                                                    }
                                                    i33 = i10;
                                                    s13 = s13;
                                                    c9 = 4;
                                                    c8 = 3;
                                                    s8 = 1;
                                                    s15 = 0;
                                                    sArr2[i12][i13] = 0;
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
                                                if (i14 == 5) {
                                                }
                                                if (i12 > 0) {
                                                }
                                                i33 = i10;
                                                s13 = s13;
                                                c9 = 4;
                                                c8 = 3;
                                                s8 = 1;
                                                s15 = 0;
                                                sArr2[i12][i13] = 0;
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
                            }
                            this.f3015a.add(arrayList);
                            i31 = i34;
                        } else {
                            c9 = 4;
                        }
                        s14 = s8;
                        c10 = c9;
                        c8 = c8;
                        i32 = i9;
                        s16 = s16;
                        i30 = 6;
                        s15 = s15;
                        i29 = 5;
                        s13 = s13;
                        i33++;
                    } else {
                        c9 = c10;
                    }
                    i9 = i32;
                    s8 = s14;
                    s14 = s8;
                    c10 = c9;
                    c8 = c8;
                    i32 = i9;
                    s16 = s16;
                    i30 = 6;
                    s15 = s15;
                    i29 = 5;
                    s13 = s13;
                    i33++;
                }
                s15 = s15;
                i32++;
                s16 = s16;
                i30 = 6;
                i29 = 5;
                s13 = s13;
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
                double d14 = (((double) (i7 - s7)) * d13) / ((double) (s8 - s7));
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
                dAbs = Math.abs((((double) (i7 - s9)) * d16) / ((double) (s10 - s9))) + (((double) i12) * d16) + aVar.minLongitude;
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
                double d19 = (((double) (i7 - s11)) * d18) / ((double) (s12 - s11));
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
            dAbs = Math.abs((((double) (i7 - s13)) * d22) / ((double) (s14 - s13))) + (((double) i16) * d22) + aVar.minLongitude;
            d10 = d21;
        }
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
            MainActivity.f3625m1.a("showWindowIsolines:" + e8);
        }
        o.c(15);
        v2.e("GPS-M", "Contour Task ended");
        return "OK";
    }

    public final void d(String str, int i7) {
        org.mapsforge.map.layer.overlay.Polyline dVar;
        boolean z;
        int i8;
        char c8;
        boolean z7;
        boolean z8 = true;
        char c9 = 2;
        org.mapsforge.core.graphics.Paint lVarA = n2.a(Color.argb(50, 50, 50, 50), 1, 2);
        org.mapsforge.core.graphics.Paint lVarA2 = n2.a(Color.argb(100, 20, 20, 20), 2, 2);
        int i9 = 0;
        while (i9 < this.f3016b.size()) {
            if (this.f3016b.get(i9).f3172b % 100 == 0) {
                dVar = new org.mapsforge.map.layer.overlay.Polyline(lVarA2, org.mapsforge.map.android.graphics.AndroidGraphicFactory.INSTANCE);
                z = z8;
            } else {
                dVar = new org.mapsforge.map.layer.overlay.Polyline(lVarA, org.mapsforge.map.android.graphics.AndroidGraphicFactory.INSTANCE);
                z = false;
            }
            dVar.getLatLongs().clear();
            for (int i10 = 0; i10 < this.f3016b.get(i9).f3171a.size(); i10++) {
                dVar.getLatLongs().add(this.f3016b.get(i9).f3171a.get(i10));
            }
            if (z) {
                int size = this.f3016b.get(i9).f3171a.size();
                int i11 = 0;
                double dAtan2 = 0.0d;
                while (i11 < size - 1) {
                    org.mapsforge.core.model.LatLong cVar3 = this.f3016b.get(i9).f3171a.get(i11);
                    int i12 = i11 + 1;
                    org.mapsforge.core.model.LatLong cVar4 = this.f3016b.get(i9).f3171a.get(i12);
                    Objects.requireNonNull(cVar3);
                    int i13 = i9;
                    double radians = Math.toRadians(cVar4.latitude - cVar3.latitude);
                    double radians2 = Math.toRadians(cVar4.longitude - cVar3.longitude);
                    double d8 = radians / 2.0d;
                    double dSin = Math.sin(d8) * Math.sin(d8);
                    double dCos = Math.cos(Math.toRadians(cVar3.latitude));
                    org.mapsforge.core.graphics.Paint lVar = lVarA2;
                    org.mapsforge.core.graphics.Paint lVar2 = lVarA;
                    double d9 = radians2 / 2.0d;
                    double dSin2 = (Math.sin(d9) * Math.sin(d9) * Math.cos(Math.toRadians(cVar4.latitude)) * dCos) + dSin;
                    dAtan2 += Math.atan2(Math.sqrt(dSin2), Math.sqrt(1.0d - dSin2)) * 2.0d * 6378137.0d;
                    if (dAtan2 > 1000.0d) {
                        i8 = i13;
                        org.mapsforge.core.model.LatLong cVar6 = this.f3016b.get(i8).f3171a.get(i11);
                        int i14 = (int) this.f3016b.get(i8).f3172b;
                        Context context = this.f3018d;
                        List<n> list = o.f3084p;
                        String string = Integer.toString(i14);
                        TextView textView = new TextView(context);
                        textView.setGravity(17);
                        textView.setTextSize(8.0f);
                        z7 = true;
                        textView.setTypeface(textView.getTypeface(), 1);
                        textView.setText(string);
                        org.mapsforge.core.graphics.Bitmap bVarB = n2.b(context, textView);
                        c8 = 2;
                        org.mapsforge.map.layer.overlay.Marker bVar = new org.mapsforge.map.layer.overlay.Marker(cVar6, bVarB, 0, (-bVarB.getHeight()) / 2);
                        bVar.setVisible(false);
                        bVar.requestRedraw();
                        AgpsApplication.f3580w.add(bVar);
                        MainActivity.f3626n1.a(bVar);
                        dAtan2 = 0.0d;
                    } else {
                        i8 = i13;
                        c8 = 2;
                        z7 = true;
                    }
                    c9 = c8;
                    lVarA = lVar2;
                    z8 = z7;
                    i11 = i12;
                    lVarA2 = lVar;
                    i9 = i8;
                }
            }
            MainActivity.f3626n1.a(dVar);
            c9 = c9;
            lVarA = lVarA;
            z8 = z8;
            i9++;
            lVarA2 = lVarA2;
        }
    }

    public final void e(String str, int i7) {
        org.mapsforge.core.graphics.Paint lVarA = n2.a(Color.argb(100, 235, 235, 235), 1, 1);
        for (int i8 = 0; i8 < this.f3016b.size(); i8++) {
            org.mapsforge.map.layer.overlay.Polyline dVar = new org.mapsforge.map.layer.overlay.Polyline(lVarA, org.mapsforge.map.android.graphics.AndroidGraphicFactory.INSTANCE);
            dVar.getLatLongs().clear();
            dVar.getLatLongs().addAll(this.f3016b.get(i8).f3171a);
            MainActivity.f3626n1.a(dVar);
        }
        StringBuilder sbA = android.support.v4.media.b.a("FINE CONTOUR ------------------>   dem Name=");
        boolean z = MainActivity.I0;
        String[] strArrSplit = str.split("/");
        sbA.append(strArrSplit[strArrSplit.length - 1]);
        sbA.append(" Elevation=");
        sbA.append(i7);
        v2.e("GPS-M", sbA.toString());
    }
}
