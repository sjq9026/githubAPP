package com.sjq.githubapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sjq.githubapp.R;
import com.sjq.githubapp.base.BaseFragment;
import com.sjq.githubapp.javabean.PopularKeyEntity;
import com.sjq.githubapp.presenters.PopularPresenter;
import com.sjq.githubapp.views.PopularView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PopularFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PopularFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularFragment extends BaseFragment<PopularView, PopularPresenter> implements PopularView , View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private View contentView;
    private ViewPager viewPager;
    private ImageView search;

    // TODO: Rename and change types of parameters
    private String mParam1;


    private OnFragmentInteractionListener mListener;
    private MagicIndicator magicIndicator;


    public PopularFragment() {
        super();
    }

    @Override
    public Context getContext() {
        if (mListener != null) {
            return (Activity) mListener;
        } else {
            throw new RuntimeException("must implement OnFragmentInteractionListener");

        }
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment PopularFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PopularFragment newInstance(String param1) {
        PopularFragment fragment = new PopularFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
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
        contentView = inflater.inflate(R.layout.fragment_popular, container, false);
        initView();
        mPresenter.initLanguage();
        return contentView;
    }


    private void initView() {

        magicIndicator = contentView.findViewById(R.id.magic_indicator);
        viewPager = contentView.findViewById(R.id.view_pager);
        search = contentView.findViewById(R.id.search_img);
        search.setOnClickListener(this);

    }

    @Override
    public void refreshLanguage(final ArrayList<PopularKeyEntity> languageEntities) {
        Log.i("init", languageEntities.toString());
        if (magicIndicator == null) {
            magicIndicator = contentView.findViewById(R.id.magic_indicator);
        }
        magicIndicator.setNavigator(mPresenter.initCommonNavigator(languageEntities));
        viewPager.setAdapter(mPresenter.getFragmentPagerAdapter(languageEntities, getChildFragmentManager()));
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    @Override
    public void onTabClick(int index) {
        viewPager.setCurrentItem(index);
    }


    @Override
    protected PopularPresenter initPresenter() {
        return new PopularPresenter(this);
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


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_img:

                break;
        }
    }
}
