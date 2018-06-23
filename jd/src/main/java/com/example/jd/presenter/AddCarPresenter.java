package com.example.jd.presenter;

import com.example.jd.bean.AddCarBean;
import com.example.jd.modle.AddCarModel;
import com.example.jd.view.interfaces.IAddCarView;
import com.example.mvp.mvp.BasePresenter;

public class AddCarPresenter  {
    private IAddCarView iAddCarView;
    private AddCarModel addCarModel;

    public AddCarPresenter(IAddCarView iAddCarView) {
        this.iAddCarView = iAddCarView;
        addCarModel=new AddCarModel();
    }

    public void addCarData(String uid, String pid, String token){
       addCarModel.addCar(uid, pid, token, new AddCarModel.IAddCarModel() {
            @Override
            public void success(AddCarBean addCarBean) {
                iAddCarView.addSuccess(addCarBean);
            }
        });
    }
}
