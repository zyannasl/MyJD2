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

public class MiaoAdapter extends RecyclerView.Adapter<MiaoAdapter.MyViewHolder> {
    private List<HomeBean.MiaoshaBean.ListBean> list;
    private Context context;

    public MiaoAdapter(List<HomeBean.MiaoshaBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.miaosha_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.price.setText(list.get(position).getPrice());
        holder.bargainPrice.setText(list.get(position).getBargainPrice());
        holder.bargainPrice.setPaintFlags(holder.bargainPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        holder.sdv.setImageURI(split[0]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView sdv;
        private TextView price;
        private TextView bargainPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.miaosha_sdv);
            price = itemView.findViewById(R.id.miaoisha_price);
            bargainPrice = itemView.findViewById(R.id.miaosha_bargainPrice);

        }
    }
}
