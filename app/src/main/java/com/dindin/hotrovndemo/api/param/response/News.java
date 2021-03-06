package com.dindin.hotrovndemo.api.param.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;

public class News implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Country")
    @Expose
    private Integer country;
    @SerializedName("Province")
    @Expose
    private Integer province;
    @SerializedName("City")
    @Expose
    private Integer city;
    @SerializedName("District")
    @Expose
    private Integer district;
    @SerializedName("Village")
    @Expose
    private Integer village;
    @SerializedName("Lat")
    @Expose
    private Double lat;
    @SerializedName("Lng")
    @Expose
    private Double lng;
    @SerializedName("DateNotif")
    @Expose
    private Integer dateNotify;
    @SerializedName("RequestSupport")
    @Expose
    private String requestSupport;
    @SerializedName("DateCreated")
    @Expose
    private BigInteger dateCreated;
    @SerializedName("CountHelperJoined")
    @Expose
    private Integer countHelperJoined;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getVillage() {
        return village;
    }

    public void setVillage(Integer village) {
        this.village = village;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getDateNotify() {
        return dateNotify;
    }

    public void setDateNotify(Integer dateNotify) {
        this.dateNotify = dateNotify;
    }

    public String getRequestSupport() {
        return requestSupport;
    }

    public void setRequestSupport(String requestSupport) {
        this.requestSupport = requestSupport;
    }

    public BigInteger getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(BigInteger dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getCountHelperJoined() {
        return countHelperJoined;
    }

    public void setCountHelperJoined(Integer countHelperJoined) {
        this.countHelperJoined = countHelperJoined;
    }
}
