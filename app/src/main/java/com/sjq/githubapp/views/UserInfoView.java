package com.sjq.githubapp.views;

import android.content.Context;

import com.sjq.githubapp.base.BaseView;
import com.sjq.githubapp.javabean.UserRepo;

import java.util.ArrayList;

public interface UserInfoView extends BaseView {


    Context getContext();

    void initUserName(String userName);

    void initUserIcon(String userIcon);

    void initAdapter(ArrayList<UserRepo> list);


}
