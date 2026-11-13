package com.giobat.AgpsTrackerPP.tron;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Manages local Inter-Process Communication (IPC) between A-GPS Tracker++ (Tron edition)
 * and the Pingo P2P application (com.pingo.app).
 *
 * Implements strict scoping and target isolation ("A quién retransmitimos") to ensure
 * high-frequency game telemetry is only exchanged with confirmed match participants and never
 * spammed to personal contacts.
 */
public class PingoBridgeManager {
    private static final String TAG = "PingoBridgeManager";

    public static final String PINGO_PACKAGE_NAME = "com.pingo.app";

    // Intent Actions exchanged with Pingo
    public static final String ACTION_TRON_SEND_PACKET = "com.pingo.app.ACTION_TRON_SEND_PACKET";
    public static final String ACTION_TRON_RECEIVE_PACKET = "com.giobat.agps.ACTION_TRON_RECEIVE_PACKET";
    public static final String ACTION_TRON_SET_MATCH = "com.pingo.app.ACTION_TRON_SET_MATCH";
    public static final String ACTION_TRON_CLEAR_MATCH = "com.pingo.app.ACTION_TRON_CLEAR_MATCH";

    // Intent Extras
    public static final String EXTRA_MATCH_ID = "EXTRA_MATCH_ID";
    public static final String EXTRA_TARGET_PEER_ID = "EXTRA_TARGET_PEER_ID";
    public static final String EXTRA_SENDER_PEER_ID = "EXTRA_SENDER_PEER_ID";
    public static final String EXTRA_PAYLOAD = "EXTRA_PAYLOAD";

    // Payload Types
    public static final String TYPE_POSITION = "tron_pos";
    public static final String TYPE_DEREZZED = "tron_derezzed";
    public static final String TYPE_ARENA_SYNC = "tron_arena_sync";

    private final Context context;
    private String activeMatchId = "TRON-1";
    private String targetOpponentPeerId = null;
    private TronP2PListener p2pListener;
    private BroadcastReceiver packetReceiver;
    private boolean isRegistered = false;

    public PingoBridgeManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public void setListener(TronP2PListener listener) {
        this.p2pListener = listener;
        if (p2pListener != null) {
            p2pListener.onPingoStatusChanged(isPingoInstalled(), isRegistered);
        }
    }

    public String getActiveMatchId() {
        return activeMatchId;
    }

    public void setActiveMatchId(String activeMatchId) {
        this.activeMatchId = activeMatchId;
    }

    public String getTargetOpponentPeerId() {
        return targetOpponentPeerId;
    }

    public void setTargetOpponentPeerId(String targetOpponentPeerId) {
        this.targetOpponentPeerId = targetOpponentPeerId;
    }

