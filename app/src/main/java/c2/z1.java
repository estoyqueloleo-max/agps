package c2;

import android.content.Intent;
import android.content.UriPermission;
import android.widget.Toast;
import androidx.documentfile.provider.DocumentFile;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import java.util.Iterator;

public class z1 implements androidx.activity.result.b<androidx.activity.result.a> {

    public final MainActivity mainActivity;

    public z1(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void a(androidx.activity.result.a result) {
        Intent intent = result.f289i;
        if (result.f288h != -1) {
            Toast toast = Toast.makeText(this.mainActivity.getApplicationContext(), this.mainActivity.getApplicationContext().getString(R.string.something_wrong_gpx_dir), Toast.LENGTH_LONG);
            toast.setGravity(17, 0, 0);
            toast.show();
            return;
        }
        if (intent != null) {
            Iterator<UriPermission> it = this.mainActivity.getContentResolver().getPersistedUriPermissions().iterator();
            while (it.hasNext()) {
                this.mainActivity.getContentResolver().releasePersistableUriPermission(it.next().getUri(), 3);
            }
            MainActivity.f3624l1 = intent.getData();
            try {
                this.mainActivity.getContentResolver().takePersistableUriPermission(MainActivity.f3624l1, 3);
                DocumentFile rootDir = DocumentFile.fromTreeUri(this.mainActivity.getApplicationContext(), MainActivity.f3624l1);
                if (rootDir != null && rootDir.findFile("AgpsTrackerPhoto") == null) {
                    rootDir.createDirectory("AgpsTrackerPhoto");
                    v2.e("GPS-M", "AgpsTrackerPhoto directory created");
                }
                this.mainActivity.L = false;
            } catch (Exception unused) {
            }
        }
    }
}
