package c2;

import android.content.DialogInterface;
import android.net.Uri;
import com.giobat.AgpsTrackerPP.ActivityFileDialogGpxJpg;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;

/**
 * Positive click listener for deleting a JPG photo file in ActivityFileDialogGpxJpg.
 */
public class f implements DialogInterface.OnClickListener {

    public final t2 fileItem;
    public final ActivityFileDialogGpxJpg activity;

    public f(ActivityFileDialogGpxJpg activity, t2 fileItem) {
        this.activity = activity;
        this.fileItem = fileItem;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        if (this.fileItem.documentFile != null && !this.fileItem.documentFile.delete()) {
            this.activity.A(this.activity.getResources().getString(R.string.error_delete_file) + ":" + this.fileItem.name);
        }
        Uri uri = MainActivity.f3624l1;
        this.activity.x(uri);
        dialogInterface.dismiss();
    }
}
