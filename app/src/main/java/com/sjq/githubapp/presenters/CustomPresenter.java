package com.sjq.githubapp.presenters;

import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.PopularKeyEntity;
import com.sjq.githubapp.models.PopularModel;
import com.sjq.githubapp.models.PopularModelImpl;
import com.sjq.githubapp.views.CustomView;

import java.util.ArrayList;


public class CustomPresenter implements BasePresenter {
    CustomView mView;
      PopularModel model;
    public CustomPresenter(CustomView loginView) {
        this.mView = loginView;
        this.model = new PopularModelImpl();
    }

    public void initLanguage() {
        ArrayList<PopularKeyEntity> mLanguages = model.getAllLanguage();
        if(mLanguages == null){
            mLanguages = new ArrayList<>();
        }
        //更新UI
       mView.setLanguageAdapter(mLanguages);
    }

    public  void saveAllLanguage(ArrayList<PopularKeyEntity> list){
        model.saveAllLanguage(list);

    }



    @Override
    public void onDestroy() {

    }
}