package com.dindin.hotrovndemo.api.param.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DownloadImageNewsResponse {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("LinkedOutsite")
    @Expose
    private String linkedOutsite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkedOutsite() {
        return linkedOutsite;
    }

    public void setLinkedOutsite(String linkedOutsite) {
        this.linkedOutsite = linkedOutsite;
    }
}
