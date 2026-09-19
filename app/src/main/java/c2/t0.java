package c2;

import android.content.DialogInterface;
import android.location.Location;
import android.widget.EditText;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class t0 implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ EditText f3173h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ CharSequence f3174i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ boolean f3175j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ EditText f3176k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Location f3177l;

    public t0(MainActivity mainActivity, EditText editText, CharSequence charSequence, boolean z, EditText editText2, Location location) {
        this.f3173h = editText;
        this.f3174i = charSequence;
        this.f3175j = z;
        this.f3176k = editText2;
        this.f3177l = location;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        String string = this.f3173h.getText().toString();
        if (string.equals(XmlPullParser.NO_NAMESPACE)) {
            StringBuilder sbA = android.support.v4.media.b.a(XmlPullParser.NO_NAMESPACE);
            sbA.append((Object) this.f3174i);
            string = sbA.toString();
        }
        x3 x3Var = this.f3175j ? AgpsApplication.f3574q : AgpsApplication.f3575r;
        if (x3Var != null) {
            x3Var.f3246k++;
            x3Var.f3237b.add(new a4(string, this.f3176k.getText().toString(), this.f3177l, 3));
            MainActivity.U0 = true;
        }
        dialogInterface.dismiss();
    }
}
