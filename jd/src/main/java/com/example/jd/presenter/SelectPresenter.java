package com.example.jd.presenter;

import com.example.jd.bean.SelectBean;
import com.example.jd.modle.SelectModel;
import com.example.jd.view.interfaces.ISelectView;
import com.example.mvp.mvp.BasePresenter;

public class SelectPresenter extends BasePresenter<SelectModel,ISelectView>{
    public void selectData(String keyword){
        model.getSelect(keyword, new SelectModel.ISelectModel() {
            @Override
            public void success(SelectBean selectBean) {
                view.selectData(selectBean);
            }
        });
    }
}
