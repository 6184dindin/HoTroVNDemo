package com.dindin.hotrovndemo.api.param.response.getinfonewsresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsInfo {

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
    @SerializedName("AdminPost")
    @Expose
    private String adminPost;
    @SerializedName("PhoneContact")
    @Expose
    private String phoneContact;
    @SerializedName("RolePersonPost")
    @Expose
    private String rolePersonPost;
    @SerializedName("RequestSupport")
    @Expose
    private String requestSupport;
    @SerializedName("Descriptions")
    @Expose
    private String descriptions;
    @SerializedName("DateCreated")
    @Expose
    private Integer dateCreated;

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

    public String getAdminPost() {
        return adminPost;
    }

    public void setAdminPost(String adminPost) {
        this.adminPost = adminPost;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public String getRolePersonPost() {
        return rolePersonPost;
    }

    public void setRolePersonPost(String rolePersonPost) {
        this.rolePersonPost = rolePersonPost;
    }

    public String getRequestSupport() {
        return requestSupport;
    }

    public void setRequestSupport(String requestSupport) {
        this.requestSupport = requestSupport;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Integer getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Integer dateCreated) {
        this.dateCreated = dateCreated;
    }

}