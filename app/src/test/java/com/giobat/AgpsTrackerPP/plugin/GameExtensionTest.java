package com.giobat.AgpsTrackerPP.plugin;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class GameExtensionTest {

    @Test
    public void testGameExtensionProviderReturnsInstance() {
        GameExtension extension = GameExtensionProvider.get();
        assertNotNull("GameExtensionProvider must always return a non-null instance", extension);
    }
}

