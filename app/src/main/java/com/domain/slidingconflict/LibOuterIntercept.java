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
                //onInterceptTouchEvent()只会调用一次，不会重复调用（一旦处理事件之后，后续不会再调用）
                //这里一定要返回false，如果返回true,事件就不会继续传递
                //父容器必须返回false，即不拦截ACTION_DOWN事件，
                //这是因为一旦父容器拦截了ACTION_DOWN，
                //那么后续的ACTION_MOVE和ACTION_UP事件都会直接交由父容器处理，
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
                //最后是ACTION_UP事件，这里必须要返回false，
                //因为ACTION_UP事件本身没有太多意义考虑一种情况，假设事件交由子元素处理，如果父容器在ACTION_UP时返回了true，
                //会导致子元素无法接收到ACTION_UP事件，这个时候子元素中的onClick事件就无法触发，
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
