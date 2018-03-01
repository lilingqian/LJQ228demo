package bwie.com.a1510dproject.uitls;

import android.util.Log;

/**
 * Created by peng on 2017/12/4.
 */

public class MyLogger {
    private static boolean isOnline = true;

    public static void Log_e(String key, String msg) {
        if (!isOnline) {
            Log.e(key, msg);
        }
    }
}
