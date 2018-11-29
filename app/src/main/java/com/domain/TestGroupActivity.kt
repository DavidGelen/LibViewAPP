package com.domain

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.VelocityTracker

class TestGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_layout5)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val mVelocityTracker = VelocityTracker.obtain()

        //将事件添加进去（可以理解为告诉mVelocityTracker移动距离）
        mVelocityTracker.addMovement(event)

        //设定测速的单位时间
        mVelocityTracker.computeCurrentVelocity(1000)

        //获取水平方向的移动速度
        val velocityX = mVelocityTracker.xVelocity

        //获取竖直方向的移动速度
        val velocityY = mVelocityTracker.yVelocity

        //不用之后务必进行回收，释放资源
        mVelocityTracker.clear()
        mVelocityTracker.recycle()
        return super.onTouchEvent(event)
    }
}
