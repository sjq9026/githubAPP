package com.sjq.githubapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sjq.githubapp.R;
import com.sjq.githubapp.base.BaseMvpActivity;
import com.sjq.githubapp.presenters.LoginPresenter;
import com.sjq.githubapp.views.LoginView;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import androidx.constraintlayout.widget.ConstraintLayout;
import jp.wasabeef.blurry.Blurry;


public class LoginActivity extends BaseMvpActivity<LoginView, LoginPresenter> implements LoginView, View.OnClickListener {


    private EditText userNameEt;
    private EditText psdEt;
    private Button loginBtn;

    private LoadingDialog mLoadingView;
    private ConstraintLayout wrap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }


    private void initView() {
        mLoadingView = new LoadingDialog(this);
        mLoadingView.setLoadingText("加载中")
                .setSuccessText("加载成功")//显示加载成功时的文字
                //.setFailedText("加载失败")
                .setInterceptBack(true)
                .setLoadSpeed(LoadingDialog.Speed.SPEED_ONE)
                .setRepeatCount(1000);

        userNameEt = (EditText) findViewById(R.id.user_name_et);
        psdEt = (EditText) findViewById(R.id.psd_et);
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);


        wrap = (ConstraintLayout) findViewById(R.id.wrap);
//        Blurry.with(this).radius(25).sampling(2).onto(wrap);
        Blurry.with(this)
                .radius(10)
                .sampling(8)
                .color(Color.argb(66, 255, 255, 0))
                .async()
                .animate(500)
                .onto(wrap);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                mLoadingView.show();
                mPresenter.doLogin(userNameEt.getText().toString().trim(), psdEt.getText().toString().trim());
        }

    }

    @Override
    public void loginSuccess() {
        mLoadingView.close();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void loginFailed(String errMsg) {
        mLoadingView.close();
    }
}
