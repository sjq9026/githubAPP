package com.sjq.githubapp.models;

import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.javabean.TrendingKeyEntity;

import java.util.ArrayList;

public interface TrendingModel extends BaseModel {

    ArrayList<TrendingKeyEntity> getLanguage();
    ArrayList<TrendingKeyEntity>  getAllLanguage();
    void saveAllLanguage(ArrayList<TrendingKeyEntity> list);

}
