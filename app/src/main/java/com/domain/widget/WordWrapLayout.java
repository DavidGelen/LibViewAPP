package com.domain.widget;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @name HACN_Mobile_App_Android
 * @package name：lib.widget
 * @anthor DavidZhang
 * @time 2018/10/11 14:33
 * @class describe 支持自动换行的RelativeLayout
 */
public class WordWrapLayout extends RelativeLayout {

    List<Integer> eachLineHeight = new ArrayList<>();
    List<List<View>> allViews = new ArrayList<>();

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
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        int width = 0;
        int height = 0;

        int lineWidth = 0;
        int lineHeight = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams childLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childMeasuredHeight = child.getMeasuredHeight() + childLayoutParams.bottomMargin + childLayoutParams.topMargin;
            int childMeasuredWidth = child.getMeasuredWidth() + childLayoutParams.leftMargin + childLayoutParams.rightMargin;

            if (lineWidth + childMeasuredWidth > widthSpecSize) {
                width = Math.max(lineWidth, childMeasuredWidth);
                height = lineHeight + height;
                lineWidth = childMeasuredWidth;
                lineHeight = childMeasuredWidth;
            } else {
                lineWidth = lineWidth + childMeasuredWidth;
                lineHeight = Math.max(lineHeight, childMeasuredHeight);
            }

            if (i == childCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }

        setMeasuredDimension(widthSpecMode == MeasureSpec.EXACTLY ? widthSpecSize : width,
                heightSpecMode == MeasureSpec.EXACTLY ? heightSpecSize : height
        );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        eachLineHeight.clear();
        allViews.clear();

        int childCount = getChildCount();
        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineViews = new ArrayList<>();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childMeasuredHeight = child.getMeasuredHeight() + layoutParams.bottomMargin + layoutParams.topMargin;
            int childMeasuredWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;

            if (lineWidth + childMeasuredWidth > getWidth()) {
                lineWidth = 0;
                eachLineHeight.add(lineHeight);
                allViews.add(lineViews);
                lineViews = new ArrayList<>();
            }

            lineHeight = Math.max(lineHeight, childMeasuredHeight);
            lineWidth = lineWidth + childMeasuredWidth;
            lineViews.add(child);

            if (i == childCount - 1) {
                eachLineHeight.add(lineHeight);
                allViews.add(lineViews);
            }
        }

        int left = 0;
        int top = 0;

        for (int i = 0; i < allViews.size(); i++) {

            List<View> views = allViews.get(i);
            lineHeight = eachLineHeight.get(i);

            for (int j = 0; j < views.size(); j++) {
                View child = views.get(j);
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child
                        .getLayoutParams();
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();
                child.layout(lc, tc, rc, bc);

                left += child.getMeasuredWidth() + lp.rightMargin
                        + lp.leftMargin;
                Point point = new Point();
                point.x = lc;
                point.y = tc;
                child.setTag(point);
            }
            top = top + lineHeight;
            left = 0;
        }
    }

}
