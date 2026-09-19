package o5;

import android.R;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import com.jjoe64.graphview.GraphView;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f17122a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final GraphView f17123b;

    public final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f17124a;

        public b(c cVar, a aVar) {
        }
    }

    public c(GraphView graphView) {
        this.f17123b = graphView;
        new Paint().setTextAlign(Paint.Align.LEFT);
        b bVar = new b(this, null);
        this.f17122a = bVar;
        bVar.f17124a = graphView.getGridLabelRenderer().f4489a.f4510a;
        float f7 = this.f17122a.f17124a;
        Color.argb(180, 100, 100, 100);
        float f8 = this.f17122a.f17124a;
        TypedValue typedValue = new TypedValue();
        graphView.getContext().getTheme().resolveAttribute(R.attr.textAppearanceSmall, typedValue, true);
        try {
            TypedArray typedArrayObtainStyledAttributes = graphView.getContext().obtainStyledAttributes(typedValue.data, new int[]{R.attr.textColorPrimary});
            typedArrayObtainStyledAttributes.getColor(0, -16777216);
            typedArrayObtainStyledAttributes.recycle();
        } catch (Exception unused) {
        }
        Objects.requireNonNull(this.f17122a);
    }
}
