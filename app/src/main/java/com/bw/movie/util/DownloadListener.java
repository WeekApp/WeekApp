package com.bw.movie.util;

/**
 * date:2019.2.13
 * author:赵颖冰(lenovo)
 * function:
 *
 *  断点续传的方法
 */
public  interface DownloadListener {
    void startDownload();

    void pauseDownload();

    void finishDownload(String path);

    void downloadProgress(long progress);
}