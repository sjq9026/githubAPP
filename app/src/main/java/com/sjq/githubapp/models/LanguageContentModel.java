package com.sjq.githubapp.models;

import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.javabean.PopularEntity;
import com.sjq.githubapp.javabean.PopularResponse;
import com.sjq.githubapp.javabean.UserContactPopularEntity;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface LanguageContentModel extends BaseModel {
    Observable<PopularResponse> getPopularList(String name, String sort);



    void addFavoritePopularData(UserContactPopularEntity favoriteEntity);
    void removeFavoritePopularData(UserContactPopularEntity favoriteEntity);
    ArrayList<PopularEntity> getFavoritePopular (String userName);
}
