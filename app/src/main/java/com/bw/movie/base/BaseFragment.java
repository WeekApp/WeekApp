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
import com.bw.movie.util.NetworkUtils;
import com.bw.movie.util.ToastUtils;

import java.util.Map;

import retrofit2.http.HEAD;

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
        if(NetworkUtils.hasNetwork(getActivity())) {
            mPersenter.requestGetBack(url, aClass);
        }else{
            ToastUtils.show(getActivity(),"小飞");
        }
    }

    protected void doNetPost(String url, Map<String,String> map,Class aClass){

        if(NetworkUtils.hasNetwork(getActivity())) {
            mPersenter.requestPostBack(url,map,aClass);
        }else{
            ToastUtils.show(getActivity(),"小飞");
        }
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
