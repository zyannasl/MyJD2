package com.example.jd.adapter;

import android.content.Context;
import android.graphics.Paint;
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

public class TuiAdapter extends RecyclerView.Adapter<TuiAdapter.MyViewHolder> {
    private List<HomeBean.TuijianBean.ListBeanX> list;
    private Context context;

    public TuiAdapter(List<HomeBean.TuijianBean.ListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.tuijian_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.price.setText(list.get(position).getPrice());
        holder.title.setText(list.get(position).getTitle());
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        holder.sdv.setImageURI(split[0]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView sdv;
        TextView title;
        TextView price;
        public MyViewHolder(View itemView) {
            super(itemView);
            sdv=itemView.findViewById(R.id.tuijian_sdv);
            title=itemView.findViewById(R.id.tuijian_title);
            price=itemView.findViewById(R.id.tuijian_price);
        }
    }
}
