package p5;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import com.jjoe64.graphview.GraphView;
import java.util.Iterator;
import p5.c;

/* JADX INFO: loaded from: classes.dex */
public class f<E extends p5.c> extends p5.a<E> {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public f<E>.c f17278j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Paint f17279k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public b f17280l;

    public interface b {
    }

    public final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f17281a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f17282b;

        public c(f fVar, a aVar) {
        }
    }

    public f(E[] eArr) {
        super(eArr);
        f<E>.c cVar = new c(this, null);
        this.f17278j = cVar;
        cVar.f17281a = 20.0f;
        Paint paint = new Paint();
        this.f17279k = paint;
        paint.setStrokeCap(Paint.Cap.ROUND);
        this.f17278j.f17282b = 1;
    }

    @Override // p5.g
    public void b(GraphView graphView, Canvas canvas, boolean z) {
        double dB;
        double d8;
        Canvas canvas2;
        Canvas canvas3 = canvas;
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
        double d9 = d8;
        Iterator<E> itC = c(dC, dA);
        this.f17279k.setColor(this.f17258d);
        double d10 = dB - d9;
        double d11 = dA - dC;
        float graphContentHeight = graphView.getGraphContentHeight();
        float graphContentWidth = graphView.getGraphContentWidth();
        float graphContentLeft = graphView.getGraphContentLeft();
        float graphContentTop = graphView.getGraphContentTop();
        while (itC.hasNext()) {
            E next = itC.next();
            double d12 = graphContentHeight;
            double y2 = ((next.getY() - d9) / d10) * d12;
            double x = (next.getX() - dC) / d11;
            double d13 = dC;
            double d14 = graphContentWidth;
            double d15 = x * d14;
            boolean z7 = d15 > d14;
            if (y2 < 0.0d) {
                z7 = true;
            }
            if (y2 > d12) {
                z7 = true;
            }
            if (d15 < 0.0d) {
                z7 = true;
            }
            float f7 = 1.0f + graphContentLeft + ((float) d15);
            float f8 = ((float) (((double) graphContentTop) - y2)) + graphContentHeight;
            j(f7, f8, next);
            if (z7) {
                canvas2 = canvas3;
            } else {
                if (this.f17280l != null) {
                    Paint paint = this.f17279k;
                    paint.setStrokeWidth(5.0f);
                    float f9 = f7 - 10.0f;
                    float f10 = f8 - 10.0f;
                    float f11 = f7 + 10.0f;
                    float f12 = f8 + 10.0f;
                    canvas.drawLine(f9, f10, f11, f12, paint);
                    canvas.drawLine(f11, f10, f9, f12, paint);
                } else {
                    f<E>.c cVar = this.f17278j;
                    int i7 = cVar.f17282b;
                    if (i7 == 1) {
                        canvas3.drawCircle(f7, f8, cVar.f17281a, this.f17279k);
                    } else if (i7 == 3) {
                        float f13 = cVar.f17281a;
                        canvas.drawRect(f7 - f13, f8 - f13, f7 + f13, f8 + f13, this.f17279k);
                    } else if (i7 == 2) {
                        float f14 = this.f17278j.f17281a;
                        double d16 = f8;
                        float f15 = this.f17278j.f17281a;
                        Point[] pointArr = {new Point((int) f7, (int) (f8 - this.f17278j.f17281a)), new Point((int) (f7 + f14), (int) ((((double) f14) * 0.67d) + d16)), new Point((int) (f7 - f15), (int) ((((double) f15) * 0.67d) + d16))};
                        Paint paint2 = this.f17279k;
                        canvas.drawVertices(Canvas.VertexMode.TRIANGLES, 8, new float[]{pointArr[0].x, pointArr[0].y, pointArr[1].x, pointArr[1].y, pointArr[2].x, pointArr[2].y, pointArr[0].x, pointArr[0].y}, 0, null, 0, null, 0, null, 0, 0, paint2);
                        Path path = new Path();
                        path.moveTo(pointArr[0].x, pointArr[0].y);
                        path.lineTo(pointArr[1].x, pointArr[1].y);
                        path.lineTo(pointArr[2].x, pointArr[2].y);
                        canvas2 = canvas;
                        canvas2.drawPath(path, paint2);
                    }
                }
                canvas2 = canvas3;
            }
            canvas3 = canvas2;
            dC = d13;
            graphContentTop = graphContentTop;
            graphContentLeft = graphContentLeft;
            graphContentWidth = graphContentWidth;
            graphContentHeight = graphContentHeight;
        }
    }

    @Override // p5.a
    public void i(GraphView graphView, Canvas canvas, boolean z, p5.c cVar) {
    }
}
