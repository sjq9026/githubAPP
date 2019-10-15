package com.sjq.githubapp.presenters;
import android.util.Log;


import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.OwnerEntity;
import com.sjq.githubapp.javabean.PopularFavoriteEntity;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.PopularResponse;

import com.sjq.githubapp.javabean.PopularStateEntity;
import com.sjq.githubapp.models.LanguageContentModelImpl;
import com.sjq.githubapp.views.LanguageContentView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LanguageContentPresenter implements BasePresenter {


    private LanguageContentModelImpl model;
    private LanguageContentView mView;
    public LanguageContentPresenter(LanguageContentView view) {
        model = new LanguageContentModelImpl();
        mView = view;
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
                        mView.updateRecycleViewData(listResponse.getItemEntities());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("AAAAAA","getPopularItemList()--->"+throwable.getMessage());

                    }});
    }


    public  void getFavoritePopularItemList(){

        ArrayList<PopularFavoriteEntity> list = model.getFavoritePopular();
        ArrayList<PopularItemEntity> result_list = new ArrayList<>();
        for (PopularFavoriteEntity favoriteEntity : list) {
            PopularItemEntity itemEntity = new PopularItemEntity();
            itemEntity.setId(favoriteEntity.getPopularId());
            OwnerEntity ownerEntity = new OwnerEntity();
            ownerEntity.setAvatar_url(itemEntity.getArchive_url());
            itemEntity.setOwner(ownerEntity);
            itemEntity.setDescription(favoriteEntity.getDescription());
            itemEntity.setStargazers_count(favoriteEntity.getStargazers_count());
            itemEntity.setFull_name(favoriteEntity.getFull_name());
            itemEntity.setHtml_url(favoriteEntity.getHtml_url());
            itemEntity.setFavorite(true);
            result_list.add(itemEntity);

        }
        mView.updateRecycleViewData(result_list);
    }





    public void onFavoriteClick(int position, PopularItemEntity popularItemEntity) {
        PopularFavoriteEntity favoriteEntity = new PopularFavoriteEntity();
        favoriteEntity.setAvatar_url(popularItemEntity.getOwner().getAvatar_url());
        favoriteEntity.setDescription(popularItemEntity.getDescription());
        favoriteEntity.setPopularId(popularItemEntity.getId());
        favoriteEntity.setStargazers_count(popularItemEntity.getStargazers_count());
        favoriteEntity.setFull_name(popularItemEntity.getFull_name());
        favoriteEntity.setHtml_url(popularItemEntity.getHtml_url());
        if(!popularItemEntity.isFavorite()){
            model.addFavoritePopularData(favoriteEntity);
//            //为了favoritefragment 实时刷新
//            PopularStateEntity popularStateEntity = new PopularStateEntity();
//            popularStateEntity.setFavorite(true);
//            popularStateEntity.setPosition(position);
//            EventBus.getDefault().post(popularStateEntity);
            mView.onItemFavoriteStatusChange(position,true);
        }else{
            model.removeFavoritePopularData(favoriteEntity);
//            PopularStateEntity popularStateEntity = new PopularStateEntity();
//            popularStateEntity.setFavorite(false);
//            popularStateEntity.setPosition(position);
//            EventBus.getDefault().post(popularStateEntity);
            mView.onItemFavoriteStatusChange(position,false);
        }
    }
    @Override
    public void onDestroy() {
        mView = null;
    }

}
