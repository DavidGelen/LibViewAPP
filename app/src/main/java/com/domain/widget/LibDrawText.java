package com.domain.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.view.View;

import java.util.Locale;

/**
 * @name LibViewAPP
 * @class name：com.domain.widget
 * @class describe
 * @anthor David
 * @time 2018/10/14 11:06
 * @class describe
 */
public class LibDrawText extends View {

    public LibDrawText(Context context) {
        super(context);
    }

    public LibDrawText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LibDrawText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int textHeight = getMeasuredHeight() + 30;

        String text = "雨谷底条今直沿微写";
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(30);

        paint.setTextLocale(Locale.CHINA); // 简体中文
        canvas.drawText(text, 150, 150, paint);
        paint.setTextLocale(Locale.TAIWAN); // 繁体中文
        canvas.drawText(text, 150, 150 + 40, paint);
        paint.setTextLocale(Locale.JAPAN); // 日语
        canvas.drawText(text, 150, 150 + 40 * 2, paint);
    }
}
