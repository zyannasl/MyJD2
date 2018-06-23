package com.example.jd.view.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.adapter.CarAdapter;
import com.example.jd.bean.CarBean;
import com.example.jd.modle.CarModel;
import com.example.jd.presenter.CarPresenter;
import com.example.jd.utils.MessageEvent;
import com.example.jd.utils.PriceAndCountEvent;
import com.example.jd.view.interfaces.ICarView;
import com.example.mvp.BaseFragment;
import com.example.mvp.mvp.BaseModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ShoppingCat extends BaseFragment<CarPresenter> implements ICarView {

    private SharedPreferences user;
    private ExpandableListView elv;
    private CheckBox check;
    private TextView price;
    private TextView num;
    private CarAdapter carAdapter;

    @Override
    protected void initData() {
        user=getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        String suid = user.getString("uid", null);
        String stoken = user.getString("token", null);
        EventBus.getDefault().register(this);
        presenter.showCar(suid,stoken);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carAdapter.changeAllListCbState(check.isChecked());
            }
        });
    }

    @Override
    protected BaseModel initModel() {
        return new CarModel();
    }

    @Override
    protected CarPresenter initPresenter() {
        return new CarPresenter();
    }

    @Override
    protected void initView(View view) {
        elv = view.findViewById(R.id.car_elv);
        check = view.findViewById(R.id.car_check);
        price = view.findViewById(R.id.car_price);
        num = view.findViewById(R.id.car_num);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fragment_car;
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
    public void showCar(List<CarBean.DataBean> groupList, List<List<CarBean.DataBean.ListBean>> childList) {
        carAdapter = new CarAdapter(getContext(), groupList, childList);
        elv.setAdapter(carAdapter);
        //去除箭头
        elv.setGroupIndicator(null);
        //默认展开
        for (int i = 0; i < groupList.size(); i++) {
            elv.expandGroup(i);
        }
    }
    @org.greenrobot.eventbus.Subscribe
    public void onMessageEvent(MessageEvent event) {
        check.setChecked(event.isChecked());
    }

    @org.greenrobot.eventbus.Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        num.setText("结算(" + event.getCount() + ")");
        price.setText(event.getPrice() + "");
    }
}
