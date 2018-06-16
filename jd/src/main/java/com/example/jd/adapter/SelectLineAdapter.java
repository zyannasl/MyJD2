package com.example.jd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jd.R;
import com.example.jd.bean.SelectBean;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;

public class SelectLineAdapter extends RecyclerView.Adapter<SelectLineAdapter.MyHolderView> {
    private Context context;
    private List<SelectBean.DataBean> list;
    private OnItemClickListener onItemClickListener;

    public SelectLineAdapter(Context context, List<SelectBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_line, parent, false);
        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolderView viewHolder, int position) {

        if (list != null) {
            if (list.get(position).getImages() != null) {
                String[] split = list.get(position).getImages().split("\\|");
               // Glide.with(context).load(split[0]).into(viewHolder.image);
                viewHolder.image.setImageURI(split[0]);
            }

            viewHolder.title.setText(list.get(position).getTitle());
            viewHolder.prices.setText(list.get(position).getPrice() + "");
            viewHolder.subhead.setText(list.get(position).getSubhead());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    int pid = list.get(pos).getPid();
                    onItemClickListener.onClick(v, pid);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public interface OnItemClickListener {
        void onClick(View view, int pid);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    class MyHolderView extends RecyclerView.ViewHolder {
        private TextView subhead;
        private TextView title;
        private SimpleDraweeView image;
        private TextView prices;

        public MyHolderView(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.select_line_iv);
            title = itemView.findViewById(R.id.select_line_title);
            prices = itemView.findViewById(R.id.select_line_prices);
            subhead = itemView.findViewById(R.id.select_line_subhead);
        }
    }
}
