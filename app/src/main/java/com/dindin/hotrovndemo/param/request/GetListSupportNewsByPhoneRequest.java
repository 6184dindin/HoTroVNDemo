package com.dindin.hotrovndemo.param.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetListSupportNewsByPhoneRequest {
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("FieldsId")
    @Expose
    private Integer fieldsId;
    @SerializedName("SecCode")
    @Expose
    private String secCode;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getFieldsId() {
        return fieldsId;
    }

    public void setFieldsId(Integer fieldsId) {
        this.fieldsId = fieldsId;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }
}
