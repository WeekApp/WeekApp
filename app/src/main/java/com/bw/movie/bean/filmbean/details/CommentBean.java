package com.bw.movie.bean.filmbean.details;

import java.util.List;

public class CommentBean {


    /**
     * result : [{"commentContent":"","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-01-24/20190124155351.jpg","commentId":1970,"commentTime":1548315076000,"commentUserId":1737,"commentUserName":"lsq","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":1},{"commentContent":"去你妹","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-01-24/20190124154714.jpg","commentId":1943,"commentTime":1547878593000,"commentUserId":1555,"commentUserName":"666qunima","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"%E7%AB%A5%E7%AB%A5%E6%84%9F%E5%86%92%E4%BA%86%EF%BC%8C%E5%A4%A7%E5%A4%B4%E5%A5%BD%E5%BF%83%E7%96%BC","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-01-23/20190123212341.png","commentId":1926,"commentTime":1547793970000,"commentUserId":1651,"commentUserName":"苗苗","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"%E7%AB%A5%E7%AB%A5%E6%84%9F%E5%86%92%E4%BA%86%EF%BC%8C%E5%A4%A7%E5%A4%B4%E5%A5%BD%E5%BF%83%E7%96%BC","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-01-23/20190123212341.png","commentId":1925,"commentTime":1547793967000,"commentUserId":1651,"commentUserName":"苗苗","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"单点","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":1902,"commentTime":1547783535000,"commentUserId":1695,"commentUserName":"枫叶","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"来了老弟","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2018-12-26/20181226100504.jpg","commentId":1857,"commentTime":1545790432000,"commentUserId":1558,"commentUserName":"zzz","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":2},{"commentContent":"啊啊啊","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2018-12-26/20181226093459.png","commentId":1330,"commentTime":1543904566000,"commentUserId":1451,"commentUserName":"网吧","greatNum":3,"hotComment":0,"isGreat":0,"replyNum":5},{"commentContent":"有没有5k的朋友","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2018-12-26/20181226093459.png","commentId":1279,"commentTime":1543883420000,"commentUserId":1451,"commentUserName":"网吧","greatNum":3,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"来了老弟这可是企业级接口","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2018-12-10/20181210114835.png","commentId":1267,"commentTime":1543817187000,"commentUserId":1385,"commentUserName":"来了！老弟！！","greatNum":7,"hotComment":0,"isGreat":0,"replyNum":2},{"commentContent":"大萨达封","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2018-12-05/20181205141302.png","commentId":1256,"commentTime":1543798340000,"commentUserId":1418,"commentUserName":"小黑","greatNum":2,"hotComment":0,"isGreat":0,"replyNum":1}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commentContent :
         * commentHeadPic : http://mobile.bwstudent.com/images/movie/head_pic/2019-01-24/20190124155351.jpg
         * commentId : 1970
         * commentTime : 1548315076000
         * commentUserId : 1737
         * commentUserName : lsq
         * greatNum : 1
         * hotComment : 0
         * isGreat : 0
         * replyNum : 1
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;
        private int replyNum;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }
    }
}
