package com.example.jd;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jd.view.activity.HomePagerActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_iv)
    ImageView iv;

    private TextView tv;

    private int num = 3;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                int t = (Integer) msg.obj;
                if (t > 0) {
                    tv.setText(t + "s");
                } else {
                    Intent intent = new Intent(MainActivity.this, HomePagerActivity.class);
                    startActivity(intent);
                    finish();
                    timer.cancel();
                }
            }
        }
    };
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.main_tv);
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                num--;
                Message msg = Message.obtain();
                msg.what = 0;
                msg.obj = num;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(task, 1000, 1000);
    }


}
