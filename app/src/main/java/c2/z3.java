package c2;

import android.content.Context;
import android.os.Process;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import com.giobat.AgpsTrackerPP.UnzipProgressState;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Background worker for extracting downloaded zip files (maps or DEMs).
 */
public class z3 implements Runnable {

    public final File zipFile;
    public final Context context;
    public final String targetFileExtension;

    public z3(File zipFile, Context context, String targetFileExtension) {
        this.zipFile = zipFile;
        this.context = context;
        this.targetFileExtension = targetFileExtension;
    }

    @Override
    public void run() {
        Process.setThreadPriority(10);
        byte[] buffer = new byte[4096];
        try {
            UnzipProgressState.isUnzipping = true;
            UnzipProgressState.cancelUnzip = false;
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(this.zipFile));
            v2.e("GPS-I", "Unzipping : " + this.zipFile.getName());
            MainActivity.f3625m1.a(this.context.getString(R.string.unzipping));
            UnzipProgressState.unzipProgressBytes = 0L;
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    break;
                }
                v2.e("GPS-I", "In this ZIP: " + nextEntry.getName() + " Size=" + nextEntry.getSize());
                if (nextEntry.getName().endsWith(this.targetFileExtension)) {
                    File file = new File(this.zipFile.getParent() + "/" + nextEntry.getName());
                    FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                    while (true) {
                        int bytesRead = zipInputStream.read(buffer, 0, 4096);
                        if (bytesRead == -1) {
                            break;
                        }
                        fileOutputStream.write(buffer, 0, bytesRead);
                        UnzipProgressState.unzipProgressBytes += (long) bytesRead;
                        if (UnzipProgressState.cancelUnzip) {
                            fileOutputStream.close();
                            file.delete();
                            MainActivity.f3625m1.a(this.context.getString(R.string.unzipping_aborted));
                            break;
                        }
                    }
                    zipInputStream.closeEntry();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    MainActivity.f3625m1.a(this.context.getString(R.string.unzipping_completed));
                    if (file.canRead()) {
                        try {
                            this.zipFile.delete();
                        } catch (Exception e8) {
                            v2.e("GPS-I", "Error file copy: " + e8);
                        }
                    }
                }
            }
            zipInputStream.close();
        } catch (Exception e9) {
            androidx.appcompat.widget.d.c("Unzip exception: ", e9, "GPS-I");
        }
        UnzipProgressState.isUnzipping = false;
    }
}
