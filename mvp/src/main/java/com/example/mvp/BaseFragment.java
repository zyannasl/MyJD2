package com.example.mvp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvp.mvp.BaseModel;
import com.example.mvp.mvp.BasePresenter;
import com.example.mvp.mvp.IBaseView;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView{
    public P presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(bindLayoutId(),container,false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter=initPresenter();
        if(presenter!=null){
            Log.e("BaseFragment","presenter");
            presenter.attch(initModel(),this);
        }
        initData();
    }

    protected abstract void initData();


    /**
     * 初始化子类model
     * @return
     */
    protected abstract BaseModel initModel();

    /**
     * 让子类出示化P
     * @return
     */
    protected abstract P initPresenter();

    /**
     * 初始化控件
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 绑定资源
     * @return
     */
    protected abstract int bindLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detach();
        }
    }
}
