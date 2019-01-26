package com.bw.movie.mvp.util;

public class Apis {

    //注册
    public static final String URL_POST_REGISTER = "movieApi/user/v1/registerUser";
    //登录
    public static final String URL_POST_LOGIN = "movieApi/user/v1/login";
    //画廊轮播
    public static final String URL_GET_BANNER = "movieApi/movie/v1/findHotMovieList?page=1&count=10";
    //正在上映
    public static final String URL_GET_ING = "movieApi/movie/v1/findReleaseMovieList?page=1&count=10";
    //即将上映
    public static final String URL_GET_JIJ = "/movieApi/movie/v1/findComingSoonMovieList?page=1&count=10";
}
