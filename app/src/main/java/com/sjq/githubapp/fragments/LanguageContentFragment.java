package com.sjq.githubapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.http.GET;

import android.service.autofill.TextValueSanitizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sjq.githubapp.R;
import com.sjq.githubapp.activities.PopularDetailActivity;
import com.sjq.githubapp.adapters.PopularAdapter;
import com.sjq.githubapp.base.BaseFragment;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.PopularStateEntity;
import com.sjq.githubapp.presenters.LanguageContentPresenter;
import com.sjq.githubapp.views.LanguageContentView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LanguageContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LanguageContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LanguageContentFragment extends BaseFragment<LanguageContentView, LanguageContentPresenter> implements LanguageContentView, PopularAdapter.onPopularItemClickListener {


    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private PopularAdapter madapter;
    private ArrayList<PopularItemEntity> mlist;

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
        Log.i("AAAAAA","newInstance()===>"+param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPresenter.getPopularItemList(getArguments().getString("name"));
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Nullable
    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void updateRecycleViewData(ArrayList<PopularItemEntity> list) {
           mlist = list;
            if(madapter == null){
                madapter = new PopularAdapter(getActivity(),this.mlist,this);
                recyclerView.setAdapter(madapter);
            }else{
                madapter.notifyDataSetChanged();
                //recyclerView.setAdapter(madapter);
            }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_language_content, container, false);
         recyclerView = view.findViewById(R.id.recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.VERTICAL);
        Log.i("AAAAAA","onCreateView()===>"+getArguments().getString("name"));
        recyclerView.setLayoutManager(manager);
        EventBus.getDefault().register(this);
        //设置分隔线
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        //recyclerView.setAdapter(madapter = new PopularAdapter(getActivity(),new ArrayList<PopularItemEntity>()));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
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



    @Override
    public void onItemFavoriteStatusChange(int position, boolean newFavoriteStatus) {
        this.mlist.get(position).setFavorite(newFavoriteStatus);
        madapter.notifyItemChanged(position);
    }




    @Override
    public void onPopularItemClick(int position, PopularItemEntity popularItemEntity) {
            //mPresenter.onPopularItemClick(position,popularItemEntity);
        PopularDetailActivity.startToPopularDetailActivity(getActivity(),position,popularItemEntity);

    }

    @Override
    public void onFavoriteClick(int position, PopularItemEntity popularItemEntity) {
            mPresenter.onFavoriteClick(position,popularItemEntity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("AAAAAA","onDestroy()");
        madapter = null;
        mlist = null;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFavoriteStateChange(PopularStateEntity entity){
        this.mlist.get(entity.getPosition()).setFavorite(entity.isFavorite());
        madapter.notifyItemChanged(entity.getPosition());

    }
}
