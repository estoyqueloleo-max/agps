package com.giobat.AgpsTrackerPP;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapsforge.core.graphics.GraphicFactory;
import org.mapsforge.core.graphics.TileBitmap;
import org.mapsforge.core.model.Tile;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.layer.cache.InMemoryTileCache;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.renderer.DatabaseRenderer;
import org.mapsforge.map.layer.renderer.RendererJob;
import org.mapsforge.map.model.DisplayModel;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.rendertheme.ExternalRenderTheme;
import org.mapsforge.map.rendertheme.XmlRenderTheme;
import org.mapsforge.map.rendertheme.rule.RenderThemeFuture;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.GraphicsMode;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Visual automated tests for validating Mapsforge vector rendering in Robolectric.
 * Uses Robolectric Native Graphics (RNG) to execute real vector canvas drawing
 * and produces visual PNG artifacts for inspection and pixel assertions.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
public class MapVisualRenderTest {

    private Context context;
    private File worldMapFile;
    private File themeFile;
    private GraphicFactory graphicFactory;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        AndroidGraphicFactory.createInstance(context);
        graphicFactory = AndroidGraphicFactory.INSTANCE;

        worldMapFile = new File("src/main/res/raw/world.map");
        if (!worldMapFile.exists()) {
            worldMapFile = new File("app/src/main/res/raw/world.map");
        }

        themeFile = new File("src/main/assets/mapsforge/osmarender.xml");
        if (!themeFile.exists()) {
            themeFile = new File("app/src/main/assets/mapsforge/osmarender.xml");
        }
    }

    @Test
    public void testVisualRenderingZoom0WholeWorld() throws Exception {
        renderAndValidateTile(0, 0, (byte) 0, "visual_rendered_world_z0.png");
    }

    @Test
    public void testVisualRenderingZoom1NorthAtlantic() throws Exception {
        renderAndValidateTile(0, 0, (byte) 1, "visual_rendered_world_z1_x0_y0.png");
    }

    private void renderAndValidateTile(int tileX, int tileY, byte zoomLevel, String outputFileName) throws Exception {
        Assert.assertTrue("world.map must exist", worldMapFile.exists());
        Assert.assertTrue("Theme file must exist", themeFile.exists());

        MapFile mapFile = new MapFile(worldMapFile);
        DisplayModel displayModel = new DisplayModel();
        XmlRenderTheme theme = new ExternalRenderTheme(themeFile);
        RenderThemeFuture future = new RenderThemeFuture(graphicFactory, theme, displayModel);
        future.run(); // Parse synchronously
        Assert.assertNotNull("Render theme parsed", future.get());

        TileCache tileCache = new InMemoryTileCache(100);
        DatabaseRenderer renderer = new DatabaseRenderer(mapFile, graphicFactory, tileCache, null, true, true, null);

        Tile tile = new Tile(tileX, tileY, zoomLevel, 256);
        RendererJob job = new RendererJob(tile, mapFile, future, displayModel, 1.0f, false, false);

        TileBitmap tileBitmap = renderer.executeJob(job);
        Assert.assertNotNull("Rendered tile must not be null", tileBitmap);

        Bitmap androidBitmap = AndroidGraphicFactory.getBitmap(tileBitmap);
        Assert.assertNotNull("Android Bitmap must not be null", androidBitmap);
        Assert.assertEquals(256, androidBitmap.getWidth());
        Assert.assertEquals(256, androidBitmap.getHeight());

        // Assert pixel variations (ensures not a blank single-color tile)
        Set<Integer> uniqueColors = new HashSet<>();
        for (int x = 0; x < 256; x += 16) {
            for (int y = 0; y < 256; y += 16) {
                uniqueColors.add(androidBitmap.getPixel(x, y));
            }
        }
        Assert.assertTrue("Tile should have distinct vector rendered colors (land, water), found: " + uniqueColors.size(),
                uniqueColors.size() >= 2);

        // Save into visual test report directory
        File outputDir = new File("build/reports/visual-tests");
        outputDir.mkdirs();
        File pngFile = new File(outputDir, outputFileName);
        try (FileOutputStream fos = new FileOutputStream(pngFile)) {
            tileBitmap.compress(fos);
        }

        Assert.assertTrue("Output file must exist", pngFile.exists());
        Assert.assertTrue("Output file must have substantial content (> 500 bytes)", pngFile.length() > 500);

        // Copy to artifact directory if available for IDE preview
        File artifactDir = new File("/home/jose/.gemini/antigravity-ide/brain/a9383928-66c6-4983-b0a1-cca92df3a4e0");
        if (artifactDir.exists()) {
            File artifactImg = new File(artifactDir, outputFileName);
            try (FileOutputStream fos = new FileOutputStream(artifactImg)) {
                tileBitmap.compress(fos);
            }
        }

        System.out.println("Visual test generated: " + pngFile.getAbsolutePath() + " (" + pngFile.length() + " bytes, " + uniqueColors.size() + " unique sample colors)");
    }
}
