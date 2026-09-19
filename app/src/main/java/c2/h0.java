package c2;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class h0 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity f2995h;

    public h0(MainActivity mainActivity) {
        this.f2995h = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        MainActivity mainActivity = this.f2995h;
        Objects.requireNonNull(mainActivity);
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("application/*");
        if (Build.VERSION.SDK_INT >= 26) {
            intent.putExtra("android.provider.extra.INITIAL_URI", Uri.parse("content://com.android.externalstorage.documents/document/primary:Download"));
        }
        mainActivity.C0.a(intent, null);
        dialogInterface.dismiss();
    }
}
