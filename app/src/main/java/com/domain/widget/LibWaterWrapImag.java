package com.domain.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.domain.R;

/**
 * @name LibViewAPP
 * @class name：com.domain.widget
 * @class describe
 * @anthor David
 * @time 2018/10/14 20:37
 * @class describe
 */
public class LibWaterWrapImag extends AppCompatImageView {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public LibWaterWrapImag(Context context) {
        super(context);
    }

    public LibWaterWrapImag(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LibWaterWrapImag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setColor(Color.parseColor("#FFC107"));
        paint.setTextSize(28); //默認單位是像素
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable mDrawable = getDrawable();
        if(mDrawable != null) {
            //在图片上加水印
            canvas.save();
            //canvas.setMatrix(getImageMatrix());
            canvas.concat(getImageMatrix());
            Rect mRect = mDrawable.getBounds();
            canvas.drawText(getContext().getString(R.string.image_size,mRect.width(),mRect.height()),
                    20,40,paint);
            canvas.restore();
        }
    }
}
