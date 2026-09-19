package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatButton;

/* JADX INFO: loaded from: classes.dex */
public class MyGpsButtonView extends AppCompatButton {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static int f3698u = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static boolean f3699v = true;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Paint f3700k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Paint f3701l;
    public Paint m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Paint f3702n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public RectF f3703o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f3704p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f3705q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f3706r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f3707s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f3708t;

    public MyGpsButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3700k = new Paint();
        this.f3701l = new Paint();
        this.m = new Paint();
        this.f3702n = new Paint();
        this.f3704p = 0.0f;
        this.f3703o = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.f3700k.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
        this.m.setColor(-12550144);
        this.m.setStyle(Paint.Style.STROKE);
        this.m.setStrokeWidth(4.0f);
        this.m.setAntiAlias(true);
        this.f3701l.setColor(-65536);
        this.f3701l.setStyle(Paint.Style.STROKE);
        this.f3701l.setStrokeWidth(10.0f);
        this.f3701l.setAntiAlias(true);
        this.f3702n.setColor(-65536);
        this.f3702n.setStyle(Paint.Style.STROKE);
        this.f3701l.setStrokeWidth(10.0f);
        this.f3702n.setAntiAlias(true);
        setElevation(1.0f);
    }

    public static int a(int i7) {
        return (int) (i7 * Resources.getSystem().getDisplayMetrics().density);
    }

    public final void b(Canvas canvas, boolean z) {
        Path path = new Path();
        float f7 = 80.0f;
        if (z) {
            this.m.setColor(-16777216);
            this.f3704p = 80.0f;
            f7 = 40.0f;
            boolean z7 = f3699v;
            if (z7) {
                f3698u++;
            } else {
                f3698u--;
            }
            int i7 = f3698u;
            if (i7 > 5 || i7 < 0) {
                f3699v = !z7;
            }
        } else {
            this.f3704p = 80.0f;
            this.m.setColor(-12550144);
            f3698u = 0;
        }
        for (int i8 = 1; i8 < 7; i8++) {
            float f8 = this.f3707s;
            float f9 = (i8 * f8) / 7;
            this.f3703o.left = (f8 + f9) - a(5);
            this.f3703o.right = ((this.f3705q + this.f3707s) - f9) - a(5);
            RectF rectF = this.f3703o;
            float f10 = this.f3707s;
            rectF.top = (1.2f * f9) + (-f10);
            rectF.bottom = f10 - f9;
            path.addArc(rectF, this.f3704p + (f3698u * 8), f7);
        }
        path.close();
        canvas.drawPath(path, this.m);
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        this.f3705q = getWidth();
        float height = getHeight();
        this.f3706r = height;
        this.f3707s = this.f3705q / 2.0f;
        this.f3708t = height / 2.0f;
        this.f3700k.setTextSize(height / 3.0f);
        float fMeasureText = this.f3700k.measureText("GPS");
        float f7 = this.f3706r / 6.0f;
        int iC = r.g.c(MainActivity.O0);
        if (iC == 0 || iC == 1) {
            canvas.drawText("GPS", this.f3707s - (fMeasureText / 2.0f), this.f3708t + a(10), this.f3700k);
            b(canvas, true);
            return;
        }
        if (iC == 2) {
            b(canvas, false);
            canvas.drawText("GPS", this.f3707s - (fMeasureText / 2.0f), this.f3708t + a(10), this.f3700k);
        } else {
            if (iC == 3) {
                canvas.drawText("gps...", this.f3707s - (fMeasureText / 2.0f), this.f3708t + a(10), this.f3700k);
                return;
            }
            canvas.drawText("GPS", this.f3707s - (fMeasureText / 2.0f), this.f3708t + a(10), this.f3700k);
            this.f3700k.setTextSize(f7);
            canvas.drawText("Error", this.f3707s - (this.f3700k.measureText("Error") / 2.0f), this.f3706r - a(2), this.f3700k);
        }
    }
}
