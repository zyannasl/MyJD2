package com.example.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mvp.mvp.BaseModel;
import com.example.mvp.mvp.BasePresenter;
import com.example.mvp.mvp.IBaseView;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        initView();
        presenter=initPresenter();
        if(presenter!=null){
            presenter.attch(initModel(),this);
        }
        initData();
    }

    protected abstract void initData();

    /**
     * 初始化子类Model
     * @return
     */
    protected abstract BaseModel initModel();

    /**
     * 让子类初始化p
     * @return
     */
    protected abstract P initPresenter();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 绑定资源
     * @return
     */
    protected abstract int bindLayoutId();

    /**
     * 连接失败
     * @param msg
     */
    public void serverFail(String msg){

    }
    public void showLoading(){

    }
    public void hideLoading(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detach();
        }
    }
}
