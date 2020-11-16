package com.dindin.hotrovndemo.param.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImageRequest {
    @SerializedName("NewsId")
    @Expose
    private Integer newsId;
    @SerializedName("orderNum")
    @Expose
    private Integer orderNum;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("SecCode")
    @Expose
    private String secCode;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }
}
