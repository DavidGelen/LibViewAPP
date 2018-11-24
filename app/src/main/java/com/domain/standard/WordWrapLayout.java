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
        //计算WordWrapLayout的宽高和测量模式
        int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //单行最大宽度
        int maxSingleLineWidth = 0;

        //所有子View的高度
        int totalHeight = 0;

        //当前行的宽度
        int curLineWidth = 0;

        //当前行的高度
        int curLineHeight = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);

            //如果gone，不测量了
            if (View.GONE == childView.getVisibility()) {
                continue;
            }

            //对子View进行测量
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            //考虑子View的Margin
            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();

            //子View需要的宽度 为 子View 本身宽度+marginLeft + marginRight
            int childViewUsedWidth = childView.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childViewUsedHeight = childView.getMeasuredHeight() + params.topMargin + params.bottomMargin;

            //这里考虑了WordWrapLayout的padding,这个时候要考虑换行了
            if (curLineWidth + childViewUsedWidth > widthMeasureSize - getPaddingLeft() - getPaddingRight()) {
                //得到最新的最大行宽,用于设置父控件的宽度
                maxSingleLineWidth = Math.max(maxSingleLineWidth, curLineWidth);

                //上面统计了宽度，再来统计高度，因为高度是线性增加的
                totalHeight += curLineHeight;

                //刷新当前行宽高数据
                curLineWidth = childViewUsedWidth;
                curLineHeight = childViewUsedHeight;
            } else {
                //不换行,叠加当前行宽 和 拿到当前最高的那个子View的高度
                curLineWidth += childViewUsedWidth;
                curLineHeight = Math.max(curLineHeight, childViewUsedHeight);
            }

            //如果已经是最后一个View,要比较当前行的 宽度和最大宽度，叠加一共的高度
            if (i == count - 1) {
                maxSingleLineWidth = Math.max(maxSingleLineWidth, curLineWidth);
                totalHeight += childViewUsedHeight;
            }

            //适配padding,如果是wrap_content,则除了子控件本身占据的控件，还要在加上父控件的padding
            setMeasuredDimension(
                    widthMode == MeasureSpec.EXACTLY ? widthMeasureSize : maxSingleLineWidth + getPaddingLeft() + getPaddingRight(),
                    heightMode == MeasureSpec.EXACTLY ? heightMeasureSize : totalHeight + getPaddingTop() + getPaddingBottom());
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();

        //得到WordWrapLayout的宽高(包含padding)
        int width = getWidth();

        //子View水平排列的上限边界
        int rightLimit = width - getPaddingRight();

        //在WordWrapLayout里面摆放子View的起点，即左上角的顶点
        int baseLeft = getPaddingLeft();
        int baseTop = getPaddingTop();

        //存储现在的left top
        int curLeft = baseLeft;
        int curTop = baseTop;

        //临时增加一个temp 存储上一个View的高度 解决过长的两行View导致显示不正确的bug
        int lastChildHeight = 0;

        //子View怎么摆全靠这4个点，这4个点决定了子View在WordWrapLayout中的位置
        int viewL, viewT, viewR, viewB;

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);

            if (View.GONE == childView.getVisibility()) {
                continue;
            }

            //获取子View本身的宽高:
            int childMeasuredWidth = childView.getMeasuredWidth();
            int childMeasuredHeight = childView.getMeasuredHeight();

            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();

            //子View需要的宽高 为 本身宽高+marginLeft + marginRight
            int oneChildNeedWidth = childMeasuredWidth + params.leftMargin + params.rightMargin;
            int oneChildNeedHeight = childMeasuredHeight + params.topMargin + params.bottomMargin;

            if (curLeft + oneChildNeedWidth > rightLimit) {
                //换行
                curTop += lastChildHeight;

                //子View layout时候4个点的坐标
                viewL = curLeft + params.leftMargin;
                viewT = curTop + params.topMargin;
                viewR = viewL + childMeasuredWidth;
                viewB = viewT + childMeasuredHeight;
                curLeft = baseLeft + oneChildNeedWidth;
            } else {
                viewL = curLeft + params.leftMargin;
                viewT = curTop + params.topMargin;
                viewR = viewL + childMeasuredWidth;
                viewB = viewT + childMeasuredHeight;
                curLeft = curLeft + oneChildNeedWidth;
            }

            lastChildHeight = oneChildNeedHeight;
            childView.layout(viewL, viewT, viewR, viewB);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

}
