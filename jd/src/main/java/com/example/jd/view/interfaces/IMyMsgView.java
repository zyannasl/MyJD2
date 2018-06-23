package com.example.jd.view.interfaces;

import com.example.jd.bean.UserMsgBean;
import com.example.mvp.mvp.IBaseView;

public interface IMyMsgView extends IBaseView {
    //获取信息
    void userMsg(UserMsgBean userMsgBean);
    //上传头像
    void lodingImg();
}
