package com.example.jd.presenter;


import com.example.jd.bean.HomeBean;
import com.example.jd.modle.HomeModel;
import com.example.jd.view.interfaces.IHomeView;
import com.example.mvp.mvp.BasePresenter;

public class HomePresenter extends BasePresenter<HomeModel,IHomeView> {

    public void homeData(){
        
        model.getData(new HomeModel.IHomeModel() {
            @Override
            public void success(HomeBean homeBean) {
                view.success(homeBean);
            }
        });
    }
}
