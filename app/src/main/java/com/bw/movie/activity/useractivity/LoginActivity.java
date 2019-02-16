package com.bw.movie.activity.useractivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.Utils.WeiXinUtil;
import com.bw.movie.app.App;
import com.bw.movie.wxapi.WXEntryActivity;
import com.bw.movie.activity.homeactivity.HomeActivity;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.userbean.LoginBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.EncryptUtil;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_text_fast)
    TextView mRegister;
    @BindView(R.id.login_sure)
    Button login_sure;
    @BindView(R.id.login_edit_number)
    EditText mLnumber;
    @BindView(R.id.login_edit_pass)
    EditText mLpass;
    @BindView(R.id.login_cb_remaber_pass)
    CheckBox mRpass;
    @BindView(R.id.login_cb_self_login)
    CheckBox mRlogin;
    @BindView(R.id.login_icon_eye)
    ImageView mEyes;
    @BindView(R.id.disanfang_icon_wechat)
    ImageView wechat;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void initData() {
        //去登陆
        toLogin();
        //去注册
        toRegister();
        //自动登录
        initRemeberPass();
        //小眼睛
        initEyes();
        //微信登录
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (App.api == null) {
                    App.api = WXAPIFactory.createWXAPI(LoginActivity.this, App.APP_ID, true);
                }
                if (!App.api.isWXAppInstalled()) {
                    ToastUtils.show(LoginActivity.this,"您手机尚未安装微信，请安装后再登录");
                    return;
                }

                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                WeiXinUtil.reg(LoginActivity.this).sendReq(req);
                finish();


                //startActivity(new Intent(LoginActivity.this,WXEntryActivity.class));
            }
        });
    }

    private void initEyes() {

        mEyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLpass.getInputType()==129){
                    mLpass.setInputType(128);
                }else{
                    mLpass.setInputType(129);
                }
            }
        });
    }

    private void toRegister() {
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private void initRemeberPass() {
        //取出记住密码的状态值
        boolean ischeck = sharedPreferences.getBoolean("ischeck",false);
        //判断他为true就记住
        if(ischeck)
        {
            String number = sharedPreferences.getString("login_number", null);
            String pass = sharedPreferences.getString("login_pass", null);

            mLnumber.setText(number);
            mLpass.setText(pass);
            mRpass.setChecked(ischeck);
        }

        //取出自动登录的状态值
        boolean v_ischeck = sharedPreferences.getBoolean("v_ischeck",false);
        if(v_ischeck){
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }

        //勾选了自动登录 自动勾选记住密码
        mRlogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mRlogin.isChecked()){
                    mRpass.setChecked(true);
                }
            }
        });

        //当记住密码的复选框没有勾选 并且取消自动登录的复选框
        mRpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!mRpass.isChecked()){
                    mRlogin.setChecked(false);
                }
            }
        });
    }

    private void toLogin() {
        //登录点击事件
        login_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取出输入框里面的值
                String number = mLnumber.getText().toString();
                String pass = mLpass.getText().toString();
                //使用md5进行加密
                String encrypt = EncryptUtil.encrypt(pass);
                //sp记住密码
                if(mRpass.isChecked()){
                    editor.putString("login_number",number);
                    editor.putString("login_pass",pass);

                    editor.putBoolean("ischeck",true);
                    editor.commit();
                }else{
                    editor.clear();
                    editor.commit();
                }

                //记住密码
                if(mRlogin.isChecked()){
                    editor.putBoolean("v_ischeck",true);
                    editor.commit();
                }
                if(!number.equals("")&&!encrypt.equals("")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("phone", number);
                        map.put("pwd", encrypt);
                        doNetPost(Apis.URL_POST_LOGIN, map, LoginBean.class);
                } else{
                    ToastUtils.show(LoginActivity.this, "输入的内容不能为空");
                }
            }
        });
    }

    @Override
    protected void initView() {
        //绑定ButterKnife控件
        ButterKnife.bind(this);
        //储存方式
        sharedPreferences = getSharedPreferences("userName",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //加载布局
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void netSuccess(Object data) {
        if(data instanceof LoginBean){
            LoginBean user = (LoginBean) data;

            if(user.getStatus().equals("0000")){
                //取出个人信息的状态值
                editor.putString("userId",user.getResult().getUserId()+"")
                        .putString("sessionId",user.getResult().getSessionId()).commit();
                startActivity(new Intent(this,HomeActivity.class));
                finish();
            }else{
                ToastUtils.show(this,user.getMessage());
            }
        }
    }

    @Override
    protected void netFail(Object data) {

    }
}
