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
 * @time 2018/11/17 16:52
 * @class describe
 */
public class LibViewGroup5 extends ViewGroup {

    public LibViewGroup5(Context context) {
        super(context);
    }

    public LibViewGroup5(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LibViewGroup5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量所有的子view
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else {
            //父布局的宽度
            int measuredWidth = getMeasuredWidth();
            //当前摆放view的总宽度
            int currentWidth = 0;
            //当前摆放view的总高度
            int currentHeight = getChildAt(0).getHeight();
            //当前行view的最大高度
            int currentLineMaxHeight = 0;

            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);

                if (currentLineMaxHeight < child.getMeasuredHeight()) {
                    currentLineMaxHeight = child.getMeasuredHeight();
                }

                if (measuredWidth < currentWidth + child.getMeasuredWidth()) {
                    currentWidth = 0;
                    currentHeight = currentHeight + currentLineMaxHeight;
                } else {
                    currentWidth = currentWidth + child.getMeasuredWidth();
                }
            }

            setMeasuredDimension(measuredWidth, currentHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //父布局的宽度
        int measuredWidth = getMeasuredWidth();
        //父布局的高度
        int measuredHeight = getMeasuredHeight();
        //当前摆放view的总宽度
        int currentWidth = 0;
        //当前摆放view的总高度
        int currentHeight = 0;
        //当前行view的最大高度
        int currentLineMaxHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            if (currentLineMaxHeight < child.getMeasuredHeight()) {
                currentLineMaxHeight = child.getMeasuredHeight();
            }

            if (measuredWidth < currentWidth + child.getMeasuredWidth()) {
                currentWidth = 0;
                currentHeight = currentHeight + currentLineMaxHeight;
            }

            child.layout(currentWidth, currentHeight, currentWidth + child.getMeasuredWidth(), child.getMeasuredHeight() + currentHeight);

            if (measuredWidth >= currentWidth + child.getMeasuredWidth()) {
                currentWidth = currentWidth + child.getMeasuredWidth();
            }
        }

    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }
    }

    @Override
    public LibViewGroup5.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LibViewGroup5.LayoutParams(getContext(), attrs);
    }
}
