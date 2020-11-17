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
    private List<LISTHELPER> lISTHELPER = null;

    public NewsInfo getNewsInfo() {
        return newsInfo;
    }

    public void setNewsInfo(NewsInfo newsInfo) {
        this.newsInfo = newsInfo;
    }

    public List<LISTHELPER> getLISTHELPER() {
        return lISTHELPER;
    }

    public void setLISTHELPER(List<LISTHELPER> lISTHELPER) {
        this.lISTHELPER = lISTHELPER;
    }

}