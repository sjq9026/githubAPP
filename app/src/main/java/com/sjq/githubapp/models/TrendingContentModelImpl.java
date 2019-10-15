package com.sjq.githubapp.models;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.db.greendao.PopularFavoriteEntityDao;
import com.sjq.githubapp.db.greendao.TrendingFavoriteEntityDao;
import com.sjq.githubapp.javabean.PopularFavoriteEntity;
import com.sjq.githubapp.javabean.TrendingFavoriteEntity;
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
    public void addFavoriteTrendingData(TrendingFavoriteEntity favoriteEntity) {
        MyApplication.getmDaoSession().getTrendingFavoriteEntityDao().insertOrReplace(favoriteEntity);
    }

    @Override
    public void removeFavoriteTrendingData(TrendingFavoriteEntity favoriteEntity) {
        TrendingFavoriteEntity entity = MyApplication.getmDaoSession().getTrendingFavoriteEntityDao().queryBuilder().where(TrendingFavoriteEntityDao.Properties.Repo.eq(favoriteEntity.getRepo())).unique();
        MyApplication.getmDaoSession().getTrendingFavoriteEntityDao().delete(entity);

    }

    @Override
    public ArrayList<TrendingFavoriteEntity> getFavoriteTrending() {
        return  (ArrayList<TrendingFavoriteEntity>)MyApplication.getmDaoSession().getTrendingFavoriteEntityDao().loadAll();
    }
}
