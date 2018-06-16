package com.example.jd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.bean.FenLeiRightBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ClassifyChildViewAdapter extends RecyclerView.Adapter<ClassifyChildViewAdapter.MyViewHolder> {
    private List<FenLeiRightBean.DataBean.ListBean> childList;
    private Context context;

    public ClassifyChildViewAdapter(List<FenLeiRightBean.DataBean.ListBean> childList, Context context) {
        this.childList = childList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_classify_child_rlv,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.child_sdv.setImageURI(childList.get(position).getIcon());
        holder.child_tv.setText(childList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView child_sdv;
        private final TextView child_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            child_sdv = itemView.findViewById(R.id.child_sdv);
            child_tv = itemView.findViewById(R.id.child_tv);
        }
    }
}
