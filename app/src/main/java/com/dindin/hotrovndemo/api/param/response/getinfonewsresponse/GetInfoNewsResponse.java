package com.dindin.hotrovndemo.api.param.response.getinfonewsresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetInfoNewsResponse {

    @SerializedName("NewsInfo")
    @Expose
    private NewsInfo newsInfo;
    @SerializedName("LIST_HELPER")
    @Expose
    private List<Helper> helpers = null;

    public NewsInfo getNewsInfo() {
        return newsInfo;
    }

    public void setNewsInfo(NewsInfo newsInfo) {
        this.newsInfo = newsInfo;
    }

    public List<Helper> getHelpers() {
        return helpers;
    }

    public void setHelpers(List<Helper> helpers) {
        this.helpers = helpers;
    }

}