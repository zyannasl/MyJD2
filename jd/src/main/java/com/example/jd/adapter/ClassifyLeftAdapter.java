package com.example.jd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.bean.FenLeiLeftBean;

import java.util.List;


public class ClassifyLeftAdapter extends RecyclerView.Adapter<ClassifyLeftAdapter.MyViewHolder> {
    private List<FenLeiLeftBean.DataBean> list;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public ClassifyLeftAdapter(List<FenLeiLeftBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_classify_left,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
       holder.title.setText(list.get(position).getName());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int pos=holder.getLayoutPosition();
               int cid=list.get(pos).getCid();
               onItemClickListener.onClick(v,cid);
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener{
        void onClick(View view,int cid);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public MyViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.classify_left_tv);
        }
    }
}
