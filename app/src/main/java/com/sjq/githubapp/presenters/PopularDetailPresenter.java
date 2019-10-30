package com.sjq.githubapp.presenters;

import com.sjq.githubapp.base.BasePresenter;

import com.sjq.githubapp.javabean.PopularEntity;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.TrendingEntity;
import com.sjq.githubapp.javabean.TrendingItemEntity;
import com.sjq.githubapp.models.LanguageContentModelImpl;
import com.sjq.githubapp.models.TrendingContentModelImpl;
import com.sjq.githubapp.views.PopularDetailView;


public class PopularDetailPresenter implements BasePresenter {
    private LanguageContentModelImpl model;
    private TrendingContentModelImpl mTrendingModel;
    private PopularDetailView mView;
    public PopularDetailPresenter(PopularDetailView popularView) {
        mView = popularView;
        model = new LanguageContentModelImpl();
        mTrendingModel = new TrendingContentModelImpl();
    }
    public void onFavoriteClick(int position, PopularItemEntity popularItemEntity) {
        PopularEntity favoriteEntity = new PopularEntity();
        favoriteEntity.setAvatar_url(popularItemEntity.getOwner().getAvatar_url());
        favoriteEntity.setDescription(popularItemEntity.getDescription());
        favoriteEntity.setPopularId(popularItemEntity.getId());
        favoriteEntity.setStargazers_count(popularItemEntity.getStargazers_count());
        favoriteEntity.setFull_name(popularItemEntity.getFull_name());
        if(!popularItemEntity.isFavorite()){
            model.addFavoritePopularData(favoriteEntity);
            mView.onItemFavoriteStatusChange(position,true);
        }else{
            model.removeFavoritePopularData(favoriteEntity);
            mView.onItemFavoriteStatusChange(position,false);
        }
    }

    public void onFavoriteClick(int position, TrendingItemEntity trendingItemEntity) {
        TrendingEntity favoriteEntity = new TrendingEntity();
        favoriteEntity.setAdded_stars(trendingItemEntity.getAdded_stars());
        if(trendingItemEntity.getAvatars()!=null && trendingItemEntity.getAvatars().size() > 0){
            favoriteEntity.setAvatarImg(trendingItemEntity.getAvatars().get(0));
        }else{
            favoriteEntity.setAvatarImg("error");
        }
        favoriteEntity.setDesc(trendingItemEntity.getDesc());
        favoriteEntity.setRepo(trendingItemEntity.getRepo());
        favoriteEntity.setRepo_link(trendingItemEntity.getRepo_link());
        favoriteEntity.setStars(trendingItemEntity.getStars());

        if(!trendingItemEntity.isFavorite()){
            mTrendingModel.addFavoriteTrendingData(favoriteEntity);
            mView.onItemFavoriteStatusChange(position,true);
        }else{
            mTrendingModel.removeFavoriteTrendingData(favoriteEntity);
            mView.onItemFavoriteStatusChange(position,false);
        }
    }


    @Override
    public void onDestroy() {

    }
}
