package com.giobat.AgpsTrackerPP;

import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.widget.d;
import androidx.appcompat.app.AppCompatActivity;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class ActivityMyPhotoEditComment extends AppCompatActivity {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ImageView f3561v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public u0.a f3562w;
    public EditText x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public ParcelFileDescriptor f3563y = null;

    public void onCancelComment(View view) {
        finish();
    }

    public void onChangeComment(View view) throws Throwable {
        u0.a aVar;
        try {
            Editable text = this.x.getText();
            if (text != null && (aVar = this.f3562w) != null) {
                aVar.M("UserComment", text.toString());
            }
            this.f3562w.I();
            ParcelFileDescriptor parcelFileDescriptor = this.f3563y;
            if (parcelFileDescriptor != null) {
                parcelFileDescriptor.close();
            }
        } catch (Exception e8) {
            d.c("Error:", e8, "GPS-M");
        }
        finish();
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.my_photo_edit_activity);
        this.f3561v = (ImageView) findViewById(R.id.photo_view);
        Uri uri = (Uri) getIntent().getParcelableExtra("FILE_URI");
        try {
            this.f3561v.setImageBitmap(ActivityMyPhotoShow.w(this, uri));
            this.f3561v.setRotation(ActivityMyPhotoShow.x(this, uri));
        } catch (Exception unused) {
            finish();
        }
        this.x = (EditText) findViewById(R.id.comment);
        setTitle(uri.getPath());
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = getContentResolver().openFileDescriptor(uri, "rw");
            this.f3563y = parcelFileDescriptorOpenFileDescriptor;
            u0.a aVar = new u0.a(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
            this.f3562w = aVar;
            String strF = aVar.f("UserComment");
            if (strF == null || strF.equals(XmlPullParser.NO_NAMESPACE)) {
                return;
            }
            this.x.setText(strF);
        } catch (Exception e8) {
            d.c("Error:", e8, "GPS-M");
        }
    }
}
