package p5;

import android.graphics.Canvas;
import android.graphics.PointF;
import com.jjoe64.graphview.GraphView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import p5.c;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<E extends c> implements g<E> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f17257c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public e f17261g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Boolean f17263i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<E> f17255a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Map<PointF, E> f17256b = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f17258d = -16746548;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f17259e = Double.NaN;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public double f17260f = Double.NaN;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public List<WeakReference<GraphView>> f17262h = new ArrayList();

    /* JADX INFO: renamed from: p5.a$a, reason: collision with other inner class name */
    public class C0085a implements Iterator<E> {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Iterator<E> f17264h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public E f17265i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public E f17266j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f17267k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ double f17268l;
        public final /* synthetic */ double m;

        public C0085a(a aVar, double d8, double d9) {
            this.f17268l = d8;
            this.m = d9;
            Iterator<E> it = aVar.f17255a.iterator();
            this.f17264h = it;
            this.f17265i = null;
            this.f17266j = null;
            boolean z = true;
            this.f17267k = true;
            E next = it.hasNext() ? this.f17264h.next() : null;
            if (next != null) {
                if (next.getX() < d8) {
                    while (true) {
                        if (!this.f17264h.hasNext()) {
                            z = false;
                            break;
                        }
                        E next2 = this.f17264h.next();
                        this.f17265i = next2;
                        if (next2.getX() >= this.f17268l) {
                            this.f17266j = this.f17265i;
                            this.f17265i = next;
                            break;
                        }
                        next = this.f17265i;
                    }
                } else {
                    this.f17265i = next;
                }
            } else {
                z = false;
            }
            if (z) {
                return;
            }
            this.f17265i = null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            E e8 = this.f17265i;
            return e8 != null && (e8.getX() <= this.m || this.f17267k);
        }

        @Override // java.util.Iterator
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E e8 = this.f17265i;
            if (e8.getX() > this.m) {
                this.f17267k = false;
            }
            E e9 = this.f17266j;
            if (e9 != null) {
                this.f17265i = e9;
                this.f17266j = null;
            } else if (this.f17264h.hasNext()) {
                this.f17265i = this.f17264h.next();
            } else {
                this.f17265i = null;
            }
            return e8;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public a() {
    }

    @Override // p5.g
    public double a() {
        if (this.f17255a.isEmpty()) {
            return 0.0d;
        }
        List<E> list = this.f17255a;
        return list.get(list.size() - 1).getX();
    }

    @Override // p5.g
    public Iterator<E> c(double d8, double d9) {
        return (d8 > g() || d9 < a()) ? new C0085a(this, d8, d9) : this.f17255a.iterator();
    }

    @Override // p5.g
    public double d() {
        if (this.f17255a.isEmpty()) {
            return 0.0d;
        }
        if (!Double.isNaN(this.f17260f)) {
            return this.f17260f;
        }
        double y2 = this.f17255a.get(0).getY();
        for (int i7 = 1; i7 < this.f17255a.size(); i7++) {
            double y7 = this.f17255a.get(i7).getY();
            if (y2 < y7) {
                y2 = y7;
            }
        }
        this.f17260f = y2;
        return y2;
    }

    @Override // p5.g
    public double e() {
        if (this.f17255a.isEmpty()) {
            return 0.0d;
        }
        if (!Double.isNaN(this.f17259e)) {
            return this.f17259e;
        }
        double y2 = this.f17255a.get(0).getY();
        for (int i7 = 1; i7 < this.f17255a.size(); i7++) {
            double y7 = this.f17255a.get(i7).getY();
            if (y2 > y7) {
                y2 = y7;
            }
        }
        this.f17259e = y2;
        return y2;
    }

    @Override // p5.g
    public void f(float f7, float f8) {
        if (this.f17261g != null) {
            E e8 = null;
            float f9 = Float.NaN;
            E value = null;
            for (Map.Entry<PointF, E> entry : this.f17256b.entrySet()) {
                float f10 = entry.getKey().x - f7;
                float f11 = entry.getKey().y - f8;
                float fSqrt = (float) Math.sqrt((f11 * f11) + (f10 * f10));
                if (value == null || fSqrt < f9) {
                    value = entry.getValue();
                    f9 = fSqrt;
                }
            }
            if (value != null && f9 < 120.0f) {
                e8 = value;
            }
            if (e8 != null) {
                this.f17261g.a(this, e8);
            }
        }
    }

    @Override // p5.g
    public double g() {
        if (this.f17255a.isEmpty()) {
            return 0.0d;
        }
        return this.f17255a.get(0).getX();
    }

    @Override // p5.g
    public String getTitle() {
        return this.f17257c;
    }

    public void h(c cVar) {
        if (this.f17255a.size() > 1) {
            double x = this.f17255a.get(0).getX();
            for (int i7 = 1; i7 < this.f17255a.size(); i7++) {
                if (this.f17255a.get(i7).getX() != Double.NaN) {
                    if (x > this.f17255a.get(i7).getX()) {
                        throw new IllegalArgumentException("The order of the values is not correct. X-Values have to be ordered ASC. First the lowest x value and at least the highest x value.");
                    }
                    x = this.f17255a.get(i7).getX();
                }
            }
        }
    }

    public abstract void i(GraphView graphView, Canvas canvas, boolean z, c cVar);

    @Override // p5.g
    public boolean isEmpty() {
        return this.f17255a.isEmpty();
    }

    public void j(float f7, float f8, E e8) {
        boolean zBooleanValue;
        if (this.f17261g == null) {
            Boolean bool = this.f17263i;
            if (bool == null) {
                Iterator<WeakReference<GraphView>> it = this.f17262h.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        this.f17263i = Boolean.FALSE;
                        zBooleanValue = false;
                        break;
                    }
                    WeakReference<GraphView> next = it.next();
                    if (next != null && next.get() != null && next.get().f4482q) {
                        this.f17263i = Boolean.TRUE;
                        zBooleanValue = true;
                        break;
                    }
                }
            } else {
                zBooleanValue = bool.booleanValue();
            }
            if (!zBooleanValue) {
                return;
            }
        }
        this.f17256b.put(new PointF(f7, f8), e8);
    }

    public void k(E[] eArr) {
        this.f17255a.clear();
        for (E e8 : eArr) {
            this.f17255a.add(e8);
        }
        h(null);
        this.f17259e = Double.NaN;
        this.f17260f = Double.NaN;
        for (WeakReference<GraphView> weakReference : this.f17262h) {
            if (weakReference != null && weakReference.get() != null) {
                weakReference.get().c(true, false);
            }
        }
    }

    public a(E[] eArr) {
        for (E e8 : eArr) {
            this.f17255a.add(e8);
        }
        h(null);
    }
}
