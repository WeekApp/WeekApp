package com.bw.movie.base;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.mvp.persenter.IPersenter;
import com.bw.movie.mvp.view.IView;
import com.bw.movie.util.NetworkUtils;
import com.bw.movie.util.ToastUtils;

import java.util.Map;

import retrofit2.http.HEAD;
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public  abstract class BaseFragment extends Fragment implements IView {

    IPersenter mPersenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getContext(),getLayout(), null);
        permission(view);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        mPersenter = new IPersenter(this);

        initView(view);

        initData();

        return view;
    }

    private void permission(View view) {
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
            ActivityCompat.requestPermissions((Activity) view.getContext(), mPermissionList, 123);
        }
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
