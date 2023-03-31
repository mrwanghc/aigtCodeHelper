package com.aigt.code.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StandardResponse<T> {

    private int code;

    private String msg;

    private T data;

    public Boolean isSuccess() {
        return code == 1 && data != null;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
