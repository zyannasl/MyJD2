package com.example.jd.view.interfaces;

import com.example.jd.bean.CarBean;
import com.example.mvp.mvp.IBaseView;

import java.util.List;

public interface ICarView extends IBaseView {

        void showCar(List<CarBean.DataBean> groupList, List<List<CarBean.DataBean.ListBean>> childList);

}
