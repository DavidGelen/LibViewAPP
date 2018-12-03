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
 * @time 2018/12/3 17:19
 * @class describe
 */
public class LibDraw4 extends View {

    public LibDraw4(Context context) {
        super(context);
    }

    public LibDraw4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LibDraw4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        //锁住画布
        //save( )和restore( )最好配对使用，若restore( )的调用次数比save( )多可能会造成异常
        canvas.save();
        @SuppressLint("DrawAllocation") Rect rect = new Rect(20, 200, 900, 1000);
        canvas.clipRect(rect);
        canvas.drawColor(Color.YELLOW);
        paint.setColor(Color.BLACK);
        canvas.drawText("黄色部分为Canvas剪裁后的区域", 10, 310, paint);
        canvas.restore();
        //完全锁住这个黄色的画布

        paint.setColor(Color.RED);
        canvas.drawText("XXOO", 20, 170, paint);
    }
}
