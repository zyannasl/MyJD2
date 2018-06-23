package com.example.jd.modle;


import com.example.jd.bean.CarBean;
import com.example.jd.utils.RetrofitUtils;
import com.example.mvp.mvp.BaseModel;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CarModel extends BaseModel {
     private RetrofitUtils retrofitUtils;
     public void showCar(String uid, String token, final ICarModel iCarModel){
          retrofitUtils=RetrofitUtils.getInstance();
          HashMap<String, String> params = new HashMap<>();
          params.put("uid", uid);
          params.put("token", token);
          retrofitUtils.getApi().showCar(params)
                  .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                  .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                  .subscribe(new Observer<CarBean>() {
                       @Override
                       public void onSubscribe(Disposable d) {

                       }

                       @Override
                       public void onNext(CarBean carBean) {
                              iCarModel.success(carBean);
                       }

                       @Override
                       public void onError(Throwable e) {

                       }

                       @Override
                       public void onComplete() {

                       }
                  });
     }
     public interface ICarModel{
          void success(CarBean carBean);
     }
}
