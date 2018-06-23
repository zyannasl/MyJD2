package com.example.jd.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jd.R;
import com.example.jd.bean.UserMsgBean;
import com.example.jd.presenter.MyMsgPresenter;
import com.example.jd.view.interfaces.IMyMsgView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.io.File;
import java.util.List;

public class MyMsgActivity extends TakePhotoActivity implements View.OnClickListener, IMyMsgView {

    private ImageView image;
    private TextView nicheng;
    private TextView sex;
    private TextView brthday;
    private Button tuichu;
    private TextView name;
    private SharedPreferences sharedPreferences;
    private MyMsgPresenter myMsgPresenter;
    private PopupWindow mPopWindow;
    private TakePhoto takePhoto;
    private Uri imageUri;
    private CropOptions cropOptions;
    //获取照片的输入保存地址
    private Uri getImageCropUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg);
        initData();
        initView();
        initClick();
        //申请权限
        initPermission();

    }


    private void initData() {
        myMsgPresenter = new MyMsgPresenter(this);
//获取uid pid token
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String uid = sp.getString("uid", "");
        Log.e("xxxxx",uid);
        myMsgPresenter.userMsg(uid);
        //获取takePhoto实例
        takePhoto=getTakePhoto();
        //设置裁剪参数
        cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();

    }


    /**
     * 初始化控件
     */
    private void initView() {
        image = findViewById(R.id.mymsg_image);
        name = findViewById(R.id.mymsg_name);
        nicheng = findViewById(R.id.mymsg_nicheng);
        sex = findViewById(R.id.mymsg_sex);
        brthday = findViewById(R.id.mymsg_brthday);
        tuichu = findViewById(R.id.mymsg_unlogin);
    }

    /**
     * 初始化点击事件
     */
    private void initClick() {
        image.setOnClickListener(this);
        name.setOnClickListener(this);
        nicheng.setOnClickListener(this);
        sex.setOnClickListener(this);
        brthday.setOnClickListener(this);
        tuichu.setOnClickListener(this);

    }


    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mymsg_image:
                showPopupVindow();
                break;
            case R.id.mymsg_nicheng:
                Intent nicheng = new Intent(MyMsgActivity.this, UpdateNiCheng.class);
                startActivity(nicheng);
                break;
            case R.id.mymsg_name:
                Toast.makeText(this, "用户名不支持修改哦···", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mymsg_sex:
                Intent sex = new Intent(MyMsgActivity.this, UpdateSex.class);
                startActivity(sex);
                break;
            case R.id.mymsg_brthday:
                Intent brthday = new Intent(MyMsgActivity.this, UpdaterBrithday.class);
                startActivity(brthday);
                break;
            case R.id.mymsg_unlogin:
                sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                Intent intent = new Intent(MyMsgActivity.this, HomePagerActivity.class);
                startActivity(intent);
                break;
            case R.id.xiangji:
                imageUri = getImageCropUri();
                //拍照并裁剪
                takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
                mPopWindow.dismiss();

                break;
            case R.id.xiangce:
                imageUri = getImageCropUri();
                //从相册中选取图片并裁剪
                takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
                mPopWindow.dismiss();

                break;
            case R.id.quxiao:
                mPopWindow.dismiss();
                break;

        }
    }
    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Toast.makeText(MyMsgActivity.this, "Error:" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        String iconPath = result.getImage().getOriginalPath();
        //Toast显示图片路径
        Toast.makeText(this, "imagePath:" + iconPath, Toast.LENGTH_SHORT).show();
        //Google Glide库 用于加载图片资源
        Glide.with(this).load(iconPath).into(image);
    }



    private void showPopupVindow() {
        //设置contentView
        View contentView = LayoutInflater.from(MyMsgActivity.this).inflate(R.layout.item_popupwindow, null);
        mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        Button xiangji = contentView.findViewById(R.id.xiangji);
        Button xiangce = contentView.findViewById(R.id.xiangce);
        Button quxiao = contentView.findViewById(R.id.quxiao);
        xiangji.setOnClickListener(this);
        xiangce.setOnClickListener(this);
        quxiao.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(MyMsgActivity.this).inflate(R.layout.activity_main, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 获取用户信息
     */

    @Override
    public void userMsg(UserMsgBean userMsgBean) {
        if (userMsgBean.getData().getIcon() == null) {
            Glide.with(this).load(R.mipmap.user).into(image);
        } else {
            Glide.with(this).load(userMsgBean.getData().getIcon()).into(image);
        }

        name.setText(userMsgBean.getData().getUsername());
        if (userMsgBean.getData().getNickname() == null) {
            nicheng.setText("还没有哦");
        } else {
            nicheng.setText(userMsgBean.getData().getNickname());
        }

        if (userMsgBean.getData().getGender() == 0) {
            sex.setText("男");
        } else if (userMsgBean.getData().getGender() == 1) {
            sex.setText("女");

        } else {
            sex.setText("保密");
        }
        brthday.setText(userMsgBean.getData().getAge() + "");


    }

    /**
     * 申请相机相册权限
     */
    private void initPermission() {
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .send();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，其它的交给AndPermission吧，最后一个参数是PermissionListener。
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, listener);
    }

    //权限申请回调接口
    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if(requestCode == 100) {
                // TODO 相应代码。
                //do nothing
            }
        }
        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。

            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(MyMsgActivity.this, deniedPermissions)) {

                // 用自定义的提示语
                AndPermission.defaultSettingDialog(MyMsgActivity.this, 103)
                        .setTitle("权限申请失败")
                        .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
                        .setPositiveButton("好，去设置")
                        .show();
            }
        }
    };


    /**
     * 上传头像
     */

    @Override
    public void lodingImg() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void serverFail(String msg) {

    }
}
