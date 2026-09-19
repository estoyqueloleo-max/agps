package com.ortiz.touchview;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.OverScroller;
import androidx.appcompat.widget.AppCompatImageView;

/* JADX INFO: loaded from: classes.dex */
public class TouchImageView extends AppCompatImageView {
    public static final /* synthetic */ int W = 0;
    public float[] A;
    public float B;
    public c C;
    public int D;
    public ImageView.ScaleType E;
    public boolean F;
    public boolean G;
    public q5.f H;
    public int I;
    public int J;
    public int K;
    public int L;
    public float M;
    public float N;
    public float O;
    public float P;
    public ScaleGestureDetector Q;
    public GestureDetector R;
    public q5.c S;
    public GestureDetector.OnDoubleTapListener T;
    public View.OnTouchListener U;
    public q5.d V;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f4526k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Matrix f4527l;
    public Matrix m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f4528n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f4529o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public q5.a f4530p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public q5.a f4531q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f4532r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public q5.b f4533s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f4534t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f4535u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f4536v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f4537w;
    public float x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f4538y;
    public float z;

    public final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public OverScroller f4539a;

        public a(TouchImageView touchImageView, Context context) {
            this.f4539a = new OverScroller(context);
        }
    }

    public final class b implements Runnable {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final long f4540h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final float f4541i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final float f4542j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final float f4543k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final float f4544l;
        public final boolean m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public final AccelerateDecelerateInterpolator f4545n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public final PointF f4546o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public final PointF f4547p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public final /* synthetic */ TouchImageView f4548q;

        public b(TouchImageView touchImageView, float f7, float f8, float f9, boolean z) {
            w5.a.d(touchImageView, "this$0");
            this.f4548q = touchImageView;
            this.f4545n = new AccelerateDecelerateInterpolator();
            touchImageView.setState(q5.b.ANIMATE_ZOOM);
            this.f4540h = System.currentTimeMillis();
            this.f4541i = touchImageView.getCurrentZoom();
            this.f4542j = f7;
            this.m = z;
            PointF pointFR = touchImageView.r(f8, f9, false);
            float f10 = pointFR.x;
            this.f4543k = f10;
            float f11 = pointFR.y;
            this.f4544l = f11;
            this.f4546o = touchImageView.q(f10, f11);
            this.f4547p = new PointF(touchImageView.I / 2, touchImageView.J / 2);
        }

        @Override // java.lang.Runnable
        public void run() {
            q5.b bVar = q5.b.NONE;
            if (this.f4548q.getDrawable() == null) {
                this.f4548q.setState(bVar);
                return;
            }
            float interpolation = this.f4545n.getInterpolation(Math.min(1.0f, (System.currentTimeMillis() - this.f4540h) / 500.0f));
            float f7 = this.f4541i;
            this.f4548q.o(((((double) interpolation) * ((double) (this.f4542j - f7))) + ((double) f7)) / ((double) this.f4548q.getCurrentZoom()), this.f4543k, this.f4544l, this.m);
            PointF pointF = this.f4546o;
            float f8 = pointF.x;
            PointF pointF2 = this.f4547p;
            float fA = u.e.a(pointF2.x, f8, interpolation, f8);
            float f9 = pointF.y;
            float fA2 = u.e.a(pointF2.y, f9, interpolation, f9);
            PointF pointFQ = this.f4548q.q(this.f4543k, this.f4544l);
            this.f4548q.f4527l.postTranslate(fA - pointFQ.x, fA2 - pointFQ.y);
            this.f4548q.g();
            TouchImageView touchImageView = this.f4548q;
            touchImageView.setImageMatrix(touchImageView.f4527l);
            q5.d dVar = this.f4548q.V;
            if (dVar != null) {
                dVar.a();
            }
            if (interpolation < 1.0f) {
                this.f4548q.postOnAnimation(this);
            } else {
                this.f4548q.setState(bVar);
            }
        }
    }

    public final class c implements Runnable {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public a f4549h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f4550i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f4551j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final /* synthetic */ TouchImageView f4552k;

        public c(TouchImageView touchImageView, int i7, int i8) {
            int imageWidth;
            int i9;
            int imageHeight;
            int i10;
            w5.a.d(touchImageView, "this$0");
            this.f4552k = touchImageView;
            touchImageView.setState(q5.b.FLING);
            this.f4549h = new a(touchImageView, touchImageView.getContext());
            touchImageView.f4527l.getValues(touchImageView.A);
            float[] fArr = touchImageView.A;
            int imageWidth2 = (int) fArr[2];
            int i11 = (int) fArr[5];
            if (touchImageView.f4529o && touchImageView.m(touchImageView.getDrawable())) {
                imageWidth2 -= (int) touchImageView.getImageWidth();
            }
            float imageWidth3 = touchImageView.getImageWidth();
            int i12 = touchImageView.I;
            if (imageWidth3 > i12) {
                imageWidth = i12 - ((int) touchImageView.getImageWidth());
                i9 = 0;
            } else {
                imageWidth = imageWidth2;
                i9 = imageWidth;
            }
            float imageHeight2 = touchImageView.getImageHeight();
            int i13 = touchImageView.J;
            if (imageHeight2 > i13) {
                imageHeight = i13 - ((int) touchImageView.getImageHeight());
                i10 = 0;
            } else {
                imageHeight = i11;
                i10 = imageHeight;
            }
            this.f4549h.f4539a.fling(imageWidth2, i11, i7, i8, imageWidth, i9, imageHeight, i10);
            this.f4550i = imageWidth2;
            this.f4551j = i11;
        }

        @Override // java.lang.Runnable
        public void run() {
            q5.d dVar = this.f4552k.V;
            if (dVar != null) {
                dVar.a();
            }
            if (this.f4549h.f4539a.isFinished()) {
                return;
            }
            a aVar = this.f4549h;
            aVar.f4539a.computeScrollOffset();
            if (aVar.f4539a.computeScrollOffset()) {
                int currX = this.f4549h.f4539a.getCurrX();
                int currY = this.f4549h.f4539a.getCurrY();
                int i7 = currX - this.f4550i;
                int i8 = currY - this.f4551j;
                this.f4550i = currX;
                this.f4551j = currY;
                this.f4552k.f4527l.postTranslate(i7, i8);
                this.f4552k.h();
                TouchImageView touchImageView = this.f4552k;
                touchImageView.setImageMatrix(touchImageView.f4527l);
                this.f4552k.postOnAnimation(this);
            }
        }
    }

    public final class d extends GestureDetector.SimpleOnGestureListener {
        public d() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (motionEvent != null) {
                TouchImageView touchImageView = TouchImageView.this;
                if (touchImageView.f4528n) {
                    GestureDetector.OnDoubleTapListener onDoubleTapListener = touchImageView.T;
                    boolean zOnDoubleTap = onDoubleTapListener == null ? false : onDoubleTapListener.onDoubleTap(motionEvent);
                    TouchImageView touchImageView2 = TouchImageView.this;
                    if (touchImageView2.f4533s != q5.b.NONE) {
                        return zOnDoubleTap;
                    }
                    float doubleTapScale = (touchImageView2.getDoubleTapScale() > 0.0f ? 1 : (touchImageView2.getDoubleTapScale() == 0.0f ? 0 : -1)) == 0 ? TouchImageView.this.x : TouchImageView.this.getDoubleTapScale();
                    float currentZoom = TouchImageView.this.getCurrentZoom();
                    TouchImageView touchImageView3 = TouchImageView.this;
                    float f7 = touchImageView3.f4535u;
                    TouchImageView.this.postOnAnimation(new b(touchImageView3, currentZoom == f7 ? doubleTapScale : f7, motionEvent.getX(), motionEvent.getY(), false));
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            GestureDetector.OnDoubleTapListener onDoubleTapListener = TouchImageView.this.T;
            if (onDoubleTapListener == null) {
                return false;
            }
            return onDoubleTapListener.onDoubleTapEvent(motionEvent);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f7, float f8) {
            c cVar = TouchImageView.this.C;
            if (cVar != null) {
                cVar.f4552k.setState(q5.b.NONE);
                cVar.f4549h.f4539a.forceFinished(true);
            }
            TouchImageView touchImageView = TouchImageView.this;
            c cVar2 = new c(touchImageView, (int) f7, (int) f8);
            TouchImageView.this.postOnAnimation(cVar2);
            touchImageView.C = cVar2;
            return super.onFling(motionEvent, motionEvent2, f7, f8);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            TouchImageView.this.performLongClick();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            GestureDetector.OnDoubleTapListener onDoubleTapListener = TouchImageView.this.T;
            Boolean boolValueOf = onDoubleTapListener == null ? null : Boolean.valueOf(onDoubleTapListener.onSingleTapConfirmed(motionEvent));
            return boolValueOf == null ? TouchImageView.this.performClick() : boolValueOf.booleanValue();
        }
    }

    public final class e implements View.OnTouchListener {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final PointF f4554h = new PointF();

        public e() {
        }

        /* JADX WARN: Code duplicated, block: B:31:0x009b  */
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            q5.b bVar = q5.b.NONE;
            q5.b bVar2 = q5.b.DRAG;
            w5.a.d(view, "v");
            w5.a.d(motionEvent, "event");
            if (TouchImageView.this.getDrawable() == null) {
                TouchImageView.this.setState(bVar);
                return false;
            }
            TouchImageView touchImageView = TouchImageView.this;
            if (touchImageView.f4528n) {
                touchImageView.Q.onTouchEvent(motionEvent);
            }
            TouchImageView.this.R.onTouchEvent(motionEvent);
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            q5.b bVar3 = TouchImageView.this.f4533s;
            if (bVar3 == bVar || bVar3 == bVar2 || bVar3 == q5.b.FLING) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.f4554h.set(pointF);
                    c cVar = TouchImageView.this.C;
                    if (cVar != null) {
                        cVar.f4552k.setState(bVar);
                        cVar.f4549h.f4539a.forceFinished(true);
                    }
                    TouchImageView.this.setState(bVar2);
                } else if (action == 1) {
                    TouchImageView.this.setState(bVar);
                } else if (action == 2) {
                    TouchImageView touchImageView2 = TouchImageView.this;
                    if (touchImageView2.f4533s == bVar2) {
                        float f7 = pointF.x;
                        PointF pointF2 = this.f4554h;
                        float f8 = f7 - pointF2.x;
                        float f9 = pointF.y - pointF2.y;
                        if (touchImageView2.getImageWidth() <= touchImageView2.I) {
                            f8 = 0.0f;
                        }
                        TouchImageView touchImageView3 = TouchImageView.this;
                        if (touchImageView3.getImageHeight() <= touchImageView3.J) {
                            f9 = 0.0f;
                        }
                        TouchImageView.this.f4527l.postTranslate(f8, f9);
                        TouchImageView.this.h();
                        this.f4554h.set(pointF.x, pointF.y);
                    }
                } else if (action == 6) {
                    TouchImageView.this.setState(bVar);
                }
            }
            TouchImageView touchImageView4 = TouchImageView.this;
            q5.c cVar2 = touchImageView4.S;
            if (cVar2 != null) {
                cVar2.a(view, motionEvent, touchImageView4.r(motionEvent.getX(), motionEvent.getY(), true));
            }
            TouchImageView touchImageView5 = TouchImageView.this;
            touchImageView5.setImageMatrix(touchImageView5.f4527l);
            View.OnTouchListener onTouchListener = TouchImageView.this.U;
            if (onTouchListener != null) {
                onTouchListener.onTouch(view, motionEvent);
            }
            q5.d dVar = TouchImageView.this.V;
            if (dVar != null) {
                dVar.a();
            }
            return true;
        }
    }

    public final class f extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        public f() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            w5.a.d(scaleGestureDetector, "detector");
            TouchImageView touchImageView = TouchImageView.this;
            double scaleFactor = scaleGestureDetector.getScaleFactor();
            float focusX = scaleGestureDetector.getFocusX();
            float focusY = scaleGestureDetector.getFocusY();
            int i7 = TouchImageView.W;
            touchImageView.o(scaleFactor, focusX, focusY, true);
            q5.d dVar = TouchImageView.this.V;
            if (dVar == null) {
                return true;
            }
            dVar.a();
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            w5.a.d(scaleGestureDetector, "detector");
            TouchImageView.this.setState(q5.b.ZOOM);
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            float f7;
            w5.a.d(scaleGestureDetector, "detector");
            super.onScaleEnd(scaleGestureDetector);
            TouchImageView.this.setState(q5.b.NONE);
            float currentZoom = TouchImageView.this.getCurrentZoom();
            float currentZoom2 = TouchImageView.this.getCurrentZoom();
            TouchImageView touchImageView = TouchImageView.this;
            float f8 = touchImageView.x;
            boolean z = true;
            if (currentZoom2 > f8) {
                f7 = f8;
            } else {
                float currentZoom3 = touchImageView.getCurrentZoom();
                float f9 = TouchImageView.this.f4535u;
                if (currentZoom3 < f9) {
                    f7 = f9;
                } else {
                    z = false;
                    f7 = currentZoom;
                }
            }
            if (z) {
                TouchImageView touchImageView2 = TouchImageView.this;
                TouchImageView.this.postOnAnimation(new b(touchImageView2, f7, touchImageView2.I / 2, touchImageView2.J / 2, true));
            }
        }
    }

    public static /* synthetic */ class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4557a;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            iArr[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            iArr[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            iArr[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            iArr[ImageView.ScaleType.FIT_START.ordinal()] = 5;
            iArr[ImageView.ScaleType.FIT_END.ordinal()] = 6;
            iArr[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            f4557a = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        w5.a.d(context, "context");
        q5.a aVar = q5.a.CENTER;
        this.f4530p = aVar;
        this.f4531q = aVar;
        super.setClickable(true);
        this.D = getResources().getConfiguration().orientation;
        this.Q = new ScaleGestureDetector(context, new f());
        this.R = new GestureDetector(context, new d());
        this.f4527l = new Matrix();
        this.m = new Matrix();
        this.A = new float[9];
        this.f4526k = 1.0f;
        if (this.E == null) {
            this.E = ImageView.ScaleType.FIT_CENTER;
        }
        this.f4535u = 1.0f;
        this.x = 3.0f;
        this.f4538y = 0.75f;
        this.z = 3.75f;
        setImageMatrix(this.f4527l);
        setScaleType(ImageView.ScaleType.MATRIX);
        setState(q5.b.NONE);
        this.G = false;
        super.setOnTouchListener(new e());
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{com.giobat.AgpsTrackerPP.R.attr.zoom_enabled}, 0, 0);
        w5.a.c(typedArrayObtainStyledAttributes, "context.theme.obtainStyl…chImageView, defStyle, 0)");
        try {
            if (!isInEditMode()) {
                this.f4528n = typedArrayObtainStyledAttributes.getBoolean(0, true);
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getImageHeight() {
        return this.N * this.f4526k;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getImageWidth() {
        return this.M * this.f4526k;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setState(q5.b bVar) {
        this.f4533s = bVar;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i7) {
        this.f4527l.getValues(this.A);
        float f7 = this.A[2];
        return getImageWidth() >= ((float) this.I) && (f7 < -1.0f || i7 >= 0) && ((Math.abs(f7) + ((float) this.I)) + ((float) 1) < getImageWidth() || i7 <= 0);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i7) {
        this.f4527l.getValues(this.A);
        float f7 = this.A[5];
        return getImageHeight() >= ((float) this.J) && (f7 < -1.0f || i7 >= 0) && ((Math.abs(f7) + ((float) this.J)) + ((float) 1) < getImageHeight() || i7 <= 0);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:41:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:63:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:65:0x0101  */
    /* JADX WARN: Code duplicated, block: B:66:0x0103  */
    /* JADX WARN: Code duplicated, block: B:68:0x0106  */
    /* JADX WARN: Code duplicated, block: B:70:0x010c  */
    /* JADX WARN: Code duplicated, block: B:71:0x010e  */
    /* JADX WARN: Code duplicated, block: B:73:0x0111  */
    public final void f() {
        boolean z;
        boolean z7;
        boolean z8;
        q5.a aVar = this.f4532r ? this.f4530p : this.f4531q;
        this.f4532r = false;
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0 || this.f4527l == null || this.m == null) {
            return;
        }
        if (this.f4534t == -1.0f) {
            setMinZoom(-1.0f);
            float f7 = this.f4526k;
            float f8 = this.f4535u;
            if (f7 < f8) {
                this.f4526k = f8;
            }
        }
        int iJ = j(drawable);
        int i7 = i(drawable);
        float f9 = iJ;
        float fMax = this.I / f9;
        float f10 = i7;
        float f11 = this.J / f10;
        ImageView.ScaleType scaleType = this.E;
        switch (scaleType == null ? -1 : g.f4557a[scaleType.ordinal()]) {
            case 1:
                fMax = 1.0f;
                break;
            case 2:
                fMax = Math.max(fMax, f11);
                break;
            case 3:
                float fMin = Math.min(1.0f, Math.min(fMax, f11));
                fMax = Math.min(fMin, fMin);
                break;
            case 4:
            case 5:
            case 6:
                fMax = Math.min(fMax, f11);
                break;
            default:
                int i8 = this.I;
                float f12 = i8 - (fMax * f9);
                int i9 = this.J;
                float f13 = i9 - (f11 * f10);
                this.M = i8 - f12;
                this.N = i9 - f13;
                if (this.f4526k == 1.0f) {
                    z = true;
                } else {
                    z = false;
                }
                if (!(!z) || this.F) {
                    if (this.O == 0.0f) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    if (z7) {
                        n();
                    } else {
                        if (this.P == 0.0f) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        if (z8) {
                            n();
                        }
                    }
                    this.m.getValues(this.A);
                    float[] fArr = this.A;
                    float f14 = this.M / f9;
                    float f15 = this.f4526k;
                    fArr[0] = f14 * f15;
                    fArr[4] = (this.N / f10) * f15;
                    float f16 = fArr[2];
                    float f17 = fArr[5];
                    this.A[2] = l(f16, f15 * this.O, getImageWidth(), this.K, this.I, iJ, aVar);
                    this.A[5] = l(f17, this.P * this.f4526k, getImageHeight(), this.L, this.J, i7, aVar);
                    this.f4527l.setValues(this.A);
                } else {
                    if (this.f4529o && m(drawable)) {
                        this.f4527l.setRotate(90.0f);
                        this.f4527l.postTranslate(f9, 0.0f);
                        this.f4527l.postScale(fMax, f11);
                    } else {
                        this.f4527l.setScale(fMax, f11);
                    }
                    ImageView.ScaleType scaleType2 = this.E;
                    int i10 = scaleType2 == null ? -1 : g.f4557a[scaleType2.ordinal()];
                    if (i10 == 5) {
                        this.f4527l.postTranslate(0.0f, 0.0f);
                    } else if (i10 != 6) {
                        float f18 = 2;
                        this.f4527l.postTranslate(f12 / f18, f13 / f18);
                    } else {
                        this.f4527l.postTranslate(f12, f13);
                    }
                    this.f4526k = 1.0f;
                }
                h();
                setImageMatrix(this.f4527l);
        }
        f11 = fMax;
        int i11 = this.I;
        float f19 = i11 - (fMax * f9);
        int i12 = this.J;
        float f110 = i12 - (f11 * f10);
        this.M = i11 - f19;
        this.N = i12 - f110;
        if (this.f4526k == 1.0f) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            if (this.O == 0.0f) {
                z7 = true;
            } else {
                z7 = false;
            }
            if (z7) {
                n();
            } else {
                if (this.P == 0.0f) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                if (z8) {
                    n();
                }
            }
            this.m.getValues(this.A);
            float[] fArr2 = this.A;
            float f111 = this.M / f9;
            float f112 = this.f4526k;
            fArr2[0] = f111 * f112;
            fArr2[4] = (this.N / f10) * f112;
            float f113 = fArr2[2];
            float f114 = fArr2[5];
            this.A[2] = l(f113, f112 * this.O, getImageWidth(), this.K, this.I, iJ, aVar);
            this.A[5] = l(f114, this.P * this.f4526k, getImageHeight(), this.L, this.J, i7, aVar);
            this.f4527l.setValues(this.A);
        } else {
            if (this.O == 0.0f) {
                z7 = true;
            } else {
                z7 = false;
            }
            if (z7) {
                n();
            } else {
                if (this.P == 0.0f) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                if (z8) {
                    n();
                }
            }
            this.m.getValues(this.A);
            float[] fArr3 = this.A;
            float f115 = this.M / f9;
            float f116 = this.f4526k;
            fArr3[0] = f115 * f116;
            fArr3[4] = (this.N / f10) * f116;
            float f117 = fArr3[2];
            float f118 = fArr3[5];
            this.A[2] = l(f117, f116 * this.O, getImageWidth(), this.K, this.I, iJ, aVar);
            this.A[5] = l(f118, this.P * this.f4526k, getImageHeight(), this.L, this.J, i7, aVar);
            this.f4527l.setValues(this.A);
        }
        h();
        setImageMatrix(this.f4527l);
    }

    public final void g() {
        h();
        this.f4527l.getValues(this.A);
        float imageWidth = getImageWidth();
        int i7 = this.I;
        if (imageWidth < i7) {
            float imageWidth2 = (i7 - getImageWidth()) / 2;
            if (this.f4529o && m(getDrawable())) {
                imageWidth2 += getImageWidth();
            }
            this.A[2] = imageWidth2;
        }
        float imageHeight = getImageHeight();
        int i8 = this.J;
        if (imageHeight < i8) {
            this.A[5] = (i8 - getImageHeight()) / 2;
        }
        this.f4527l.setValues(this.A);
    }

    public final float getCurrentZoom() {
        return this.f4526k;
    }

    public final float getDoubleTapScale() {
        return this.B;
    }

    public final float getMaxZoom() {
        return this.x;
    }

    public final float getMinZoom() {
        return this.f4535u;
    }

    public final q5.a getOrientationChangeFixedPixel() {
        return this.f4530p;
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        ImageView.ScaleType scaleType = this.E;
        w5.a.b(scaleType);
        return scaleType;
    }

    public final PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return new PointF(0.5f, 0.5f);
        }
        int iJ = j(drawable);
        int i7 = i(drawable);
        PointF pointFR = r(this.I / 2.0f, this.J / 2.0f, true);
        pointFR.x /= iJ;
        pointFR.y /= i7;
        return pointFR;
    }

    public final q5.a getViewSizeChangeFixedPixel() {
        return this.f4531q;
    }

    public final RectF getZoomedRect() {
        if (this.E == ImageView.ScaleType.FIT_XY) {
            throw new UnsupportedOperationException("getZoomedRect() not supported with FIT_XY");
        }
        PointF pointFR = r(0.0f, 0.0f, true);
        PointF pointFR2 = r(this.I, this.J, true);
        float fJ = j(getDrawable());
        float fI = i(getDrawable());
        return new RectF(pointFR.x / fJ, pointFR.y / fI, pointFR2.x / fJ, pointFR2.y / fI);
    }

    public final void h() {
        this.f4527l.getValues(this.A);
        float[] fArr = this.A;
        this.f4527l.postTranslate(k(fArr[2], this.I, getImageWidth(), (this.f4529o && m(getDrawable())) ? getImageWidth() : 0.0f), k(fArr[5], this.J, getImageHeight(), 0.0f));
    }

    public final int i(Drawable drawable) {
        return (m(drawable) && this.f4529o) ? drawable.getIntrinsicWidth() : drawable.getIntrinsicHeight();
    }

    public final int j(Drawable drawable) {
        return (m(drawable) && this.f4529o) ? drawable.getIntrinsicHeight() : drawable.getIntrinsicWidth();
    }

    public final float k(float f7, float f8, float f9, float f10) {
        float f11;
        if (f9 <= f8) {
            f11 = (f8 + f10) - f9;
        } else {
            f10 = (f8 + f10) - f9;
            f11 = f10;
        }
        if (f7 < f10) {
            return (-f7) + f10;
        }
        if (f7 > f11) {
            return (-f7) + f11;
        }
        return 0.0f;
    }

    public final float l(float f7, float f8, float f9, int i7, int i8, int i9, q5.a aVar) {
        float f10 = i8;
        float f11 = 0.5f;
        if (f9 < f10) {
            return (f10 - (i9 * this.A[0])) * 0.5f;
        }
        if (f7 > 0.0f) {
            return -((f9 - f10) * 0.5f);
        }
        if (aVar == q5.a.BOTTOM_RIGHT) {
            f11 = 1.0f;
        } else if (aVar == q5.a.TOP_LEFT) {
            f11 = 0.0f;
        }
        return -(((((i7 * f11) + (-f7)) / f8) * f9) - (f10 * f11));
    }

    public final boolean m(Drawable drawable) {
        boolean z = this.I > this.J;
        w5.a.b(drawable);
        return z != (drawable.getIntrinsicWidth() > drawable.getIntrinsicHeight());
    }

    public final void n() {
        if (this.J == 0 || this.I == 0) {
            return;
        }
        this.f4527l.getValues(this.A);
        this.m.setValues(this.A);
        this.P = this.N;
        this.O = this.M;
        this.L = this.J;
        this.K = this.I;
    }

    public final void o(double d8, float f7, float f8, boolean z) {
        float f9;
        float f10;
        double d9;
        if (z) {
            f9 = this.f4538y;
            f10 = this.z;
        } else {
            f9 = this.f4535u;
            f10 = this.x;
        }
        float f11 = this.f4526k;
        float f12 = ((float) d8) * f11;
        this.f4526k = f12;
        if (f12 <= f10) {
            if (f12 < f9) {
                this.f4526k = f9;
                d9 = f9;
            }
            float f13 = (float) d8;
            this.f4527l.postScale(f13, f13, f7, f8);
            g();
        }
        this.f4526k = f10;
        d9 = f10;
        d8 = d9 / ((double) f11);
        float f14 = (float) d8;
        this.f4527l.postScale(f14, f14, f7, f8);
        g();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        w5.a.d(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        int i7 = getResources().getConfiguration().orientation;
        if (i7 != this.D) {
            this.f4532r = true;
            this.D = i7;
        }
        n();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        w5.a.d(canvas, "canvas");
        this.G = true;
        this.F = true;
        q5.f fVar = this.H;
        if (fVar != null) {
            w5.a.b(fVar);
            float f7 = fVar.f17465a;
            q5.f fVar2 = this.H;
            w5.a.b(fVar2);
            float f8 = fVar2.f17466b;
            q5.f fVar3 = this.H;
            w5.a.b(fVar3);
            float f9 = fVar3.f17467c;
            q5.f fVar4 = this.H;
            w5.a.b(fVar4);
            p(f7, f8, f9, fVar4.f17468d);
            this.H = null;
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i7, int i8) {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        int iJ = j(drawable);
        int i9 = i(drawable);
        int size = View.MeasureSpec.getSize(i7);
        int mode = View.MeasureSpec.getMode(i7);
        int size2 = View.MeasureSpec.getSize(i8);
        int mode2 = View.MeasureSpec.getMode(i8);
        if (mode == Integer.MIN_VALUE) {
            iJ = Math.min(iJ, size);
        } else if (mode != 0) {
            iJ = size;
        }
        if (mode2 == Integer.MIN_VALUE) {
            i9 = Math.min(i9, size2);
        } else if (mode2 != 0) {
            i9 = size2;
        }
        if (!this.f4532r) {
            n();
        }
        setMeasuredDimension((iJ - getPaddingLeft()) - getPaddingRight(), (i9 - getPaddingTop()) - getPaddingBottom());
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        w5.a.d(parcelable, "state");
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        this.f4526k = bundle.getFloat("saveScale");
        float[] floatArray = bundle.getFloatArray("matrix");
        w5.a.b(floatArray);
        this.A = floatArray;
        this.m.setValues(floatArray);
        this.P = bundle.getFloat("matchViewHeight");
        this.O = bundle.getFloat("matchViewWidth");
        this.L = bundle.getInt("viewHeight");
        this.K = bundle.getInt("viewWidth");
        this.F = bundle.getBoolean("imageRendered");
        this.f4531q = (q5.a) bundle.getSerializable("viewSizeChangeFixedPixel");
        this.f4530p = (q5.a) bundle.getSerializable("orientationChangeFixedPixel");
        if (this.D != bundle.getInt("orientation")) {
            this.f4532r = true;
        }
        super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("orientation", this.D);
        bundle.putFloat("saveScale", this.f4526k);
        bundle.putFloat("matchViewHeight", this.N);
        bundle.putFloat("matchViewWidth", this.M);
        bundle.putInt("viewWidth", this.I);
        bundle.putInt("viewHeight", this.J);
        this.f4527l.getValues(this.A);
        bundle.putFloatArray("matrix", this.A);
        bundle.putBoolean("imageRendered", this.F);
        bundle.putSerializable("viewSizeChangeFixedPixel", this.f4531q);
        bundle.putSerializable("orientationChangeFixedPixel", this.f4530p);
        return bundle;
    }

    @Override // android.view.View
    public void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        this.I = i7;
        this.J = i8;
        f();
    }

    public final void p(float f7, float f8, float f9, ImageView.ScaleType scaleType) {
        if (!this.G) {
            this.H = new q5.f(f7, f8, f9, scaleType);
            return;
        }
        if (this.f4534t == -1.0f) {
            setMinZoom(-1.0f);
            float f10 = this.f4526k;
            float f11 = this.f4535u;
            if (f10 < f11) {
                this.f4526k = f11;
            }
        }
        if (scaleType != this.E) {
            w5.a.b(scaleType);
            setScaleType(scaleType);
        }
        this.f4526k = 1.0f;
        f();
        o(f7, this.I / 2.0f, this.J / 2.0f, true);
        this.f4527l.getValues(this.A);
        float[] fArr = this.A;
        float f12 = this.I;
        float f13 = this.M;
        float f14 = 2;
        float f15 = f7 - 1;
        fArr[2] = ((f12 - f13) / f14) - ((f8 * f15) * f13);
        float f16 = this.J;
        float f17 = this.N;
        fArr[5] = ((f16 - f17) / f14) - ((f9 * f15) * f17);
        this.f4527l.setValues(fArr);
        h();
        n();
        setImageMatrix(this.f4527l);
    }

    public final PointF q(float f7, float f8) {
        this.f4527l.getValues(this.A);
        return new PointF((getImageWidth() * (f7 / getDrawable().getIntrinsicWidth())) + this.A[2], (getImageHeight() * (f8 / getDrawable().getIntrinsicHeight())) + this.A[5]);
    }

    public final PointF r(float f7, float f8, boolean z) {
        this.f4527l.getValues(this.A);
        float intrinsicWidth = getDrawable().getIntrinsicWidth();
        float intrinsicHeight = getDrawable().getIntrinsicHeight();
        float[] fArr = this.A;
        float f9 = fArr[2];
        float f10 = fArr[5];
        float imageWidth = ((f7 - f9) * intrinsicWidth) / getImageWidth();
        float imageHeight = ((f8 - f10) * intrinsicHeight) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            imageHeight = Math.min(Math.max(imageHeight, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, imageHeight);
    }

    public final void setDoubleTapScale(float f7) {
        this.B = f7;
    }

    @Override // androidx.appcompat.widget.s, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        w5.a.d(bitmap, "bm");
        this.F = false;
        super.setImageBitmap(bitmap);
        n();
        f();
    }

    @Override // androidx.appcompat.widget.s, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.F = false;
        super.setImageDrawable(drawable);
        n();
        f();
    }

    @Override // androidx.appcompat.widget.s, android.widget.ImageView
    public void setImageResource(int i7) {
        this.F = false;
        super.setImageResource(i7);
        n();
        f();
    }

    @Override // androidx.appcompat.widget.s, android.widget.ImageView
    public void setImageURI(Uri uri) {
        this.F = false;
        super.setImageURI(uri);
        n();
        f();
    }

    public final void setMaxZoom(float f7) {
        this.x = f7;
        this.z = f7 * 1.25f;
        this.f4536v = false;
    }

    public final void setMaxZoomRatio(float f7) {
        this.f4537w = f7;
        float f8 = this.f4535u * f7;
        this.x = f8;
        this.z = f8 * 1.25f;
        this.f4536v = true;
    }

    public final void setMinZoom(float f7) {
        this.f4534t = f7;
        if (f7 == -1.0f) {
            ImageView.ScaleType scaleType = this.E;
            if (scaleType == ImageView.ScaleType.CENTER || scaleType == ImageView.ScaleType.CENTER_CROP) {
                Drawable drawable = getDrawable();
                int iJ = j(drawable);
                int i7 = i(drawable);
                if (iJ > 0 && i7 > 0) {
                    float f8 = this.I / iJ;
                    float f9 = this.J / i7;
                    this.f4535u = this.E == ImageView.ScaleType.CENTER ? Math.min(f8, f9) : Math.min(f8, f9) / Math.max(f8, f9);
                }
            } else {
                this.f4535u = 1.0f;
            }
        } else {
            this.f4535u = f7;
        }
        if (this.f4536v) {
            setMaxZoomRatio(this.f4537w);
        }
        this.f4538y = this.f4535u * 0.75f;
    }

    public final void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        w5.a.d(onDoubleTapListener, "onDoubleTapListener");
        this.T = onDoubleTapListener;
    }

    public final void setOnTouchCoordinatesListener(q5.c cVar) {
        w5.a.d(cVar, "onTouchCoordinatesListener");
        this.S = cVar;
    }

    public final void setOnTouchImageViewListener(q5.d dVar) {
        w5.a.d(dVar, "onTouchImageViewListener");
        this.V = dVar;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        w5.a.d(onTouchListener, "onTouchListener");
        this.U = onTouchListener;
    }

    public final void setOrientationChangeFixedPixel(q5.a aVar) {
        this.f4530p = aVar;
    }

    public final void setRotateImageToFitScreen(boolean z) {
        this.f4529o = z;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        w5.a.d(scaleType, "type");
        ImageView.ScaleType scaleType2 = ImageView.ScaleType.MATRIX;
        if (scaleType == scaleType2) {
            super.setScaleType(scaleType2);
            return;
        }
        this.E = scaleType;
        if (this.G) {
            setZoom(this);
        }
    }

    public final void setViewSizeChangeFixedPixel(q5.a aVar) {
        this.f4531q = aVar;
    }

    public final void setZoom(float f7) {
        p(f7, 0.5f, 0.5f, this.E);
    }

    public final void setZoomEnabled(boolean z) {
        this.f4528n = z;
    }

    public final void setZoom(TouchImageView touchImageView) {
        w5.a.d(touchImageView, "img");
        PointF scrollPosition = touchImageView.getScrollPosition();
        p(touchImageView.f4526k, scrollPosition.x, scrollPosition.y, touchImageView.getScaleType());
    }
}
