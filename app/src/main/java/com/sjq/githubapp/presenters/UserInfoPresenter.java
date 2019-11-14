package com.sjq.githubapp.presenters;

import android.util.Log;

import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.UserInfoResponse;
import com.sjq.githubapp.javabean.UserRepo;
import com.sjq.githubapp.models.UserInfoModel;
import com.sjq.githubapp.models.UserInfoModelImpl;
import com.sjq.githubapp.util.UtilsSharePre;
import com.sjq.githubapp.views.UserInfoView;

import java.util.ArrayList;

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
        userName = UtilsSharePre.getInstance().getPreferenceString(mView.getContext(), UtilsSharePre.USER_NAME, "未知");

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
                        mView.initUserIcon(userInfoResponse.getAvatar_url());
                        Log.i("userInfo", userInfoResponse.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("userInfo", throwable.getMessage());
                    }
                });
    }

    public void getUserRepos() {
//        model.getUserRepos(userName)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<UserRepo>() {
//
//                    @Override
//                    public void accept(UserRepo userRepos) throws Exception {
//                        Log.i("userInfo", userRepos.toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.i("userInfo", throwable.getMessage());
//                    }
//                });
        model.getUserRepos(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<UserRepo>>() {


                    @Override
                    public void accept(ArrayList<UserRepo> userRepos) throws Exception {
                        Log.i("userInfo", userRepos.toString());
                        mView.initAdapter(userRepos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("userInfo", throwable.getMessage());
                    }
                });
    }


    @Override
    public void onDestroy() {

    }
}
