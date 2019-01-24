package com.bw.movie.mvp.persenter;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;

public interface ImplPersenter {

    void requestPostBack(String url, Map<String, String> params, Class aClass);
    void requestGetBack(String url, Class aClass);
    void requestDelBack(String url, Class aClass);
    void requestPutBack(String url, Map<String, String> params, Class aClass);
    void requestPostIconBack(String url, File file, Class aClass);
    void RequestPost(String url, Map<String, RequestBody> map, Class aClass);
}
