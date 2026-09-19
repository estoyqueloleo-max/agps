package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.d;
import c2.v2;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Activity to display a full-screen route photo with zoom, rotation and EXIF metadata.
 */
public class ActivityMyPhotoShow extends AppCompatActivity {

    public ImageView photoImageView;
    public float currentRotationDegrees = 0.0f;

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            while ((height / inSampleSize) > reqHeight || (width / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromUri(Context context, Uri uri, int reqWidth, int reqHeight) {
        if (context == null || uri == null) {
            return null;
        }
        try (ParcelFileDescriptor parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r")) {
            if (parcelFileDescriptor != null) {
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

                options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
                options.inJustDecodeBounds = false;

                return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
            }
        } catch (Throwable t) {
            v2.e("GPS-M", "Error decoding photo bitmap from URI: " + t);
        }
        return null;
    }

    /**
     * Backward-compatible decode method with safe sampling to avoid OutOfMemoryError.
     */
    public static Bitmap w(Context context, Uri uri) {
        return decodeSampledBitmapFromUri(context, uri, 2048, 2048);
    }

    public static int x(Context context, Uri uri) {
        if (context == null || uri == null) {
            return 0;
        }
        try (InputStream inputStream = context.getContentResolver().openInputStream(uri)) {
            if (inputStream != null) {
                return new u0.a(inputStream).q();
            }
        } catch (Throwable t) {
            v2.e("GPS-M", "Error reading EXIF orientation: " + t);
        }
        return 0;
    }

    public static long y(u0.a exif) {
        String strB = a2.e.b(exif.f("GPSDateStamp"), " ", exif.f("GPSTimeStamp"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return simpleDateFormat.parse(strB).getTime();
        } catch (Exception e8) {
            d.c("EXIF Error reading gps time:", e8, "GPS-M");
            return 0L;
        }
    }

    public static Bitmap z(Bitmap bitmap, float degrees) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.preRotate(degrees);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (rotatedBitmap != bitmap) {
            bitmap.recycle();
        }
        return rotatedBitmap;
    }

    public void onClickRotate(View view) {
        float nextRotation = this.currentRotationDegrees + 90.0f;
        this.currentRotationDegrees = nextRotation;
        if (this.photoImageView != null) {
            this.photoImageView.setRotation(nextRotation);
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.my_photo_show_activity);
        this.photoImageView = (ImageView) findViewById(R.id.photo_view);

        String filePathExtra = getIntent().getStringExtra("filePath");
        Uri uri = (Uri) getIntent().getParcelableExtra("FILE_URI");
        if (uri == null) {
            uri = getIntent().getData();
        }

        // Resilient title extraction supporting SAF content URIs and POSIX paths
        String displayTitle = null;
        if (filePathExtra != null && !filePathExtra.isEmpty()) {
            int lastSlash = filePathExtra.lastIndexOf('/');
            displayTitle = (lastSlash >= 0) ? filePathExtra.substring(lastSlash + 1) : filePathExtra;
        }
        if ((displayTitle == null || displayTitle.isEmpty()) && uri != null) {
            String lastSegment = uri.getLastPathSegment();
            if (lastSegment != null) {
                int lastSlash = lastSegment.lastIndexOf('/');
                displayTitle = (lastSlash >= 0) ? lastSegment.substring(lastSlash + 1) : lastSegment;
            }
        }
        if (displayTitle == null || displayTitle.isEmpty()) {
            displayTitle = getString(R.string.photo_title);
        }
        setTitle(displayTitle);

        if (uri != null) {
            try {
                int orientationDegrees = x(this, uri);
                Bitmap bitmap = w(this, uri);
                if (bitmap != null) {
                    if (orientationDegrees != 0) {
                        bitmap = z(bitmap, orientationDegrees);
                    }
                    if (this.photoImageView != null) {
                        this.photoImageView.setImageBitmap(bitmap);
                    }
                    this.currentRotationDegrees = orientationDegrees;
                }
            } catch (Throwable t) {
                v2.e("GPS-M", "Error loading photo image: " + t);
            }

            TextView textView = (TextView) findViewById(R.id.photo_text_view);
            if (textView != null) {
                try (InputStream inputStream = getContentResolver().openInputStream(uri)) {
                    if (inputStream != null) {
                        u0.a exif = new u0.a(inputStream);
                        String userComment = exif.f("UserComment");
                        DecimalFormat coordFormat = new DecimalFormat("0.#####");
                        String dateTimeOriginal = exif.f("DateTimeOriginal");
                        if (dateTimeOriginal == null) {
                            dateTimeOriginal = "N.A:";
                        }
                        String infoText = dateTimeOriginal + "\n" + (userComment != null ? userComment : "");
                        double[] latLon = exif.k();
                        if (latLon == null) {
                            infoText = infoText + "\nLat= NA\nLon= NA";
                        } else {
                            infoText = (infoText + "\n" + getString(R.string.latitude) + " " + coordFormat.format(latLon[0]) + "°")
                                    + "\n" + getString(R.string.longitude) + " " + coordFormat.format(latLon[1]) + "°";
                        }
                        DecimalFormat altFormat = new DecimalFormat("00");
                        u0.a.d altAttr = exif.h("GPSAltitude");
                        double altitudeMeters = -1.0d;
                        if (altAttr != null) {
                            try {
                                altitudeMeters = altAttr.g(exif.f18208h);
                            } catch (NumberFormatException ignored) {
                            }
                        }
                        int altRef = exif.g("GPSAltitudeRef", -1);
                        double finalAltitude;
                        if (altitudeMeters < 0.0d || altRef < 0) {
                            finalAltitude = -999.0d;
                        } else {
                            finalAltitude = altitudeMeters * (altRef == 1 ? -1 : 1);
                        }
                        String altText = infoText + "\n" + getString(R.string.altitude) + " ";
                        if (finalAltitude == -999.0d) {
                            altText = altText + "NA";
                        } else {
                            altText = altText + altFormat.format(MainActivity.W(finalAltitude)) + MainActivity.y();
                        }
                        textView.setText(altText);
                    }
                } catch (Throwable t) {
                    v2.e("GPS-M", "Error reading photo EXIF attributes: " + t);
                }
            }
        }
    }
}
