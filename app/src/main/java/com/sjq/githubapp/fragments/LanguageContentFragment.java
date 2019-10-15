package com.sjq.githubapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private static final String ARG_PARAM1 = "param1";
    public final static String FAVORITE = "Favorite";
    public final static String NET_WORK = "NET_WORK";
    private static final String TYPE = "type";
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
    public static LanguageContentFragment newInstance(String param1,String param2) {
        LanguageContentFragment fragment = new LanguageContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        args.putString(TYPE,param2);
        Log.i("AAAAAA","newInstance()===>"+param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();


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
                //madapter.notifyDataSetChanged();
                madapter.dataChange(list);
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
        Log.i("AAAAAA","onCreateView()===>"+getArguments().getString(ARG_PARAM1));
        recyclerView.setLayoutManager(manager);
        EventBus.getDefault().register(this);
        //设置分隔线
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator( new DefaultItemAnimator());

        Log.i("AAAAAA","onCreate()===>");
        if (getArguments() != null) {
            if(getArguments().get(TYPE).equals(LanguageContentFragment.NET_WORK)){
                mPresenter.getPopularItemList(getArguments().getString(ARG_PARAM1));
            }else{
                mPresenter.getFavoritePopularItemList();
            }
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        mPresenter.onDestroy();
    }

    @Override
    protected LanguageContentPresenter initPresenter() {
        return new LanguageContentPresenter(this);
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
//           PopularStateEntity popularStateEntity = new PopularStateEntity();
//           popularStateEntity.setFavorite(newFavoriteStatus);
//           popularStateEntity.setPosition(position);
//           EventBus.getDefault().post(popularStateEntity);


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

        madapter = null;
        mlist = null;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFavoriteStateChange(PopularStateEntity entity){
        if(getArguments() != null){
            if(getArguments().get(TYPE).equals(LanguageContentFragment.NET_WORK)){
                this.mlist.get(entity.getPosition()).setFavorite(entity.isFavorite());
                madapter.notifyItemChanged(entity.getPosition());
            }else{
                //当是收藏fragment的时候，如果从别的页面点击了添加，这里直接从新查找一边
                if(entity.getIsFavorite()){
                    mPresenter.getFavoritePopularItemList();
                }else{
                    this.mlist.remove(entity.getPosition());
                }
                madapter.notifyDataSetChanged();
            }
        }


    }
}
