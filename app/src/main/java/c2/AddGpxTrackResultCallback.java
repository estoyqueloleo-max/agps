package c2;

import android.content.Intent;
import android.net.Uri;
import com.giobat.AgpsTrackerPP.MainActivity;

public class AddGpxTrackResultCallback implements androidx.activity.result.b<androidx.activity.result.a> {

    public final MainActivity mainActivity;

    public AddGpxTrackResultCallback(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void a(androidx.activity.result.a result) {
        Intent intent = result.f289i;
        if (result.f288h != -1 || intent == null) {
            return;
        }
        Uri uri = (Uri) intent.getParcelableExtra("FILE_URI");
        this.mainActivity.n0(uri, "GPXfileRD_DONE_ADD", 2);
        this.mainActivity.invalidateOptionsMenu();
    }
}
