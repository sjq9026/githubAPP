package com.sjq.githubapp.models;

import android.database.Cursor;
import android.util.Log;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.db.greendao.UserContactPopularKeyEntityDao;
import com.sjq.githubapp.javabean.PopularKeyEntity;
import com.sjq.githubapp.javabean.UserContactPopularKeyEntity;

import java.util.ArrayList;

public class PopularModelImpl implements PopularModel {

    @Override
    public void saveAllLanguage(ArrayList<UserContactPopularKeyEntity> list,String userName) {
        for (UserContactPopularKeyEntity popularKeyEntity : getUserLanguage(userName)) {
            MyApplication.getmDaoSession().getUserContactPopularKeyEntityDao().delete(popularKeyEntity);
        }
        for (UserContactPopularKeyEntity popularKeyEntity : list) {
            MyApplication.getmDaoSession().getUserContactPopularKeyEntityDao().insertOrReplace(popularKeyEntity);
        }
    }


    @Override
    public void saveSortFinishLanguage(ArrayList<PopularKeyEntity> list, String userName) {
        for (UserContactPopularKeyEntity popularKeyEntity : getUserLanguage(userName)) {
            MyApplication.getmDaoSession().getUserContactPopularKeyEntityDao().delete(popularKeyEntity);
        }


        for (PopularKeyEntity popularKeyEntity : list) {
            UserContactPopularKeyEntity entity = new UserContactPopularKeyEntity();
            entity.setPopular_key_id(popularKeyEntity.getId());
            entity.setUser_name(userName);
            MyApplication.getmDaoSession().getUserContactPopularKeyEntityDao().insert(entity);

        }

    }

    /**
     * 从数据库中查询以选中的的语言
     * @return
     */
    @Override
    public ArrayList<PopularKeyEntity> getLanguage(String userName) {
        ArrayList<PopularKeyEntity> mLanguages = new ArrayList<>();
        String sql = "SELECT\n" +
                "\t*\n" +
                " FROM\n" +
                "\tPOPULAR_KEY_ENTITY\n" +
                " INNER  JOIN USER_CONTACT_POPULAR_KEY_ENTITY ON USER_CONTACT_POPULAR_KEY_ENTITY.POPULAR_KEY_ID = POPULAR_KEY_ENTITY.ID\n" +
                " AND USER_CONTACT_POPULAR_KEY_ENTITY.USER_NAME =" + "\'" + userName + "\'";
        Log.i("SQL", sql);
        Cursor cursor = MyApplication.getmDaoSession().getDatabase().rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do
            {
                PopularKeyEntity entity = new PopularKeyEntity();
                entity.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                entity.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                entity.setPath(cursor.getString(cursor.getColumnIndex("PATH")));
                mLanguages.add(entity);
            } while (cursor.moveToNext());
        }
        return mLanguages;
    }



    /**
     * 从数据库中查询全部
     *
     * @return
     */
    @Override
    public ArrayList<PopularKeyEntity> getAllLanguage() {
        ArrayList<PopularKeyEntity> mLanguages = new ArrayList<>();
        mLanguages = (ArrayList<PopularKeyEntity>) MyApplication.getmDaoSession().getPopularKeyEntityDao().loadAll();
        if (mLanguages == null) {
            mLanguages = new ArrayList<>();
        }
        return mLanguages;
    }

    @Override
    public ArrayList<UserContactPopularKeyEntity> getUserLanguage(String userName) {
        return (ArrayList<UserContactPopularKeyEntity>) MyApplication.getmDaoSession().getUserContactPopularKeyEntityDao().queryBuilder().where(UserContactPopularKeyEntityDao.Properties.User_name.eq(userName)).list();
    }


}
