package c2;

import android.content.Context;
import android.content.Intent;
import androidx.documentfile.provider.DocumentFile;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.location.Location;
import android.net.Uri;
import com.giobat.AgpsTrackerPP.MainActivity;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3130a;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Uri f3138i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f3140k;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f3131b = XmlPullParser.NO_NAMESPACE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public CopyOnWriteArrayList<Location> f3132c = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Vector<a4> f3133d = new Vector<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Vector<Boolean> f3134e = new Vector<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Vector<String> f3135f = new Vector<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Vector<String> f3136g = new Vector<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3137h = 0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final SimpleDateFormat f3139j = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);

    public r(Context context) {
        this.f3130a = context;
    }

    /* JADX WARN: Code duplicated, block: B:140:0x0402  */
    /* JADX WARN: Code duplicated, block: B:142:0x042b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:143:0x042c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:145:0x042e A[RETURN] */
    public boolean a(InputStream inputStream, Context context) {
        this.f3131b = XmlPullParser.NO_NAMESPACE;
        this.f3132c.clear();
        this.f3133d.clear();
        this.f3134e.clear();
        this.f3135f.clear();
        this.f3136g.clear();
        this.f3137h = 0;
        this.f3140k = false;

        if (inputStream == null) {
            return false;
        }

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, null);

            String currentTrackName = "";
            String currentTrackDesc = "";
            String currentSegName = "";
            String currentSegDesc = "";
            String currentWptName = "";
            String currentWptDesc = "";

            boolean inMetadata = false;
            boolean inTrk = false;
            boolean inRte = false;
            boolean inTrkseg = false;
            boolean inTrkpt = false;
            boolean inRtept = false;
            boolean inWpt = false;

            String currentTag = "";
            StringBuilder currentText = new StringBuilder();

            double currentLat = 0.0d;
            double currentLon = 0.0d;
            double currentEle = 0.0d;
            String currentTime = "2000-01-01T00:00:00Z";

            int segPointCount = 0;

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String rawName = parser.getName();
                String tagName = "";
                if (rawName != null) {
                    tagName = rawName.toLowerCase(Locale.US);
                    int colonIdx = tagName.indexOf(':');
                    if (colonIdx >= 0 && colonIdx < tagName.length() - 1) {
                        tagName = tagName.substring(colonIdx + 1);
                    }
                }

                if (eventType == XmlPullParser.START_TAG) {
                    currentTag = tagName;
                    currentText.setLength(0);

                    if ("metadata".equals(currentTag)) {
                        inMetadata = true;
                    } else if ("trk".equals(currentTag)) {
                        inTrk = true;
                        currentTrackName = "";
                        currentTrackDesc = "";
                    } else if ("rte".equals(currentTag)) {
                        inRte = true;
                        currentTrackName = "";
                        currentTrackDesc = "";
                    } else if ("trkseg".equals(currentTag)) {
                        inTrkseg = true;
                        currentSegName = currentTrackName;
                        currentSegDesc = currentTrackDesc;
                        segPointCount = 0;
                    } else if ("trkpt".equals(currentTag)) {
                        inTrkpt = true;
                        currentLat = parseCoordinate(parser.getAttributeValue(null, "lat"));
                        currentLon = parseCoordinate(parser.getAttributeValue(null, "lon"));
                        currentEle = 0.0d;
                        currentTime = "2000-01-01T00:00:00Z";
                    } else if ("rtept".equals(currentTag)) {
                        inRtept = true;
                        currentLat = parseCoordinate(parser.getAttributeValue(null, "lat"));
                        currentLon = parseCoordinate(parser.getAttributeValue(null, "lon"));
                        currentEle = 0.0d;
                        currentTime = "2000-01-01T00:00:00Z";
                    } else if ("wpt".equals(currentTag)) {
                        inWpt = true;
                        currentLat = parseCoordinate(parser.getAttributeValue(null, "lat"));
                        currentLon = parseCoordinate(parser.getAttributeValue(null, "lon"));
                        currentEle = 0.0d;
                        currentTime = "2000-01-01T00:00:00Z";
                        currentWptName = "";
                        currentWptDesc = "";
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    if (parser.getText() != null) {
                        currentText.append(parser.getText());
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    String tag = tagName;
                    String text = currentText.toString().trim();

                    if ("metadata".equals(tag)) {
                        inMetadata = false;
                    } else if ("trk".equals(tag)) {
                        inTrk = false;
                        if (!inTrkseg && segPointCount > 0) {
                            if (!this.f3134e.isEmpty()) {
                                this.f3134e.set(this.f3134e.size() - 1, Boolean.TRUE);
                            }
                            this.f3135f.add(currentTrackName.isEmpty() ? "Track" : currentTrackName);
                            this.f3136g.add(currentTrackDesc);
                            segPointCount = 0;
                        }
                    } else if ("rte".equals(tag)) {
                        inRte = false;
                        if (segPointCount > 0) {
                            if (!this.f3134e.isEmpty()) {
                                this.f3134e.set(this.f3134e.size() - 1, Boolean.TRUE);
                            }
                            this.f3135f.add(currentTrackName.isEmpty() ? "Route" : currentTrackName);
                            this.f3136g.add(currentTrackDesc);
                            segPointCount = 0;
                        }
                    } else if ("trkseg".equals(tag)) {
                        inTrkseg = false;
                        if (segPointCount > 0) {
                            if (!this.f3134e.isEmpty()) {
                                this.f3134e.set(this.f3134e.size() - 1, Boolean.TRUE);
                            }
                            this.f3135f.add(currentSegName.isEmpty() ? currentTrackName : currentSegName);
                            this.f3136g.add(currentSegDesc.isEmpty() ? currentTrackDesc : currentSegDesc);
                            segPointCount = 0;
                        }
                    } else if ("trkpt".equals(tag) || "rtept".equals(tag)) {
                        Location loc = new Location("trk");
                        loc.setLatitude(currentLat);
                        loc.setLongitude(currentLon);
                        loc.setAltitude(currentEle);
                        loc.setTime(b(currentTime).getTime());
                        this.f3132c.add(loc);
                        this.f3134e.add(Boolean.FALSE);
                        this.f3137h++;
                        segPointCount++;
                        inTrkpt = false;
                        inRtept = false;
                    } else if ("wpt".equals(tag)) {
                        Location loc = new Location("WPT");
                        loc.setLatitude(currentLat);
                        loc.setLongitude(currentLon);
                        loc.setAltitude(currentEle);
                        loc.setTime(b(currentTime).getTime());
                        this.f3133d.add(new a4(currentWptName, currentWptDesc, loc, 3));
                        inWpt = false;
                    } else if ("name".equals(tag)) {
                        if (inWpt) {
                            currentWptName = text;
                        } else if (inTrkseg) {
                            currentSegName = text;
                        } else if (inTrk || inRte) {
                            currentTrackName = text;
                        } else if (inMetadata && this.f3131b.isEmpty()) {
                            this.f3131b = text;
                        }
                    } else if ("desc".equals(tag)) {
                        if (inWpt) {
                            currentWptDesc = text;
                        } else if (inTrkseg) {
                            currentSegDesc = text;
                        } else if (inTrk || inRte) {
                            currentTrackDesc = text;
                        } else if (inMetadata) {
                            this.f3131b = text;
                        }
                    } else if ("ele".equals(tag)) {
                        if (inTrkpt || inRtept || inWpt) {
                            try {
                                currentEle = Double.parseDouble(text.replace(',', '.'));
                            } catch (Exception ignored) {
                            }
                        }
                    } else if ("time".equals(tag)) {
                        if (inTrkpt || inRtept || inWpt) {
                            currentTime = text;
                        }
                    }

                    currentTag = "";
                    currentText.setLength(0);
                }

                eventType = parser.next();
            }

            inputStream.close();

            if (!this.f3132c.isEmpty()) {
                while (this.f3134e.size() < this.f3132c.size()) {
                    this.f3134e.add(Boolean.FALSE);
                }
                this.f3134e.set(this.f3134e.size() - 1, Boolean.TRUE);

                if (this.f3135f.isEmpty()) {
                    this.f3135f.add(currentTrackName.isEmpty() ? "Track 1" : currentTrackName);
                }
                if (this.f3136g.isEmpty()) {
                    this.f3136g.add(currentTrackDesc);
                }
                while (this.f3136g.size() < this.f3135f.size()) {
                    this.f3136g.add("");
                }
                while (this.f3135f.size() < this.f3136g.size()) {
                    this.f3135f.add("Track");
                }
            }

            v2.e("GPS-M", "Found Ntrk=" + this.f3132c.size() + "    Nway=" + this.f3133d.size());
            return (this.f3137h > 0 || !this.f3133d.isEmpty());

        } catch (Exception e) {
            String err = "XML Pull parser failed: " + e;
            if (MainActivity.f3625m1 != null) {
                MainActivity.f3625m1.a(err);
            }
            v2.e("GPS-M", err);
            return false;
        }
    }

    private double parseCoordinate(String str) {
        if (str == null) return 0.0d;
        try {
            return Double.parseDouble(str.trim().replace(',', '.'));
        } catch (Exception e) {
            return 0.0d;
        }
    }

    public Date b(String str) {
        if (str == null || str.trim().isEmpty()) {
            return new Date(0L);
        }
        str = str.trim();
        String[] patterns = new String[]{
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "yyyy-MM-dd'T'HH:mm:ssXXX",
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                "yyyy-MM-dd'T'HH:mm:ss'Z'",
                "yyyy-MM-dd'T'HH:mm:ss",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd"
        };
        for (String pattern : patterns) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                return sdf.parse(str);
            } catch (Exception ignored) {
            }
        }
        if (!this.f3140k) {
            this.f3140k = true;
            if (MainActivity.f3625m1 != null) {
                MainActivity.f3625m1.a("Time/Date format not understood: " + str);
            }
        }
        return new Date(0L);
    }

    public final void c(boolean z, String str, String str2, int i7) {
        v2.e("GPS-M", "Gpxutil: NotifyFinish reading GPX file=" + str + " resultOK=" + z);
        Intent intent = new Intent(str2);
        intent.putExtra("TRACK_TYPE", i7);
        intent.putExtra("filename", str);
        intent.putExtra("resultOK", z);
        LocalBroadcastManager.getInstance(this.f3130a).sendBroadcast(intent);
    }

    public void d(Uri uri, String str, String str2, int i7) {
        if (uri != null) {
            this.f3138i = uri;
            v2.e("GPS-M", "Start Read and Parse of " + str2);
            Context context = this.f3130a;
            v2.e("GPS-M", "readAndParseUri: name= " + str2);
            try {
                InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
                if (a(inputStreamOpenInputStream, context)) {
                    c(true, str2, str, i7);
                } else {
                    c(false, str2, str, i7);
                }
                inputStreamOpenInputStream.close();
            } catch (IOException unused) {
                c(false, str2, str, i7);
            }
            MainActivity.U0 = true;
        }
    }

    public final String e(String str) {
        return str == null ? "?" : str.replace("&", "-").replace("\"", "-").replace("'", "-").replace("<", "-").replace(">", "-");
    }

    public void f(DocumentFile aVar, x3 x3Var, String str, String str2, String str3) {
        String strE = e(str);
        String strE2 = e(str3);
        String strE3 = e(x3Var.f3248n);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.f3130a.getContentResolver().openOutputStream(aVar.getUri())))) {
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n");
            bufferedWriter.write("<gpx\n xmlns=\"http://www.topografix.com/GPX/1/1\" \n creator= \"" + strE + str2 + "\"\n version= \"1.1\"\n xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\">");
            bufferedWriter.write("\n<metadata>");
            StringBuilder sb = new StringBuilder();
            sb.append("\n\t<name>");
            sb.append(strE2);
            sb.append("</name>");
            bufferedWriter.write(sb.toString());
            bufferedWriter.write("\n\t<desc>" + strE3 + "</desc>");
            bufferedWriter.write("\n</metadata>\n");
            for (a4 a4Var : x3Var.f3237b) {
                if (a4Var.f2960d == 3) {
                    i(bufferedWriter, a4Var);
                }
            }
            for (q3 q3Var : x3Var.f3236a) {
                if (q3Var.d() > 1) {
                    bufferedWriter.write("\n<trk>");
                    bufferedWriter.write("\n\t<name>" + e(q3Var.f3126e) + "</name>");
                    bufferedWriter.write("\n\t<desc>" + e(null) + "</desc>");
                    bufferedWriter.write("\n<trkseg>\n");
                    for (int i7 = 0; i7 < q3Var.d(); i7++) {
                        h(bufferedWriter, q3Var.f3123b.get(i7));
                    }
                    bufferedWriter.write("\t</trkseg>\n");
                    bufferedWriter.write("</trk>\n");
                }
            }
            bufferedWriter.write("</gpx>");
        } catch (Exception e8) {
            MainActivity.f3625m1.a("Error writing GPX1:" + e8);
        }
    }

    public void g(File file, x3 x3Var, String str, String str2, String str3, String str4) {
        String strE = e(str);
        String strE2 = e(str3);
        String strE3 = e(str4);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n");
            bufferedWriter.write("<gpx\n xmlns=\"http://www.topografix.com/GPX/1/1\" \n creator= \"" + strE + str2 + "\"\n version= \"1.1\"\n xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\">");
            bufferedWriter.write("\n<metadata>");
            StringBuilder sb = new StringBuilder();
            sb.append("\n\t<name>");
            sb.append(strE2);
            sb.append("</name>");
            bufferedWriter.write(sb.toString());
            bufferedWriter.write("\n\t<desc>" + strE3 + "</desc>");
            bufferedWriter.write("\n</metadata>\n");
            for (a4 a4Var : x3Var.f3237b) {
                if (a4Var.f2960d == 3) {
                    i(bufferedWriter, a4Var);
                }
            }
            for (q3 q3Var : x3Var.f3236a) {
                if (q3Var.d() > 1) {
                    bufferedWriter.write("\n<trk>");
                    bufferedWriter.write("\n\t<name>" + e(q3Var.f3126e) + "</name>");
                    bufferedWriter.write("\n\t<desc>" + e(null) + "</desc>");
                    bufferedWriter.write("\n<trkseg>\n");
                    for (int i7 = 0; i7 < q3Var.d(); i7++) {
                        h(bufferedWriter, q3Var.f3123b.get(i7));
                    }
                    bufferedWriter.write("\t</trkseg>\n");
                    bufferedWriter.write("</trk>\n");
                }
            }
            bufferedWriter.write("</gpx>");
            bufferedWriter.close();
        } catch (Exception e8) {
            MainActivity.f3625m1.a("Error writing GPX2:" + e8);
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    public void h(BufferedWriter bufferedWriter, Location location) throws IOException {
        Locale locale = Locale.US;
        new DecimalFormatSymbols(locale).setDecimalSeparator('.');
        StringBuffer stringBuffer = new StringBuffer();
        NumberFormat decimalFormat = DecimalFormat.getInstance(locale);
        ((DecimalFormat) decimalFormat).applyPattern("##0.00000000");
        NumberFormat decimalFormat2 = DecimalFormat.getInstance(locale);
        ((DecimalFormat) decimalFormat2).applyPattern("####0.0");
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        double altitude = location.getAltitude();
        long time = location.getTime();
        this.f3139j.setTimeZone(TimeZone.getTimeZone("utc"));
        stringBuffer.append("\t<trkpt lat=\"" + decimalFormat.format(latitude) + "\" ");
        stringBuffer.append("lon=\"" + decimalFormat.format(longitude) + "\">");
        stringBuffer.append("<ele>" + decimalFormat2.format(altitude) + "</ele>");
        stringBuffer.append("<time>" + this.f3139j.format(Long.valueOf(time)) + "</time>");
        stringBuffer.append("</trkpt>\n");
        bufferedWriter.write(stringBuffer.toString());
    }

    public void i(BufferedWriter bufferedWriter, a4 a4Var) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        Locale locale = Locale.US;
        NumberFormat decimalFormat = DecimalFormat.getInstance(locale);
        ((DecimalFormat) decimalFormat).applyPattern("##0.00000000");
        NumberFormat decimalFormat2 = DecimalFormat.getInstance(locale);
        ((DecimalFormat) decimalFormat2).applyPattern("####0.0");
        double latitude = a4Var.f2959c.getLatitude();
        double longitude = a4Var.f2959c.getLongitude();
        double altitude = a4Var.f2959c.getAltitude();
        long time = a4Var.f2959c.getTime();
        StringBuilder sbA = new StringBuilder("\t<wpt lat=\"");
        sbA.append(decimalFormat.format(latitude));
        sbA.append("\" ");
        stringBuffer.append(sbA.toString());
        stringBuffer.append("lon=\"" + decimalFormat.format(longitude) + "\">");
        stringBuffer.append("<ele>" + decimalFormat2.format(altitude) + "</ele>");
        this.f3139j.setTimeZone(TimeZone.getTimeZone("utc"));
        stringBuffer.append("<time>" + this.f3139j.format(Long.valueOf(time)) + "</time>");
        stringBuffer.append("<name>" + e(a4Var.f2957a) + "</name>");
        stringBuffer.append("<desc>" + e(a4Var.f2958b) + "</desc>");
        stringBuffer.append("</wpt>\n");
        bufferedWriter.write(stringBuffer.toString());
    }
}
