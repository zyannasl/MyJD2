package com.example.jd.modle;

import com.example.jd.bean.AddCarBean;
import com.example.jd.utils.RetrofitUtils;
import com.example.mvp.mvp.BaseModel;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AddCarModel extends BaseModel {
    private RetrofitUtils retrofitUtils;
    public void addCar(String uid, String pid, String token, final IAddCarModel iAddCarModel){
        retrofitUtils=RetrofitUtils.getInstance();
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("pid", pid);
        params.put("token", token);
        retrofitUtils.getApi().addCar(params)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddCarBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddCarBean addCarBean) {
                        iAddCarModel.success(addCarBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface IAddCarModel {
        void success(AddCarBean addCarBean);
    }
}
