package c2;

import android.content.DialogInterface;
import com.giobat.AgpsTrackerPP.MainActivity;

public class DialogDismissListener implements DialogInterface.OnClickListener {
    public DialogDismissListener(MainActivity mainActivity) {
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i7) {
        dialogInterface.dismiss();
    }
}
