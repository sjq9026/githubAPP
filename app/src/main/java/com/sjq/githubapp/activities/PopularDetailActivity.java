package com.sjq.githubapp.activities;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjq.githubapp.R;
import com.sjq.githubapp.base.BaseMvpActivity;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.PopularStateEntity;
import com.sjq.githubapp.javabean.TrendingItemEntity;
import com.sjq.githubapp.javabean.TrendingStateEntity;
import com.sjq.githubapp.presenters.PopularDetailPresenter;
import com.sjq.githubapp.views.PopularDetailView;

import org.greenrobot.eventbus.EventBus;

public class PopularDetailActivity extends BaseMvpActivity<PopularDetailView,PopularDetailPresenter>implements View.OnClickListener,PopularDetailView {

   private PopularItemEntity popularItemEntity;
   private TrendingItemEntity trendingItemEntity;
   private final static String Popular_Param_Name = "PopularItem";
    private final static String Param_Position = "Position";
    private final static  String Trending_Param_Name = "TrendingItem";


    private WebView mWebview;
   private ImageView back_img;
   private TextView title_tv;
   private ImageView favorite_img;

    //原始的收藏状态
    private boolean originalFavorite = false;
    private int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_detail);
        popularItemEntity = (PopularItemEntity) getIntent().getSerializableExtra(Popular_Param_Name);
        trendingItemEntity = (TrendingItemEntity) getIntent().getSerializableExtra(Trending_Param_Name);
        if(popularItemEntity!=null){
            originalFavorite = popularItemEntity.isFavorite();
        }else{
            originalFavorite = trendingItemEntity.isFavorite();
        }

        position = getIntent().getIntExtra(Param_Position,0);
        initView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public PopularDetailPresenter initPresenter() {
        return new PopularDetailPresenter(this);
    }


    public void initView() {
        mWebview = findViewById(R.id.web_view);
        back_img = findViewById(R.id.back_img);
        title_tv = findViewById(R.id.title_tv);
        favorite_img = findViewById(R.id.favorite_img);
        back_img.setOnClickListener(this);
        favorite_img.setOnClickListener(this);
        if(popularItemEntity != null){
            if(popularItemEntity.isFavorite()){
                favorite_img.setBackgroundResource(R.drawable.favorite_red);
            }else{
                favorite_img.setBackgroundResource(R.drawable.favorite_white);
            }
            title_tv.setText(popularItemEntity.getHtml_url());
            mWebview.loadUrl(popularItemEntity.getHtml_url());
        }else{
            if(trendingItemEntity.isFavorite()){
                favorite_img.setBackgroundResource(R.drawable.favorite_red);
            }else{
                favorite_img.setBackgroundResource(R.drawable.favorite_white);
            }
            title_tv.setText(trendingItemEntity.getRepo_link());
            mWebview.loadUrl(trendingItemEntity.getRepo_link());
        }


        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
        WebChromeClient chromeClient = new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                title_tv.setText(title);
            }
        };
        mWebview.setWebChromeClient(chromeClient);
    }

    @Override
    public void initData() {
        super.initData();
    }

    public static void startToPopularDetailActivity(Context context,int position, PopularItemEntity popularItemEntity){
        Intent intent = new Intent(context,PopularDetailActivity.class);
        intent.putExtra(Popular_Param_Name,popularItemEntity);
        intent.putExtra(Param_Position,position);
        context.startActivity(intent);
    }

    public static void startToPopularDetailActivity(Context context,int position, TrendingItemEntity trendingItemEntity){
        Intent intent = new Intent(context,PopularDetailActivity.class);
        intent.putExtra(Trending_Param_Name,trendingItemEntity);
        intent.putExtra(Param_Position,position);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.back_img:
                onBackPressed();
                break;
            case R.id.title_tv:
                break;
            case R.id.favorite_img:
                if(popularItemEntity != null){
                    mPresenter.onFavoriteClick(0,popularItemEntity);
                }else{
                    mPresenter.onFavoriteClick(0,trendingItemEntity);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(mWebview.canGoBack()){
            mWebview.goBack();
        }else {
            if(popularItemEntity != null){
                if(originalFavorite != popularItemEntity.isFavorite()){
                    //因为涉及到多页面刷新，每个页面的操作和position位置不一样，所以用eventbus
                    PopularStateEntity popularStateEntity = new PopularStateEntity();
                    popularStateEntity.setFavorite(popularItemEntity.isFavorite());
                    popularStateEntity.setPosition(position);
                    popularStateEntity.setPopular_id(popularItemEntity.getId());
                    EventBus.getDefault().post(popularStateEntity);
                }
            }else{
                if(originalFavorite != trendingItemEntity.isFavorite()){
                    TrendingStateEntity popularStateEntity = new TrendingStateEntity();
                    popularStateEntity.setFavorite(trendingItemEntity.isFavorite());
                    popularStateEntity.setRepo(trendingItemEntity.getRepo());
                    popularStateEntity.setPosition(position);
                    EventBus.getDefault().post(popularStateEntity);
                }
            }
            super.onBackPressed();
        }
    }

    @Override
    public void onItemFavoriteStatusChange(int position, boolean isFavorite) {
       if(popularItemEntity != null){
           popularItemEntity.setFavorite(isFavorite);
       }else{
           trendingItemEntity.setFavorite(isFavorite);
       }

        if(isFavorite){
            favorite_img.setBackgroundResource(R.drawable.favorite_red);
        }else{
            favorite_img.setBackgroundResource(R.drawable.favorite_white);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

}
