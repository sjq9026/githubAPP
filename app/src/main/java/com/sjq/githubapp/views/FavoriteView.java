package com.sjq.githubapp.views;

import android.content.Context;

import com.sjq.githubapp.base.BaseView;

public interface FavoriteView extends BaseView {

Context getContext();
 void onTabClick(int index);


}
