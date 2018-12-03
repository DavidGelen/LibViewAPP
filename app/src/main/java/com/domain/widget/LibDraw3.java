package com.domain.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @name LibViewAPP
 * @package name：com.domain.widget
 * @anthor DavidZhang
 * @time 2018/12/3 17:11
 * @class describe
 */
public class LibDraw3 extends View {

    public LibDraw3(Context context) {
        super(context);
    }

    public LibDraw3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LibDraw3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        @SuppressLint("DrawAllocation") Paint paint = new Paint();
        paint.setTextSize(60);
        paint.setColor(Color.BLUE);
        canvas.drawText("绿色部分为Canvas剪裁前的区域", 20, 80, paint);
        @SuppressLint("DrawAllocation") Rect rect = new Rect(20, 200, 900, 1000);

        //当我们调用了canvas.clipRect( )后，如果再继续画图那么所绘的图只会在所剪裁的范围内体现。
        //当然除了按照矩形剪裁以外，还可以有别的剪裁方式，比如：canvas.clipPath( )和canvas.clipRegion( )。
        canvas.clipRect(rect);
        canvas.drawColor(Color.YELLOW);
        paint.setColor(Color.BLACK);
        canvas.drawText("黄色部分为Canvas剪裁后的区域", 10, 310, paint);
    }
}
