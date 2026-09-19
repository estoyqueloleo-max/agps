package c2;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Process;
import android.provider.DocumentsContract;
import androidx.documentfile.provider.DocumentFile;
import com.giobat.AgpsTrackerPP.ActivityFileDialogGpxJpg;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import java.io.OutputStream;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class i2 implements DialogInterface.OnClickListener {

    public final Uri zipUri;
    public final MainActivity mainActivity;

    public class a implements Runnable {
        public a() {
        }

        @Override
        public void run() {
            Uri uriH = null;
            Process.setThreadPriority(10);
            MainActivity.f3630r1 = true;
            MainActivity activity = i2.this.mainActivity;
            b4 b4Var = activity.K;
            Uri uri = i2.this.zipUri;
            Context context = activity.getApplicationContext();
            Objects.requireNonNull(b4Var);
            byte[] bArr = new byte[4096];
            try {
                ZipInputStream zipInputStream = new ZipInputStream(context.getContentResolver().openInputStream(uri));
                v2.e("GPS-I", "Unzipping : " + uri.getPath());
                MainActivity.f3625m1.a(context.getString(R.string.unzipping));
                DocumentFile rootDir = DocumentFile.fromTreeUri(context, MainActivity.f3624l1);
                DocumentFile photoDir = rootDir != null ? rootDir.findFile("AgpsTrackerPhoto") : null;
                b4Var.f2965a = 0L;
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        break;
                    }
                    v2.e("GPS-I", "In this ZIP: " + nextEntry.getName() + " Size=" + nextEntry.getSize());
                    String name = nextEntry.getName();
                    ContentResolver contentResolver = context.getContentResolver();
                    if (name.endsWith(".jpg") && (photoDir == null || photoDir.findFile(name) == null)) {
                        uriH = DocumentsContract.createDocument(contentResolver, ActivityFileDialogGpxJpg.z(context, MainActivity.f3624l1, "AgpsTrackerPhoto"), "application/*", name);
                    } else if (name.endsWith(".gpx")) {
                        DocumentFile gpxFile = rootDir != null ? rootDir.findFile(name) : null;
                        uriH = gpxFile == null ? (rootDir != null ? rootDir.createFile("application/*", name).getUri() : null) : gpxFile.getUri();
                        b4Var.f2966b = uriH;
                    }
                    if (uriH != null) {
                        OutputStream outputStream = context.getContentResolver().openOutputStream(uriH);
                        if (outputStream != null) {
                            while (true) {
                                int readCount = zipInputStream.read(bArr, 0, 4096);
                                if (readCount == -1) {
                                    break;
                                }
                                outputStream.write(bArr, 0, readCount);
                                b4Var.f2965a += (long) readCount;
                            }
                            outputStream.flush();
                            outputStream.close();
                        }
                    }
                    MainActivity.f3625m1.a(name + " " + context.getString(R.string.imported));
                    zipInputStream.closeEntry();
                }
                MainActivity.f3625m1.a(context.getString(R.string.unzipping_completed));
                zipInputStream.close();
            } catch (Exception e8) {
                androidx.appcompat.widget.d.c("Unzip exception: ", e8, "GPS-I");
            }
            String strS = activity.S(activity.K.f2966b);
            if (strS == null) {
                return;
            }
            v2.e("GPS-I", "-->parseTrackAndStartLoading:" + strS);
            activity.H = new r(activity.getApplicationContext());
            activity.H.d(activity.K.f2966b, "GPXfileRD_DONE", strS, 2);
            v2.e("GPS-I", "-->parseTrackAndStartLoading done");
            MainActivity.I0 = false;
            MainActivity.f3631s1 = true;
        }
    }

    public i2(MainActivity mainActivity, Uri uri) {
        this.mainActivity = mainActivity;
        this.zipUri = uri;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i7) {
        dialogInterface.dismiss();
        if (this.mainActivity.B()) {
            new Thread(new a()).start();
        }
    }
}
