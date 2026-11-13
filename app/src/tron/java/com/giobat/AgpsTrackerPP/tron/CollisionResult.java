package com.giobat.AgpsTrackerPP.tron;

/**
 * Result of checking a new player movement point against Tron game rules.
 */
public class CollisionResult {
    public enum CollisionType {
        NONE,
        ARENA_BOUNDARY,
        SELF_TRAIL,
        OPPONENT_TRAIL
    }

    private final CollisionType type;
    private final String details;
    private final String collidedWithPlayerId;
    private final TronPoint collisionPoint;

    public CollisionResult(CollisionType type, String details, String collidedWithPlayerId, TronPoint collisionPoint) {
        this.type = type;
        this.details = details;
        this.collidedWithPlayerId = collidedWithPlayerId;
        this.collisionPoint = collisionPoint;
    }

    public static CollisionResult none() {
        return new CollisionResult(CollisionType.NONE, "No collision", null, null);
    }

    public static CollisionResult arenaBoundary(TronPoint point, double currentRadiusMeters) {
        return new CollisionResult(CollisionType.ARENA_BOUNDARY,
                "Exceeded arena boundary radius of " + String.format("%.1f", currentRadiusMeters) + "m",
                null, point);
    }

    public static CollisionResult selfTrail(TronPoint point, String details) {
        return new CollisionResult(CollisionType.SELF_TRAIL, details, null, point);
    }

    public static CollisionResult opponentTrail(TronPoint point, String opponentId, String details) {
        return new CollisionResult(CollisionType.OPPONENT_TRAIL, details, opponentId, point);
    }

    public boolean hasCollided() {
        return type != CollisionType.NONE;
    }

    public CollisionType getType() {
        return type;
    }

    public String getDetails() {
        return details;
    }

    public String getCollidedWithPlayerId() {
        return collidedWithPlayerId;
    }

    public TronPoint getCollisionPoint() {
        return collisionPoint;
    }

    @Override
    public String toString() {
        return "CollisionResult{" +
                "type=" + type +
                ", details='" + details + '\'' +
                ", opponent='" + collidedWithPlayerId + '\'' +
                '}';
    }
}

