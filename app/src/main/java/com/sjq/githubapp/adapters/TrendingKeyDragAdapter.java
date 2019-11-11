package com.sjq.githubapp.adapters;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sjq.githubapp.R;
import com.sjq.githubapp.javabean.TrendingKeyEntity;

import java.util.List;

import androidx.annotation.NonNull;


public class TrendingKeyDragAdapter extends BaseItemDraggableAdapter<TrendingKeyEntity,BaseViewHolder> {


    public TrendingKeyDragAdapter(List data) {
        super(R.layout.language_item_layout, data);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, TrendingKeyEntity item) {
        helper.setText(R.id.language_name_tv,item.getName());



    }
}
