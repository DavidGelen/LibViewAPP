package com.domain.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @name LibViewAPP
 * @class name：com.domain.widget
 * @class describe
 * @anthor David
 * @time 2018/10/25 20:40
 * @class describe
 */
public class LibLine extends View {

    public LibLine(Context context) {
        super(context);
    }

    public LibLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LibLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int mWidth = getWidth();
        int mHeight = getHeight();

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawCircle(0,0,400,mPaint);
        canvas.drawCircle(0,0,380,mPaint);

        for(int i = 0; i < 36;i++) {
            canvas.rotate(10);//这是相对位置，不是绝对，记住这点
            canvas.drawLine(380,0,400,0,mPaint);
        }
    }

    private void type5(Canvas canvas, Paint mPaint, int mWidth, int mHeight) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.rotate(30);
        canvas.drawLine(0,0,300,0,mPaint);
    }

    private void type4(Canvas canvas, Paint mPaint, int mWidth, int mHeight) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(-400,-400,400,400);   // 矩形区域
        for (int i = 0; i<= 20; i++)
        {
            canvas.scale(0.9f,0.9f);
            canvas.drawRect(rect,mPaint);
        }
    }

    private void type3(Canvas canvas, Paint mPaint, int mWidth, int mHeight) {
        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(0,-400,400,0);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect,mPaint);


        canvas.scale(-0.5f,0.5f);          // 画布缩放

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);
    }

    private void type2(Canvas canvas, Paint mPaint, int mWidth, int mHeight) {
        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        // 矩形区域
        RectF rect = new RectF(0,-400,400,0);

        // 绘制黑色矩形
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rect,mPaint);

        // 画布缩放  <-- 缩放中心向右偏移了200个单位
        canvas.scale(0.5f,0.5f,200,0);

        // 绘制蓝色矩形
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rect,mPaint);
    }

    private void type1(Canvas canvas, Paint mPaint, int mWidth, int mHeight) {
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rect = new RectF(0,-400,400,0);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect,mPaint);

        canvas.scale(0.5f,0.5f);                // 画布缩放

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);
    }
}
