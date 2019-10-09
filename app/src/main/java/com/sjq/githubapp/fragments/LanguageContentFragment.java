package com.sjq.githubapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.service.autofill.TextValueSanitizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sjq.githubapp.R;
import com.sjq.githubapp.base.BaseFragment;
import com.sjq.githubapp.presenters.LanguageContentPresenter;
import com.sjq.githubapp.views.LanguageContentView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LanguageContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LanguageContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LanguageContentFragment extends BaseFragment<LanguageContentView, LanguageContentPresenter> implements LanguageContentView {


    private OnFragmentInteractionListener mListener;

    public LanguageContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment LanguageContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LanguageContentFragment newInstance(String param1) {
        LanguageContentFragment fragment = new LanguageContentFragment();
        Bundle args = new Bundle();
        args.putString("name",param1);
        Log.i("TAG","newInstance----->"+param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_language_content, container, false);
        TextView tv = view.findViewById(R.id.tv);

        tv.setText(getArguments().getString("name"));
        return view;
    }

    @Override
    protected LanguageContentPresenter initPresenter() {
        return new LanguageContentPresenter();
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
