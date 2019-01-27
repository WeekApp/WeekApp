package com.bw.movie.mvp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.bw.movie.app.App;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitUtils {
    private static  RetrofitUtils instance;
    private OkHttpClient client;
    private final String BASE_URL="http://mobile.bwstudent.com/";
    private BaseApis baseApis;
    //创建单例
    public static RetrofitUtils getInstance(){
        if(instance==null){
            synchronized (RetrofitUtils.class){
                instance=new RetrofitUtils();
            }
        }
        return instance;
    }
    //构造方法
    public RetrofitUtils(){
        //自定义日至拦截器
        client=new OkHttpClient.Builder()
                .writeTimeout(2000, TimeUnit.SECONDS)
                .readTimeout(2000,TimeUnit.SECONDS)
                .connectTimeout(2000,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request request = chain.request();

                        SharedPreferences sharedPreferences = App.getApplication().getSharedPreferences("userName",Context.MODE_PRIVATE);
                        String userId = sharedPreferences.getString("userId", "");
                        String sessionId = sharedPreferences.getString("sessionId","");
                        Log.i("TTTTXX",userId);
                        Log.i("TTTTXX",sessionId);
                        //重新构造方法
                        Request.Builder builder = request.newBuilder();
                        //把原来的请求参数 原样的放进去
                        builder.method(request.method(),request.body());
                        //添加特殊的id
                        if(!TextUtils.isEmpty(userId)&&!TextUtils.isEmpty(sessionId)){
                            builder.addHeader("userId",userId);
                            builder.addHeader("sessionId",sessionId);
                        }
                        builder.addHeader("ak","0110010010000");
                        builder.addHeader("Content-Type","application/x-www-form-urlencoded");
                        Request build = builder.build();
                        return chain.proceed(build);
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();
        baseApis=retrofit.create(BaseApis.class);
    }

   //get请求
    public void get(String url,HttpCallBack httpCallBack){
        baseApis.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(httpCallBack));
    }

    public void del(String url,HttpCallBack httpCallBack){
        baseApis.del(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(httpCallBack));
    }

    //put请求
    public void put(String url, Map<String,String> map,HttpCallBack httpCallBack){
        if(map==null){
            map=new HashMap<>();
        }
        baseApis.put(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(httpCallBack));
    }

    //post请求
    public void post(String url, Map<String,String> map,HttpCallBack httpCallBack){
        if(map==null){
            map=new HashMap<>();
        }
        baseApis.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(httpCallBack));
    }

    public void postFormBody(String url,Map<String,RequestBody> map,HttpCallBack httpCallBack){

        if(map==null){
            map = new HashMap<>();
        }
        baseApis.postFormBody(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(httpCallBack));
    }

    private Observer getObserver(final HttpCallBack callBack) {
        Observer observer=new Observer<ResponseBody>() {

            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if(callBack!=null){
                    callBack.onFail(e.getMessage());
                }
                Log.i("TTT",e.getMessage());
        }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String result = responseBody.string();
                    System.out.print(result);
                    if(callBack!=null){
                        callBack.onSuccess(result);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if(callBack!=null){
                        callBack.onFail(e.getMessage());
                    }
                }
            }
        };
        return observer;
    }

    public interface HttpCallBack{
        void onSuccess(String data);//成功
        void onFail(String error);//失败
    }
}
