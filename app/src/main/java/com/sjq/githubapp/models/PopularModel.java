package com.sjq.githubapp.models;

import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.javabean.PopularKeyEntity;
import com.sjq.githubapp.javabean.UserContactPopularKeyEntity;

import java.util.ArrayList;

public interface PopularModel  extends BaseModel {

    ArrayList<PopularKeyEntity> getLanguage(String userName);
    ArrayList<PopularKeyEntity>  getAllLanguage();

    ArrayList<UserContactPopularKeyEntity> getUserLanguage(String userName);

    void saveAllLanguage(ArrayList<UserContactPopularKeyEntity> list,String userName);

    void saveSortFinishLanguage(ArrayList<PopularKeyEntity> list,String userName);

}
