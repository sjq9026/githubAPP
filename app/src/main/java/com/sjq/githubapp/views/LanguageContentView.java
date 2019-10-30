package com.sjq.githubapp.views;

import android.content.Context;

import com.sjq.githubapp.base.BaseView;
import com.sjq.githubapp.javabean.PopularItemEntity;

import java.util.ArrayList;

public interface LanguageContentView extends BaseView {

        Context getContext();

        void updateRecycleViewData(ArrayList<PopularItemEntity> list);

        void onItemFavoriteStatusChange(int position,boolean newFavoriteStatus, PopularItemEntity popularItemEntity);

        void refreshError();


}
