package com.sjq.githubapp.models;

import android.database.Cursor;
import android.util.Log;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.db.greendao.UserContactTrendingEntityDao;
import com.sjq.githubapp.javabean.TrendingEntity;
import com.sjq.githubapp.javabean.TrendingResponse;
import com.sjq.githubapp.javabean.UserContactTrendingEntity;
import com.sjq.githubapp.network.NetWorkManager;

import java.util.ArrayList;

import io.reactivex.Observable;

public class TrendingContentModelImpl implements TrendingContentModel {


    @Override
    public Observable<TrendingResponse> getTrendingList(String lang, String since) {
        return NetWorkManager.getRequest().getTrendingContent(lang, since);
    }

    @Override
    public void addFavoriteTrendingData(UserContactTrendingEntity favoriteEntity) {
        MyApplication.getmDaoSession().getUserContactTrendingEntityDao().insertOrReplace(favoriteEntity);
    }

    @Override
    public void removeFavoriteTrendingData(UserContactTrendingEntity favoriteEntity) {
        UserContactTrendingEntity entity = MyApplication.getmDaoSession().getUserContactTrendingEntityDao().queryBuilder().
                where(UserContactTrendingEntityDao.Properties.Trending_repo.eq(favoriteEntity.getTrending_repo()), UserContactTrendingEntityDao.Properties.User_name.eq(favoriteEntity.getUser_name())).unique();
        if (entity != null) {
            MyApplication.getmDaoSession().getUserContactTrendingEntityDao().delete(entity);
        }
    }

    @Override
    public ArrayList<TrendingEntity> getFavoriteTrending(String userName) {
        ArrayList<TrendingEntity> arrayList = new ArrayList<>();
        String sql = "SELECT\n" +
                "\tTRENDING_ENTITY.*\n" +
                " FROM\n" +
                " \tTRENDING_ENTITY\n" +
                " INNER JOIN USER_CONTACT_TRENDING_ENTITY ON USER_CONTACT_TRENDING_ENTITY.USER_NAME  == \'" + userName + "\'\n" +
                " AND USER_CONTACT_TRENDING_ENTITY.TRENDING_REPO == TRENDING_ENTITY.REPO";
        Log.i("SQL", sql);
        Cursor cursor = MyApplication.getmDaoSession().getDatabase().rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do
            {
                TrendingEntity entity = new TrendingEntity();
                entity.setAdded_stars(cursor.getString(cursor.getColumnIndex("STARS")));
                entity.setAvatarImg(cursor.getString(cursor.getColumnIndex("AVATAR_IMG")));
                entity.setDesc(cursor.getString(cursor.getColumnIndex("DESC")));
                entity.setForks(cursor.getString(cursor.getColumnIndex("FORKS")));
                entity.setLang(cursor.getString(cursor.getColumnIndex("LANG")));
                entity.setRepo(cursor.getString(cursor.getColumnIndex("REPO")));
                entity.setRepo_link(cursor.getString(cursor.getColumnIndex("REPO_LINK")));
                entity.setStars(cursor.getString(cursor.getColumnIndex("STARS")));
                arrayList.add(entity);
            } while (cursor.moveToNext());
        }
        return arrayList;

    }
}
