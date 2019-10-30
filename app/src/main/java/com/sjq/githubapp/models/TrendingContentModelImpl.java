package com.sjq.githubapp.models;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.db.greendao.TrendingEntityDao;
import com.sjq.githubapp.javabean.TrendingEntity;
import com.sjq.githubapp.javabean.TrendingResponse;
import com.sjq.githubapp.network.NetWorkManager;

import java.util.ArrayList;

import io.reactivex.Observable;

public class TrendingContentModelImpl implements TrendingContentModel {


    @Override
    public Observable<TrendingResponse> getTrendingList(String lang, String since) {
        return NetWorkManager.getRequest().getTrendingContent(lang,since);
    }

    @Override
    public void addFavoriteTrendingData(TrendingEntity favoriteEntity) {
        MyApplication.getmDaoSession().getTrendingEntityDao().insertOrReplace(favoriteEntity);
    }

    @Override
    public void removeFavoriteTrendingData(TrendingEntity favoriteEntity) {
        TrendingEntity entity = MyApplication.getmDaoSession().getTrendingEntityDao().queryBuilder().where(TrendingEntityDao.Properties.Repo.eq(favoriteEntity.getRepo())).unique();
        MyApplication.getmDaoSession().getTrendingEntityDao().delete(entity);

    }

    @Override
    public ArrayList<TrendingEntity> getFavoriteTrending() {
        return  (ArrayList<TrendingEntity>)MyApplication.getmDaoSession().getTrendingEntityDao().loadAll();
    }
}
