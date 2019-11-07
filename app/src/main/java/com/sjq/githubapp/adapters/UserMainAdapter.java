package com.sjq.githubapp.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class UserMainAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public UserMainAdapter(ArrayList<String> datas){
        super(android.R.layout.simple_list_item_1,datas);
    }
    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(android.R.id.text1,item);


    }
}
