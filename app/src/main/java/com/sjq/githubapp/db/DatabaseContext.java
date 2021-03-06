package com.sjq.githubapp.db;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class DatabaseContext extends ContextWrapper {
    public static String dbPath = "";
    public DatabaseContext(Context base, String dbPath) {
        super(base);
        if(!TextUtils.isEmpty(dbPath)){
            this.dbPath = dbPath;
        }
    }
    @Override
    public File getDatabasePath(String name){
        File dbDir = new File(this.dbPath);
        if(!dbDir.exists()){
            dbDir.mkdir();
        }
        File dbFile = new File(this.dbPath, name);
        //File dbFile = new File(this.dbPath+"/"+name, name);
        if(!dbFile.exists()){
            try {
                dbFile.createNewFile();
            } catch (IOException e) {
                Log.e("TAG",e.getMessage().toString());
                e.printStackTrace();
            }
        }
        return dbFile;


//        boolean sdExist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
//        if(!sdExist){//如果不存在,
//            Log.e("SD卡管理：", "SD卡不存在，请加载SD卡");
//            return null;
//        }
//        else{//如果存在
//            //获取sd卡路径
//            String dbDir= Environment.getExternalStorageDirectory().toString();
//            dbDir += "/databases";//数据库所在目录
//            String dbPath = dbDir+"/"+name;//数据库路径
//            //判断目录是否存在，不存在则创建该目录
//            File dirFile = new File(dbDir);
//            if(!dirFile.exists())
//                dirFile.mkdirs();
//            //数据库文件是否创建成功
//            boolean isFileCreateSuccess = false;
//            //判断文件是否存在，不存在则创建该文件
//            File dbFile = new File(dbPath);
//            if(!dbFile.exists()){
//                try {
//                    isFileCreateSuccess = dbFile.createNewFile();//创建文件
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            else
//                isFileCreateSuccess = true;
//            //返回数据库文件对象
//            if(isFileCreateSuccess)
//                return dbFile;
//            else
//                return null;
//        }


    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }
}
