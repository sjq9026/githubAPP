package com.sjq.githubapp.models;

import android.database.Cursor;
import android.util.Log;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.db.greendao.UserContactPopularEntityDao;
import com.sjq.githubapp.javabean.PopularEntity;
import com.sjq.githubapp.javabean.PopularResponse;
import com.sjq.githubapp.javabean.UserContactPopularEntity;
import com.sjq.githubapp.network.NetWorkManager;

import java.util.ArrayList;

import io.reactivex.Observable;


public class LanguageContentModelImpl implements LanguageContentModel {

    @Override
    public Observable<PopularResponse> getPopularList(String name, String sort) {
        Observable<PopularResponse> observable = NetWorkManager.getRequest().getLanguageContext(name, sort);
        return observable;

    }


    @Override
    public void addFavoritePopularData(UserContactPopularEntity favoriteEntity) {
//     UserContactPopularEntity entity =   MyApplication.getmDaoSession().getUserContactPopularEntityDao().queryBuilder().
//                where(UserContactPopularEntityDao.Properties.Popular_id.eq(favoriteEntity.getPopular_id()),UserContactPopularEntityDao.Properties.User_name.eq(favoriteEntity.getUser_name())).unique();
        MyApplication.getmDaoSession().getUserContactPopularEntityDao().insertOrReplace(favoriteEntity);
    }

    @Override
    public void removeFavoritePopularData(UserContactPopularEntity favoriteEntity) {
        // MyApplication.getmDaoSession().getPopularFavoriteEntityDao().hasKey(favoriteEntity);
        UserContactPopularEntity entity = MyApplication.getmDaoSession().getUserContactPopularEntityDao().queryBuilder().
                where(UserContactPopularEntityDao.Properties.Popular_id.eq(favoriteEntity.getPopular_id()), UserContactPopularEntityDao.Properties.User_name.eq(favoriteEntity.getUser_name())).unique();
        if (entity != null) {
            MyApplication.getmDaoSession().getUserContactPopularEntityDao().delete(entity);
        }
        // MyApplication.getmDaoSession().getPopularFavoriteEntityDao().insertOrReplace(favoriteEntity);

    }

    @Override
    public ArrayList<PopularEntity> getFavoritePopular(String userName) {
        ArrayList<PopularEntity> arrayList = new ArrayList<>();
        String sql = "SELECT\n" +
                "\tPOPULAR_ENTITY.*\n" +
                " FROM\n" +
                "\tPOPULAR_ENTITY\n" +
                " INNER JOIN USER_CONTACT_POPULAR_ENTITY ON USER_CONTACT_POPULAR_ENTITY.USER_NAME == \'" + userName + "\'\n" +
                " AND USER_CONTACT_POPULAR_ENTITY.POPULAR_ID == POPULAR_ENTITY.POPULAR_ID";
        Log.i("SQL",sql);
        Cursor cursor = MyApplication.getmDaoSession().getDatabase().rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do
            {
                PopularEntity entity = new PopularEntity();
                entity.setPopularId(cursor.getInt(cursor.getColumnIndex("POPULAR_ID")));
                entity.setStargazers_count(cursor.getInt(cursor.getColumnIndex("STARGAZERS_COUNT")));
                entity.setHtml_url(cursor.getString(cursor.getColumnIndex("HTML_URL")));
                entity.setFull_name(cursor.getString(cursor.getColumnIndex("FULL_NAME")));
                entity.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
                entity.setAvatar_url(cursor.getString(cursor.getColumnIndex("AVATAR_URL")));
                arrayList.add(entity);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }
}
