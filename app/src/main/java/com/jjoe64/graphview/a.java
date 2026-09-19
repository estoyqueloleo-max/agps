package com.jjoe64.graphview;

import android.R;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.TypedValue;
import java.util.Map;
import java.util.Objects;
import o5.b;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0052a f4489a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final GraphView f4490b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Map<Integer, Double> f4491c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Map<Integer, Double> f4492d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Map<Integer, Double> f4493e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Paint f4494f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Paint f4495g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Paint f4496h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4497i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Integer f4498j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Integer f4499k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Integer f4500l;
    public Integer m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Integer f4501n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Integer f4502o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public b f4503p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f4504q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f4505r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f4506s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f4507t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f4508u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f4509v;

    /* JADX INFO: renamed from: com.jjoe64.graphview.a$a, reason: collision with other inner class name */
    public final class C0052a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f4510a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Paint.Align f4511b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Paint.Align f4512c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4513d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f4514e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f4515f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f4516g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f4517h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f4518i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f4519j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f4520k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f4521l;
        public int m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f4522n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f4523o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f4524p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f4525q;

        public C0052a(a aVar) {
        }
    }

    public a(GraphView graphView) {
        int dimensionPixelSize;
        this.f4490b = graphView;
        b bVar = new b();
        this.f4503p = bVar;
        bVar.f17121b = graphView.getViewport();
        this.f4489a = new C0052a(this);
        TypedValue typedValue = new TypedValue();
        graphView.getContext().getTheme().resolveAttribute(R.attr.textAppearanceSmall, typedValue, true);
        int i7 = -7829368;
        int i8 = -16777216;
        int i9 = 20;
        try {
            TypedArray typedArrayObtainStyledAttributes = graphView.getContext().obtainStyledAttributes(typedValue.data, new int[]{R.attr.textColorPrimary, R.attr.textColorSecondary, R.attr.textSize, R.attr.horizontalGap});
            int color = typedArrayObtainStyledAttributes.getColor(0, -16777216);
            int color2 = typedArrayObtainStyledAttributes.getColor(1, -7829368);
            int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, 20);
            dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, 20);
            typedArrayObtainStyledAttributes.recycle();
            i8 = color;
            i7 = color2;
            i9 = dimensionPixelSize2;
        } catch (Exception unused) {
            dimensionPixelSize = 20;
        }
        C0052a c0052a = this.f4489a;
        c0052a.f4513d = i8;
        c0052a.f4514e = i8;
        c0052a.f4515f = i8;
        c0052a.f4516g = i7;
        float f7 = i9;
        c0052a.f4510a = f7;
        c0052a.f4518i = dimensionPixelSize;
        c0052a.f4525q = ((int) f7) / 5;
        c0052a.f4511b = Paint.Align.RIGHT;
        c0052a.f4512c = Paint.Align.LEFT;
        c0052a.f4517h = true;
        c0052a.f4520k = i8;
        c0052a.m = i8;
        c0052a.f4519j = f7;
        c0052a.f4521l = f7;
        c0052a.f4522n = true;
        c0052a.f4523o = true;
        c0052a.f4524p = 1;
        Paint paint = new Paint();
        this.f4494f = paint;
        paint.setColor(this.f4489a.f4516g);
        this.f4494f.setStrokeWidth(0.0f);
        Paint paint2 = new Paint();
        this.f4495g = paint2;
        paint2.setTextSize(this.f4489a.f4510a);
        this.f4495g.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.f4496h = paint3;
        paint3.setTextSize(this.f4489a.f4510a);
        this.f4496h.setTextAlign(Paint.Align.CENTER);
        this.f4506s = 5;
        this.f4507t = 5;
        this.f4509v = true;
        this.f4508u = true;
    }

    public int a() {
        String str = this.f4504q;
        if (str == null || str.length() <= 0) {
            return 0;
        }
        return (int) this.f4489a.f4521l;
    }

    public int b() {
        Objects.requireNonNull(this.f4489a);
        Objects.requireNonNull(this.f4489a);
        Integer num = this.f4498j;
        if (num == null || !this.f4489a.f4523o) {
            return 0;
        }
        return num.intValue();
    }

    public int c() {
        String str = this.f4505r;
        if (str == null || str.length() <= 0) {
            return 0;
        }
        return (int) this.f4489a.f4519j;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0045  */
    /* JADX WARN: Code duplicated, block: B:31:0x0050  */
    /* JADX WARN: Code duplicated, block: B:34:0x0058  */
    public double d(double d8, boolean z) {
        int i7 = 0;
        while (Math.abs(d8) >= 10.0d) {
            d8 /= 10.0d;
            i7++;
        }
        while (Math.abs(d8) < 1.0d) {
            d8 *= 10.0d;
            i7--;
        }
        if (z) {
            if (d8 != 1.0d) {
                if (d8 <= 2.0d) {
                    d8 = 2.0d;
                } else if (d8 <= 5.0d) {
                    d8 = 5.0d;
                } else if (d8 < 10.0d) {
                    d8 = 10.0d;
                }
            }
        } else if (d8 != 1.0d) {
            if (d8 <= 4.9d) {
                d8 = 2.0d;
            } else if (d8 <= 9.9d) {
                d8 = 5.0d;
            } else if (d8 < 15.0d) {
                d8 = 10.0d;
            }
        }
        return Math.pow(10.0d, i7) * d8;
    }
}
