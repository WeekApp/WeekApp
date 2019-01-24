package com.example.movie.bean.userbean;

public class RegisterBean {


    /**
     * message : 两次输入的密码不相同
     * status : 1002
     */

    private String message;
    private String status;

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
}
