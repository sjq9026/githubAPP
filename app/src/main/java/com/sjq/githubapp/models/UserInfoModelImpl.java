package com.sjq.githubapp.models;

import com.sjq.githubapp.javabean.UserInfoResponse;
import com.sjq.githubapp.javabean.UserRepo;
import com.sjq.githubapp.network.NetWorkManager;

import java.util.ArrayList;

import io.reactivex.Observable;

public class UserInfoModelImpl implements UserInfoModel {


    @Override
    public Observable<UserInfoResponse> getUserInfo(String userName) {
        return NetWorkManager.getRequest().getUserInfo(userName);
    }

    @Override
    public Observable<ArrayList<UserRepo>> getUserRepos(String userName) {
       // return NetWorkManager.getRequest().getUserRepos(userName);
        return NetWorkManager.getRequest().getUserRepos(userName);
    }
}
