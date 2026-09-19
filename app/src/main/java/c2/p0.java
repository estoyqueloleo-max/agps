package c2;

import android.content.DialogInterface;
import android.widget.CheckBox;
import android.widget.TextView;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class p0 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3112h;

    public p0(MainActivity mainActivity) {
        this.f3112h = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        MainActivity mainActivity = this.f3112h;
        boolean z = MainActivity.I0;
        Objects.requireNonNull(mainActivity);
        v2.e("GPS-M", "------------Reset----------------");
        AgpsApplication.f3575r = new x3(1);
        AgpsApplication.f3569k = 0;
        mainActivity.f3641c0 = false;
        AgpsApplication.f3568j = false;
        AgpsApplication.f3576s = 1;
        mainActivity.I();
        MainActivity.s0("REC_ON", false);
        MainActivity.U0 = true;
        mainActivity.B = 4;
        y3 y3Var = MainActivity.Y0;
        if (y3Var != null && y3Var.f3273h) {
            y3Var.b(mainActivity.findViewById(R.id.graph_window));
        }
        mainActivity.findViewById(R.id.current_track_graph_button).setVisibility(4);
        CheckBox checkBox = (CheckBox) mainActivity.findViewById(R.id.follow_path_return_checkbox);
        checkBox.setVisibility(4);
        checkBox.setChecked(false);
        AgpsApplication.f3571n = false;
        ((TextView) mainActivity.findViewById(R.id.rec_curr_txt)).setText(XmlPullParser.NO_NAMESPACE);
        dialogInterface.dismiss();
    }
}
