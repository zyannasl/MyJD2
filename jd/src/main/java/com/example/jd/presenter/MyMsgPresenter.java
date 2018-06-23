package com.example.jd.presenter;

import com.example.jd.bean.UserMsgBean;
import com.example.jd.modle.MyMsgModel;
import com.example.jd.view.interfaces.IMyMsgView;

public class MyMsgPresenter  {
     private IMyMsgView iMyMsgView;
     private MyMsgModel myMsgModel;

     public MyMsgPresenter(IMyMsgView iMyMsgView) {
          this.iMyMsgView = iMyMsgView;
          myMsgModel=new MyMsgModel();
     }
     public void userMsg(String uid){
          myMsgModel.myMsg(uid, new MyMsgModel.IMyMsgModel() {
               @Override
               public void success(UserMsgBean userMsgBean) {
                    iMyMsgView.userMsg(userMsgBean);
               }
          });
     }
}
