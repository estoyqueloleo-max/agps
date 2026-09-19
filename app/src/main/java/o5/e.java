package o5;

import com.jjoe64.graphview.GraphView;
import java.util.ArrayList;
import java.util.List;
import p5.g;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f17132d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f17130b = new d();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f17131c = new d();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<g> f17129a = new ArrayList();

    public e(GraphView graphView) {
        b bVar = new b();
        this.f17132d = bVar;
        bVar.f17121b = graphView.getViewport();
    }

    public double a(boolean z) {
        return (z ? this.f17130b : this.f17131c).f17127c;
    }

    public double b(boolean z) {
        return (z ? this.f17130b : this.f17131c).f17128d;
    }
}
