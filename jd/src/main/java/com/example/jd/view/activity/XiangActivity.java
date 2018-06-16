package com.example.jd.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jd.R;
import com.example.jd.bean.AddCarBean;
import com.example.jd.bean.XiangBean;
import com.example.jd.modle.XiangModel;
import com.example.jd.presenter.AddCarPresenter;
import com.example.jd.presenter.XiangPresenter;
import com.example.jd.view.interfaces.IAddCarView;
import com.example.jd.view.interfaces.IXiangView;
import com.example.mvp.BaseActivity;
import com.example.mvp.mvp.BaseModel;
import com.example.mvp.mvp.BasePresenter;
import com.facebook.drawee.view.SimpleDraweeView;

public class XiangActivity extends BaseActivity<XiangPresenter> implements IXiangView, View.OnClickListener, IAddCarView {
    private SimpleDraweeView image;
    private TextView title;
    private TextView price;
    private TextView subhead;
    private ImageView xiaoxi;
    private ImageView dianpu;
    private ImageView shopcar;
    private TextView addshopcar;
    private String pid;
    private AddCarPresenter addCarPresenter;
    private ImageView guanzhu;
    private Boolean falg = false;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xq_xiaoxi:
                Intent intent2 = new Intent(this, MsgActivity.class);
                startActivity(intent2);
                break;
            case R.id.xq_dianpu:
                Toast.makeText(this, "点击店铺页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.xq_shopcar:
                Toast.makeText(this, "跳转购物车页面", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(XiangActivity.this, HomePagerActivity.class);
                startActivity(intent1);
                break;
            case R.id.xq_addshopcar:
                //获取uid pid token
                SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                String uid = sp.getString("uid", "");
                String token = sp.getString("token", "");
                //判断是否登录
                if (token == null || token == "") {
                    Toast.makeText(this, "还没登录, 请先登录", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(XiangActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    AddCarPresenter addCarPresenter = new AddCarPresenter();
                    addCarPresenter.addCarData(uid, pid, token);
                }

                break;
            case R.id.xq_guangzhu:
                if(!falg){
                    Glide.with(this).load(R.mipmap.selectheart).into(guanzhu);
                    falg=true;
                }else{
                    Glide.with(this).load(R.mipmap.normalheart).into(guanzhu);
                    falg=false;
                }
        }
    }

    @Override
    public void success(XiangBean xiangBean) {
        String images = xiangBean.getData().getImages();
        String[] split = images.split("\\|");
        image.setImageURI(split[0]);
        title.setText(xiangBean.getData().getTitle());
        price.setText(xiangBean.getData().getPrice());
        subhead.setText(xiangBean.getData().getSubhead());
    }

    @Override
    protected void initData() {
//获得上一页面传来的pid
        Intent intent = getIntent();
        if (intent != null) {
            pid = intent.getStringExtra("pid");
        }


    }

    @Override
    protected BaseModel initModel() {
        return new XiangModel();
    }

    @Override
    protected XiangPresenter initPresenter() {
        return new XiangPresenter();
    }

    @Override
    protected void initView() {
        image = findViewById(R.id.xq_image);
        title = findViewById(R.id.xq_title);
        price = findViewById(R.id.xq_price);
        subhead = findViewById(R.id.xq_su);
        xiaoxi = findViewById(R.id.xq_xiaoxi);
        dianpu = findViewById(R.id.xq_dianpu);
        shopcar = findViewById(R.id.xq_shopcar);
        addshopcar = findViewById(R.id.xq_addshopcar);
        guanzhu = findViewById(R.id.xq_guangzhu);
        guanzhu.setOnClickListener(this);
        xiaoxi.setOnClickListener(this);
        dianpu.setOnClickListener(this);
        shopcar.setOnClickListener(this);
        addshopcar.setOnClickListener(this);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_xiang;
    }

    @Override
    public void addSuccess(AddCarBean addCarBean) {
        Toast.makeText(this, "" + addCarBean.getMsg(), Toast.LENGTH_SHORT).show();
    }
}
