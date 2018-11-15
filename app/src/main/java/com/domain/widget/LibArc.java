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
 * @time 2018/10/24 23:02
 * @class describe
 */
public class LibArc extends View {

    public LibArc(Context context) {
        super(context);
    }

    public LibArc(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LibArc(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(0.5F);
        canvas.drawCircle(400,500,300,mPaint);

        RectF mrect = new RectF(100,200,700,800);

        Paint pai1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        pai1.setColor(Color.BLACK);
        canvas.drawArc(mrect,-90,195,true,pai1);

        Paint pai2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        pai2.setColor(Color.GREEN);
        canvas.drawArc(mrect,-90,-15,true,pai2);

        Paint pai3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        pai3.setColor(Color.RED);
        canvas.drawArc(mrect,-105,-45,true,pai3);
    }
}
