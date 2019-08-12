package com.dd.server.exception.message;

public enum ErrorInfo {

    // 操作性错误
    MISS_PARAM(1001, "参数缺失或参数格式错误"),
    PATH_NOT_FOUNT(1002, "路径不存在"),

    // 业务错误

    // 数据库等错误
    UNKNOWN_ERROR(3001, "系统错误"),
    NETWORK_ERROR(3002, "网络错误"),
    PERMIT_ERROR(3003, "权限错误"),
    ;

    private int code;
    private String desc;

    ErrorInfo(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
