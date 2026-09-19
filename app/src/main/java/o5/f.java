package o5;

import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;
import com.jjoe64.graphview.GraphView;
import androidx.core.view.ViewCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f17133a = Double.NaN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double f17134b = Double.NaN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f17135c = new d(Double.NaN, Double.NaN, Double.NaN, Double.NaN);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final GraphView f17136d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d f17137e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public d f17138f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f17139g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f17140h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f17141i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public GestureDetector f17142j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ScaleGestureDetector f17143k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public OverScroller f17144l;
    public n0.c m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public n0.c f17145n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public n0.c f17146o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public n0.c f17147p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17148q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17149r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f17150s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f17151t;

    public class a implements ScaleGestureDetector.OnScaleGestureListener {
        public a() {
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            double dC = f.this.f17137e.c();
            Objects.requireNonNull(f.this);
            f fVar = f.this;
            double d8 = (dC / 2.0d) + fVar.f17137e.f17125a;
            Objects.requireNonNull(fVar);
            double scaleFactor = dC / ((double) scaleGestureDetector.getScaleFactor());
            f fVar2 = f.this;
            d dVar = fVar2.f17137e;
            double d9 = d8 - (scaleFactor / 2.0d);
            dVar.f17125a = d9;
            dVar.f17126b = d9 + scaleFactor;
            double dC2 = fVar2.c(true);
            if (!Double.isNaN(f.this.f17135c.f17125a)) {
                dC2 = Math.min(dC2, f.this.f17135c.f17125a);
            }
            f fVar3 = f.this;
            d dVar2 = fVar3.f17137e;
            if (dVar2.f17125a < dC2) {
                dVar2.f17125a = dC2;
                dVar2.f17126b = dC2 + scaleFactor;
            }
            double dA = fVar3.a(true);
            if (!Double.isNaN(f.this.f17135c.f17126b)) {
                dA = Math.max(dA, f.this.f17135c.f17126b);
            }
            if (scaleFactor == 0.0d) {
                f.this.f17137e.f17126b = dA;
            }
            f fVar4 = f.this;
            d dVar3 = fVar4.f17137e;
            double d10 = dVar3.f17125a;
            double d11 = (d10 + scaleFactor) - dA;
            if (d11 > 0.0d) {
                if (d10 - d11 > dC2) {
                    double d12 = d10 - d11;
                    dVar3.f17125a = d12;
                    dVar3.f17126b = d12 + scaleFactor;
                } else {
                    dVar3.f17125a = dC2;
                    dVar3.f17126b = dA;
                }
            }
            Objects.requireNonNull(fVar4);
            f.this.f17136d.c(true, false);
            GraphView graphView = f.this.f17136d;
            ViewCompat.postInvalidateOnAnimation(graphView);
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            f fVar = f.this;
            if (fVar.f17136d.f4482q || !fVar.f17141i) {
                return false;
            }
            fVar.f17139g = true;
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            f fVar = f.this;
            fVar.f17139g = false;
            Objects.requireNonNull(fVar);
            GraphView graphView = f.this.f17136d;
            ViewCompat.postInvalidateOnAnimation(graphView);
        }
    }

    public class b extends GestureDetector.SimpleOnGestureListener {
        public b() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            f fVar = f.this;
            if (fVar.f17136d.f4482q) {
                return true;
            }
            if (!fVar.f17140h || fVar.f17139g) {
                return false;
            }
            fVar.f17146o.e();
            fVar.f17147p.e();
            fVar.m.e();
            fVar.f17145n.e();
            f.this.f17144l.forceFinished(true);
            GraphView graphView = f.this.f17136d;
            ViewCompat.postInvalidateOnAnimation(graphView);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f7, float f8) {
            return true;
        }

        /* JADX WARN: Code duplicated, block: B:59:0x0198 A[PHI: r7
          0x0198: PHI (r7v14 double) = (r7v13 double), (r7v20 double) binds: [B:58:0x0196, B:55:0x0186] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:60:0x019b  */
        /* JADX WARN: Code duplicated, block: B:72:0x01d6  */
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f7, float f8) {
            double d8;
            double d9;
            double d10;
            double d11;
            f fVar = f.this;
            if (fVar.f17136d.f4482q) {
                return true;
            }
            if (!fVar.f17140h || fVar.f17139g) {
                return false;
            }
            double dC = (fVar.f17137e.c() * ((double) f7)) / ((double) f.this.f17136d.getGraphContentWidth());
            double d12 = f8;
            double dA = (f.this.f17137e.a() * d12) / ((double) f.this.f17136d.getGraphContentHeight());
            f fVar2 = f.this;
            double dMin = fVar2.f17138f.f17125a;
            if (!Double.isNaN(fVar2.f17135c.f17125a)) {
                dMin = Math.min(dMin, f.this.f17135c.f17125a);
            }
            f fVar3 = f.this;
            double dMax = fVar3.f17138f.f17126b;
            if (!Double.isNaN(fVar3.f17135c.f17126b)) {
                dMax = Math.max(dMax, f.this.f17135c.f17126b);
            }
            double d13 = dMax - dMin;
            f fVar4 = f.this;
            double dMin2 = fVar4.f17138f.f17128d;
            double d14 = dMax;
            if (!Double.isNaN(fVar4.f17135c.f17128d)) {
                dMin2 = Math.min(dMin2, f.this.f17135c.f17128d);
            }
            f fVar5 = f.this;
            double dMax2 = fVar5.f17138f.f17127c;
            if (!Double.isNaN(fVar5.f17135c.f17127c)) {
                dMax2 = Math.max(dMax2, f.this.f17135c.f17127c);
            }
            double d15 = dMax2 - dMin2;
            double d16 = dMax2;
            int iC = (int) ((d13 / f.this.f17137e.c()) * ((double) f.this.f17136d.getGraphContentWidth()));
            int iA = (int) ((d15 / f.this.f17137e.a()) * ((double) f.this.f17136d.getGraphContentHeight()));
            double d17 = iC;
            f fVar6 = f.this;
            d dVar = fVar6.f17137e;
            double d18 = dVar.f17125a;
            int i7 = (int) ((((d18 + dC) - dMin) * d17) / d13);
            double d19 = dVar.f17128d;
            int i8 = (int) ((-1.0d) * ((((d19 + dA) - dMin2) * ((double) iA)) / d15));
            boolean z = d18 > dMin || dVar.f17126b < d14;
            boolean z7 = d19 > dMin2 || dVar.f17127c < d16;
            e eVar = fVar6.f17136d.m;
            boolean z8 = eVar != null;
            if (z8) {
                double dA2 = (eVar.f17131c.a() * d12) / ((double) f.this.f17136d.getGraphContentHeight());
                e eVar2 = f.this.f17136d.m;
                d dVar2 = eVar2.f17131c;
                double d20 = dVar2.f17128d;
                d dVar3 = eVar2.f17130b;
                z7 |= d20 > dVar3.f17128d || dVar2.f17127c < dVar3.f17127c;
                d8 = dA2;
            } else {
                d8 = 0.0d;
            }
            Objects.requireNonNull(f.this);
            boolean z9 = z7 & false;
            if (z) {
                if (dC < 0.0d) {
                    d10 = (f.this.f17137e.f17125a + dC) - dMin;
                    if (d10 < 0.0d) {
                        d11 = dC - d10;
                    } else {
                        d11 = dC;
                    }
                } else {
                    d10 = (f.this.f17137e.f17126b + dC) - d14;
                    if (d10 > 0.0d) {
                        d11 = dC - d10;
                    } else {
                        d11 = dC;
                    }
                }
                d dVar4 = f.this.f17137e;
                dVar4.f17125a += d11;
                dVar4.f17126b += d11;
            }
            if (z9) {
                if (z8) {
                    d9 = dA;
                } else if (dA < 0.0d) {
                    double d21 = (f.this.f17137e.f17128d + dA) - dMin2;
                    if (d21 < 0.0d) {
                        d9 = dA - d21;
                    } else {
                        d9 = dA;
                    }
                } else {
                    double d22 = (f.this.f17137e.f17127c + dA) - d16;
                    if (d22 > 0.0d) {
                        d9 = dA - d22;
                    } else {
                        d9 = dA;
                    }
                }
                f fVar7 = f.this;
                d dVar5 = fVar7.f17137e;
                dVar5.f17127c += d9;
                dVar5.f17128d += d9;
                if (z8) {
                    d dVar6 = fVar7.f17136d.m.f17131c;
                    dVar6.f17127c += d8;
                    dVar6.f17128d += d8;
                }
            }
            if (z && i7 < 0) {
                f fVar8 = f.this;
                fVar8.f17146o.c(i7 / fVar8.f17136d.getGraphContentWidth());
            }
            if (!z8 && z9 && i8 < 0) {
                f fVar9 = f.this;
                fVar9.f17145n.c(i8 / fVar9.f17136d.getGraphContentHeight());
            }
            if (z && i7 > iC - f.this.f17136d.getGraphContentWidth()) {
                f fVar10 = f.this;
                fVar10.f17147p.c((fVar10.f17136d.getGraphContentWidth() + (i7 - iC)) / f.this.f17136d.getGraphContentWidth());
            }
            if (!z8 && z9 && i8 > iA - f.this.f17136d.getGraphContentHeight()) {
                f fVar11 = f.this;
                fVar11.m.c((fVar11.f17136d.getGraphContentHeight() + (i8 - iA)) / f.this.f17136d.getGraphContentHeight());
            }
            f.this.f17136d.c(true, false);
            GraphView graphView = f.this.f17136d;
            ViewCompat.postInvalidateOnAnimation(graphView);
            return true;
        }
    }

    public f(GraphView graphView) {
        a aVar = new a();
        b bVar = new b();
        this.f17137e = new d();
        this.f17138f = new d();
        this.f17144l = new OverScroller(graphView.getContext());
        this.m = new n0.c(graphView.getContext());
        this.f17145n = new n0.c(graphView.getContext());
        this.f17146o = new n0.c(graphView.getContext());
        this.f17147p = new n0.c(graphView.getContext());
        this.f17142j = new GestureDetector(graphView.getContext(), bVar);
        this.f17143k = new ScaleGestureDetector(graphView.getContext(), aVar);
        this.f17136d = graphView;
        this.f17148q = 1;
        this.f17149r = 1;
        new Paint();
    }

    public double a(boolean z) {
        return z ? this.f17138f.f17126b : this.f17137e.f17126b;
    }

    public double b(boolean z) {
        return z ? this.f17138f.f17127c : this.f17137e.f17127c;
    }

    public double c(boolean z) {
        return z ? this.f17138f.f17125a : this.f17137e.f17125a;
    }

    public double d(boolean z) {
        return z ? this.f17138f.f17128d : this.f17137e.f17128d;
    }
}
