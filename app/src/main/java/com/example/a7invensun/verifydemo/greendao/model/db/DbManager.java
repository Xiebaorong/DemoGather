package com.example.a7invensun.verifydemo.greendao.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.a7invensun.verifydemo.greendao.model.gen.DaoMaster;
import com.example.a7invensun.verifydemo.greendao.model.gen.DaoSession;
import com.example.a7invensun.verifydemo.logdemo.util.LogUtils;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class DbManager {
    // 是否加密
    public static final boolean ENCRYPTED = true;
    private static final String DB_NAME = "greendao.db";
    private static DaoMaster.DevOpenHelper mDevOpenHelper;
    private static DbManager mDbManager;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    public DbManager(Context context) {
        LogUtils.e("DbManager");
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        getDaoMaster(context);
        getDaoSession(context);
    }


    public static DbManager getInstance(Context context) {
        if (null == mDbManager) {
            synchronized (DbManager.class) {
                if (null ==mDbManager){
                    mDbManager = new DbManager(context);
                }
            }
        }
        return mDbManager;
    }


    /**
     * 获取可读数据库
     * @param context
     * @return
     */
    public static SQLiteDatabase getReadableDatabase(Context context) {
        if (mDevOpenHelper == null){
            getInstance(context);
        }
        return mDevOpenHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     * @param context
     * @return
     */
    public static SQLiteDatabase getWritableDatabase(Context context) {
        if (mDevOpenHelper==null){
            getInstance(context);
        }
        return mDevOpenHelper.getWritableDatabase();
    }


    public static DaoMaster getDaoMaster(Context context) {
        LogUtils.e("DaoMaster");
        if (mDaoMaster == null){
            synchronized (DbManager.class){
                if (mDaoMaster==null){
                    mDaoMaster = new DaoMaster(getWritableDatabase(context));
                }
            }
        }
        return mDaoMaster;
    }

    public static DaoSession getDaoSession(Context context){
        LogUtils.e("DaoSession");
        if (mDaoSession ==null){
            synchronized (DbManager.class){
                mDaoSession = getDaoMaster(context).newSession();
            }
        }
        return mDaoSession;
    }

}
