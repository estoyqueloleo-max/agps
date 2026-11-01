package c2;

import android.location.Location;

import com.giobat.AgpsTrackerPP.MainActivity;

import org.mapsforge.core.graphics.Canvas;
import org.mapsforge.core.graphics.Color;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.graphics.Style;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.core.model.Rotation;
import org.mapsforge.core.util.MercatorProjection;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.layer.Layer;

import java.util.List;

/**
 * Mapsforge layer that renders interactive rounded vertices (points) over the loaded track
 * when in track editing mode. Allows visual identification, selection, and editing of individual
 * track points.
 */
public class TrackEditPointsLayer extends Layer {

    private final x3 track;
    private final Paint outerCirclePaint;
    private final Paint innerCirclePaint;
    private final Paint selectedOuterPaint;
    private final Paint selectedInnerPaint;
    private int selectedPointIndex = -1;

    public TrackEditPointsLayer(x3 track) {
        this.track = track;

        // Default points: amber/orange circle with white core
        this.outerCirclePaint = AndroidGraphicFactory.INSTANCE.createPaint();
        this.outerCirclePaint.setStyle(Style.FILL);
        this.outerCirclePaint.setColor(AndroidGraphicFactory.INSTANCE.createColor(220, 255, 112, 67)); // Deep Orange

        this.innerCirclePaint = AndroidGraphicFactory.INSTANCE.createPaint();
        this.innerCirclePaint.setStyle(Style.FILL);
        this.innerCirclePaint.setColor(AndroidGraphicFactory.INSTANCE.createColor(Color.WHITE));

        // Selected point: highlighted blue/yellow ring
        this.selectedOuterPaint = AndroidGraphicFactory.INSTANCE.createPaint();
        this.selectedOuterPaint.setStyle(Style.FILL);
        this.selectedOuterPaint.setColor(AndroidGraphicFactory.INSTANCE.createColor(255, 33, 150, 243)); // Material Blue

        this.selectedInnerPaint = AndroidGraphicFactory.INSTANCE.createPaint();
        this.selectedInnerPaint.setStyle(Style.FILL);
        this.selectedInnerPaint.setColor(AndroidGraphicFactory.INSTANCE.createColor(255, 255, 235, 59)); // Bright Yellow
    }

    public void setSelectedPointIndex(int index) {
        this.selectedPointIndex = index;
        requestRedraw();
    }

    public int getSelectedPointIndex() {
        return this.selectedPointIndex;
    }

    @Override
    public synchronized void draw(BoundingBox boundingBox, byte zoomLevel, Canvas canvas, Point topLeftPoint, Rotation rotation) {
        if (this.track == null || this.displayModel == null) {
            return;
        }

        // Only draw points if edit mode is active and at a zoom level where points are distinguishable
        if (MainActivity.trackEditMode == MainActivity.TRACK_EDIT_MODE_NONE && this.selectedPointIndex < 0) {
            return;
        }

        List<Location> points = this.track.f3238c;
        if (points == null || points.isEmpty()) {
            return;
        }

        long mapSize = MercatorProjection.getMapSize(zoomLevel, displayModel.getTileSize());
        int totalPoints = points.size();

        // Sample points at lower zooms to keep drawing fast and uncluttered,
        // but draw all points at close zoom levels (>= 16).
        int step = 1;
        if (zoomLevel < 14) {
            step = Math.max(1, totalPoints / 100);
        } else if (zoomLevel < 16) {
            step = Math.max(1, totalPoints / 300);
        }

        int baseRadius = 8;
        if (zoomLevel >= 17) {
            baseRadius = 11;
        } else if (zoomLevel <= 13) {
            baseRadius = 6;
        }

        for (int i = 0; i < totalPoints; i += step) {
            Location loc = points.get(i);
            if (loc == null) continue;

            double lat = loc.getLatitude();
            double lon = loc.getLongitude();

            // Quick viewport bounding box check
            if (lat < boundingBox.minLatitude || lat > boundingBox.maxLatitude ||
                lon < boundingBox.minLongitude || lon > boundingBox.maxLongitude) {
                // Always render the selected point even if sampled out
                if (i != this.selectedPointIndex) {
                    continue;
                }
            }

            int pixelX = (int) (MercatorProjection.longitudeToPixelX(lon, mapSize) - topLeftPoint.x);
            int pixelY = (int) (MercatorProjection.latitudeToPixelY(lat, mapSize) - topLeftPoint.y);

            boolean isSelected = (i == this.selectedPointIndex);
            int outerR = isSelected ? (baseRadius + 5) : baseRadius;
            int innerR = isSelected ? (baseRadius - 1) : Math.max(2, baseRadius - 3);

            Paint outer = isSelected ? selectedOuterPaint : outerCirclePaint;
            Paint inner = isSelected ? selectedInnerPaint : innerCirclePaint;

            canvas.drawCircle(pixelX, pixelY, outerR, outer);
            canvas.drawCircle(pixelX, pixelY, innerR, inner);
        }

        // Ensure the selected point is drawn on top if it wasn't rendered in the loop
        if (this.selectedPointIndex >= 0 && this.selectedPointIndex < totalPoints) {
            Location selLoc = points.get(this.selectedPointIndex);
            if (selLoc != null) {
                int px = (int) (MercatorProjection.longitudeToPixelX(selLoc.getLongitude(), mapSize) - topLeftPoint.x);
                int py = (int) (MercatorProjection.latitudeToPixelY(selLoc.getLatitude(), mapSize) - topLeftPoint.y);
                int outerR = baseRadius + 6;
                int innerR = baseRadius;
                canvas.drawCircle(px, py, outerR, selectedOuterPaint);
                canvas.drawCircle(px, py, innerR, selectedInnerPaint);
            }
        }
    }
}
