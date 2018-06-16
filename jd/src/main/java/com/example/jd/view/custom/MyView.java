package com.example.jd.view.custom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.adapter.MyAdapter;
import com.example.jd.sql.Dao;
import com.example.jd.view.activity.SelectActivity;
import java.util.List;

public class MyView extends LinearLayout {


    private EditText select;
    private TextView sousuo;
    private CallBackInterface callBackInterface;
    private Button delete;
    private ListView listview;
    private List<String> strings;
    private Dao dao;
    private String s;
    private List<String> list;


    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_my_view, this, true);
        select = view.findViewById(R.id.myview_select);
        sousuo = view.findViewById(R.id.myview_tv);
        delete = view.findViewById(R.id.delete_btn);
        listview = view.findViewById(R.id.listview);
        dao = new Dao(getContext());
        strings = dao.queryData();
        MyAdapter myAdapter = new MyAdapter(getContext(), strings);
        listview.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        sousuo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBackInterface != null) {
                    String keywords = select.getText().toString();
                    callBackInterface.onSearchClick(keywords);
                    dao.addData(keywords);
                    //将数据添加到listview
                    list = dao.queryData();
                    MyAdapter myAdapter = new MyAdapter(getContext(), list);
                    listview.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                }
            }
        });
        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBackInterface != null) {
                    callBackInterface.onDeleteClick();
                    Dao dao = new Dao(getContext());
                    dao.deleteData();
                    List<String> strings = dao.queryData();
                    MyAdapter myAdapter = new MyAdapter(getContext(), strings);
                    listview.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                }
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = strings.get(position);
                Intent intent = new Intent(getContext(), SelectActivity.class);
                intent.putExtra("keywords", s);
                getContext().startActivity(intent);
            }
        });
    }



    public void setCallBackInterface(CallBackInterface callBackInterface) {
        this.callBackInterface = callBackInterface;
    }

    public interface CallBackInterface {

        void onSearchClick(String keywords);

        void onDeleteClick();

    }

}
