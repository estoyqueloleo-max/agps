package c2;

import android.content.Intent;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.documentfile.provider.DocumentFile;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.P2ActivitySettingStorage;
import java.util.Objects;

/**
 * ActivityResultCallback for storage tree URI picker in P2ActivitySettingStorage.
 */
public class f3 implements ActivityResultCallback<ActivityResult> {

    public final P2ActivitySettingStorage settingsStorageActivity;

    public f3(P2ActivitySettingStorage activity) {
        this.settingsStorageActivity = activity;
    }

    @Override
    public void onActivityResult(ActivityResult result) {
        if (result == null) {
            return;
        }
        Intent intent = result.getData();
        int resultCode = result.getResultCode();
        if (intent == null) {
            return;
        }
        Objects.requireNonNull(this.settingsStorageActivity);
        v2.e("GPS-M", "onActivityResult...........RequestCode: SS_OPEN_GPX_DIR_REQUEST");
        if (resultCode != -1) {
            return;
        }
        MainActivity.f3624l1 = intent.getData();
        this.settingsStorageActivity.getContentResolver().takePersistableUriPermission(MainActivity.f3624l1, 3);
        if (this.settingsStorageActivity.w(MainActivity.f3624l1)) {
            DocumentFile rootDir = DocumentFile.fromTreeUri(this.settingsStorageActivity, MainActivity.f3624l1);
            if (rootDir != null && rootDir.findFile("AgpsTrackerPhoto") == null) {
                rootDir.createDirectory("AgpsTrackerPhoto");
                Log.i("GPS-M", "AgpsTrackerPhoto directory created");
            }
        }
    }
}
