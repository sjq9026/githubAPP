package com.sjq.githubapp.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.sjq.githubapp.adapters.LanguageAdapter;
import com.sjq.githubapp.base.BaseMvpActivity;
import com.sjq.githubapp.javabean.PopularKeyEntity;
import com.sjq.githubapp.presenters.CustomPresenter;
import com.sjq.githubapp.views.CustomView;

import java.util.ArrayList;

public class CustomActivity extends BaseMvpActivity<CustomView, CustomPresenter> implements CustomView, View.OnClickListener {
private RecyclerView recyclerView;
private ImageView back_img;
private TextView edit_tv;
private ArrayList<PopularKeyEntity> mList;
private LanguageAdapter mAdapter;
public static final String LANGUAGE_FLAG = "Language";
    public static final String KEY_FLAG = "Key";




        public static void startCustomActivity(Activity context,String flag){
            Intent intent = new Intent(context,CustomActivity.class);
            intent.putExtra("flag",flag);
            context.startActivity(intent);
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
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
    public CustomPresenter initPresenter() {
        return new CustomPresenter(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setLanguageAdapter(ArrayList<PopularKeyEntity> list) {
        mList = list;
        if(mAdapter == null){
            mAdapter = new LanguageAdapter(list);
            recyclerView.setAdapter(mAdapter);
            recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Log.i("adapter",position+"");
                    mList.get(position).setChecked(!mList.get(position).getChecked());
                }
            });
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.edit_tv:
                if(mAdapter != null){
                    if(edit_tv.getText().equals("编辑")){
                        mAdapter.setEditAble(true);
                        edit_tv.setText("保存");
                    }else{
                        mAdapter.setEditAble(false);
                        mPresenter.saveAllLanguage(mList);
                        Log.i("list",mList.toString());
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
    protected void onDestroy() {
        super.onDestroy();
        mList = null;
        mAdapter = null;
    }
}
