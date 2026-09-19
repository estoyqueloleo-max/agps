package c2;

import android.widget.SeekBar;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;

/* JADX INFO: loaded from: classes.dex */
public class w2 implements SeekBar.OnSeekBarChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3221a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public j3 f3222b;

    public w2(int i7, j3 j3Var) {
        this.f3221a = i7;
        this.f3222b = j3Var;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i7, boolean z) {
        if (seekBar.getId() == R.id.SizeSeekBar) {
            int progress = seekBar.getProgress();
            this.f3222b.f3024b = progress;
            if (progress == 0) {
                progress = 1;
            }
            if (this.f3221a == 1) {
                MainActivity.f3634v1 = progress;
            } else {
                MainActivity.f3635w1 = progress;
            }
            MainActivity.U0 = true;
            return;
        }
        int id = seekBar.getId();
        if (id == R.id.RedSeekBar) {
            j3 j3Var = this.f3222b;
            j3Var.f3023a = (i7 << 16) | (j3Var.f3023a & 65535);
        } else if (id == R.id.GreenSeekBar) {
            j3 j3Var2 = this.f3222b;
            j3Var2.f3023a = (i7 << 8) | (j3Var2.f3023a & 16711935);
        } else if (id == R.id.BlueSeekBar) {
            j3 j3Var3 = this.f3222b;
            j3Var3.f3023a = i7 | (j3Var3.f3023a & 16776960);
        }
        j3 j3Var4 = this.f3222b;
        int i8 = j3Var4.f3023a | (-16777216);
        j3Var4.f3023a = i8;
        if (this.f3221a == 1) {
            MainActivity.f3632t1 = i8;
        } else {
            MainActivity.f3633u1 = i8;
        }
        j3Var4.f3026d.findViewById(R.id.color_line).setBackgroundColor(i8);
        MainActivity.U0 = true;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
