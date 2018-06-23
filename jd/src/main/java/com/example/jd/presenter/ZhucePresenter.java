package com.example.jd.presenter;

import com.example.jd.bean.RegBean;
import com.example.jd.modle.ZhuceModel;
import com.example.jd.view.interfaces.IZhuceView;
import com.example.mvp.mvp.BasePresenter;

public class ZhucePresenter extends BasePresenter<ZhuceModel,IZhuceView> {
    public void zhuce(String mobile,String password){
        model.zhuce(mobile, password, new ZhuceModel.IZhuceModel() {
            @Override
            public void success(RegBean regBean) {
                view.success(regBean);
            }
        });
    }
}
