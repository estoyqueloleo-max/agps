package d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<I, O> {

    /* JADX INFO: renamed from: d.a$a, reason: collision with other inner class name */
    public static final class C0053a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @SuppressLint({"UnknownNullness"})
        public final T f4558a;

        public C0053a(@SuppressLint({"UnknownNullness"}) T t7) {
            this.f4558a = t7;
        }
    }

    public abstract Intent a(Context context, @SuppressLint({"UnknownNullness"}) I i7);

    public C0053a<O> b(Context context, @SuppressLint({"UnknownNullness"}) I i7) {
        return null;
    }

    @SuppressLint({"UnknownNullness"})
    public abstract O c(int i7, Intent intent);
}
