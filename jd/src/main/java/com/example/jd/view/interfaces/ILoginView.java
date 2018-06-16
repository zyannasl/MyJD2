package com.example.jd.view.interfaces;

import com.example.jd.bean.LoginBean;
import com.example.mvp.mvp.IBaseView;

public interface ILoginView extends IBaseView{
    //登录成功
    void loginSuccess(LoginBean loginBean);
    //登录失败
    void loginError(String mag);

    void mobileError();

    void pwdError();
}