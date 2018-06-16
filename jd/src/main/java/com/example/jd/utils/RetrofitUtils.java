package com.example.jd.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private final Retrofit retrofit;

    private RetrofitUtils(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        //建立retrofit对象

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseUrl)
                .client(client)//实现retrofit和ok的关联
                .addConverterFactory(GsonConverterFactory.create())//默认解析gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    //单例模式
    public static RetrofitUtils INSTANCE;
    public static RetrofitUtils getInstance(){
        if(INSTANCE==null){
            synchronized(RetrofitUtils.class){
                if(INSTANCE==null){
                    INSTANCE=new RetrofitUtils();
                }
            }
        }
        return INSTANCE;
    }
    //创建方法,供调用
    public RetrofitApi getApi(){
        //获取接口
        RetrofitApi retrofitApi=retrofit.create(RetrofitApi.class);
        return retrofitApi;
    }
}
