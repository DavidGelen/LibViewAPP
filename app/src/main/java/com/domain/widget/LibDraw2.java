package com.domain.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @name LibViewAPP
 * @package name：com.domain.widget
 * @anthor DavidZhang
 * @time 2018/12/3 17:08
 * @class describe
 */
public class LibDraw2 extends View {

    public LibDraw2(Context context) {
        super(context);
    }

    public LibDraw2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LibDraw2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        @SuppressLint("DrawAllocation") Paint paint = new Paint();
        paint.setTextSize(70);
        paint.setColor(Color.BLUE);
        canvas.drawText("蓝色字体为Translate前所画", 20, 80, paint);
        //canvas.translate(100, 300);
        canvas.rotate(15);
        paint.setColor(Color.BLACK);
        canvas.drawText("黑色字体为Translate后所画", 20, 80, paint);
    }
}
