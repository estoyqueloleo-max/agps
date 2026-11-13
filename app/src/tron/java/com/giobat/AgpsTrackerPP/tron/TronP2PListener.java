package com.giobat.AgpsTrackerPP.tron;

/**
 * Callback listener for peer-to-peer Tron game networking events received via Pingo.
 */
public interface TronP2PListener {
    /**
     * Called when a valid position update from an opponent in the active match is received.
     */
    void onOpponentPositionReceived(String peerId, double latitude, double longitude, float speedKmh, long timestamp);

    /**
     * Called when an opponent in the match is derezzed (crashed into trail or arena).
     */
    void onOpponentDerezzed(String peerId, String reason);

    /**
     * Called when the arena boundary geometry is synchronized by the match host.
     */
    void onArenaSyncReceived(double centerLatitude, double centerLongitude, double radiusMeters);

    /**
     * Called when Pingo availability or bridge connection status changes.
     */
    void onPingoStatusChanged(boolean isInstalled, boolean isConnected);
}
