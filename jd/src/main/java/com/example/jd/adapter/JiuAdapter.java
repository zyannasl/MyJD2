package com.example.jd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.bean.HomeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class JiuAdapter extends RecyclerView.Adapter<JiuAdapter.MyViewHolder> {
    private List<HomeBean.FenleiBean> list;
    private Context context;

    public JiuAdapter(List<HomeBean.FenleiBean> list, Context context) {
        this.list=list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.jiugongge_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getName());
        holder.sdv.setImageURI(list.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView sdv;
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.jiugongge_sdv);
            tv = itemView.findViewById(R.id.jiugongge_tv);
        }
    }
}
