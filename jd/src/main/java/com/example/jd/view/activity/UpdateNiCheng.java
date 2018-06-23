package com.example.jd.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jd.R;
import com.example.jd.bean.UpdateBean;
import com.example.jd.modle.UpdateNichengModel;
import com.example.jd.presenter.UpdateNichengPresenter;
import com.example.jd.view.interfaces.IUpdateNichengView;
import com.example.mvp.BaseActivity;
import com.example.mvp.mvp.BaseModel;

public class UpdateNiCheng extends BaseActivity<UpdateNichengPresenter> implements IUpdateNichengView,View.OnClickListener{
    private TextView fanhui;
    private TextView sure;
    private EditText nicheng;
    private SharedPreferences user;
    private String nickname="";


    @Override
    protected void initData() {

    }

    @Override
    protected BaseModel initModel() {
        return new UpdateNichengModel();
    }

    @Override
    protected UpdateNichengPresenter initPresenter() {
        return new UpdateNichengPresenter();
    }

    @Override
    protected void initView() {
        fanhui = findViewById(R.id.nicheng_fanhui);
        fanhui.setOnClickListener(this);
        sure = findViewById(R.id.nicheng_sure);
        sure.setOnClickListener(this);
        nicheng=findViewById(R.id.nicheng_et);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_update_ni_cheng;
    }

    @Override
    public void updateData(UpdateBean updateBean) {
        Toast.makeText(this, ""+ updateBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nicheng_fanhui:
                finish();
                break;
            case R.id.nicheng_sure:
                nickname = nicheng.getText().toString();
                user = getSharedPreferences("User", Context.MODE_PRIVATE);
                String suid = user.getString("uid", null);
                String stoken = user.getString("token", null);
                presenter.updateNicheng(nickname,suid,stoken);
                Intent intent = new Intent(UpdateNiCheng.this, MyMsgActivity.class);
                startActivity(intent);
                break;
        }
    }
}
