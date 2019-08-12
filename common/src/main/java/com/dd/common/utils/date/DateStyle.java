package com.dd.common.utils.date;

public enum DateStyle {

    YYYY("yyyy"),
    YYYYMMDD("yyyyMMdd"),
    YYYYMMDDHHMMSS("yyyyMMddHHmmss"),

    HH_MM("HH:mm"),
    MM_DD("MM-dd"),
    YYYY_MM_DD("yyyy-MM-dd"),
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss");

    private String value;

    DateStyle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
