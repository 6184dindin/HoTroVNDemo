package com.dindin.hotrovndemo.api.param.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBase<T> {

    @SerializedName("ResultCode")
    @Expose
    private String resultCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ResultData")
    @Expose
    private List<T> resultData;

    public String getResultCode() {
        return resultCode;
    }
    public String getMessage() {
        return message;
    }

    public List<T> getResultData() {
        return resultData;
    }
}

