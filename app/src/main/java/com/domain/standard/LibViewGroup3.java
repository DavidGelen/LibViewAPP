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
 * @time 2018/11/17 14:53
 * @class describe
 */
public class LibViewGroup3 extends ViewGroup {

    public LibViewGroup3(Context context) {
        super(context);
    }

    public LibViewGroup3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LibViewGroup3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heigtSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();
        if(childCount == 0) {
            setMeasuredDimension(0,0);
            return;
        }

        int maxWidth = 0;
        int maxHeight = 0;
        for(int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if(maxWidth < childView.getMeasuredWidth()) {
                maxWidth = childView.getMeasuredWidth();
            }
            maxHeight += childView.getMeasuredHeight();
        }

        maxWidth = maxWidth + getPaddingStart() + getPaddingEnd();
        maxHeight = maxHeight + getPaddingTop() + getPaddingBottom();

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
           setMeasuredDimension(maxWidth,maxHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(maxWidth,heigtSpecSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize,maxHeight);
        } else {
            setMeasuredDimension(widthSpecSize,heigtSpecSize);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        if(childCount == 0) {
            return;
        }

        int usedHeight = 0;
        for(int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.layout(getPaddingLeft(),
                    usedHeight + getPaddingTop(),
                    childView.getMeasuredWidth() + getPaddingRight(),
                    usedHeight + childView.getMeasuredHeight() + getPaddingBottom());

            usedHeight += childView.getMeasuredHeight();
        }
    }
}
