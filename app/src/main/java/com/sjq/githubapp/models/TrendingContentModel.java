package com.sjq.githubapp.models;

import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.javabean.TrendingEntity;
import com.sjq.githubapp.javabean.TrendingResponse;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface TrendingContentModel extends BaseModel {
    Observable<TrendingResponse> getTrendingList(String lang, String since);

    void addFavoriteTrendingData(TrendingEntity favoriteEntity);

    void removeFavoriteTrendingData(TrendingEntity favoriteEntity);

    ArrayList<TrendingEntity> getFavoriteTrending();
}
