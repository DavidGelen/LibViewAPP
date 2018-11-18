package com.domain.standard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @name LibViewAPP
 * @class name：com.domain.standard
 * @class describe
 * @anthor David
 * @time 2018/11/17 13:41
 * @class 该ViewGroup系列只展示垂直线性的ViewGroup,不考虑chidView的margin和padding
 */
public class LibViewGroup1 extends ViewGroup {

    public LibViewGroup1(Context context) {
        super(context);
    }

    public LibViewGroup1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LibViewGroup1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int maxWidth = left;
        int maxHeight = top;
        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(maxWidth,maxHeight,maxWidth + child.getMeasuredWidth(),maxHeight + child.getMeasuredHeight());
            maxHeight += child.getMeasuredHeight();
        }
    }
}
