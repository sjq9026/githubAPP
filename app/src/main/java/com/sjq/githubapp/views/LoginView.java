package com.sjq.githubapp.views;

import android.content.Context;

import com.sjq.githubapp.base.BaseView;

public interface LoginView  extends BaseView {

    void loginSuccess();
    void loginFailed(String errMsg);
    Context getContext();


}
