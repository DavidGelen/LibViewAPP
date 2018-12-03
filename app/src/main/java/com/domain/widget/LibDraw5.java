package com.domain.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.domain.R;

/**
 * @name LibViewAPP
 * @package name：com.domain.widget
 * @anthor DavidZhang
 * @time 2018/12/3 17:30
 * @class describe 画圆角图片
 */
public class LibDraw5 extends View {

    public LibDraw5(Context context) {
        super(context);
    }

    public LibDraw5(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LibDraw5(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBitmapWithMatrix(canvas);
    }

    private void drawBitmapWithMatrix(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bosh);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        canvas.drawBitmap(bitmap, matrix, paint);
        matrix.setTranslate(width / 2, height);
        canvas.drawBitmap(bitmap, matrix, paint);
        matrix.postScale(0.5f, 0.5f);
        //matrix.preScale(2f, 2f);
        canvas.drawBitmap(bitmap, matrix, paint);
    }

    /**
     * @param bitmap 原图
     * @param pixels 角度
     * @return 带圆角的图
     */
    public Bitmap getRoundCornerBitmap(Bitmap bitmap, float pixels) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap roundCornerBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundCornerBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(rect);
        canvas.drawRoundRect(rectF, pixels, pixels, paint);
        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return roundCornerBitmap;
    }

}
