package com.sjq.githubapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.sjq.githubapp.R;
import com.sjq.githubapp.adapters.TrendingKeyDragAdapter;
import com.sjq.githubapp.base.BaseMvpActivity;
import com.sjq.githubapp.javabean.TrendingKeyEntity;
import com.sjq.githubapp.presenters.SortTrendingKeyPresenter;
import com.sjq.githubapp.views.SortTrendingKeyView;

import java.util.ArrayList;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SortTrendingKeyActivity extends BaseMvpActivity<SortTrendingKeyView, SortTrendingKeyPresenter> implements SortTrendingKeyView , View.OnClickListener {
    private RecyclerView recyclerView;
    private ImageView back_img;
    private TextView edit_tv;
    private ArrayList<TrendingKeyEntity> mList;
    private TrendingKeyDragAdapter mAdapter;
    public static final String LANGUAGE_FLAG = "Language";
    public static final String KEY_FLAG = "Key";
    public static void startCustomActivity(Activity context, String flag){
        Intent intent = new Intent(context,SortTrendingKeyActivity.class);
        intent.putExtra("flag",flag);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_trending_key);
        initView();
        mPresenter.initCheckedLanguage();
    }

    @Override
    public SortTrendingKeyPresenter initPresenter() {
        return new SortTrendingKeyPresenter(this);
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
    public Context getContext() {
        return this;
    }

    @Override
    public void setLanguageAdapter(ArrayList<TrendingKeyEntity> list) {
        mList = list;
        if(mAdapter == null){
            mAdapter = new TrendingKeyDragAdapter(list);
            ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
            recyclerView.setAdapter(mAdapter);
            // 开启拖拽
            mAdapter.enableDragItem(itemTouchHelper, R.id.wrap, true);
            // mAdapter.enableDragItem(itemTouchHelper);
            mAdapter.setOnItemDragListener(new OnItemDragListener() {
                @Override
                public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i) {

                }

                @Override
                public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder1, int i1) {

                }

                @Override
                public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i) {

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
                    mPresenter.saveSortFinishLanguage(mList);
                    Log.i("list",mList.toString());
                }
                this.finish();
                break;
            case R.id.back_img:
                this.finish();
                break;
        }
    }
}
