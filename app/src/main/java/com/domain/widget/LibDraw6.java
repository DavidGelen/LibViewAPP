package com.domain.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.domain.R;

/**
 * @name LibViewAPP
 * @package name：com.domain.widget
 * @anthor DavidZhang
 * @time 2018/12/3 17:58
 * @class describe
 */
public class LibDraw6 extends View {

    public LibDraw6(Context context) {
        super(context);
    }

    public LibDraw6(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LibDraw6(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*BitmapShader———图像渲染
    LinearGradient——–线性渲染
    RadialGradient——–环形渲染
    SweepGradient——–扫描渲染
    ComposeShader——组合渲染*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        @SuppressLint("DrawAllocation") Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bosh);
        int radius = bitmap.getWidth()/2;
        @SuppressLint("DrawAllocation") BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
        paint.setShader(bitmapShader);
        canvas.translate(250,430);
        canvas.drawCircle(radius, radius, radius, paint);
    }
}
