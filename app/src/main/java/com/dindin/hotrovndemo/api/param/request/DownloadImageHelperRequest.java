package com.dindin.hotrovndemo.api.param.request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DownloadImageHelperRequest {
    @SerializedName("HelpsId")
    @Expose
    private Integer helpsId;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("SecCode")
    @Expose
    private String secCode;

    public Integer getHelpsId() {
        return helpsId;
    }

    public void setHelpsId(Integer helpsId) {
        this.helpsId = helpsId;
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
