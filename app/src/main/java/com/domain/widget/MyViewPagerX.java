package com.domain.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @name LibViewAPP
 * @package name：com.domain.widget
 * @anthor DavidZhang
 * @time 2018/11/30 14:39
 * @class describe
 */
public class MyViewPagerX extends ViewGroup {

    private Scroller mScroller;
    private VelocityTracker mTracker;
    private Context context;
    private int mLastX;
    private int mLastY;
    private int currentIndex;

    public MyViewPagerX(Context context) {
        super(context);
        initView();
        this.context = context;
    }

    public MyViewPagerX(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        this.context = context;
    }

    public MyViewPagerX(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        this.context = context;
    }

    private void initView() {
        if (mScroller == null) {
            mScroller = new Scroller(context);
            mTracker = VelocityTracker.obtain();
        }
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        //测量子View
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //支持wrap_content
        View childView = getChildAt(0);
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (measureWidthMode == MeasureSpec.AT_MOST && measureHeightMode == MeasureSpec.AT_MOST) {
            measureWidth = childView.getMeasuredWidth() * childCount;
            measureHeight = childView.getMeasuredHeight();
        } else if (measureHeightMode == MeasureSpec.AT_MOST) {
            measureHeight = childView.getMeasuredHeight();
        } else if (measureWidthMode == MeasureSpec.AT_MOST) {
            measureWidth = childView.getMeasuredWidth() * childCount;
        }
        //测量自己
        setMeasuredDimension(measureWidth, measureHeight);
    }

    /**
     * 布局：layout确定自己的位置
     * onLayout确定子View的位置
     * 1：遍历子View，分别获取各个子View的left,right,top,bottom等值
     * 2：进行布局：childView.layout（）；
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                //严谨一点判断一下当前View有没有被Gone掉
                if (childView.getVisibility() != GONE) {
                    int childWidth = childView.getMeasuredWidth();
                    int childHeight = childView.getMeasuredHeight();
                    childView.layout(childWidth * (i - 1), 0, childWidth + i, childHeight);
                }

            }
        }
    }

    /**
     * 借助scroller弹性滑动方法：
     * 1：初始化scroller对象mScroller;
     * 2:设置滑动距离：mScroller.startScroll(mScroller.getFinalX(),mScroller.getFinalY(),x,y);
     * 3：重绘
     * 4：重写computeScroll（），自己实现滑动逻辑
     * 5：重绘
     *
     * @param x
     * @param y
     */
    private void startSmoothTo(int x, int y) {
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), x, y);
        invalidate();

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }

    }

    @Override
    protected void onDetachedFromWindow() {
        mTracker.recycle();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isIntercepted = false;

                if (!mScroller.isFinished()) {
                    //如果没有滑动结束比方说：处理用户手指快速滑动并离开屏幕然后点击屏幕也就是
                    // “快速滑动”这一过程未结束，重新进来ACTION_DOWN事件对其进行拦截
                    mScroller.abortAnimation();
                    isIntercepted = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                //如果这里返回true的话，内部控件无法接收ACTION_UP事件
                //考虑perfomOnClick()在Action_up中执行，所以
                //内部控件无法相应onClick事件
                isIntercepted = false;

                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                if (Math.abs(x - mLastX) > Math.abs(y - mLastY)) {
                    isIntercepted = true;
                } else {
                    isIntercepted = false;
                }
                break;
        }
        mLastY = y;
        mLastX = x;
        return isIntercepted;
    }

    /**
     * 1：ACTION_DOWN中：不做任何处理，见注释
     * 思考：主要是action_move和action_up中的逻辑：
     * action_up中等同于滑动动作结束，需要判断当前滑动距离应该显示哪个页面
     * action_move中不需要考虑那么多，滑动没结束，继续滑动就行
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //如果没有滑动结束比方说：处理用户手指快速滑动并离开屏幕然后点击屏幕也就是
                // “快速滑动”这一过程未结束，重新进来ACTION_DOWN事件：不做任何处理
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }

                break;
            case MotionEvent.ACTION_UP:
                int mScrollX = getScrollX();
                mTracker.addMovement(event);
                mTracker.computeCurrentVelocity(1000);
                float mXVelocity = mTracker.getXVelocity();
                //如果滑动速度大于50：
                //滑动速度>0证明从左向右滑动，currentIndex应该-1；
                //滑动速度<0证明从右向左滑动，currentIndex应该+1;
                //如果滑动速度小于50
                //自己举例子试试，说不明白；

                // 然后 对currentIndex 进行大于0处理；
                //获取滑动差距，继续代码滑动
                //特别说明getScrollX()表示：控件左边距到控件内容左边距的偏移距离
                if (Math.abs(mXVelocity) > 50) {
                    currentIndex = mXVelocity > 0 ? currentIndex - 1 : currentIndex + 1;
                } else {
                    currentIndex = (mScrollX + getWidth() / 2) / getWidth();
                }
                currentIndex = Math.max(0, Math.min(currentIndex, getChildCount() - 1));
                startSmoothTo(currentIndex * getWidth() - mScrollX, 0);
                mTracker.clear();
                break;
            case MotionEvent.ACTION_MOVE:
                //考虑scrollBy和scrollTo滑动方向和预期相反，所以是mLastX-x,不是x-mLastX;
                scrollBy(mLastX - x, 0);
                break;

        }
        mLastX = x;
        mLastY = y;
        return true;
    }
}
