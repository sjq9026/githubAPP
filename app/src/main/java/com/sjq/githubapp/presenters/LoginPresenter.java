package com.sjq.githubapp.presenters;

import android.os.Handler;

import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.views.LoginView;


public class LoginPresenter extends BasePresenter<LoginView> {

    public void doLogin(String userName, String psd) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.loginSuccess();
            }
        }, 2000);

    }
}
