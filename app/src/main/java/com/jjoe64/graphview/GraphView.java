package com.jjoe64.graphview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import androidx.core.view.ViewCompat;
import o5.d;
import o5.e;
import o5.f;
import org.xmlpull.v1.XmlPullParser;
import p5.g;

/* JADX INFO: loaded from: classes.dex */
public class GraphView extends View {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public List<g> f4474h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public com.jjoe64.graphview.a f4475i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public f f4476j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f4477k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public b f4478l;
    public e m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public c f4479n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public o5.c f4480o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Paint f4481p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f4482q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Paint f4483r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public o5.a f4484s;

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f4485a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4486b;

        public b(a aVar) {
        }
    }

    public class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f4487a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public PointF f4488b;

        public c(GraphView graphView, a aVar) {
        }
    }

    public GraphView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.f4483r = paint;
        paint.setTextAlign(Paint.Align.CENTER);
        this.f4483r.setColor(-16777216);
        this.f4483r.setTextSize(50.0f);
        this.f4478l = new b(null);
        this.f4476j = new f(this);
        this.f4475i = new com.jjoe64.graphview.a(this);
        this.f4480o = new o5.c(this);
        this.f4474h = new ArrayList();
        this.f4481p = new Paint();
        this.f4479n = new c(this, null);
        b bVar = this.f4478l;
        com.jjoe64.graphview.a.C0052a c0052a = this.f4475i.f4489a;
        bVar.f4486b = c0052a.f4515f;
        bVar.f4485a = c0052a.f4510a;
    }

    public void a(g gVar) {
        ((p5.a) gVar).f17262h.add(new WeakReference<>(this));
        this.f4474h.add(gVar);
        c(false, false);
    }

    /* JADX WARN: Code duplicated, block: B:237:0x05e6  */
    /* JADX WARN: Code duplicated, block: B:240:0x05f2  */
    /* JADX WARN: Code duplicated, block: B:243:0x0626  */
    /* JADX WARN: Code duplicated, block: B:244:0x062a  */
    /* JADX WARN: Code duplicated, block: B:247:0x0648  */
    /* JADX WARN: Code duplicated, block: B:250:0x065a  */
    /* JADX WARN: Code duplicated, block: B:277:0x0712  */
    /* JADX WARN: Code duplicated, block: B:278:0x0729  */
    /* JADX WARN: Code duplicated, block: B:281:0x073b  */
    /* JADX WARN: Code duplicated, block: B:283:0x0749  */
    /* JADX WARN: Code duplicated, block: B:284:0x074d  */
    /* JADX WARN: Code duplicated, block: B:286:0x0751  */
    /* JADX WARN: Code duplicated, block: B:287:0x0754  */
    /* JADX WARN: Code duplicated, block: B:290:0x0770  */
    /* JADX WARN: Code duplicated, block: B:294:0x0789 A[LOOP:9: B:292:0x0786->B:294:0x0789, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:419:0x0675 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:423:0x07a3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:60:0x0259  */
    public void b(Canvas canvas) {
        boolean z = false;
        com.jjoe64.graphview.a aVar = null;
        Canvas canvas2 = null;
        char c8 = 0;
        boolean z7 = false;
        int iIntValue = 0;
        com.jjoe64.graphview.a.C0052a c0052a = null;
        Paint.Align align = null;
        int i7 = 0;
        int iC = 0;
        String strA = null;
        String[] strArrSplit = null;
        float length = 0.0f;
        int i8 = 0;
        boolean z8 = false;
        com.jjoe64.graphview.a aVar2 = null;
        double d8 = 0.0d;
        double dDoubleValue = 0.0d;
        boolean z9 = false;
        double dDoubleValue2 = 0.0d;
        double d9 = 0.0d;
        f viewport = null;
        double d10 = 0.0d;
        double dFloor = 0.0d;
        int iC2 = 0;
        Map<Integer, Double> map = null;
        double graphContentWidth = 0.0d;
        int i9 = 0;
        double d11 = 0.0d;
        double dDoubleValue3 = 0.0d;
        double d12 = 0.0d;
        boolean zDraw = false;
        if (!canvas.isHardwareAccelerated()) {
            Log.w("GraphView", "GraphView should be used in hardware accelerated mode.You can use android:hardwareAccelerated=\"true\" on your activity. Read this for more info:https://developer.android.com/guide/topics/graphics/hardware-accel.html");
        }
        String str = this.f4477k;
        if (str != null && str.length() > 0) {
            this.f4481p.setColor(this.f4478l.f4486b);
            this.f4481p.setTextSize(this.f4478l.f4485a);
            this.f4481p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.f4477k, canvas.getWidth() / 2, this.f4481p.getTextSize(), this.f4481p);
        }
        Objects.requireNonNull(this.f4476j);
        com.jjoe64.graphview.a aVar3 = this.f4475i;
        Integer num = aVar3.f4501n;
        String str2 = XmlPullParser.NO_NAMESPACE;
        boolean z10 = false;
        if (num == null) {
            String strA2 = aVar3.f4503p.a(((aVar3.f4490b.getViewport().a(false) - aVar3.f4490b.getViewport().c(false)) * 0.783d) + aVar3.f4490b.getViewport().c(false), true);
            if (strA2 == null) {
                strA2 = XmlPullParser.NO_NAMESPACE;
            }
            Rect rect = new Rect();
            aVar3.f4495g.getTextBounds(strA2, 0, strA2.length(), rect);
            aVar3.f4501n = Integer.valueOf(rect.width());
            aVar3.f4502o = Integer.valueOf(rect.height());
            int i10 = 1;
            for (byte b8 : strA2.getBytes()) {
                if (b8 == 10) {
                    i10++;
                }
            }
            Integer numValueOf = Integer.valueOf(aVar3.f4502o.intValue() * i10);
            aVar3.f4502o = numValueOf;
            aVar3.f4502o = Integer.valueOf((int) Math.max(numValueOf.intValue(), aVar3.f4489a.f4510a));
            Objects.requireNonNull(aVar3.f4489a);
            aVar3.f4502o = Integer.valueOf(aVar3.f4502o.intValue() + aVar3.f4489a.f4525q);
            z = true;
        } else {
            z = false;
        }
        if (aVar3.f4498j == null) {
            String strA3 = aVar3.f4503p.a(aVar3.f4490b.getViewport().b(false), false);
            if (strA3 == null) {
                strA3 = XmlPullParser.NO_NAMESPACE;
            }
            Rect rect2 = new Rect();
            aVar3.f4495g.getTextBounds(strA3, 0, strA3.length(), rect2);
            aVar3.f4498j = Integer.valueOf(rect2.width());
            aVar3.f4499k = Integer.valueOf(rect2.height());
            String strA4 = aVar3.f4503p.a(aVar3.f4490b.getViewport().d(false), false);
            if (strA4 == null) {
                strA4 = XmlPullParser.NO_NAMESPACE;
            }
            aVar3.f4495g.getTextBounds(strA4, 0, strA4.length(), rect2);
            Integer numValueOf2 = Integer.valueOf(Math.max(aVar3.f4498j.intValue(), rect2.width()));
            aVar3.f4498j = numValueOf2;
            Integer numValueOf3 = Integer.valueOf(numValueOf2.intValue() + 6);
            aVar3.f4498j = numValueOf3;
            aVar3.f4498j = Integer.valueOf(numValueOf3.intValue() + aVar3.f4489a.f4525q);
            int i11 = 1;
            for (byte b9 : strA4.getBytes()) {
                if (b9 == 10) {
                    i11++;
                }
            }
            aVar3.f4499k = Integer.valueOf(aVar3.f4499k.intValue() * i11);
            z = true;
        }
        if (aVar3.f4500l == null) {
            e eVar = aVar3.f4490b.m;
            if (eVar == null) {
                aVar3.f4500l = 0;
                aVar3.m = 0;
            } else {
                String strA5 = aVar3.f4490b.m.f17132d.a(aVar3.f4490b.m.b(false) + ((eVar.a(false) - aVar3.f4490b.m.b(false)) * 0.783d), false);
                Rect rect3 = new Rect();
                aVar3.f4495g.getTextBounds(strA5, 0, strA5.length(), rect3);
                aVar3.f4500l = Integer.valueOf(rect3.width());
                aVar3.m = Integer.valueOf(rect3.height());
                int i12 = 1;
                for (byte b10 : strA5.getBytes()) {
                    if (b10 == 10) {
                        i12++;
                    }
                }
                aVar3.m = Integer.valueOf(aVar3.m.intValue() * i12);
            }
            z = true;
        }
        if (z) {
            aVar3.f4490b.b(canvas);
            canvas2 = canvas;
        } else {
            if (aVar3.f4497i) {
                aVar = aVar3;
                str2 = XmlPullParser.NO_NAMESPACE;
            } else {
                boolean z11 = !r.g.b(3, aVar3.f4490b.getViewport().f17149r);
                if (aVar3.f4502o == null) {
                    z8 = false;
                } else {
                    double d13 = aVar3.f4490b.getViewport().d(false);
                    double dB = aVar3.f4490b.getViewport().b(false);
                    if (d13 == dB) {
                        z8 = false;
                    } else {
                        int i13 = aVar3.f4506s;
                        double d14 = i13 - 1;
                        double dRound = Math.round(((dB - d13) / d14) * 1000000.0d) / 1000000.0d;
                        if (dRound == 0.0d) {
                            dB = (d14 * 1.0E-7d) + d13;
                            dRound = 1.0E-7d;
                        }
                        double d15 = dB;
                        if (aVar3.f4508u) {
                            dRound = aVar3.d(dRound, z11);
                        } else {
                            Map<Integer, Double> map2 = aVar3.f4491c;
                            if (map2 != null && map2.size() > 1) {
                                Iterator<Double> it = aVar3.f4491c.values().iterator();
                                int i14 = 0;
                                double dDoubleValue4 = 0.0d;
                                while (true) {
                                    if (!it.hasNext()) {
                                        dDoubleValue3 = 0.0d;
                                        break;
                                    }
                                    Double next = it.next();
                                    if (i14 != 0) {
                                        dDoubleValue3 = next.doubleValue();
                                        break;
                                    } else {
                                        dDoubleValue4 = next.doubleValue();
                                        i14++;
                                    }
                                }
                                double d16 = dDoubleValue3 - dDoubleValue4;
                                if (d16 > 0.0d) {
                                    dRound = d16 > dRound ? d16 / 2.0d : d16 < dRound ? d16 * 2.0d : Double.NaN;
                                    double d17 = d15 - d13;
                                    int i15 = (int) (d17 / d16);
                                    int i16 = (int) (d17 / dRound);
                                    boolean z12 = i15 > i13 || i16 > i13 || i16 > i15;
                                    if (dRound == Double.NaN || !z12 || i16 > i13) {
                                        dRound = d16;
                                    }
                                }
                            }
                        }
                        f viewport2 = aVar3.f4490b.getViewport();
                        if (!viewport2.f17151t || viewport2.f17136d.getGridLabelRenderer().f4508u) {
                            d12 = 0.0d;
                        } else {
                            if (Double.isNaN(viewport2.f17133a)) {
                                viewport2.f17133a = viewport2.d(false);
                            }
                            d12 = viewport2.f17133a;
                        }
                        double dFloor2 = (Math.floor((d13 - d12) / dRound) * dRound) + d12;
                        if (z11) {
                            aVar3.f4490b.getViewport().f17137e.f17128d = dFloor2;
                            aVar3.f4490b.getViewport().f17137e.f17127c = Math.max(d15, (d14 * dRound) + dFloor2);
                            aVar3.f4490b.getViewport().f17149r = 2;
                        }
                        int iA = ((int) ((aVar3.f4490b.getViewport().f17137e.a() * (-1.0d)) / dRound)) + 2;
                        Map<Integer, Double> map3 = aVar3.f4491c;
                        if (map3 != null) {
                            map3.clear();
                        } else {
                            aVar3.f4491c = new LinkedHashMap(iA);
                        }
                        double graphContentHeight = (((double) aVar3.f4490b.getGraphContentHeight()) / aVar3.f4490b.getViewport().f17137e.a()) * (-1.0d);
                        int i17 = 0;
                        while (i17 < iA) {
                            double d18 = (((double) i17) * dRound) + dFloor2;
                            double d19 = dRound;
                            if (d18 <= aVar3.f4490b.getViewport().f17137e.f17127c && d18 >= aVar3.f4490b.getViewport().f17137e.f17128d) {
                                aVar3.f4491c.put(Integer.valueOf((int) ((d18 - aVar3.f4490b.getViewport().f17137e.f17128d) * graphContentHeight)), Double.valueOf(d18));
                            }
                            i17++;
                            dRound = d19;
                        }
                        z8 = true;
                    }
                }
                aVar3.f4497i = z8;
                if (aVar3.f4502o == null) {
                    aVar = aVar3;
                    z9 = false;
                } else {
                    e eVar2 = aVar3.f4490b.m;
                    if (eVar2 != null) {
                        double dB2 = eVar2.b(false);
                        double dA = aVar3.f4490b.m.a(false);
                        int i18 = aVar3.f4506s;
                        Objects.requireNonNull(aVar3.f4490b.m);
                        double d20 = dA - dB2;
                        double dRound2 = Math.round((d20 / ((double) (i18 - 1))) * 1000000.0d) / 1000000.0d;
                        Map<Integer, Double> map4 = aVar3.f4492d;
                        if (map4 != null && map4.size() > 1) {
                            Iterator<Double> it2 = aVar3.f4492d.values().iterator();
                            int i19 = 0;
                            double dDoubleValue5 = 0.0d;
                            while (true) {
                                if (!it2.hasNext()) {
                                    dDoubleValue = 0.0d;
                                    break;
                                }
                                Double next2 = it2.next();
                                if (i19 != 0) {
                                    dDoubleValue = next2.doubleValue();
                                    break;
                                } else {
                                    dDoubleValue5 = next2.doubleValue();
                                    i19++;
                                }
                            }
                            double d21 = dDoubleValue - dDoubleValue5;
                            if (d21 > 0.0d) {
                                dRound2 = d21 > dRound2 ? d21 / 2.0d : d21 < dRound2 ? d21 * 2.0d : Double.NaN;
                                int i20 = (int) (d20 / d21);
                                int i21 = (int) (d20 / dRound2);
                                boolean z13 = i20 > i18 || i21 > i18 || i21 > i20;
                                if (dRound2 == Double.NaN || !z13 || i21 > i18) {
                                    dRound2 = d21;
                                }
                            }
                        }
                        Objects.requireNonNull(aVar3.f4490b.getSecondScale());
                        double dFloor3 = (Math.floor((dB2 - Double.NaN) / dRound2) * dRound2) + Double.NaN;
                        int iMax = Math.max(((int) ((aVar3.f4490b.getSecondScale().f17131c.a() * (-1.0d)) / dRound2)) + 2, 2);
                        Map<Integer, Double> map5 = aVar3.f4492d;
                        if (map5 != null) {
                            map5.clear();
                        } else {
                            aVar3.f4492d = new LinkedHashMap(iMax);
                        }
                        double graphContentHeight2 = (((double) aVar3.f4490b.getGraphContentHeight()) / aVar3.f4490b.getSecondScale().f17131c.a()) * (-1.0d);
                        int i22 = 0;
                        while (i22 < iMax) {
                            double d22 = (((double) i22) * dRound2) + dFloor3;
                            com.jjoe64.graphview.a aVar4 = aVar3;
                            if (d22 > aVar3.f4490b.getSecondScale().f17131c.f17127c) {
                                aVar2 = aVar4;
                                d8 = dFloor3;
                            } else {
                                aVar2 = aVar4;
                                d8 = dFloor3;
                                if (d22 >= aVar2.f4490b.getSecondScale().f17131c.f17128d) {
                                    aVar2.f4492d.put(Integer.valueOf((int) ((d22 - aVar2.f4490b.getSecondScale().f17131c.f17128d) * graphContentHeight2)), Double.valueOf(d22));
                                }
                            }
                            i22++;
                            aVar3 = aVar2;
                            dFloor3 = d8;
                        }
                    }
                    aVar = aVar3;
                    z9 = true;
                }
                boolean z14 = z8 & z9;
                aVar.f4497i = z14;
                boolean z15 = !r.g.b(3, aVar.f4490b.getViewport().f17148q);
                if (aVar.f4498j != null) {
                    double dC = aVar.f4490b.getViewport().c(false);
                    double dA2 = aVar.f4490b.getViewport().a(false);
                    if (dC != dA2) {
                        int i23 = aVar.f4507t;
                        double d23 = i23 - 1;
                        double dRound3 = Math.round(((dA2 - dC) / d23) * 1000000.0d) / 1000000.0d;
                        if (dRound3 == 0.0d) {
                            dA2 = (d23 * 1.0E-7d) + dC;
                            dRound3 = 1.0E-7d;
                        }
                        if (aVar.f4509v) {
                            dRound3 = aVar.d(dRound3, false);
                        } else {
                            Map<Integer, Double> map6 = aVar.f4493e;
                            if (map6 != null && map6.size() > 1) {
                                Iterator<Double> it3 = aVar.f4493e.values().iterator();
                                int i24 = 0;
                                double dDoubleValue6 = 0.0d;
                                while (true) {
                                    if (!it3.hasNext()) {
                                        dDoubleValue2 = 0.0d;
                                        break;
                                    }
                                    Double next3 = it3.next();
                                    if (i24 != 0) {
                                        dDoubleValue2 = next3.doubleValue();
                                        break;
                                    } else {
                                        dDoubleValue6 = next3.doubleValue();
                                        i24++;
                                    }
                                }
                                double d24 = dDoubleValue2 - dDoubleValue6;
                                if (d24 > 0.0d) {
                                    dRound3 = d24 > dRound3 ? d24 / 2.0d : d24 < dRound3 ? 2.0d * d24 : Double.NaN;
                                    double d25 = dA2 - dC;
                                    d9 = d23;
                                    int i25 = (int) (d25 / d24);
                                    int i26 = (int) (d25 / dRound3);
                                    boolean z16 = i25 > i23 || i26 > i23 || i26 > i25;
                                    if (dRound3 == Double.NaN || !z16 || i26 > i23) {
                                        dRound3 = d24;
                                    }
                                }
                            }
                        }
                        d9 = d23;
                        viewport = aVar.f4490b.getViewport();
                        if (viewport.f17150s || viewport.f17136d.getGridLabelRenderer().f4509v) {
                            d10 = 0.0d;
                        } else {
                            if (Double.isNaN(viewport.f17134b)) {
                                viewport.f17134b = viewport.c(false);
                            }
                            d10 = viewport.f17134b;
                        }
                        if (dRound3 <= 0.0d) {
                            dRound3 = 1.0d;
                        }
                        dFloor = (Math.floor((dC - d10) / dRound3) * dRound3) + d10;
                        if (z15) {
                            aVar.f4490b.getViewport().f17137e.f17125a = dFloor;
                            aVar.f4490b.getViewport().f17137e.f17126b = (d9 * dRound3) + dFloor;
                            aVar.f4490b.getViewport().f17148q = 2;
                        }
                        double viewportSpan = aVar.f4490b.getViewport().f17137e.c();
                        if (viewportSpan <= 0.0d) {
                            viewportSpan = 1.0d;
                        }
                        iC2 = ((int) (viewportSpan / dRound3)) + 1;
                        if (iC2 <= 0 || iC2 > 100) {
                            iC2 = Math.max(2, i23);
                        }
                        map = aVar.f4493e;
                        if (map != null) {
                            map.clear();
                        } else {
                            aVar.f4493e = new LinkedHashMap(iC2);
                        }
                        graphContentWidth = ((double) aVar.f4490b.getGraphContentWidth()) / viewportSpan;
                        for (i9 = 0; i9 < iC2; i9++) {
                            d11 = (((double) i9) * dRound3) + dFloor;
                            if (d11 >= aVar.f4490b.getViewport().f17137e.f17125a) {
                                aVar.f4493e.put(Integer.valueOf((int) ((d11 - aVar.f4490b.getViewport().f17137e.f17125a) * graphContentWidth)), Double.valueOf(d11));
                            }
                        }
                        z10 = true;
                    }
                }
                aVar.f4497i = z14 & z10;
            }
            if (aVar.f4497i) {
                float graphContentLeft = aVar.f4490b.getGraphContentLeft();
                aVar.f4495g.setColor(aVar.f4489a.f4513d);
                aVar.f4495g.setTextAlign(aVar.f4489a.f4511b);
                aVar.f4491c.size();
                for (Map.Entry<Integer, Double> entry : aVar.f4491c.entrySet()) {
                    float graphContentHeight3 = (aVar.f4490b.getGraphContentHeight() + aVar.f4490b.getGraphContentTop()) - entry.getKey().intValue();
                    if (aVar.f4489a.f4517h) {
                        if (entry.getValue().doubleValue() == 0.0d) {
                            aVar.f4494f.setStrokeWidth(5.0f);
                        } else {
                            aVar.f4494f.setStrokeWidth(0.0f);
                        }
                    }
                    int i27 = aVar.f4489a.f4524p;
                    r.g.a(i27);
                    z7 = i27 != 2 && i27 != 4;
                    if (z7) {
                        canvas.drawLine(graphContentLeft, graphContentHeight3, graphContentLeft + aVar.f4490b.getGraphContentWidth(), graphContentHeight3, aVar.f4494f);
                    }
                    if (aVar.f4489a.f4523o) {
                        iIntValue = aVar.f4498j != null ? aVar.f4498j.intValue() : 0;
                        c0052a = aVar.f4489a;
                        align = c0052a.f4511b;
                        if (align == Paint.Align.RIGHT) {
                            i7 = iIntValue - c0052a.f4525q;
                        } else if (align == Paint.Align.CENTER) {
                            i7 = iIntValue / 2;
                        } else {
                            i7 = 0;
                        }
                        iC = aVar.c() + c0052a.f4518i + i7;
                        strA = aVar.f4503p.a(entry.getValue().doubleValue(), false);
                        if (strA == null) {
                            strA = str2;
                        }
                        strArrSplit = strA.split("\n");
                        length = (((strArrSplit.length * aVar.f4489a.f4510a) * 1.1f) / 2.0f) + graphContentHeight3;
                        for (i8 = 0; i8 < strArrSplit.length; i8++) {
                            canvas.drawText(strArrSplit[i8], iC, length - ((((strArrSplit.length - i8) - 1) * aVar.f4489a.f4510a) * 1.1f), aVar.f4495g);
                        }
                    }
                }
                canvas2 = canvas;
                GraphView graphView = aVar.f4490b;
                if (graphView.m != null) {
                    float graphContentWidth2 = aVar.f4490b.getGraphContentWidth() + graphView.getGraphContentLeft();
                    aVar.f4495g.setColor(aVar.f4489a.f4514e);
                    aVar.f4495g.setTextAlign(aVar.f4489a.f4512c);
                    for (Map.Entry<Integer, Double> entry2 : aVar.f4492d.entrySet()) {
                        float graphContentHeight4 = (aVar.f4490b.getGraphContentHeight() + aVar.f4490b.getGraphContentTop()) - entry2.getKey().intValue();
                        int iIntValue2 = aVar.f4500l.intValue();
                        int i28 = (int) graphContentWidth2;
                        Paint.Align align2 = aVar.f4489a.f4512c;
                        if (align2 == Paint.Align.RIGHT) {
                            i28 += iIntValue2;
                        } else if (align2 == Paint.Align.CENTER) {
                            iIntValue2 /= 2;
                            i28 += iIntValue2;
                        }
                        String[] strArrSplit2 = aVar.f4490b.m.f17132d.a(entry2.getValue().doubleValue(), false).split("\n");
                        char c9 = 0;
                        float length2 = (((strArrSplit2.length * aVar.f4489a.f4510a) * 1.1f) / 2.0f) + graphContentHeight4;
                        int i29 = 0;
                        while (i29 < strArrSplit2.length) {
                            canvas2.drawText(strArrSplit2[i29], i28, length2 - ((((strArrSplit2.length - i29) - 1) * aVar.f4489a.f4510a) * 1.1f), aVar.f4495g);
                            i29++;
                            c9 = 0;
                        }
                    }
                }
                aVar.f4495g.setColor(aVar.f4489a.f4515f);
                int i30 = 0;
                for (Map.Entry<Integer, Double> entry3 : aVar.f4493e.entrySet()) {
                    if (aVar.f4489a.f4517h) {
                        if (entry3.getValue().doubleValue() == 0.0d) {
                            aVar.f4494f.setStrokeWidth(5.0f);
                        } else {
                            aVar.f4494f.setStrokeWidth(0.0f);
                        }
                    }
                    int i31 = aVar.f4489a.f4524p;
                    r.g.a(i31);
                    if ((i31 == 1 || (i31 == 2 && i31 != 4)) && entry3.getKey().intValue() <= aVar.f4490b.getGraphContentWidth()) {
                        canvas.drawLine(entry3.getKey().intValue() + aVar.f4490b.getGraphContentLeft(), aVar.f4490b.getGraphContentTop(), entry3.getKey().intValue() + aVar.f4490b.getGraphContentLeft(), aVar.f4490b.getGraphContentHeight() + aVar.f4490b.getGraphContentTop(), aVar.f4494f);
                    }
                    if (aVar.f4489a.f4522n) {
                        aVar.f4495g.setTextAlign(Paint.Align.CENTER);
                        if (i30 == aVar.f4493e.size() - 1) {
                            aVar.f4495g.setTextAlign(Paint.Align.RIGHT);
                        }
                        if (i30 == 0) {
                            aVar.f4495g.setTextAlign(Paint.Align.LEFT);
                        }
                        String strA6 = aVar.f4503p.a(entry3.getValue().doubleValue(), true);
                        if (strA6 == null) {
                            strA6 = str2;
                        }
                        String[] strArrSplit3 = strA6.split("\n");
                        Objects.requireNonNull(aVar.f4489a);
                        for (int i32 = 0; i32 < strArrSplit3.length; i32++) {
                            float height = (canvas.getHeight() - aVar.f4489a.f4518i) - aVar.a();
                            float length3 = (strArrSplit3.length - i32) - 1;
                            com.jjoe64.graphview.a.C0052a c0052a2 = aVar.f4489a;
                            float f7 = (height - ((length3 * c0052a2.f4510a) * 1.1f)) + c0052a2.f4525q;
                            float fIntValue = entry3.getKey().intValue() + aVar.f4490b.getGraphContentLeft();
                            Objects.requireNonNull(aVar.f4489a);
                            Objects.requireNonNull(aVar.f4489a);
                            canvas2.drawText(strArrSplit3[i32], fIntValue, f7, aVar.f4495g);
                        }
                    }
                    i30++;
                }
                String str3 = aVar.f4504q;
                if (str3 != null && str3.length() > 0) {
                    aVar.f4496h.setColor(aVar.f4489a.m);
                    aVar.f4496h.setTextSize(aVar.f4489a.f4521l);
                    canvas2.drawText(aVar.f4504q, canvas.getWidth() / 2, canvas.getHeight() - aVar.f4489a.f4518i, aVar.f4496h);
                }
                String str4 = aVar.f4505r;
                if (str4 != null && str4.length() > 0) {
                    aVar.f4496h.setColor(aVar.f4489a.f4520k);
                    aVar.f4496h.setTextSize(aVar.f4489a.f4519j);
                    float fC = aVar.c();
                    float height2 = canvas.getHeight() / 2;
                    canvas.save();
                    canvas2.rotate(-90.0f, fC, height2);
                    canvas2.drawText(aVar.f4505r, fC, height2, aVar.f4496h);
                    canvas.restore();
                }
                e eVar3 = aVar.f4490b.m;
            } else {
                canvas2 = canvas;
            }
        }
        Iterator<g> it4 = this.f4474h.iterator();
        while (it4.hasNext()) {
            it4.next().b(this, canvas2, false);
        }
        e eVar4 = this.m;
        if (eVar4 != null) {
            Iterator<g> it5 = eVar4.f17129a.iterator();
            while (it5.hasNext()) {
                it5.next().b(this, canvas2, true);
            }
        }
        o5.a aVar5 = this.f4484s;
        if (aVar5 != null) {
            aVar5.a(canvas2);
        }
        f fVar = this.f4476j;
        if (fVar.m.b()) {
            zDraw = false;
        } else {
            int iSave = canvas.save();
            canvas2.translate(fVar.f17136d.getGraphContentLeft(), fVar.f17136d.getGraphContentTop());
            fVar.m.f16786a.setSize(fVar.f17136d.getGraphContentWidth(), fVar.f17136d.getGraphContentHeight());
            zDraw = fVar.m.f16786a.draw(canvas2);
            canvas2.restoreToCount(iSave);
        }
        if (!fVar.f17145n.b()) {
            int iSave2 = canvas.save();
            canvas2.translate(fVar.f17136d.getGraphContentLeft(), fVar.f17136d.getGraphContentHeight() + fVar.f17136d.getGraphContentTop());
            canvas2.rotate(180.0f, fVar.f17136d.getGraphContentWidth() / 2, 0.0f);
            fVar.f17145n.f16786a.setSize(fVar.f17136d.getGraphContentWidth(), fVar.f17136d.getGraphContentHeight());
            if (fVar.f17145n.f16786a.draw(canvas2)) {
                zDraw = true;
            }
            canvas2.restoreToCount(iSave2);
        }
        if (!fVar.f17146o.b()) {
            int iSave3 = canvas.save();
            canvas2.translate(fVar.f17136d.getGraphContentLeft(), fVar.f17136d.getGraphContentHeight() + fVar.f17136d.getGraphContentTop());
            canvas2.rotate(-90.0f, 0.0f, 0.0f);
            fVar.f17146o.f16786a.setSize(fVar.f17136d.getGraphContentHeight(), fVar.f17136d.getGraphContentWidth());
            if (fVar.f17146o.f16786a.draw(canvas2)) {
                zDraw = true;
            }
            canvas2.restoreToCount(iSave3);
        }
        if (!fVar.f17147p.b()) {
            int iSave4 = canvas.save();
            canvas2.translate(fVar.f17136d.getGraphContentWidth() + fVar.f17136d.getGraphContentLeft(), fVar.f17136d.getGraphContentTop());
            canvas2.rotate(90.0f, 0.0f, 0.0f);
            fVar.f17147p.f16786a.setSize(fVar.f17136d.getGraphContentHeight(), fVar.f17136d.getGraphContentWidth());
            boolean z17 = fVar.f17147p.f16786a.draw(canvas2) ? true : zDraw;
            canvas2.restoreToCount(iSave4);
            zDraw = z17;
        }
        if (zDraw) {
            GraphView graphView2 = fVar.f17136d;
            ViewCompat.postInvalidateOnAnimation(graphView2);
        }
        Objects.requireNonNull(this.f4480o);
    }

    public void c(boolean z, boolean z7) {
        f fVar = this.f4476j;
        List<g> series = fVar.f17136d.getSeries();
        ArrayList<g> arrayList = new ArrayList(fVar.f17136d.getSeries());
        e eVar = fVar.f17136d.m;
        if (eVar != null) {
            arrayList.addAll(eVar.f17129a);
        }
        fVar.f17138f.b(0.0d, 0.0d, 0.0d, 0.0d);
        if (!arrayList.isEmpty() && !((g) arrayList.get(0)).isEmpty()) {
            double dG = ((g) arrayList.get(0)).g();
            for (g gVar : arrayList) {
                if (!gVar.isEmpty() && dG > gVar.g()) {
                    dG = gVar.g();
                }
            }
            fVar.f17138f.f17125a = dG;
            double dA = ((g) arrayList.get(0)).a();
            for (g gVar2 : arrayList) {
                if (!gVar2.isEmpty() && dA < gVar2.a()) {
                    dA = gVar2.a();
                }
            }
            fVar.f17138f.f17126b = dA;
            if (!series.isEmpty() && !series.get(0).isEmpty()) {
                double dE = series.get(0).e();
                for (g gVar3 : series) {
                    if (!gVar3.isEmpty() && dE > gVar3.e()) {
                        dE = gVar3.e();
                    }
                }
                fVar.f17138f.f17128d = dE;
                double d8 = series.get(0).d();
                for (g gVar4 : series) {
                    if (!gVar4.isEmpty() && d8 < gVar4.d()) {
                        d8 = gVar4.d();
                    }
                }
                fVar.f17138f.f17127c = d8;
            }
        }
        if (fVar.f17149r == 2) {
            fVar.f17149r = 1;
        }
        if (fVar.f17149r == 1) {
            d dVar = fVar.f17137e;
            d dVar2 = fVar.f17138f;
            dVar.f17127c = dVar2.f17127c;
            dVar.f17128d = dVar2.f17128d;
        }
        if (fVar.f17148q == 2) {
            fVar.f17148q = 1;
        }
        if (fVar.f17148q == 1) {
            d dVar3 = fVar.f17137e;
            d dVar4 = fVar.f17138f;
            dVar3.f17125a = dVar4.f17125a;
            dVar3.f17126b = dVar4.f17126b;
        } else if (fVar.f17150s && !fVar.f17151t && fVar.f17138f.c() != 0.0d) {
            double d9 = Double.MAX_VALUE;
            for (g gVar5 : series) {
                d dVar5 = fVar.f17137e;
                Iterator itC = gVar5.c(dVar5.f17125a, dVar5.f17126b);
                while (itC.hasNext()) {
                    double y2 = ((p5.c) itC.next()).getY();
                    if (d9 > y2) {
                        d9 = y2;
                    }
                }
            }
            if (d9 != Double.MAX_VALUE) {
                fVar.f17137e.f17128d = d9;
            }
            double d10 = Double.MIN_VALUE;
            for (g gVar6 : series) {
                d dVar6 = fVar.f17137e;
                Iterator itC2 = gVar6.c(dVar6.f17125a, dVar6.f17126b);
                while (itC2.hasNext()) {
                    double y7 = ((p5.c) itC2.next()).getY();
                    if (d10 < y7) {
                        d10 = y7;
                    }
                }
            }
            if (d10 != Double.MIN_VALUE) {
                fVar.f17137e.f17127c = d10;
            }
        }
        d dVar7 = fVar.f17137e;
        double d11 = dVar7.f17125a;
        double d12 = dVar7.f17126b;
        if (d11 == d12) {
            dVar7.f17126b = d12 + 1.0d;
        }
        double d13 = dVar7.f17127c;
        if (d13 == dVar7.f17128d) {
            dVar7.f17127c = d13 + 1.0d;
        }
        e eVar2 = this.m;
        if (eVar2 != null) {
            List<g> list = eVar2.f17129a;
            eVar2.f17130b.b(0.0d, 0.0d, 0.0d, 0.0d);
            if (!list.isEmpty() && !list.get(0).isEmpty()) {
                double dG2 = list.get(0).g();
                for (g gVar7 : list) {
                    if (!gVar7.isEmpty() && dG2 > gVar7.g()) {
                        dG2 = gVar7.g();
                    }
                }
                eVar2.f17130b.f17125a = dG2;
                double dA2 = list.get(0).a();
                for (g gVar8 : list) {
                    if (!gVar8.isEmpty() && dA2 < gVar8.a()) {
                        dA2 = gVar8.a();
                    }
                }
                eVar2.f17130b.f17126b = dA2;
                if (!list.isEmpty() && !list.get(0).isEmpty()) {
                    double dE2 = list.get(0).e();
                    for (g gVar9 : list) {
                        if (!gVar9.isEmpty() && dE2 > gVar9.e()) {
                            dE2 = gVar9.e();
                        }
                    }
                    eVar2.f17130b.f17128d = dE2;
                    double d14 = list.get(0).d();
                    for (g gVar10 : list) {
                        if (!gVar10.isEmpty() && d14 < gVar10.d()) {
                            d14 = gVar10.d();
                        }
                    }
                    eVar2.f17130b.f17127c = d14;
                }
            }
        }
        com.jjoe64.graphview.a aVar = this.f4475i;
        Objects.requireNonNull(aVar);
        if (!z7) {
            aVar.f4497i = false;
        }
        if (!z) {
            aVar.f4498j = null;
            aVar.f4499k = null;
            aVar.f4500l = null;
            aVar.m = null;
        }
        postInvalidate();
    }

    @Override // android.view.View
    public void computeScroll() {
        super.computeScroll();
        Objects.requireNonNull(this.f4476j);
    }

    public o5.a getCursorMode() {
        return this.f4484s;
    }

    public int getGraphContentHeight() {
        int height = getHeight() - (getGridLabelRenderer().f4489a.f4518i * 2);
        com.jjoe64.graphview.a gridLabelRenderer = getGridLabelRenderer();
        Integer num = gridLabelRenderer.f4502o;
        return ((height - ((num == null || !gridLabelRenderer.f4489a.f4522n) ? 0 : num.intValue())) - getTitleHeight()) - getGridLabelRenderer().a();
    }

    public int getGraphContentLeft() {
        return getGridLabelRenderer().c() + getGridLabelRenderer().b() + getGridLabelRenderer().f4489a.f4518i;
    }

    public int getGraphContentTop() {
        return getTitleHeight() + getGridLabelRenderer().f4489a.f4518i;
    }

    public int getGraphContentWidth() {
        int width = (getWidth() - (getGridLabelRenderer().f4489a.f4518i * 2)) - getGridLabelRenderer().b();
        if (this.m == null) {
            return width;
        }
        Integer num = getGridLabelRenderer().f4500l;
        float fIntValue = width - (num == null ? 0 : num.intValue());
        Objects.requireNonNull(this.m);
        return (int) (fIntValue - 0.0f);
    }

    public com.jjoe64.graphview.a getGridLabelRenderer() {
        return this.f4475i;
    }

    public o5.c getLegendRenderer() {
        return this.f4480o;
    }

    public e getSecondScale() {
        if (this.m == null) {
            this.m = new e(this);
            float f7 = this.f4475i.f4489a.f4510a;
        }
        return this.m;
    }

    public List<g> getSeries() {
        return this.f4474h;
    }

    public String getTitle() {
        return this.f4477k;
    }

    public int getTitleColor() {
        return this.f4478l.f4486b;
    }

    public int getTitleHeight() {
        String str = this.f4477k;
        if (str == null || str.length() <= 0) {
            return 0;
        }
        return (int) this.f4481p.getTextSize();
    }

    public float getTitleTextSize() {
        return this.f4478l.f4485a;
    }

    public f getViewport() {
        return this.f4476j;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            b(canvas);
        } else {
            canvas.drawColor(Color.rgb(200, 200, 200));
            canvas.drawText("GraphView: No Preview available", canvas.getWidth() / 2, canvas.getHeight() / 2, this.f4483r);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        c(false, false);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x012d  */
    /* JADX WARN: Code duplicated, block: B:38:0x0139 A[LOOP:0: B:36:0x0133->B:38:0x0139, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:41:0x014f  */
    /* JADX WARN: Code duplicated, block: B:44:0x015b A[LOOP:1: B:42:0x0155->B:44:0x015b, LOOP_END] */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        Iterator<g> it;
        e eVar;
        Iterator<g> it2;
        f fVar = this.f4476j;
        boolean zOnTouchEvent = fVar.f17143k.onTouchEvent(motionEvent) | fVar.f17142j.onTouchEvent(motionEvent);
        if (fVar.f17136d.f4482q) {
            if (motionEvent.getAction() == 0) {
                o5.a cursorMode = fVar.f17136d.getCursorMode();
                Objects.requireNonNull(cursorMode);
                float fMax = Math.max(motionEvent.getX(), cursorMode.f17104b.getGraphContentLeft());
                cursorMode.f17105c = fMax;
                cursorMode.f17105c = Math.min(fMax, cursorMode.f17104b.getGraphContentWidth() + cursorMode.f17104b.getGraphContentLeft());
                cursorMode.f17106d = motionEvent.getY();
                cursorMode.f17107e = true;
                cursorMode.b();
                cursorMode.f17104b.invalidate();
                zOnTouchEvent |= true;
            }
            if (motionEvent.getAction() == 2) {
                o5.a cursorMode2 = fVar.f17136d.getCursorMode();
                if (cursorMode2.f17107e) {
                    float fMax2 = Math.max(motionEvent.getX(), cursorMode2.f17104b.getGraphContentLeft());
                    cursorMode2.f17105c = fMax2;
                    cursorMode2.f17105c = Math.min(fMax2, cursorMode2.f17104b.getGraphContentWidth() + cursorMode2.f17104b.getGraphContentLeft());
                    cursorMode2.f17106d = motionEvent.getY();
                    cursorMode2.b();
                    cursorMode2.f17104b.invalidate();
                }
                zOnTouchEvent |= true;
            }
            if (motionEvent.getAction() == 1) {
                o5.a cursorMode3 = fVar.f17136d.getCursorMode();
                cursorMode3.f17107e = false;
                cursorMode3.b();
                cursorMode3.f17104b.invalidate();
                zOnTouchEvent |= true;
            }
        }
        boolean zOnTouchEvent2 = super.onTouchEvent(motionEvent);
        c cVar = this.f4479n;
        Objects.requireNonNull(cVar);
        z = false;
        if (motionEvent.getAction() == 0) {
            cVar.f4487a = System.currentTimeMillis();
            cVar.f4488b = new PointF(motionEvent.getX(), motionEvent.getY());
        } else if (cVar.f4487a > 0 && motionEvent.getAction() == 2) {
            if (Math.abs(motionEvent.getX() - cVar.f4488b.x) > 60.0f || Math.abs(motionEvent.getY() - cVar.f4488b.y) > 60.0f) {
                cVar.f4487a = 0L;
            }
        } else if (motionEvent.getAction() == 1 && System.currentTimeMillis() - cVar.f4487a < 400) {
            z = true;
        }
        if (z) {
            for (g gVar : this.f4474h) {
                gVar.f(motionEvent.getX(), motionEvent.getY());
            }
            eVar = this.m;
            if (eVar != null) {
                for (g gVar2 : eVar.f17129a) {
                    gVar2.f(motionEvent.getX(), motionEvent.getY());
                }
            }
        }
        return zOnTouchEvent || zOnTouchEvent2;
    }

    public void setCursorMode(boolean z) {
        this.f4482q = z;
        if (!z) {
            this.f4484s = null;
            invalidate();
        } else if (this.f4484s == null) {
            this.f4484s = new o5.a(this);
        }
        for (g gVar : this.f4474h) {
            if (gVar instanceof p5.a) {
                ((p5.a) gVar).f17263i = null;
            }
        }
    }

    public void setLegendRenderer(o5.c cVar) {
        this.f4480o = cVar;
    }

    public void setTitle(String str) {
        this.f4477k = str;
    }

    public void setTitleColor(int i7) {
        this.f4478l.f4486b = i7;
    }

    public void setTitleTextSize(float f7) {
        this.f4478l.f4485a = f7;
    }
}
