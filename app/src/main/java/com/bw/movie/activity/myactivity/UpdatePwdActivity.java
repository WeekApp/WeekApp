package com.bw.movie.activity.myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.userbean.RegisterBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.EncryptUtil;
import com.bw.onlymycinema.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePwdActivity extends BaseActivity {

    @BindView(R.id.oldpwd)
    TextView oldpwd;
    @BindView(R.id.newpwd)
    TextView newpwd;
    @BindView(R.id.againnewpwd)
    TextView againnewpwd;
    @BindView(R.id.update)
    Button update;

    //初始化数据
    @Override
    protected void initData() {

    }
    //点击事件
    @OnClick(R.id.update)
    public void onViewClicked() {
        String old = oldpwd.getText().toString().trim();
        String newp = newpwd.getText().toString().trim();
        String againnew = againnewpwd.getText().toString().trim();

        String encryptold = EncryptUtil.encrypt(old);
        String encryptnew = EncryptUtil.encrypt(newp);
        String encryptagain = EncryptUtil.encrypt(againnew);
        String encrypt = EncryptUtil.encrypt("123456");
        System.out.println("aaa:"+encryptold);
        System.out.println("bbb:"+encryptnew);
        System.out.println("ccc:"+encryptagain);
        System.out.println("ddd:"+encrypt);
        Map<String,String> map=new HashMap<>();
        map.put("oldPwd",encryptold);
        map.put("newPwd",encryptnew);
        map.put("newPwd2",encryptnew);
        doNetPost(Apis.URL_POST_UPDATEPWD,map,RegisterBean.class);
    }

    //初始化控件
    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }
    //返回布局
    @Override
    protected int getLayout() {
        return R.layout.activity_update_pwd;
    }
    //请求成功
    @Override
    protected void netSuccess(Object data) {
        if (data instanceof RegisterBean){
            RegisterBean registerBean= (RegisterBean) data;
            Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, registerBean.getStatus(), Toast.LENGTH_SHORT).show();
            if (registerBean.getStatus().equals("1001")){
                startActivity(new Intent(this,MyMessageActivity.class));
                finish();
            }
        }
    }
    //请求失败
    @Override
    protected void netFail(Object data) {

    }


}
