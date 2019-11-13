package com.sjq.githubapp.presenters;

import android.util.Log;

import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.UserInfoResponse;
import com.sjq.githubapp.models.UserInfoModel;
import com.sjq.githubapp.models.UserInfoModelImpl;
import com.sjq.githubapp.util.UtilsSharePre;
import com.sjq.githubapp.views.UserInfoView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class UserInfoPresenter implements BasePresenter {
    private final String userName;
    UserInfoView mView;
    UserInfoModel model;

    public UserInfoPresenter(UserInfoView userInfoView) {
        this.mView = userInfoView;
        this.model = new UserInfoModelImpl();
        userName = UtilsSharePre.getInstance().getPreferenceString(mView.getContext(), UtilsSharePre.USER_NAME, "");

    }

    public void getUserName() {
        if (userName != null) {
            mView.initUserName(userName);
        } else {
            mView.initUserName("");
        }
    }

    public void getUserInfo() {
        model.getUserInfo(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInfoResponse>() {
                    @Override
                    public void accept(UserInfoResponse userInfoResponse) throws Exception {
                        Log.i("userInfo",userInfoResponse.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("userInfo",throwable.getMessage());
                    }
                });
    }


    @Override
    public void onDestroy() {

    }
}
