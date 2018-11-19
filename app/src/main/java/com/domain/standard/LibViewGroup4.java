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
 * @time 2018/11/17 15:46
 * @class describe
 */
public class LibViewGroup4 extends ViewGroup {

    public LibViewGroup4(Context context) {
        super(context);
    }

    public LibViewGroup4(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LibViewGroup4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();
        if(childCount == 0) {
            setMeasuredDimension(0,0);
            return;
        }

        int widthPadding = getPaddingLeft() + getPaddingRight();
        int heightPadding = getPaddingTop() + getPaddingBottom();

        int widthMarginSize = 0;
        int heightMarginSize = 0;

        int usedWidth = 0;
        int usedHeight = 0;
        for(int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LibViewGroup4.LayoutParams params = (LayoutParams) childView.getLayoutParams();
            widthMarginSize = params.leftMargin + params.rightMargin;
            heightMarginSize += params.topMargin + params.bottomMargin;

            //永远等于child最宽的那个宽度
            usedWidth = (usedWidth < childView.getMeasuredWidth()) ? childView.getMeasuredWidth() : usedWidth;
            usedHeight += childView.getMeasuredHeight();
        }

        setMeasuredDimension((widthMode == MeasureSpec.AT_MOST)
                        ? widthPadding + usedWidth + widthMarginSize
                        : widthSpecSize + widthPadding + widthMarginSize,
                (heightMode == MeasureSpec.AT_MOST)
                        ? usedHeight + heightPadding + heightMarginSize
                        : heightSpecSize + heightPadding + heightMarginSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if(childCount == 0) {
            return;
        }

        int usedHeight = 0;
        for(int i = 0;i < getChildCount();i++){
            View child = getChildAt(i);
            LibViewGroup4.LayoutParams params = (LayoutParams) child.getLayoutParams();
            child.layout(0 + getPaddingLeft() + params.leftMargin,
                    usedHeight + getPaddingTop(),
                    child.getMeasuredWidth() + getPaddingRight() + params.rightMargin,
                    usedHeight + child.getMeasuredHeight() + getPaddingBottom());

            usedHeight +=
                    child.getMeasuredHeight() +
                            params.topMargin +
                            params.bottomMargin;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LibViewGroup4.LayoutParams(getContext(), attrs);
    }
}
