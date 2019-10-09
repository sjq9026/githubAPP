package com.sjq.githubapp.activities;



import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sjq.githubapp.R;
import com.sjq.githubapp.base.BaseFragment;
import com.sjq.githubapp.base.BaseMvpActivity;
import com.sjq.githubapp.fragments.FavoriteFragment;
import com.sjq.githubapp.fragments.MineFragment;
import com.sjq.githubapp.fragments.PopularFragment;
import com.sjq.githubapp.fragments.TrendingFragment;
import com.sjq.githubapp.javabean.LanguageEntity;
import com.sjq.githubapp.presenters.MainPresenter;
import com.sjq.githubapp.views.MainView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends BaseMvpActivity<MainView, MainPresenter> implements MainView, BottomNavigationView.OnNavigationItemSelectedListener, BaseFragment.OnFragmentInteractionListener {


    private FrameLayout fragmeLayout;
    private BottomNavigationView bottomNavView;
    //11
    private BaseFragment f1,f2,f3,f4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }

    public void initView() {
        fragmeLayout = (FrameLayout) findViewById(R.id.fragme_layout);
        bottomNavView = (BottomNavigationView) findViewById(R.id.bottom_nav_view);

        bottomNavView.setOnNavigationItemSelectedListener(this);
        bottomNavView.setSelectedItemId(bottomNavView.getMenu().getItem(0).getItemId());
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (menuItem.getItemId()){
            case R.id.item_popular:
                if(f1==null){
                    f1 = PopularFragment.newInstance("Popular");
                    transaction.add(R.id.fragme_layout,f1);
                }else{
                    transaction.show(f1);
                }
                break;
            case R.id.item_trending:
                if(f2==null){
                    f2 = TrendingFragment.newInstance("Trending");
                    transaction.add(R.id.fragme_layout,f2);
                }else{
                    transaction.show(f2);
                }
                break;
            case R.id.item_favorite:
                if(f3==null){
                    f3 = FavoriteFragment.newInstance("Favorite");
                    transaction.add(R.id.fragme_layout,f3);
                }else{
                    transaction.show(f3);
                }
                break;
            case R.id.item_mine:
                if(f4==null){
                    f4 = MineFragment.newInstance("Mine");
                    transaction.add(R.id.fragme_layout,f4);
                }else{
                    transaction.show(f4);
                }
                break;
        }
        transaction.commit();
        return true;
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
        if(f3!=null){
            transaction.hide(f3);
        }
        if(f4!=null){
            transaction.hide(f4);
        }

    }

    @Override
    public void initData() {


    }

    @Override
    public void onFragmentInteraction() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void refreshLanguage(ArrayList<LanguageEntity> arrayList) {
        Log.i("TAGTAG",arrayList.toString());


    }
}
