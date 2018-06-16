package com.example.jd.view.interfaces;

import com.example.jd.bean.FenLeiLeftBean;
import com.example.jd.bean.FenLeiRightBean;
import com.example.mvp.mvp.IBaseView;

import java.util.List;

public interface IClassifyView extends IBaseView{
    void successLeft(FenLeiLeftBean fenLeiLeftBean);
    void successRight(List<FenLeiRightBean.DataBean> groupList,List<List<FenLeiRightBean.DataBean.ListBean>> childList);

}
