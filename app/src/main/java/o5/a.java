package o5;

import android.R;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.TypedValue;
import com.jjoe64.graphview.GraphView;
import java.util.HashMap;
import java.util.Map;
import p5.g;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f17103a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final GraphView f17104b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f17105c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f17106d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f17107e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Map<p5.a, p5.c> f17108f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f17109g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Paint f17110h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public double f17111i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public b f17112j = new b(null);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f17113k;

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f17114a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f17115b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f17116c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f17117d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f17118e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f17119f;

        public b(Object obj) {
        }
        public b() {
        }
    }

    public a(GraphView graphView) {
        this.f17104b = graphView;
        Paint paint = new Paint();
        this.f17103a = paint;
        paint.setColor(Color.argb(128, 180, 180, 180));
        paint.setStrokeWidth(10.0f);
        this.f17108f = new HashMap();
        this.f17109g = new Paint();
        this.f17110h = new Paint();
        this.f17112j.f17114a = graphView.getGridLabelRenderer().f4489a.f4510a;
        b bVar = this.f17112j;
        float f7 = bVar.f17114a;
        bVar.f17115b = (int) (f7 / 5.0f);
        bVar.f17116c = (int) (f7 / 2.0f);
        bVar.f17117d = Color.argb(180, 100, 100, 100);
        b bVar2 = this.f17112j;
        bVar2.f17118e = (int) bVar2.f17114a;
        TypedValue typedValue = new TypedValue();
        graphView.getContext().getTheme().resolveAttribute(R.attr.textAppearanceSmall, typedValue, true);
        int i7 = -16777216;
        try {
            TypedArray typedArrayObtainStyledAttributes = graphView.getContext().obtainStyledAttributes(typedValue.data, new int[]{R.attr.textColorPrimary});
            int color = typedArrayObtainStyledAttributes.getColor(0, -16777216);
            typedArrayObtainStyledAttributes.recycle();
            i7 = color;
        } catch (Exception unused) {
        }
        this.f17112j.f17119f = i7;
        this.f17113k = 0;
    }

    public void a(Canvas canvas) {
        if (this.f17107e) {
            float f7 = this.f17105c;
            canvas.drawLine(f7, 0.0f, f7, canvas.getHeight(), this.f17103a);
        }
        for (Map.Entry<p5.a, p5.c> entry : this.f17108f.entrySet()) {
            entry.getKey().i(this.f17104b, canvas, false, entry.getValue());
        }
        if (this.f17108f.isEmpty()) {
            return;
        }
        this.f17110h.setTextSize(this.f17112j.f17114a);
        this.f17110h.setColor(this.f17112j.f17119f);
        int i7 = (int) (((double) this.f17112j.f17114a) * 0.8d);
        int iMax = this.f17113k;
        if (iMax == 0) {
            Rect rect = new Rect();
            for (Map.Entry<p5.a, p5.c> entry2 : this.f17108f.entrySet()) {
                String strC = c(entry2.getKey(), entry2.getValue());
                this.f17110h.getTextBounds(strC, 0, strC.length(), rect);
                iMax = Math.max(iMax, rect.width());
            }
            if (iMax == 0) {
                iMax = 1;
            }
            b bVar = this.f17112j;
            iMax += (bVar.f17116c * 2) + i7 + bVar.f17115b;
            this.f17113k = iMax;
        }
        float f8 = this.f17105c;
        b bVar2 = this.f17112j;
        float f9 = iMax;
        float f10 = (f8 - bVar2.f17118e) - f9;
        if (f10 < 0.0f) {
            f10 = 0.0f;
        }
        float size = (bVar2.f17114a + bVar2.f17115b) * (this.f17108f.size() + 1);
        b bVar3 = this.f17112j;
        float f11 = size - bVar3.f17115b;
        float f12 = (this.f17106d - f11) - (bVar3.f17114a * 4.5f);
        float f13 = f12 >= 0.0f ? f12 : 0.0f;
        float f14 = f11 + f13 + (bVar3.f17116c * 2);
        this.f17109g.setColor(bVar3.f17117d);
        canvas.drawRoundRect(new RectF(f10, f13, f9 + f10, f14), 8.0f, 8.0f, this.f17109g);
        this.f17110h.setFakeBoldText(true);
        String strA = this.f17104b.getGridLabelRenderer().f4503p.a(this.f17111i, true);
        b bVar4 = this.f17112j;
        int i8 = bVar4.f17116c;
        canvas.drawText(strA, i8 + f10, (i8 / 2) + f13 + bVar4.f17114a, this.f17110h);
        this.f17110h.setFakeBoldText(false);
        int i9 = 1;
        for (Map.Entry<p5.a, p5.c> entry3 : this.f17108f.entrySet()) {
            this.f17109g.setColor(entry3.getKey().f17258d);
            b bVar5 = this.f17112j;
            float f15 = bVar5.f17116c;
            float f16 = f15 + f10;
            float f17 = i9;
            float f18 = ((bVar5.f17115b + bVar5.f17114a) * f17) + f15 + f13;
            float f19 = i7;
            canvas.drawRect(new RectF(f16, f18, f16 + f19, f18 + f19), this.f17109g);
            String strC2 = c(entry3.getKey(), entry3.getValue());
            b bVar6 = this.f17112j;
            int i10 = bVar6.f17116c;
            float f20 = i10 + f10 + f19;
            float f21 = bVar6.f17115b;
            float f22 = bVar6.f17114a;
            canvas.drawText(strC2, f20 + f21, ((f22 + f21) * f17) + (i10 / 2) + f13 + f22, this.f17110h);
            i9++;
        }
    }

    public final void b() {
        this.f17108f.clear();
        double x = 0.0d;
        for (g gVar : this.f17104b.getSeries()) {
            if (gVar instanceof p5.a) {
                p5.a aVar = (p5.a) gVar;
                float f7 = this.f17105c;
                p5.c cVar = null;
                float f8 = Float.NaN;
                p5.c cVar2 = null;
                for (Map.Entry<?, ?> entry : ((Map<?, ?>) aVar.f17256b).entrySet()) {
                    float fAbs = Math.abs(((PointF) entry.getKey()).x - f7);
                    if (cVar2 == null || fAbs < f8) {
                        cVar2 = (p5.c) entry.getValue();
                        f8 = fAbs;
                    }
                }
                if (cVar2 != null && f8 < 200.0f) {
                    cVar = cVar2;
                }
                if (cVar != null) {
                    x = cVar.getX();
                    this.f17108f.put(aVar, cVar);
                }
            }
        }
        if (this.f17108f.isEmpty()) {
            return;
        }
        this.f17111i = x;
    }

    public String c(g gVar, p5.c cVar) {
        StringBuffer stringBuffer = new StringBuffer();
        if (gVar.getTitle() != null) {
            stringBuffer.append(gVar.getTitle());
            stringBuffer.append(": ");
        }
        stringBuffer.append(this.f17104b.getGridLabelRenderer().f4503p.a(cVar.getY(), false));
        return stringBuffer.toString();
    }
}
