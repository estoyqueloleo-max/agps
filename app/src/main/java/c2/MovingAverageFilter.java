package c2;

import android.location.Location;

/**
 * 5-point moving average smoothing filter for elevation data.
 */
public class MovingAverageFilter {

    public Location[] locationBuffer = new Location[5];
    public Location[] f3285a = this.locationBuffer;
    public double[] valueBuffer = new double[5];
    public boolean isFirst = true;
    public boolean f3289e = true;
    public int index = 0;
    public int count = 0;
    public int f3287c = 0;

    public void c() {
        this.f3289e = true;
        this.isFirst = true;
        this.index = 0;
        this.count = 0;
    }

    public static void b(double[] values) {
        if (values == null || values.length <= 1) {
            return;
        }
        int length = values.length;
        double[] filtered = new double[length];
        MovingAverageFilter filter = new MovingAverageFilter();
        filtered[values.length - 1] = values[values.length - 1];
        for (int i = 0; i < values.length - 1; i++) {
            filtered[i] = filter.a(values[i]);
        }
        System.arraycopy(filtered, 0, values, 0, values.length);
    }

    public double a(double value) {
        if (this.isFirst) {
            this.isFirst = false;
            for (int i = 0; i < 5; i++) {
                this.valueBuffer[i] = value;
            }
        }
        double[] buf = this.valueBuffer;
        int idx = this.index;
        buf[idx] = value;
        int nextIdx = idx + 1;
        this.index = nextIdx;
        if (nextIdx > 4) {
            this.index = 0;
        }
        double sum = 0.0d;
        for (int i = 0; i < 5; i++) {
            sum += this.valueBuffer[i];
        }
        return sum / 5.0d;
    }
}
