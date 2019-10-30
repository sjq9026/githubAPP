package com.sjq.githubapp.models;

import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.javabean.PopularEntity;
import com.sjq.githubapp.javabean.PopularResponse;
import com.sjq.githubapp.javabean.WanAndroidResponse;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface LanguageContentModel extends BaseModel {
    Observable<PopularResponse> getPopularList(String name, String sort);

    Observable<WanAndroidResponse> testWandroid(String banner,String json);

    void addFavoritePopularData(PopularEntity favoriteEntity);
    void removeFavoritePopularData(PopularEntity favoriteEntity);
    ArrayList<PopularEntity> getFavoritePopular ();
}
