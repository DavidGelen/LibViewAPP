package com.domain.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @name LibViewAPP
 * @class name：com.domain.widget
 * @class describe
 * @anthor David
 * @time 2018/10/22 22:34
 * @class 自動換行Layout
 */
public class AutoWrapLayout extends ViewGroup {

    private int mMarginLeft;
    private int mMarginTop;

    public AutoWrapLayout(Context context) {
        super(context);
    }

    public AutoWrapLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoWrapLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = getChildCount();
        if(size < 1) {
            return;
        }

        for(int i = 0; i < size;i++) {
            View child = getChildAt(i);
            child.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);
        }
    }

    @Override
    protected void onLayout(boolean arg0, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        int row = 0;
        int lengthX = left;
        int lengthY = top;

        int VIEW_MARGIN = 15;

        for (int i = 0; i < count; i++) {
            final View child = this.getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            lengthX += width + VIEW_MARGIN;
            lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height
                    + top;

            // if it can't drawing on a same line , skip to next line
            if (lengthX > right) {
                lengthX = width + VIEW_MARGIN + left;
                row++;
                lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height
                        + top;
            }
            child.layout(lengthX - width, lengthY - height, lengthX, lengthY);
        }
    }
}
