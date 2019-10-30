package com.sjq.githubapp.models;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.db.greendao.PopularEntityDao;
import com.sjq.githubapp.javabean.PopularEntity;
import com.sjq.githubapp.javabean.PopularResponse;
import com.sjq.githubapp.javabean.WanAndroidResponse;
import com.sjq.githubapp.network.NetWorkManager;

import java.util.ArrayList;

import io.reactivex.Observable;


public class TrendingListModelImpl implements LanguageContentModel {

    @Override
    public Observable<PopularResponse> getPopularList(String name, String sort) {
        Observable<PopularResponse> observable = NetWorkManager.getRequest().getLanguageContext(name,sort);
      return   observable;

    }

    @Override
    public Observable<WanAndroidResponse> testWandroid(String banner, String json) {
        return NetWorkManager.getRequest().testWandroid(banner,json);
    }

    @Override
    public void addFavoritePopularData(PopularEntity favoriteEntity) {
        MyApplication.getmDaoSession().getPopularEntityDao().insertOrReplace(favoriteEntity);
    }

    @Override
    public void removeFavoritePopularData(PopularEntity favoriteEntity) {
       // MyApplication.getmDaoSession().getPopularFavoriteEntityDao().hasKey(favoriteEntity);
        PopularEntity entity = MyApplication.getmDaoSession().getPopularEntityDao().queryBuilder().where(PopularEntityDao.Properties.PopularId.eq(favoriteEntity.getPopularId())).unique();

        MyApplication.getmDaoSession().getPopularEntityDao().delete(entity);
       // MyApplication.getmDaoSession().getPopularFavoriteEntityDao().insertOrReplace(favoriteEntity);

    }

    @Override
    public ArrayList<PopularEntity> getFavoritePopular() {
        return  (ArrayList<PopularEntity>)MyApplication.getmDaoSession().getPopularEntityDao().loadAll();
    }


}
