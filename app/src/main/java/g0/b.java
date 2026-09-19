package g0;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5274a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f5275b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5276c;

    public interface a {
        void a();
    }

    public void a() {
        synchronized (this) {
            if (this.f5274a) {
                return;
            }
            this.f5274a = true;
            this.f5276c = true;
            a aVar = this.f5275b;
            if (aVar != null) {
                try {
                    aVar.a();
                } catch (Throwable th) {
                    synchronized (this) {
                        this.f5276c = false;
                        notifyAll();
                        throw th;
                    }
                }
            }
            synchronized (this) {
                this.f5276c = false;
                notifyAll();
            }
        }
    }

    public void b(a aVar) {
        synchronized (this) {
            while (this.f5276c) {
                try {
                    wait();
                } catch (InterruptedException unused) {
                }
            }
            if (this.f5275b == aVar) {
                return;
            }
            this.f5275b = aVar;
            if (this.f5274a) {
                aVar.a();
            }
        }
    }
}
