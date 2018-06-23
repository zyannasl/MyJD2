package com.example.jd.modle;

import com.example.jd.bean.RegBean;
import com.example.jd.utils.RetrofitUtils;
import com.example.mvp.mvp.BaseModel;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ZhuceModel extends BaseModel{
    private RetrofitUtils retrofitUtils;

    public void zhuce(String mobile, String password, final IZhuceModel iZhuceModel){
        retrofitUtils=RetrofitUtils.getInstance();
        HashMap<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("password", password);
        retrofitUtils.getApi().reg(params)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegBean regBean) {
                        iZhuceModel.success(regBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface IZhuceModel {
        void success(RegBean regBean);

    }
}
