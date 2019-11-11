package com.sjq.githubapp.models;

import com.sjq.githubapp.javabean.PopularEntity;
import com.sjq.githubapp.javabean.PopularResponse;
import com.sjq.githubapp.javabean.UserContactPopularEntity;
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
    public void addFavoritePopularData(UserContactPopularEntity favoriteEntity) {

    }

    @Override
    public void removeFavoritePopularData(UserContactPopularEntity favoriteEntity) {

    }

    @Override
    public ArrayList<PopularEntity> getFavoritePopular(String userName) {
        return null;
    }


}
