package com.dindin.hotrovndemo.param.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetInfoNewsRequest {
    @SerializedName("NewsId")
    @Expose
    private Integer newsId;
    @SerializedName("FieldsId")
    @Expose
    private Integer fieldsId;
    @SerializedName("SecCode")
    @Expose
    private String secCode;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getFieldsId() {
        return fieldsId;
    }

    public void setFieldsId(Integer fieldsId) {
        this.fieldsId = fieldsId;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }
}
