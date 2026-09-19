package c2;

import android.util.Log;
import androidx.documentfile.provider.DocumentFile;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.util.Iterator;
import org.mapsforge.core.model.BoundingBox;

public class h1 implements Runnable {

    public final DocumentFile[] photoFiles;
    public final BoundingBox boundingBox;
    public final MainActivity mainActivity;

    public h1(MainActivity mainActivity, DocumentFile[] photoFiles, BoundingBox boundingBox) {
        this.mainActivity = mainActivity;
        this.photoFiles = photoFiles;
        this.boundingBox = boundingBox;
    }

    @Override
    public void run() {
        Log.d("UI thread", "I am the UI thread");
        int i7 = 0;
        while (true) {
            DocumentFile[] files = this.photoFiles;
            if (i7 >= files.length) {
                return;
            }
            try {
                h3 h3Var = new h3(files[i7].getUri(), this.mainActivity.getApplicationContext());
                if (this.boundingBox.contains(h3Var.f3000b)) {
                    Iterator<h3> it = m2.f3051q.iterator();
                    boolean alreadyExists = false;
                    while (it.hasNext()) {
                        if (it.next().f3000b.equals(h3Var.f3000b)) {
                            alreadyExists = true;
                        }
                    }
                    if (!alreadyExists) {
                        m2.f3051q.add(h3Var);
                    }
                }
            } catch (Exception unused) {
            }
            i7++;
        }
    }
}
