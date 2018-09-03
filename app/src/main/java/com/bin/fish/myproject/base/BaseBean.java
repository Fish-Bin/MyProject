package com.bin.fish.myproject.base;

public class BaseBean<T> {
    private int error_code=-1;
    private String msg;
    private int status;
    private T data;


    public int getError_code() {
        return error_code;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
