package c2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class j2 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3022h;

    public j2(MainActivity mainActivity) {
        this.f3022h = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        if (Build.VERSION.SDK_INT >= 29) {
            intent.putExtra("android.provider.extra.INITIAL_URI", MediaStore.Files.getContentUri("external"));
        }
        this.f3022h.f3666z0.a(intent, null);
        dialogInterface.dismiss();
    }
}
