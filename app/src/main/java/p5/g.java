package p5;

import android.graphics.Canvas;
import com.jjoe64.graphview.GraphView;
import java.util.Iterator;
import p5.c;

/* JADX INFO: loaded from: classes.dex */
public interface g<E extends c> {
    double a();

    void b(GraphView graphView, Canvas canvas, boolean z);

    Iterator<E> c(double d8, double d9);

    double d();

    double e();

    void f(float f7, float f8);

    double g();

    String getTitle();

    boolean isEmpty();
}
