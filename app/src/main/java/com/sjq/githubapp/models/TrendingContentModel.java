package com.sjq.githubapp.models;

import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.javabean.PopularFavoriteEntity;
import com.sjq.githubapp.javabean.PopularResponse;
import com.sjq.githubapp.javabean.TrendingFavoriteEntity;
import com.sjq.githubapp.javabean.TrendingResponse;
import com.sjq.githubapp.javabean.WanAndroidResponse;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface TrendingContentModel extends BaseModel {
    Observable<TrendingResponse> getTrendingList(String lang, String since);

    void addFavoriteTrendingData(TrendingFavoriteEntity favoriteEntity);

    void removeFavoriteTrendingData(TrendingFavoriteEntity favoriteEntity);

    ArrayList<TrendingFavoriteEntity> getFavoriteTrending();
}
