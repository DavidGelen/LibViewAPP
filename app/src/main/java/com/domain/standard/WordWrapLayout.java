package com.domain.standard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @name HACN_Mobile_App_Android
 * @package name：lib.widget
 * @anthor DavidZhang
 * @time 2018/10/11 14:33
 * @class describe 支持自动换行的ViewGroup
 */
public class WordWrapLayout extends ViewGroup {

    public WordWrapLayout(Context context) {
        this(context, null);
    }

    public WordWrapLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WordWrapLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightMeasure = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //单行最大宽度
        int maxLineWidth = 0;

        //最后计算出的高度值
        int totalHeight = 0;

        //当前行的宽度
        int curLineWidth = 0;

        //存储当前行的高度
        int curLineHeight = 0;

        int count = getChildCount();
        View child = null;
        MarginLayoutParams params = null;

        //子View Layout需要的宽高(包含margin)，用于计算是否越界
        int childWidth;
        int childHeight;

        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            //如果gone，不测量了
            if (View.GONE == child.getVisibility()) {
                continue;
            }
            //先测量子View
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            params = (MarginLayoutParams) child.getLayoutParams();
            childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            if (curLineWidth + childWidth > widthMeasure - getPaddingLeft() - getPaddingRight()) {
                //立刻换行
                maxLineWidth = Math.max(maxLineWidth, curLineWidth);
                totalHeight += curLineHeight;
                curLineWidth = childWidth;
                curLineHeight = childHeight;
            } else {
                //不换行：叠加当前行宽和比较当前行高
                curLineWidth += childWidth;
                curLineHeight = Math.max(curLineHeight, childHeight);
            }
            //如果已经是最后一个View,要比较当前行的 宽度和最大宽度，叠加一共的高度
            if (i == count - 1) {
                maxLineWidth = Math.max(maxLineWidth, curLineWidth);
                totalHeight += childHeight;
            }
        }

        //适配padding,如果是wrap_content,则除了子控件本身占据的控件，还要在加上父控件的padding
        setMeasuredDimension(
                widthMode != MeasureSpec.EXACTLY? maxLineWidth + getPaddingLeft() + getPaddingRight() : widthMeasure,
                heightMode != MeasureSpec.EXACTLY ? totalHeight + getPaddingTop() + getPaddingBottom() : heightMeasure);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int width = getWidth();
        int rightLimit =  width - getPaddingRight();

        //存储基准的left top
        int baseLeft = getPaddingLeft();
        int baseTop = getPaddingTop();

        //存储现在的left top，这个坐标会随着子View的叠加而改变
        int curLeft = baseLeft;
        int curTop = baseTop;

        View child = null;
        //子view用于layout的 l t r b
        int viewL,viewT,viewR,viewB;
        MarginLayoutParams params = null;
        //子View Layout需要的宽高(包含margin)，用于计算是否越界
        int childWidth;
        int childHeight;
        //子View 本身的宽高
        int childW,childH;

        //临时增加一个temp 存储上一个View的高度
        int lastChildHeight =0;
        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            //如果gone，不布局了
            if (View.GONE == child.getVisibility()) {
                continue;
            }
            childW = child.getMeasuredWidth();
            childH = child.getMeasuredHeight();
            //获取子View的LayoutParams，用于获取其margin
            params = (MarginLayoutParams) child.getLayoutParams();
            //子View需要的宽高 为 本身宽高+marginLeft + marginRight
            childWidth =  childW + params.leftMargin + params.rightMargin;
            childHeight = childH + params.topMargin + params.bottomMargin;
            if (curLeft + childWidth > rightLimit ) {
                curTop = curTop + lastChildHeight;
                viewL = baseLeft +params.leftMargin;
                viewT = curTop + params.topMargin;
                viewR = viewL + childW;
                viewB = viewT + childH;
                curLeft = baseLeft + childWidth;
            } else {
                viewL = curLeft +params.leftMargin;
                viewT = curTop + params.topMargin;
                viewR = viewL + childW;
                viewB = viewT + childH;
                curLeft = curLeft + childWidth;
            }
            lastChildHeight = childHeight;
            child.layout(viewL,viewT,viewR,viewB);
        }
    }

    @Override
    public ViewGroup.MarginLayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
