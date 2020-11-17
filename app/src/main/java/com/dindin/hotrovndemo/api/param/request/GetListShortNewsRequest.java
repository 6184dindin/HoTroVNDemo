package com.dindin.hotrovndemo.api.param.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetListShortNewsRequest {
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
    @SerializedName("DateBegin")
    @Expose
    private Integer dateBegin;
    @SerializedName("DateEnd")
    @Expose
    private Integer dateEnd;
    @SerializedName("SecCode")
    @Expose
    private String secCode;

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

    public Integer getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Integer dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Integer getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Integer dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }
}

