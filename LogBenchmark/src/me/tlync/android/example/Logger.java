package me.tlync.android.example;

import android.util.Log;

public class Logger {

    public static int d(String tag, String format, Object args) {
        return Log.d(tag, String.format(format, args));
    }

}
