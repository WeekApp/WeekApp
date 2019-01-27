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
    //电影详情
    public static final String URL_GET_DETI = "movieApi/movie/v1/findMoviesById?movieId=%s";
    //查看电影详情
    public static final String URL_GET_MOVIEDETAILS = "movieApi/movie/v1/findMoviesDetail?movieId=%s";
    //查询影片评论
    public static final String URL_GET_PNGLUN = "movieApi/movie/v1/findAllMovieComment?movieId=%s&page=1&count=1000";
    //影片点赞
    public static final String URL_POST_DIANZAN = "movieApi/movie/v1/verify/movieCommentGreat";
    //关注影院
    public static final String URL_GET_GUANZHU = "movieApi/movie/v1/verify/followMovie?movieId=%s";
    //取消关注
    public static final String URL_GET_QUXIAOGUANZHU = "movieApi/movie/v1/verify/cancelFollowMovie?movieId=%s";
}
