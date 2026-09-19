package com.giobat.AgpsTrackerPP;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.d;
import androidx.appcompat.app.ActionBar;
import androidx.documentfile.provider.DocumentFile;
import c2.t2;
import c2.v2;
import c2.w3;
import c2.y2;
import c7.f;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.TreeMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class ActivityFileDialogGpxJpg extends AppCompatActivity {
    public static boolean L = true;
    public static int M = 2;
    public static boolean N = false;
    public ArrayList<t2> C;
    public int D;
    public int G;
    public Uri H;
    public Handler J;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public TextView f3547v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ArrayList<HashMap<String, Object>> f3548w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public SimpleAdapter f3549y;
    public ListView z;
    public int A = -1;
    public View B = null;
    public String E = XmlPullParser.NO_NAMESPACE;
    public long F = 0;
    public boolean I = false;
    public Runnable K = new c();

    public class a extends SimpleAdapter {
        public a(Context context, List list, int i7, String[] strArr, int[] iArr) {
            super(context, list, i7, strArr, iArr);
        }

        @Override // android.widget.SimpleAdapter, android.widget.Adapter
        public View getView(int i7, View view, ViewGroup viewGroup) {
            String str;
            int iQ;
            w3 w3Var = null;
            if (view == null) {
                view = ActivityFileDialogGpxJpg.this.getLayoutInflater().inflate(ActivityFileDialogGpxJpg.this.G, (ViewGroup) null);
            }
            TextView textView = (TextView) view.findViewById(R.id.fdrowtext);
            ImageView imageView = (ImageView) view.findViewById(R.id.fdrowimage);
            t2 t2Var = ActivityFileDialogGpxJpg.this.C.get(i7);
            if (t2Var.f3179a.endsWith("jpg")) {
                Context applicationContext = ActivityFileDialogGpxJpg.this.getApplicationContext();
                Uri uri = t2Var.f3184f;
                try {
                    u0.a aVar = new u0.a(applicationContext.getContentResolver().openInputStream(uri));
                    w3 w3Var2 = new w3();
                    Bitmap bitmapT = aVar.t();
                    if (bitmapT == null) {
                        bitmapT = MainActivity.d0(applicationContext, uri);
                    }
                    String strF = aVar.f("UserComment");
                    w3Var2.f3223h = bitmapT;
                    w3Var2.f3224i = strF;
                    w3Var = w3Var2;
                } catch (IOException unused) {
                }
                Bitmap bitmap = (Bitmap) w3Var.f3223h;
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    ActivityFileDialogGpxJpg activityFileDialogGpxJpg = ActivityFileDialogGpxJpg.this;
                    Uri uri2 = t2Var.f3184f;
                    Objects.requireNonNull(activityFileDialogGpxJpg);
                    try {
                        iQ = new u0.a(activityFileDialogGpxJpg.getContentResolver().openInputStream(uri2)).q();
                    } catch (Exception unused2) {
                        iQ = 0;
                    }
                    imageView.setRotation(iQ);
                }
            } else {
                imageView.setImageResource(((Integer) ActivityFileDialogGpxJpg.this.f3548w.get(i7).get("image")).intValue());
            }
            String string = t2Var.f3179a + "\n" + ActivityFileDialogGpxJpg.this.f3548w.get(i7).get("date");
            if (w3Var != null && (str = (String) w3Var.f3224i) != null && !str.equals(XmlPullParser.NO_NAMESPACE)) {
                StringBuilder sbD = f.d(string, "\n");
                sbD.append((String) w3Var.f3224i);
                string = sbD.toString();
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
            if (ActivityFileDialogGpxJpg.this.C.get(i7).f3182d) {
                ActivityFileDialogGpxJpg activityFileDialogGpxJpg = ActivityFileDialogGpxJpg.this;
                if (activityFileDialogGpxJpg.C.size() != 0 && activityFileDialogGpxJpg.C.size() >= i7 - 1) {
                    Uri uri = activityFileDialogGpxJpg.C.get(i7).f3184f;
                    activityFileDialogGpxJpg.H = uri;
                    activityFileDialogGpxJpg.x(uri);
                }
            } else {
                ActivityFileDialogGpxJpg activityFileDialogGpxJpg2 = ActivityFileDialogGpxJpg.this;
                activityFileDialogGpxJpg2.A = i7;
                activityFileDialogGpxJpg2.B = view;
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - ActivityFileDialogGpxJpg.this.F < 300) {
                    v2.e("GPS-M", "=> Double Click -------------------------");
                    t2 t2Var = ActivityFileDialogGpxJpg.this.C.get(i7);
                    String str = t2Var.f3179a;
                    if (str.endsWith("gpx")) {
                        Intent intent = ActivityFileDialogGpxJpg.this.getIntent();
                        intent.putExtra("RESULT_PATH", str);
                        intent.putExtra("FILE_URI", t2Var.f3184f);
                        ActivityFileDialogGpxJpg.this.setResult(-1, intent);
                        ActivityFileDialogGpxJpg.this.finish();
                    } else if (t2Var.f3179a.endsWith("jpg")) {
                        ActivityFileDialogGpxJpg activityFileDialogGpxJpg3 = ActivityFileDialogGpxJpg.this;
                        Uri uri2 = t2Var.f3184f;
                        Objects.requireNonNull(activityFileDialogGpxJpg3);
                        Intent intent2 = new Intent(activityFileDialogGpxJpg3, (Class<?>) ActivityMyPhotoShow.class);
                        intent2.setData(uri2);
                        intent2.putExtra("filePath", str);
                        intent2.putExtra("FILE_URI", uri2);
                        intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        activityFileDialogGpxJpg3.startActivity(intent2);
                    }
                    ActivityFileDialogGpxJpg.this.F = 0L;
                }
                ActivityFileDialogGpxJpg.this.F = jCurrentTimeMillis;
            }
            ActivityFileDialogGpxJpg.this.z.invalidate();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActivityFileDialogGpxJpg activityFileDialogGpxJpg = ActivityFileDialogGpxJpg.this;
            activityFileDialogGpxJpg.J.postDelayed(activityFileDialogGpxJpg.K, 1000L);
            ActivityFileDialogGpxJpg activityFileDialogGpxJpg2 = ActivityFileDialogGpxJpg.this;
            if (activityFileDialogGpxJpg2.I) {
                activityFileDialogGpxJpg2.I = false;
                activityFileDialogGpxJpg2.x(activityFileDialogGpxJpg2.H);
            }
            if (ActivityFileDialogGpxJpg.N) {
                TextView textView = (TextView) activityFileDialogGpxJpg2.findViewById(R.id.order_by);
                if (ActivityFileDialogGpxJpg.M == 1) {
                    textView.setText(activityFileDialogGpxJpg2.getResources().getString(R.string.order_by_name));
                } else {
                    textView.setText(activityFileDialogGpxJpg2.getResources().getString(R.string.order_by_date));
                }
                activityFileDialogGpxJpg2.f3549y.notifyDataSetChanged();
                ActivityFileDialogGpxJpg.N = false;
            }
        }
    }

    public static List<t2> y(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriBuildChildDocumentsUriUsingTree = DocumentsContract.buildChildDocumentsUriUsingTree(uri, DocumentsContract.getDocumentId(uri));
        LinkedList linkedList = new LinkedList();
        Cursor cursorQuery = contentResolver.query(uriBuildChildDocumentsUriUsingTree, new String[]{"document_id", "_display_name", "mime_type", "flags", "last_modified", "_size"}, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                String string = cursorQuery.getString(0);
                String string2 = cursorQuery.getString(1);
                String string3 = cursorQuery.getString(2);
                String string4 = cursorQuery.getString(3);
                Long lValueOf = Long.valueOf(Long.parseLong(cursorQuery.getString(4)));
                int i7 = Integer.parseInt(cursorQuery.getString(5));
                Uri uriBuildDocumentUriUsingTree = DocumentsContract.buildDocumentUriUsingTree(uriBuildChildDocumentsUriUsingTree, string);
                try {
                    linkedList.add(new t2(string, string2, string3, string4, lValueOf, i7, DocumentFile.fromSingleUri(context, uriBuildDocumentUriUsingTree), uriBuildDocumentUriUsingTree));
                } catch (Exception e8) {
                    d.c("getListUriFiles: ", e8, "GPS-M");
                }
            } catch (Exception e9) {
                d.c("getListUriFiles query: ", e9, "GPS-M");
            }
        }
        cursorQuery.close();
        return linkedList;
    }

    public static Uri z(Context context, Uri uri, String str) {
        Cursor cursorQuery = context.getContentResolver().query(DocumentsContract.buildChildDocumentsUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri)), new String[]{"document_id", "_display_name", "mime_type"}, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                String string = cursorQuery.getString(0);
                String string2 = cursorQuery.getString(1);
                if (cursorQuery.getString(2).equals("vnd.android.document/directory") && string2.equals(str)) {
                    return DocumentsContract.buildDocumentUriUsingTree(uri, string);
                }
            } catch (Exception e8) {
                d.c("getListUriFiles;", e8, "GPS-M");
            }
        }
        cursorQuery.close();
        return null;
    }

    public void A(String str) {
        View viewFindViewById = findViewById(R.id.my_snack_coordinator_layout);
        v2.e("GPS-M", str);
        Snackbar.make(viewFindViewById, str, Snackbar.LENGTH_SHORT).show();
    }

    public void onClickActionBar(View view) {
        int i7 = this.A;
        if (this.B == null || i7 == -1) {
            A(getResources().getString(R.string.please_select_item));
            return;
        }
        t2 t2Var = this.C.get(i7);
        String str = t2Var.f3179a;
        int id = view.getId();
        String str2 = "gpx";
        if (id == R.id.load_add_file || id == R.id.load_add_file_image || id == R.id.load_add_file_text) {
            if (t2Var.f3179a.endsWith("gpx")) {
                getIntent().putExtra("RESULT_PATH", str);
                getIntent().putExtra("FILE_URI", t2Var.f3184f);
                setResult(-1, getIntent());
                finish();
            } else if (t2Var.f3179a.endsWith("jpg")) {
                Uri uri = t2Var.f3184f;
                Intent intent = new Intent(this, (Class<?>) ActivityMyPhotoShow.class);
                intent.setData(uri);
                intent.putExtra("filePath", str);
                intent.putExtra("FILE_URI", uri);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(intent);
            }
        } else if (id == R.id.share_file || id == R.id.share_file_image || id == R.id.share_file_text) {
            if (t2Var.f3179a.endsWith("gpx") || t2Var.f3179a.endsWith("jpg")) {
                Intent intent2 = new Intent("android.intent.action.SEND");
                intent2.setType("vnd.android.cursor.dir/email");
                intent2.putExtra("android.intent.extra.STREAM", t2Var.f3184f);
                intent2.addFlags(1);
                intent2.putExtra("android.intent.extra.SUBJECT", "AGPS-TrackerOm: " + t2Var.f3179a);
                startActivity(Intent.createChooser(intent2, getResources().getString(R.string.how_share_file)));
            }
            this.A = -1;
        } else if (id == R.id.delete_file || id == R.id.delete_file_image || id == R.id.delete_file_text) {
            if (!t2Var.f3182d || t2Var.f3179a.endsWith("gpx")) {
                if (t2Var.f3183e != null && t2Var.f3183e.canWrite()) {
                    String str3 = t2Var.f3179a;
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(getResources().getString(R.string.really_delete) + " " + str3 + " ?");
                    builder.setNegativeButton(getResources().getString(R.string.no), new c2.c(this));
                    builder.setPositiveButton(getResources().getString(R.string.yes), new c2.d(this, t2Var));
                    builder.show();
                }
            } else if (!t2Var.f3179a.endsWith("jpg")) {
                A(getResources().getString(R.string.error_delete_file));
            } else if (t2Var.f3183e != null && t2Var.f3183e.canWrite()) {
                String str4 = t2Var.f3179a;
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle(R.string.delete_file);
                builder2.setMessage(getResources().getString(R.string.really_delete) + " " + str4 + " ?");
                builder2.setNegativeButton(getResources().getString(R.string.no), new c2.e(this));
                builder2.setPositiveButton(getResources().getString(R.string.yes), new c2.f(this, t2Var));
                builder2.show();
            }
            this.A = -1;
            this.z.refreshDrawableState();
        } else if (id == R.id.rename_file || id == R.id.rename_file_image || id == R.id.rename_file_text) {
            String strY = MainActivity.Y(t2Var.f3179a);
            if (t2Var.f3183e != null && t2Var.f3183e.canWrite()) {
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                builder3.setCancelable(false);
                View viewInflate = getLayoutInflater().inflate(R.layout.rename_file_dialog, (ViewGroup) null);
                if (!strY.endsWith("gpx")) {
                    str2 = strY.endsWith("jpg") ? "jpg" : "???";
                }
                builder3.setTitle(getResources().getString(R.string.rename) + " " + str2);
                builder3.setView(viewInflate);
                EditText editText = (EditText) viewInflate.findViewById(R.id.filename);
                editText.setText(strY.contains(".") ? strY.substring(0, strY.lastIndexOf(46)) : XmlPullParser.NO_NAMESPACE);
                builder3.setPositiveButton(getResources().getString(R.string.ok), new c2.a(this, editText, str2, t2Var));
                builder3.setNegativeButton(getResources().getString(R.string.cancel), new c2.b(this));
                builder3.show();
            }
            this.A = -1;
        } else if ((id == R.id.edit_jpg || id == R.id.edit_jpg_image || id == R.id.edit_jpg_text) && t2Var.f3179a.endsWith("jpg")) {
            Intent intent3 = new Intent(this, (Class<?>) ActivityMyPhotoEditComment.class);
            intent3.putExtra("FILE_URI", t2Var.f3184f);
            startActivity(intent3);
        }
        this.z.setAdapter((ListAdapter) this.f3549y);
    }

    public void onClickGoBackGpx(View view) {
        finish();
    }

    @Override // androidx.fragment.app.q, androidx.activity.ComponentActivity, a0.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setResult(0, getIntent());
        setContentView(R.layout.activity_file_dialog_gpx_jpg);
        TextView textView = (TextView) findViewById(R.id.local_archive_header);
        this.f3547v = (TextView) findViewById(R.id.local_archive_title);
        String stringExtra = getIntent().getStringExtra("PAGE_HEADER");
        int intExtra = getIntent().getIntExtra("REQ_TYPE", 0);
        this.x = intExtra;
        if (intExtra == 1003 || intExtra == 1008) {
            this.D = 1;
            this.E = "gpx";
            this.G = R.layout.file_dialog_list_row_gpx;
        }
        if (intExtra == 1007) {
            this.D = 2;
            this.E = "jpg";
            this.G = R.layout.file_dialog_list_row_jpg;
        }
        if (stringExtra != null) {
            textView.setText(stringExtra);
        }
        this.f3548w = new ArrayList<>();
        this.f3549y = new a(this, this.f3548w, this.G, new String[]{"key", "image", "date"}, new int[]{R.id.fdrowtext, R.id.fdrowimage, R.id.fdrowdate});
        this.C = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.list);
        this.z = listView;
        listView.setAdapter((ListAdapter) this.f3549y);
        this.z.setOnItemClickListener(new b());
        int i7 = this.x;
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        ActionBar aVarT = getSupportActionBar();
        if (aVarT == null) {
            return;
        }
        aVarT.setDisplayOptions(16);
        aVarT.setDisplayShowCustomEnabled(true);
        if (i7 == 1003) {
            aVarT.setCustomView(R.layout.ab_gpx_files_custom_action_bar_layout_load);
        } else if (i7 == 1008) {
            aVarT.setCustomView(R.layout.ab_gpx_files_custom_action_bar_layout_add);
        } else if (i7 == 1007) {
            aVarT.setCustomView(R.layout.ab_jpg_files_custom_action_bar_layout);
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
        Uri uri = MainActivity.f3624l1;
        if (uri == null) {
            Toast toastMakeText = Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.something_wrong_gpx_dir), 1);
            toastMakeText.setGravity(17, 0, 0);
            toastMakeText.show();
            return;
        }
        int i7 = this.D;
        if (i7 == 1) {
            this.H = DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri));
        } else if (i7 != 2) {
            return;
        } else {
            this.H = z(this, uri, "AgpsTrackerPhoto");
        }
        if (L) {
            L = false;
            A(getResources().getString(R.string.double_tap_advice));
        }
        x(this.H);
        Handler handler = this.J;
        if (handler != null) {
            handler.removeCallbacks(this.K);
        }
        this.J = new Handler(Looper.getMainLooper());
        this.K.run();
    }

    @Override // f.e, androidx.fragment.app.q, android.app.Activity
    public void onStop() {
        super.onStop();
        Handler handler = this.J;
        if (handler != null) {
            handler.removeCallbacks(this.K);
        }
    }

    public void sortFilesListGpx(View view) {
        if (M == 1) {
            M = 2;
        } else {
            M = 1;
        }
        this.A = -1;
        this.I = true;
    }

    public final void w(t2 t2Var, int i7) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key", t2Var.f3179a);
        map.put("image", Integer.valueOf(i7));
        new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        map.put("date", i7 != R.drawable.folder ? y2.d(t2Var.f3181c) : XmlPullParser.NO_NAMESPACE);
        this.f3548w.add(map);
    }

    public final void x(Uri uri) {
        if (uri == null) {
            return;
        }
        String lastPathSegment = uri.getLastPathSegment();
        this.f3547v.setText(("External Dir:" + lastPathSegment.substring(lastPathSegment.lastIndexOf(58) + 1)));
        N = false;
        StringBuilder sbA = android.support.v4.media.b.a("GGG GetDir:");
        sbA.append(uri.toString());
        v2.e("GPS-M", sbA.toString());
        List<t2> listY = y(this, uri);
        StringBuilder sbA2 = android.support.v4.media.b.a("GGG GetDir:");
        sbA2.append(uri.toString());
        v2.e("GPS-M", sbA2.toString());
        this.f3548w.clear();
        this.C.clear();
        TreeMap treeMap = new TreeMap();
        TreeMap treeMap2 = new TreeMap();
        for (t2 t2Var : listY) {
            if (t2Var.f3182d) {
                String str = t2Var.f3179a;
                if (!str.equals("AgpsTrackerPhoto")) {
                    Long lValueOf = Long.valueOf(t2Var.f3180b.longValue() + ((long) TimeZone.getDefault().getOffset(t2Var.f3180b.longValue())));
                    if (M == 2) {
                        treeMap.put(lValueOf.toString(), t2Var);
                    } else {
                        treeMap.put(str, t2Var);
                    }
                }
            } else {
                String str2 = t2Var.f3179a;
                if (str2.toLowerCase().endsWith(this.E.toLowerCase())) {
                    Long lValueOf2 = Long.valueOf(t2Var.f3180b.longValue() + ((long) TimeZone.getDefault().getOffset(t2Var.f3180b.longValue())));
                    if (M == 2) {
                        while (treeMap2.containsKey(lValueOf2.toString())) {
                            lValueOf2 = Long.valueOf(lValueOf2.longValue() + 1);
                        }
                        treeMap2.put(lValueOf2.toString(), t2Var);
                    } else {
                        treeMap2.put(str2, t2Var);
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.addAll(treeMap.tailMap(XmlPullParser.NO_NAMESPACE).values());
        arrayList2.addAll(treeMap2.tailMap(XmlPullParser.NO_NAMESPACE).values());
        if (M == 2) {
            Collections.reverse(arrayList);
            Collections.reverse(arrayList2);
        }
        this.C.addAll(arrayList);
        this.C.addAll(arrayList2);
        this.f3548w.clear();
        for (t2 t2Var2 : this.C) {
            if (t2Var2.f3182d) {
                w(t2Var2, R.drawable.folder);
            } else if (t2Var2.f3179a.endsWith("gpx")) {
                w(t2Var2, R.drawable.focus_loaded_track2);
            } else {
                w(t2Var2, R.drawable.question_mark_icon64);
            }
        }
        N = true;
        this.f3549y.notifyDataSetChanged();
    }
}
