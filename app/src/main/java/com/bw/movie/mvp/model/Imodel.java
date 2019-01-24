package com.bw.movie.mvp.model;

import com.bw.movie.mvp.callback.MyCallBack;
import com.bw.movie.mvp.util.RetrofitUtils;
import com.google.gson.Gson;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;

public class Imodel implements ImplModel{

    @Override
    public void startPostRequest(String url, Map<String, String> params, final Class aClass, final MyCallBack myCallBack) {
       RetrofitUtils.getInstance().post(url, params, new RetrofitUtils.HttpCallBack() {
           @Override
           public void onSuccess(String data) {
               try {
                   Gson gson=new Gson();
                   Object o = gson.fromJson(data, aClass);
                   myCallBack.setSuccess(o);
               }catch (Exception e){
                   e.printStackTrace();
                   myCallBack.setError(e.getMessage());
               }
           }

           @Override
           public void onFail(String error) {
                    myCallBack.setError(error);
           }
       });
    }

    @Override
    public void startGetRequest(String url, final Class aClass, final MyCallBack myCallBack) {
        RetrofitUtils.getInstance().get(url, new RetrofitUtils.HttpCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    Gson gson=new Gson();
                    Object o = gson.fromJson(data,aClass);
                    myCallBack.setSuccess(o);
                }catch (Exception e){
                    e.printStackTrace();
                    myCallBack.setError(e.getMessage());
                }
            }

            @Override
            public void onFail(String error) {
                myCallBack.setError(error);
            }
        });
    }

    @Override
    public void startDelRequest(String url, final Class aClass,final MyCallBack myCallBack) {
        RetrofitUtils.getInstance().del(url, new RetrofitUtils.HttpCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    Gson gson=new Gson();
                    Object o = gson.fromJson(data,aClass);
                    myCallBack.setSuccess(o);
                }catch (Exception e){
                    e.printStackTrace();
                    myCallBack.setError(e.getMessage());
                }
            }

            @Override
            public void onFail(String error) {
                myCallBack.setError(error);
            }
        });
    }

    @Override
    public void startPutRequest(String url, Map<String, String> params, final Class aClass, final MyCallBack myCallBack) {
        RetrofitUtils.getInstance().put(url, params, new RetrofitUtils.HttpCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    Gson gson=new Gson();
                    Object o = gson.fromJson(data,aClass);
                    myCallBack.setSuccess(o);
                }catch (Exception e){
                    e.printStackTrace();
                    myCallBack.setError(e.getMessage());
                }
            }

            @Override
            public void onFail(String error) {
                myCallBack.setError(error);
            }
        });
    }

    @Override
    public void startIconPostRequest(String url, File file, Class aClass, MyCallBack myCallBack) {

    }

    @Override
    public void startIconPost(String url, Map<String, RequestBody> map,final Class aClass,final MyCallBack myCallBack) {
        RetrofitUtils.getInstance().postFormBody(url, map, new RetrofitUtils.HttpCallBack() {
            @Override
            public void onSuccess(String data) {
                try {
                    Gson gson=new Gson();
                    Object o = gson.fromJson(data,aClass);
                    myCallBack.setSuccess(o);
                }catch (Exception e){
                    e.printStackTrace();
                    myCallBack.setError(e.getMessage());
                }
            }

            @Override
            public void onFail(String error) {
                myCallBack.setError(error);
            }
        });
    }
}