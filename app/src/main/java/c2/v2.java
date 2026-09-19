package c2;

import android.util.Log;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class v2 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f3203h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static File f3204i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static BufferedWriter f3205j;

    public static void e(String str, String str2) {
        Log.i(str, str2);
        if (f3204i == null) {
            if (!f3203h) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            String str3 = new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.US).format(Long.valueOf(jCurrentTimeMillis)) + ".log";
            f3204i = MainActivity.X(MainActivity.Z0, "Logs/" + str3, 7);
        }
        try {
            if (f3205j == null && f3204i != null) {
                f3205j = new BufferedWriter(new FileWriter(f3204i));
            }
            if (f3205j != null) {
                f3205j.write(f(System.currentTimeMillis()) + ":" + str + "-" + str2 + "\n");
                f3205j.flush();
            }
        } catch (IOException e8) {
            Log.i(str, "Exception" + e8);
        }
    }

    public static String f(long j7) {
        Locale locale = Locale.US;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", locale);
        NumberFormat decimalFormat = DecimalFormat.getInstance(locale);
        ((DecimalFormat) decimalFormat).applyPattern("000");
        return simpleDateFormat.format(Long.valueOf(j7)) + "." + decimalFormat.format(j7 % 1000) + ":";
    }
}
