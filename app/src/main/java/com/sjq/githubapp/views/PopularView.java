package com.sjq.githubapp.views;

import android.content.Context;

import com.sjq.githubapp.base.BaseView;
import com.sjq.githubapp.javabean.PopularKeyEntity;

import java.util.ArrayList;

public interface PopularView extends BaseView {

    Context getContext();

   void refreshLanguage(ArrayList<PopularKeyEntity> languageEntities);

   void onTabClick(int index);
}
