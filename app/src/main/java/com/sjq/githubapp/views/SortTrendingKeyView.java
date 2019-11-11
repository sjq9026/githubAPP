package com.sjq.githubapp.views;

import android.content.Context;

import com.sjq.githubapp.base.BaseView;
import com.sjq.githubapp.javabean.TrendingKeyEntity;

import java.util.ArrayList;

public interface SortTrendingKeyView extends BaseView {


    Context getContext();

    void setLanguageAdapter(ArrayList<TrendingKeyEntity> list);


}
