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
        if (view instanceof android.widget.TextView) {
            android.widget.TextView textView = (android.widget.TextView) view;
            CharSequence text = textView.getText();
            float textWidth = 0.0f;
            if (text != null && text.length() > 0) {
                textWidth = textView.getPaint().measureText(text.toString());
            }
            float density = context.getResources().getDisplayMetrics().density;
            if (density <= 0.0f) {
                density = 1.0f;
            }
            float fallbackWidth = (text != null && text.length() > 0) ? text.length() * (textView.getTextSize() * 0.65f) : 0.0f;
            textWidth = Math.max(textWidth, fallbackWidth);

            int minW = (int) Math.ceil(textWidth + textView.getCompoundPaddingLeft() + textView.getCompoundPaddingRight());
            android.graphics.Paint.FontMetrics fm = textView.getPaint().getFontMetrics();
            float fontHeight = Math.max(Math.abs(fm.bottom - fm.top), textView.getTextSize() * 1.1f);
            int minH = (int) Math.ceil(fontHeight + textView.getCompoundPaddingTop() + textView.getCompoundPaddingBottom());

            int widthSpec = View.MeasureSpec.makeMeasureSpec(Math.max(minW, (int) (30 * density)), View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(Math.max(minH, (int) (14 * density)), View.MeasureSpec.EXACTLY);
            view.measure(widthSpec, heightSpec);
        } else {
            int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(widthSpec, heightSpec);
        }
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

    /**
     * Generates a plain text-only altitude label (no background card or border).
     * Renders the elevation number in bold, semi-transparent brown text directly on
     * a transparent bitmap — much less visually noisy on dense contour maps.
     */
    public static org.mapsforge.core.graphics.Bitmap createAltitudeBadge(Context context, int elevation) {
        String altText = Integer.toString(elevation);
        float density = context.getResources().getDisplayMetrics().density;
        if (density <= 0.0f) {
            density = 1.0f;
        }

        float textSizePx = 10.0f * density;
        android.graphics.Paint textPaint = new android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(textSizePx);
        textPaint.setTypeface(android.graphics.Typeface.create(android.graphics.Typeface.DEFAULT, android.graphics.Typeface.BOLD));
        // Dark brown, fully opaque so numbers stand out crisply
        textPaint.setColor(android.graphics.Color.rgb(80, 40, 20));

        // White stroke halo for legibility against contour lines and terrain
        android.graphics.Paint strokePaint = new android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);
        strokePaint.setTextSize(textSizePx);
        strokePaint.setTypeface(android.graphics.Typeface.create(android.graphics.Typeface.DEFAULT, android.graphics.Typeface.BOLD));
        strokePaint.setColor(android.graphics.Color.argb(220, 255, 255, 255));
        strokePaint.setStyle(android.graphics.Paint.Style.STROKE);
        strokePaint.setStrokeWidth(2.5f * density);

        float textWidth = Math.max(textPaint.measureText(altText), altText.length() * (textSizePx * 0.65f));
        android.graphics.Paint.FontMetrics fm = textPaint.getFontMetrics();
        float textHeight = Math.abs(fm.bottom - fm.top);

        int padH = (int) Math.ceil(3.0f * density);
        int padV = (int) Math.ceil(2.0f * density);
        int w = (int) Math.ceil(textWidth) + (padH * 2);
        int h = (int) Math.ceil(textHeight) + (padV * 2);
        if (w < 1) w = 1;
        if (h < 1) h = 1;

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        // Draw white halo first, then the dark text on top
        float x = padH;
        float y = padV - fm.ascent;
        canvas.drawText(altText, x, y, strokePaint);
        canvas.drawText(altText, x, y, textPaint);

        return AndroidGraphicFactory.convertToBitmap(new BitmapDrawable(context.getResources(), bitmap));
    }
}

