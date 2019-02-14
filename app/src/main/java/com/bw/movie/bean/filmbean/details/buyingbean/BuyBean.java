package com.bw.movie.bean.filmbean.details.buyingbean;

import java.util.List;

public class BuyBean {


    /**
     * result : [{"address":"北京市崇文区崇文门外大街18号国瑞城首层、地下二层","followCinema":false,"id":9,"name":"北京百老汇影城国瑞购物中心店"},{"address":"北京市海淀区远大路1号B座5层魔影国际影城","followCinema":false,"id":4,"name":"魔影国际影城"},{"address":"东城区滨河路乙1号雍和航星园74-76号楼","followCinema":false,"id":1,"name":"青春光线电影院"}]
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
         * address : 北京市崇文区崇文门外大街18号国瑞城首层、地下二层
         * followCinema : false
         * id : 9
         * name : 北京百老汇影城国瑞购物中心店
         */

        private String address;
        private boolean followCinema;
        private int id;
        private String name;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isFollowCinema() {
            return followCinema;
        }

        public void setFollowCinema(boolean followCinema) {
            this.followCinema = followCinema;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
