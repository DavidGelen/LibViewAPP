package com.domain.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @name LibViewAPP
 * @class name：com.domain.widget
 * @class describe
 * @anthor David
 * @time 2018/11/17 13:13
 * @class describe
 */
@SuppressLint("AppCompatCustomView")
public class LibTextView extends TextView {
    public LibTextView(Context context) {
        super(context);
    }

    public LibTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LibTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        //TextView只有在获取焦点后才会显示隐藏文字,所以把它的返回值始终设置为true。
        return true;
    }
}
