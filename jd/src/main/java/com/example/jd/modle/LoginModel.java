package com.example.jd.modle;

import android.support.v7.widget.RecyclerView;

import com.example.jd.bean.LoginBean;
import com.example.jd.utils.RetrofitUtils;
import com.example.mvp.mvp.BaseModel;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class LoginModel extends BaseModel{
    private RetrofitUtils retrofitUtils;
    public void login(String mobile, String pwd, final ILoginModel iLoginModel){
        retrofitUtils=RetrofitUtils.getInstance();
        HashMap<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("password", pwd);
        retrofitUtils.getApi().login(params)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        iLoginModel.success(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface ILoginModel {
        void success(LoginBean loginBean);
    }
}
