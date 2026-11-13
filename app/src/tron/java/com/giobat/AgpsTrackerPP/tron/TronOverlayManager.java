package com.giobat.AgpsTrackerPP.tron;

import android.graphics.Color;

import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.graphics.Style;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.Layers;
import org.mapsforge.map.layer.overlay.Circle;
import org.mapsforge.map.layer.overlay.Polyline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Visual renderer for Tron light walls and arena boundary on top of Mapsforge.
 * Creates glowing neon polylines for trails and dynamic boundary rings.
 */
public class TronOverlayManager {
    private MapView mapView;
    private Circle arenaCircle;

    // Each player has a pair of polylines: [0] = outer glow, [1] = inner core
    private final Map<String, List<Polyline>> playerTrails = new ConcurrentHashMap<>();

    public void attach(MapView mapView) {
        this.mapView = mapView;
    }

    public void detach() {
        if (mapView != null) {
            clear();
            this.mapView = null;
        }
    }

    /**
     * Updates or creates the circular boundary of the arena.
     */
    public synchronized void updateArena(double centerLat, double centerLon, float radiusMeters) {
        if (mapView == null) return;

        LatLong center = new LatLong(centerLat, centerLon);
        Layers layers = mapView.getLayerManager().getLayers();

        if (arenaCircle == null) {
            Paint strokePaint = AndroidGraphicFactory.INSTANCE.createPaint();
            strokePaint.setColor(Color.argb(220, 255, 0, 85)); // Neon Pink/Magenta
            strokePaint.setStrokeWidth(6.0f);
            strokePaint.setStyle(Style.STROKE);

            Paint fillPaint = AndroidGraphicFactory.INSTANCE.createPaint();
            fillPaint.setColor(Color.argb(18, 255, 0, 85)); // Faint glow fill
            fillPaint.setStyle(Style.FILL);

            arenaCircle = new Circle(center, radiusMeters, fillPaint, strokePaint);
            layers.add(arenaCircle);
        } else {
            arenaCircle.setLatLong(center);
            arenaCircle.setRadius(radiusMeters);
            arenaCircle.requestRedraw();
        }
    }

    /**
     * Appends a coordinate to a player's glowing light trail on the map.
     */
    public synchronized void addPlayerPoint(String playerId, double latitude, double longitude, String colorHex) {
        if (mapView == null) return;

        LatLong newCoord = new LatLong(latitude, longitude);
        List<Polyline> lines = playerTrails.get(playerId);

        if (lines == null) {
            lines = createPlayerPolylines(colorHex);
            playerTrails.put(playerId, lines);

            Layers layers = mapView.getLayerManager().getLayers();
            for (Polyline polyline : lines) {
                layers.add(polyline);
            }
        }

        for (Polyline polyline : lines) {
            polyline.addPoint(newCoord);
            polyline.requestRedraw();
        }
    }

    private List<Polyline> createPlayerPolylines(String colorHex) {
        int baseColor = Color.parseColor(colorHex != null ? colorHex : "#00F0FF");
        int glowColor = Color.argb(100, Color.red(baseColor), Color.green(baseColor), Color.blue(baseColor));

        // 1. Outer glow stroke (wide, translucent)
        Paint glowPaint = AndroidGraphicFactory.INSTANCE.createPaint();
        glowPaint.setColor(glowColor);
        glowPaint.setStrokeWidth(12.0f);
        glowPaint.setStyle(Style.STROKE);
        Polyline glowLine = new Polyline(glowPaint, AndroidGraphicFactory.INSTANCE);

        // 2. Inner core stroke (bright neon, sharp)
        Paint corePaint = AndroidGraphicFactory.INSTANCE.createPaint();
        corePaint.setColor(baseColor);
        corePaint.setStrokeWidth(4.5f);
        corePaint.setStyle(Style.STROKE);
        Polyline coreLine = new Polyline(corePaint, AndroidGraphicFactory.INSTANCE);

        List<Polyline> pair = new ArrayList<>(2);
        pair.add(glowLine);
        pair.add(coreLine);
        return pair;
    }

    public synchronized void clear() {
        if (mapView == null) return;

        Layers layers = mapView.getLayerManager().getLayers();
        if (arenaCircle != null) {
            layers.remove(arenaCircle);
            arenaCircle = null;
        }

        for (List<Polyline> lines : playerTrails.values()) {
            for (Polyline line : lines) {
                layers.remove(line);
            }
        }
        playerTrails.clear();
        mapView.repaint();
    }
}

