package com.bw.movie.util;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author(张渊卓)
 * date:2019/1/14
 */
public class RxPartMapUtils {

    public static RequestBody toRequestBodyOfImage(File pFile){

        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), pFile);
        return fileBody;
    }

}
