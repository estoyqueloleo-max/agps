package com.giobat.AgpsTrackerPP.tron;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.giobat.AgpsTrackerPP.MainActivity;
import com.giobat.AgpsTrackerPP.R;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.view.MapView;

/**
 * Dedicated fullscreen Tron Lightcycle Arena activity.
 * Visualizes the neon playing grid, detects collisions in real-time,
 * and provides acoustic and haptic feedback.
 */
public class TronArenaActivity extends AppCompatActivity implements LocationListener {

    private static final String PLAYER_ID = "player1";
    private static final double DEFAULT_RADIUS_METERS = 250.0;
    private static final double MIN_RADIUS_METERS = 30.0;
    private static final double SHRINK_RATE_METERS_PER_SEC = 1.0;

    private MapView mapView;
    private TextView tvTimer;
    private TextView tvSpeed;
    private TextView tvRadius;
    private TextView tvStatus;
    private Button btnStart;
    private Button btnShrinkToggle;
    private Button btnReset;
    private Button btnClose;

    private TronCollisionEngine collisionEngine;
    private TronOverlayManager overlayManager;
    private TronFeedbackManager feedbackManager;
    private LocationManager locationManager;

    private boolean isMatchActive = false;
    private boolean isShrinkEnabled = false;
    private boolean isDerezzed = false;
    private long matchStartTimeMillis = 0L;
    private double arenaCenterLat = 40.4153; // Default fallback: Madrid
    private double arenaCenterLon = -3.6845;

    private final Handler timerHandler = new Handler(Looper.getMainLooper());
    private final Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (isMatchActive && !isDerezzed) {
                long elapsedSeconds = (System.currentTimeMillis() - matchStartTimeMillis) / 1000L;
                long minutes = elapsedSeconds / 60L;
                long seconds = elapsedSeconds % 60L;
                tvTimer.setText(String.format(java.util.Locale.US, "%02d:%02d", minutes, seconds));

                // Update shrinking arena boundary if active
                if (isShrinkEnabled && collisionEngine != null) {
                    ArenaGeofence arena = collisionEngine.getArena();
                    float currentRadius = (float) arena.getCurrentRadiusMeters(System.currentTimeMillis());
                    tvRadius.setText(String.format(java.util.Locale.US, "%.0f m", currentRadius));
                    overlayManager.updateArena(arena.getCenterLatitude(), arena.getCenterLongitude(), currentRadius);
                }

                timerHandler.postDelayed(this, 1000L);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tron_arena);

