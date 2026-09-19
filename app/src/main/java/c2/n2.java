package c2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import org.mapsforge.core.graphics.GraphicFactory;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.graphics.Style;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;

/* JADX INFO: loaded from: classes.dex */
public class n2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final GraphicFactory f3081a = AndroidGraphicFactory.INSTANCE;

    public static Paint a(int color, int strokeWidth, int style) {
        Paint paint = AndroidGraphicFactory.INSTANCE.createPaint();
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        if (style == 1) {
            paint.setStyle(Style.FILL);
        } else {
            paint.setStyle(Style.STROKE);
        }
        return paint;
    }

    public static org.mapsforge.core.graphics.Bitmap b(Context context, View view) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthSpec, heightSpec);
        int w = view.getMeasuredWidth();
        int h = view.getMeasuredHeight();
        if (w <= 0) {
            w = 100;
        }
        if (h <= 0) {
            h = 50;
        }
        view.layout(0, 0, w, h);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.setBounds(0, 0, w, h);
            background.draw(canvas);
        }
        view.draw(canvas);
        return AndroidGraphicFactory.convertToBitmap(new BitmapDrawable(context.getResources(), bitmapCreateBitmap));
    }
}
