package com.giobat.AgpsTrackerPP.tron;

/**
 * Defines the play area boundary for a Tron match with optional dynamic shrinking.
 */
public class ArenaGeofence {
    private final double centerLatitude;
    private final double centerLongitude;
    private final double initialRadiusMeters;
    private final double minimumRadiusMeters;
    private final double shrinkRateMetersPerSecond;
    private final long startTimeMillis;

    public ArenaGeofence(double centerLatitude, double centerLongitude, double initialRadiusMeters) {
        this(centerLatitude, centerLongitude, initialRadiusMeters, initialRadiusMeters, 0.0, 0L);
    }

    public ArenaGeofence(double centerLatitude, double centerLongitude,
                         double initialRadiusMeters, double minimumRadiusMeters,
                         double shrinkRateMetersPerSecond, long startTimeMillis) {
        this.centerLatitude = centerLatitude;
        this.centerLongitude = centerLongitude;
        this.initialRadiusMeters = initialRadiusMeters;
        this.minimumRadiusMeters = minimumRadiusMeters;
        this.shrinkRateMetersPerSecond = shrinkRateMetersPerSecond;
        this.startTimeMillis = startTimeMillis;
    }

    public double getCenterLatitude() {
        return centerLatitude;
    }

    public double getCenterLongitude() {
        return centerLongitude;
    }

    public double getInitialRadiusMeters() {
        return initialRadiusMeters;
    }

    public double getMinimumRadiusMeters() {
        return minimumRadiusMeters;
    }

    public double getCurrentRadiusMeters(long currentTimeMillis) {
        if (shrinkRateMetersPerSecond <= 0.0 || startTimeMillis <= 0L || currentTimeMillis <= startTimeMillis) {
            return initialRadiusMeters;
        }
        double elapsedSeconds = (currentTimeMillis - startTimeMillis) / 1000.0;
        double shrunkRadius = initialRadiusMeters - (elapsedSeconds * shrinkRateMetersPerSecond);
        return Math.max(minimumRadiusMeters, shrunkRadius);
    }

    public boolean isInside(TronPoint point, long currentTimeMillis) {
        double currentRadius = getCurrentRadiusMeters(currentTimeMillis);
        double distanceFromCenter = Math.sqrt(point.getXMeters() * point.getXMeters() +
                                              point.getYMeters() * point.getYMeters());
        return distanceFromCenter <= currentRadius;
    }
}

