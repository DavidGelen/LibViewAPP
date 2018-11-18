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
 * @time 2018/11/17 14:23
 * @class describe
 */
public class LibViewGroup2 extends ViewGroup {

    public LibViewGroup2(Context context) {
        super(context);
    }

    public LibViewGroup2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LibViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);//测量所有的子布局,这句不可以遗漏

        //得到自身的测量模式和实际测量的大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int maxWidth = 0;//最大宽度
        int maxHeight = 0;//最大高度

        //测量子空间的宽，高然后汇总
        int childCount = getChildCount();
        if (childCount < 1) {
            setMeasuredDimension(0, 0);
            return;
        }

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            //这一句一定要这么写，为什么这么写，请用屁股想
           /* if(maxWidth < childView.getMeasuredWidth()){
                maxWidth = childView.getMeasuredWidth();
            }*/

           //我们取所有子view里最宽和最高的值作为我们布局的宽高 很符合"包裹内容"的定义
            maxWidth = childView.getMeasuredWidth() > maxWidth ? childView.getMeasuredWidth() : maxWidth;
            maxHeight += childView.getMeasuredHeight();
        }

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(maxWidth, maxHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(maxWidth, heightSpecSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, maxHeight);
        } else {
            setMeasuredDimension(widthSpecSize, heightSpecSize);
        }
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
