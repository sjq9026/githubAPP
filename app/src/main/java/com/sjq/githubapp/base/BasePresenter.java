package com.sjq.githubapp.base;

/**
 * Created by Administrator on 2016/10/18.
 */

public abstract class BasePresenter<V> {
    public V view;


    public void onAttach(V view) {
        this.view = view;
    }

    public void onDestory() {
        this.view = null;
    }
}
