package c2;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.StatFs;
import android.view.Display;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import com.giobat.AgpsTrackerPP.AgpsApplication;
import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.Dimension;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.rendertheme.AssetsRenderTheme;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.datastore.MapDataStore;
import org.mapsforge.map.datastore.MultiMapDataStore;
import org.mapsforge.map.layer.cache.FileSystemTileCache;
import org.mapsforge.map.layer.cache.InMemoryTileCache;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.cache.TwoLevelTileCache;
import org.mapsforge.map.layer.overlay.Circle;
import org.mapsforge.map.layer.overlay.Marker;
import org.mapsforge.map.layer.overlay.Polyline;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.model.MapViewPosition;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.rendertheme.XmlRenderTheme;
import org.mapsforge.map.rendertheme.XmlRenderThemeMenuCallback;
import org.mapsforge.map.rendertheme.XmlRenderThemeStyleMenu;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class m2 implements XmlRenderThemeMenuCallback {

    private static final Logger LOGGER = Logger.getLogger(m2.class.getName());

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static boolean f3047l = false;
    /** User position marker overlay (pallino_blu or freccia_blu) */
    public static k3 userPositionMarker = null;
    public static k3 m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static ArrayList<y> f3048n = null;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static ArrayList<y> f3049o = null;

    /** Drawable resource ID for the user marker (pallino_blu or freccia_blu) */
    public static int userMarkerIconResId = com.giobat.AgpsTrackerPP.R.drawable.pallino_blu;
    public static int f3050p = com.giobat.AgpsTrackerPP.R.drawable.pallino_blu;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static CopyOnWriteArrayList<h3> f3051q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static List<y2> f3052r = new ArrayList();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static ArrayList<y2> f3053s = new ArrayList<>();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static byte f3054t = 0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static boolean f3055u = true;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static ArrayList<r3> f3056v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3057a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public MultiMapDataStore multiMapDataStore;
    public MultiMapDataStore f3058b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TileRendererLayer tileRendererLayer;
    public TileRendererLayer f3059c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TileCache tileCache;
    public TileCache f3060d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public com.giobat.AgpsTrackerPP.b f3061e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Circle gpsAccuracyCircle;
    public Circle f3062f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public File f3063g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3064h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public XmlRenderThemeStyleMenu f3065i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public b f3066j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public LatLong f3067k;
    private LatLong lastOverlayPosition = null;
    private float lastOverlayRadius = -1.0f;
    private String lastLoadedMapFingerprint = "";

    @Override
    public Set<String> getCategories(XmlRenderThemeStyleMenu xmlRenderThemeStyleMenu) {
        this.f3065i = xmlRenderThemeStyleMenu;
        return null;
    }

    public class a extends MapFile {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final /* synthetic */ String f3068k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(m2 m2Var, File file, String str) {
            super(file);
            this.f3068k = str;
        }


        @Override
        protected String extractLocalized(String str) {
            String strC = MapDataStore.extract(str, this.f3068k);
            String strC2 = MapDataStore.extract(str, null);
            String strC3 = MapDataStore.extract(str, "en");
            if (!strC2.equals(strC)) {
                return strC2 + " (" + strC + ")";
            }
            if (strC3.equals(strC2)) {
                return strC2;
            }
            return strC2 + " (" + strC3 + ")";
        }
    }

    public class b extends BroadcastReceiver {
        public b(Context context, l2 l2Var) {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Bundle extras;
            if (!intent.getAction().equals("SFTP_OP_RESULT") || (extras = intent.getExtras()) == null) {
                return;
            }
            String string = extras.getString("OP");
            String string2 = extras.getString("STATUS");
            extras.getString("FNAME");
            v2.e("GPS-M", "==> SFTP Broadcast ricevuto! " + string + " -" + string2);
            if (string == null || string2 == null) {
                return;
            }
            if (string.equals("ACTION_SFTP_CONNECT") && string2.equals("OK")) {
                v2.e("GPS-M", "==> sFTP connect OK");
            } else if (string.equals("ACTION_SFTP_CHANGE_DIR") && string2.equals("OK")) {
                v2.e("GPS-M", "==> SFTP change dir OK");
            } else if (string.equals("SFTP_DOWNLOAD") && string2.equals("OK")) {
                v2.e("GPS-M", "==> sFTP download ended OK");
            } else if (string.equals("SFTP_SD_DEM") && string2.equals("OK")) {
                v2.e("GPS-M", "==> sFTP search& download ended OK ");
                o.g(m2.this.f3057a);
                o.e(m2.this.f3057a);
                m2.f3047l = false;
                MainActivity.setMapZoomLevel((byte) 8);
            } else {
                v2.e("GPS-M", "Unzip failed!!!\n  DEM NOT Loaded");
            }
            LatLong cVar = m2.this.f3067k;
            double d8 = cVar.latitude;
            double d9 = cVar.longitude;
            byte b8 = AgpsApplication.B;
            MapView mapView = MainActivity.M0;
            if (mapView == null) {
                return;
            }
            mapView.setCenter(new LatLong(d8, d9));
            MainActivity.setMapZoomLevel(b8);
        }
    }

    public m2(Context context, Application application, boolean z) {
        double d8;
        androidx.lifecycle.d0.m(0.0d);
        androidx.lifecycle.d0.n(0.0d);
        androidx.lifecycle.d0.m(0.0d);
        androidx.lifecycle.d0.n(0.0d);
        new LatLong(0.0d, 0.0d);
        new LatLong(0.0d, 0.0d);
        this.f3064h++;
        this.f3057a = context;
        this.f3061e = new com.giobat.AgpsTrackerPP.b(context);
        MainActivity.M0.setClickable(true);
        MainActivity.M0.getMapScaleBar().setVisible(false);
        MainActivity.M0.setBuiltInZoomControls(false);
        MainActivity.M0.setZoomLevelMin((byte) 2);
        MainActivity.M0.setZoomLevelMax((byte) 20);
        int rawTileSize = MainActivity.M0.getModel().displayModel.getTileSize();
        int tileSize = rawTileSize > 0 ? rawTileSize : 256;
        d8 = MainActivity.M0.getModel().frameBufferModel.getOverdrawFactor();
        if (d8 <= 0.0d) {
            d8 = 1.25d;
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i7 = Math.max(1, point.y);
        int i8 = Math.max(1, point.x);
        int hypot = (int) Math.hypot(i8, i7);
        org.mapsforge.core.model.Dimension mapDim = new org.mapsforge.core.model.Dimension(hypot, hypot);
        org.mapsforge.core.model.Dimension fbDim = org.mapsforge.map.controller.FrameBufferController.calculateFrameBufferDimension(mapDim, d8);
        int tilesX = 2 + (fbDim.width / tileSize);
        int tilesY = 2 + (fbDim.height / tileSize);
        int minTilesInViewport = Math.max(128, tilesX * tilesY);
        int iMax2 = Math.max(256, minTilesInViewport * 2);
        LOGGER.info("TILECACHE INMEMORY SIZE: " + Integer.toString(iMax2));
        TileCache dVar = new InMemoryTileCache(iMax2);
        this.f3060d = dVar;
        this.tileCache = dVar;
        this.f3058b = new MultiMapDataStore(MultiMapDataStore.DataPolicy.DEDUPLICATE);
        Context context2 = this.f3057a;
        File fileX = MainActivity.X(context2, "World/world.map", 5);
        if (fileX != null) {
            File parent = fileX.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
        }
        try {
            if (fileX != null && (!fileX.exists() || fileX.length() == 0)) {
                InputStream inputStreamOpenRawResource = context2.getResources().openRawResource(R.raw.world);
                FileOutputStream fileOutputStream = new FileOutputStream(fileX);
                byte[] bArr = new byte[4096];
                while (true) {
                    int i9 = inputStreamOpenRawResource.read(bArr);
                    if (i9 <= 0) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, i9);
                    }
                }
                inputStreamOpenRawResource.close();
                fileOutputStream.close();
            }
        } catch (IOException e9) {
            MainActivity.f3625m1.a("getWorldMapFile: " + e9);
            v2.e("GPS-M", "<====================================================================================================>");
            fileX = null;
        }
        this.f3063g = fileX;
        ((ArrayList) f3052r).clear();
        g(f3052r, false);
        Location location = new Location("dummyProvider");
        location.setLatitude(0.0d);
        location.setLongitude(0.0d);
        Drawable initialDrawable = androidx.core.content.ContextCompat.getDrawable(this.f3057a, f3050p);
        if (initialDrawable == null) {
            initialDrawable = androidx.core.content.ContextCompat.getDrawable(this.f3057a, com.giobat.AgpsTrackerPP.R.drawable.pallino_blu);
        }
        m = new k3(new LatLong(0.0d, 0.0d), AndroidGraphicFactory.convertToBitmap(initialDrawable), 0, 0);
        LatLong cVar = new LatLong(0.0d, 0.0d);
        Paint fillPaint = n2.a(Color.argb(10, 0, 0, 255), 1, 1);
        fillPaint.setStyle(org.mapsforge.core.graphics.Style.FILL);
        Paint strokePaint = n2.a(Color.argb(160, 0, 0, 255), 1, 2);
        strokePaint.setStyle(org.mapsforge.core.graphics.Style.STROKE);
        this.f3062f = new Circle(cVar, 0.0f, fillPaint, strokePaint);
        MainActivity.M0.getModel().mapViewPosition.addObserver(new l2(this));
    }

    public static String c(String str) {
        int iIndexOf = str.indexOf(".");
        return iIndexOf == -1 ? XmlPullParser.NO_NAMESPACE : str.substring(0, iIndexOf);
    }

    public static boolean f(LatLong cVar, String str) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
            randomAccessFile.seek(44L);
            double d8 = ((double) randomAccessFile.readInt()) / 1000000.0d;
            double d9 = ((double) randomAccessFile.readInt()) / 1000000.0d;
            double d10 = ((double) randomAccessFile.readInt()) / 1000000.0d;
            double d11 = ((double) randomAccessFile.readInt()) / 1000000.0d;
            randomAccessFile.readShort();
            randomAccessFile.close();
            androidx.lifecycle.d0.m(d8);
            androidx.lifecycle.d0.n(d9);
            androidx.lifecycle.d0.m(d10);
            androidx.lifecycle.d0.n(d11);
            if (d8 > d10) {
                throw new IllegalArgumentException("invalid latitude range: " + d8 + ' ' + d10);
            }
            if (d9 <= d11) {
                double d12 = cVar.latitude;
                double d13 = cVar.longitude;
                return (d8 > d12 ? 1 : (d8 == d12 ? 0 : -1)) <= 0 && (d10 > d12 ? 1 : (d10 == d12 ? 0 : -1)) >= 0 && (d9 > d13 ? 1 : (d9 == d13 ? 0 : -1)) <= 0 && (d11 > d13 ? 1 : (d11 == d13 ? 0 : -1)) >= 0;
            }
            throw new IllegalArgumentException("invalid longitude range: " + d9 + ' ' + d11);
        } catch (Exception e8) {
            v2.e("GPS-M", "=====>File" + str + "Exception:" + e8);
            return false;
        }
    }

    public static void h(Context context) {
        f3056v = new ArrayList<>();
        boolean z = AgpsApplication.f3566h;
        File fileX = MainActivity.X(context, "Lists/mapsList.txt", 4);
        if (!fileX.exists()) {
            return;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileX));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return;
                }
                String[] strArrSplit = line.split(",");
                double d8 = Double.parseDouble(strArrSplit[1]);
                double d9 = Double.parseDouble(strArrSplit[2]);
                double d10 = Double.parseDouble(strArrSplit[3]);
                double d11 = Double.parseDouble(strArrSplit[4]);
                f3056v.add(new r3(strArrSplit[0], Long.parseLong(strArrSplit[5]), new BoundingBox(d8, d9, d10, d11)));
            }
        } catch (Exception e8) {
            androidx.appcompat.widget.d0.c("readServer_MapsList: ", e8, MainActivity.f3625m1);
        }
    }

    public void a() {
        XmlRenderTheme aVar;
        v2.e("GPS-M", "MapsForge: Clear Layers and Add Map Layer ");
        v2.e("GPS-I", "Main tileRendererLayer new");
        x2 x2Var = new x2(this.f3060d, this.f3058b, MainActivity.M0.getModel().mapViewPosition, AndroidGraphicFactory.INSTANCE, this.f3057a, this);
        this.f3059c = x2Var;
        try {
            aVar = MainActivity.f3622j1 ? new AssetsRenderTheme(this.f3057a.getAssets(), "", "Elevate.xml", this) : new AssetsRenderTheme(this.f3057a.getAssets(), "", "gioDefault.xml");
        } catch (Exception e8) {
            androidx.appcompat.widget.d0.c("Theme not found!: ", e8, MainActivity.f3625m1);
            try {
                aVar = new AssetsRenderTheme(this.f3057a.getAssets(), "mapsforge/", "osmarender.xml");
            } catch (Exception e9) {
                aVar = null;
            }
        }
        if (aVar != null) {
            x2Var.setXmlRenderTheme(aVar);
        }
        if (MainActivity.M0.getLayerManager() != null) {
            v2.e("GPS-I", "MapView Layers clear");
            u2 u2Var = MainActivity.f3626n1;
            Objects.requireNonNull(u2Var);
            try {
                u2Var.f3196b.acquire();
            } catch (Exception unused) {
            }
            try {
                u2Var.f3195a.clear(false);
            } catch (Exception unused2) {
            }
            u2Var.f3196b.release();
            this.f3060d.purge();
            v2.e("GPS-I", "MapView Layers redraw");
            MainActivity.f3626n1.a(this.f3059c);
        }
    }

    public boolean b(LatLong cVar, boolean z) {
        f3048n = new ArrayList<>();
        f3049o = new ArrayList<>();
        File mapsDir = MainActivity.X(this.f3057a, "Maps", 2);
        File[] fileArrListFiles = mapsDir != null ? mapsDir.listFiles() : null;
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File mapFileItem : fileArrListFiles) {
                if (mapFileItem == null || !mapFileItem.isFile() || !mapFileItem.getName().toLowerCase().endsWith(".map") || mapFileItem.length() < 100) {
                    continue;
                }
                try (RandomAccessFile randomAccessFile = new RandomAccessFile(mapFileItem, "r")) {
                    randomAccessFile.seek(44L);
                    double d8 = ((double) randomAccessFile.readInt()) / 1000000.0d;
                    double d9 = ((double) randomAccessFile.readInt()) / 1000000.0d;
                    double d10 = ((double) randomAccessFile.readInt()) / 1000000.0d;
                    double d11 = ((double) randomAccessFile.readInt()) / 1000000.0d;
                    f3048n.add(new y(mapFileItem, mapFileItem.getName(), new BoundingBox(d8, d9, d10, d11)));
                } catch (Exception e8) {
                    v2.e("GPS-M", "Error reading header of " + mapFileItem.getName() + ": " + e8);
                }
            }
            ((ArrayList) f3052r).clear();
            StringBuilder fpBuilder = new StringBuilder();
            if (cVar != null && (cVar.latitude != 0.0d || cVar.longitude != 0.0d)) {
                for (y yVar : f3048n) {
                    if (yVar.f3253b != null && yVar.f3253b.contains(cVar) && yVar.f3254c != null && yVar.f3254c.canRead()) {
                        ((ArrayList) f3052r).add(new y2(yVar.f3252a, "Maps", 0L, false, yVar.f3254c.length(), 2));
                        f3049o.add(yVar);
                        fpBuilder.append(yVar.f3252a).append(";").append(yVar.f3254c.length()).append(";");
                    }
                }
            }
            if (((ArrayList) f3052r).isEmpty()) {
                for (y yVar : f3048n) {
                    if (yVar.f3254c != null && yVar.f3254c.canRead()) {
                        ((ArrayList) f3052r).add(new y2(yVar.f3252a, "Maps", 0L, false, yVar.f3254c.length(), 2));
                        f3049o.add(yVar);
                        fpBuilder.append(yVar.f3252a).append(";").append(yVar.f3254c.length()).append(";");
                    }
                }
            }
            String currentFingerprint = fpBuilder.toString();
            boolean mapsChanged = !currentFingerprint.equals(lastLoadedMapFingerprint) || this.f3058b == null;
            if (mapsChanged) {
                lastLoadedMapFingerprint = currentFingerprint;
                g(f3052r, z);
                a();
            }
            return ((ArrayList) f3052r).size() > 0;
        }
        return false;
    }

    public void d(LatLong cVar) {
        h(this.f3057a);
        f3053s.clear();
        if (cVar.latitude != 0.0d && cVar.longitude != 0.0d) {
            for (int i7 = 0; i7 < f3056v.size(); i7++) {
                if (f3056v.get(i7).f3150b.contains(cVar)) {
                    String[] strArrSplit = f3056v.get(i7).f3149a.split("/");
                    String str = strArrSplit[strArrSplit.length - 1];
                    String string = AgpsApplication.f3577t;
                    for (int i8 = 1; i8 < strArrSplit.length - 1; i8++) {
                        StringBuilder sbD = c7.f.d(string, "/");
                        sbD.append(strArrSplit[i8]);
                        string = sbD.toString();
                    }
                    f3053s.add(new y2(str, string, 0L, false, f3056v.get(i7).f3151c.longValue(), 2));
                }
            }
        }
        File[] fileArrListFiles = MainActivity.X(this.f3057a, "Maps", 2).listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                String name = file.getName();
                for (int i9 = 0; i9 < f3053s.size(); i9++) {
                    if (f3053s.get(i9).f3262i.equals(name)) {
                        f3053s.remove(i9);
                    }
                }
            }
        }
        Collections.sort(f3053s);
    }

    public byte e(BoundingBox aVar) {
        byte b8;
        Dimension bVarU = MainActivity.M0.getModel().mapViewDimension.getDimension();
        if (bVarU == null) {
            return (byte) 17;
        }
        long jE = e0.e((byte) 0, MainActivity.M0.getModel().displayModel.getTileSize());
        double dFloor = Math.floor(Math.min((-Math.log(Math.abs(e0.l(aVar.maxLongitude, jE) - e0.l(aVar.minLongitude, jE)) / ((double) bVarU.width))) / Math.log(2.0d), (-Math.log(Math.abs(e0.j(aVar.maxLatitude, jE) - e0.j(aVar.minLatitude, jE)) / ((double) bVarU.height))) / Math.log(2.0d)));
        if (dFloor < 0.0d) {
            b8 = 0;
        } else {
            b8 = dFloor > 127.0d ? (byte) 127 : (byte) dFloor;
        }
        return b8 > 0 ? (byte) (b8 - 1) : b8;
    }

    public boolean g(List<y2> list, boolean z) {
        double d8;
        double d9;
        double d10;
        double d11;
        if (list.size() == 0) {
            return false;
        }
        StringBuilder sbA = android.support.v4.media.b.a("PrepareMultiMapDataStore...: Nmaps=");
        sbA.append(list.size());
        v2.e("GPS-M", sbA.toString());
        MultiMapDataStore cVar = this.f3058b;
        if (cVar != null) {
            cVar.close();
        }
        this.f3058b = new MultiMapDataStore(MultiMapDataStore.DataPolicy.DEDUPLICATE);
        File file = this.f3063g;
        if (file != null && file.canRead()) {
            MapFile worldMap = new MapFile(this.f3063g);
            worldMap.setPriority(-1);
            this.f3058b.addMapDataStore(worldMap, false, false);
        }
        if (list.size() == 0) {
            return true;
        }
        String language = Locale.getDefault().getLanguage();
        Iterator<y2> it = list.iterator();
        boolean z7 = true;
        double d12 = 0.0d;
        double d13 = 0.0d;
        double d14 = 0.0d;
        double d15 = 0.0d;
        while (it.hasNext()) {
            y2 next = it.next();
            try {
                Iterator<y2> it2 = it;
                File fileX = MainActivity.X(this.f3057a, next.a(), next.m);
                if (fileX.canRead()) {
                    v2.e("GPS-M", "Try to use MAP: " + next.f3262i);
                    a aVar = new a(this, fileX, language);
                    if (z7) {
                        d8 = aVar.boundingBox().minLatitude;
                        d9 = aVar.boundingBox().minLongitude;
                        d10 = aVar.boundingBox().maxLatitude;
                        d11 = aVar.boundingBox().maxLongitude;
                    } else {
                        if (d12 > aVar.boundingBox().minLatitude) {
                            d12 = aVar.boundingBox().minLatitude;
                        }
                        if (d13 > aVar.boundingBox().minLongitude) {
                            d13 = aVar.boundingBox().minLongitude;
                        }
                        if (d14 < aVar.boundingBox().maxLatitude) {
                            d14 = aVar.boundingBox().maxLatitude;
                        }
                        if (d15 < aVar.boundingBox().maxLongitude) {
                            d15 = aVar.boundingBox().maxLongitude;
                        }
                        d8 = d12;
                        d9 = d13;
                        d10 = d14;
                        d11 = d15;
                    }
                    this.f3058b.addMapDataStore(aVar, false, false);
                    v2.e("GPS-M", "Added MAP: " + next.f3262i);
                    d15 = d11;
                    d14 = d10;
                    d13 = d9;
                    d12 = d8;
                    z7 = false;
                } else {
                    v2.e("GPS-M", "Error: Can't use MAP: " + next.f3262i);
                }
                it = it2;
            } catch (Exception e8) {
                androidx.appcompat.widget.d0.c("Map loading Error : ", e8, MainActivity.f3625m1);
                return false;
            }
        }
        BoundingBox aVar2 = new BoundingBox(d12, d13, d14, d15);
        if (!z) {
            return true;
        }
        AgpsApplication.B = e(aVar2);
        return true;
    }

    public void i(x3 x3Var) {
        for (int i7 = 0; i7 < x3Var.f3247l.size(); i7++) {
            if (x3Var.f3247l.get(i7).f3001c == null) {
                x3Var.f3247l.get(i7).f3001c = new v3(this.f3057a, x3Var.f3247l.get(i7));
            }
            u2 u2Var = MainActivity.f3626n1;
            v3 v3Var = x3Var.f3247l.get(i7).f3001c;
            Objects.requireNonNull(u2Var);
            try {
                u2Var.f3196b.acquire();
            } catch (Exception unused) {
            }
            try {
                if (!u2Var.f3195a.contains(v3Var)) {
                    u2Var.f3195a.add(v3Var, false);
                }
            } catch (Exception unused2) {
            }
            u2Var.f3196b.release();
        }
    }

    public void j(x3 x3Var, boolean z) {
        if (x3Var == null) {
            return;
        }
        for (q3 q3Var : x3Var.f3236a) {
            if (q3Var.f3129h == null || MainActivity.U0) {
                if (z) {
                    x3 x3Var2 = AgpsApplication.f3574q;
                    int i7 = MainActivity.f3633u1;
                    Objects.requireNonNull(x3Var2);
                    Paint paint = n2.a(MainActivity.f3633u1, MainActivity.f3635w1, 2);
                    paint.setStyle(org.mapsforge.core.graphics.Style.STROKE);
                    q3Var.f3129h = new Polyline(paint, AndroidGraphicFactory.INSTANCE);
                } else {
                    x3 x3Var3 = AgpsApplication.f3575r;
                    int i8 = MainActivity.f3632t1;
                    Objects.requireNonNull(x3Var3);
                    Paint paint = n2.a(MainActivity.f3632t1, MainActivity.f3634v1, 2);
                    paint.setStyle(org.mapsforge.core.graphics.Style.STROKE);
                    q3Var.f3129h = new Polyline(paint, AndroidGraphicFactory.INSTANCE);
                }
            }
            if (q3Var.f3129h.getLatLongs().size() != q3Var.f3122a.size()) {
                u2 u2Var = MainActivity.f3626n1;
                Polyline dVar = q3Var.f3129h;
                CopyOnWriteArrayList<LatLong> copyOnWriteArrayList = q3Var.f3122a;
                Objects.requireNonNull(u2Var);
                // Update polyline data under semaphore to protect Layers list mutation,
                // but DO NOT call requestRedraw() while holding the semaphore.
                // requestRedraw() can signal the rendering thread which may call back into
                // the UI thread, causing a deadlock if the UI thread is also waiting on
                // this semaphore (e.g. y3 animation Runnable).
                try {
                    u2Var.f3196b.acquire();
                } catch (Exception unused) {
                }
                try {
                    synchronized (dVar) {
                        dVar.getLatLongs().clear();
                    }
                    synchronized (dVar) {
                        dVar.getLatLongs().addAll(copyOnWriteArrayList);
                    }
                    if (!u2Var.f3195a.contains(dVar)) {
                        u2Var.f3195a.add(dVar, false);
                    }
                } catch (Exception unused2) {
                }
                u2Var.f3196b.release();
                // Trigger redraw AFTER releasing semaphore to avoid deadlock
                try {
                    dVar.requestRedraw();
                } catch (Exception unused3) {
                }
            } else {
                u2 u2Var2 = MainActivity.f3626n1;
                Polyline dVar2 = q3Var.f3129h;
                Objects.requireNonNull(u2Var2);
                try {
                    u2Var2.f3196b.acquire();
                } catch (Exception unused3) {
                }
                try {
                    if (!u2Var2.f3195a.contains(dVar2)) {
                        u2Var2.f3195a.add(dVar2, false);
                    }
                } catch (Exception unused4) {
                }
                u2Var2.f3196b.release();
            }
        }
    }

    public void k(x3 x3Var, boolean z) {
        int i7;
        for (int i8 = 0; i8 < x3Var.f3237b.size(); i8++) {
            if (x3Var.f3237b.get(i8).f2961e == null) {
                a4 a4Var = x3Var.f3237b.get(i8);
                Context context = this.f3057a;
                a4 a4Var2 = x3Var.f3237b.get(i8);
                if (z) {
                    int iC = a4Var2.f2960d - 1;
                    if (iC == 0) {
                        a4Var2.f2962f = R.drawable.start_icon_bluet;
                    } else if (iC == 1) {
                        a4Var2.f2962f = R.drawable.stop_icon_bluet;
                    } else if (iC == 2) {
                        a4Var2.f2962f = R.drawable.marker_icon_blue;
                    } else if (iC == 3) {
                        a4Var2.f2962f = 0;
                    }
                    i7 = a4Var2.f2962f;
                } else {
                    int iC2 = a4Var2.f2960d - 1;
                    if (iC2 == 0) {
                        a4Var2.f2962f = R.drawable.start_icon_redt;
                    } else if (iC2 == 1) {
                        a4Var2.f2962f = R.drawable.stop_icon_redt;
                    } else if (iC2 == 2) {
                        a4Var2.f2962f = R.drawable.marker_icon_red;
                    } else if (iC2 == 3) {
                        a4Var2.f2962f = 0;
                    }
                    i7 = a4Var2.f2962f;
                }
                a4Var.f2961e = new t3(context, i7, x3Var.f3237b.get(i8));
            }
            u2 u2Var = MainActivity.f3626n1;
            t3 t3Var = x3Var.f3237b.get(i8).f2961e;
            Objects.requireNonNull(u2Var);
            try {
                u2Var.f3196b.acquire();
            } catch (Exception unused) {
            }
            try {
                if (!u2Var.f3195a.contains(t3Var)) {
                    u2Var.f3195a.add(t3Var, false);
                }
            } catch (Exception unused2) {
            }
            u2Var.f3196b.release();
        }
    }

    public void l(Location location) {
        MapView mapView = MainActivity.M0;
        if (mapView == null || mapView.getLayerManager() == null) {
            return;
        }
        if (MainActivity.U0) {
            v2.e("GPS-M", "-------------------------update Map ---------------------------");
            u2 u2Var = MainActivity.f3626n1;
            Objects.requireNonNull(u2Var);
            try {
                u2Var.f3196b.acquire();
            } catch (Exception unused) {
            }
            while (u2Var.f3195a.size() > 1) {
                try {
                    u2Var.f3195a.remove(1, false);
                } catch (Exception unused2) {
                }
            }
            u2Var.f3196b.release();
            f3051q = new CopyOnWriteArrayList<>();
            if (AgpsApplication.f3581y) {
                AgpsApplication.B = (byte) 8;
                MainActivity.M0.setZoomLevel(AgpsApplication.B);
                o.g(this.f3057a);
                o.e(this.f3057a);
                AgpsApplication.f3581y = false;
            }
            if (MainActivity.O1 && MainActivity.f3627o1) {
                f3050p = R.drawable.freccia_blu;
            } else {
                f3050p = R.drawable.pallino_blu;
            }
            Drawable drawableB = ContextCompat.getDrawable(this.f3057a, f3050p);
            if (drawableB != null) {
                m.o(AndroidGraphicFactory.convertToBitmap(drawableB));
            }
            LatLong cVar = MainActivity.V1;
            if (cVar != null) {
                Bitmap bVar = MainActivity.W1;
                MainActivity.U1 = new Marker(cVar, bVar, 0, (-bVar.getHeight()) / 2);
                MainActivity.f3626n1.a(MainActivity.U1);
            }
        }
        x3 x3Var = AgpsApplication.f3575r;
        if (x3Var != null && x3Var.i() > 0) {
            j(AgpsApplication.f3575r, false);
            k(AgpsApplication.f3575r, false);
            i(AgpsApplication.f3575r);
        }
        x3 x3Var2 = AgpsApplication.f3574q;
        if (x3Var2 != null && x3Var2.i() > 0) {
            j(AgpsApplication.f3574q, true);
            k(AgpsApplication.f3574q, true);
            i(AgpsApplication.f3574q);
        }
        if (f3055u) {
            if (MainActivity.f3617e1 != null) {
                Context context = this.f3057a;
                byte b8 = AgpsApplication.B;
                if (b8 >= 6 && b8 <= 8) {
                    try {
                        MainActivity.f3617e1.a();
                        MainActivity.f3617e1.b(context);
                    } catch (Exception e8) {
                        androidx.appcompat.widget.d0.c("ShowCountries:", e8, MainActivity.f3625m1);
                    }
                }
            }
            f3055u = false;
        }
        if (location != null) {
            LatLong cVar2 = new LatLong(location.getLatitude(), location.getLongitude());
            float f7 = MainActivity.D1;
            if (m != null && MainActivity.f3626n1 != null) {
                m.p(cVar2);
                if (MainActivity.O1) {
                    if (MainActivity.M1) {
                        m.f3037q = MainActivity.N1;
                    } else {
                        m.f3037q = MainActivity.N1;
                    }
                }
                Drawable drawableB2 = ContextCompat.getDrawable(this.f3057a, f3050p);
                if (drawableB2 != null) {
                    m.o(AndroidGraphicFactory.convertToBitmap(drawableB2));
                }
                k3 k3Var = m;
                k3Var.setVisible(true);
                k3Var.requestRedraw();
                m.p(cVar2);
                u2 u2Var2 = MainActivity.f3626n1;
                k3 k3Var2 = m;
                Objects.requireNonNull(u2Var2);
                try {
                    u2Var2.f3196b.acquire();
                } catch (Exception unused3) {
                }
                try {
                    k3Var2.p(cVar2);
                    if (!u2Var2.f3195a.contains(k3Var2)) {
                        u2Var2.f3195a.add(k3Var2, false);
                    }
                } catch (Exception unused4) {
                }
                u2Var2.f3196b.release();
            }
            Circle aVar = this.f3062f;
            if (aVar != null) {
                u2 u2Var3 = MainActivity.f3626n1;
                Objects.requireNonNull(u2Var3);
                try {
                    u2Var3.f3196b.acquire();
                } catch (Exception unused5) {
                }
                try {
                    synchronized (aVar) {
                        aVar.setLatLong(cVar2);
                    }
                    synchronized (aVar) {
                        aVar.setRadius(f7);
                    }
                    if (!u2Var3.f3195a.contains(aVar)) {
                        u2Var3.f3195a.add(aVar, false);
                    }
                } catch (Exception unused6) {
                }
                u2Var3.f3196b.release();
            }
        }
        boolean overlayChanged = (lastOverlayPosition == null)
            || (location != null && (Math.abs(lastOverlayPosition.latitude - location.getLatitude()) > 0.000005d
                || Math.abs(lastOverlayPosition.longitude - location.getLongitude()) > 0.000005d))
            || (Math.abs(lastOverlayRadius - MainActivity.D1) > 1.0f);
        if (overlayChanged && location != null) {
            lastOverlayPosition = new LatLong(location.getLatitude(), location.getLongitude());
            lastOverlayRadius = MainActivity.D1;
        }
        MainActivity.U0 = false;
        if (overlayChanged) {
            u2 u2Var4 = MainActivity.f3626n1;
            Objects.requireNonNull(u2Var4);
            try {
                u2Var4.f3196b.acquire();
            } catch (Exception unused7) {
            }
            try {
                MainActivity.M0.getLayerManager().redrawLayers();
            } catch (Exception unused8) {
            }
            u2Var4.f3196b.release();
        }
    }

    public byte m(x3 x3Var) {
        s sVar = x3Var.f3245j;
        double d8 = sVar.f3163l;
        double d9 = sVar.m;
        double d10 = sVar.f3161j;
        double d11 = sVar.f3162k;
        androidx.lifecycle.d0.m(d8);
        androidx.lifecycle.d0.n(d9);
        androidx.lifecycle.d0.m(d10);
        androidx.lifecycle.d0.n(d11);
        if (d8 > d10) {
            throw new IllegalArgumentException("invalid latitude range: " + d8 + ' ' + d10);
        }
        if (d9 > d11) {
            throw new IllegalArgumentException("invalid longitude range: " + d9 + ' ' + d11);
        }
        s sVar2 = x3Var.f3245j;
        byte bE = e(new BoundingBox(sVar2.f3163l, sVar2.m, sVar2.f3161j, sVar2.f3162k));
        MapViewPosition mapViewPosition = MainActivity.M0.getModel().mapViewPosition;
        LatLong cVar2 = new LatLong(d8 + ((d10 - d8) / 2.0d), d9 + ((d11 - d9) / 2.0d));
        if (bE < 0) {
            throw new IllegalArgumentException(androidx.appcompat.widget.f0.a("zoomLevel must not be negative: ", bE));
        }
        synchronized (mapViewPosition) {
            mapViewPosition.setCenter(cVar2);
            mapViewPosition.setZoomLevel(bE);
        }
        return bE;
    }
}
