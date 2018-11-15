package com.domain.slidingconflict;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * @name LibViewAPP
 * @class name：com.domain.widget
 * @class describe
 * @anthor David
 * @time 2018/11/4 22:11
 * @class 外部拦截，即父容器来决定是否拦截
 */
public class LibOuterIntercept extends ViewGroup {
    private int mLastX;
    private int mLastY;

    public LibOuterIntercept(Context context) {
        super(context);
    }

    public LibOuterIntercept(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LibOuterIntercept(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                //这个地方不能返回true，一旦返回true后续的所有事件（ACTION_MOVE和ACTION_UP）
                //都会交给父控件处理，这个时候事件没法在传递给子元素了
                intercepted = false;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                //这个事件可以根据需要来决定是否拦截
                boolean parentNeedDeal = true;//伪代码，父控件需要处理
                if(parentNeedDeal) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                intercepted = false;
                break;
            }
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return intercepted;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
