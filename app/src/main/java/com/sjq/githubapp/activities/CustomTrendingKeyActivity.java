package com.sjq.githubapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.sjq.githubapp.R;
import com.sjq.githubapp.adapters.TrendingKeyAdapter;
import com.sjq.githubapp.base.BaseMvpActivity;
import com.sjq.githubapp.javabean.TrendingKeyEntity;
import com.sjq.githubapp.presenters.CustomTrendingKeyPresenter;
import com.sjq.githubapp.views.CustomTrendingView;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomTrendingKeyActivity extends BaseMvpActivity<CustomTrendingView, CustomTrendingKeyPresenter> implements CustomTrendingView, View.OnClickListener {
    private RecyclerView recyclerView;
    private ImageView back_img;
    private TextView edit_tv;
    private ArrayList<TrendingKeyEntity> mList;
    private TrendingKeyAdapter mAdapter;
    public static final String LANGUAGE_FLAG = "Language";
    public static final String KEY_FLAG = "Key";

    public static void startCustomActivity(Activity context, String flag){
        Intent intent = new Intent(context,CustomTrendingKeyActivity.class);
        intent.putExtra("flag",flag);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_trending_key);
        initView();
        mPresenter.initLanguage();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        back_img = findViewById(R.id.back_img);
        edit_tv = findViewById(R.id.edit_tv);
        edit_tv.setOnClickListener(this);
        back_img.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public CustomTrendingKeyPresenter initPresenter() {
        return new CustomTrendingKeyPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_tv:
                if (mAdapter != null) {
                    if (edit_tv.getText().equals("编辑")) {
                        mAdapter.setEditAble(true);
                        edit_tv.setText("保存");
                    } else {
                        mAdapter.setEditAble(false);
                        mPresenter.saveAllLanguage(mList);
                        Log.i("list", mList.toString());
                        edit_tv.setText("编辑");
                    }
                }
                break;
            case R.id.back_img:
                this.finish();
                break;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mList = null;
        mAdapter = null;
    }

    @Override
    public void setLanguageAdapter(ArrayList<TrendingKeyEntity> list) {
        mList = list;
        if (mAdapter == null) {
            mAdapter = new TrendingKeyAdapter(list);
            recyclerView.setAdapter(mAdapter);
            recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Log.i("adapter", position + "");
                    mList.get(position).setChecked(!mList.get(position).getChecked());
                }
            });
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
