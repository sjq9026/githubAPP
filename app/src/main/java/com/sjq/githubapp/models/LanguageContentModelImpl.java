package com.sjq.githubapp.models;

import android.util.Log;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.db.greendao.PopularFavoriteEntityDao;
import com.sjq.githubapp.javabean.Demo2Entity;
import com.sjq.githubapp.javabean.PopularFavoriteEntity;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.PopularResponse;
import com.sjq.githubapp.javabean.WanAndroidResponse;
import com.sjq.githubapp.network.NetWorkManager;
import com.sjq.githubapp.network.Response;


import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;


public class LanguageContentModelImpl implements LanguageContentModel {

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
    public void addFavoritePopularData(PopularFavoriteEntity favoriteEntity) {
        MyApplication.getmDaoSession().getPopularFavoriteEntityDao().insertOrReplace(favoriteEntity);
    }

    @Override
    public void removeFavoritePopularData(PopularFavoriteEntity favoriteEntity) {
       // MyApplication.getmDaoSession().getPopularFavoriteEntityDao().hasKey(favoriteEntity);
        PopularFavoriteEntity entity = MyApplication.getmDaoSession().getPopularFavoriteEntityDao().queryBuilder().where(PopularFavoriteEntityDao.Properties.PopularId.eq(favoriteEntity.getPopularId())).unique();

        MyApplication.getmDaoSession().getPopularFavoriteEntityDao().delete(entity);
       // MyApplication.getmDaoSession().getPopularFavoriteEntityDao().insertOrReplace(favoriteEntity);

    }

    @Override
    public ArrayList<PopularFavoriteEntity> getFavoritePopular() {
        return  (ArrayList<PopularFavoriteEntity>)MyApplication.getmDaoSession().getPopularFavoriteEntityDao().loadAll();
    }


}
