package com.giobat.AgpsTrackerPP;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.p;
import c2.g;
import c2.h;
import c2.m2;
import c2.r3;
import c2.v2;
import c2.y2;
import c7.f;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.TreeMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class ActivityInternalFileDialog extends AppCompatActivity {
    public static boolean K = true;
    public int B;
    public SimpleAdapter C;
    public ListView D;
    public ArrayList<y2> G;
    public int H;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public TextView f3553v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ArrayList<HashMap<String, Object>> f3554w;
    public String x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f3555y = "/";
    public String[] z = null;
    public long A = 0;
    public int E = -1;
    public View F = null;
    public long I = 0;
    public int J = 2;

    public class a extends SimpleAdapter {
        public a(Context context, List list, int i7, String[] strArr, int[] iArr) {
            super(context, list, i7, strArr, iArr);
        }

        @Override // android.widget.SimpleAdapter, android.widget.Adapter
        public View getView(int i7, View view, ViewGroup viewGroup) {
            String string;
            long jLongValue;
            if (view == null) {
                view = ActivityInternalFileDialog.this.getLayoutInflater().inflate(R.layout.file_dialog_list_row_gpx, (ViewGroup) null);
            }
            TextView textView = (TextView) view.findViewById(R.id.fdrowtext);
            ImageView imageView = (ImageView) view.findViewById(R.id.fdrowimage);
            y2 y2Var = ActivityInternalFileDialog.this.G.get(i7);
            int iIntValue = ((Integer) ActivityInternalFileDialog.this.f3554w.get(i7).get("image")).intValue();
            if (y2Var.f3262i.equals("⤴")) {
                iIntValue = R.drawable.ic_arrow_upward_black_24dp;
            }
            imageView.setImageResource(iIntValue);
            if (y2Var.f3262i.equals("⤴")) {
                string = XmlPullParser.NO_NAMESPACE;
            } else {
                String string2 = y2Var.f3262i;
                if (ActivityInternalFileDialog.this.z[0].equals("map")) {
                    String str = y2Var.f3262i;
                    Iterator<r3> it = m2.f3056v.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            jLongValue = 0;
                            break;
                        }
                        r3 next = it.next();
                        String str2 = next.f3149a;
                        if (MainActivity.Y(m2.c(str2.substring(2, str2.length()))).equals(m2.c(str))) {
                            jLongValue = next.f3151c.longValue();
                            break;
                        }
                    }
                    if (jLongValue > 0 && y2Var.f3265l.longValue() != jLongValue) {
                        StringBuilder sbD = f.d(string2, " (");
                        sbD.append(ActivityInternalFileDialog.this.getResources().getString(R.string.updated_map_available));
                        sbD.append(" ");
                        sbD.append(y2.d(jLongValue));
                        sbD.append(")");
                        string2 = sbD.toString();
                        View viewFindViewById = ActivityInternalFileDialog.this.findViewById(R.id.update_map);
                        if (viewFindViewById != null) {
                            viewFindViewById.setVisibility(0);
                        }
                    }
                }
                StringBuilder sbD2 = f.d(string2, "\n");
                sbD2.append(ActivityInternalFileDialog.this.f3554w.get(i7).get("date"));
                string = sbD2.toString();
            }
            textView.setText(string);
            return view;
        }
    }

    public class b implements AdapterView.OnItemClickListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i7, long j7) {
            String strA = null;
            if (ActivityInternalFileDialog.this.G.get(i7).f3261h) {
                ActivityInternalFileDialog activityInternalFileDialog = ActivityInternalFileDialog.this;
                if (activityInternalFileDialog.G.size() != 0 && activityInternalFileDialog.G.size() >= i7 - 1) {
                    y2 y2Var = activityInternalFileDialog.G.get(i7);
                    if (y2Var.f3262i.equals("⤴")) {
                        if (!y2Var.f3263j.equals("/storage/emulated/0")) {
                            strA = MainActivity.R(y2Var.f3263j);
                        }
                    } else if (y2Var.f3263j.equals("/")) {
                        StringBuilder sbA = android.support.v4.media.b.a("/");
                        sbA.append(y2Var.f3262i);
                        strA = sbA.toString();
                    } else {
                        strA = y2Var.a();
                    }
                    if (strA != null && MainActivity.X(activityInternalFileDialog, strA, activityInternalFileDialog.H).canRead()) {
                        activityInternalFileDialog.z(strA);
                    }
                }
                ActivityInternalFileDialog.this.D.refreshDrawableState();
                return;
            }
            ActivityInternalFileDialog activityInternalFileDialog2 = ActivityInternalFileDialog.this;
            activityInternalFileDialog2.E = i7;
            activityInternalFileDialog2.F = view;
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - ActivityInternalFileDialog.this.I < 300) {
                v2.e("GPS-M", "=> Double Click -------------------------");
                y2 y2Var2 = ActivityInternalFileDialog.this.G.get(i7);
                String strA2 = y2Var2.a();
                if (y2Var2.f3262i.endsWith("hgt") || y2Var2.f3262i.endsWith("map")) {
                    Intent intent = ActivityInternalFileDialog.this.getIntent();
                    intent.putExtra("RESULT_PATH", strA2);
                    ActivityInternalFileDialog.this.setResult(-1, intent);
                    ActivityInternalFileDialog.this.finish();
                }
                ActivityInternalFileDialog.this.I = 0L;
            }
            ActivityInternalFileDialog.this.I = jCurrentTimeMillis;
        }
    }

    public class c implements DialogInterface.OnClickListener {
        public c(ActivityInternalFileDialog activityInternalFileDialog) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            dialogInterface.dismiss();
        }
    }

    public class d implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ File f3558h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ String f3559i;

        public d(File file, String str) {
            this.f3558h = file;
            this.f3559i = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i7) {
            this.f3558h.isDirectory();
            if (!this.f3558h.delete()) {
                ActivityInternalFileDialog activityInternalFileDialog = ActivityInternalFileDialog.this;
                activityInternalFileDialog.A(activityInternalFileDialog.getResources().getString(R.string.error_delete_file));
            }
            ActivityInternalFileDialog activityInternalFileDialog2 = ActivityInternalFileDialog.this;
            String str = this.f3559i;
            boolean z = ActivityInternalFileDialog.K;
            activityInternalFileDialog2.z(str);
            dialogInterface.dismiss();
        }
    }

    public static int y(String str) {
        if (str.equals("map")) {
            return 2;
        }
        return str.equals("hgt") ? 1 : 6;
    }

    public void A(String str) {
        Snackbar.make(findViewById(R.id.my_snack_coordinator_layout), str, Snackbar.LENGTH_SHORT).show();
    }

    public void fileDialogHelp(View view) {
        new p(this, 3);
    }

    public void onClickActionBar(View view) {
        String strA;
        int id = view.getId();
        if (id == R.id.show_dems || id == R.id.show_dems_image || id == R.id.show_dems_text) {
            getIntent().putExtra("RESULT_PATH", "?");
            setResult(-1, getIntent());
            finish();
        }
        int i7 = this.E;
        if (this.F == null || i7 == -1) {
            A(getResources().getString(R.string.please_select_item));
            return;
        }
        y2 y2Var = this.G.get(i7);
        String strA2 = y2Var.a();
        if (id == R.id.load_add_file || id == R.id.load_add_file_image || id == R.id.load_add_file_text) {
            if (y2Var.f3262i.endsWith("hgt") || y2Var.f3262i.endsWith("map")) {
                getIntent().putExtra("RESULT_PATH", strA2);
                setResult(-1, getIntent());
                finish();
            }
        } else if (id == R.id.delete_file_image || id == R.id.delete_file_text || id == R.id.delete_dem || id == R.id.delete_dem_image || id == R.id.delete_dem_text) {
            if (y2Var.f3261h || y2Var.f3262i.endsWith("map") || y2Var.f3262i.endsWith("zip")) {
                x(strA2, this);
            } else if (y2Var.f3262i.endsWith("hgt")) {
                x(strA2, this);
                AgpsApplication.f3581y = true;
            } else {
                A(getResources().getString(R.string.error_delete_file));
            }
            this.E = -1;
            this.D.refreshDrawableState();
        } else {
            String strSubstring = XmlPullParser.NO_NAMESPACE;
            if (id == R.id.rename_file || id == R.id.rename_file_image || id == R.id.rename_file_text) {
                if (this.B == 1002) {
                    A(getResources().getString(R.string.dem_filename_cant_be_changed));
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setCancelable(false);
                    View viewInflate = getLayoutInflater().inflate(R.layout.rename_file_dialog, (ViewGroup) null);
                    File fileX = MainActivity.X(getApplicationContext(), strA2, 2);
                    if (fileX.canRead()) {
                        builder.setTitle(getResources().getString(R.string.rename));
                        builder.setView(viewInflate);
                        EditText editText = (EditText) viewInflate.findViewById(R.id.filename);
                        if (strA2.contains(".")) {
                            strSubstring = strA2.substring(0, strA2.lastIndexOf(46));
                        }
                        editText.setText(strSubstring);
                        builder.setPositiveButton(getResources().getString(R.string.ok), new g(this, strA2, editText, fileX));
                        builder.setNegativeButton(getResources().getString(R.string.cancel), new h(this));
                        builder.show();
                    }
                    z(this.f3555y);
                }
                this.E = -1;
            } else if ((id == R.id.update_map || id == R.id.update_map_image || id == R.id.update_map_txt) && this.B == 1001) {
                String strSubstring2 = strA2.substring(4);
                String strSubstring3 = strSubstring2.substring(1, strSubstring2.indexOf(".osm"));
                Iterator<r3> it = m2.f3056v.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        strA = XmlPullParser.NO_NAMESPACE;
                        break;
                    }
                    r3 next = it.next();
                    if (next.f3149a.contains(strSubstring3)) {
                        int iIndexOf = next.f3149a.indexOf(strSubstring3);
                        StringBuilder sbA = android.support.v4.media.b.a("maps");
                        sbA.append(next.f3149a.substring(1, iIndexOf - 1));
                        strSubstring = sbA.toString();
                        strA = (strSubstring3 + ".map");
                        break;
                    }
                }
                getResources().getString(R.string.downloading_map);
                SFtpServerFileDialogActivity.d(this, strSubstring.substring(strSubstring.indexOf("maps")), strA, 2);
                finish();
            }
        }
        this.E = -1;
        this.D.setAdapter((ListAdapter) this.C);
    }

    public void onClickGoBack(View view) {
        finish();
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setResult(0, getIntent());
        setContentView(R.layout.activity_file_dialog_internal);
        TextView textView = (TextView) findViewById(R.id.local_archive_header);
        this.f3553v = (TextView) findViewById(R.id.local_archive_title);
        m2.h(this);
        this.z = getIntent().getStringArrayExtra("FORMAT_FILTER");
        String stringExtra = getIntent().getStringExtra("PAGE_HEADER");
        this.B = getIntent().getIntExtra("REQ_TYPE", 0);
        this.x = getIntent().getStringExtra("START_PATH");
        this.H = y(this.z[0]);
        this.f3555y = this.x;
        if (stringExtra != null) {
            textView.setText(stringExtra);
        }
        this.f3554w = new ArrayList<>();
        this.C = new a(this, this.f3554w, R.layout.file_dialog_list_row_gpx, new String[]{"key", "image", "date"}, new int[]{R.id.fdrowtext, R.id.fdrowimage, R.id.fdrowdate});
        this.G = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.list);
        this.D = listView;
        listView.setAdapter((ListAdapter) this.C);
        this.D.setOnItemClickListener(new b());
        int i7 = this.B;
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        androidx.appcompat.app.ActionBar aVarT = getSupportActionBar();
        if (aVarT == null) {
            return;
        }
        aVarT.setDisplayOptions(androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        aVarT.setDisplayShowCustomEnabled(true);
        if (i7 == 1002) {
            aVarT.setCustomView(R.layout.ab_dem_files_custom_action_bar_layout);
        }
        if (i7 == 1001) {
            aVarT.setCustomView(R.layout.ab_map_files_custom_action_bar_layout);
        }
    }

    @Override // f.e, androidx.fragment.app.q, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.q, android.app.Activity
    public void onResume() {
        super.onResume();
        v2.e("GPS-M", "File Dialog: OnResume()");
        z(this.f3555y);
        if (((ArrayList) m2.f3052r).size() == 0) {
            getResources().getString(R.string.no_current_maps);
        } else {
            String strA = getResources().getString(R.string.current_map) + " ";
            boolean z = true;
            for (y2 y2Var : (List<y2>) (List<?>) m2.f3052r) {
                if (z) {
                    z = false;
                } else {
                    strA = (strA + " + ");
                }
                StringBuilder sbA = android.support.v4.media.b.a(strA);
                sbA.append(y2Var.f3262i);
                strA = sbA.toString();
            }
        }
        if (K) {
            K = false;
            A(getResources().getString(R.string.double_tap_advice));
        }
    }

    public void sortFilesList(View view) {
        if (this.J == 1) {
            this.J = 2;
        } else {
            this.J = 1;
        }
        this.E = -1;
        z(this.f3555y);
    }

    public final void w(y2 y2Var, int i7) {
        String str;
        HashMap<String, Object> map = new HashMap<>();
        map.put("key", y2Var.f3262i);
        map.put("image", Integer.valueOf(i7));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        if (i7 != R.drawable.folder) {
            str = y2.d(y2Var.f3265l.longValue()) + ", " + simpleDateFormat.format(y2Var.f3264k);
        } else {
            str = XmlPullParser.NO_NAMESPACE;
        }
        map.put("date", str);
        this.f3554w.add(map);
    }

    public final void x(String str, Context context) {
        File fileX = MainActivity.X(this, str, this.H);
        if (fileX.canRead()) {
            String strY = MainActivity.Y(str);
            String strA0 = MainActivity.a0(str);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(context.getResources().getString(R.string.really_delete) + " " + strY + " ?");
            builder.setNegativeButton(context.getResources().getString(R.string.no), new c(this));
            builder.setPositiveButton(context.getResources().getString(R.string.yes), new d(fileX, strA0));
            builder.show();
        }
    }

    public final void z(String str) {
        String string;
        boolean z;
        v2.e("GPS-M", "GetDir:" + str);
        this.f3554w.clear();
        this.f3555y = str;
        File[] fileArrListFiles = MainActivity.X(this, str, this.H).listFiles();
        if (fileArrListFiles == null) {
            String str2 = this.x;
            this.f3555y = str2;
            fileArrListFiles = MainActivity.X(this, str2, this.H).listFiles();
            if (fileArrListFiles == null) {
                return;
            }
        }
        String str3 = this.f3555y;
        String strY = MainActivity.Y(str3);
        int iC = r.g.c(this.H);
        if (iC == 0 || iC == 1) {
            StringBuilder sbA = android.support.v4.media.b.a(MainActivity.f3621i1 ? getResources().getString(R.string.sd_mem) + " " : getResources().getString(R.string.internal_mem) + "\n");
            sbA.append((Object) getText(R.string.local_archive));
            sbA.append(": ");
            sbA.append(str3);
            string = sbA.toString();
        } else if (iC == 2 || iC == 3 || iC == 4) {
            string = ((Object) getText(R.string.local_archive)) + ":" + str3;
        } else {
            string = "???";
        }
        this.f3553v.setText(string);
        this.G.clear();
        long j7 = 0;
        if (!strY.equals("Maps") && !strY.equals("Dems") && !strY.equals(XmlPullParser.NO_NAMESPACE)) {
            y2 y2Var = new y2("⤴", this.f3555y, 0L, true, 0L, 6);
            this.G.add(y2Var);
            w(y2Var, R.drawable.folder);
        }
        TreeMap treeMap = new TreeMap();
        TreeMap treeMap2 = new TreeMap();
        this.A = 0L;
        int i7 = 0;
        while (i7 < fileArrListFiles.length) {
            if (fileArrListFiles[i7].isDirectory()) {
                String name = fileArrListFiles[i7].getName();
                if (!name.equals("Maps") && !name.equals("Dems")) {
                    Long lValueOf = Long.valueOf(fileArrListFiles[i7].lastModified() + ((long) TimeZone.getDefault().getOffset(fileArrListFiles[i7].lastModified())));
                    if (this.J == 2) {
                        treeMap.put(lValueOf.toString(), new y2(name, this.f3555y, lValueOf, true, 0L, this.H));
                    } else {
                        treeMap.put(name, new y2(name, this.f3555y, lValueOf, true, 0L, this.H));
                    }
                    this.A += j7;
                }
            } else {
                String name2 = fileArrListFiles[i7].getName();
                String lowerCase = name2.toLowerCase();
                if (this.z != null) {
                    int i8 = 0;
                    while (true) {
                        String[] strArr = this.z;
                        if (i8 >= strArr.length) {
                            z = false;
                            break;
                        } else {
                            if (lowerCase.endsWith(strArr[i8].toLowerCase())) {
                                z = true;
                                break;
                            }
                            i8++;
                        }
                    }
                    if (z) {
                        Long lValueOf2 = Long.valueOf(fileArrListFiles[i7].lastModified() + ((long) TimeZone.getDefault().getOffset(fileArrListFiles[i7].lastModified())));
                        if (this.J == 2) {
                            while (treeMap2.containsKey(lValueOf2.toString())) {
                                lValueOf2 = Long.valueOf(lValueOf2.longValue() + 1);
                            }
                            treeMap2.put(lValueOf2.toString(), new y2(name2, this.f3555y, lValueOf2, false, fileArrListFiles[i7].length(), this.H));
                        } else {
                            treeMap2.put(name2, new y2(name2, this.f3555y, lValueOf2, false, fileArrListFiles[i7].length(), this.H));
                        }
                        this.A = fileArrListFiles[i7].length() + this.A;
                    }
                } else {
                    treeMap2.put(name2, new y2(name2, this.f3555y, Long.valueOf(fileArrListFiles[i7].lastModified() + ((long) TimeZone.getDefault().getOffset(fileArrListFiles[i7].lastModified()))), false, fileArrListFiles[i7].length(), this.H));
                    this.A = fileArrListFiles[i7].length() + this.A;
                }
            }
            i7++;
            j7 = 0;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.addAll(treeMap.tailMap(XmlPullParser.NO_NAMESPACE).values());
        arrayList2.addAll(treeMap2.tailMap(XmlPullParser.NO_NAMESPACE).values());
        if (this.J == 2) {
            Collections.reverse(arrayList);
            Collections.reverse(arrayList2);
        }
        this.G.addAll(arrayList);
        this.G.addAll(arrayList2);
        this.f3554w.clear();
        for (y2 y2Var2 : this.G) {
            if (y2Var2.f3261h) {
                if (y2Var2.f3262i.endsWith("hgt")) {
                    w(y2Var2, R.drawable.folder_dem);
                } else {
                    w(y2Var2, R.drawable.folder);
                }
            } else if (y2Var2.f3262i.endsWith("map")) {
                w(y2Var2, R.drawable.ic_map_black_36dp);
            } else if (y2Var2.f3262i.endsWith("hgt")) {
                w(y2Var2, R.drawable.ic_crop_original_black_36dp);
            } else {
                w(y2Var2, R.drawable.question_mark_icon64);
            }
        }
        TextView textView = (TextView) findViewById(R.id.order_by);
        if (this.J == 1) {
            textView.setText(getResources().getString(R.string.order_by_name));
        } else {
            textView.setText(getResources().getString(R.string.order_by_date));
        }
        this.C.notifyDataSetChanged();
    }
}
