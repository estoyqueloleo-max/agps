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
import com.ortiz.touchview.TouchImageView;
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

    public TouchImageView photoImageView;
    public Bitmap currentBitmap = null;
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

        // 1. Primary path: Decode via ParcelFileDescriptor with offset rewind
        try (ParcelFileDescriptor parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r")) {
            if (parcelFileDescriptor != null) {
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

                options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
                options.inJustDecodeBounds = false;

                // Rewind file descriptor offset back to 0 before the actual decoding pass
                try {
                    android.system.Os.lseek(fileDescriptor, 0, android.system.OsConstants.SEEK_SET);
                } catch (Throwable seekException) {
                    v2.e("GPS-M", "Notice: FileDescriptor not seekable, fallback will be used if needed: " + seekException);
                }

                Bitmap decodedBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                if (decodedBitmap != null) {
                    return decodedBitmap;
                }
            }
        } catch (Throwable parcelException) {
            v2.e("GPS-M", "Error decoding photo bitmap via ParcelFileDescriptor: " + parcelException);
        }

        // 2. Resilient fallback: Decode using two consecutive InputStreams from ContentResolver
        try {
            BitmapFactory.Options streamOptions = new BitmapFactory.Options();
            streamOptions.inJustDecodeBounds = true;
            try (InputStream boundsStream = context.getContentResolver().openInputStream(uri)) {
                if (boundsStream != null) {
                    BitmapFactory.decodeStream(boundsStream, null, streamOptions);
                }
            }

            if (streamOptions.outWidth > 0 && streamOptions.outHeight > 0) {
                streamOptions.inSampleSize = calculateInSampleSize(streamOptions, reqWidth, reqHeight);
                streamOptions.inJustDecodeBounds = false;
                try (InputStream contentStream = context.getContentResolver().openInputStream(uri)) {
                    if (contentStream != null) {
                        return BitmapFactory.decodeStream(contentStream, null, streamOptions);
                    }
                }
            }
        } catch (Throwable streamException) {
            v2.e("GPS-M", "Error decoding photo bitmap via InputStream fallback: " + streamException);
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
        } catch (Throwable orientationException) {
            v2.e("GPS-M", "Error reading EXIF orientation: " + orientationException);
        }
        return 0;
    }

    public static long y(u0.a exif) {
        String dateTimeGpsString = a2.e.b(exif.f("GPSDateStamp"), " ", exif.f("GPSTimeStamp"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return simpleDateFormat.parse(dateTimeGpsString).getTime();
        } catch (Exception parseException) {
            d.c("EXIF Error reading gps time:", parseException, "GPS-M");
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
        if (this.currentBitmap != null) {
            this.currentBitmap = z(this.currentBitmap, 90.0f);
            this.currentRotationDegrees = (this.currentRotationDegrees + 90.0f) % 360.0f;
            if (this.photoImageView != null) {
                this.photoImageView.setImageBitmap(this.currentBitmap);
            }
        } else if (this.photoImageView != null) {
            float nextRotation = this.currentRotationDegrees + 90.0f;
            this.currentRotationDegrees = nextRotation;
            this.photoImageView.setRotation(nextRotation);
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.my_photo_show_activity);
        this.photoImageView = (TouchImageView) findViewById(R.id.photo_view);

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
                    this.currentBitmap = bitmap;
                    if (this.photoImageView != null) {
                        this.photoImageView.setImageBitmap(bitmap);
                    }
                    this.currentRotationDegrees = orientationDegrees;
                }
            } catch (Throwable loadException) {
                v2.e("GPS-M", "Error loading photo image: " + loadException);
            }

            TextView photoDetailsTextView = (TextView) findViewById(R.id.photo_text_view);
            if (photoDetailsTextView != null) {
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
                        u0.a.d altitudeAttribute = exif.h("GPSAltitude");
                        double altitudeMeters = -1.0d;
                        if (altitudeAttribute != null) {
                            try {
                                altitudeMeters = altitudeAttribute.g(exif.f18208h);
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
                        photoDetailsTextView.setText(altText);
                    }
                } catch (Throwable exifException) {
                    v2.e("GPS-M", "Error reading photo EXIF attributes: " + exifException);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.currentBitmap != null && !this.currentBitmap.isRecycled()) {
            this.currentBitmap.recycle();
            this.currentBitmap = null;
        }
    }
}
