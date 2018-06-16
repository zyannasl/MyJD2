package com.example.jd.view.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jd.R;
import com.example.jd.view.fragment.Classify;
import com.example.jd.view.fragment.Finds;
import com.example.jd.view.fragment.Home;
import com.example.jd.view.fragment.Mine;
import com.example.jd.view.fragment.ShoppingCat;
import com.hjm.bottomtabbar.BottomTabBar;

public class HomePagerActivity extends AppCompatActivity {

    BottomTabBar bottomTabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pager);
        bottomTabBar=findViewById(R.id.bottomTabBar);
        initData();
    }

    private void initData() {
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(200, 150)
                .setFontSize(0)
                .setChangeColor(Color.RED, Color.GRAY)
                .addTabItem("", R.mipmap.ac1s2, R.mipmap.ac0s1, Home.class)
                .addTabItem("", R.mipmap.abxf2, R.mipmap.abwf1, Classify.class)
                .addTabItem("", R.mipmap.abzfa2, R.mipmap.abyfa1, Finds.class)
                .addTabItem("", R.mipmap.abvg2, R.mipmap.abug1, ShoppingCat.class)
                .addTabItem("", R.mipmap.ac3w2, R.mipmap.ac2w1, Mine.class)
                .isShowDivider(false);
    }
}
