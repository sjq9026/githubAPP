package com.sjq.githubapp.presenters;

import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.TrendingKeyEntity;
import com.sjq.githubapp.models.TrendingModel;
import com.sjq.githubapp.models.TrendingModelImpl;
import com.sjq.githubapp.util.UtilsSharePre;
import com.sjq.githubapp.views.SortTrendingKeyView;

import java.util.ArrayList;


public class SortTrendingKeyPresenter implements BasePresenter {
    SortTrendingKeyView mView;
    TrendingModel model;
    String userName;

    public SortTrendingKeyPresenter(SortTrendingKeyView loginView) {
        this.mView = loginView;
        this.model = new TrendingModelImpl();
        this.userName = UtilsSharePre.getInstance().getPreferenceString(mView.getContext(), UtilsSharePre.USER_NAME, "");

    }


    public void saveSortFinishLanguage(ArrayList<TrendingKeyEntity> list) {
        model.saveSortFinishLanguage(list, userName);

    }

    public void initCheckedLanguage() {
        ArrayList<TrendingKeyEntity> mLanguages = model.getLanguage(userName);
        if (mLanguages == null) {
            mLanguages = new ArrayList<>();
        }
        //更新UI
        mView.setLanguageAdapter(mLanguages);
    }


    @Override
    public void onDestroy() {

    }
}
