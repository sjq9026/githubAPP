package com.sjq.githubapp.presenters;

import android.os.Handler;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.UserEntity;
import com.sjq.githubapp.util.UtilsSharePre;
import com.sjq.githubapp.views.LoginView;

import java.util.ArrayList;


public class LoginPresenter implements BasePresenter {
    LoginView mView;
    public LoginPresenter(LoginView loginView) {
        this.mView = loginView;
    }

    public void doLogin(String userName, String token) {
        if(userName.isEmpty() || token.isEmpty()){
            mView.loginFailed("用户名和token不能为空");
            return;
        }
        UtilsSharePre.getInstance().setPreferenceString(mView.getContext(),UtilsSharePre.USER_NAME,userName);
        UtilsSharePre.getInstance().setPreferenceString(mView.getContext(),UtilsSharePre.TOKEN,token);
      ArrayList<UserEntity> users  = (ArrayList<UserEntity>) MyApplication.getmDaoSession().getUserEntityDao().loadAll();
      UserEntity userEntity = new UserEntity();
      userEntity.setToken(token);
      userEntity.setUser_name(userName);
      boolean isCon = false;
      if(users == null || users.size() == 0){
          userEntity.setAutoId(0L);
            MyApplication.getmDaoSession().getUserEntityDao().insert(userEntity);
        }else {
          for (UserEntity user : users) {
              if(user.getUser_name().equals(userName)){
                  isCon = true;
              }
          }
          if(!isCon){
              userEntity.setAutoId(Long.parseLong(users.size()+""));
              MyApplication.getmDaoSession().getUserEntityDao().insert(userEntity);
          }

      }

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
