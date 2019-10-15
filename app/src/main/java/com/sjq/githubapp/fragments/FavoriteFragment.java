package com.sjq.githubapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sjq.githubapp.R;
import com.sjq.githubapp.base.BaseFragment;
import com.sjq.githubapp.presenters.FavoritePresenter;
import com.sjq.githubapp.views.FavoriteView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavoriteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends BaseFragment<FavoriteView, FavoritePresenter> implements FavoriteView{
    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_PARAM1 = "param1";



    private ViewPager viewPager;


    // TODO: Rename and change types of parameters
    private String mParam1;



    private MagicIndicator magicIndicator;





    private OnFragmentInteractionListener mListener;
    private View mView;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        mView = inflater.inflate(R.layout.fragment_favorite, container, false);
        initView();
        // Inflate the layout for this fragment
        return mView;
    }

    private void initView() {
        viewPager = mView.findViewById(R.id.view_pager);
        magicIndicator =mView.findViewById(R.id.magic_indicator);
        ArrayList<String> mTitleDataList = new ArrayList<>();
        mTitleDataList.add("Popular");
        mTitleDataList.add("Trending");
        magicIndicator.setNavigator(mPresenter.initCommonNavigator(mTitleDataList));
        viewPager.setAdapter(mPresenter.getFragmentPagerAdapter(mTitleDataList,getChildFragmentManager()));
        ViewPagerHelper.bind(magicIndicator, viewPager);

    }

    @Nullable
    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onTabClick(int index) {
        viewPager.setCurrentItem(index);
    }

    @Override
    protected FavoritePresenter initPresenter() {
        return new FavoritePresenter(this);
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
    public void onDestroy() {
        super.onDestroy();
    }
}
