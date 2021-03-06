package com.sjq.githubapp.presenters;

import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.PopularKeyEntity;
import com.sjq.githubapp.models.PopularModel;
import com.sjq.githubapp.models.PopularModelImpl;
import com.sjq.githubapp.util.UtilsSharePre;
import com.sjq.githubapp.views.SortView;

import java.util.ArrayList;


public class SortPresenter implements BasePresenter {
    SortView mView;
    PopularModel model;
    String userName;

    public SortPresenter(SortView loginView) {
        this.mView = loginView;
        this.model = new PopularModelImpl();
        this.userName = UtilsSharePre.getInstance().getPreferenceString(mView.getContext(), UtilsSharePre.USER_NAME, "");

    }



    public void saveSortFinishLanguage(ArrayList<PopularKeyEntity> list) {
        model.saveSortFinishLanguage(list, userName);

    }

    public void initCheckedLanguage() {
        ArrayList<PopularKeyEntity> mLanguages = model.getLanguage(userName);
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
