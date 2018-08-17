package com.example.a7invensun.verifydemo.logdemo.util;

import android.util.Log;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class LogUtils {
    /**
     * true :打开所有log  false：关闭所有的日志
     */
    public static boolean OPEN_LOG = true;

    /**
     * true :打开debug日志，false ：关闭debug日志
     */
    public static boolean DEBUG = true;
    /**
     * TAG 名称
     */
    private static String tag = "app_log";
    private String mClassName;
    private static LogUtils log;
    private static final String USER_NAME = "@tool@";

    private LogUtils(String name) {
        mClassName = name;
    }

    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();

        if (sts == null) {
            return null;
        }

        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }
            String fullClassName = st.getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            return mClassName + "[ " + Thread.currentThread().getName() + ": "
                    + st.getFileName() + ":" + st.getLineNumber() + " "
                    + st.getMethodName() + " ]";
//            return mClassName + "[ " + Thread.currentThread().getName() + ": "
//                    + className + ":" + st.getLineNumber() + " "
//                    + st.getMethodName() + " ]";
        }
        return null;
    }

    public static void i(Object str) {
        print(Log.INFO, str);
    }

    public static void d(Object str) {
        print(Log.DEBUG, str);
    }

    public static void v(Object str) {
        print(Log.VERBOSE, str);
    }

    public static void w(Object str) {
        print(Log.WARN, str);
    }

    public static void e(Object str) {
        print(Log.ERROR, str);
    }
    private static void print(int index, Object str) {
        if (!OPEN_LOG) {
            return;
        }
        if (log == null) {
            log = new LogUtils(USER_NAME);
        }
        String name = log.getFunctionName();
        if (name != null) {
            str = name + " - " + str;
        }

        // Close the debug log When DEBUG is false
        if (!DEBUG) {
            if (index <= Log.DEBUG) {
                return;
            }
        }
        switch (index) {
            case Log.VERBOSE:
                Log.v(tag, str.toString());
                break;
            case Log.DEBUG:
                Log.d(tag, str.toString());
                break;
            case Log.INFO:
                Log.i(tag, str.toString());
                break;
            case Log.WARN:
                Log.w(tag, str.toString());
                break;
            case Log.ERROR:
                Log.e(tag, str.toString());
                break;
            default:
                break;
        }
    }
}
