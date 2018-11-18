package com.domain

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_layout.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_layout)
        groupLayout.setOnClickListener(this)
        textView.setOnClickListener(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        textView2.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val width = (textView2.width).toFloat()
                //move8(width)
                textView2.viewTreeObserver.removeGlobalOnLayoutListener(this)
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.groupLayout -> {
                // 方式一：通过补间动画移动
                move7()

                // 方式二：通过属性动画移动
                //move2()

                // 方式三：通过setTranslationX或setTranslationY等移动
                // move3()

                // 方式四：通过scrollBy或ScrollTo移动
                //move4()

                // 方式五：通过改变布局参数
                //move5()

                // 方式六：通过改变布局参数
                //move6()
            }

            R.id.textView -> {
                Log.e("WWE", "getLeft: ${textView.left}")
                Log.e("WWE", "getTop: ${textView.top}")
                Log.e("WWE", "getX: ${textView.getX()}")
                Log.e("WWE", "getY: ${textView.getY()}")
            }
        }
    }

    private fun move6() {
        textView.layout(textView.left + 20, textView.top,
                textView.right, textView.bottom)
    }

    private fun move5() {
        val lp = textView.layoutParams as LinearLayout.LayoutParams
        lp.leftMargin += 100
        textView.layoutParams = lp
    }

    private fun move4() {
        groupLayout.scrollBy(-30, -30)
    }

    private fun move7() {
        groupLayout.scrollBy(30, -30)
    }

    private fun move3() {
        textView.translationY = 300f
    }

    private fun move2() {
        val objectAnimator = ObjectAnimator.ofFloat(textView,
                "translationY", 0f, 50f, 100f, 150f, 200f, 250f, 300f)
        objectAnimator.setDuration(2000).start()
    }

    private fun move1() {
        val translateAnimation = TranslateAnimation(20f, 20f,
                0f, 300f)
        translateAnimation.duration = 2000;
        translateAnimation.fillAfter = true;// 保持移动后的状态
        textView.startAnimation(translateAnimation)
    }

    private fun move8(width: Float) {
        Log.d("wwe","width -> $width")
        val translateAnimation = TranslateAnimation(textView2.paddingLeft.toFloat(), -width,
                0f, 0f)
        translateAnimation.duration = 200000
        translateAnimation.fillAfter = true// 保持移动后的状态
        textView2.startAnimation(translateAnimation)
    }
}
