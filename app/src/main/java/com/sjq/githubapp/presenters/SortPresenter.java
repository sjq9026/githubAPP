package com.sjq.githubapp.presenters;

import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.PopularKeyEntity;
import com.sjq.githubapp.models.PopularModel;
import com.sjq.githubapp.models.PopularModelImpl;
import com.sjq.githubapp.views.SortView;

import java.util.ArrayList;


public class SortPresenter implements BasePresenter {
    SortView mView;
      PopularModel model;
    public SortPresenter(SortView loginView) {
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

    public void saveSortFinishLanguage(ArrayList<PopularKeyEntity> list){
        model.saveSortFinishLanguage(list);
    }

    public void initCheckedLanguage(){
        ArrayList<PopularKeyEntity> mLanguages = model.getLanguage();
        if(mLanguages == null){
            mLanguages = new ArrayList<>();
        }
        //更新UI
        mView.setLanguageAdapter(mLanguages);
    }



    @Override
    public void onDestroy() {

    }
}
