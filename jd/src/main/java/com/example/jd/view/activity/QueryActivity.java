package com.example.jd.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jd.R;
import com.example.jd.view.custom.FlowLayout;
import com.example.jd.view.custom.MyView;

public class QueryActivity extends AppCompatActivity {
    private String mNames[] = {
            "杏子", "婴儿防晒服 新品", "戴尔xps",
            "红米5a", "玫瑰花"
    };
    private FlowLayout mFlowLayout;
    private MyView myview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        initChildViews();
        myview = findViewById(R.id.myview);
        myview.setCallBackInterface(new MyView.CallBackInterface() {
            @Override
            public void onSearchClick(String keywords) {
                Intent intent = new Intent(QueryActivity.this, SelectActivity.class);
                intent.putExtra("keywords", keywords);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick() {
                Toast.makeText(QueryActivity.this, "清空了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initChildViews() {
        mFlowLayout = findViewById(R.id.flowLayout);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 10;
        lp.rightMargin = 10;
        lp.topMargin = 10;
        lp.bottomMargin = 10;
        for (int i = 0; i < mNames.length; i++) {
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.BLACK);
            view.setBackgroundColor(Color.GRAY);
            view.setTextSize(18);
            mFlowLayout.addView(view, lp);

        }
    }
}
