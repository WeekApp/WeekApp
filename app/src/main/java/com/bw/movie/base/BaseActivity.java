package com.bw.movie.base;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bw.movie.mvp.persenter.IPersenter;
import com.bw.movie.mvp.view.IView;
import com.bw.movie.util.NetworkUtils;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import java.util.Map;

import okhttp3.RequestBody;

public  abstract class BaseActivity extends FragmentActivity implements IView {

    IPersenter mPersenter;
    PopupWindow yPop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mPersenter = new IPersenter(this);
        initView();
        initData();

    }

    //开始网络请求
    protected abstract void initData();

    protected abstract void initView();
    protected abstract int getLayout();

    protected abstract void netSuccess(Object data);
    protected abstract void netFail(Object data);


    protected void doNetGet(String url, Class aClass){
        //TODO:弹出loading
       // hideLoading();
        if(NetworkUtils.hasNetwork(this)) {
            mPersenter.requestGetBack(url, aClass);
        }else{
            initPop();
        }
    }

    protected void doNetPost(String url, Map<String,String> map,Class aClass){
        hideLoading();
        if(NetworkUtils.hasNetwork(this)) {
            mPersenter.requestPostBack(url,map,aClass);
        }else{
            initPop();
        }
    }

    public void initPop(){
        View view = View.inflate(this,R.layout.wuline,null);
        Button button = view.findViewById(R.id.qushezhi);
        yPop = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //设置焦点
        yPop.setFocusable(true);
        //设置是否可以触摸
        yPop.setTouchable(true);
        yPop.setBackgroundDrawable(new BitmapDrawable());
        yPop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        yPop.update();
        yPop.showAsDropDown(view, Gravity.BOTTOM,0 ,0 );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //判断手机系统的版本  即API大于10 就是3.0或以上版本
                if(android.os.Build.VERSION.SDK_INT>10){
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                }else{
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                startActivity(intent);
                yPop.dismiss();
            }
        });
    }

    protected void doNetPostFile(String url, Map<String, RequestBody> map, Class aClass){
        hideLoading();
        if(NetworkUtils.hasNetwork(this)) {
            mPersenter.RequestPost(url,map,aClass);
        }else{
            ToastUtils.show(this,"小飞");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPersenter != null){
            mPersenter.onDatch();
        }
    }

    @Override
    public void showRequest(Object data) {
        hideLoading();
        netSuccess(data);
    }

    @Override
    public void showError(Object data) {
        hideLoading();
        netFail(data);
    }

    private void hideLoading(){
       /* builder = new AlertDialog.Builder(this);
        View view = View.inflate(this,R.layout.activity_loading,null);
        builder.setView(view);
        builder.show();*/
    }
}
