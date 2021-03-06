package com.bw.movie.activity.useractivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.bw.movie.activity.homeactivity.HomeActivity;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.userbean.LoginBean;
import com.bw.movie.bean.userbean.RegisterBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.EncryptUtil;
import com.bw.movie.util.NetworkUtils;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterActivity extends BaseActivity {

    @BindView(R.id.register_edit_name)
    EditText mRname;
    @BindView(R.id.register_edit_date)
    EditText mRdate;
    @BindView(R.id.register_edit_email)
    EditText mRemail;
    @BindView(R.id.register_edit_number)
    EditText mRnumber;
    @BindView(R.id.register_edit_pass)
    EditText mRpass;
    @BindView(R.id.register_ok)
    Button mRegister;
    @BindView(R.id.rb_nan)
    RadioButton nan;
    @BindView(R.id.rb_nv)
    RadioButton nv;

    int sexboy;
    String number;
    String mpass;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private CustomDatePicker mDatePicker;

    @Override
    protected void initData() {
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mRname.getText().toString();
                String date = mRdate.getText().toString();
                String emial = mRemail.getText().toString();
                String pass = mRpass.getText().toString();
                number = mRnumber.getText().toString();
                //MD5进行加密
                mpass = EncryptUtil.encrypt(pass);

                if(nan.isChecked()){
                    sexboy = 1;
                }else if(nv.isChecked()){
                    sexboy = 2;
                }
                if(NetworkUtils.hasNetwork(RegisterActivity.this)) {
                    Map<String,String> map = new HashMap<>();
                    map.put("nickName", name);
                    map.put("phone", number);
                    map.put("pwd", mpass);
                    map.put("birthday", date);
                    map.put("email", emial);
                    map.put("sex", sexboy + "");
                    map.put("pwd2", mpass);
                    doNetPost(Apis.URL_POST_REGISTER, map, RegisterBean.class);
                }else{
                    ToastUtils.show(RegisterActivity.this,"当前网络不可用，请检查网络");
                }
            }
        });
    }

    //加载id
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        //储存方式
        sharedPreferences = getSharedPreferences("userName",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        initDatePicker();
        mRdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatePicker.show(mRdate.getText().toString());
            }
        });

    }

    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("1990-01-01", false);
        long endTimestamp = System.currentTimeMillis();

        mRdate.setText(DateFormatUtils.long2Str(endTimestamp, false));

        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mRdate.setText(DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(false);
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void netSuccess(Object data) {
        if(data instanceof RegisterBean){
            RegisterBean user = (RegisterBean) data;
            ToastUtils.show(this,user.getMessage());
            if(user.getStatus().equals("0000")){
                //注册成功并立马执行登录的接口
                startRequest();
            }else{
                ToastUtils.show(this,user.getMessage());
            }
        }

        if(data instanceof LoginBean){
            LoginBean user = (LoginBean) data;
            if(user.getStatus().equals("0000")){
                ToastUtils.show(this,"注册并登陆成功");
                //取出个人信息的状态值
                editor.putString("userId",user.getResult().getUserId()+"")
                        .putString("sessionId",user.getResult().getSessionId()).commit();
                startActivity(new Intent(this,HomeActivity.class));
            }
        }
    }

    private void startRequest() {
        Map<String,String> map = new HashMap<>();
        map.put("phone",number);
        map.put("pwd",mpass);
        doNetPost(Apis.URL_POST_LOGIN,map,LoginBean.class);
    }

    @Override
    protected void netFail(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatePicker.onDestroy();
    }
}
