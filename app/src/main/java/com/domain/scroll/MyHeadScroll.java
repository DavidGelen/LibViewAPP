package com.domain.scroll;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

/*上面代码可以说是模板，借助scroller实现弹性滑动基本上都这样写（不懂的地方看注释），大致流程如下：
        1：初始化mScroller,并设置滑动数据，重绘；
        2：手动实现computScroll(）方法，自己实现滑动逻辑；
        3：切记，两个重绘的地方，缺一不可；*/

public class MyHeadScroll extends View {

    private Scroller mScroller;

    public MyHeadScroll(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }
    private void startScroll(int x,int y){
        /**
         * 2 设置滑动距离数据
         */
        mScroller.startScroll(mScroller.getFinalX(),mScroller.getFinalY(),x,y);
        /**
         * 3 重绘，会调用触发computeScroll（）
         */
        invalidate();
    }

    @Override
    //这个方法是个空实现，需要重写，自己写滑动逻辑
    public void computeScroll() {
        //判断滑动是否完整，没成就继续滑动，继续重绘，继续调用computeScroll，类似递归，知道移动完整
        if(mScroller.computeScrollOffset()){
            /**
             *4  真正滑动的地方，nnd原来也是借助scrollTo或者scrollBy
             */
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
        super.computeScroll();
    }

}
