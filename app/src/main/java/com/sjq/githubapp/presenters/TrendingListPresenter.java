package com.sjq.githubapp.presenters;

import android.util.Log;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.db.greendao.TrendingEntityDao;
import com.sjq.githubapp.javabean.TrendingEntity;
import com.sjq.githubapp.javabean.TrendingItemEntity;
import com.sjq.githubapp.javabean.TrendingResponse;
import com.sjq.githubapp.javabean.TrendingStateEntity;
import com.sjq.githubapp.javabean.UserContactTrendingEntity;
import com.sjq.githubapp.models.TrendingContentModelImpl;
import com.sjq.githubapp.util.UtilsSharePre;
import com.sjq.githubapp.views.TrendingContentView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TrendingListPresenter implements BasePresenter {


    private TrendingContentModelImpl model;
    private TrendingContentView mView;
    private String userName;

    public TrendingListPresenter(TrendingContentView view) {
        model = new TrendingContentModelImpl();
        mView = view;
        this.userName = UtilsSharePre.getInstance().getPreferenceString(mView.getContext(), UtilsSharePre.USER_NAME, "");

    }


    public void getTrendingItemList(String lang, String since) {
        model.getTrendingList(lang, since)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TrendingResponse>() {
                    @Override
                    public void accept(TrendingResponse listResponse) {

                        ArrayList<TrendingEntity> list = model.getFavoriteTrending(userName);
                        for (TrendingItemEntity itemEntity : listResponse.getTrendingItemEntities()) {

                            TrendingEntity entity = new TrendingEntity();
                            entity.setStars(itemEntity.getStars());
                            entity.setRepo_link(itemEntity.getRepo_link());
                            entity.setRepo(itemEntity.getRepo());
                            entity.setLang(itemEntity.getLang());
                            entity.setForks(itemEntity.getForks());
                            ArrayList<String> a = itemEntity.getAvatars();
                            if (a != null && a.size() > 0) {
                                entity.setAvatarImg(a.get(0));
                            } else {
                                entity.setAvatarImg("error");
                            }
                            entity.setAdded_stars(itemEntity.getAdded_stars());
                            entity.setDesc(itemEntity.getDesc());
                            TrendingEntity already = MyApplication.getmDaoSession().getTrendingEntityDao().queryBuilder().
                                    where(TrendingEntityDao.Properties.Repo.eq(itemEntity.getRepo())).unique();
                            if (already == null) {
                                MyApplication.getmDaoSession().getTrendingEntityDao().insertOrReplace(entity);
                            }


                            for (TrendingEntity favoriteEntity : list) {
                                if (itemEntity.getRepo().equals(favoriteEntity.getRepo())) {
                                    itemEntity.setFavorite(true);
                                }
                            }
                        }
                        mView.updateRecycleViewData(listResponse.getTrendingItemEntities());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("AAAAAA", "getPopularItemList()--->" + throwable.getMessage());
                        if (mView != null) {
                            mView.refreshError();
                        }

                    }
                });

    }

    public void getFavoriteTrendingItemList() {
        ArrayList<TrendingEntity> list = model.getFavoriteTrending(userName);
        ArrayList<TrendingItemEntity> result_list = new ArrayList<>();
        for (TrendingEntity trendingFavoriteEntity : list) {
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
        TrendingEntity favoriteEntity = new TrendingEntity();
        favoriteEntity.setAdded_stars(trendingItemEntity.getAdded_stars());
        if (trendingItemEntity.getAvatars() != null && trendingItemEntity.getAvatars().size() > 0) {
            favoriteEntity.setAvatarImg(trendingItemEntity.getAvatars().get(0));
        } else {
            favoriteEntity.setAvatarImg("error");
        }
        favoriteEntity.setDesc(trendingItemEntity.getDesc());
        favoriteEntity.setRepo(trendingItemEntity.getRepo());
        favoriteEntity.setRepo_link(trendingItemEntity.getRepo_link());
        favoriteEntity.setStars(trendingItemEntity.getStars());



        UserContactTrendingEntity userContactTrendingEntity = new UserContactTrendingEntity();
        userContactTrendingEntity.setTrending_repo(trendingItemEntity.getRepo());
        userContactTrendingEntity.setUser_name(userName);

        TrendingStateEntity popularStateEntity = new TrendingStateEntity();

        popularStateEntity.setRepo(trendingItemEntity.getRepo());
        popularStateEntity.setPosition(position);
        if (!trendingItemEntity.isFavorite()) {
            model.addFavoriteTrendingData(userContactTrendingEntity);
            popularStateEntity.setFavorite(true);
            //mView.onItemFavoriteStatusChange(position,true);
        } else {
            model.removeFavoriteTrendingData(userContactTrendingEntity);
            popularStateEntity.setFavorite(false);
            //mView.onItemFavoriteStatusChange(position,false);
        }
        EventBus.getDefault().post(popularStateEntity);
    }


    @Override
    public void onDestroy() {
        mView = null;
    }

}
