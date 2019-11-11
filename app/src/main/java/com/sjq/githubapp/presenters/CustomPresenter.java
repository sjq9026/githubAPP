package com.sjq.githubapp.presenters;

import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.PopularKeyEntity;
import com.sjq.githubapp.javabean.UserContactPopularKeyEntity;
import com.sjq.githubapp.models.PopularModel;
import com.sjq.githubapp.models.PopularModelImpl;
import com.sjq.githubapp.util.UtilsSharePre;
import com.sjq.githubapp.views.CustomView;

import java.util.ArrayList;


public class CustomPresenter implements BasePresenter {
    private final String userName;
    CustomView mView;
      PopularModel model;
    public CustomPresenter(CustomView loginView) {
        this.mView = loginView;
        this.model = new PopularModelImpl();
         userName = UtilsSharePre.getInstance().getPreferenceString(mView.getContext(), UtilsSharePre.USER_NAME, "");

    }

    public void initLanguage() {
        ArrayList<PopularKeyEntity> mLanguages = model.getAllLanguage();
        ArrayList<PopularKeyEntity> userLanguage = model.getLanguage(userName);

        if(mLanguages == null){
            mLanguages = new ArrayList<>();
        }
        if(userLanguage == null){
            userLanguage = new ArrayList<>();
        }

        for (PopularKeyEntity mLanguage : mLanguages) {
            boolean isCon = false;
            for (PopularKeyEntity entity : userLanguage) {
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

    public  void saveAllLanguage(ArrayList<PopularKeyEntity> list){
        ArrayList<UserContactPopularKeyEntity> userContactPopularKeyEntities = new ArrayList<>();
        for (PopularKeyEntity entity : list) {
            UserContactPopularKeyEntity entity1 = new UserContactPopularKeyEntity();
            if(entity.getChecked()){
                entity1.setUser_name(userName);
                entity1.setPopular_key_id(entity.getId());
                userContactPopularKeyEntities.add(entity1);
            }
        }
        model.saveAllLanguage(userContactPopularKeyEntities,userName);

    }



    @Override
    public void onDestroy() {

    }
}
