package com.domain

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.domain.vlip.FlipperView
import kotlinx.android.synthetic.main.activity_test_view.*

class TestViewActivity : AppCompatActivity(), FlipperView.OnNoticeClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_frame_layout)
        //setContentView(R.layout.activity_test_view)
        val notices = listOf("大促销下单拆福袋，亿万新年红包随便拿","家电五折团，抢十亿无门槛现金红包","星球大战剃须刀首发送200元代金券")
       // addFlipLayout(notices)
    }

    private fun addFlipTextView(notices: List<String>) {
        flipView.setOnNoticeClickListener(this)
        flipView.addNoticeTextView(notices)
        flipView.startFlipping()
    }

    private fun addFlipLayout(notices: List<String>) {
        flipView.setOnNoticeClickListener(this)
        flipView.addNoticeLayout(notices)
        flipView.startFlipping()
    }

    @SuppressLint("WrongConstant")
    override fun onNotieClick(position: Int, notice: String?) {
        Toast.makeText(this,notice,3000).show()
    }
}
