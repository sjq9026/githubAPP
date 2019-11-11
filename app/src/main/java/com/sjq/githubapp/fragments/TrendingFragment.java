package com.sjq.githubapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sjq.githubapp.R;
import com.sjq.githubapp.base.BaseFragment;
import com.sjq.githubapp.javabean.TrendingKeyEntity;
import com.sjq.githubapp.presenters.TrendingPresenter;
import com.sjq.githubapp.views.PopularView;
import com.sjq.githubapp.views.TrendingView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;

import java.util.ArrayList;


public class TrendingFragment extends BaseFragment<PopularView, TrendingPresenter> implements TrendingView {

    private static final String ARG_PARAM1 = "param1";

    private View contentView;
    private ViewPager viewPager;
    private ArrayList<TrendingKeyEntity> mLanguages;
    private TextView title_tv;


    // TODO: Rename and change types of parameters
    private String mParam1;


    private OnFragmentInteractionListener mListener;
    private MagicIndicator magicIndicator;

    public TrendingFragment() {
        super();
    }


    public static TrendingFragment newInstance(String param1) {
        TrendingFragment fragment = new TrendingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Context getContext() {
        if (mListener != null) {
            return (Activity) mListener;
        } else {
            throw new RuntimeException("must implement OnFragmentInteractionListener");

        }
    }

    @Override
    public void refreshLanguage(ArrayList<TrendingKeyEntity> keyEntities) {
        this.mLanguages = keyEntities;
        Log.i("init", keyEntities.toString());
        if (magicIndicator == null) {
            magicIndicator = contentView.findViewById(R.id.magic_indicator);
        }
        title_tv.setText(mLanguages.get(0).getName()+"优质项目推荐");
        magicIndicator.setNavigator(mPresenter.initCommonNavigator(keyEntities));
        viewPager.setAdapter(mPresenter.getFragmentPagerAdapter(keyEntities, getChildFragmentManager()));
        ViewPagerHelper.bind(magicIndicator, viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                title_tv.setText(mLanguages.get(position).getName()+"优质项目推荐");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onTabClick(int index) {
        title_tv.setText(mLanguages.get(index).getName()+"优质项目推荐");
        viewPager.setCurrentItem(index);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView = inflater.inflate(R.layout.fragment_trending, container, false);
        initView();
        mPresenter.initLanguage();
        return contentView;
    }

    private void initView() {
        title_tv = contentView.findViewById(R.id.title_tv);
        magicIndicator = contentView.findViewById(R.id.magic_indicator);
        viewPager = contentView.findViewById(R.id.view_pager);
    }

    @Override
    protected TrendingPresenter initPresenter() {
        return new TrendingPresenter(this);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
