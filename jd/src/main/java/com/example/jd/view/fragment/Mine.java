package com.example.jd.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.view.activity.LoginActivity;
import com.example.jd.view.activity.MyMsgActivity;


public class Mine extends Fragment implements View.OnClickListener{
    private ImageView img;
    private TextView login;
    private boolean have;
    private String mobile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine,container,false);
        img = view.findViewById(R.id.mine_image);
        login = view.findViewById(R.id.mine_login);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        img.setOnClickListener(this);
        SharedPreferences sp = getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        have = sp.getBoolean("have", false);
        mobile = sp.getString("mobile", "登录/注册 >");
        login.setText(mobile);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_image:
                if(have==false){
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent1 = new Intent(getActivity(), MyMsgActivity.class);
                    startActivity(intent1);
                }

        }
    }


}
