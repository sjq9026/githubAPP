package com.sjq.githubapp.base;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.sjq.githubapp.R;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseMvpActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements BaseView {

    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        //StatusBarUtil.setTransparent(this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onAttach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
    }

    public abstract T initPresenter();


    @Override
    public void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showShorToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
