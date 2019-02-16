package com.bw.movie.bean.mybean;

import java.util.List;

/**
 * date:2019.1.27
 * author:赵颖冰(lenovo)
 * function:
 */
public class MessageBean {


    /**
     * result : {"cinemasList":[{"address":"朝阳区广顺北大街16号望京华彩商业中心B1","commentTotal":0,"distance":0,"followCinema":0,"id":10,"logo":"http://mobile.bwstudent.com/images/movie/logo/hyxd.jpg","name":"华谊兄弟影院"},{"address":"北京市朝阳区建国门外大街1号国贸商城区域三地下一层3B120","commentTotal":0,"distance":0,"followCinema":0,"id":7,"logo":"http://mobile.bwstudent.com/images/movie/logo/blg.jpg","name":"北京百丽宫影城"},{"address":"北京市海淀区新街口外大街25号","commentTotal":0,"distance":0,"followCinema":0,"id":14,"logo":"http://mobile.bwstudent.com/images/movie/logo/zygj.jpg","name":"中影国际影城北京小西天店"}],"headPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","integral":10,"movieList":[{"fare":0,"id":18,"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/hjxd/hjxd1.jpg","name":"黄金兄弟","releaseTime":1537545600000,"summary":"狮王（郑伊健 饰）、火山（陈小春 饰）、Bill（谢天华 饰）、淡定（钱嘉乐 饰）、老鼠（林晓峰 饰）五个出生入死的兄弟，在恩师曹sir（曾志伟 饰）的带领下，为了救济儿童而偷取特效药，却惨遭设局，陷入枪林弹雨的险境之中。兄弟们抱着视死如归的豪情，展开一连串的追查与激战。他们明白，即使无法活着回来，也比一人活着痛快！"},{"fare":0,"id":19,"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/jhen/jhen1.jpg","name":"江湖儿女","releaseTime":1537545600000,"summary":"故事起始于2001年的山西大同，模特巧巧（赵涛 饰）与出租车公司老板斌哥（廖凡 饰）是一对恋人，斌哥每天在外面呼朋唤友，巧巧却希望两人能够尽快步入婚姻的殿堂。然而一次斌哥在街头遭到竞争对手的袭击，巧巧为了保护斌哥在街头开枪，被判刑五年。巧巧出狱以后，开始寻找斌哥以便重新开始，然而事情却发生了意想不到的变化。"},{"fare":0,"id":20,"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/ws/ws1.jpg","name":"无双","releaseTime":1537545600000,"summary":"以代号\u201c画家\u201d（周润发 饰）为首的犯罪团伙，掌握了制造伪钞技术，难辨真伪，并在全球进行交易获取利益，引起警方高度重视。然而\u201c画家\u201d和其他成员的身份一直成谜，警方的破案进度遭受到了前所未有的挑战。在关键时刻，擅长绘画的李问（郭富城 饰）打开了破案的突破口，而\u201c画家\u201d的真实身份却让众人意想不到\u2026\u2026"}],"nickName":"你需要","phone":"15852525555","userSignStatus":2}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * cinemasList : [{"address":"朝阳区广顺北大街16号望京华彩商业中心B1","commentTotal":0,"distance":0,"followCinema":0,"id":10,"logo":"http://mobile.bwstudent.com/images/movie/logo/hyxd.jpg","name":"华谊兄弟影院"},{"address":"北京市朝阳区建国门外大街1号国贸商城区域三地下一层3B120","commentTotal":0,"distance":0,"followCinema":0,"id":7,"logo":"http://mobile.bwstudent.com/images/movie/logo/blg.jpg","name":"北京百丽宫影城"},{"address":"北京市海淀区新街口外大街25号","commentTotal":0,"distance":0,"followCinema":0,"id":14,"logo":"http://mobile.bwstudent.com/images/movie/logo/zygj.jpg","name":"中影国际影城北京小西天店"}]
         * headPic : http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg
         * integral : 10
         * movieList : [{"fare":0,"id":18,"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/hjxd/hjxd1.jpg","name":"黄金兄弟","releaseTime":1537545600000,"summary":"狮王（郑伊健 饰）、火山（陈小春 饰）、Bill（谢天华 饰）、淡定（钱嘉乐 饰）、老鼠（林晓峰 饰）五个出生入死的兄弟，在恩师曹sir（曾志伟 饰）的带领下，为了救济儿童而偷取特效药，却惨遭设局，陷入枪林弹雨的险境之中。兄弟们抱着视死如归的豪情，展开一连串的追查与激战。他们明白，即使无法活着回来，也比一人活着痛快！"},{"fare":0,"id":19,"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/jhen/jhen1.jpg","name":"江湖儿女","releaseTime":1537545600000,"summary":"故事起始于2001年的山西大同，模特巧巧（赵涛 饰）与出租车公司老板斌哥（廖凡 饰）是一对恋人，斌哥每天在外面呼朋唤友，巧巧却希望两人能够尽快步入婚姻的殿堂。然而一次斌哥在街头遭到竞争对手的袭击，巧巧为了保护斌哥在街头开枪，被判刑五年。巧巧出狱以后，开始寻找斌哥以便重新开始，然而事情却发生了意想不到的变化。"},{"fare":0,"id":20,"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/ws/ws1.jpg","name":"无双","releaseTime":1537545600000,"summary":"以代号\u201c画家\u201d（周润发 饰）为首的犯罪团伙，掌握了制造伪钞技术，难辨真伪，并在全球进行交易获取利益，引起警方高度重视。然而\u201c画家\u201d和其他成员的身份一直成谜，警方的破案进度遭受到了前所未有的挑战。在关键时刻，擅长绘画的李问（郭富城 饰）打开了破案的突破口，而\u201c画家\u201d的真实身份却让众人意想不到\u2026\u2026"}]
         * nickName : 你需要
         * phone : 15852525555
         * userSignStatus : 2
         */

