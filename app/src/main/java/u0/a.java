package u0;

import android.graphics.Bitmap;
import androidx.exifinterface.media.ExifInterface;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;

/**
 * Adapter bridging legacy obfuscated u0.a calls to AndroidX ExifInterface.
 */
public class a extends ExifInterface {

    public Object f18208h = null;

    public a(String filename) throws IOException {
        super(filename);
    }

    public a(File file) throws IOException {
        super(file.getAbsolutePath());
    }

    public a(FileDescriptor fileDescriptor) throws IOException {
        super(fileDescriptor);
    }

    public a(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    public String f(String tag) {
        return getAttribute(tag);
    }

    public int g(String tag, int defaultValue) {
        return getAttributeInt(tag, defaultValue);
    }

    public int q() {
        return getRotationDegrees();
    }

    public Bitmap t() {
        return getThumbnailBitmap();
    }

    public double[] k() {
        return getLatLong();
    }

    public void M(String tag, String value) {
        setAttribute(tag, value);
    }

    public void N(android.location.Location location) {
        setGpsInfo(location);
    }

    public void I() throws IOException {
        saveAttributes();
    }

    public static class d {
        private final double value;

        public d(double value) {
            this.value = value;
        }

        public double g(Object unused) {
            return this.value;
        }
    }

    public d h(String tag) {
        if ("GPSAltitude".equalsIgnoreCase(tag)) {
            double alt = getAltitude(-1.0d);
            if (alt >= 0.0d) {
                return new d(alt);
            }
        }
        return null;
    }
}
