package c2;

import com.giobat.AgpsTrackerPP.MainActivity;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static p f3105c = new p();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f3106d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f3107e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static double f3108f = -100000.0d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public short[][] f3109a = (short[][]) Array.newInstance((Class<?>) short.class, 1446, 727);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public InputStream f3110b;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            v2.e("GPS-M", "[#] EGM96.java - Start loading grid");
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(p.this.f3110b));
            Objects.requireNonNull(p.this);
            Objects.requireNonNull(p.this);
            int i7 = 3;
            int i8 = 3;
            for (int i9 = 0; i9 < 1038240; i9++) {
                try {
                    p.this.f3109a[i7][i8] = dataInputStream.readShort();
                    i7++;
                    Objects.requireNonNull(p.this);
                    if (i7 >= 1443) {
                        i8++;
                        Objects.requireNonNull(p.this);
                        i7 = 3;
                    }
                } catch (IOException e8) {
                    p.f3106d = false;
                    p.f3107e = false;
                    MainActivity.f3625m1.a("EGM96: " + e8);
                    return;
                }
            }
            v2.e("GPS-M", "[#] EGM96.java - File  Loaded");
            Objects.requireNonNull(p.this);
            for (int i10 = 0; i10 < 3; i10++) {
                for (int i11 = 3; i11 < 724; i11++) {
                    short[][] sArr = p.this.f3109a;
                    sArr[i10][i11] = sArr[i10 + 1440][i11];
                    int i12 = 3 + i10;
                    sArr[i12 + 1440][i11] = sArr[i12][i11];
                }
            }
            for (int i13 = 0; i13 < 3; i13++) {
                for (int i14 = 0; i14 < 1446; i14++) {
                    if (i14 > 720) {
                        p pVar = p.this;
                        short[][] sArr2 = pVar.f3109a;
                        short[] sArr3 = sArr2[i14];
                        int i15 = i14 - 720;
                        short[] sArr4 = sArr2[i15];
                        Objects.requireNonNull(pVar);
                        sArr3[i13] = sArr4[6 - i13];
                        short[][] sArr5 = p.this.f3109a;
                        sArr5[i14][3 + i13 + 721] = sArr5[i15][722 - i13];
                    } else {
                        p pVar2 = p.this;
                        short[][] sArr6 = pVar2.f3109a;
                        short[] sArr7 = sArr6[i14];
                        int i16 = i14 + 720;
                        short[] sArr8 = sArr6[i16];
                        Objects.requireNonNull(pVar2);
                        sArr7[i13] = sArr8[6 - i13];
                        short[][] sArr9 = p.this.f3109a;
                        sArr9[i14][3 + i13 + 721] = sArr9[i16][722 - i13];
                    }
                }
            }
            p.f3107e = false;
            p.f3106d = true;
            v2.e("GPS-M", "[#] EGM96.java - Grid Successfully Loaded");
            return;
        }
    }

    public void a(InputStream inputStream) {
        if (!f3106d && !f3107e) {
            f3107e = true;
            this.f3110b = inputStream;
            new Thread(new a()).start();
        } else {
            if (f3107e) {
                v2.e("GPS-M", "[#] EGM96.java - Grid is already loading, please wait");
            }
            if (f3106d) {
                v2.e("GPS-M", "[#] EGM96.java - Grid already loaded");
            }
        }
    }
}
