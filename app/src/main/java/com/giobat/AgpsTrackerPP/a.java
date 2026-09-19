package com.giobat.AgpsTrackerPP;

import android.os.SystemClock;
import c2.v2;

/* JADX INFO: loaded from: classes.dex */
public class a implements Runnable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ MainActivity.PurchaseStatusListener f3747h;

    public a(MainActivity.PurchaseStatusListener dVar) {
        this.f3747h = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        SystemClock.sleep(10000L);
        v2.e("GPS-B", "Start Ads");
    }
}
