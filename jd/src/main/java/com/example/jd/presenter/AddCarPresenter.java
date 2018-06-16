package com.example.jd.presenter;

import com.example.jd.bean.AddCarBean;
import com.example.jd.modle.AddCarModel;
import com.example.jd.view.interfaces.IAddCarView;
import com.example.mvp.mvp.BasePresenter;

public class AddCarPresenter extends BasePresenter<AddCarModel,IAddCarView> {
    public void addCarData(String uid, String pid, String token){
        model.addCar(uid, pid, token, new AddCarModel.IAddCarModel() {
            @Override
            public void success(AddCarBean addCarBean) {
                view.addSuccess(addCarBean);
            }
        });
    }
}
