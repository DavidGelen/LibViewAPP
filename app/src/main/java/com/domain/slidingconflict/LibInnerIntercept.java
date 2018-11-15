package com.domain.slidingconflict;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @name LibViewAPP
 * @class name：com.domain.slidingconflict
 * @class describe
 * @anthor David
 * @time 2018/11/4 22:28
 * @class 内部拦截，即子容器来决定是否拦截事件
 */
public class LibInnerIntercept extends View {
    private int mLastX;
    private int mLastY;

    public LibInnerIntercept(Context context) {
        super(context);
    }

    public LibInnerIntercept(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LibInnerIntercept(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int delataY = y - mLastY;
                boolean parentNeedDeal = true;
                if(parentNeedDeal) {
                    //如果父容器需要处理此类点击事件
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(event);
    }
}
