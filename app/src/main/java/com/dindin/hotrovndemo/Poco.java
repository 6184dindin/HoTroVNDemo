package com.dindin.hotrovndemo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class Poco implements Serializable {
    BigInteger id;
    int country;
    int province;
    int city;
    int district;
    int village;
    double lat;
    double lng;
    int dateNotif;
    String adminPost;
    String phoneContact;
    String rolePersonPost;
    String requestSupport;
    String descriptions;
    BigInteger dateCreated;
    List<HelperJoined> helperJoinedList;

    public Poco() {
    }

    public Poco(BigInteger id, int country, int province, int city, int district, int village,
                double lat, double lng, int dateNotif, String adminPost, String phoneContact,
                String rolePersonPost, String requestSupport, String descriptions,
                BigInteger dateCreated, List<HelperJoined> helperJoinedList) {
        this.id = id;
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.village = village;
        this.lat = lat;
        this.lng = lng;
        this.dateNotif = dateNotif;
        this.adminPost = adminPost;
        this.phoneContact = phoneContact;
        this.rolePersonPost = rolePersonPost;
        this.requestSupport = requestSupport;
        this.descriptions = descriptions;
        this.dateCreated = dateCreated;
        this.helperJoinedList = helperJoinedList;
    }
}
