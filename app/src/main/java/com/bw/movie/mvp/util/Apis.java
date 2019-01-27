package com.bw.movie.mvp.util;

public class Apis {

    //注册
    public static final String URL_POST_REGISTER = "movieApi/user/v1/registerUser";
    //登录
    public static final String URL_POST_LOGIN = "movieApi/user/v1/login";
    //画廊轮播
    public static final String URL_GET_BANNER = "movieApi/movie/v1/findHotMovieList?page=1&count=10";
    //根据id查询详情
    public static final String URL_GET_DETI = "movieApi/movie/v1/findMoviesById?movieId=%s";
    //查看电影的详情
    public static final String URL_GET_MOVIEDETAILS = "movieApi/movie/v1/findMoviesDetail?movieId=%s";
    //查看电影的评论
    public static final String URL_GET_PNGLUN = "movieApi/movie/v1/findAllMovieComment?movieId=%s&page=1&count=1000";
    //正在上映
    public static final String URL_GET_ING = "movieApi/movie/v1/findReleaseMovieList?page=1&count=10";
    //即将上映
    public static final String URL_GET_JIJ = "/movieApi/movie/v1/findComingSoonMovieList?page=1&count=10";
    //查询推荐影院
    public static final String URL_GET_REMMOND = "movieApi/cinema1/findRecommendCinemas?page=1&count=10";
    //查询附近影院
    public static final String URL_GET_NEARLY = "movieApi/cinema1/findNearbyCinemas?longitude=116.30551391385724&latitude=40.04571807462411&page=1&count=10";
    //根据影院ID查询电影
    public static final String URL_GET_findMovieListByCinemaId = "movieApi/movie1/findMovieListByCinemaId";
    //根据影院ID电影Id查询电影排期
    public static final String URL_GET_findMovieScheduleList = "movieApi/movie1/findMovieScheduleList";
    //影片评论点赞
    public static final String URL_POST_DIANZAN = "movieApi/movie/v1/verify/movieCommentGreat?commentId=%s";
    //关注影片
    public static final String URL_GET_GUANZHU = "movieApi/movie/v1/verify/followMovie?movieId=%s";
    //取消关注影片
    public static final String URL_GET_QUXIAOGUANZHU = "movieApi/movie/v1/verify/cancelFollowMovie?movieId=%s";
}
