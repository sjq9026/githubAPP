package com.sjq.githubapp.views;

import android.content.Context;

import com.sjq.githubapp.base.BaseView;
import com.sjq.githubapp.javabean.LanguageEntity;

import java.util.ArrayList;

public interface PopularView extends BaseView {

    Context getContext();

   void refreshLanguage(ArrayList<LanguageEntity> languageEntities);

   void onTabClick(int index);
}
