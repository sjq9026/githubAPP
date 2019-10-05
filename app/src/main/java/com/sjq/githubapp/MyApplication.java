package com.sjq.githubapp;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.sjq.githubapp.db.DatabaseContext;
import com.sjq.githubapp.db.greendao.DaoMaster;
import com.sjq.githubapp.db.greendao.DaoSession;

public class MyApplication extends Application {

    public static final String DB_NAME = "app.db";

    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(new DatabaseContext(this, getFilesDir().getAbsolutePath()), "github.db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getmDaoSession() {
        return mDaoSession;
    }
}
