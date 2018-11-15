package com.domain.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.domain.R;

/**
 * @name LibViewAPP
 * @class name：com.domain.widget
 * @class describe
 * @anthor David
 * @time 2018/11/2 22:25
 * @class describe
 */
public class LibSwipeMenuLayout extends ViewGroup {

    /**
     * 左侧为菜单
     */
    private boolean isLeftMenu = true;

    /**
     * 拦截子类的点击事件，给父类享用
     */
    private boolean enableParentLongClick = false;

    /**
     * 滑动的最小判断距离
     */
    private int mScaleTouchSlop;

    /**
     * scrollView抛掷的最大速度
     */
    private int mMaxVelocity;

    private float expandRatio = 0.3f;
    private float collapseRatio = 0.7f;
    private int expandDuration = 150;
    private int collapseDuration = 150;

    /**
     * 菜单布局宽度
     */
    private int mWidthofMenu;

    /**展开的阈值*/
    private int mExpandLimit;

    /**关闭的阈值*/
    private int mCollapseLimit;

    public LibSwipeMenuLayout(Context context) {
        this(context, null);
    }

    public LibSwipeMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LibSwipeMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.SwipeMenuLayout, defStyleAttr,
                0);
        int count = ta.getIndexCount();

        for (int i = 0; i < count; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.SwipeMenuLayout_isLeftMenu:
                    isLeftMenu = ta.getBoolean(attr, true);
                    break;

                case R.styleable.SwipeMenuLayout_enableParentLongClick:
                    enableParentLongClick = ta.getBoolean(attr, false);
                    break;

                case R.styleable.SwipeMenuLayout_expandRatio:
                    expandRatio = ta.getFloat(attr, 0.3f);
                    break;

                case R.styleable.SwipeMenuLayout_collapseRatio:
                    collapseRatio = 1 - ta.getFloat(attr, 0.3f);
                    break;

                case R.styleable.SwipeMenuLayout_expandDuration:
                    expandDuration = ta.getInt(attr, 150);
                    break;

                case R.styleable.SwipeMenuLayout_collapseDuration:
                    collapseDuration = ta.getInt(attr, 150);
                    break;
            }
        }
        ta.recycle();
        mScaleTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMaxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setClickable(true);

        //防止多次measure导致累加
        mWidthofMenu = 0;
        int widthOfContent = 0;//content的宽度
        int heightOfContent = 0;//content的高度

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.setClickable(true);
            if (childView.getVisibility() == GONE)
                continue;
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            heightOfContent = Math.max(heightOfContent, childView.getMeasuredHeight());
            if (i == 0) {
                widthOfContent = getMeasuredWidth();
            } else {
                mWidthofMenu += childView.getMeasuredWidth();
            }
        }

        mExpandLimit = (int) (mWidthofMenu * expandRatio);
        mCollapseLimit = (int) (mWidthofMenu * collapseRatio);
        setMeasuredDimension(widthOfContent, heightOfContent);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = l;
        int right = r;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() == GONE)
                continue;
            if (i == 0) {
                childView.layout(left, getPaddingTop(), left + childView.getMeasuredWidth(), getPaddingTop() + childView.getMeasuredHeight());
            } else {
                if (isLeftMenu) {
                    childView.layout(left - childView.getMeasuredWidth(), getPaddingTop(), left, getPaddingTop() + childView.getMeasuredHeight());
                    left = left - childView.getMeasuredWidth();
                } else {//菜单在右边
                    childView.layout(right, getPaddingTop(), right + childView.getMeasuredWidth(), getPaddingTop() + childView.getMeasuredHeight());
                    right = right + childView.getMeasuredWidth();
                }
            }
        }
    }
}
