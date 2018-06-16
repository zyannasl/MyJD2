package com.example.jd.view.interfaces;

import com.example.jd.bean.CarBean;

import java.util.List;

public interface ICarFragmentView {
    void showList(List<CarBean.DataBean> groupList, List<List<CarBean.DataBean.ListBean>> childList);
}