package com.example.jd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jd.R;


import com.bumptech.glide.Glide;
import com.example.jd.bean.CarBean;
import com.example.jd.utils.MessageEvent;
import com.example.jd.utils.PriceAndCountEvent;
import com.facebook.drawee.view.SimpleDraweeView;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class CarAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<CarBean.DataBean> groupList;
    private List<List<CarBean.DataBean.ListBean>> childList;
    private final LayoutInflater inflater;


    public CarAdapter(Context context, List<CarBean.DataBean> groupList, List<List<CarBean.DataBean.ListBean>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final GroupViewHolder holder;
        if(convertView==null){
            holder=new GroupViewHolder();
            convertView = inflater.inflate(R.layout.item_car_parent,null);
            holder.cbGroup= convertView.findViewById(R.id.cb_parent);
            holder.tv_number= convertView.findViewById(R.id.tv_number);
            convertView.setTag(holder);
        }else{
            holder=(GroupViewHolder)convertView.getTag();
        }
        final CarBean.DataBean dataBean = groupList.get(groupPosition);
        holder.cbGroup.setChecked(dataBean.isChecked());
        holder.tv_number.setText(dataBean.getSellerName()+"");
        //一级checkbox
        holder.cbGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setChecked(holder.cbGroup.isChecked());
                changeChildCbState(groupPosition, holder.cbGroup.isChecked());
                EventBus.getDefault().post(compute());
                changeAllCbState(isAllGroupCbSelected());
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder holder;
        if(convertView==null){
            holder=new ChildViewHolder();
            convertView=inflater.inflate(R.layout.item_car_child,null);
            holder.cbChild=convertView.findViewById(R.id.child_cb);
            holder.image=convertView.findViewById(R.id.child_image);
            holder.title=convertView.findViewById(R.id.child_title);
            holder.price=convertView.findViewById(R.id.child_price);
            holder.del=convertView.findViewById(R.id.child_del);
            holder.jia=convertView.findViewById(R.id.child_jia);
            holder.jian=convertView.findViewById(R.id.child_jian);
            holder.num=convertView.findViewById(R.id.child_num);
            convertView.setTag(holder);
        }else{
            holder=(ChildViewHolder)convertView.getTag();
        }
        final CarBean.DataBean.ListBean listBean = childList.get(groupPosition).get(childPosition);
        holder.cbChild.setChecked(listBean.isChecked());
        holder.title.setText(listBean.getTitle());
        holder.price.setText(listBean.getPrice()+"");
        String[] split = listBean.getImages().split("\\|");
        //Glide.with(context).load(split[0]).into(holder.image);
        holder.image.setImageURI(split[0]);
        holder.num.setText(listBean.getNum()+"");

        //二级
        holder.cbChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBean.setChecked(holder.cbChild.isChecked());
                PriceAndCountEvent compute = compute();
                EventBus.getDefault().post(compute);

                if (holder.cbChild.isChecked()) {
                    //当前checkbox是选中状态
                    if (isAllChildCbSelected(groupPosition)) {
                        changGroupCbState(groupPosition, true);
                        changeAllCbState(isAllGroupCbSelected());
                    }
                } else {
                    changGroupCbState(groupPosition, false);
                    changeAllCbState(isAllGroupCbSelected());
                }
                notifyDataSetChanged();
            }
        });
        //加号
        holder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(listBean.getNum());
                holder.num.setText(++num+"");
                listBean.setNum(num+"");
                if (holder.cbChild.isChecked()) {
                    PriceAndCountEvent priceAndCountEvent = compute();
                    EventBus.getDefault().post(priceAndCountEvent);
                }
            }
        });

        //减号
         holder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(listBean.getNum());
                if (num == 1) {
                    return;
                }
                holder.num.setText(--num + "");
                listBean.setNum(num+"");
                if (holder.cbChild.isChecked()) {
                    PriceAndCountEvent priceAndCountEvent = compute();
                    EventBus.getDefault().post(priceAndCountEvent);
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupViewHolder{
        CheckBox cbGroup;
        TextView tv_number;
    }
    class ChildViewHolder{
        CheckBox cbChild;
        SimpleDraweeView image;
        TextView title;
        TextView price;
        TextView del;
        ImageView jian;
        ImageView jia;
        TextView num;
    }
    /**
     * 改变全选的状态
     *
     * @param flag
     */
    private void changeAllCbState(boolean flag) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setChecked(flag);
        EventBus.getDefault().post(messageEvent);
    }

    /**
     * 改变一级列表checkbox状态
     *
     * @param groupPosition
     */
    private void changGroupCbState(int groupPosition, boolean flag) {
        CarBean.DataBean dataBean = groupList.get(groupPosition);
        dataBean.setChecked(flag);
    }



    /**
     * 改变二级列表checkbox状态
     *
     * @param groupPosition
     * @param flag
     */
    private void changeChildCbState(int groupPosition, boolean flag) {
        List<CarBean.DataBean.ListBean> datasBeen = childList.get(groupPosition);
        for (int i = 0; i < datasBeen.size(); i++) {
            CarBean.DataBean.ListBean datasBean = datasBeen.get(i);
            datasBean.setChecked(flag);
        }
    }

    /**
     * 判断一级列表是否全部选中
     *
     * @return
     */
    private boolean isAllGroupCbSelected() {
        for (int i = 0; i < groupList.size(); i++) {
            CarBean.DataBean dataBean = groupList.get(i);
            if (!dataBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断二级列表是否全部选中
     *
     * @param groupPosition
     * @return
     */
    private boolean isAllChildCbSelected(int groupPosition) {
        List<CarBean.DataBean.ListBean> datasBeen = childList.get(groupPosition);
        for (int i = 0; i < datasBeen.size(); i++) {
            CarBean.DataBean.ListBean datasBean = datasBeen.get(i);
            if (!datasBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算列表中，选中的钱和数量
     */
    private PriceAndCountEvent compute() {
        int count = 0;
        double price = 0;
        for (int i = 0; i < childList.size(); i++) {
            List<CarBean.DataBean.ListBean> datasBeen = childList.get(i);
            for (int j = 0; j < datasBeen.size(); j++) {
                CarBean.DataBean.ListBean datasBean = datasBeen.get(j);
                if (datasBean.isChecked()) {
                    price +=  Double.parseDouble(datasBean.getNum())*  Double.parseDouble(datasBean.getPrice());
                    count +=  Integer.parseInt(datasBean.getNum());
                }
            }
        }
        PriceAndCountEvent priceAndCountEvent = new PriceAndCountEvent();
        priceAndCountEvent.setCount(count+"");
        priceAndCountEvent.setPrice(price+"");
        return priceAndCountEvent;
    }


    /**
     * 设置全选、反选
     *
     * @param flag
     */
    public void changeAllListCbState(boolean flag) {
        for (int i = 0; i < groupList.size(); i++) {
            changGroupCbState(i, flag);
            changeChildCbState(i, flag);
        }
        EventBus.getDefault().post(compute());
        notifyDataSetChanged();
    }
}
