package com.example.movie.mvp.persenter;


import com.example.movie.mvp.callback.MyCallBack;
import com.example.movie.mvp.model.Imodel;
import com.example.movie.mvp.view.IView;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;

public class IPersenter implements ImplPersenter{

    IView mIView;
    Imodel mImodel;

    public IPersenter(IView iView){
        mIView = iView;
        mImodel = new Imodel();
    }

    @Override
    public void requestPostBack(String url, Map<String, String> params, Class aClass) {
        mImodel.startPostRequest(url, params, aClass, new MyCallBack() {
            @Override
            public void setSuccess(Object data) {
                mIView.showRequest(data);
            }

            @Override
            public void setError(Object error) {
                mIView.showError(error);
            }
        });
    }

    @Override
    public void requestGetBack(String url, Class aClass) {
        mImodel.startGetRequest(url, aClass, new MyCallBack() {
            @Override
            public void setSuccess(Object data) {
                mIView.showRequest(data);
            }

            @Override
            public void setError(Object error) {

            }
        });
    }

    @Override
    public void requestDelBack(String url, Class aClass) {
        mImodel.startDelRequest(url, aClass, new MyCallBack() {
            @Override
            public void setSuccess(Object data) {
                mIView.showRequest(data);
            }

            @Override
            public void setError(Object error) {
                mIView.showError(error);
            }
        });
    }

    @Override
    public void requestPutBack(String url, Map<String, String> params, Class aClass) {
        mImodel.startPutRequest(url, params, aClass, new MyCallBack() {
            @Override
            public void setSuccess(Object data) {
                mIView.showRequest(data);
            }

            @Override
            public void setError(Object error) {
                mIView.showError(error);
            }
        });
    }

    @Override
    public void requestPostIconBack(String url, File file, Class aClass) {
        mImodel.startIconPostRequest(url, file, aClass, new MyCallBack() {
            @Override
            public void setSuccess(Object data) {
                mIView.showRequest(data);
            }

            @Override
            public void setError(Object error) {
                mIView.showError(error);
            }
        });
    }

    @Override
    public void RequestPost(String url, Map<String, RequestBody> map, Class aClass) {
        mImodel.startIconPost(url, map, aClass, new MyCallBack() {
            @Override
            public void setSuccess(Object data) {
                mIView.showRequest(data);
            }

            @Override
            public void setError(Object error) {
                mIView.showError(error);
            }
        });
    }

    //解决内存泄露
    public void onDatch(){
        if(mIView!=null)
        {
            mIView=null;
        }
        if(mImodel!=null)
        {
            mImodel=null;
        }
    }
}
