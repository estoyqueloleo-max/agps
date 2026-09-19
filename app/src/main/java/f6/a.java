package f6;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;

/* JADX INFO: loaded from: classes.dex */
public class a extends Canvas {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Canvas f5272a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f5273b = new Paint(2);

    @Override // android.graphics.Canvas
    public boolean clipPath(Path path, Region.Op op) {
        return this.f5272a.clipPath(path, op);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(RectF rectF, Region.Op op) {
        return this.f5272a.clipRect(rectF, op);
    }

    @Override // android.graphics.Canvas
    public void concat(Matrix matrix) {
        this.f5272a.concat(matrix);
    }

    @Override // android.graphics.Canvas
    public void drawARGB(int i7, int i8, int i9, int i10) {
        this.f5272a.drawARGB(i7, i8, i9, i10);
    }

    @Override // android.graphics.Canvas
    public void drawArc(RectF rectF, float f7, float f8, boolean z, Paint paint) {
        this.f5272a.drawArc(rectF, f7, f8, z, paint);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap bitmap, float f7, float f8, Paint paint) {
        if (paint == null) {
            paint = this.f5273b;
        } else {
            paint.setFilterBitmap(true);
        }
        this.f5272a.drawBitmap(bitmap, f7, f8, paint);
    }

    @Override // android.graphics.Canvas
    public void drawBitmapMesh(Bitmap bitmap, int i7, int i8, float[] fArr, int i9, int[] iArr, int i10, Paint paint) {
        this.f5272a.drawBitmapMesh(bitmap, i7, i8, fArr, i9, iArr, i10, paint);
    }

    @Override // android.graphics.Canvas
    public void drawCircle(float f7, float f8, float f9, Paint paint) {
        this.f5272a.drawCircle(f7, f8, f9, paint);
    }

    @Override // android.graphics.Canvas
    public void drawColor(int i7) {
        this.f5272a.drawColor(i7);
    }

    @Override // android.graphics.Canvas
    public void drawLine(float f7, float f8, float f9, float f10, Paint paint) {
        this.f5272a.drawLine(f7, f8, f9, f10, paint);
    }

    @Override // android.graphics.Canvas
    public void drawLines(float[] fArr, int i7, int i8, Paint paint) {
        this.f5272a.drawLines(fArr, i7, i8, paint);
    }

    @Override // android.graphics.Canvas
    public void drawOval(RectF rectF, Paint paint) {
        this.f5272a.drawOval(rectF, paint);
    }

    @Override // android.graphics.Canvas
    public void drawPaint(Paint paint) {
        this.f5272a.drawPaint(paint);
    }

    @Override // android.graphics.Canvas
    public void drawPath(Path path, Paint paint) {
        this.f5272a.drawPath(path, paint);
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture picture) {
        this.f5272a.drawPicture(picture);
    }

    @Override // android.graphics.Canvas
    public void drawPoint(float f7, float f8, Paint paint) {
        this.f5272a.drawPoint(f7, f8, paint);
    }

    @Override // android.graphics.Canvas
    public void drawPoints(float[] fArr, int i7, int i8, Paint paint) {
        this.f5272a.drawPoints(fArr, i7, i8, paint);
    }

    @Override // android.graphics.Canvas
    public void drawPosText(char[] cArr, int i7, int i8, float[] fArr, Paint paint) {
        this.f5272a.drawPosText(cArr, i7, i8, fArr, paint);
    }

    @Override // android.graphics.Canvas
    public void drawRGB(int i7, int i8, int i9) {
        this.f5272a.drawRGB(i7, i8, i9);
    }

    @Override // android.graphics.Canvas
    public void drawRect(RectF rectF, Paint paint) {
        this.f5272a.drawRect(rectF, paint);
    }

    @Override // android.graphics.Canvas
    public void drawRoundRect(RectF rectF, float f7, float f8, Paint paint) {
        this.f5272a.drawRoundRect(rectF, f7, f8, paint);
    }

    @Override // android.graphics.Canvas
    public void drawText(char[] cArr, int i7, int i8, float f7, float f8, Paint paint) {
        this.f5272a.drawText(cArr, i7, i8, f7, f8, paint);
    }

    @Override // android.graphics.Canvas
    public void drawTextOnPath(char[] cArr, int i7, int i8, Path path, float f7, float f8, Paint paint) {
        this.f5272a.drawTextOnPath(cArr, i7, i8, path, f7, f8, paint);
    }

    @Override // android.graphics.Canvas
    public void drawVertices(Canvas.VertexMode vertexMode, int i7, float[] fArr, int i8, float[] fArr2, int i9, int[] iArr, int i10, short[] sArr, int i11, int i12, Paint paint) {
        this.f5272a.drawVertices(vertexMode, i7, fArr, i8, fArr2, i9, iArr, i10, sArr, i11, i12, paint);
    }

    @Override // android.graphics.Canvas
    public boolean getClipBounds(Rect rect) {
        return this.f5272a.getClipBounds(rect);
    }

    @Override // android.graphics.Canvas
    public DrawFilter getDrawFilter() {
        return this.f5272a.getDrawFilter();
    }

    @Override // android.graphics.Canvas
    public int getHeight() {
        return this.f5272a.getHeight();
    }

    @Override // android.graphics.Canvas
    public void getMatrix(Matrix matrix) {
        this.f5272a.getMatrix(matrix);
    }

    @Override // android.graphics.Canvas
    public int getSaveCount() {
        return this.f5272a.getSaveCount();
    }

    @Override // android.graphics.Canvas
    public int getWidth() {
        return this.f5272a.getWidth();
    }

    @Override // android.graphics.Canvas
    public boolean isOpaque() {
        return this.f5272a.isOpaque();
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(RectF rectF, Canvas.EdgeType edgeType) {
        return this.f5272a.quickReject(rectF, edgeType);
    }

    @Override // android.graphics.Canvas
    public void restore() {
        this.f5272a.restore();
    }

    @Override // android.graphics.Canvas
    public void restoreToCount(int i7) {
        this.f5272a.restoreToCount(i7);
    }

    @Override // android.graphics.Canvas
    public void rotate(float f7) {
        this.f5272a.rotate(f7);
    }

    @Override // android.graphics.Canvas
    public int save() {
        return this.f5272a.save();
    }

    @Override // android.graphics.Canvas
    public int saveLayer(RectF rectF, Paint paint, int i7) {
        return this.f5272a.saveLayer(rectF, paint, i7);
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(RectF rectF, int i7, int i8) {
        return this.f5272a.saveLayerAlpha(rectF, i7, i8);
    }

    @Override // android.graphics.Canvas
    public void scale(float f7, float f8) {
        this.f5272a.scale(f7, f8);
    }

    @Override // android.graphics.Canvas
    public void setBitmap(Bitmap bitmap) {
        this.f5272a.setBitmap(bitmap);
    }

    @Override // android.graphics.Canvas
    public void setDrawFilter(DrawFilter drawFilter) {
        this.f5272a.setDrawFilter(drawFilter);
    }

    @Override // android.graphics.Canvas
    public void setMatrix(Matrix matrix) {
        this.f5272a.setMatrix(matrix);
    }

    @Override // android.graphics.Canvas
    public void skew(float f7, float f8) {
        this.f5272a.skew(f7, f8);
    }

    @Override // android.graphics.Canvas
    public void translate(float f7, float f8) {
        this.f5272a.translate(f7, f8);
    }

    @Override // android.graphics.Canvas
    public boolean clipPath(Path path) {
        return this.f5272a.clipPath(path);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(Rect rect, Region.Op op) {
        return this.f5272a.clipRect(rect, op);
    }

    @Override // android.graphics.Canvas
    public void drawColor(int i7, PorterDuff.Mode mode) {
        this.f5272a.drawColor(i7, mode);
    }

    @Override // android.graphics.Canvas
    public void drawLines(float[] fArr, Paint paint) {
        this.f5272a.drawLines(fArr, paint);
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture picture, RectF rectF) {
        this.f5272a.drawPicture(picture, rectF);
    }

    @Override // android.graphics.Canvas
    public void drawPoints(float[] fArr, Paint paint) {
        this.f5272a.drawPoints(fArr, paint);
    }

    @Override // android.graphics.Canvas
    public void drawPosText(String str, float[] fArr, Paint paint) {
        this.f5272a.drawPosText(str, fArr, paint);
    }

    @Override // android.graphics.Canvas
    public void drawRect(Rect rect, Paint paint) {
        this.f5272a.drawRect(rect, paint);
    }

    @Override // android.graphics.Canvas
    public void drawText(String str, float f7, float f8, Paint paint) {
        this.f5272a.drawText(str, f7, f8, paint);
    }

    @Override // android.graphics.Canvas
    public void drawTextOnPath(String str, Path path, float f7, float f8, Paint paint) {
        this.f5272a.drawTextOnPath(str, path, f7, f8, paint);
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(Path path, Canvas.EdgeType edgeType) {
        return this.f5272a.quickReject(path, edgeType);
    }

    @Override // android.graphics.Canvas
    public int saveLayer(float f7, float f8, float f9, float f10, Paint paint, int i7) {
        return this.f5272a.saveLayer(f7, f8, f9, f10, paint, i7);
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(float f7, float f8, float f9, float f10, int i7, int i8) {
        return this.f5272a.saveLayerAlpha(f7, f8, f9, f10, i7, i8);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(RectF rectF) {
        return this.f5272a.clipRect(rectF);
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture picture, Rect rect) {
        this.f5272a.drawPicture(picture, rect);
    }

    @Override // android.graphics.Canvas
    public void drawRect(float f7, float f8, float f9, float f10, Paint paint) {
        this.f5272a.drawRect(f7, f8, f9, f10, paint);
    }

    @Override // android.graphics.Canvas
    public void drawText(String str, int i7, int i8, float f7, float f8, Paint paint) {
        this.f5272a.drawText(str, i7, i8, f7, f8, paint);
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(float f7, float f8, float f9, float f10, Canvas.EdgeType edgeType) {
        return this.f5272a.quickReject(f7, f8, f9, f10, edgeType);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(Rect rect) {
        return this.f5272a.clipRect(rect);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap bitmap, Rect rect, RectF rectF, Paint paint) {
        if (paint == null) {
            paint = this.f5273b;
        } else {
            paint.setFilterBitmap(true);
        }
        this.f5272a.drawBitmap(bitmap, rect, rectF, paint);
    }

    @Override // android.graphics.Canvas
    public void drawText(CharSequence charSequence, int i7, int i8, float f7, float f8, Paint paint) {
        this.f5272a.drawText(charSequence, i7, i8, f7, f8, paint);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(float f7, float f8, float f9, float f10, Region.Op op) {
        return this.f5272a.clipRect(f7, f8, f9, f10, op);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(float f7, float f8, float f9, float f10) {
        return this.f5272a.clipRect(f7, f8, f9, f10);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(int i7, int i8, int i9, int i10) {
        return this.f5272a.clipRect(i7, i8, i9, i10);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap bitmap, Rect rect, Rect rect2, Paint paint) {
        if (paint == null) {
            paint = this.f5273b;
        } else {
            paint.setFilterBitmap(true);
        }
        this.f5272a.drawBitmap(bitmap, rect, rect2, paint);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(int[] iArr, int i7, int i8, int i9, int i10, int i11, int i12, boolean z, Paint paint) {
        Paint paint2 = paint;
        if (paint2 == null) {
            paint2 = this.f5273b;
        } else {
            paint2.setFilterBitmap(true);
        }
        this.f5272a.drawBitmap(iArr, i7, i8, i9, i10, i11, i12, z, paint2);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint) {
        if (paint == null) {
            paint = this.f5273b;
        } else {
            paint.setFilterBitmap(true);
        }
        this.f5272a.drawBitmap(bitmap, matrix, paint);
    }
}
