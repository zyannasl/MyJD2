package com.example.jd.modle;

import com.example.jd.bean.XiangBean;
import com.example.jd.utils.RetrofitUtils;
import com.example.mvp.mvp.BaseModel;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class XiangModel extends BaseModel {
    private RetrofitUtils retrofitUtils;

    public void xiangData(String pid, final IXiangQingModel iXiangQingModel){
        retrofitUtils=RetrofitUtils.getInstance();
        HashMap<String, String> params = new HashMap<>();
        params.put("pid", pid);
        retrofitUtils.getApi().getXiang(params)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<XiangBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XiangBean xiangBean) {
                        iXiangQingModel.success(xiangBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface IXiangQingModel {
        void success(XiangBean xiangBean);
    }
}
