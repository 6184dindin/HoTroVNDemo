package com.dindin.hotrovndemo.api.param.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DownloadImageNewsRequest {
    @SerializedName("NewsId")
    @Expose
    private Integer newsId;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("SecCode")
    @Expose
    private String secCode;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }
}
