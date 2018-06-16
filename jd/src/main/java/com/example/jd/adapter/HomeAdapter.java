package com.example.jd.adapter;

;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.jd.R;
import com.example.jd.bean.HomeBean;
import com.example.jd.utils.GlideImageLoader;
import com.example.jd.utils.RetrofitUtils;
import com.example.jd.view.activity.QueryActivity;
import com.youth.banner.Banner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeBean.BannerBean> bannerList;
    private List<HomeBean.FenleiBean> fenleiList;
    private List<HomeBean.MiaoshaBean.ListBean> miaoshaList;
    private List<HomeBean.TuijianBean.ListBeanX> tuijianList;
    private Context context;
    private final int BANNER = 1;
    private final int JIU = 2;
    private final int MIAO = 4;
    private final int TUI = 5;
    private final int KUAI = 3;



    public HomeAdapter(List<HomeBean.BannerBean> bannerList, List<HomeBean.FenleiBean> fenleiList, List<HomeBean.MiaoshaBean.ListBean> miaoshaList, List<HomeBean.TuijianBean.ListBeanX> tuijianList, Context context) {
        this.bannerList = bannerList;
        this.fenleiList = fenleiList;
        this.miaoshaList = miaoshaList;
        this.tuijianList = tuijianList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            View view = LayoutInflater.from(context).inflate(R.layout.home_item_banner, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == JIU) {
            View view2 = LayoutInflater.from(context).inflate(R.layout.home_item_jiugongge, parent, false);
            return new ViewHolder2(view2);
        } else if (viewType == KUAI) {
            View view3 = LayoutInflater.from(context).inflate(R.layout.home_item_paomadeng, parent, false);
            return new ViewHolder3(view3);
        } else if (viewType == MIAO) {
            View view4 = LayoutInflater.from(context).inflate(R.layout.home_item_miaosha, parent, false);
            return new ViewHolder4(view4);
        } else {
            View view5 = LayoutInflater.from(context).inflate(R.layout.home_item_tuijian, parent, false);
            return new ViewHolder5(view5);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == BANNER) {
            ArrayList<String> icon = new ArrayList<>();
            for (int i = 0; i < bannerList.size(); i++) {
                icon.add(bannerList.get(i).getIcon());
            }
            ((ViewHolder1) holder).banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            ((ViewHolder1) holder).banner.setImages(icon);
            //banner设置方法全部调用完毕时最后调用
            ((ViewHolder1) holder).banner.start();
            ((ViewHolder1)holder).selectIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, QueryActivity.class);
                    context.startActivity(intent);
                }
            });
        } else if (viewType == JIU) {
            JiuAdapter jiuAdapter = new JiuAdapter(fenleiList, context);
            ((ViewHolder2) holder).jiugongge.setLayoutManager(new GridLayoutManager(context, 2, LinearLayoutManager.HORIZONTAL, false));
            ((ViewHolder2) holder).jiugongge.setAdapter(jiuAdapter);
        } else if (viewType == KUAI) {
            ((ViewHolder3) holder).filpper.addView(View.inflate(context, R.layout.item_paomadeng, null));
        } else if (viewType == MIAO) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date curDate = new Date(System.currentTimeMillis());
            String format = df.format(curDate);
            StringBuffer buffer = new StringBuffer();
            String substring = format.substring(0, 11);
            buffer.append(substring);
            Log.d("ccc", substring);
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour % 2 == 0) {
                ((ViewHolder4) holder).miaosha_time.setText(hour + "点场");
                buffer.append((hour + 2));
                buffer.append(":00:00");
            } else {
                ((ViewHolder4) holder).miaosha_time.setText((hour - 1) + "点场");
                buffer.append((hour + 1));
                buffer.append(":00:00");
            }
            String totime = buffer.toString();
            try {
                java.util.Date date = df.parse(totime);
                java.util.Date date1 = df.parse(format);
                long defferenttime = date.getTime() - date1.getTime();
                long days = defferenttime / (1000 * 60 * 60 * 24);
                long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                long seconds = defferenttime % 60000;
                long second = Math.round((float) seconds / 1000);
                ((ViewHolder4) holder).miaosha_shi.setText("0" + hours + "");
                if (minute >= 10) {
                    ((ViewHolder4) holder).miaosha_minter.setText(minute + "");
                } else {
                    ((ViewHolder4) holder).miaosha_minter.setText("0" + minute + "");
                }
                if (second >= 10) {
                    ((ViewHolder4) holder).miaosha_second.setText(second + "");
                } else {
                    ((ViewHolder4) holder).miaosha_second.setText("0" + second + "");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            MiaoAdapter miaoAdapter = new MiaoAdapter(miaoshaList, context);
            ((ViewHolder4) holder).rmiaosha.setLayoutManager(new GridLayoutManager(context, 4, LinearLayoutManager.VERTICAL, false));
            ((ViewHolder4) holder).rmiaosha.setAdapter(miaoAdapter);
        } else if (viewType == TUI) {
            TuiAdapter tuiAdapter = new TuiAdapter(tuijianList, context);
            ((ViewHolder5) holder).tuijian.setLayoutManager(new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false));
            ((ViewHolder5) holder).tuijian.setAdapter(tuiAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER;
        } else if (position == 1) {
            return JIU;
        } else if (position == 3) {
            return MIAO;
        } else if (position == 2) {
            return KUAI;
        } else if (position == 4) {
            return TUI;
        }
        return 5;
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        private Banner banner;
        private ImageView saoyisao;
        private ImageView selectIv;
        private ImageView msg;

        public ViewHolder1(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.home_item_banner);
            saoyisao =itemView. findViewById(R.id.sao_iv);
            selectIv = itemView.findViewById(R.id.select_iv);
            msg = itemView.findViewById(R.id.msg_iv);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        private RecyclerView jiugongge;

        public ViewHolder2(View itemView) {
            super(itemView);
            jiugongge = itemView.findViewById(R.id.home_item_jiugongge);
        }
    }

    class ViewHolder3 extends RecyclerView.ViewHolder {

        private ViewFlipper filpper;

        public ViewHolder3(View itemView) {
            super(itemView);
            filpper = itemView.findViewById(R.id.view_filpper);

        }
    }

    class ViewHolder4 extends RecyclerView.ViewHolder {
        private TextView miaosha_time;
        private TextView miaosha_shi;
        private TextView miaosha_minter;
        private TextView miaosha_second;
        //秒杀RecyclerView
        private RecyclerView rmiaosha;

        public ViewHolder4(View itemView) {
            super(itemView);
            miaosha_time = itemView.findViewById(R.id.tv_miaosha_time);
            miaosha_shi = itemView.findViewById(R.id.tv_miaosha_shi);
            miaosha_minter = itemView.findViewById(R.id.tv_miaosha_minter);
            miaosha_second = itemView.findViewById(R.id.tv_miaosha_second);
            rmiaosha = itemView.findViewById(R.id.home_miaosha_rlv);
        }
    }

    class ViewHolder5 extends RecyclerView.ViewHolder {
        private RecyclerView tuijian;

        public ViewHolder5(View itemView) {
            super(itemView);
            tuijian = itemView.findViewById(R.id.tuijian_rlv);
        }
    }
}
