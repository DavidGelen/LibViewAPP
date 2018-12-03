package com.domain.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @name LibViewAPP
 * @class name：com.domain.widget
 * @anthor David
 * @time 2018/12/2 12:10
 * @class describe
 */
public class LibListView extends ListView {

    public LibListView(Context context) {
        super(context);
    }

    public LibListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LibListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * @Author: David
     * @Date: 2018/12/2 12:11
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     * @Description: 让一个ScrollView里面的ListView全部展开,可以这么干
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
