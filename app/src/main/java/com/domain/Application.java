package com.domain;

import android.content.Context;

/**
 * @name LibViewAPP
 * @class name：com.domain
 * @class describe
 * @anthor David
 * @time 2018/10/11 23:02
 * @class describe
 */
public class Application extends android.app.Application {

    public static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
    }


}
