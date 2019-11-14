package com.sjq.githubapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sjq.githubapp.R;
import com.sjq.githubapp.adapters.UserMainAdapter;
import com.sjq.githubapp.javabean.UserRepo;
import com.sjq.githubapp.presenters.UserInfoPresenter;
import com.sjq.githubapp.views.UserInfoView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserMainActivity extends AppCompatActivity implements UserInfoView {
    private RecyclerView recyclerView;
    private TextView user_name_tv;
    private RoundedImageView icon_img;
    private UserInfoPresenter mPresenter;
    private UserMainAdapter mUserMainAdapter;

    public static void startCustomActivity(Activity context) {
        Intent intent = new Intent(context, UserMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        StatusBarUtil.setColorNoTranslucent(this, Color.parseColor("#ADFBFB"));
        initView();
        mPresenter = new UserInfoPresenter(this);
        mPresenter.getUserName();
        mPresenter.getUserInfo();
        mPresenter.getUserRepos();
    }

    private void initView() {


        user_name_tv = findViewById(R.id.user_name);
        icon_img = findViewById(R.id.icon_img);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void initUserName(String userName) {
        user_name_tv.setText(userName);
    }

    @Override
    public void initUserIcon(String userIcon) {
        Glide.with(this)
                .load(userIcon)
                .into(icon_img);
    }

    @Override
    public void initAdapter(ArrayList<UserRepo> list) {
        recyclerView.setAdapter(new UserMainAdapter(list));
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLongToast(String msg) {

    }

    @Override
    public void showShorToast(String msg) {

    }
}
