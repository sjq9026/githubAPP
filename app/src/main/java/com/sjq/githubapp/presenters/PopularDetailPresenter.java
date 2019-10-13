package com.sjq.githubapp.presenters;

import android.os.Handler;

import com.sjq.githubapp.base.BasePresenter;

import com.sjq.githubapp.javabean.PopularFavoriteEntity;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.models.LanguageContentModelImpl;
import com.sjq.githubapp.views.PopularDetailView;


public class PopularDetailPresenter extends BasePresenter<PopularDetailView> {
    private LanguageContentModelImpl model;
    public PopularDetailPresenter() {
        model = new LanguageContentModelImpl();
    }
    public void onFavoriteClick(int position, PopularItemEntity popularItemEntity) {
        PopularFavoriteEntity favoriteEntity = new PopularFavoriteEntity();
        favoriteEntity.setAvatar_url(popularItemEntity.getOwner().getAvatar_url());
        favoriteEntity.setDescription(popularItemEntity.getDescription());
        favoriteEntity.setPopularId(popularItemEntity.getId());
        favoriteEntity.setStargazers_count(popularItemEntity.getStargazers_count());
        favoriteEntity.setFull_name(popularItemEntity.getFull_name());
        if(!popularItemEntity.isFavorite()){
            model.addFavoritePopularData(favoriteEntity);
            view.onItemFavoriteStatusChange(position,true);
        }else{
            model.removeFavoritePopularData(favoriteEntity);
            view.onItemFavoriteStatusChange(position,false);
        }
    }


}
