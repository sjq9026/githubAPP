package com.sjq.githubapp.base;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public abstract class BaseFragment<V extends BaseView, T extends BasePresenter<V>>
                                                    extends Fragment implements BaseView {
    public T mPresenter;

    public BaseFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = initPresenter();
        mPresenter.onAttach((V) this);
        TextView textView = new TextView(getActivity());
        textView.setText("brank fragment");
        return textView;
    }

    protected abstract T initPresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
    }

    @Override
    public void showLongToast(String msg) {

    }

    @Override
    public void showShorToast(String msg) {

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }
}
