package com.example.jd.modle;

import com.example.jd.bean.UserMsgBean;
import com.example.jd.utils.RetrofitUtils;
import com.example.mvp.mvp.BaseModel;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyMsgModel extends BaseModel{
    private RetrofitUtils retrofitUtils;

    public void myMsg(String uid, final IMyMsgModel iMyMsgModel){
        retrofitUtils=RetrofitUtils.getInstance();
        HashMap<String,String> parme = new HashMap<>();
        parme.put("uid",uid);
        retrofitUtils.getApi().userMsg(parme)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserMsgBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserMsgBean userMsgBean) {
                        iMyMsgModel.success(userMsgBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IMyMsgModel{
        void success(UserMsgBean userMsgBean);
    }

}
