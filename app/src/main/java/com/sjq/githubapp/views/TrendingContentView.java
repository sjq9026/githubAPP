package com.sjq.githubapp.views;

import android.content.Context;

import com.sjq.githubapp.base.BaseView;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.TrendingItemEntity;

import java.util.ArrayList;

public interface TrendingContentView extends BaseView {

        Context getContext();

        void updateRecycleViewData(ArrayList<TrendingItemEntity> list);

        void onItemFavoriteStatusChange(int position, boolean newFavoriteStatus);


}
