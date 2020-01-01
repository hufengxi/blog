package com.app.appName.dto;

public class ResultDto {
    public static final int RESULT_STATUS_SUCCESS = 0;
    public static final int RESULT_STATUS_FAIL = 1;

    private int status = RESULT_STATUS_FAIL;
    private String code;
    private String msg;
    private Object item;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
