package com.bw.movie.base;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.bw.movie.mvp.persenter.IPersenter;
import com.bw.movie.mvp.view.IView;
import com.bw.onlymycinema.R;

import java.util.Map;

public  abstract class BaseActivity extends FragmentActivity implements IView {

    IPersenter mPersenter;
    private AlertDialog.Builder builder;

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
        hideLoading();
        mPersenter.requestGetBack(url, aClass);
    }

    protected void doNetPost(String url, Map<String,String> map,Class aClass){
        hideLoading();
        mPersenter.requestPostBack(url,map,aClass);
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
