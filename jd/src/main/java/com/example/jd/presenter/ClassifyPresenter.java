package com.example.jd.presenter;


import android.util.Log;

import com.example.jd.bean.FenLeiLeftBean;
import com.example.jd.bean.FenLeiRightBean;
import com.example.jd.modle.ClassifyModle;
import com.example.jd.view.interfaces.IClassifyView;
import com.example.mvp.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;


public class ClassifyPresenter extends BasePresenter<ClassifyModle,IClassifyView> {
    /**
     * 分类左侧列表
     */
    public void leftData(){
     model.getLeftData(new ClassifyModle.IClassifyModel() {
         @Override
         public void successLift(FenLeiLeftBean fenLeiLeftBean) {
             Log.e("presenter",fenLeiLeftBean.getData().size()+"");
             view.successLeft(fenLeiLeftBean);
         }

         @Override
         public void successRigth(FenLeiRightBean fenLeiRightBean) {

         }
     });
    }

    /**
     * 分类右侧列表
     * @param cid
     */
    public void rigthData(String cid){
        model.getRigthData(cid, new ClassifyModle.IClassifyModel() {
            @Override
            public void successLift(FenLeiLeftBean fenLeiLeftBean) {

            }

            @Override
            public void successRigth(FenLeiRightBean fenLeiRightBean) {
                List<FenLeiRightBean.DataBean> dataBean = fenLeiRightBean.getData();
                List<List<FenLeiRightBean.DataBean.ListBean>> childList =new ArrayList<>();
                for(int i=0;i<dataBean.size();i++){
                    List<FenLeiRightBean.DataBean.ListBean> list = dataBean.get(i).getList();
                    childList.add(list);
                }
                view.successRight(dataBean,childList);
            }
        });
    }

}
