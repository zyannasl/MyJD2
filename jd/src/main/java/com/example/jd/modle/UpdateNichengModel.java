package com.example.jd.modle;

import android.util.Log;

import com.example.jd.bean.UpdateBean;
import com.example.jd.utils.RetrofitUtils;
import com.example.jd.view.interfaces.IUpdateNichengView;
import com.example.mvp.mvp.BaseModel;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UpdateNichengModel extends BaseModel {
    private RetrofitUtils retrofitUtils;
    public void updateNicheng(String nickname, String suid, String stoken, final IUpdateNichengModel iUpdateNichengModel){
        retrofitUtils=RetrofitUtils.getInstance();
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", suid);
        params.put("nickname", nickname);
        params.put("token", stoken);
        retrofitUtils.getApi().updateNicheng(params)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateBean updateBean) {
                        iUpdateNichengModel.success(updateBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IUpdateNichengModel{
        void success(UpdateBean updateBean);

    }
}
