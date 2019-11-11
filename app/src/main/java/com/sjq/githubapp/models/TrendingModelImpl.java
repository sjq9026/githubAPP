package com.sjq.githubapp.models;

import android.database.Cursor;
import android.util.Log;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.db.greendao.UserContactTrendingKeyEntityDao;
import com.sjq.githubapp.javabean.TrendingKeyEntity;
import com.sjq.githubapp.javabean.UserContactTrendingKeyEntity;

import java.util.ArrayList;

public class TrendingModelImpl implements TrendingModel {

    @Override
    public void saveAllLanguage(ArrayList<UserContactTrendingKeyEntity> list, String userName) {
        for (UserContactTrendingKeyEntity popularKeyEntity : getUserLanguage(userName)) {
            MyApplication.getmDaoSession().getUserContactTrendingKeyEntityDao().delete(popularKeyEntity);
        }
        for (UserContactTrendingKeyEntity popularKeyEntity : list) {
            MyApplication.getmDaoSession().getUserContactTrendingKeyEntityDao().insertOrReplace(popularKeyEntity);
        }
    }

    /**
     * 从数据库中查询以选中的的语言
     *
     * @return
     */
    @Override
    public ArrayList<TrendingKeyEntity> getLanguage(String userName) {
        ArrayList<TrendingKeyEntity> mLanguages = new ArrayList<>();
        String sql = "SELECT\n" +
                " \t*\n" +
                "FROM\n" +
                " \tTRENDING_KEY_ENTITY\n" +
                " INNER JOIN USER_CONTACT_TRENDING_KEY_ENTITY ON USER_CONTACT_TRENDING_KEY_ENTITY.TRENDING_KEY_ID = TRENDING_KEY_ENTITY.ID\n" +
                " AND USER_CONTACT_TRENDING_KEY_ENTITY.USER_NAME = " + "\'" + userName + "\'";
        Log.i("SQL", sql);
        Cursor cursor = MyApplication.getmDaoSession().getDatabase().rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do
            {
                TrendingKeyEntity entity = new TrendingKeyEntity();
                entity.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                entity.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                entity.setPath(cursor.getString(cursor.getColumnIndex("PATH")));
                mLanguages.add(entity);
            } while (cursor.moveToNext());
        }
        return mLanguages;
    }

    /**
     * 从数据库中查询以选中的的语言
     *
     * @return
     */
    @Override
    public ArrayList<TrendingKeyEntity> getAllLanguage(String userName) {
        ArrayList<TrendingKeyEntity> mLanguages = new ArrayList<>();
        mLanguages = (ArrayList<TrendingKeyEntity>) MyApplication.getmDaoSession().getTrendingKeyEntityDao().loadAll();
        if (mLanguages == null) {
            mLanguages = new ArrayList<>();
        }
        return mLanguages;
    }


    @Override
    public ArrayList<UserContactTrendingKeyEntity> getUserLanguage(String userName) {
        return (ArrayList<UserContactTrendingKeyEntity>) MyApplication.getmDaoSession().getUserContactTrendingKeyEntityDao().queryBuilder().where(UserContactTrendingKeyEntityDao.Properties.User_name.eq(userName)).list();
    }

    @Override
    public void saveSortFinishLanguage(ArrayList<TrendingKeyEntity> list, String userName) {
        for (UserContactTrendingKeyEntity popularKeyEntity : getUserLanguage(userName)) {
            MyApplication.getmDaoSession().getUserContactTrendingKeyEntityDao().delete(popularKeyEntity);
        }


        for (TrendingKeyEntity popularKeyEntity : list) {
            UserContactTrendingKeyEntity entity = new UserContactTrendingKeyEntity();
            entity.setTrending_key_id(popularKeyEntity.getId());
            entity.setUser_name(userName);
            MyApplication.getmDaoSession().getUserContactTrendingKeyEntityDao().insert(entity);
        }

    }


}
