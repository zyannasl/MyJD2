package com.example.jd.presenter;


import com.example.jd.bean.UpdateBean;
import com.example.jd.modle.UpdateNichengModel;
import com.example.jd.view.interfaces.IUpdateNichengView;
import com.example.mvp.mvp.BasePresenter;

public class UpdateNichengPresenter extends BasePresenter<UpdateNichengModel,IUpdateNichengView> {
    public void updateNicheng(String nickname,String suid,String stoken){
        model.updateNicheng(nickname, suid, stoken, new UpdateNichengModel.IUpdateNichengModel() {
            @Override
            public void success(UpdateBean updateBean) {
                view.updateData(updateBean);
            }
        });
    }
}
