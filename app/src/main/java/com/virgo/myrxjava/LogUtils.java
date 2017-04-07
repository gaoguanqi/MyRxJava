package com.virgo.myrxjava;

import android.util.Log;

/**
 * Created by Administrator on 2016/11/3.
 */
public class LogUtils {

    private static final boolean isDeBug = true;


    public static void e(String tag, String msg) {
        if (isDeBug) {
            Log.e(tag, msg);
        }
    }


}
