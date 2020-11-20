package com.dindin.hotrovndemo.api.param.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class CreateHelpsNewsRequest {
    @SerializedName("NewsId")
    @Expose
    private Integer newsId;
    @SerializedName("FieldsId")
    @Expose
    private Integer fieldsId;
    @SerializedName("PhoneCreated")
    @Expose
    private String phoneCreated;
    @SerializedName("AdminHelper")
    @Expose
    private String adminHelper;
    @SerializedName("PhoneContact")
    @Expose
    private String phoneContact;
    @SerializedName("RolePersonHelper")
    @Expose
    private String rolePersonHelper;
    @SerializedName("Organization")
    @Expose
    private String organization;
    @SerializedName("TimeBegin")
    @Expose
    private Integer timeBegin;
    @SerializedName("TimeEnd")
    @Expose
    private Integer timeEnd;
    @SerializedName("SupportValue")
    @Expose
    private String supportValue;
    @SerializedName("DateCreated")
    @Expose
    private BigInteger dateCreated;
    @SerializedName("SecCode")
    @Expose
    private String secCode;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getFieldsId() {
        return fieldsId;
    }

    public void setFieldsId(Integer fieldsId) {
        this.fieldsId = fieldsId;
    }

    public String getPhoneCreated() {
        return phoneCreated;
    }

    public void setPhoneCreated(String phoneCreated) {
        this.phoneCreated = phoneCreated;
    }

    public String getAdminHelper() {
        return adminHelper;
    }

    public void setAdminHelper(String adminHelper) {
        this.adminHelper = adminHelper;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public String getRolePersonHelper() {
        return rolePersonHelper;
    }

    public void setRolePersonHelper(String rolePersonHelper) {
        this.rolePersonHelper = rolePersonHelper;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Integer timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Integer getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Integer timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getSupportValue() {
        return supportValue;
    }

    public void setSupportValue(String supportValue) {
        this.supportValue = supportValue;
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
