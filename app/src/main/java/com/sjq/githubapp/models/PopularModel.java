package com.sjq.githubapp.models;

import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.javabean.LanguageEntity;

import java.util.ArrayList;

public interface PopularModel  extends BaseModel {

    ArrayList<LanguageEntity> getLanguage();

}
