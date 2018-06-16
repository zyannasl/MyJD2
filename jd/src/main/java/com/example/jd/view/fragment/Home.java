package com.example.jd.view.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.jd.R;
import com.example.jd.adapter.HomeAdapter;
import com.example.jd.bean.HomeBean;
import com.example.jd.modle.HomeModel;
import com.example.jd.presenter.HomePresenter;
import com.example.jd.view.interfaces.IHomeView;
import com.example.mvp.BaseFragment;
import com.example.mvp.mvp.BaseModel;

import java.util.List;

public class Home extends BaseFragment<HomePresenter> implements IHomeView{

    private RecyclerView home_rlv;

    @Override
    protected void initData() {
        presenter.homeData();
    }

    @Override
    protected BaseModel initModel() {
        return new HomeModel();
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView(View view) {
        home_rlv = view.findViewById(R.id.home_rlv);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fragment_home;
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

    @Override
    public void success(HomeBean homeBean) {
        HomeAdapter homeAdapter = new HomeAdapter(homeBean.getBanner(),homeBean.getFenlei(),homeBean.getMiaosha().getList(),homeBean.getTuijian().getList(),getActivity());
        home_rlv.setLayoutManager(new GridLayoutManager(getActivity(),1, LinearLayoutManager.VERTICAL,false));
        home_rlv.setAdapter(homeAdapter);
    }
}
