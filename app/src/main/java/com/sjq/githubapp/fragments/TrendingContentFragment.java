package com.sjq.githubapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.http.PATCH;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sjq.githubapp.R;
import com.sjq.githubapp.activities.PopularDetailActivity;
import com.sjq.githubapp.adapters.PopularAdapter;
import com.sjq.githubapp.adapters.TrendingAdapter;
import com.sjq.githubapp.base.BaseFragment;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.PopularStateEntity;
import com.sjq.githubapp.javabean.TrendingItemEntity;
import com.sjq.githubapp.javabean.TrendingStateEntity;
import com.sjq.githubapp.presenters.TrendingListPresenter;
import com.sjq.githubapp.views.LanguageContentView;
import com.sjq.githubapp.views.PopularDetailView;
import com.sjq.githubapp.views.TrendingContentView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrendingContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrendingContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrendingContentFragment extends BaseFragment<TrendingContentView, TrendingListPresenter> implements TrendingContentView , TrendingAdapter.onTrendingItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String TYPE = "type";

    // TODO: Rename and change types of parameters
    private String mParam1;
    public final static String FAVORITE = "Favorite";
    public final static String NET_WORK = "NET_WORK";

    private BaseFragment.OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private TrendingAdapter madapter;
    private ArrayList<TrendingItemEntity> mlist;
    private SmartRefreshLayout refresh_layout;
    private View view;

    public TrendingContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment TrendingContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrendingContentFragment newInstance(String param1,String param2) {
        TrendingContentFragment fragment = new TrendingContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(TYPE, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_trending_content, container, false);
        refresh_layout = view.findViewById(R.id.refresh_layout);
        recyclerView = view.findViewById(R.id.recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.VERTICAL);
        Log.i("AAAAAA","onCreateView()===>"+getArguments().getString(ARG_PARAM1));
        recyclerView.setLayoutManager(manager);
        EventBus.getDefault().register(this);
        //设置分隔线
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        if (getArguments() != null) {
            if(getArguments().get(TYPE).equals(TrendingContentFragment.NET_WORK)){
                refresh_layout.autoRefresh();
                mPresenter.getTrendingItemList(getArguments().getString(ARG_PARAM1),"weekly");
            }else {
                mPresenter.getFavoriteTrendingItemList();
            }
        }
        refresh_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (getArguments() != null) {
                    if(getArguments().get(TYPE).equals(LanguageContentFragment.NET_WORK)){
                        mPresenter.getTrendingItemList(getArguments().getString(ARG_PARAM1),"weekly");
                    }else{
                        mPresenter.getFavoriteTrendingItemList();
                    }
                }
            }
        });

        return view;
    }

    @Override
    protected TrendingListPresenter initPresenter() {
        return new TrendingListPresenter(this);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (BaseFragment.OnFragmentInteractionListener) context;
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
    public void updateRecycleViewData(ArrayList<TrendingItemEntity> list) {
        refresh_layout.finishRefresh();
        mlist = list;
        if(madapter == null){
            madapter = new TrendingAdapter(getActivity(),this.mlist,this);
            madapter.setHasStableIds(true);
            recyclerView.setAdapter(madapter);
        }else{
            madapter.dataChange(list);
            //recyclerView.setAdapter(madapter);
        }
    }

    @Override
    public void onItemFavoriteStatusChange(int position, boolean newFavoriteStatus) {
//        if(getArguments() != null &&  getArguments().getString(TYPE).equals(NET_WORK)){
//            this.mlist.get(position).setFavorite(newFavoriteStatus);
//            madapter.notifyItemChanged(position);
//        }else{
//           TrendingStateEntity popularStateEntity = new TrendingStateEntity();
//           popularStateEntity.setFavorite(newFavoriteStatus);
//           popularStateEntity.setPosition(position);
//           EventBus.getDefault().post(popularStateEntity);
//        }

    }

    @Override
    public void refreshError() {
        refresh_layout.finishRefresh(false);
    }


    @Override
    public void onTrendingItemClick(int position, TrendingItemEntity trendingItemEntity) {
        PopularDetailActivity.startToPopularDetailActivity(getActivity(),position,trendingItemEntity);

    }

    @Override
    public void onFavoriteClick(int position, TrendingItemEntity trendingItemEntity) {
        mPresenter.onFavoriteClick(position,trendingItemEntity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.i("AAAAAA","onDestroy()");
        madapter = null;
        mlist = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFavoriteStateChange(TrendingStateEntity entity){
        if(getArguments() != null){
            if(getArguments().get(TYPE).equals(LanguageContentFragment.NET_WORK)){
                if(this.mlist != null){
                    int index = -1;
                    for (int i=0;i<this.mlist.size();i++) {
                        if(this.mlist.get(i).getRepo().equals(entity.getRepo()) ){
                            this.mlist.get(i).setFavorite(entity.getIsFavorite());
                            index = i;
                            break;
                        }
                    }
                    if(index > -1){
                        madapter.notifyItemChanged(index);
                    }
                }
            }else{

                if(this.mlist != null){
                    int index = -1;
                    for (int i=0;i<this.mlist.size();i++) {
                        if(this.mlist.get(i).getRepo().equals(entity.getRepo()) ){
                            index = i;
                            break;
                        }
                    }
                    if(index > -1){
                        madapter.notifyItemChanged(index);
                    }
                    //当是收藏fragment的时候，如果从别的页面点击了添加，这里直接从新查找一边
                    if(entity.getIsFavorite()){
                        mPresenter.getFavoriteTrendingItemList();
                    }else{
                        this.mlist.remove(index);
                    }
                    madapter.dataChange(this.mlist);
                }




            }
        }


    }
}
