package com.example.jd.modle;

import com.example.jd.bean.FenLeiLeftBean;
import com.example.jd.bean.FenLeiRightBean;
import com.example.jd.utils.RetrofitUtils;
import com.example.mvp.mvp.BaseModel;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ClassifyModle extends BaseModel {
    private RetrofitUtils retrofitUtils;

    public void getLeftData(final IClassifyModel iClassifyModel){
        retrofitUtils = RetrofitUtils.getInstance();
        retrofitUtils.getApi().getClassifyLeft()
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<FenLeiLeftBean>() {
                               @Override
                               public void onSubscribe(Disposable d) {

                               }

                               @Override
                               public void onNext(FenLeiLeftBean fenLeiLeftBean) {


                                   iClassifyModel.successLift(fenLeiLeftBean);
                               }

                               @Override
                               public void onError(Throwable e) {

                               }

                               @Override
                               public void onComplete() {

                               }
                           });
    }
    public void getRigthData(String cid, final IClassifyModel iClassifyModel){
        retrofitUtils=RetrofitUtils.getInstance();
        HashMap<String, String> params = new HashMap<>();
        params.put("cid", cid);
        retrofitUtils.getApi().getClassifyRight(params)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<FenLeiRightBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FenLeiRightBean fenLeiRightBean) {

                        iClassifyModel.successRigth(fenLeiRightBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IClassifyModel{
        void successLift(FenLeiLeftBean fenLeiLeftBean);
        void successRigth(FenLeiRightBean fenLeiRightBean);

    }
}
