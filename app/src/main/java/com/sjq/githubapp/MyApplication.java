package com.sjq.githubapp;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.sjq.githubapp.db.DatabaseContext;
import com.sjq.githubapp.db.greendao.DaoMaster;
import com.sjq.githubapp.db.greendao.DaoSession;
import com.sjq.githubapp.network.NetWorkManager;
import com.sjq.githubapp.util.UtilsFile;

import java.io.File;
import java.io.IOException;

public class MyApplication extends Application {

    public static final String DB_NAME = "github.db";

    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkManager.getInstance().init();
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(new DatabaseContext(this, UtilsFile.getSDFilePath()), DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());

        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getmDaoSession() {
        return mDaoSession;
    }



   
}
