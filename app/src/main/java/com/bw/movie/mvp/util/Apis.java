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
    //根据电影ID和影院ID查询电影排期列表
    public static final String URL_GET_YINGYUAN = "movieApi/movie/v1/findMovieScheduleList?cinemasId=%s&movieId=%s";
    //关注的影院
    public static final String URL_GET_QUREYMESSAGE = "movieApi/cinema/v1/verify/findCinemaPageList?page=1&count=10000";
    //查询用户关注的影片
    public static final String URL_GET_WFMEF = "movieApi/movie/v1/verify/findMoviePageList?page=1&count=10000";
    //查询用户信息
    public static final String URL_GET_QUAI = "movieApi/user/v1/verify/findUserHomeInfo";
    //评论
    public static final String URL_POST_PINGLU = "movieApi/movie/v1/verify/movieComment";
    //签到
    public static final String URL_GET_USERSIGNIN = "movieApi/user/v1/verify/userSignIn";
    //查询系统消息
    public static final String URL_GET_REMIND = "movieApi/tool/v1/verify/findAllSysMsgList?page=1&count=5";
    //我的信息
    public static final String URL_GET_MYMESSAGE ="movieApi/user/v1/verify/getUserInfoByUserId";
    //影院用户评论列表
    public static final String URL_POST_FILMCOMMENT = "movieApi/cinema/v1/findAllCinemaComment?cinemaId=%s&page=1&count=100000";
    //关注影院
    public static final String URL_GET_GUANZHUYINGYUAN = "movieApi/cinema/v1/verify/followCinema?cinemaId=%s";
    //取消关注影院
    public static final String URL_GET_CANCLEGUANZHUYINGYUAN = "movieApi/cinema/v1/verify/cancelFollowCinema?cinemaId=%s";
    //查询电影信息明细
    public static final String URL_GET_MINGZI = "movieApi/cinema/v1/findCinemaInfo?cinemaId=%s";
    //搜索
    public static final String URL_GET_SEARCH = "movieApi/cinema/v1/findAllCinemas";
    //上传图片
    public static final String URL_POST_UPLOADHEADPIC = "movieApi/user/v1/verify/uploadHeadPic";
    //修改密码
    public static final String URL_POST_UPDATEPWD = "movieApi/user/v1/verify/modifyUserPwd";
    //改变系统消息状态
    public static final String URL_GET_UPDATECHANGERSYS = "movieApi/tool/v1/verify/changeSysMsgStatus";
    //查询代付款
    public static final String URL_GET_QUERYMONEY = "movieApi/user/v1/verify/findUserBuyTicketRecordList";
    //意见反馈
    public static final String URL_GET_FEEDBACK = "movieApi/tool/v1/verify/recordFeedBack";
    //更新新版本
    public static final String URL_GET_VERSION = "movieApi/tool/v1/findNewVersion";
    //影院评论点赞
    public static final String URL_POST_ZAN = "movieApi/cinema/v1/verify/cinemaCommentGreat";
    //购票下单
    public static final String URL_SHAPE_ORDER = "movieApi/movie/v1/verify/buyMovieTicket";
    //微信登录
    public static final String LOGIN_WX_URL = "movieApi/user/v1/weChatBindingLogin";
    //支付
    public static final String _URL_PAG_MONEY = "movieApi/movie/v1/verify/pay";
    //根据用户ID查询用户信息
    public static final String URL_GET_AWFK = "movieApi/user/v1/verify/getUserInfoByUserId";
}
