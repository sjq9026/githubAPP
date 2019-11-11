package com.sjq.githubapp.models;

import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.javabean.TrendingKeyEntity;
import com.sjq.githubapp.javabean.UserContactTrendingKeyEntity;

import java.util.ArrayList;

public interface TrendingModel extends BaseModel {

    ArrayList<TrendingKeyEntity> getLanguage(String userName);
    ArrayList<TrendingKeyEntity>  getAllLanguage(String userName);
    void saveAllLanguage(ArrayList<UserContactTrendingKeyEntity> list,String userName);
    ArrayList<UserContactTrendingKeyEntity> getUserLanguage(String userName);
    void saveSortFinishLanguage(ArrayList<TrendingKeyEntity> list, String userName);



}
