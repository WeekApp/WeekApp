package com.bw.movie.util;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author(赵颖冰)
 * date:2019/1/28
 */
public class RxPartMapUtils {

    public static RequestBody toRequestBodyOfImage(File pFile){

        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), pFile);
        return fileBody;
    }

}
