package com.hc.bean.checker;

import java.io.Serializable;

public class CheckResult implements Serializable {
    public static final String SUCCESS = "success";
    private boolean pass;
    private String message;

    public CheckResult(boolean pass, String message) {
        this.pass = pass;
        this.message = message;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CheckResult{" +
                "pass=" + pass +
                ", message='" + message + '\'' +
                '}';
    }
}
