package com.example.jd.view.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.jd.R;
import com.example.jd.adapter.ClassifyLeftAdapter;
import com.example.jd.adapter.ClassifyRigthAdatpter;
import com.example.jd.bean.FenLeiLeftBean;
import com.example.jd.bean.FenLeiRightBean;
import com.example.jd.modle.ClassifyModle;
import com.example.jd.presenter.ClassifyPresenter;
import com.example.jd.view.interfaces.IClassifyView;
import com.example.mvp.BaseFragment;
import com.example.mvp.mvp.BaseModel;

import java.util.List;


public class Classify extends BaseFragment<ClassifyPresenter> implements IClassifyView {


    private RecyclerView rlv;
    private ExpandableListView elv;


    @Override
    protected void initData() {
        Log.e("initData","5555");
       presenter.leftData();
       presenter.rigthData("1");

    }

    @Override
    protected BaseModel initModel() {
        return new ClassifyModle();
    }

    @Override
    protected ClassifyPresenter initPresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected void initView(View view) {
        rlv = view.findViewById(R.id.classify_rlv);
        elv = view.findViewById(R.id.classify_elv);

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fragment_classify;
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
    public void successLeft(FenLeiLeftBean fenLeiLeftBean) {
        ClassifyLeftAdapter classifyLeftAdapter = new ClassifyLeftAdapter(fenLeiLeftBean.getData(),getActivity());
        rlv.setLayoutManager(new GridLayoutManager(getActivity(),1, LinearLayoutManager.VERTICAL,false));
        rlv.setAdapter(classifyLeftAdapter);
        classifyLeftAdapter.setOnItemClickListener(new ClassifyLeftAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int cid) {
                Toast.makeText(getContext(), ""+cid, Toast.LENGTH_SHORT).show();
                presenter.rigthData(cid+"");
            }
        });
    }

    @Override
    public void successRight(List<FenLeiRightBean.DataBean> groupList, List<List<FenLeiRightBean.DataBean.ListBean>> childList) {
        ClassifyRigthAdatpter classifyRigthAdatpter = new ClassifyRigthAdatpter(getActivity(), groupList, childList);
        elv.setAdapter(classifyRigthAdatpter);
        elv.setGroupIndicator(null);
        //默认全部展开
        for(int i=0;i<groupList.size();i++){
            elv.expandGroup(i);
        }
    }


}
