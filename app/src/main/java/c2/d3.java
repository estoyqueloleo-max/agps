package c2;

import android.content.DialogInterface;
import android.content.Intent;
import com.giobat.AgpsTrackerPP.P2ActivitySettingLocation;

/* JADX INFO: loaded from: classes.dex */
public class d3 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ P2ActivitySettingLocation f2975h;

    public d3(P2ActivitySettingLocation p2ActivitySettingLocation) {
        this.f2975h = p2ActivitySettingLocation;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        this.f2975h.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        dialogInterface.dismiss();
    }
}
