package com.example.dcl.dailymarketlist.Model;

public class Check {
    private String result;
    private String error_msg;

    public Check(String result, String error_msg) {
        this.result = result;
        this.error_msg = error_msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
