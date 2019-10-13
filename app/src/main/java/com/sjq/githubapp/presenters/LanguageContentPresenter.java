package com.sjq.githubapp.presenters;
import android.util.Log;


import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.PopularFavoriteEntity;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.PopularResponse;

import com.sjq.githubapp.models.LanguageContentModelImpl;
import com.sjq.githubapp.views.LanguageContentView;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LanguageContentPresenter extends BasePresenter<LanguageContentView> {
    private LanguageContentModelImpl model;
    public LanguageContentPresenter() {
        model = new LanguageContentModelImpl();
    }


    public  void  getPopularItemList(String languageName){
//        model.getPopularList(languageName,"start")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//
//                .subscribe(new Consumer<PopularResponse>() {
//                    @Override
//                    public void accept(PopularResponse listResponse)  {
//                        Log.i("AAAAAA","getPopularItemList()");
//
//
//                        view.updateRecycleViewData(listResponse.getItemEntities());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.i("AAAAAA","getPopularItemList()--->"+throwable.getMessage());
//
//                    }
//                });

        model.getPopularList(languageName,"start")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PopularResponse>() {
                    @Override
                    public void accept(PopularResponse listResponse)  {
                        Log.i("AAAAAA","getPopularItemList()");
                      ArrayList<PopularFavoriteEntity> list = model.getFavoritePopular();
                        for (PopularItemEntity itemEntity : listResponse.getItemEntities()) {
                            for (PopularFavoriteEntity favoriteEntity : list) {
                                if(itemEntity.getId() == favoriteEntity.getPopularId()){
                                    itemEntity.setFavorite(true);
                                }
                            }
                        }
                        view.updateRecycleViewData(listResponse.getItemEntities());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("AAAAAA","getPopularItemList()--->"+throwable.getMessage());

                    }});
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
