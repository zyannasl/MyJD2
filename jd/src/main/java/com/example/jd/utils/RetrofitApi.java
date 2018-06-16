package com.example.jd.utils;

import com.example.jd.bean.AddCarBean;
import com.example.jd.bean.BaseResponseBean;
import com.example.jd.bean.CarBean;
import com.example.jd.bean.FenLeiLeftBean;
import com.example.jd.bean.FenLeiRightBean;
import com.example.jd.bean.HomeBean;
import com.example.jd.bean.LoginBean;
import com.example.jd.bean.RegBean;
import com.example.jd.bean.SelectBean;
import com.example.jd.bean.XiangBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface RetrofitApi {
    @GET("home/getHome")
    Observable<BaseResponseBean<HomeBean>> getData();

    @GET("product/getCatagory")
    Observable<FenLeiLeftBean> getClassifyLeft();

    @POST("product/getProductCatagory")
    @FormUrlEncoded
    Observable<FenLeiRightBean> getClassifyRight(@FieldMap Map<String,String> p);

    @POST("product/getCarts")
    @FormUrlEncoded
    Observable<CarBean> showCar(@FieldMap Map<String,String> p);

    @POST("product/searchProducts")
    @FormUrlEncoded
    Observable<SelectBean> getSelect(@FieldMap Map<String,String> p);

    @POST("product/getProductDetail")
    @FormUrlEncoded
    Observable<XiangBean> getXiang(@FieldMap Map<String,String> p);

    @POST("product/addCart")
    @FormUrlEncoded
    Observable<AddCarBean> addCar(@FieldMap Map<String,String> p);

    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginBean> login(@FieldMap Map<String,String> p);

    @POST("user/reg")
    @FormUrlEncoded
    Observable<RegBean> reg(@FieldMap Map<String,String> p);
}
