package com.sjq.githubapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sjq.githubapp.R;
import com.sjq.githubapp.adapters.UserMainAdapter;

import java.util.ArrayList;

public class UserMainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;







    public static void startCustomActivity(Activity context){
        Intent intent = new Intent(context,UserMainActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        initView();
    }

    private void initView() {
        ArrayList<String> strings = new ArrayList<>();
        for(int i=0;i<24;i++){
            strings.add("i="+i);
        }
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new UserMainAdapter(strings));

    }
}
