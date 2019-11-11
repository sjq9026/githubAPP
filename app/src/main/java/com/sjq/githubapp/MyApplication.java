package com.sjq.githubapp;

import android.app.Application;

import com.sjq.githubapp.db.DatabaseContext;
import com.sjq.githubapp.db.greendao.DaoMaster;
import com.sjq.githubapp.db.greendao.DaoSession;
import com.sjq.githubapp.network.NetWorkManager;
import com.sjq.githubapp.util.UtilsFile;

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
        //DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.this, DB_NAME, null);

        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());

        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getmDaoSession() {
        return mDaoSession;
    }



   
}
