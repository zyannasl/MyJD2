package com.example.jd.modle;

import android.util.Log;

import com.example.jd.bean.BaseResponseBean;
import com.example.jd.bean.HomeBean;
import com.example.jd.utils.RetrofitUtils;
import com.example.mvp.mvp.BaseModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeModel extends BaseModel {

    private RetrofitUtils retrofitUtils;

    public void getData(final IHomeModel iHomeModel) {
        retrofitUtils = RetrofitUtils.getInstance();
        retrofitUtils.getApi().getData()
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponseBean<HomeBean>>() {
                               @Override
                               public void onSubscribe(Disposable d) {
                                   Log.e("onSubscribe", d + "");
                               }

                               @Override
                               public void onNext(BaseResponseBean<HomeBean> homeBeanBaseResponseBean) {
                                   HomeBean data = homeBeanBaseResponseBean.data;
                                   iHomeModel.success(data);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.e("onErrer", e + "");
                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }

    public interface IHomeModel {
        void success(HomeBean homeBean);
    }
}
