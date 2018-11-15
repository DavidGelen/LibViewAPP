package com.domain.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @name LibViewAPP
 * @class name：com.domain.widget
 * @class describe
 * @anthor David
 * @time 2018/11/3 22:03
 * @class describe
 */
public class LibViewMove extends View {

    public LibViewMove(Context context) {
        super(context);
    }

    public LibViewMove(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LibViewMove(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int mLastX;
    private int mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;

                //int translationX = ViewH
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }
}
