package me.weishu.freereflection.app;

import android.app.Application;
import android.util.Log;

import me.weishu.reflection.Reflection;

/**
 * Created by dongqiangqiang on 2024/10/21
 */
public class App extends Application {

    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        int ret = Reflection.unseal(this);
        Log.i(TAG, "unseal result: " + ret);
    }

}
