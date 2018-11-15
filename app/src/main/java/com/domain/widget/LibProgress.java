package com.domain.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @name LibViewAPP
 * @class name：com.domain.widget
 * @class describe
 * @anthor David
 * @time 2018/10/24 21:53
 * @class describe
 */
public class LibProgress extends View {

    private static final int DEFAULT_MIN_WIDTH = 400;

    private float width;
    private float height;
    private float raduis;

    private float currentAngle = 0f;

    /**基础颜色，这里是橙红色*/
    private static final int RED = 230, GREEN = 85, BLUE = 35;

    /**最小不透明度*/
    private static final int MIN_ALPHA = 30;

    /**最大不透明度*/
    private static final int MAX_ALPHA = 255;

    /**圆环外圆半径占View最大半径的百分比*/
    private static final float mRaduisPercent = 0.65f;

    /**圆环宽度占View最大半径的百分比*/
    private static final float mWidthPercent = 0.12f;

    /**第二个圆出现时，第一个圆的半径百分比*/
    private static final float MIDDLE_WAVE_RADUIS_PERCENT = 0.9f;

    /**波纹圆环宽度*/
    private static final float WAVE_WIDTH = 5f;

    public LibProgress(Context context) {
        super(context);
        init();
    }

    public LibProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LibProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        thread.start();
    }

    private Thread thread = new Thread(){
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                postInvalidate();
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        resetParams();

        //将画布中心设为原点(0,0), 方便后面计算坐标
        canvas.translate(width / 2, height / 2);
        canvas.rotate(-currentAngle, 0, 0);
        if (currentAngle >= 360f){
            currentAngle = currentAngle - 360f;
        } else{
            currentAngle = currentAngle + 2f;
        }

        //画渐变圆环
        float doughnutWidth = raduis * mWidthPercent;//圆环宽度

        //圆环外接矩形
        RectF rectF = new RectF(-raduis * mRaduisPercent, -raduis * mRaduisPercent, raduis * mRaduisPercent, raduis * mRaduisPercent);
        /*initPaint();
        paint.setStrokeWidth(doughnutWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShader(new SweepGradient(0, 0, doughnutColors, null));
        canvas.drawArc(rectF, 0, 360, false, paint);*/

    }

    private void resetParams() {
        width = getWidth();
        height = getHeight();
        raduis = Math.min(width, height)/2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int measureSpec) {
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
