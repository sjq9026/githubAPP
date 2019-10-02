package com.sjq.githubapp.network;


import com.sjq.githubapp.javabean.DemoEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Request {
    // 填上需要访问的服务器地址
    public static String HOST = "https://www.xxx.com/app_v5/";

    @POST("?service=sser.getList")
    Observable<Response<List<DemoEntity>>> getList(@Query("id") String id);
}
