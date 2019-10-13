package com.sjq.githubapp.models;

import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.javabean.LanguageEntity;
import com.sjq.githubapp.javabean.PopularFavoriteEntity;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.PopularResponse;
import com.sjq.githubapp.javabean.WanAndroidResponse;
import com.sjq.githubapp.network.NetWorkManager;
import com.sjq.githubapp.network.Response;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public interface LanguageContentModel extends BaseModel {
    Observable<PopularResponse> getPopularList(String name, String sort);

    Observable<WanAndroidResponse> testWandroid(String banner,String json);

    void addFavoritePopularData(PopularFavoriteEntity favoriteEntity);
    void removeFavoritePopularData(PopularFavoriteEntity favoriteEntity);
    ArrayList<PopularFavoriteEntity> getFavoritePopular ();
}
