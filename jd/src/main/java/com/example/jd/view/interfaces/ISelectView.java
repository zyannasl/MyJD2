package com.example.jd.view.interfaces;

import com.example.jd.bean.SelectBean;
import com.example.mvp.mvp.IBaseView;

public interface ISelectView extends IBaseView {
    //显示商品也

    void selectData(SelectBean selectBean);

}
