package com.giobat.AgpsTrackerPP.plugin;

/**
 * Provides access to the active GameExtension instance for the current build flavor.
 */
public final class GameExtensionProvider {
    private static final GameExtension INSTANCE = new GameExtensionImpl();

    public static GameExtension get() {
        return INSTANCE;
    }

    private GameExtensionProvider() {}
}

