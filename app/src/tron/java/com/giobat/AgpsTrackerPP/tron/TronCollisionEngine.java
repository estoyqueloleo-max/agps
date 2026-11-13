package com.giobat.AgpsTrackerPP.tron;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Geometric collision and rule engine for the Outdoor Tron Lightcycle challenge.
 * Operates purely in memory with high-speed local 2D planar math, completely decoupled
 * from Android UI and hardware sensors for deterministic unit testing.
 */
public class TronCollisionEngine {
    public static final double EARTH_RADIUS_METERS = 6371000.0;
    public static final double DEFAULT_IMMUNITY_BUFFER_METERS = 8.0;
    public static final double DEFAULT_WALL_PROXIMITY_METERS = 1.0;

    private ArenaGeofence arena;
    private double immunityBufferMeters;
    private double wallProximityThresholdMeters;
    private final Map<String, LightTrail> playerTrails = new ConcurrentHashMap<>();

    public TronCollisionEngine(ArenaGeofence arena) {
        this(arena, DEFAULT_IMMUNITY_BUFFER_METERS, DEFAULT_WALL_PROXIMITY_METERS);
    }

    public TronCollisionEngine(ArenaGeofence arena, double immunityBufferMeters, double wallProximityThresholdMeters) {
        this.arena = arena;
        this.immunityBufferMeters = immunityBufferMeters;
        this.wallProximityThresholdMeters = wallProximityThresholdMeters;
    }

    public void setArena(ArenaGeofence arena) {
        this.arena = arena;
    }

    public ArenaGeofence getArena() {
        return arena;
    }

    public void setImmunityBufferMeters(double immunityBufferMeters) {
        this.immunityBufferMeters = immunityBufferMeters;
    }

    public double getImmunityBufferMeters() {
        return immunityBufferMeters;
    }

    public void setWallProximityThresholdMeters(double wallProximityThresholdMeters) {
        this.wallProximityThresholdMeters = wallProximityThresholdMeters;
    }

    public double getWallProximityThresholdMeters() {
        return wallProximityThresholdMeters;
    }

    public LightTrail registerPlayer(String playerId, String colorHex) {
        LightTrail trail = new LightTrail(playerId, colorHex);
        playerTrails.put(playerId, trail);
        return trail;
    }

    public LightTrail getTrail(String playerId) {
        return playerTrails.get(playerId);
    }

    public Map<String, LightTrail> getAllTrails() {
        return Collections.unmodifiableMap(playerTrails);
    }

    public void reset() {
        playerTrails.clear();
    }

    /**
     * Converts WGS84 latitude/longitude to local planar coordinates (meters)
     * relative to the arena center using Equirectangular projection.
     */
    public TronPoint toLocalPoint(double latitude, double longitude, long timestamp) {
        double centerLat = arena.getCenterLatitude();
        double centerLon = arena.getCenterLongitude();

        double latRadians = Math.toRadians(latitude);
        double lonRadians = Math.toRadians(longitude);
        double centerLatRadians = Math.toRadians(centerLat);
        double centerLonRadians = Math.toRadians(centerLon);

        double deltaLat = latRadians - centerLatRadians;
        double deltaLon = lonRadians - centerLonRadians;

        double xMeters = deltaLon * Math.cos(centerLatRadians) * EARTH_RADIUS_METERS;
        double yMeters = deltaLat * EARTH_RADIUS_METERS;

        return new TronPoint(latitude, longitude, xMeters, yMeters, timestamp);
    }

    /**
     * Processes a new GPS coordinate update for a player.
     * Evaluates arena boundary, opponent collisions, and self-trail collisions.
     *
     * @return CollisionResult indicating if a collision occurred. If no collision,
     * the point is accepted and appended to the player's trail.
     */
    public synchronized CollisionResult processNewLocation(String playerId, double latitude, double longitude, long timestamp) {
        TronPoint newPoint = toLocalPoint(latitude, longitude, timestamp);

        // 1. Check arena boundary
        if (!arena.isInside(newPoint, timestamp)) {
            return CollisionResult.arenaBoundary(newPoint, arena.getCurrentRadiusMeters(timestamp));
        }

        LightTrail playerTrail = playerTrails.get(playerId);
        if (playerTrail == null) {
            playerTrail = registerPlayer(playerId, "#00F0FF");
        }

        TronPoint lastPoint = playerTrail.getLastPoint();
        if (lastPoint == null) {
            playerTrail.addPoint(newPoint);
            return CollisionResult.none();
        }

        TronSegment tentativeSegment = new TronSegment(lastPoint, newPoint);

        // 2. Check collisions with opponent trails
        for (Map.Entry<String, LightTrail> entry : playerTrails.entrySet()) {
            String otherPlayerId = entry.getKey();
            if (otherPlayerId.equals(playerId)) {
                continue;
            }

            LightTrail opponentTrail = entry.getValue();
            for (TronSegment opponentSegment : opponentTrail.getSegments()) {
                if (tentativeSegment.intersects(opponentSegment)) {
                    return CollisionResult.opponentTrail(newPoint, otherPlayerId,
                            "Crossed opponent light trail of " + otherPlayerId);
                }
                if (opponentSegment.distanceTo(newPoint) <= wallProximityThresholdMeters) {
                    return CollisionResult.opponentTrail(newPoint, otherPlayerId,
                            "Collided with opponent light wall proximity of " + otherPlayerId);
                }
            }
        }

        // 3. Check self-collision with immunity buffer
        List<TronSegment> existingSegments = playerTrail.getSegments();
        double accumulatedBackwardDistance = tentativeSegment.getLengthMeters();

        for (int i = existingSegments.size() - 1; i >= 0; i--) {
            TronSegment historicalSegment = existingSegments.get(i);
            accumulatedBackwardDistance += historicalSegment.getLengthMeters();

            // Skip checking recent segments within the grace/immunity buffer
            if (accumulatedBackwardDistance <= immunityBufferMeters) {
                continue;
            }

            if (tentativeSegment.intersects(historicalSegment)) {
                return CollisionResult.selfTrail(newPoint, "Crossed own light trail");
            }

            if (historicalSegment.distanceTo(newPoint) <= wallProximityThresholdMeters) {
                return CollisionResult.selfTrail(newPoint, "Collided with own light wall proximity");
            }
        }

        // All checks passed: accept movement and update trail
        playerTrail.addPoint(newPoint);
        return CollisionResult.none();
    }
}

