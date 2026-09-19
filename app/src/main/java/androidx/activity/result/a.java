package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0009a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f288h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Intent f289i;

    /* JADX INFO: renamed from: androidx.activity.result.a$a, reason: collision with other inner class name */
    public static class C0009a implements Parcelable.Creator<a> {
        @Override // android.os.Parcelable.Creator
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public a[] newArray(int i7) {
            return new a[i7];
        }
    }

    public a(int i7, Intent intent) {
        this.f288h = i7;
        this.f289i = intent;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        String strValueOf;
        StringBuilder sbA = android.support.v4.media.b.a("ActivityResult{resultCode=");
        int i7 = this.f288h;
        if (i7 != -1) {
            strValueOf = i7 != 0 ? String.valueOf(i7) : "RESULT_CANCELED";
        } else {
            strValueOf = "RESULT_OK";
        }
        sbA.append(strValueOf);
        sbA.append(", data=");
        sbA.append(this.f289i);
        sbA.append('}');
        return sbA.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f288h);
        parcel.writeInt(this.f289i == null ? 0 : 1);
        Intent intent = this.f289i;
        if (intent != null) {
            intent.writeToParcel(parcel, i7);
        }
    }

    public a(Parcel parcel) {
        this.f288h = parcel.readInt();
        this.f289i = parcel.readInt() == 0 ? null : (Intent) Intent.CREATOR.createFromParcel(parcel);
    }
}
