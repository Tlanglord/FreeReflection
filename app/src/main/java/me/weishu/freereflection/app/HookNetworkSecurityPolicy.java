package me.weishu.freereflection.app;

import android.annotation.SuppressLint;
import android.security.NetworkSecurityPolicy;
import android.util.Log;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dongqiangqiang on 2024/10/18
 */
public class HookNetworkSecurityPolicy {

    private static final String TAG = "HookNetworkSecurity";

    public static void setCleartextTrafficPermittedTrue() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            try {
                NetworkSecurityPolicy instance = NetworkSecurityPolicy.getInstance();
                Class<?> nspClazz = Class.forName("android.security.NetworkSecurityPolicy");
                @SuppressLint("SoonBlockedPrivateApi") Method method = nspClazz.getDeclaredMethod("setCleartextTrafficPermitted", boolean.class);
                method.setAccessible(true);
                method.invoke(instance, true);
                Log.d(TAG, "setCleartextTrafficPermittedTure: success!!");
            } catch (Exception e) {
                e.printStackTrace();
            }

            testHttp();
        }

    }


    public static void testHttp() {

        Log.d(TAG, "testHttp: start");
        // 测试http
        new Thread(() -> {
            try {
                URL url = new URL("http://www.baidu.com");
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();

                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    Log.d(TAG, new String(buffer, 0, len));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

}
