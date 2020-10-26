package com.dindin.hotrovndemo;

import java.io.Serializable;
import java.math.BigInteger;

public class HelperJoined implements Serializable {
    String adminHelper;
    String phoneContact;
    String rolePersonHelper;
    String organization;
    BigInteger timeBegin;
    BigInteger timeEnd;
    String supportValue;
    BigInteger dateCreated;

    public HelperJoined(String adminHelper, String phoneContact, String rolePersonHelper,
                        String organization, BigInteger timeBegin, BigInteger timeEnd,
                        String supportValue, BigInteger dateCreated) {
        this.adminHelper = adminHelper;
        this.phoneContact = phoneContact;
        this.rolePersonHelper = rolePersonHelper;
        this.organization = organization;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.supportValue = supportValue;
        this.dateCreated = dateCreated;
    }
}
