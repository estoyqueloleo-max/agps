package androidx.appcompat.widget;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class DocumentationUrlTest {

    @Test
    public void testSpanishDocumentationUrls() {
        assertEquals("https://estoyqueloleo-max.github.io/agps/help_es.html",
                p.resolveDocumentationUrl(3, "es"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/index_es.html",
                p.resolveDocumentationUrl(2, "es"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/help_es.html#geographic_maps",
                p.resolveDocumentationUrl(5, "es"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/help_es.html#geographic_maps",
                p.resolveDocumentationUrl(6, "es"));
    }

    @Test
    public void testItalianDocumentationUrls() {
        assertEquals("https://estoyqueloleo-max.github.io/agps/help_it.html",
                p.resolveDocumentationUrl(3, "it"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/index_it.html",
                p.resolveDocumentationUrl(2, "it"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/help_it.html#geographic_maps",
                p.resolveDocumentationUrl(5, "it"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/help_it.html#geographic_maps",
                p.resolveDocumentationUrl(6, "it"));
    }

    @Test
    public void testFrenchDocumentationUrls() {
        assertEquals("https://estoyqueloleo-max.github.io/agps/help_fr.html",
                p.resolveDocumentationUrl(3, "fr"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/index_fr.html",
                p.resolveDocumentationUrl(2, "fr"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/help_fr.html#geographic_maps",
                p.resolveDocumentationUrl(5, "fr-FR"));
    }

    @Test
    public void testPortugueseDocumentationUrls() {
        assertEquals("https://estoyqueloleo-max.github.io/agps/help_pt.html",
                p.resolveDocumentationUrl(3, "pt"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/index_pt.html",
                p.resolveDocumentationUrl(2, "pt"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/help_pt.html#geographic_maps",
                p.resolveDocumentationUrl(6, "pt_BR"));
    }

    @Test
    public void testRussianDocumentationUrls() {
        assertEquals("https://estoyqueloleo-max.github.io/agps/help_ru.html",
                p.resolveDocumentationUrl(3, "ru"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/index_ru.html",
                p.resolveDocumentationUrl(2, "ru"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/help_ru.html#geographic_maps",
                p.resolveDocumentationUrl(5, "ru-RU"));
    }

    @Test
    public void testEnglishAndFallbackDocumentationUrls() {
        assertEquals("https://estoyqueloleo-max.github.io/agps/help.html",
                p.resolveDocumentationUrl(3, "en"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/index.html",
                p.resolveDocumentationUrl(2, "en"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/help.html",
                p.resolveDocumentationUrl(3, "de"));
        assertEquals("https://estoyqueloleo-max.github.io/agps/index.html",
                p.resolveDocumentationUrl(2, "ja"));
    }

    @Test
    public void testExternalMapUrlUnchanged() {
        assertEquals("https://openandromaps.org/en/downloads",
                p.resolveDocumentationUrl(4, "es"));
        assertEquals("https://openandromaps.org/en/downloads",
                p.resolveDocumentationUrl(4, "it"));
        assertEquals("https://openandromaps.org/en/downloads",
                p.resolveDocumentationUrl(4, "en"));
    }

    @Test
    public void testLanguageSuffixHelper() {
        assertEquals("_it", p.c(2));
        assertEquals("_es", p.c(3));
        assertEquals("_fr", p.c(4));
        assertEquals("_ru", p.c(5));
        assertEquals("_pt", p.c(7));
        assertEquals("", p.c(1));
    }
}
