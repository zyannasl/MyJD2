package com.example.jd.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jd.R;
import com.example.jd.bean.RegBean;
import com.example.jd.modle.ZhuceModel;
import com.example.jd.presenter.ZhucePresenter;
import com.example.jd.view.interfaces.IZhuceView;
import com.example.mvp.BaseActivity;
import com.example.mvp.mvp.BaseModel;

public class ZhuceActivity extends BaseActivity<ZhucePresenter> implements IZhuceView,View.OnClickListener {
    private ImageView fanhui;
    private EditText moible;
    private EditText pwd;
    private Button button;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuce_fanhui:
                finish();
                break;
            case R.id.zhuce_button:
               presenter.zhuce(moible.getText().toString(), pwd.getText().toString());
                break;
        }
    }

    @Override
    public void mobileError() {
        Toast.makeText(this, "手机号有误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passError() {
        Toast.makeText(this, "密码不能为空或者不合法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(RegBean regBean) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void serverError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BaseModel initModel() {
        return new ZhuceModel();
    }

    @Override
    protected ZhucePresenter initPresenter() {
        return new ZhucePresenter();
    }

    @Override
    protected void initView() {
        fanhui = findViewById(R.id.zhuce_fanhui);
        fanhui.setOnClickListener(this);
        moible = findViewById(R.id.zhuce_number);
        pwd = findViewById(R.id.zhuce_pwd);
        button = findViewById(R.id.zhuce_button);
        button.setOnClickListener(this);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_zhuce;
    }
}
