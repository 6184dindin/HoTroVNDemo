package com.dindin.hotrovndemo.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Cities")
    @Expose
    private List<InfoAddress> infoAddresses = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<InfoAddress> getInfoAddresses() {
        return infoAddresses;
    }

    public void setInfoAddresses(List<InfoAddress> infoAddresses) {
        this.infoAddresses = infoAddresses;
    }
}
