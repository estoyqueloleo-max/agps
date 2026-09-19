package c2;

import android.content.DialogInterface;
import android.widget.EditText;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;

/**
 * Dialog listener for saving current GPS track.
 */
public class i1 implements DialogInterface.OnClickListener {

    public final MainActivity mainActivity;
    public final EditText trackNameInput;
    public final EditText descriptionInput;

    public i1(MainActivity mainActivity, EditText trackNameInput, EditText descriptionInput) {
        this.mainActivity = mainActivity;
        this.trackNameInput = trackNameInput;
        this.descriptionInput = descriptionInput;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        x3 currentTrack = AgpsApplication.f3574q;
        if (currentTrack != null && currentTrack.i() > 1) {
            String name = this.trackNameInput.getText().toString();
            String desc = this.descriptionInput.getText().toString();
            currentTrack.f3242g = name;
            currentTrack.f3243h = name;
            currentTrack.f3244i = desc;
            MainActivity.A(this.mainActivity, name + ".gpx", name, currentTrack);
        }
        dialogInterface.dismiss();
    }
}
