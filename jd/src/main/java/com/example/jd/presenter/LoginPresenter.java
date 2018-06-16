package com.example.jd.presenter;

import com.example.jd.bean.LoginBean;
import com.example.jd.modle.LoginModel;
import com.example.jd.view.interfaces.ILoginView;
import com.example.mvp.mvp.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginModel,ILoginView>{
    public void login(String mobile, String pwd){
        model.login(mobile, pwd, new LoginModel.ILoginModel() {
            @Override
            public void success(LoginBean loginBean) {
                view.loginSuccess(loginBean);
            }
        });
    }
}