        initViews();
        initMap();
        initEngineAndFeedback();
        initControls();
        subscribeLocationUpdates();
    }

    private void initViews() {
        mapView = findViewById(R.id.tron_map_view);
        tvTimer = findViewById(R.id.tron_tv_timer);
        tvSpeed = findViewById(R.id.tron_tv_speed);
        tvRadius = findViewById(R.id.tron_tv_radius);
        tvStatus = findViewById(R.id.tron_tv_status);
        btnStart = findViewById(R.id.btn_tron_start);
        btnShrinkToggle = findViewById(R.id.btn_tron_shrink_toggle);
        btnReset = findViewById(R.id.btn_tron_reset);
        btnClose = findViewById(R.id.btn_tron_close);
    }

    private void initMap() {
        if (mapView != null) {
            mapView.setClickable(true);
            mapView.getMapScaleBar().setVisible(false);
            mapView.setBuiltInZoomControls(true);
            mapView.getMapZoomControls().setAutoHide(true);

            // Center on last known location from MainActivity if available
            if (MainActivity.f3613a1 != null && MainActivity.f3613a1.getLatitude() != 0.0) {
                arenaCenterLat = MainActivity.f3613a1.getLatitude();
                arenaCenterLon = MainActivity.f3613a1.getLongitude();
                mapView.getModel().mapViewPosition.setCenter(new LatLong(arenaCenterLat, arenaCenterLon));
                mapView.getModel().mapViewPosition.setZoomLevel((byte) 17);
            }
        }
    }

    private void initEngineAndFeedback() {
        ArenaGeofence initialArena = new ArenaGeofence(arenaCenterLat, arenaCenterLon, DEFAULT_RADIUS_METERS);
        collisionEngine = new TronCollisionEngine(initialArena, 8.0, 1.2);

        overlayManager = new TronOverlayManager();
        overlayManager.attach(mapView);
        overlayManager.updateArena(arenaCenterLat, arenaCenterLon, (float) DEFAULT_RADIUS_METERS);

        feedbackManager = new TronFeedbackManager(this);
    }

    private void initControls() {
        btnStart.setOnClickListener(v -> toggleMatch());
        btnShrinkToggle.setOnClickListener(v -> toggleShrink());
        btnReset.setOnClickListener(v -> resetMatch());
        btnClose.setOnClickListener(v -> finish());
    }

    private void toggleMatch() {
        if (!isMatchActive) {
            startMatch();
        } else {
            pauseMatch();
        }
    }

    private void startMatch() {
        if (isDerezzed) {
            resetMatch();
        }

        // Center arena on current location if available
        if (MainActivity.f3613a1 != null && MainActivity.f3613a1.getLatitude() != 0.0) {
            arenaCenterLat = MainActivity.f3613a1.getLatitude();
            arenaCenterLon = MainActivity.f3613a1.getLongitude();
        }

        matchStartTimeMillis = System.currentTimeMillis();
        ArenaGeofence arena = isShrinkEnabled
                ? new ArenaGeofence(arenaCenterLat, arenaCenterLon, DEFAULT_RADIUS_METERS, MIN_RADIUS_METERS, SHRINK_RATE_METERS_PER_SEC, matchStartTimeMillis)
                : new ArenaGeofence(arenaCenterLat, arenaCenterLon, DEFAULT_RADIUS_METERS);

        collisionEngine.setArena(arena);
        overlayManager.updateArena(arenaCenterLat, arenaCenterLon, (float) DEFAULT_RADIUS_METERS);

        if (mapView != null) {
            mapView.getModel().mapViewPosition.setCenter(new LatLong(arenaCenterLat, arenaCenterLon));
            mapView.getModel().mapViewPosition.setZoomLevel((byte) 17);
        }

        isMatchActive = true;
        isDerezzed = false;
        btnStart.setText("PAUSE");
        btnStart.setBackgroundColor(Color.parseColor("#FFAA00"));
        tvStatus.setText("ACTIVE");
        tvStatus.setTextColor(Color.parseColor("#00F0FF"));

        feedbackManager.playMatchStart();
        timerHandler.post(timerRunnable);
    }

    private void pauseMatch() {
        isMatchActive = false;
        btnStart.setText("RESUME");
        btnStart.setBackgroundColor(Color.parseColor("#00F0FF"));
        tvStatus.setText("PAUSED");
        tvStatus.setTextColor(Color.parseColor("#FFAA00"));
        timerHandler.removeCallbacks(timerRunnable);
    }

    private void toggleShrink() {
        isShrinkEnabled = !isShrinkEnabled;
        btnShrinkToggle.setText(isShrinkEnabled ? "RING: ON" : "RING: OFF");
        btnShrinkToggle.setBackgroundColor(isShrinkEnabled ? Color.parseColor("#FF0055") : Color.parseColor("#202A3C"));
        Toast.makeText(this, isShrinkEnabled ? "Shrinking Arena: 1 m/s" : "Fixed Arena: 250 m", Toast.LENGTH_SHORT).show();
    }

    private void resetMatch() {
        isMatchActive = false;
        isDerezzed = false;
        timerHandler.removeCallbacks(timerRunnable);

        collisionEngine.reset();
        overlayManager.clear();
        overlayManager.updateArena(arenaCenterLat, arenaCenterLon, (float) DEFAULT_RADIUS_METERS);

        tvTimer.setText("00:00");
        tvSpeed.setText("0.0 km/h");
        tvRadius.setText(String.format(java.util.Locale.US, "%.0f m", DEFAULT_RADIUS_METERS));
        tvStatus.setText("READY");
        tvStatus.setTextColor(Color.parseColor("#00FF66"));

        btnStart.setText("START");
        btnStart.setBackgroundColor(Color.parseColor("#00F0FF"));
    }

    @SuppressLint("MissingPermission")
    private void subscribeLocationUpdates() {
        try {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1.0f, this);
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (!isMatchActive || isDerezzed) return;

        double speedKmh = location.hasSpeed() ? location.getSpeed() * 3.6 : 0.0;
        tvSpeed.setText(String.format(java.util.Locale.US, "%.1f km/h", speedKmh));

        long now = System.currentTimeMillis();
        CollisionResult result = collisionEngine.processNewLocation(
                PLAYER_ID, location.getLatitude(), location.getLongitude(), now);

        if (result.hasCollided()) {
            handleCollision(result);
        } else {
            overlayManager.addPlayerPoint(PLAYER_ID, location.getLatitude(), location.getLongitude(), "#00F0FF");
        }
    }

    private void handleCollision(CollisionResult result) {
        isDerezzed = true;
        isMatchActive = false;
        timerHandler.removeCallbacks(timerRunnable);

        tvStatus.setText("DEREZZED!");
        tvStatus.setTextColor(Color.parseColor("#FF0055"));
        btnStart.setText("START");
        btnStart.setBackgroundColor(Color.parseColor("#00F0FF"));

        feedbackManager.playCollisionAlert();
        Toast.makeText(this, "💥 DEREZZED! " + result.getDetails(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {}

    @Override
    public void onProviderDisabled(@NonNull String provider) {}

    @Override
    @Deprecated
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerHandler.removeCallbacks(timerRunnable);
        if (locationManager != null) {
            try {
                locationManager.removeUpdates(this);
            } catch (Exception ignored) {
            }
        }
        if (overlayManager != null) {
            overlayManager.detach();
        }
        if (feedbackManager != null) {
            feedbackManager.release();
        }
        if (mapView != null) {
            mapView.destroy();
        }
    }
}

