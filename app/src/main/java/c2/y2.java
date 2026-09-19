package c2;

import android.content.Context;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class y2 implements Comparable<y2> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3261h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f3262i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f3263j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Long f3264k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Long f3265l;
    public int m;

    public y2(String str, String str2, Long l7, boolean z, long j7, int i7) {
        this.f3262i = str;
        this.f3263j = str2;
        this.f3264k = l7;
        this.f3261h = z;
        this.f3265l = Long.valueOf(j7);
        this.m = i7;
    }

    public static String d(long j7) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");
        if (j7 == 0) {
            return XmlPullParser.NO_NAMESPACE;
        }
        double d8 = j7;
        if (d8 > 1.073741824E9d) {
            return decimalFormat.format(d8 / 1.073741824E9d) + " GB";
        }
        if (d8 > 1048576.0d) {
            return decimalFormat.format(d8 / 1048576.0d) + " MB";
        }
        if (d8 > 1024.0d) {
            return decimalFormat.format(d8 / 1024.0d) + " KB";
        }
        return decimalFormat.format(j7) + " B";
    }

    public String a() {
        return this.f3263j + "/" + this.f3262i;
    }

    @Override // java.lang.Comparable
    public int compareTo(y2 y2Var) {
        y2 y2Var2 = y2Var;
        if (this.f3265l.longValue() > y2Var2.f3265l.longValue()) {
            return 1;
        }
        return this.f3265l.longValue() < y2Var2.f3265l.longValue() ? -1 : 0;
    }

    public boolean e(Context context) {
        try {
            byte[] bArr = new byte[4096];
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(MainActivity.X(context, a(), this.m)));
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    zipInputStream.close();
                    return true;
                }
                if (nextEntry.getName().endsWith("hgt")) {
                    File fileX = MainActivity.X(context, new y2("unzipped", "GpxTemp", 0L, false, 0L, this.m).a(), this.m);
                    FileOutputStream fileOutputStream = new FileOutputStream(fileX, false);
                    while (true) {
                        int i7 = zipInputStream.read(bArr, 0, 4096);
                        if (i7 == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i7);
                    }
                    zipInputStream.closeEntry();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    if (fileX.canRead()) {
                        try {
                            MainActivity.L(fileX, MainActivity.X(context, "Dems/" + nextEntry.getName(), 1));
                            fileX.delete();
                        } catch (Exception e8) {
                            v2.e("GPS-M", "Error file copy: " + e8);
                            return false;
                        }
                    } else {
                        continue;
                    }
                }
            }
        } catch (Exception e9) {
            androidx.appcompat.widget.d.c("Unzip exception: ", e9, "GPS-M");
            return false;
        }
    }
}
