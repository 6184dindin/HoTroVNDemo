package com.dindin.hotrovndemo.param.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBase<T> {

    @SerializedName("ResultCode")
    @Expose
    private String resultCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ResultData")
    @Expose
    private T resultData;

    public String getResultCode() {
        return resultCode;
    }
    public String getMessage() {
        return message;
    }

    public T getResultData() {
        return resultData;
    }
}

