package com.example.jd.modle;

import com.example.jd.bean.SelectBean;
import com.example.jd.utils.RetrofitUtils;
import com.example.mvp.mvp.BaseModel;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class SelectModel extends BaseModel{
    private RetrofitUtils retrofitUtils;
    public void getSelect(String keywords, final ISelectModel iSelectModel){
        retrofitUtils=RetrofitUtils.getInstance();
        HashMap<String, String> params = new HashMap<>();
        params.put("keywords", keywords);
        retrofitUtils.getApi().getSelect(params)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SelectBean selectBean) {
                        iSelectModel.success(selectBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface ISelectModel {
        void success(SelectBean selectBean);

    }
}
