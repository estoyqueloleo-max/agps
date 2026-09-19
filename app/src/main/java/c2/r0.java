package c2;

import android.content.DialogInterface;
import android.widget.EditText;
import com.giobat.AgpsTrackerPP.MainActivity;

/**
 * Dialog listener for saving recorded GPS track in MainActivity.
 */
public class r0 implements DialogInterface.OnClickListener {

    public final EditText trackNameInput;
    public final EditText descriptionInput;
    public final MainActivity mainActivity;

    public class SaveTrackRunnable implements Runnable {
        public final String trackName;

        public SaveTrackRunnable(String trackName) {
            this.trackName = trackName;
        }

        @Override
        public void run() {
            MainActivity.A(r0.this.mainActivity, this.trackName + ".gpx", this.trackName, r0.this.mainActivity.f3640b0);
        }
    }

    public r0(MainActivity mainActivity, EditText trackNameInput, EditText descriptionInput) {
        this.mainActivity = mainActivity;
        this.trackNameInput = trackNameInput;
        this.descriptionInput = descriptionInput;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        String trackName = this.trackNameInput.getText().toString();
        this.mainActivity.f3640b0.f3248n = this.descriptionInput.getText().toString();
        v2.e("GPS-M", "DialogBoxRecorderFileSave: start UI thread");
        this.mainActivity.runOnUiThread(new SaveTrackRunnable(trackName));
        dialogInterface.dismiss();
    }
}
