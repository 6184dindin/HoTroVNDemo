package com.dindin.hotrovndemo.api.param.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetListHelpJobsByPhoneRequest {

    @SerializedName("FieldsId")
    @Expose
    private Integer fieldsId;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("SecCode")
    @Expose
    private String secCode;

    public Integer getFieldsId() {
        return fieldsId;
    }

    public void setFieldsId(Integer fieldsId) {
        this.fieldsId = fieldsId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }
}
