package com.example.jd.view.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jd.R;
import com.example.jd.adapter.SelectGroupAdapter;
import com.example.jd.adapter.SelectLineAdapter;
import com.example.jd.bean.SelectBean;
import com.example.jd.modle.SelectModel;
import com.example.jd.presenter.SelectPresenter;
import com.example.jd.view.interfaces.ISelectView;
import com.example.mvp.BaseActivity;
import com.example.mvp.mvp.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends BaseActivity<SelectPresenter> implements View.OnClickListener,ISelectView{
    private ImageView fanhui;
    private EditText select_et;
    private ImageView select_et_lin;
    private String keywords;
    private RecyclerView select_rv;
    private boolean flog = false;
    private List<SelectBean.DataBean> list;
    private SelectGroupAdapter selectGroupAdapter;
    private SelectLineAdapter selectLineAdapter;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_fanhui:
                finish();
                break;
            case R.id.select_iv_lin:
                if (!flog) {
                    Glide.with(this).load(R.mipmap.kind_grid).into(select_et_lin);
                    selectGroupAdapter = new SelectGroupAdapter(SelectActivity.this, list);
                    select_rv.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
                    select_rv.setAdapter(selectGroupAdapter);
                    flog = true;
                    selectGroupAdapter.setClickListener(new SelectGroupAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(View view, int pid) {
                            Intent intent = new Intent(SelectActivity.this, XiangActivity.class);
                            intent.putExtra("pid", pid + "");
                            startActivity(intent);
                        }
                    });
                } else {
                    Glide.with(this).load(R.mipmap.kind_liner).into(select_et_lin);
                    selectLineAdapter = new SelectLineAdapter(SelectActivity.this, list);
                    select_rv.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
                    select_rv.setAdapter(selectLineAdapter);
                    flog = false;
                    selectLineAdapter.setOnItemClickListener(new SelectLineAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(View view, int pid) {
                            Intent intent = new Intent(SelectActivity.this, XiangActivity.class);
                            intent.putExtra("pid", pid + "");
                            startActivity(intent);
                        }
                    });

                }
                break;
        }

    }

    @Override
    public void selectData(SelectBean selectBean) {
        list = selectBean.getData();
        selectLineAdapter = new SelectLineAdapter(SelectActivity.this, list);
        select_rv.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        select_rv.setAdapter(selectLineAdapter);

        selectLineAdapter.setOnItemClickListener(new SelectLineAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int pid) {
                Intent intent = new Intent(SelectActivity.this, XiangActivity.class);
                intent.putExtra("pid", pid + "");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        //取得从上一个Activity当中传递过来的Intent对象
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        if (intent != null) {
            keywords = intent.getStringExtra("keywords");
        }
        select_et.setText(keywords);

        presenter.selectData(keywords);
    }

    @Override
    protected BaseModel initModel() {
        return new SelectModel();
    }

    @Override
    protected SelectPresenter initPresenter() {
        return new SelectPresenter();
    }

    @Override
    protected void initView() {
        fanhui = findViewById(R.id.select_fanhui);
        select_et = findViewById(R.id.select_et);
        select_et_lin = findViewById(R.id.select_iv_lin);
        select_rv = findViewById(R.id.select_rv);
        fanhui.setOnClickListener(this);
        select_et_lin.setOnClickListener(this);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_select;
    }



}
