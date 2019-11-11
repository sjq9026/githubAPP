package com.sjq.githubapp.presenters;

import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.PopularEntity;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.TrendingEntity;
import com.sjq.githubapp.javabean.TrendingItemEntity;
import com.sjq.githubapp.javabean.UserContactPopularEntity;
import com.sjq.githubapp.javabean.UserContactTrendingEntity;
import com.sjq.githubapp.models.LanguageContentModelImpl;
import com.sjq.githubapp.models.TrendingContentModelImpl;
import com.sjq.githubapp.util.UtilsSharePre;
import com.sjq.githubapp.views.PopularDetailView;


public class PopularDetailPresenter implements BasePresenter {
    private LanguageContentModelImpl model;
    private TrendingContentModelImpl mTrendingModel;
    private PopularDetailView mView;
    private String userName;
    public PopularDetailPresenter(PopularDetailView popularView) {
        mView = popularView;
        model = new LanguageContentModelImpl();
        mTrendingModel = new TrendingContentModelImpl();
        this.userName = UtilsSharePre.getInstance().getPreferenceString(mView.getContext(), UtilsSharePre.USER_NAME, "");

    }
    public void onFavoriteClick(int position, PopularItemEntity popularItemEntity) {
        PopularEntity favoriteEntity = new PopularEntity();
        favoriteEntity.setAvatar_url(popularItemEntity.getOwner().getAvatar_url());
        favoriteEntity.setDescription(popularItemEntity.getDescription());
        favoriteEntity.setPopularId(popularItemEntity.getId());
        favoriteEntity.setStargazers_count(popularItemEntity.getStargazers_count());
        favoriteEntity.setFull_name(popularItemEntity.getFull_name());

        UserContactPopularEntity popularEntity = new UserContactPopularEntity();
        popularEntity.setUser_name(userName);
        popularEntity.setPopular_id(popularItemEntity.getId());
        if(!popularItemEntity.isFavorite()){
            model.addFavoritePopularData(popularEntity);
            mView.onItemFavoriteStatusChange(position,true);
        }else{
            model.removeFavoritePopularData(popularEntity);
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

        UserContactTrendingEntity userContactTrendingEntity = new UserContactTrendingEntity();
        userContactTrendingEntity.setUser_name(userName);
        userContactTrendingEntity.setTrending_repo(trendingItemEntity.getRepo());

        if(!trendingItemEntity.isFavorite()){
            mTrendingModel.addFavoriteTrendingData(userContactTrendingEntity);
            mView.onItemFavoriteStatusChange(position,true);
        }else{
            mTrendingModel.removeFavoriteTrendingData(userContactTrendingEntity);
            mView.onItemFavoriteStatusChange(position,false);
        }
    }


    @Override
    public void onDestroy() {

    }
}
