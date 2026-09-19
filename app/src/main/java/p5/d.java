package p5;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.animation.AccelerateInterpolator;
import com.jjoe64.graphview.GraphView;
import java.util.Iterator;
import java.util.Objects;
import p5.c;

/* JADX INFO: loaded from: classes.dex */
public class d<E extends c> extends p5.a<E> {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public d<E>.b f17271j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Paint f17272k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Paint f17273l;
    public Paint m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Path f17274n;

    public final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f17275a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f17276b = 10.0f;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f17277c = Color.argb(100, 172, 218, 255);

        public b(d dVar, a aVar) {
        }
    }

    public d(E[] eArr) {
        super(eArr);
        this.f17271j = new b(this, null);
        Paint paint = new Paint();
        this.f17273l = paint;
        paint.setStrokeCap(Paint.Cap.ROUND);
        this.f17273l.setStyle(Paint.Style.STROKE);
        this.m = new Paint();
        Paint paint2 = new Paint();
        this.f17272k = paint2;
        paint2.setColor(Color.argb(80, 0, 0, 0));
        this.f17272k.setStyle(Paint.Style.FILL);
        new Path();
        this.f17274n = new Path();
        new AccelerateInterpolator(2.0f);
    }

    @Override // p5.g
    public void b(GraphView graphView, Canvas canvas, boolean z) {
        double dB;
        double d8;
        double d9;
        float f7;
        double d10;
        boolean z7;
        boolean z8;
        boolean z9;
        float f8 = 0.0f;
        float f9 = 0.0f;
        char c8;
        char c9;
        this.f17256b.clear();
        double dA = graphView.getViewport().a(false);
        double dC = graphView.getViewport().c(false);
        if (z) {
            dB = graphView.getSecondScale().a(false);
            d8 = graphView.getSecondScale().b(false);
        } else {
            dB = graphView.getViewport().b(false);
            d8 = graphView.getViewport().d(false);
        }
        Iterator<E> itC = c(dC, dA);
        Paint paint = this.f17273l;
        Objects.requireNonNull(this.f17271j);
        paint.setStrokeWidth(5);
        this.f17273l.setColor(this.f17258d);
        this.m.setColor(this.f17271j.f17277c);
        Paint paint2 = this.f17273l;
        this.f17274n.reset();
        Objects.requireNonNull(this.f17271j);
        double d11 = dB - d8;
        double d12 = dA - dC;
        float graphContentHeight = graphView.getGraphContentHeight();
        float graphContentWidth = graphView.getGraphContentWidth();
        float graphContentLeft = graphView.getGraphContentLeft();
        float graphContentTop = graphView.getGraphContentTop();
        float f10 = Float.NaN;
        float fMin = 0.0f;
        float fMax = 0.0f;
        double d13 = 0.0d;
        double d14 = 0.0d;
        int i7 = 0;
        boolean z10 = false;
        while (itC.hasNext()) {
            Iterator<E> it = itC;
            E next = itC.next();
            double y2 = (next.getY() - d8) / d11;
            double d15 = d11;
            double d16 = graphContentHeight;
            double d17 = y2 * d16;
            double x = (next.getX() - dC) / d12;
            double d18 = d12;
            double d19 = graphContentWidth;
            double d20 = dC;
            double d21 = d19 * x;
            if (i7 > 0) {
                if (d21 > d19) {
                    d10 = (((d17 - d14) * (d19 - d13)) / (d21 - d13)) + d14;
                    z7 = true;
                } else {
                    d19 = d21;
                    d10 = d17;
                    z7 = false;
                }
                if (d10 < 0.0d) {
                    if (d14 < 0.0d) {
                        z9 = true;
                    } else {
                        d19 = (((d19 - d13) * (0.0d - d14)) / (d10 - d14)) + d13;
                        z9 = false;
                    }
                    d10 = 0.0d;
                    z8 = true;
                } else {
                    z8 = z7;
                    z9 = false;
                }
                if (d10 > d16) {
                    if (d14 > d16) {
                        z9 = true;
                    } else {
                        d19 = (((d19 - d13) * (d16 - d14)) / (d10 - d14)) + d13;
                    }
                    d10 = d16;
                    z8 = true;
                }
                if (d13 < 0.0d) {
                    d14 = d10 - (((d10 - d14) * (0.0d - d19)) / (d13 - d19));
                    d13 = 0.0d;
                }
                float f11 = graphContentLeft + 1.0f;
                if (d14 < 0.0d) {
                    if (!z9) {
                        d13 = d19 - (((d19 - d13) * (0.0d - d10)) / (d14 - d10));
                    }
                    d14 = 0.0d;
                }
                if (d14 <= d16) {
                    d16 = d14;
                } else if (!z9) {
                    d13 = d19 - (((d19 - d13) * (d16 - d10)) / (d14 - d10));
                }
                double d22 = d8;
                double d23 = d13;
                d9 = d22;
                float f12 = ((float) d23) + f11;
                f7 = graphContentLeft;
                double d24 = graphContentTop;
                float f13 = ((float) (d24 - d16)) + graphContentHeight;
                float f14 = ((float) d19) + f11;
                float f15 = ((float) (d24 - d10)) + graphContentHeight;
                if (f14 < f12) {
                    z9 = true;
                }
                if (z9 || Float.isNaN(f13) || Float.isNaN(f15)) {
                    f8 = fMin;
                    f9 = fMax;
                } else {
                    if (!z8) {
                        if (this.f17271j.f17275a) {
                            Paint.Style style = paint2.getStyle();
                            paint2.setStyle(Paint.Style.FILL);
                            canvas.drawCircle(f14, f15, this.f17271j.f17276b, paint2);
                            paint2.setStyle(style);
                        }
                        j(f14, f15, next);
                    }
                    if (Float.isNaN(f10) || Math.abs(f14 - f10) > 0.3f) {
                        f8 = fMin;
                        f9 = fMax;
                        if (z10) {
                            c8 = 0;
                            c9 = 3;
                            l(canvas, new float[]{f10, f8, f10, f9}, paint2);
                            z10 = false;
                        } else {
                            c8 = 0;
                            c9 = 3;
                        }
                        float[] fArr = new float[4];
                        fArr[c8] = f12;
                        fArr[1] = f13;
                        fArr[2] = f14;
                        fArr[c9] = f15;
                        l(canvas, fArr, paint2);
                        f10 = f14;
                    } else if (z10) {
                        fMin = Math.min(fMin, f15);
                        fMax = Math.max(fMax, f15);
                    } else {
                        fMin = Math.min(f13, f15);
                        fMax = Math.max(f13, f15);
                        z10 = true;
                    }
                    Objects.requireNonNull(this.f17271j);
                }
                fMin = f8;
                fMax = f9;
                Objects.requireNonNull(this.f17271j);
            } else {
                d9 = d8;
                f7 = graphContentLeft;
                float f16 = fMin;
                float f17 = fMax;
                if (this.f17271j.f17275a) {
                    float f18 = f7 + 1.0f + ((float) d21);
                    float f19 = ((float) (((double) graphContentTop) - d17)) + graphContentHeight;
                    if (f18 >= f7 && f19 <= graphContentTop + graphContentHeight) {
                        Paint.Style style2 = paint2.getStyle();
                        paint2.setStyle(Paint.Style.FILL);
                        canvas.drawCircle(f18, f19, this.f17271j.f17276b, paint2);
                        paint2.setStyle(style2);
                        j(f18, f19, next);
                    }
                }
                fMin = f16;
                fMax = f17;
            }
            i7++;
            itC = it;
            graphContentWidth = graphContentWidth;
            d8 = d9;
            graphContentLeft = f7;
            d14 = d17;
            d11 = d15;
            d12 = d18;
            d13 = d21;
            dC = d20;
        }
        Objects.requireNonNull(this.f17271j);
    }

    @Override // p5.a
    public void i(GraphView graphView, Canvas canvas, boolean z, c cVar) {
        double dA = graphView.getViewport().a(false) - graphView.getViewport().c(false);
        double graphContentWidth = graphView.getGraphContentWidth();
        double dB = graphView.getViewport().b(false) - graphView.getViewport().d(false);
        double graphContentHeight = graphView.getGraphContentHeight();
        double x = (((cVar.getX() - graphView.getViewport().c(false)) * graphContentWidth) / dA) + ((double) graphView.getGraphContentLeft());
        float f7 = (float) x;
        float graphContentTop = (float) ((((double) graphView.getGraphContentTop()) + graphContentHeight) - (((cVar.getY() - graphView.getViewport().d(false)) * graphContentHeight) / dB));
        canvas.drawCircle(f7, graphContentTop, 30.0f, this.f17272k);
        Paint.Style style = this.f17273l.getStyle();
        this.f17273l.setStyle(Paint.Style.FILL);
        canvas.drawCircle(f7, graphContentTop, 23.0f, this.f17273l);
        this.f17273l.setStyle(style);
    }

    public final void l(Canvas canvas, float[] fArr, Paint paint) {
        if (fArr.length == 4 && fArr[0] == fArr[2] && fArr[1] == fArr[3]) {
            return;
        }
        canvas.drawLines(fArr, paint);
    }
}
