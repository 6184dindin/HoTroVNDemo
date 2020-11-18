package com.dindin.hotrovndemo.api.param.response.getinfonewsresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Helper implements Serializable {

    @SerializedName("HelpsId")
    @Expose
    private Integer helpsId;
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
    private Integer dateCreated;

    public Integer getHelpsId() {
        return helpsId;
    }

    public void setHelpsId(Integer helpsId) {
        this.helpsId = helpsId;
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

    public Integer getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Integer dateCreated) {
        this.dateCreated = dateCreated;
    }

}
