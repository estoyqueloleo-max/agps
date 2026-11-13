package com.giobat.AgpsTrackerPP.tron;

import java.util.Objects;

/**
 * Represents a point in the Tron game arena, containing both geographic (WGS84)
 * and local planar Cartesian coordinates (meters relative to the arena anchor).
 */
public class TronPoint {
    private final double latitude;
    private final double longitude;
    private final double xMeters;
    private final double yMeters;
    private final long timestamp;

    public TronPoint(double latitude, double longitude, double xMeters, double yMeters, long timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.xMeters = xMeters;
        this.yMeters = yMeters;
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getXMeters() {
        return xMeters;
    }

    public double getYMeters() {
        return yMeters;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double distanceTo(TronPoint other) {
        double deltaX = this.xMeters - other.xMeters;
        double deltaY = this.yMeters - other.yMeters;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TronPoint tronPoint = (TronPoint) o;
        return Double.compare(tronPoint.latitude, latitude) == 0 &&
                Double.compare(tronPoint.longitude, longitude) == 0 &&
                Double.compare(tronPoint.xMeters, xMeters) == 0 &&
                Double.compare(tronPoint.yMeters, yMeters) == 0 &&
                timestamp == tronPoint.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, xMeters, yMeters, timestamp);
    }

    @Override
    public String toString() {
        return "TronPoint{" +
                "lat=" + latitude +
                ", lon=" + longitude +
                ", x=" + xMeters +
                ", y=" + yMeters +
                ", t=" + timestamp +
                '}';
    }
}

