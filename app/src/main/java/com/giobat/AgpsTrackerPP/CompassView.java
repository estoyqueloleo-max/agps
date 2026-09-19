package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageButton;

/* JADX INFO: loaded from: classes.dex */
public class CompassView extends AppCompatImageButton {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Paint f3582k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Paint f3583l;
    public Paint m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Paint f3584n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Path f3585o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Paint f3586p;

    public CompassView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3582k = new Paint();
        this.f3583l = new Paint();
        this.m = new Paint();
        this.f3584n = new Paint();
        this.f3585o = new Path();
        this.f3586p = new Paint(1);
        this.f3582k.setColor(-16711936);
        this.f3582k.setStyle(Paint.Style.STROKE);
        this.f3582k.setStrokeWidth(2.0f);
        this.f3582k.setAntiAlias(true);
    }

    public void a(Canvas canvas, int i7, int i8, String str) {
        canvas.save();
        canvas.rotate(-90.0f, i7, i8);
        Path path = new Path();
        path.addRect(new RectF(i7 - 7, i8 - 7, i7 + 7, i8 + 7), Path.Direction.CCW);
        canvas.drawTextOnPath(str, path, 0.0f, 0.0f, this.f3584n);
        canvas.restore();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int i7 = width / 2;
        int i8 = (height / 2) - 1;
        this.f3582k.setColor(-65536);
        int i9 = width / 10;
        this.f3583l.setColor(-16777216);
        this.f3583l.setStyle(Paint.Style.STROKE);
        this.f3583l.setStrokeWidth(i9 / 3);
        this.f3583l.setAntiAlias(true);
        if (MainActivity.M1) {
            this.m.setColor(-16777216);
        } else {
            this.m.setColor(-65536);
        }
        this.m.setStyle(Paint.Style.STROKE);
        this.m.setStrokeWidth(3.0f);
        this.m.setAntiAlias(true);
        this.f3584n.setAntiAlias(true);
        this.f3584n.setStrokeWidth(1.0f);
        this.f3584n.setTextSize(15.0f);
        this.f3584n.setColor(-16777216);
        this.f3584n.setStyle(Paint.Style.FILL_AND_STROKE);
        float f7 = i7;
        float f8 = i8;
        canvas.rotate(-0.0f, f7, f8);
        int iMeasureText = (int) this.f3584n.measureText("N");
        int i10 = iMeasureText / 2;
        int i11 = i7 + 2;
        a(canvas, i11, i10, "N");
        a(canvas, i11, (height - iMeasureText) + 1, "S");
        int i12 = (i8 - i10) + 2;
        a(canvas, i10 + 2, i12, "W");
        a(canvas, width - i10, i12, "E");
        canvas.drawCircle(f7, f8, (i7 - 15) - 1, this.m);
        float f9 = 15;
        canvas.drawLine(f9, f8, width - 15, f8, this.f3583l);
        float f10 = height - 15;
        canvas.drawLine(f7, f9, f7, f10, this.f3583l);
        this.f3583l.setColor(-65536);
        canvas.drawLine(f7, f9, f7, f8, this.f3583l);
        this.m.setColor(-3355444);
        this.m.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(f7, f8, i7 - 30, this.m);
        canvas.rotate(-MainActivity.N1, f7, f8);
        this.f3585o.setFillType(Path.FillType.EVEN_ODD);
        this.f3585o.moveTo(f7, f9);
        float f11 = i7 - i9;
        this.f3585o.lineTo(f11, f8);
        float f12 = i7 + i9;
        this.f3585o.lineTo(f12, f8);
        this.f3585o.lineTo(f7, f9);
        this.f3585o.close();
        this.f3586p.setColor(-65536);
        this.f3586p.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(this.f3585o, this.f3586p);
        this.f3585o.reset();
        this.f3585o.setFillType(Path.FillType.EVEN_ODD);
        this.f3585o.moveTo(f7, f10);
        this.f3585o.lineTo(f11, f8);
        this.f3585o.lineTo(f12, f8);
        this.f3585o.lineTo(f7, f10);
        this.f3585o.close();
        this.f3586p.setColor(-16776961);
        canvas.drawPath(this.f3585o, this.f3586p);
    }
}
