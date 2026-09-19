package c2;

import android.content.DialogInterface;
import android.provider.DocumentsContract;
import android.widget.EditText;
import com.giobat.AgpsTrackerPP.ActivityFileDialogGpxJpg;
import com.giobat.AgpsTrackerPP.R;
import java.io.FileNotFoundException;

public class a implements DialogInterface.OnClickListener {

    public final EditText filenameInput;
    public final String fileExtension;
    public final t2 fileItem;
    public final ActivityFileDialogGpxJpg activity;

    public a(ActivityFileDialogGpxJpg activity, EditText filenameInput, String fileExtension, t2 fileItem) {
        this.activity = activity;
        this.filenameInput = filenameInput;
        this.fileExtension = fileExtension;
        this.fileItem = fileItem;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i7) {
        try {
            DocumentsContract.renameDocument(this.activity.getContentResolver(), this.fileItem.f3183e.getUri(), ((Object) this.filenameInput.getText()) + "." + this.fileExtension);
        } catch (FileNotFoundException unused) {
            this.activity.A(this.activity.getResources().getString(R.string.error_rename_file));
        }
        this.activity.x(this.activity.H);
        dialogInterface.dismiss();
    }
}
