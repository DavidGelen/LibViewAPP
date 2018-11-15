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
 * @time 2018/11/15 22:02
 * @class describe
 */
public class CustomViewGroup extends ViewGroup {

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     *  @author David
     *  @time 2018/11/15  22:03
     * /@param left top right bottom 当前ViewGroup相对于其父控件的坐标位置
     *@param changed 该参数指出当前ViewGroup的尺寸或者位置是否发生了改变
     *  @describe onLayout
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int mViewGroupWidth  = getMeasuredWidth();  //当前ViewGroup的总宽度
        int mViewGroupHeight = getMeasuredHeight(); //当前ViewGroup的总高度

        int mPainterPosX = left; //当前绘图光标横坐标位置
        int mPainterPosY = top;  //当前绘图光标纵坐标位置

        int childCount = getChildCount();
        for ( int i = 0; i < childCount; i++ ) {
            View childView = getChildAt(i);
            int width  = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();
            CustomViewGroup.LayoutParams margins = (CustomViewGroup.LayoutParams)(childView.getLayoutParams());

            //ChildView占用的width  = width+leftMargin+rightMargin
            //ChildView占用的height = height+topMargin+bottomMargin
            //如果剩余的空间不够，则移到下一行开始位置
            if( mPainterPosX + width + margins.leftMargin + margins.rightMargin > mViewGroupWidth ) {
                mPainterPosX = left;
                mPainterPosY += height + margins.topMargin + margins.bottomMargin;
            }

            //执行ChildView的绘制
            childView.layout(mPainterPosX+margins.leftMargin,
                    mPainterPosY+margins.topMargin,
                    mPainterPosX+margins.leftMargin+width,
                    mPainterPosY+margins.topMargin+height);
            mPainterPosX += width + margins.leftMargin + margins.rightMargin;
        }
    }

    /**
     *  @author David
     *  @time 2018/11/15  22:06
     *  如果要自定义ViewGroup支持子控件的layout_margin参数，
     *  则自定义的ViewGroup类必须重载generateLayoutParams()函数，
     *  并且在该函数中返回一个ViewGroup.MarginLayoutParams派生类对象，
     *  这样才能使用margin参数。
     *  @describe
     */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomViewGroup.LayoutParams(getContext(), attrs);
    }
}
