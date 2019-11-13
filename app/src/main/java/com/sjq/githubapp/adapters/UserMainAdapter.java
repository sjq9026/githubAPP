package com.sjq.githubapp.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sjq.githubapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class UserMainAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public UserMainAdapter(ArrayList<String> datas) {
        super(R.layout.user_main_page_item_layout, datas);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.user_name, item);


    }
}
