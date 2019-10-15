package com.sjq.githubapp.presenters;

import android.os.Handler;

import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.views.LoginView;


public class LoginPresenter implements BasePresenter {
    LoginView mView;
    public LoginPresenter(LoginView loginView) {
        this.mView = loginView;
    }

    public void doLogin(String userName, String psd) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.loginSuccess();
            }
        }, 2000);

    }

    @Override
    public void onDestroy() {

    }
}
