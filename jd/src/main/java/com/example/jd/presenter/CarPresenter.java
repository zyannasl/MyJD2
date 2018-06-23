package com.example.jd.presenter;

import com.example.jd.bean.CarBean;
import com.example.jd.modle.CarModel;
import com.example.jd.view.interfaces.ICarView;
import com.example.mvp.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class CarPresenter extends BasePresenter<CarModel,ICarView>{

    public void showCar(String uid,String token){
        model.showCar(uid, token, new CarModel.ICarModel() {
            @Override
            public void success(CarBean carBean) {
                List<CarBean.DataBean> groupList=carBean.getData();
                List<List<CarBean.DataBean.ListBean>> childList=new ArrayList<>();
                for(int i=0;i<groupList.size();i++){
                    List<CarBean.DataBean.ListBean> list = groupList.get(i).getList();
                    childList.add(list);
                }
                view.showCar(groupList,childList);
            }
        });

    }
}
