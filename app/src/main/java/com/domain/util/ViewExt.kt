package com.domain.util

import android.view.View

/**（View）中获取屏幕尺寸*/
fun View.getScreenSize() {
    val width = resources.displayMetrics.widthPixels
}