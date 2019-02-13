package com.bw.movie.util;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;


import okhttp3.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * date:2019.2.13
 * author:赵颖冰(lenovo)
 * function:
 *
 *  断点续传工具类
 */
public class DownloadUtils {
    private static final String TAG = "DownloadUtils";
    private static volatile DownloadUtils instance;
    private File file;
    private String filePath;

    private OkHttpClient client;
    private File downloadFile;
    private long startPosition;
    private Call call;

    private DownloadUtils() {
        client = new OkHttpClient();
    }

    private DownloadListener listener;

    public void setListener(DownloadListener listener) {
        this.listener = listener;
    }

    /**
     * 初始化下载父路径
     *
     * @param path
     */
    public void initDownload(String path) {
        file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            file.mkdir();
        }
        filePath = file.getAbsolutePath();
        Log.i(TAG, "initDownload: " + filePath);
    }

    public static DownloadUtils getInstance() {
        if (null == instance) {
            synchronized (DownloadUtils.class) {
                if (instance == null) {
                    instance = new DownloadUtils();
                }
            }
        }
        return instance;
    }

    public void startDownload(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }


        if (url.contains(".")) {
            String typeName = url.substring(url.lastIndexOf(".") + 1);
            if (url.contains("/")) {
                String name = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
                String fn = name + "." + typeName;

                downloadFile = new File(file, fn);
            }
        }
        startPosition = 0;
        if (downloadFile.exists()) {
            startPosition = downloadFile.length();
        }

        Request request = new Request.Builder()
                .addHeader("RANGE", "bytes=" + startPosition + "-")
                .url(url)
                .build();

        call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                listener.startDownload();

                ResponseBody body = (ResponseBody) response.body();
                long totalLength = body.contentLength() + startPosition;
                Log.i(TAG, "totalLength: " + totalLength + "----");
                InputStream is = body.byteStream();
                byte[] buf = new byte[2048];
                int length = 0;
                long totalNum = startPosition;
                RandomAccessFile raf = null;
                try {
                    raf = new RandomAccessFile(downloadFile, "rw");
                    raf.seek(totalNum);
                    while ((length = is.read(buf, 0, buf.length)) != -1) {
                        raf.write(buf, 0, length);
                        totalNum += length;
                        listener.downloadProgress(totalNum * 100 / totalLength);

                    }
                    Log.i(TAG, "totalNum==" + totalNum + "---");
                    listener.finishDownload(downloadFile.getAbsolutePath());
                    body.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }







    public void pauseDownload() {
        listener.pauseDownload();
        if (call != null && call.isExecuted()) {
            call.cancel();
        }
    }
}

