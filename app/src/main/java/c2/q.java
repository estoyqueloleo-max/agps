package c2;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;

/* JADX INFO: loaded from: classes.dex */
public class q extends Dialog implements View.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f3118h;

    public q(Context context) {
        super(context);
        this.f3118h = "elv-hiking";
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        this.f3118h = "elv-hiking";
        if (id == R.id.elv_hiking) {
            this.f3118h = "elv-hiking";
        } else if (id == R.id.elv_city) {
            this.f3118h = "elv-city";
        } else if (id == R.id.elv_mtb) {
            this.f3118h = "elv-mtb";
        } else if (id == R.id.elv_cycling) {
            this.f3118h = "elv-cycling";
        }
        MainActivity.f3637y1 = true;
        String str = this.f3118h;
        SharedPreferences.Editor editorEdit = MainActivity.R0.edit();
        editorEdit.putString("ELEVATE_STYLE", str);
        editorEdit.apply();
        dismiss();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.elevate_dialog);
        this.f3118h = MainActivity.c0("ELEVATE_STYLE", "elv-hiking");
        RadioButton radioButton = (RadioButton) findViewById(R.id.elv_hiking);
        radioButton.setOnClickListener(this);
        RadioButton radioButton2 = (RadioButton) findViewById(R.id.elv_city);
        radioButton2.setOnClickListener(this);
        RadioButton radioButton3 = (RadioButton) findViewById(R.id.elv_mtb);
        radioButton3.setOnClickListener(this);
        RadioButton radioButton4 = (RadioButton) findViewById(R.id.elv_cycling);
        radioButton4.setOnClickListener(this);
        if (this.f3118h.equals("elv-hiking")) {
            radioButton.setChecked(true);
            return;
        }
        if (this.f3118h.equals("elv-city")) {
            radioButton2.setChecked(true);
        } else if (this.f3118h.equals("elv-mtb")) {
            radioButton3.setChecked(true);
        } else if (this.f3118h.equals("elv-cycling")) {
            radioButton4.setChecked(true);
        }
    }
}
