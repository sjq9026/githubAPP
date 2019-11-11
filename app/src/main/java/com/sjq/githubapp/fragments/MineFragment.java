package com.sjq.githubapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sjq.githubapp.R;
import com.sjq.githubapp.activities.CustomActivity;
import com.sjq.githubapp.activities.CustomTrendingKeyActivity;
import com.sjq.githubapp.activities.SortActivity;
import com.sjq.githubapp.activities.SortTrendingKeyActivity;
import com.sjq.githubapp.activities.UserMainActivity;
import com.sjq.githubapp.base.BaseFragment;
import com.sjq.githubapp.presenters.MinePresenter;
import com.sjq.githubapp.views.MineView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private String mParam1;


    private OnFragmentInteractionListener mListener;

    private RelativeLayout home_layout;
    private RelativeLayout custom_language_layout;
    private RelativeLayout key_layout;
    private RelativeLayout sort_key_layout;
    private RelativeLayout sort_language_layout;


    public MineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment MineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance(String param1) {
        MineFragment fragment = new MineFragment();
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
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        home_layout = view.findViewById(R.id.home_layout);
        custom_language_layout = view.findViewById(R.id.custom_language_layout);
        key_layout = view.findViewById(R.id.key_layout);
        sort_key_layout = view.findViewById(R.id.sort_key_layout);
        sort_language_layout = view.findViewById(R.id.sort_language_layout);
        home_layout.setOnClickListener(this);
        custom_language_layout.setOnClickListener(this);
        key_layout.setOnClickListener(this);
        sort_key_layout.setOnClickListener(this);
        sort_language_layout.setOnClickListener(this);
        return view;
    }

    @Override
    protected MinePresenter initPresenter() {
        return new MinePresenter();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
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
            case R.id.home_layout:
                UserMainActivity.startCustomActivity(getActivity());
                break;
            case R.id.custom_language_layout:
                CustomActivity.startCustomActivity(getActivity(),CustomActivity.LANGUAGE_FLAG);
                break;
            case R.id.sort_key_layout:
                SortTrendingKeyActivity.startCustomActivity(getActivity(),SortTrendingKeyActivity.LANGUAGE_FLAG);
                break;
            case R.id.sort_language_layout:
                SortActivity.startCustomActivity(getActivity(),SortActivity.LANGUAGE_FLAG);
                break;
            case R.id.key_layout:
                CustomTrendingKeyActivity.startCustomActivity(getActivity(),CustomTrendingKeyActivity.LANGUAGE_FLAG);
                break;
        }
    }
}
