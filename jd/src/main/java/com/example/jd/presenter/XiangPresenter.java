package com.example.jd.presenter;

import com.example.jd.bean.XiangBean;
import com.example.jd.modle.XiangModel;
import com.example.jd.view.interfaces.IXiangView;
import com.example.mvp.mvp.BasePresenter;

public class XiangPresenter extends BasePresenter<XiangModel,IXiangView>{
    public void xiangData(String pid){
        model.xiangData(pid, new XiangModel.IXiangQingModel() {
            @Override
            public void success(XiangBean xiangBean) {
                view.success(xiangBean);
            }
        });
    }
}
