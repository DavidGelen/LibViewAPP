package com.domain.util;

import android.content.Context;

import com.domain.Application;

/**
 * @name LibViewAPP
 * @class name：com.domain.util
 * @class describe
 * @anthor David
 * @time 2018/10/11 23:01
 * @class describe
 */
public class ScreenUtils {

    private static Context sContext = Application.sAppContext;

    public static int getScreenWidth() {
        return sContext.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return sContext.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dpTopx(int dp) {
        final float scale = sContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int pxTodp(int px) {
        final float scale = sContext.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
