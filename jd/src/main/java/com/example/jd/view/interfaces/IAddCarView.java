package com.example.jd.view.interfaces;

import com.example.jd.bean.AddCarBean;
import com.example.mvp.mvp.IBaseView;

public interface IAddCarView extends IBaseView {
    void addSuccess(AddCarBean addCarBean);
}
