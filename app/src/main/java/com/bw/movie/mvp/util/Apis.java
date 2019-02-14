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
    public static final String URL_GET_REMMOND = "movieApi/cinema/v1/findRecommendCinemas?page=1&count=10";
    //查询附近影院
    public static final String URL_GET_NEARLY = "movieApi/cinema/v1/findNearbyCinemas?longitude=116.30551391385724&latitude=40.04571807462411&page=1&count=10";
    //根据影院ID查询电影
    public static final String URL_GET_findMovieListByCinemaId = "movieApi/movie/v1/findMovieListByCinemaId";
    //根据影院ID电影Id查询电影排期
    public static final String URL_GET_findMovieScheduleList = "movieApi/movie/v1/findMovieScheduleList";
    //影片评论点赞
    public static final String URL_POST_DIANZAN = "movieApi/movie/v1/verify/movieCommentGreat?commentId=%s";
    //关注影片
    public static final String URL_GET_GUANZHU = "movieApi/movie/v1/verify/followMovie?movieId=%s";
    //取消关注影片
    public static final String URL_GET_QUXIAOGUANZHU = "movieApi/movie/v1/verify/cancelFollowMovie?movieId=%s";
    //查询用户信息
    public static final String URL_GET_QUREYMESSAGE = "movieApi/user/v1/verify/findUserHomeInfo";
    //查询用户信息
    public static final String URL_GET_USERSIGNIN = "movieApi/user/v1/verify/userSignIn";
    //查询系统消息
    public static final String URL_GET_REMIND = "movieApi/tool/v1/verify/findAllSysMsgList?page=1&count=5";
    //我的信息
    public static final String URL_GET_MYMESSAGE ="movieApi/user/v1/verify/getUserInfoByUserId";
    //电影评论点赞
    public static final String URL_POST_ZAN = "movieApi/cinema/v1/verify/cinemaCommentGreat";
    //关注影院
    public static final String URL_GET_GUANZHUYINGYUAN = "movieApi/cinema/v1/verify/followCinema";
    //取消关注影院
    public static final String URL_GET_CANCLEGUANZHUYINGYUAN = "movieApi/cinema/v1/verify/cancelFollowCinema";
    //查询电影信息明细
    public static final String URL_GET_MINGZI = "movieApi/cinema/v1/findCinemaInfo?cinemaId=%s";
    //搜索
    public static final String URL_GET_SEARCH = "movieApi/cinema/v1/findAllCinemas";
    //上传图片
    public static final String URL_POST_UPLOADHEADPIC = "movieApi/user/v1/verify/uploadHeadPic";
    //修改密码
    public static final String URL_POST_UPDATEPWD = "movieApi/user1erify/modifyUserPwd";
    //改变系统消息状态
    public static final String URL_GET_UPDATECHANGERSYS = "movieApi/tool1erifyangeSysMsgStatus";
    //添加用户对影片的评论
    public static final String URL_POST_PINGLU = "movieApi/movie/v1/verify/movieComment";
    //询影院用户评论列表
    public static final String URL_POST_FILMCOMMENT = "movieApi/cinema/v1/findAllCinemaComment?cinemaId=%s&page=1&count=10000";
    //.根据电影ID和影院ID查询电影排期列表
    public static final String URL_GET_YINGYUAN = "movieApi/movie/v1/findMovieScheduleList?cinemasId=%s&movieId=%s";
    //查询代付款
    public static final String URL_GET_QUERYMONEY = "movieApi/user1erify/findUserBuyTicketRecordList";
}
