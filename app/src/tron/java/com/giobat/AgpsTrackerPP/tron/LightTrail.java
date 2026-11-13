package com.giobat.AgpsTrackerPP.tron;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the persistent light trail left behind by a player.
 */
public class LightTrail {
    private final String playerId;
    private final String colorHex;
    private final List<TronPoint> points = new ArrayList<>();
    private final List<TronSegment> segments = new ArrayList<>();
    private double totalDistanceMeters = 0.0;

    public LightTrail(String playerId, String colorHex) {
        this.playerId = playerId;
        this.colorHex = colorHex;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getColorHex() {
        return colorHex;
    }

    public List<TronPoint> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public List<TronSegment> getSegments() {
        return Collections.unmodifiableList(segments);
    }

    public double getTotalDistanceMeters() {
        return totalDistanceMeters;
    }

    public TronPoint getLastPoint() {
        if (points.isEmpty()) {
            return null;
        }
        return points.get(points.size() - 1);
    }

    /**
     * Appends a new point to the light trail, creating a new segment if a previous point exists.
     * @return the new segment formed, or null if this is the first point.
     */
    public TronSegment addPoint(TronPoint point) {
        TronPoint previous = getLastPoint();
        points.add(point);
        if (previous != null) {
            TronSegment segment = new TronSegment(previous, point);
            segments.add(segment);
            totalDistanceMeters += segment.getLengthMeters();
            return segment;
        }
        return null;
    }

    public void clear() {
        points.clear();
        segments.clear();
        totalDistanceMeters = 0.0;
    }
}

