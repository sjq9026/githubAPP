package com.sjq.githubapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sjq.githubapp.R;
import com.sjq.githubapp.adapters.UserMainAdapter;
import com.sjq.githubapp.presenters.UserInfoPresenter;
import com.sjq.githubapp.views.UserInfoView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserMainActivity extends AppCompatActivity implements UserInfoView {
    private RecyclerView recyclerView;
    private TextView user_name_tv;
    private ImageView icon_img;
    private UserInfoPresenter mPresenter;

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
    }

    private void initView() {
        ArrayList<String> strings = new ArrayList<>();

        user_name_tv = findViewById(R.id.user_name);
        icon_img = findViewById(R.id.icon_img);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new UserMainAdapter(strings));
    }

    @Override
    public void initUserName(String userName) {

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
