package com.sjq.githubapp.views;

import android.content.Context;

import com.sjq.githubapp.base.BaseView;

public interface UserInfoView extends BaseView {


    Context getContext();

    void initUserName(String userName);


}
