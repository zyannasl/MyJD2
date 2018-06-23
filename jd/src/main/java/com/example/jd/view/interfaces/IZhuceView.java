package com.example.jd.view.interfaces;

import com.example.jd.bean.RegBean;
import com.example.mvp.mvp.IBaseView;

public interface IZhuceView extends IBaseView {
    void mobileError();
    void passError();

    void success(RegBean regBean);//注册成功 code 0
    void fail(String msg);//注册失败 code 1
    void serverError(String msg);//异常情况
}
