package c2;

import android.net.Uri;
import android.os.Process;
import com.giobat.AgpsTrackerPP.MainActivity;

/**
 * Background worker to unzip GPX files when imported via d2 ActivityResultCallback.
 */
public class c2 implements Runnable {

    public final d2 callback;
    public final Uri fileUri;

    public c2(d2 callback, Uri fileUri) {
        this.callback = callback;
        this.fileUri = fileUri;
    }

    @Override
    public void run() {
        MainActivity.f3630r1 = true;
        Process.setThreadPriority(10);
        this.callback.mainActivity.C0(this.fileUri);
        MainActivity.f3631s1 = true;
    }
}
