package com.sjq.githubapp.adapters;

import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sjq.githubapp.R;
import com.sjq.githubapp.javabean.UserRepo;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class UserMainAdapter extends BaseQuickAdapter<UserRepo, BaseViewHolder> {
    public UserMainAdapter(ArrayList<UserRepo> datas) {
        super(R.layout.user_main_page_item_layout, datas);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserRepo userRepo) {
        Log.i("userRepo", userRepo.getFull_name());
        helper.setText(R.id.full_name, userRepo.getFull_name());


    }
}
