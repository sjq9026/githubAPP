package com.sjq.githubapp.models;

import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.javabean.UserInfoResponse;
import com.sjq.githubapp.javabean.UserRepo;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface UserInfoModel extends BaseModel {

    Observable<UserInfoResponse> getUserInfo(String userName);

    //Observable<UserRepo> getUserRepos(String userName);
    Observable<ArrayList<UserRepo>> getUserRepos(String userName);

}
