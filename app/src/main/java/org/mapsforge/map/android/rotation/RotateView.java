package org.mapsforge.map.android.rotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import f6.a;

/* JADX INFO: loaded from: classes.dex */
public class RotateView extends ViewGroup {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f17210h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Matrix f17211i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f17212j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final a f17213k;

    public RotateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17210h = 0.0f;
        this.f17211i = new Matrix();
        this.f17212j = -1;
        this.f17213k = new a();
        boolean hw = com.giobat.AgpsTrackerPP.MapRenderPreferences.isHardwareAccelEnabled(context);
        setLayerType(hw ? LAYER_TYPE_HARDWARE : LAYER_TYPE_SOFTWARE, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        if (this.f17210h == 0.0f) {
            super.dispatchDraw(canvas);
            return;
        }
        this.f17212j = canvas.save();
        canvas.rotate(-this.f17210h, getWidth() * 0.5f, getHeight() * 0.5f);
        a aVar = this.f17213k;
        aVar.f5272a = canvas;
        super.dispatchDraw(aVar);
        int i7 = this.f17212j;
        if (i7 != -1) {
            canvas.restoreToCount(i7);
            this.f17212j = -1;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEventObtain;
        float f7 = this.f17210h;
        if (f7 == 0.0f) {
            return super.dispatchTouchEvent(motionEvent);
        }
        float width = getWidth() * 0.5f;
        float height = getHeight() * 0.5f;
        if (f7 == 0.0f) {
            motionEventObtain = motionEvent;
        } else {
            this.f17211i.setRotate(f7, width, height);
            motionEventObtain = MotionEvent.obtain(motionEvent);
            motionEventObtain.transform(this.f17211i);
        }
        try {
            return super.dispatchTouchEvent(motionEventObtain);
        } finally {
            if (motionEventObtain != motionEvent) {
                motionEventObtain.recycle();
            }
        }
    }

    public float getHeading() {
        return this.f17210h;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i7, int i8, int i9, int i10) {
        int width = getWidth();
        int height = getHeight();
        int childCount = getChildCount();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i12 = (width - measuredWidth) / 2;
            int i13 = (height - measuredHeight) / 2;
            childAt.layout(i12, i13, measuredWidth + i12, measuredHeight + i13);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i7, int i8) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) Math.hypot(ViewGroup.getDefaultSize(getSuggestedMinimumWidth(), i7), ViewGroup.getDefaultSize(getSuggestedMinimumHeight(), i8)), 1073741824);
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            getChildAt(i9).measure(iMakeMeasureSpec, iMakeMeasureSpec);
        }
        super.onMeasure(i7, i8);
    }

    public void setHeading(float f7) {
        this.f17210h = f7;
    }
}
