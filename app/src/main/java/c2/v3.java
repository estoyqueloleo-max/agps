package c2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.giobat.AgpsTrackerPP.ActivityMyPhotoShow;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import java.io.IOException;
import java.text.DecimalFormat;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.layer.overlay.Marker;

/* JADX INFO: loaded from: classes.dex */
public class v3 extends Marker {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static Marker f3210r;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final h3 f3211o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Context f3212p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3213q;

    public v3(Context context, h3 h3Var) {
        super(h3Var.f3000b, AndroidGraphicFactory.convertToBitmap(context.getResources().getDrawable(R.drawable.ic_camera_alt_32dp_rosso, context.getTheme())), 0, 0);
        this.f3213q = false;
        this.f3212p = context;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.outline_camera_orange, this.f3212p.getTheme());
        int height = bitmapDrawable.getBitmap().getHeight();
        int i7 = (-height) / 2;
        setVerticalOffset(i7);
        this.f3211o = h3Var;
    }

    @Override
    public boolean onTap(LatLong cVar, Point gVar, Point gVar2) {
        double dM = gVar.x + ((double) getHorizontalOffset());
        double dN = gVar.y + ((double) getVerticalOffset());
        double width = (getBitmap().getWidth() / 2) * 2;
        double height = (getBitmap().getHeight() / 2) * 2;
        double dAbs = Math.abs(dM - gVar2.x);
        double dAbs2 = Math.abs(dN - gVar2.y);
        v2.e("GPS-M", "onTap photo symbol");
        if (dAbs >= width || dAbs2 >= height) {
            if (this.f3213q) {
                this.f3213q = false;
                if (f3210r != null) {
                    MainActivity.f3626n1.b(f3210r);
                }
            }
            return false;
        }
        this.f3213q = true;
        System.currentTimeMillis();
        w3 w3Var = null;
        View viewInflate = LayoutInflater.from(this.f3212p).inflate(R.layout.pp_info_window_layout, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_fname);
        String lastPathSegment = this.f3211o.f3002d.getLastPathSegment();
        textView.setText(lastPathSegment.substring(lastPathSegment.lastIndexOf(47) + 1));
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.photo_image);
        Uri uri = this.f3211o.f3002d;
        try {
            u0.a aVar = new u0.a(this.f3212p.getContentResolver().openInputStream(uri));
            w3 w3Var2 = new w3();
            Bitmap bitmapT = aVar.t();
            if (bitmapT == null) {
                bitmapT = MainActivity.d0(this.f3212p, uri);
            }
            String strF = aVar.f("UserComment");
            w3Var2.f3223h = bitmapT;
            w3Var2.f3224i = strF;
            w3Var = w3Var2;
        } catch (IOException unused) {
        }
        if (w3Var != null && w3Var.f3223h != null) {
            imageView.setImageBitmap((Bitmap) w3Var.f3223h);
        }
        imageView.setRotation(ActivityMyPhotoShow.x(this.f3212p, this.f3211o.f3002d));
        new DecimalFormat("###0");
        new DecimalFormat("###0.0000");
        ((TextView) viewInflate.findViewById(R.id.tv_descr)).setText(this.f3211o.f2999a);
        org.mapsforge.core.graphics.Bitmap bVarB = n2.b(this.f3212p, viewInflate.findViewById(R.id.info_photo_all));
        if (f3210r != null) {
            MainActivity.f3626n1.b(f3210r);
        }
        f3210r = new u3(this, cVar, bVarB, 0, (-bVarB.getHeight()) / 2);
        MainActivity.f3626n1.a(f3210r);
        return true;
    }
}
