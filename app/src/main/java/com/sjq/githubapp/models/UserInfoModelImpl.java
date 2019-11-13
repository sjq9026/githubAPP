package com.sjq.githubapp.models;

import com.sjq.githubapp.javabean.UserInfoResponse;
import com.sjq.githubapp.network.NetWorkManager;

import io.reactivex.Observable;

public class UserInfoModelImpl implements UserInfoModel {


    @Override
    public Observable<UserInfoResponse> getUserInfo(String userName) {
        return NetWorkManager.getRequest().getUserInfo(userName);
    }
}
