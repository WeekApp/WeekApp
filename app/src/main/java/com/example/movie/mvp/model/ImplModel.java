package com.example.movie.mvp.model;



import com.example.movie.mvp.callback.MyCallBack;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;

public interface ImplModel {

    void startPostRequest(String url, Map<String, String> params, Class aClass, MyCallBack myCallBack);
    void startGetRequest(String url, Class aClass, MyCallBack myCallBack);
    void startDelRequest(String url, Class aClass, MyCallBack myCallBack);
    void startPutRequest(String url, Map<String, String> params, Class aClass, MyCallBack myCallBack);
    void startIconPostRequest(String url, File file, Class aClass, MyCallBack myCallBack);
    void startIconPost(String url, Map<String, RequestBody> map, Class aClass, MyCallBack myCallBack);
}
