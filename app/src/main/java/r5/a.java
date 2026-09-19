package r5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.logging.Level;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import v5.b;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static a f17635c = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Properties f17636a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<Document> f17637b = new ArrayList<>();

    public a() {
        Properties properties = new Properties();
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        if (timeZone != null) {
            properties.setProperty("gov.nasa.worldwind.avkey.InitialLongitude", Double.toString(s5.a.a((((double) timeZone.getOffset(System.currentTimeMillis())) * 180.0d) / 4.32E7d).f17761h));
        }
        this.f17636a = properties;
        try {
            if (System.getProperty("gov.nasa.worldwind.app.config.document") != null) {
                b(System.getProperty("gov.nasa.worldwind.app.config.document"));
            }
        } catch (Exception unused) {
            v5.a.e("gov.nasa.worldwind").log(Level.WARNING, "Configuration.ConfigNotFound", System.getProperty("gov.nasa.worldwind.app.config.document"));
        }
        try {
            b(System.getProperty("gov.nasa.worldwind.config.document", "config/worldwind.xml"));
            for (int size = this.f17637b.size() - 1; size >= 0; size--) {
                c(this.f17637b.get(size));
            }
        } catch (Exception unused2) {
            v5.a.e("gov.nasa.worldwind").log(Level.WARNING, "Configuration.ConfigNotFound", System.getProperty("gov.nasa.worldwind.config.document"));
        }
        String property = System.getProperty("gov.nasa.worldwind.config.file", "config/worldwind.properties");
        InputStream fileInputStream = null;
        try {
            File file = new File(property);
            if (file.exists()) {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (FileNotFoundException unused3) {
                    v5.a.e("gov.nasa.worldwind").log(Level.FINEST, "Configuration.LocalConfigFileNotFound", property);
                }
            }
            fileInputStream = fileInputStream == null ? a.class.getResourceAsStream("/" + property) : fileInputStream;
            if (fileInputStream != null) {
                this.f17636a.load(fileInputStream);
            }
        } catch (IOException e8) {
            v5.a.e("gov.nasa.worldwind").log(Level.SEVERE, "Configuration.ExceptionReadingPropsFile", (Throwable) e8);
        }
    }

    public static synchronized String a(String str) {
        String property;
        property = f17635c.f17636a.getProperty(str);
        return property != null ? property.toString() : null;
    }

    private static boolean isEmpty(Object obj) {
        return obj == null || ((obj instanceof String) && ((String) obj).length() == 0);
    }

    public final void b(Object docSource) {
        URL url;
        Document documentD;
        if (isEmpty(docSource)) {
            return;
        }
        if (docSource == null) {
            throw new IllegalArgumentException(v5.a.a("nullValue.DocumentSourceIsNull"));
        }
        if (docSource instanceof URL) {
            documentD = b1.a.d((URL) docSource);
        } else if (docSource instanceof InputStream) {
            documentD = b1.a.c((InputStream) docSource);
        } else {
            String path;
            if (docSource instanceof File) {
                path = ((File) docSource).getPath();
            } else {
                path = docSource.toString();
                try {
                    url = new URL(path);
                } catch (Exception unused) {
                    url = null;
                }
                if (url != null) {
                    documentD = b1.a.d(url);
                    if (documentD != null) {
                        this.f17637b.add(documentD);
                    }
                    return;
                }
            }
            documentD = b1.a.b(path, null);
        }
        if (documentD != null) {
            this.f17637b.add(documentD);
        }
    }

    public final void c(Document document) {
        try {
            XPath xPathNewXPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPathNewXPath.evaluate("/WorldWindConfiguration/Property", document, XPathConstants.NODESET);
            if (nodeList != null && nodeList.getLength() != 0) {
                for (int i7 = 0; i7 < nodeList.getLength(); i7++) {
                    Node nodeItem = nodeList.item(i7);
                    String strEvaluate = xPathNewXPath.evaluate("@name", nodeItem);
                    String strEvaluate2 = xPathNewXPath.evaluate("@value", nodeItem);
                    if (!isEmpty(strEvaluate)) {
                        this.f17636a.setProperty(strEvaluate, strEvaluate2);
                    }
                }
            }
        } catch (XPathExpressionException unused) {
            v5.a.e("gov.nasa.worldwind").log(Level.WARNING, "XML.ParserConfigurationException");
        }
    }
}
