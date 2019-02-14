package com.bw.movie.bean.filmbean.details.detailsbean;

import java.util.List;

public class DetailsBean {


    /**
     * result : {"director":"林德禄","duration":"100分钟","followMovie":2,"id":17,"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)1.jpg","movieTypes":"动作 / 剧情 / 犯罪","name":"反贪风暴3","placeOrigin":"中国大陆,中国香港","posterList":["http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)1.jpg","http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)2.jpg","http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)3.jpg","http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)4.jpg","http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)5.jpg","http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)6.jpg"],"rank":0,"shortFilmList":[{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)3.jpg","videoUrl":"http://mobile.bwstudent.com/video/movie/ftfb3/ftfb(3)1.mp4"},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)2.jpg","videoUrl":"http://mobile.bwstudent.com/video/movie/ftfb3/ftfb(3)2.mp4"},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)4.jpg","videoUrl":"http://mobile.bwstudent.com/video/movie/ftfb3/ftfb(3)3.mp4"}],"starring":"古天乐,张智霖,郑嘉颖,邓丽欣,谢天华","summary":"ICAC (廉政公署) 陆志廉（古天乐 饰），JFIU (联合财富情报组) 刘保强（张智霖 饰）分别侦查贪污及洗黑钱案，但苦无线索，这时廉政公署L组 (内部纪律调查组) 程德明（郑嘉颖 饰）收到举报，指陆志廉收贿1200万，陆无法辩解实时停职。刘发现陆被诬陷，并跟一直调查的洗黑钱案有着千丝万缕关系，同时怀疑银行主任游子新（栢天男 饰）协助罪恶集团洗黑钱；陆冒险搜集罪证却遭禁锢，命悬一线......."}
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
         * director : 林德禄
         * duration : 100分钟
         * followMovie : 2
         * id : 17
         * imageUrl : http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)1.jpg
         * movieTypes : 动作 / 剧情 / 犯罪
         * name : 反贪风暴3
         * placeOrigin : 中国大陆,中国香港
         * posterList : ["http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)1.jpg","http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)2.jpg","http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)3.jpg","http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)4.jpg","http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)5.jpg","http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)6.jpg"]
         * rank : 0
         * shortFilmList : [{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)3.jpg","videoUrl":"http://mobile.bwstudent.com/video/movie/ftfb3/ftfb(3)1.mp4"},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)2.jpg","videoUrl":"http://mobile.bwstudent.com/video/movie/ftfb3/ftfb(3)2.mp4"},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)4.jpg","videoUrl":"http://mobile.bwstudent.com/video/movie/ftfb3/ftfb(3)3.mp4"}]
         * starring : 古天乐,张智霖,郑嘉颖,邓丽欣,谢天华
         * summary : ICAC (廉政公署) 陆志廉（古天乐 饰），JFIU (联合财富情报组) 刘保强（张智霖 饰）分别侦查贪污及洗黑钱案，但苦无线索，这时廉政公署L组 (内部纪律调查组) 程德明（郑嘉颖 饰）收到举报，指陆志廉收贿1200万，陆无法辩解实时停职。刘发现陆被诬陷，并跟一直调查的洗黑钱案有着千丝万缕关系，同时怀疑银行主任游子新（栢天男 饰）协助罪恶集团洗黑钱；陆冒险搜集罪证却遭禁锢，命悬一线.......
         */

        private String director;
        private String duration;
        private int followMovie;
        private int id;
        private String imageUrl;
        private String movieTypes;
        private String name;
        private String placeOrigin;
        private int rank;
        private String starring;
        private String summary;
        public List<String> posterList;
        public List<ShortFilmListBean> shortFilmList;

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public int getFollowMovie() {
            return followMovie;
        }

        public void setFollowMovie(int followMovie) {
            this.followMovie = followMovie;
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

        public String getMovieTypes() {
            return movieTypes;
        }

        public void setMovieTypes(String movieTypes) {
            this.movieTypes = movieTypes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceOrigin() {
            return placeOrigin;
        }

        public void setPlaceOrigin(String placeOrigin) {
            this.placeOrigin = placeOrigin;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getStarring() {
            return starring;
        }

        public void setStarring(String starring) {
            this.starring = starring;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<String> getPosterList() {
            return posterList;
        }

        public void setPosterList(List<String> posterList) {
            this.posterList = posterList;
        }

        public List<ShortFilmListBean> getShortFilmList() {
            return shortFilmList;
        }

        public void setShortFilmList(List<ShortFilmListBean> shortFilmList) {
            this.shortFilmList = shortFilmList;
        }

        public static class ShortFilmListBean {
            /**
             * imageUrl : http://mobile.bwstudent.com/images/movie/stills/ftfb3/ftfb(3)3.jpg
             * videoUrl : http://mobile.bwstudent.com/video/movie/ftfb3/ftfb(3)1.mp4
             */

            private String imageUrl;
            private String videoUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }
        }
    }
}
