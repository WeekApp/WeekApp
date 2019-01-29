package com.bw.movie.activity.myactivity;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.mybean.MyMessageBean;
import com.bw.movie.bean.mybean.UpdateHeafpicBean;

import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.RxPartMapUtils;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class MyMessageActivity extends BaseActivity {
    @BindView(R.id.myfragment_usermessage)
    TextView myfragmentUsermessage;
    @BindView(R.id.mymessage_headpic)
    SimpleDraweeView mymessageHeadpic;
    @BindView(R.id.mymessage_nickname)
    TextView mymessageNickname;
    @BindView(R.id.mymessage_sex)
    TextView mymessageSex;
    @BindView(R.id.mymessage_birthday)
    TextView mymessageBirthday;
    @BindView(R.id.mymessage_mobile)
    TextView mymessageMobile;
    @BindView(R.id.mymessage_email)
    TextView mymessageEmail;
    @BindView(R.id.mymessage_reset)
    SimpleDraweeView mymessageReset;
    @BindView(R.id.mymessage_back)
    SimpleDraweeView mymessageBack;
    private String path = Environment.getExternalStorageDirectory()+"/ert.png";



    //初始化数据
    @Override
    protected void initData() {
        doNetGet(Apis.URL_GET_MYMESSAGE,MyMessageBean.class);
        //点击事件
        initCilck();
    }




    private void initCilck() {

        //修改头像
        updateHeadpic();

        //修改密码
        mymessageReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updatePwd();
            }
        });

        //返回
        mymessageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void updatePwd() {
        startActivity(new Intent(MyMessageActivity.this,UpdatePwdActivity.class));
    }


    //修改头像
    private void updateHeadpic() {
        mymessageHeadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyMessageActivity.this);
                builder.setTitle("图片");
                builder.setMessage("上传的途径");
                builder.setCancelable(true);

                builder.setPositiveButton("相机", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(path)));
                        startActivityForResult(intent,1000);
                    }
                });
                builder.setNegativeButton("相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent,2000);

                    }
                });
                builder.show();
            }
        });
    }
    //返回重写
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode != RESULT_CANCELED){

            if (requestCode == 2000 & resultCode == RESULT_OK) {
                Uri uri = data.getData();
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(uri, "image/*");
                intent.putExtra("crop", true);
                intent.putExtra("aspactX", 1);
                intent.putExtra("aspactY", 1);
                intent.putExtra("outputX", 250);
                intent.putExtra("outputY", 250);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, 3000);
            }else if (requestCode==1000 & resultCode==RESULT_OK){

                Intent intent=new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(Uri.fromFile(new File(path)),"image/*");
                intent.putExtra("crop",true);
                intent.putExtra("aspactX",1);
                intent.putExtra("aspactY",1);
                intent.putExtra("outputX",250);
                intent.putExtra("outputY",250);
                intent.putExtra("return-data",true);
                startActivityForResult(intent,3000);
            }

            if (requestCode == 3000 & resultCode == RESULT_OK) {

                Bitmap bitmap = data.getParcelableExtra("data");

                String path=saveImage("crop", bitmap);
                //修改上传图片
                initData2(path);


                mymessageHeadpic.setImageBitmap(bitmap);
            }

        }


    }

    private void initData2(String path) {
        Map<String, RequestBody> map=new HashMap<>();
        File file=new File(path);
        if(file.exists()){
            RequestBody requestBody = RxPartMapUtils.toRequestBodyOfImage(file);
            map.put("image"+"\";filename=\""+file.getName(),requestBody);
        }

        doNetPostFile(Apis.URL_POST_UPLOADHEADPIC,map,UpdateHeafpicBean.class);

    }

    //保存图片
    private String saveImage(String crop, Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = crop + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    //初始化控件
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        permission();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
  
    }

    private void permission() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS,
                    Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(MyMessageActivity.this, mPermissionList, 123);
        }

    }

    //返回布局
    @Override
    protected int getLayout() {
        return R.layout.activity_my_message;
    }

    //请求成功
    @Override
    protected void netSuccess(Object data) {
        if (data instanceof MyMessageBean){
            MyMessageBean myMessageBean= (MyMessageBean) data;

            //控件赋值
            initMessage(myMessageBean);


        } else if (data instanceof UpdateHeafpicBean) {
            UpdateHeafpicBean updateHeafpicBean= (UpdateHeafpicBean) data;
            ToastUtils.show(this,updateHeafpicBean.getMessage());
            initData();
        }
    }

    //控件赋值
    private void initMessage(MyMessageBean myMessageBean) {
        long birthday = myMessageBean.getResult().getBirthday();
        String headPic = myMessageBean.getResult().getHeadPic();
        String nickName = myMessageBean.getResult().getNickName();
        String phone = myMessageBean.getResult().getPhone();
        int sex = myMessageBean.getResult().getSex();
        String email = myMessageBean.getResult().getEmail();
        mymessageHeadpic.setImageURI(headPic);
        mymessageNickname.setText(nickName);
        if (sex==1){
            String s="男";
            mymessageSex.setText(s);
        }else{
            String s="女";
            mymessageSex.setText(s);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd", Locale.CHINA);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String time = dateFormat.format(new Date(birthday));
        mymessageBirthday.setText(time);
        mymessageMobile.setText(phone);
        mymessageEmail.setText(email);
    }

    //请求失败
    @Override
    protected void netFail(Object data) {

    }

}
