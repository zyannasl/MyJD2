package com.example.jd.view.fragment;

import android.view.View;


import com.example.jd.R;
import com.example.mvp.BaseFragment;
import com.example.mvp.mvp.BaseModel;
import com.example.mvp.mvp.BasePresenter;

public class Mine extends BaseFragment {

    @Override
    protected void initData() {

    }

    @Override
    protected BaseModel initModel() {
        return null;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void serverFail(String msg) {

    }
}
