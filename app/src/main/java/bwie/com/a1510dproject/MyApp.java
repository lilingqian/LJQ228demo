package bwie.com.a1510dproject;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by peng on 2017/12/1.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this.getApplicationContext());
    }
}
