package c2;

import android.content.DialogInterface;
import com.giobat.AgpsTrackerPP.ActivityFileDialogGpxJpg;
import com.giobat.AgpsTrackerPP.R;

/**
 * Positive click listener for deleting a GPX file in ActivityFileDialogGpxJpg.
 */
public class d implements DialogInterface.OnClickListener {

    public final t2 fileItem;
    public final ActivityFileDialogGpxJpg activity;

    public d(ActivityFileDialogGpxJpg activity, t2 fileItem) {
        this.activity = activity;
        this.fileItem = fileItem;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        if (!this.fileItem.isDirectory) {
            if (this.fileItem.documentFile != null && !this.fileItem.documentFile.delete()) {
                this.activity.A(this.activity.getResources().getString(R.string.error_delete_file));
            }
            this.activity.x(this.activity.H);
        }
        dialogInterface.dismiss();
    }
}
