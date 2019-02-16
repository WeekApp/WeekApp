package com.bw.movie.activity.myactivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.mybean.VersionBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.DownloadListener;
import com.bw.movie.util.DownloadUtils;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class VersionActivity  extends BaseActivity implements View.OnClickListener {

    private TextView tv_pro;
    private ProgressBar progressBar;
    private Button btn_start;
    private Button btn_pause;
    private String downloadUrll  ;

    @Override
    protected void initData() {
        doNetGet(Apis.URL_GET_VERSION,VersionBean.class);
    }


    @Override
    protected void initView() {

        permission();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        tv_pro = findViewById(R.id.tv_pro);
        progressBar = findViewById(R.id.progressbar);
        btn_start = findViewById(R.id.start);
        btn_pause = findViewById(R.id.pause);
        btn_start.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File storageDirectory = Environment.getExternalStorageDirectory();
            String absolutePath = storageDirectory.getAbsolutePath();
            final String path = absolutePath + "/Download/";
            Log.i("zzz", "下载路径 " + path);
            DownloadUtils.getInstance().initDownload(path);

            DownloadUtils.getInstance().setListener(new DownloadListener() {


                @Override
                public void startDownload() {

                }

                @Override
                public void pauseDownload() {

                }

                @Override
                public void finishDownload(String path) {

                }

                @Override
                public void downloadProgress(final long progress) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_pro.setText((int)progress + "%");
                            progressBar.setProgress((int) progress);
                        }
                    });

                }
            });
        }
    }

    //返回布局
    @Override
    protected int getLayout() {
        return R.layout.activity_version;
    }
    //请求成功
    @Override
    protected void netSuccess(Object data) {
        if (data instanceof VersionBean){
            VersionBean versionBean= (VersionBean) data;

            if (versionBean.getFlag()==1) {
                String downloadUrl = versionBean.getDownloadUrl();
                downloadUrll = downloadUrl;
            }
        }
    }
    //请求失败
    @Override
    protected void netFail(Object data) {

    }



    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                DownloadUtils.getInstance().startDownload(downloadUrll);
                break;
            case R.id.pause:
                DownloadUtils.getInstance().pauseDownload();
                break;
        }
    }


    //动态权限
    private void permission() {
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
            ActivityCompat.requestPermissions(VersionActivity.this, mPermissionList, 123);
        }

    }
}