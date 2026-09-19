package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class ScalebarView extends View {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static int f3741k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static float f3742l;
    public static int m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static int f3743n;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Paint f3744h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Paint f3745i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Paint f3746j;

    public ScalebarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3744h = new Paint();
        this.f3745i = new Paint();
        this.f3746j = new Paint();
        this.f3744h.setColor(-16777216);
        this.f3744h.setStrokeWidth(8.0f);
        this.f3745i.setColor(-1);
        this.f3745i.setStrokeWidth(10.0f);
        this.f3746j.setColor(-16777216);
        this.f3746j.setStrokeWidth(4.0f);
        f3742l = context.getApplicationContext().getResources().getDisplayMetrics().density;
        m = context.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        f3743n = context.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i7 = f3741k;
        float f7 = f3742l;
        int i8 = (int) (((100.0f - (i7 / f7)) * f7) / 2.0f);
        float f8 = i8;
        float f9 = 10;
        float f10 = i7 + i8;
        canvas.drawLine(f8, f9, f10, f9, this.f3744h);
        float f11 = 0;
        canvas.drawLine(f8, f11, f10, f11, this.f3745i);
        float f12 = 20;
        canvas.drawLine(f8, f11, f8, f12, this.f3746j);
        float f13 = f3741k + i8;
        canvas.drawLine(f13, f11, f13, f12, this.f3746j);
    }
}
