package com.bw.movie.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class App extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        initData();
    }

    private void initData() {

        //设置洗盘缓存的路径
        DiskCacheConfig config = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("images")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .build();

        //磁盘缓存的配置
        ImagePipelineConfig pipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(config)
                .build();
        Fresco.initialize(this,pipelineConfig);
    }

    public static Context getApplication(){
        return mContext;
    }
}
