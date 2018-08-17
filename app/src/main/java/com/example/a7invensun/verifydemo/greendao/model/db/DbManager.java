package com.example.a7invensun.verifydemo.greendao.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.a7invensun.verifydemo.greendao.model.gen.DaoMaster;
import com.example.a7invensun.verifydemo.greendao.model.gen.DaoSession;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class DbManager {

    private static final String DB_NAME = "greendao.db";
    private static DaoMaster.DevOpenHelper mDevOpenHelper;
    private static DbManager mDbManager;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private static Context mContext;
    public DbManager(Context context) {
        this.mContext = context;
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        getDaoMaster(context);
    }

    public static DbManager getInstance(Context context){
        if (mDbManager == null){
            synchronized (DbManager.class){
                if (mDbManager==null){
                    mDbManager = new DbManager(mContext);
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
        if (mDaoSession ==null){
            synchronized (DbManager.class){
                mDaoSession = getDaoMaster(context).newSession();
            }
        }
        return mDaoSession;
    }

}
