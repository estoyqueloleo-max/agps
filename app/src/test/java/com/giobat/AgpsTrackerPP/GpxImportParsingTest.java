package com.giobat.AgpsTrackerPP;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import c2.r;
import c2.x3;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class GpxImportParsingTest {

    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        MainActivity.f3625m1 = new c2.s3();
        MainActivity.W0 = new java.util.concurrent.Semaphore(1);
        MainActivity activity = org.robolectric.Robolectric.buildActivity(MainActivity.class).create().get();
        MainActivity.Z0 = activity;
    }

    @Test
    public void testStandardGpxTrack() {
        String gpx = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<gpx version=\"1.1\" creator=\"TestApp\" xmlns=\"http://www.topografix.com/GPX/1/1\">\n" +
                "  <metadata>\n" +
                "    <name>Madrid Walk</name>\n" +
                "    <desc>A nice walk in Madrid</desc>\n" +
                "  </metadata>\n" +
                "  <trk>\n" +
                "    <name>Madrid Track</name>\n" +
                "    <desc>Sample Track</desc>\n" +
                "    <trkseg>\n" +
                "      <trkpt lat=\"40.4168\" lon=\"-3.7038\">\n" +
                "        <ele>650.0</ele>\n" +
                "        <time>2026-09-17T10:00:00Z</time>\n" +
                "      </trkpt>\n" +
                "      <trkpt lat=\"40.4178\" lon=\"-3.7048\">\n" +
                "        <ele>655.5</ele>\n" +
                "        <time>2026-09-17T10:05:00Z</time>\n" +
                "      </trkpt>\n" +
                "      <trkpt lat=\"40.4188\" lon=\"-3.7058\">\n" +
                "        <ele>660.0</ele>\n" +
                "        <time>2026-09-17T10:10:00Z</time>\n" +
                "      </trkpt>\n" +
                "    </trkseg>\n" +
                "  </trk>\n" +
                "</gpx>";

        r gpxReader = new r(context);
        boolean success = false;
        try {
            success = gpxReader.a(new ByteArrayInputStream(gpx.getBytes(StandardCharsets.UTF_8)), context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("DEBUG: success=" + success + " nTrk=" + gpxReader.f3132c.size() + " nWay=" + gpxReader.f3133d.size() + " h=" + gpxReader.f3137h);
        System.out.println("LOGS: " + MainActivity.f3625m1.f3170i);
        Assert.assertTrue("Should parse standard GPX successfully", success);
        Assert.assertEquals(3, gpxReader.f3132c.size());
        Assert.assertEquals(40.4168, gpxReader.f3132c.get(0).getLatitude(), 0.0001);
        Assert.assertEquals(-3.7038, gpxReader.f3132c.get(0).getLongitude(), 0.0001);
        Assert.assertEquals(650.0, gpxReader.f3132c.get(0).getAltitude(), 0.1);

        // Also test loading into x3
        x3 track = new x3(2);
        boolean loaded = track.a("Madrid Track", gpxReader);
        Assert.assertTrue("Should load into x3 successfully", loaded);
        Assert.assertEquals(3, track.i());
    }

    @Test
    public void testGpxWithoutElevationOrTime() {
        // Many export tools omit <ele> or <time>, or format them loosely
        String gpx = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<gpx version=\"1.1\" creator=\"Wikiloc\" xmlns=\"http://www.topografix.com/GPX/1/1\">\n" +
                "  <trk>\n" +
                "    <name>Track Without Ele</name>\n" +
                "    <trkseg>\n" +
                "      <trkpt lat=\"40.4168\" lon=\"-3.7038\"/>\n" +
                "      <trkpt lat=\"40.4178\" lon=\"-3.7048\"/>\n" +
                "    </trkseg>\n" +
                "  </trk>\n" +
                "</gpx>";

        r gpxReader = new r(context);
        boolean success = gpxReader.a(new ByteArrayInputStream(gpx.getBytes(StandardCharsets.UTF_8)), context);

        Assert.assertTrue("Should parse GPX without elevation or time", success);
        Assert.assertEquals(2, gpxReader.f3132c.size());
    }

    @Test
    public void testGpxWithExtensionsAndOtherTags() {
        // Modern GPX files often include <extensions>, Garmin trackpoint extensions (hr, cad, temp), etc.
        String gpx = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<gpx version=\"1.1\" creator=\"Garmin Connect\" xmlns=\"http://www.topografix.com/GPX/1/1\" xmlns:gpxtpx=\"http://www.garmin.com/xmlschemas/TrackPointExtension/v1\">\n" +
                "  <trk>\n" +
                "    <name>Garmin Run</name>\n" +
                "    <trkseg>\n" +
                "      <trkpt lat=\"40.4168\" lon=\"-3.7038\">\n" +
                "        <ele>650.0</ele>\n" +
                "        <time>2026-09-17T10:00:00Z</time>\n" +
                "        <extensions>\n" +
                "          <gpxtpx:TrackPointExtension>\n" +
                "            <gpxtpx:hr>142</gpxtpx:hr>\n" +
                "            <gpxtpx:cad>88</gpxtpx:cad>\n" +
                "          </gpxtpx:TrackPointExtension>\n" +
                "        </extensions>\n" +
                "      </trkpt>\n" +
                "      <trkpt lat=\"40.4178\" lon=\"-3.7048\">\n" +
                "        <ele>652.0</ele>\n" +
                "        <time>2026-09-17T10:01:00Z</time>\n" +
                "      </trkpt>\n" +
                "    </trkseg>\n" +
                "  </trk>\n" +
                "</gpx>";

        r gpxReader = new r(context);
        boolean success = gpxReader.a(new ByteArrayInputStream(gpx.getBytes(StandardCharsets.UTF_8)), context);

        Assert.assertTrue("Should parse GPX with extensions", success);
        Assert.assertEquals(2, gpxReader.f3132c.size());
    }

    @Test
    public void testGpxWithIso8601TimezoneOffsets() {
        // Wikiloc/Strava commonly output time like: 2026-09-17T10:00:00+02:00
        String gpx = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<gpx version=\"1.1\" creator=\"Strava\" xmlns=\"http://www.topografix.com/GPX/1/1\">\n" +
                "  <trk>\n" +
                "    <name>Strava Ride</name>\n" +
                "    <trkseg>\n" +
                "      <trkpt lat=\"40.4168\" lon=\"-3.7038\">\n" +
                "        <ele>650.0</ele>\n" +
                "        <time>2026-09-17T10:00:00+02:00</time>\n" +
                "      </trkpt>\n" +
                "      <trkpt lat=\"40.4178\" lon=\"-3.7048\">\n" +
                "        <ele>652.0</ele>\n" +
                "        <time>2026-09-17T10:01:00+02:00</time>\n" +
                "      </trkpt>\n" +
                "    </trkseg>\n" +
                "  </trk>\n" +
                "</gpx>";

        r gpxReader = new r(context);
        boolean success = gpxReader.a(new ByteArrayInputStream(gpx.getBytes(StandardCharsets.UTF_8)), context);

        Assert.assertTrue("Should parse GPX with timezone offset", success);
        Assert.assertEquals(2, gpxReader.f3132c.size());
    }

    @Test
    public void testGpxWithOnlyWaypoints() {
        // Some GPX files contain POIs/waypoints only
        String gpx = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<gpx version=\"1.1\" creator=\"Basecamp\" xmlns=\"http://www.topografix.com/GPX/1/1\">\n" +
                "  <wpt lat=\"40.4168\" lon=\"-3.7038\">\n" +
                "    <name>Madrid Center</name>\n" +
                "    <desc>Km 0</desc>\n" +
                "  </wpt>\n" +
                "  <wpt lat=\"40.4200\" lon=\"-3.7100\">\n" +
                "    <name>Point 2</name>\n" +
                "  </wpt>\n" +
                "</gpx>";

        r gpxReader = new r(context);
        boolean success = gpxReader.a(new ByteArrayInputStream(gpx.getBytes(StandardCharsets.UTF_8)), context);

        Assert.assertTrue("Should parse GPX with waypoints only", success);
        Assert.assertEquals(2, gpxReader.f3133d.size());
    }

    @Test
    public void testGpxRoute() {
        // Route instead of track: <rte> and <rtept>
        String gpx = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<gpx version=\"1.1\" creator=\"BRouter\" xmlns=\"http://www.topografix.com/GPX/1/1\">\n" +
                "  <rte>\n" +
                "    <name>BRouter Route</name>\n" +
                "    <rtept lat=\"40.4168\" lon=\"-3.7038\">\n" +
                "      <ele>650.0</ele>\n" +
                "    </rtept>\n" +
                "    <rtept lat=\"40.4178\" lon=\"-3.7048\">\n" +
                "      <ele>655.0</ele>\n" +
                "    </rtept>\n" +
                "  </rte>\n" +
                "</gpx>";

        r gpxReader = new r(context);
        boolean success = gpxReader.a(new ByteArrayInputStream(gpx.getBytes(StandardCharsets.UTF_8)), context);
        Assert.assertTrue("Should parse GPX route successfully", success);
        Assert.assertEquals(2, gpxReader.f3132c.size());
    }

    @Test
    public void testRoundTripExportAndImport() throws Exception {
        String gpx = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<gpx version=\"1.1\" creator=\"TestApp\" xmlns=\"http://www.topografix.com/GPX/1/1\">\n" +
                "  <trk>\n" +
                "    <name>Roundtrip Track</name>\n" +
                "    <trkseg>\n" +
                "      <trkpt lat=\"40.4168\" lon=\"-3.7038\"><ele>650.0</ele></trkpt>\n" +
                "      <trkpt lat=\"40.4178\" lon=\"-3.7048\"><ele>655.0</ele></trkpt>\n" +
                "    </trkseg>\n" +
                "  </trk>\n" +
                "</gpx>";

        r reader = new r(context);
        boolean parsed = reader.a(new ByteArrayInputStream(gpx.getBytes(StandardCharsets.UTF_8)), context);
        Assert.assertTrue(parsed);

        x3 track = new x3(2);
        boolean loaded = track.a("Roundtrip Track", reader);
        Assert.assertTrue(loaded);

        java.io.File tempFile = java.io.File.createTempFile("roundtrip", ".gpx");
        tempFile.deleteOnExit();

        reader.g(tempFile, track, "TestCreator", "v1", "TestTrack", "TestDesc");
        Assert.assertTrue("Exported file should exist and have size", tempFile.exists() && tempFile.length() > 0);

        r reReader = new r(context);
        boolean reParsed = reReader.a(new java.io.FileInputStream(tempFile), context);
        Assert.assertTrue("Should re-parse exported GPX", reParsed);
        Assert.assertEquals(2, reReader.f3132c.size());
    }
}
