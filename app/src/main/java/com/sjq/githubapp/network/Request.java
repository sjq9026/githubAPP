package com.sjq.githubapp.network;


import com.sjq.githubapp.javabean.Demo2Entity;
import com.sjq.githubapp.javabean.LanguageEntity;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.PopularResponse;
import com.sjq.githubapp.javabean.TrendingResponse;
import com.sjq.githubapp.javabean.WanAndroidResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Request {
    // 填上需要访问的服务器地址
    public static String HOST = "https://api.github.com/";
    public static String TRENDING_HOST = "https://trendings.herokuapp.com/";


    @Headers("BaseUrlName:github")
    @GET("search/repositories?")
    Observable<PopularResponse> getLanguageContext(@Query("q") String q, @Query("sort") String sort);

    @GET("{banner}/{json}")
    Observable<WanAndroidResponse> testWandroid(@Path("banner") String banner,@Path("json") String json);

    //https://trendings.herokuapp.com/repo?lang=java&since=weekly
    @Headers("BaseUrlName:"+TRENDING_HOST)
    @GET("repo?")
    Observable<TrendingResponse> getTrendingContent( @Query("lang") String lang , @Query("since") String since);

}

