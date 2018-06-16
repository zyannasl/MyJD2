package com.example.jd.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.bean.FenLeiRightBean;

import java.util.List;

public class ClassifyRigthAdatpter extends BaseExpandableListAdapter{
    private Context context;
    private List<FenLeiRightBean.DataBean> groupList;
    private List<List<FenLeiRightBean.DataBean.ListBean>> childList;
    private final LayoutInflater inflater;


    public ClassifyRigthAdatpter(Context context, List<FenLeiRightBean.DataBean> groupList, List<List<FenLeiRightBean.DataBean.ListBean>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;

       if(convertView==null){
           holder =new GroupViewHolder();
           convertView =inflater.inflate(R.layout.item_classify_grooup,null);
           holder.classify_group_tv= convertView.findViewById(R.id.classify_group_tv);
           convertView.setTag(holder);
       }else{
          holder=(GroupViewHolder)convertView.getTag();
       }
       holder.classify_group_tv.setText(groupList.get(groupPosition).getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;

       if(convertView==null){
           childViewHolder = new ChildViewHolder();
           convertView = inflater.inflate(R.layout.item_classify_child, null);
           childViewHolder.classify_child_rlv= convertView.findViewById(R.id.classify_child_rlv);
           convertView.setTag(childViewHolder);
       }else{
           childViewHolder=(ChildViewHolder) convertView.getTag();
       }
        ClassifyChildViewAdapter classifyChildViewAdapter = new ClassifyChildViewAdapter(childList.get(groupPosition),context);
        childViewHolder.classify_child_rlv.setLayoutManager(new GridLayoutManager(context,3, LinearLayoutManager.VERTICAL,false));
        childViewHolder.classify_child_rlv.setAdapter(classifyChildViewAdapter);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    class GroupViewHolder{
        TextView classify_group_tv;
    }
    class ChildViewHolder{
        RecyclerView classify_child_rlv;
    }
}
