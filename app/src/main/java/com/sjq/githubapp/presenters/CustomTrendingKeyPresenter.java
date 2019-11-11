package com.sjq.githubapp.presenters;

import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.TrendingKeyEntity;
import com.sjq.githubapp.javabean.UserContactTrendingKeyEntity;
import com.sjq.githubapp.models.TrendingModelImpl;
import com.sjq.githubapp.util.UtilsSharePre;
import com.sjq.githubapp.views.CustomTrendingView;

import java.util.ArrayList;

public class CustomTrendingKeyPresenter implements BasePresenter {
    private final String userName;
    CustomTrendingView mView;
    TrendingModelImpl model;

    public CustomTrendingKeyPresenter(CustomTrendingView loginView) {
        this.mView = loginView;
        this.model = new TrendingModelImpl();
        userName = UtilsSharePre.getInstance().getPreferenceString(mView.getContext(), UtilsSharePre.USER_NAME, "");

    }

    public void initLanguage() {
        ArrayList<TrendingKeyEntity> mLanguages = model.getAllLanguage(userName);
        ArrayList<TrendingKeyEntity> userLanguage = model.getLanguage(userName);

        if(mLanguages == null){
            mLanguages = new ArrayList<>();
        }
        if(userLanguage == null){
            userLanguage = new ArrayList<>();
        }

        for (TrendingKeyEntity mLanguage : mLanguages) {
            boolean isCon = false;
            for (TrendingKeyEntity entity : userLanguage) {
                if(mLanguage.getId() == entity.getId()){
                    isCon = true;
                }
            }
            if(isCon){
                mLanguage.setChecked(true);
            }else{
                mLanguage.setChecked(false);
            }
        }
        //更新UI
        mView.setLanguageAdapter(mLanguages);
    }

    public void saveAllLanguage(ArrayList<TrendingKeyEntity> list) {
        ArrayList<UserContactTrendingKeyEntity> userContactTrendingKeyEntities = new ArrayList<>();
        for (TrendingKeyEntity entity : list) {
            UserContactTrendingKeyEntity entity1 = new UserContactTrendingKeyEntity();
            if(entity.getChecked()){
                entity1.setUser_name(userName);
                entity1.setTrending_key_id(entity.getId());
                userContactTrendingKeyEntities.add(entity1);
            }
        }
        model.saveAllLanguage(userContactTrendingKeyEntities,userName);
    }

    @Override
    public void onDestroy() {

    }
}
