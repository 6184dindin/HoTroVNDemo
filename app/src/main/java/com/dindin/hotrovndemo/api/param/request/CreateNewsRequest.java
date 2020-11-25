package com.dindin.hotrovndemo.api.param.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class CreateNewsRequest {
    @SerializedName("PhoneCreated")
    @Expose
    private String phoneCreated;
    @SerializedName("FieldsId")
    @Expose
    private Integer fieldsId;
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
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("DateNotif")
    @Expose
    private BigInteger dateNotify;
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
    @SerializedName("NotificationId")
    @Expose
    private String notificationId;
    @SerializedName("DateCreated")
    @Expose
    private BigInteger dateCreated;
    @SerializedName("SecCode")
    @Expose
    private String secCode;

    public String getPhoneCreated() {
        return phoneCreated;
    }

    public void setPhoneCreated(String phoneCreated) {
        this.phoneCreated = phoneCreated;
    }

    public Integer getFieldsId() {
        return fieldsId;
    }

    public void setFieldsId(Integer fieldsId) {
        this.fieldsId = fieldsId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getDateNotify() {
        return dateNotify;
    }

    public void setDateNotify(BigInteger dateNotify) {
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

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public BigInteger getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(BigInteger dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }
}
