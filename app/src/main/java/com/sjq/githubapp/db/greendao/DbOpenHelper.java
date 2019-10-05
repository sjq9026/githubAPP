package com.sjq.githubapp.db.greendao;

import android.content.Context;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;

public class DbOpenHelper extends DaoMaster.DevOpenHelper {
    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //切记不要调用super.onUpgrade(db,oldVersion,newVersion)
        if (oldVersion < newVersion) {
            MigrationHelper.migrate((StandardDatabase) db,
                    DemoEntityDao.class //具体操作数据库的dao类, 如果需要多个,用逗号隔开
            );
        }
    }
}
