package com.sjq.githubapp.models;

import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.javabean.UserInfoResponse;

import io.reactivex.Observable;

public interface UserInfoModel extends BaseModel {

    Observable<UserInfoResponse> getUserInfo(String userName);


}
