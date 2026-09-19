package c2;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.widget.EditText;
import com.giobat.AgpsTrackerPP.ActivityFileDialogGpxJpg;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class e1 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ EditText f2979h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MainActivity f2980i;

    public e1(MainActivity mainActivity, EditText editText) {
        this.f2980i = mainActivity;
        this.f2979h = editText;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        int iQ;
        ContentResolver contentResolver = this.f2980i.getApplicationContext().getContentResolver();
        try {
            Uri uriZ = ActivityFileDialogGpxJpg.z(this.f2980i.getApplicationContext(), MainActivity.f3624l1, "AgpsTrackerPhoto");
            MainActivity mainActivity = this.f2980i;
            mainActivity.f3653o0 = DocumentsContract.createDocument(contentResolver, uriZ, "image/*", mainActivity.f3654p0.getName());
            v2.e("GPS-M", "Take photo: photoUri: " + this.f2980i.f3653o0.getLastPathSegment());
            MainActivity mainActivity2 = this.f2980i;
            if (mainActivity2.B0(mainActivity2.f3654p0, mainActivity2.f3656r0, this.f2979h.getText().toString())) {
                MainActivity mainActivity3 = this.f2980i;
                mainActivity3.C(mainActivity3.f3654p0, mainActivity3.f3653o0);
                try {
                    iQ = new u0.a(this.f2980i.f3654p0).q();
                } catch (Exception unused) {
                    iQ = 0;
                }
                h3 h3Var = new h3(this.f2980i.f3653o0, this.f2979h.getText().toString(), new org.mapsforge.core.model.LatLong(this.f2980i.f3656r0.getLatitude(), this.f2980i.f3656r0.getLongitude()), iQ);
                x3 x3Var = AgpsApplication.f3575r;
                if (x3Var != null) {
                    x3Var.f3247l.add(h3Var);
                }
                ((ArrayList) MainActivity.T1).add(h3Var);
                MainActivity.U0 = true;
            }
        } catch (Exception e8) {
            androidx.appcompat.widget.d0.c("Take photo: Error occurred while creating the File: ", e8, MainActivity.f3625m1);
        }
        dialogInterface.dismiss();
    }
}
