package com.giobat.AgpsTrackerPP.tron;

/**
 * Represents a straight-line segment between two TronPoints, providing
 * 2D segment intersection tests and point-to-segment distance calculations.
 */
public class TronSegment {
    private static final double EPSILON = 1e-5;

    private final TronPoint start;
    private final TronPoint end;
    private final double lengthMeters;

    public TronSegment(TronPoint start, TronPoint end) {
        this.start = start;
        this.end = end;
        this.lengthMeters = start.distanceTo(end);
    }

    public TronPoint getStart() {
        return start;
    }

    public TronPoint getEnd() {
        return end;
    }

    public double getLengthMeters() {
        return lengthMeters;
    }

    /**
     * Checks if this segment intersects with another segment.
     * Connected segments sharing a joint/vertex do not intersect unless they retrace backwards.
     */
    public boolean intersects(TronSegment other) {
        TronPoint a = this.start;
        TronPoint b = this.end;
        TronPoint c = other.start;
        TronPoint d = other.end;

        // Check for shared endpoints (connected at a vertex)
        if (pointsEqual(a, c)) {
            return checkOverlapFromJoint(a, b, d);
        }
        if (pointsEqual(a, d)) {
            return checkOverlapFromJoint(a, b, c);
        }
        if (pointsEqual(b, c)) {
            return checkOverlapFromJoint(b, a, d);
        }
        if (pointsEqual(b, d)) {
            return checkOverlapFromJoint(b, a, c);
        }

        // Standard non-sharing intersection test
        double o1 = orientation(a, b, c);
        double o2 = orientation(a, b, d);
        double o3 = orientation(c, d, a);
        double o4 = orientation(c, d, b);

        // General crossing case
        if (((o1 > EPSILON && o2 < -EPSILON) || (o1 < -EPSILON && o2 > EPSILON)) &&
            ((o3 > EPSILON && o4 < -EPSILON) || (o3 < -EPSILON && o4 > EPSILON))) {
            return true;
        }

        // Check if an endpoint of one lies strictly within the interior of the other (T-junction)
        if (Math.abs(o1) <= EPSILON && isStrictlyBetween(a, b, c)) return true;
        if (Math.abs(o2) <= EPSILON && isStrictlyBetween(a, b, d)) return true;
        if (Math.abs(o3) <= EPSILON && isStrictlyBetween(c, d, a)) return true;
        if (Math.abs(o4) <= EPSILON && isStrictlyBetween(c, d, b)) return true;

        return false;
    }

    /**
     * Computes the shortest distance from a point to this segment in meters.
     */
    public double distanceTo(TronPoint point) {
        double px = point.getXMeters();
        double py = point.getYMeters();
        double ax = start.getXMeters();
        double ay = start.getYMeters();
        double bx = end.getXMeters();
        double by = end.getYMeters();

        double segmentLengthSquared = (bx - ax) * (bx - ax) + (by - ay) * (by - ay);
        if (segmentLengthSquared < 1e-9) {
            return Math.sqrt((px - ax) * (px - ax) + (py - ay) * (py - ay));
        }

        // Project point onto line segment, clamped to [0, 1]
        double projectionFactor = ((px - ax) * (bx - ax) + (py - ay) * (by - ay)) / segmentLengthSquared;
        double clampedT = Math.max(0.0, Math.min(1.0, projectionFactor));

        double projectedX = ax + clampedT * (bx - ax);
        double projectedY = ay + clampedT * (by - ay);

        double deltaX = px - projectedX;
        double deltaY = py - projectedY;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    private static boolean pointsEqual(TronPoint p1, TronPoint p2) {
        return Math.abs(p1.getXMeters() - p2.getXMeters()) < EPSILON &&
               Math.abs(p1.getYMeters() - p2.getYMeters()) < EPSILON;
    }

    /**
     * When two segments connect at joint J (J->P1 and J->P2), checks if they fold back on each other.
     * Overlap occurs if the vectors JP1 and JP2 point in nearly the same direction.
     */
    private static boolean checkOverlapFromJoint(TronPoint joint, TronPoint p1, TronPoint p2) {
        double v1x = p1.getXMeters() - joint.getXMeters();
        double v1y = p1.getYMeters() - joint.getYMeters();
        double v2x = p2.getXMeters() - joint.getXMeters();
        double v2y = p2.getYMeters() - joint.getYMeters();

        double len1 = Math.sqrt(v1x * v1x + v1y * v1y);
        double len2 = Math.sqrt(v2x * v2x + v2y * v2y);

        if (len1 < EPSILON || len2 < EPSILON) {
            return false;
        }

        double dotProduct = (v1x * v2x + v1y * v2y) / (len1 * len2);
        // dotProduct > 0.98 means angle < ~11 degrees: folding back onto itself
        return dotProduct > 0.98;
    }

    private static double orientation(TronPoint p, TronPoint q, TronPoint r) {
        return (q.getYMeters() - p.getYMeters()) * (r.getXMeters() - q.getXMeters()) -
               (q.getXMeters() - p.getXMeters()) * (r.getYMeters() - q.getYMeters());
    }

    private static boolean isStrictlyBetween(TronPoint a, TronPoint b, TronPoint p) {
        double minX = Math.min(a.getXMeters(), b.getXMeters()) + EPSILON;
        double maxX = Math.max(a.getXMeters(), b.getXMeters()) - EPSILON;
        double minY = Math.min(a.getYMeters(), b.getYMeters()) + EPSILON;
        double maxY = Math.max(a.getYMeters(), b.getYMeters()) - EPSILON;

        boolean inX = (minX <= p.getXMeters() && p.getXMeters() <= maxX) || Math.abs(a.getXMeters() - b.getXMeters()) < EPSILON;
        boolean inY = (minY <= p.getYMeters() && p.getYMeters() <= maxY) || Math.abs(a.getYMeters() - b.getYMeters()) < EPSILON;

        return inX && inY;
    }
}
