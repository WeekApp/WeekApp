package com.bw.movie.bean.cinemabean;

import java.util.List;

public class ConrenFilmBean {


    /**
     * result : [{"fare":0,"id":19,"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/jhen/jhen1.jpg","name":"江湖儿女","releaseTime":1537545600000,"summary":"故事起始于2001年的山西大同，模特巧巧（赵涛 饰）与出租车公司老板斌哥（廖凡 饰）是一对恋人，斌哥每天在外面呼朋唤友，巧巧却希望两人能够尽快步入婚姻的殿堂。然而一次斌哥在街头遭到竞争对手的袭击，巧巧为了保护斌哥在街头开枪，被判刑五年。巧巧出狱以后，开始寻找斌哥以便重新开始，然而事情却发生了意想不到的变化。"},{"fare":0,"id":20,"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/ws/ws1.jpg","name":"无双","releaseTime":1537545600000,"summary":"以代号\u201c画家\u201d（周润发 饰）为首的犯罪团伙，掌握了制造伪钞技术，难辨真伪，并在全球进行交易获取利益，引起警方高度重视。然而\u201c画家\u201d和其他成员的身份一直成谜，警方的破案进度遭受到了前所未有的挑战。在关键时刻，擅长绘画的李问（郭富城 饰）打开了破案的突破口，而\u201c画家\u201d的真实身份却让众人意想不到\u2026\u2026"}]
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
         * fare : 0
         * id : 19
         * imageUrl : http://mobile.bwstudent.com/images/movie/stills/jhen/jhen1.jpg
         * name : 江湖儿女
         * releaseTime : 1537545600000
         * summary : 故事起始于2001年的山西大同，模特巧巧（赵涛 饰）与出租车公司老板斌哥（廖凡 饰）是一对恋人，斌哥每天在外面呼朋唤友，巧巧却希望两人能够尽快步入婚姻的殿堂。然而一次斌哥在街头遭到竞争对手的袭击，巧巧为了保护斌哥在街头开枪，被判刑五年。巧巧出狱以后，开始寻找斌哥以便重新开始，然而事情却发生了意想不到的变化。
         */

        private int fare;
        private int id;
        private String imageUrl;
        private String name;
        private long releaseTime;
        private String summary;

        public int getFare() {
            return fare;
        }

        public void setFare(int fare) {
            this.fare = fare;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
