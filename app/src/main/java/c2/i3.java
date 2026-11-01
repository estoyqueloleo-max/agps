package c2;

import android.location.Location;

public class i3 {

    /** Maximum distance threshold in meters to consider location on-route. */
    public double maxDistanceMeters;

    /** Segment limit or index filter. */
    public int segmentLimit;

    /** Point limit or index filter. */
    public int pointLimit;

    public i3(double maxDistanceMeters) {
        this.segmentLimit = 10000000;
        this.pointLimit = 100000000;
        this.maxDistanceMeters = maxDistanceMeters;
    }

    public i3(double maxDistanceMeters, int segmentLimit, int pointLimit) {
        v2.e("GPS-M", "PointNearTrack:  segm=" + segmentLimit + " pntIdx=" + pointLimit);
        this.maxDistanceMeters = maxDistanceMeters;
        this.segmentLimit = segmentLimit;
        this.pointLimit = pointLimit;
    }

    /**
     * Checks if the given location is within maxDistanceMeters of any point or segment
     * in the track.
     */
    public boolean a(x3 track, Location location) {
        if (track == null || track.f3236a == null || location == null) {
            return false;
        }

        int segmentIndex = 0;
        for (q3 segment : track.f3236a) {
            segmentIndex++;
            if (segmentIndex > this.segmentLimit) {
                break;
            }
            if (segment == null || segment.f3123b == null) {
                continue;
            }

            // 1. Direct waypoint distance check
            int pointCount = 0;
            for (Location waypoint : segment.f3123b) {
                pointCount++;
                if (pointCount >= this.pointLimit) {
                    break;
                }
                if (waypoint != null && waypoint.distanceTo(location) < this.maxDistanceMeters) {
                    return true;
                }
            }

            // 2. Segment line-projection distance check
            int totalPoints = segment.f3123b.size();
            for (int i = 0; i < totalPoints - 1 && i <= this.pointLimit; i++) {
                Location startPoint = segment.f3123b.get(i);
                Location endPoint = segment.f3123b.get(i + 1);
                if (startPoint == null || endPoint == null) {
                    continue;
                }

                double segStartLon = startPoint.getLongitude();
                double segStartLat = startPoint.getLatitude();
                double segEndLon = endPoint.getLongitude();
                double segEndLat = endPoint.getLatitude();
                double locLon = location.getLongitude();
                double locLat = location.getLatitude();

                double projLon;
                double projLat;

                if (segStartLon == segEndLon) {
                    projLon = segStartLon;
                    projLat = locLat;
                } else if (segStartLat == segEndLat) {
                    projLon = locLon;
                    projLat = segStartLat;
                } else {
                    double slope = (segStartLat - segEndLat) / (segStartLon - segEndLon);
                    double intercept = segStartLat - (slope * segStartLon);
                    projLon = (((locLon * slope) + locLat) - intercept) / (2.0d * slope);
                    projLat = (slope * projLon) + intercept;
                }

                double minLon = Math.min(segStartLon, segEndLon);
                double maxLon = Math.max(segStartLon, segEndLon);
                double minLat = Math.min(segStartLat, segEndLat);
                double maxLat = Math.max(segStartLat, segEndLat);

                // Check if projection falls within segment bounding box
                if (projLon >= minLon && projLon <= maxLon && projLat >= minLat && projLat <= maxLat) {
                    Location projLoc = new Location("M");
                    projLoc.setAltitude(location.getAltitude());
                    projLoc.setLatitude(projLat);
                    projLoc.setLongitude(projLon);

                    float dist = location.distanceTo(projLoc);
                    if (dist >= 0 && dist < this.maxDistanceMeters) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

