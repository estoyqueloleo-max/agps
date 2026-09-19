package c2;

import android.content.DialogInterface;
import androidx.documentfile.provider.DocumentFile;
import com.giobat.AgpsTrackerPP.MainActivity;

public class y0 implements DialogInterface.OnClickListener {

    public final String trackName;
    public final DocumentFile existingFile;
    public final DocumentFile targetDir;
    public final String targetFileName;
    public final x3 trackData;
    public final MainActivity mainActivity;

    public y0(MainActivity activity, String trackName, DocumentFile existingFile, DocumentFile targetDir, String targetFileName, x3 trackData) {
        this.mainActivity = activity;
        this.trackName = trackName;
        this.existingFile = existingFile;
        this.targetDir = targetDir;
        this.targetFileName = targetFileName;
        this.trackData = trackData;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i7) {
        v2.e("GPS-M", "Save existing track Start: " + this.trackName);
        if (this.existingFile != null) {
            this.existingFile.delete();
        }
        DocumentFile newFile = this.targetDir.createFile("application/*", this.targetFileName);
        mainActivity.J0(newFile, this.trackName, this.trackData);
        MainActivity.f3630r1 = true;
        dialogInterface.dismiss();
    }
}
