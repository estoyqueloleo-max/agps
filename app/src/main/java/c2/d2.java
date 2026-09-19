package c2;

import android.content.Intent;
import android.net.Uri;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.documentfile.provider.DocumentFile;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;

/**
 * ActivityResultCallback for importing a GPX or ZIP file in MainActivity.
 */
public class d2 implements androidx.activity.result.b<androidx.activity.result.a>, ActivityResultCallback<ActivityResult> {

    public final MainActivity mainActivity;

    public d2(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private void handleImportUri(Uri data) {
        if (data == null) {
            return;
        }
        String fileName = this.mainActivity.S(data);
        if (fileName == null) {
            return;
        }
        DocumentFile gpxDir = DocumentFile.fromTreeUri(this.mainActivity.getApplicationContext(), MainActivity.f3624l1);
        if (!fileName.endsWith(".gpx")) {
            if (fileName.endsWith(".zip")) {
                s3 s3Var = MainActivity.f3625m1;
                s3Var.a(fileName + " " + this.mainActivity.getApplication().getString(R.string.unzipping));
                new Thread(new c2(this, data)).start();
                return;
            }
            this.mainActivity.w0(this.mainActivity.getApplication().getString(R.string.unzip_gpx_title_error) + ":" + this.mainActivity.getApplication().getString(R.string.unzip_gpx_msg_error));
            return;
        }
        DocumentFile existingFile = this.mainActivity.i0(fileName, MainActivity.f3624l1);
        if (existingFile != null) {
            this.mainActivity.w0(this.mainActivity.getApplication().getString(R.string.already_exists_not_imported));
            this.mainActivity.n0(existingFile.getUri(), "GPXfileRD_DONE", 2);
        } else {
            try {
                DocumentFile importedFile = gpxDir.createFile("*/*", fileName);
                this.mainActivity.D(data, importedFile);
                this.mainActivity.w0(this.mainActivity.getApplication().getString(R.string.file) + " " + fileName + " " + this.mainActivity.getApplication().getString(R.string.imported));
                this.mainActivity.n0(importedFile.getUri(), "GPXfileRD_DONE", 2);
            } catch (Exception e8) {
                this.mainActivity.w0(this.mainActivity.getApplication().getString(R.string.error) + ":" + e8);
            }
        }
        this.mainActivity.invalidateOptionsMenu();
    }

    @Override
    public void a(androidx.activity.result.a result) {
        if (result == null || result.f288h != -1 || result.f289i == null) {
            return;
        }
        handleImportUri(result.f289i.getData());
    }

    @Override
    public void onActivityResult(ActivityResult result) {
        if (result == null || result.getResultCode() != -1 || result.getData() == null) {
            return;
        }
        handleImportUri(result.getData().getData());
    }
}
