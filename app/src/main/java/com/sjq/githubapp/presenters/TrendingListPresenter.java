package com.sjq.githubapp.presenters;

import android.util.Log;

import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.PopularFavoriteEntity;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.PopularResponse;
import com.sjq.githubapp.javabean.TrendingFavoriteEntity;
import com.sjq.githubapp.javabean.TrendingItemEntity;
import com.sjq.githubapp.javabean.TrendingResponse;
import com.sjq.githubapp.models.LanguageContentModelImpl;
import com.sjq.githubapp.models.TrendingContentModelImpl;
import com.sjq.githubapp.views.LanguageContentView;
import com.sjq.githubapp.views.PopularView;
import com.sjq.githubapp.views.TrendingContentView;
import com.sjq.githubapp.views.TrendingView;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TrendingListPresenter implements BasePresenter {


    private TrendingContentModelImpl model;
    private TrendingContentView mView;
    public TrendingListPresenter(TrendingContentView view) {
        model = new TrendingContentModelImpl();
        mView = view;
    }




    public  void  getTrendingItemList(String lang,String since){
        model.getTrendingList(lang,since)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TrendingResponse>() {
                    @Override
                    public void accept(TrendingResponse listResponse)  {
                        Log.i("AAAAAA",listResponse.getTrendingItemEntities().toString());
                        ArrayList<TrendingFavoriteEntity> list = model.getFavoriteTrending();
                        for (TrendingItemEntity itemEntity : listResponse.getTrendingItemEntities()) {
                            for (TrendingFavoriteEntity favoriteEntity : list) {
                                if(itemEntity.getRepo().equals(favoriteEntity.getRepo())){
                                    itemEntity.setFavorite(true);
                                }
                            }
                        }
                        mView.updateRecycleViewData(listResponse.getTrendingItemEntities());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("AAAAAA","getPopularItemList()--->"+throwable.getMessage());

                    }});

    }
    public  void  getFavoriteTrendingItemList(){
        ArrayList<TrendingFavoriteEntity> list = model.getFavoriteTrending();
        ArrayList<TrendingItemEntity> result_list = new ArrayList<>();
        for (TrendingFavoriteEntity trendingFavoriteEntity : list) {
            TrendingItemEntity itemEntity = new TrendingItemEntity();

            itemEntity.setDesc(trendingFavoriteEntity.getDesc());
            ArrayList<String> avvtars = new ArrayList<>();
            avvtars.add(trendingFavoriteEntity.getAvatarImg());
            itemEntity.setAvatars(avvtars);
            itemEntity.setAdded_stars(trendingFavoriteEntity.getAdded_stars());
            itemEntity.setFavorite(true);
            itemEntity.setRepo_link(trendingFavoriteEntity.getRepo_link());
            itemEntity.setRepo(trendingFavoriteEntity.getRepo());
            itemEntity.setStars(trendingFavoriteEntity.getStars());
            result_list.add(itemEntity);
        }
        mView.updateRecycleViewData(result_list);

    }




    public void onFavoriteClick(int position, TrendingItemEntity trendingItemEntity) {
        TrendingFavoriteEntity favoriteEntity = new TrendingFavoriteEntity();
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
            model.addFavoriteTrendingData(favoriteEntity);
            mView.onItemFavoriteStatusChange(position,true);
        }else{
            model.removeFavoriteTrendingData(favoriteEntity);
            mView.onItemFavoriteStatusChange(position,false);
        }
    }





    @Override
    public void onDestroy() {
        mView = null;
    }

}