        private String headPic;
        private int integral;
        private String nickName;
        private String phone;
        private int userSignStatus;
        private List<CinemasListBean> cinemasList;
        private List<MovieListBean> movieList;

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getUserSignStatus() {
            return userSignStatus;
        }

        public void setUserSignStatus(int userSignStatus) {
            this.userSignStatus = userSignStatus;
        }

        public List<CinemasListBean> getCinemasList() {
            return cinemasList;
        }

        public void setCinemasList(List<CinemasListBean> cinemasList) {
            this.cinemasList = cinemasList;
        }

        public List<MovieListBean> getMovieList() {
            return movieList;
        }

        public void setMovieList(List<MovieListBean> movieList) {
            this.movieList = movieList;
        }

        public static class CinemasListBean {
            /**
             * address : 朝阳区广顺北大街16号望京华彩商业中心B1
             * commentTotal : 0
             * distance : 0
             * followCinema : 0
             * id : 10
             * logo : http://mobile.bwstudent.com/images/movie/logo/hyxd.jpg
             * name : 华谊兄弟影院
             */

            private String address;
            private int commentTotal;
            private int distance;
            private int followCinema;
            private int id;
            private String logo;
            private String name;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getCommentTotal() {
                return commentTotal;
            }

            public void setCommentTotal(int commentTotal) {
                this.commentTotal = commentTotal;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public int getFollowCinema() {
                return followCinema;
            }

            public void setFollowCinema(int followCinema) {
                this.followCinema = followCinema;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class MovieListBean {
            /**
             * fare : 0
             * id : 18
             * imageUrl : http://mobile.bwstudent.com/images/movie/stills/hjxd/hjxd1.jpg
             * name : 黄金兄弟
             * releaseTime : 1537545600000
             * summary : 狮王（郑伊健 饰）、火山（陈小春 饰）、Bill（谢天华 饰）、淡定（钱嘉乐 饰）、老鼠（林晓峰 饰）五个出生入死的兄弟，在恩师曹sir（曾志伟 饰）的带领下，为了救济儿童而偷取特效药，却惨遭设局，陷入枪林弹雨的险境之中。兄弟们抱着视死如归的豪情，展开一连串的追查与激战。他们明白，即使无法活着回来，也比一人活着痛快！
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
}
