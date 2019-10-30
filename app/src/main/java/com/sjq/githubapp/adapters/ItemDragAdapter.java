package com.sjq.githubapp.adapters;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sjq.githubapp.R;
import com.sjq.githubapp.javabean.PopularKeyEntity;

import java.util.List;

import androidx.annotation.NonNull;


public class ItemDragAdapter extends BaseItemDraggableAdapter<PopularKeyEntity,BaseViewHolder> {


    public ItemDragAdapter(List data) {
        super(R.layout.language_item_layout, data);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, PopularKeyEntity item) {
        helper.setText(R.id.language_name_tv,item.getName());



    }
}
