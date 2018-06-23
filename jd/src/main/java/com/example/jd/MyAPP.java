package com.example.jd;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MyAPP extends Application {
    private static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context = getApplicationContext();
    }
    public static Context getAppContext() {
        return context;
    }

}
