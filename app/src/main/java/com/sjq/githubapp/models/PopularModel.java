package com.sjq.githubapp.models;

import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.javabean.PopularKeyEntity;

import java.util.ArrayList;

public interface PopularModel  extends BaseModel {

    ArrayList<PopularKeyEntity> getLanguage();
    ArrayList<PopularKeyEntity>  getAllLanguage();

    void saveAllLanguage(ArrayList<PopularKeyEntity> list);

    void saveSortFinishLanguage(ArrayList<PopularKeyEntity> list);

}