    public boolean isPingoInstalled() {
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.getPackageInfo(PINGO_PACKAGE_NAME, 0);
            return true;
        } catch (PackageManager.NameNotFoundException ignored) {
            return false;
        }
    }

    /**
     * Registers the broadcast receiver to listen for opponent packets from Pingo.
     */
    public synchronized void start() {
        if (isRegistered) return;

        IntentFilter filter = new IntentFilter(ACTION_TRON_RECEIVE_PACKET);
        packetReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context receiverContext, Intent receivedIntent) {
                handleIncomingIntent(receivedIntent);
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(packetReceiver, filter, Context.RECEIVER_EXPORTED);
        } else {
            context.registerReceiver(packetReceiver, filter);
        }

        isRegistered = true;
        notifyMatchConfigurationToPingo();

        if (p2pListener != null) {
            p2pListener.onPingoStatusChanged(isPingoInstalled(), true);
        }
    }

    /**
     * Unregisters receiver and clears match scoping in Pingo.
     */
    public synchronized void stop() {
        if (!isRegistered) return;

        if (packetReceiver != null) {
            try {
                context.unregisterReceiver(packetReceiver);
            } catch (Exception exception) {
                Log.w(TAG, "Error unregistering Pingo receiver: " + exception.getMessage());
            }
            packetReceiver = null;
        }

        isRegistered = false;
        clearMatchInPingo();

        if (p2pListener != null) {
            p2pListener.onPingoStatusChanged(isPingoInstalled(), false);
        }
    }

    public void notifyMatchConfigurationToPingo() {
        if (!isPingoInstalled() || activeMatchId == null) return;

        Intent matchIntent = new Intent(ACTION_TRON_SET_MATCH);
        matchIntent.setPackage(PINGO_PACKAGE_NAME);
        matchIntent.putExtra(EXTRA_MATCH_ID, activeMatchId);
        if (targetOpponentPeerId != null && !targetOpponentPeerId.trim().isEmpty()) {
            matchIntent.putExtra(EXTRA_TARGET_PEER_ID, targetOpponentPeerId.trim());
        }
        context.sendBroadcast(matchIntent);
    }

    public void clearMatchInPingo() {
        if (!isPingoInstalled()) return;

        Intent clearIntent = new Intent(ACTION_TRON_CLEAR_MATCH);
        clearIntent.setPackage(PINGO_PACKAGE_NAME);
        context.sendBroadcast(clearIntent);
    }

    /**
     * Broadcasts local position telemetry to the opponent via Pingo.
     */
    public void sendPosition(double latitude, double longitude, float speedKmh, long timestamp) {
        try {
            JSONObject payload = new JSONObject();
            payload.put("type", TYPE_POSITION);
            payload.put("lat", latitude);
            payload.put("lng", longitude);
            payload.put("speed", speedKmh);
            payload.put("timestamp", timestamp);

            sendPacketToPingo(payload.toString());
        } catch (JSONException exception) {
            Log.e(TAG, "Error creating position payload: " + exception.getMessage());
        }
    }

    /**
     * Broadcasts local collision / derezzed event to the opponent.
     */
    public void sendDerezzed(String collisionReason) {
        try {
            JSONObject payload = new JSONObject();
            payload.put("type", TYPE_DEREZZED);
            payload.put("reason", collisionReason);

            sendPacketToPingo(payload.toString());
        } catch (JSONException exception) {
            Log.e(TAG, "Error creating derezzed payload: " + exception.getMessage());
        }
    }

    /**
     * Broadcasts arena boundary geometry to synchronize both players.
     */
    public void sendArenaSync(double centerLatitude, double centerLongitude, double radiusMeters) {
        try {
            JSONObject payload = new JSONObject();
            payload.put("type", TYPE_ARENA_SYNC);
            payload.put("centerLat", centerLatitude);
            payload.put("centerLng", centerLongitude);
            payload.put("radius", radiusMeters);

            sendPacketToPingo(payload.toString());
        } catch (JSONException exception) {
            Log.e(TAG, "Error creating arena sync payload: " + exception.getMessage());
        }
    }

    private void sendPacketToPingo(String payloadString) {
        if (!isPingoInstalled() || activeMatchId == null) return;

        Intent sendIntent = new Intent(ACTION_TRON_SEND_PACKET);
        sendIntent.setPackage(PINGO_PACKAGE_NAME);
        sendIntent.putExtra(EXTRA_MATCH_ID, activeMatchId);
        if (targetOpponentPeerId != null && !targetOpponentPeerId.trim().isEmpty()) {
            sendIntent.putExtra(EXTRA_TARGET_PEER_ID, targetOpponentPeerId.trim());
        }
        sendIntent.putExtra(EXTRA_PAYLOAD, payloadString);

        context.sendBroadcast(sendIntent);
    }

    /**
     * Handles incoming intent from Pingo, applying strict match and sender filtering.
     */
    public void handleIncomingIntent(Intent intent) {
        if (intent == null || !ACTION_TRON_RECEIVE_PACKET.equals(intent.getAction())) {
            return;
        }

        String matchId = intent.getStringExtra(EXTRA_MATCH_ID);
        String senderPeerId = intent.getStringExtra(EXTRA_SENDER_PEER_ID);
        String payloadString = intent.getStringExtra(EXTRA_PAYLOAD);

        if (matchId == null || payloadString == null) {
            return;
        }

        // 1. Strict Match Filter: Drop packets from any other match
        if (!matchId.equals(activeMatchId)) {
            Log.d(TAG, "Ignoring packet for match: " + matchId + " (active: " + activeMatchId + ")");
            return;
        }

        // 2. Strict Opponent Filter: If target opponent is configured, drop packets from other peers
        if (targetOpponentPeerId != null && !targetOpponentPeerId.trim().isEmpty()) {
            if (senderPeerId != null && !senderPeerId.trim().isEmpty() && !targetOpponentPeerId.equalsIgnoreCase(senderPeerId.trim())) {
                Log.d(TAG, "Ignoring packet from peer: " + senderPeerId + " (target: " + targetOpponentPeerId + ")");
                return;
            }
        }

        processPayload(senderPeerId != null ? senderPeerId : "opponent", payloadString);
    }

    /**
     * Parses the JSON payload and dispatches to the TronP2PListener.
     */
    public void processPayload(String senderPeerId, String payloadString) {
        if (p2pListener == null) return;

        try {
            JSONObject payload = new JSONObject(payloadString);
            String type = payload.optString("type");

            if (TYPE_POSITION.equals(type)) {
                double latitude = payload.getDouble("lat");
                double longitude = payload.getDouble("lng");
                float speedKmh = (float) payload.optDouble("speed", 0.0);
                long timestamp = payload.optLong("timestamp", System.currentTimeMillis());

                p2pListener.onOpponentPositionReceived(senderPeerId, latitude, longitude, speedKmh, timestamp);
            } else if (TYPE_DEREZZED.equals(type)) {
                String reason = payload.optString("reason", "COLLISION");
                p2pListener.onOpponentDerezzed(senderPeerId, reason);
            } else if (TYPE_ARENA_SYNC.equals(type)) {
                double centerLatitude = payload.getDouble("centerLat");
                double centerLongitude = payload.getDouble("centerLng");
                double radiusMeters = payload.getDouble("radius");

                p2pListener.onArenaSyncReceived(centerLatitude, centerLongitude, radiusMeters);
            }
        } catch (JSONException exception) {
            Log.e(TAG, "Error parsing incoming Tron payload: " + exception.getMessage());
        }
    }
}
