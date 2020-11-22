package com.dindin.hotrovndemo.api.param.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImageNewsResponse {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("LinkedOutsite")
    @Expose
    private String linkedOutside;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkedOutside() {
        return linkedOutside;
    }

    public void setLinkedOutside(String linkedOutside) {
        this.linkedOutside = linkedOutside;
    }
}
