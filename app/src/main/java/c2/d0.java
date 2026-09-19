package c2;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import com.giobat.AgpsTrackerPP.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class d0 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f2973h;

    public d0(MainActivity mainActivity) {
        this.f2973h = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        this.f2973h.B();
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        if (Build.VERSION.SDK_INT >= 29) {
            Uri contentUri = MediaStore.Files.getContentUri("external");
            Uri uri = MainActivity.f3624l1;
            if (uri != null) {
                contentUri = uri;
            }
            intent.putExtra("android.provider.extra.INITIAL_URI", contentUri);
        }
        this.f2973h.f3666z0.a(intent, null);
        dialogInterface.dismiss();
    }
}
