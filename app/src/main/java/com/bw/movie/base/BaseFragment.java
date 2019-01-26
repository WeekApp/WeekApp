package com.bw.movie.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.mvp.persenter.IPersenter;
import com.bw.movie.mvp.view.IView;

import java.util.Map;

public  abstract class BaseFragment extends Fragment implements IView {

    IPersenter mPersenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getContext(),getLayout(), null);

        mPersenter = new IPersenter(this);

        initView(view);

        initData();

        return view;
    }

    //开始网络请求
    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract int getLayout();

    protected abstract void netSuccess(Object data);
    protected abstract void netFail(Object data);


    protected void doNetGet(String url, Class aClass){
        //TODO:弹出loading
        mPersenter.requestGetBack(url, aClass);
    }

    protected void doNetPost(String url, Map<String,String> map,Class aClass){
        mPersenter.requestPostBack(url,map,aClass);
    }

    @Override
    public void onDestroy() {
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
        //TODO:收起loading
    }
}
