package b1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import v5.b;

/**
 * NASA WorldWind XML Document utility.
 */
public class a {

    public static DocumentBuilder a(boolean namespaceAware) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(namespaceAware);
        try {
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        } catch (Exception ignored) {
        }
        try {
            return factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("ParserConfigurationException", e);
        }
    }

    public static Document b(String path, Class<?> cls) {
        if (path == null) {
            throw new IllegalArgumentException("File path is null");
        }
        File file = new File(path);
        InputStream inputStream = null;
        try {
            if (file.exists()) {
                inputStream = new FileInputStream(file);
            } else {
                inputStream = b.class.getResourceAsStream("/" + path);
            }
        } catch (Exception e) {
            throw new RuntimeException("Cannot open file: " + path, e);
        }
        if (inputStream != null) {
            return c(inputStream);
        }
        return null;
    }

    public static Document c(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream is null");
        }
        try {
            return a(true).parse(inputStream);
        } catch (IOException | SAXException e) {
            throw new RuntimeException("Exception attempting to parse XML", e);
        }
    }

    public static Document d(URL url) {
        try {
            return c(url.openStream());
        } catch (IOException e) {
            throw new RuntimeException("Exception attempting to parse XML from URL: " + url, e);
        }
    }
}
