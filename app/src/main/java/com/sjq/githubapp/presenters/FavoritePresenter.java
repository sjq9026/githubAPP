package com.sjq.githubapp.presenters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sjq.githubapp.R;
import com.sjq.githubapp.adapters.MyFragmentPagerAdapter;
import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.customviews.ScaleTransitionPagerTitleView;
import com.sjq.githubapp.fragments.LanguageContentFragment;
import com.sjq.githubapp.fragments.TrendingContentFragment;
import com.sjq.githubapp.javabean.LanguageEntity;
import com.sjq.githubapp.views.FavoriteView;
import com.sjq.githubapp.views.TrendingView;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class FavoritePresenter implements BasePresenter {

    private FavoriteView mView;

    public FavoritePresenter(FavoriteView mView) {
        this.mView = mView;
    }





        public CommonNavigator initCommonNavigator(final ArrayList<String> mTitleDataList) {

            CommonNavigator commonNavigator = new CommonNavigator(mView.getContext());
            commonNavigator.setEnablePivotScroll(false);
            commonNavigator.setAdjustMode(true);

            CommonNavigatorAdapter adapter = new CommonNavigatorAdapter() {

                @Override
                public int getCount() {
                    return mTitleDataList == null ? 0 : mTitleDataList.size();
                }
                @Override
                public IPagerTitleView getTitleView(Context context, final int index) {

                    SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                    simplePagerTitleView.setText(mTitleDataList.get(index));
                    simplePagerTitleView.setTextSize(16);
                    simplePagerTitleView.setNormalColor(Color.parseColor("#ffffff"));
                    simplePagerTitleView.setSelectedColor(Color.parseColor("#ffffff"));
                    simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mView.onTabClick(index);
                        }
                    });
                    return simplePagerTitleView;


                }

                @Override
                public IPagerIndicator getIndicator(Context context) {
                    LinePagerIndicator indicator = new LinePagerIndicator(context);
                    indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                    indicator.setColors(Color.WHITE);


                    return indicator;
                }
            };
            commonNavigator.setAdapter(adapter);
            return commonNavigator;
        }


    public MyFragmentPagerAdapter getFragmentPagerAdapter(ArrayList<String> tabs, FragmentManager manager) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabs.size(); i++) {
            if(tabs.get(i).equals("Popular")){
                fragments.add(LanguageContentFragment.newInstance(tabs.get(i),LanguageContentFragment.FAVORITE));

            }
            if(tabs.get(i).equals("Trending")){
                fragments.add(TrendingContentFragment.newInstance(tabs.get(i),LanguageContentFragment.FAVORITE));
            }
        }
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(manager, fragments);
        return myFragmentPagerAdapter;

    }


    @Override
    public void onDestroy() {
        this.mView = null;
    }
}
