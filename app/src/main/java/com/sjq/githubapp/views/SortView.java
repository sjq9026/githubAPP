package com.sjq.githubapp.views;

import android.content.Context;

import com.sjq.githubapp.base.BaseView;
import com.sjq.githubapp.javabean.PopularKeyEntity;

import java.util.ArrayList;

public interface SortView extends BaseView {


    Context getContext();

    void setLanguageAdapter(ArrayList<PopularKeyEntity> list);


}
