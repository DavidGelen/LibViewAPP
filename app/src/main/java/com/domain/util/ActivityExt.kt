package com.domain.util

import android.app.Activity
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.view.Window
import android.view.Window.ID_ANDROID_CONTENT
import android.widget.FrameLayout


/**Activity中获取屏幕尺寸*/
fun Activity.getScreenSize() {
    val mDisPlay = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(mDisPlay)
    val screenHeight = mDisPlay.heightPixels
    val screenWidth = mDisPlay.widthPixels
}

/** App显示区域（屏幕尺寸高度-状态栏高度）*/
fun Activity.getAppVisibleRect() {
    val mRect = Rect()
    window.decorView.getWindowVisibleDisplayFrame(mRect)
}

/***（App显示区域 - 标题栏高度）*/
fun Activity.getContentViewHeight() {
    //View布局区域宽高等尺寸获取
    val rect = Rect()
    window.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT).getDrawingRect(rect)
}