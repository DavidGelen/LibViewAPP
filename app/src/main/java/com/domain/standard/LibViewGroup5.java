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
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int withMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //LibViewGroup5的测量出的宽
        int withSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        //LibViewGroup5的测量出的高
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        //用来保存一行的宽高
        int lineWidth = 0, lineHeight = 0;

        //测量出来的宽高
        int width = 0, height = 0;

        int childCount = getChildCount();
        if(childCount == 0) {
            setMeasuredDimension(0,0);
            return;
        }

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            //测量每一个子控件的宽高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            //获取MarginLayoutParams对象
            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();

            //此时子控件的宽高
            int childWidth = childView.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeight = childView.getMeasuredHeight() + params.topMargin + params.bottomMargin;

            //判断当前这个子view的宽是否超过限定的宽度
            if (lineWidth + childWidth > withSpecSize) {
                //换行
                //宽度取最大的
                width = Math.max(lineWidth, childHeight);

                //重新记录下一行的宽度
                lineWidth = childWidth;

                //累加高度
                height += lineHeight;
                //记录下一行的高度
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                //行高取最大值
                lineHeight = Math.max(lineHeight, childHeight);
            }
            if (i == childCount - 1) {
                //在最后一行时的判断
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }
        setMeasuredDimension(withMode == MeasureSpec.EXACTLY ? withSpecSize : width,
                heightMode == MeasureSpec.EXACTLY ? heightSpecSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //父控件的宽度(包含padding)
        int width = getWidth();
        //子控件在父控件中x轴方向上能显示的最大宽度
        int maxWidth = width - getPaddingRight();
        //最开始的位置的
        int baseLeft = getPaddingLeft();
        int baseTop = getPaddingTop();
        //当前的距左边和顶部的距离
        int currentLeft = baseLeft;
        int currentTop = baseTop;
        //表示当前view的left、top、right、bottom的位置
        int viewL = 0, viewT = 0, viewR = 0, viewB = 0;
        //子控件的个数
        int childCount = getChildCount();
        //用来保存要换行的上一个子view的高度
        int lastChildViewHeight = 0;
        for (int i = 0; i < childCount; i++) {
            //当前子view
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == View.GONE) {
                continue;
            }
            //当前子view的MarginLayoutParams对象
            MarginLayoutParams params = (MarginLayoutParams) childAt.getLayoutParams();
            //子view实际的宽高包括margin
            int childWidth = childAt.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeight = childAt.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            if (currentLeft + childWidth > maxWidth) {//换行
                //此时距顶部距离累加
                currentTop += lastChildViewHeight;
                viewL = baseLeft + params.leftMargin;
                viewT = currentTop + params.topMargin;
                viewR = viewL + childAt.getMeasuredWidth();
                viewB = viewT + childAt.getMeasuredHeight();
                //由于此时是新的一行，所以currentLeft要从新开始
                currentLeft = baseLeft + childWidth;
            } else {
                viewL = currentLeft + params.leftMargin;
                viewT = currentTop + params.topMargin;
                viewR = viewL + childAt.getMeasuredWidth();
                viewB = viewT + childAt.getMeasuredHeight();
                //当前距左边距离累加
                currentLeft += childWidth;
            }
            lastChildViewHeight = childHeight;
            childAt.layout(viewL, viewT, viewR, viewB);
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
