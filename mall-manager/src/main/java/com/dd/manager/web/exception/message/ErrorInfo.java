package com.dd.manager.web.exception.message;

public enum ErrorInfo {

    NOT_LOGIN(401, "没有登陆或登陆信息过期"),

    // 操作性错误
    MISS_PARAM(1001, "参数缺失或参数格式错误"),
    PATH_NOT_FOUNT(1002, "路径不存在"),
    OPERATION_ERROR(1003, "操作错误"),

    // 业务错误
    PROPORTION_ERROR(2001, "错误的比例！"),
    PROPORTION_NOT_100(2002, "所有比例的总和不等于100！"),
    QR_COUNT_ERROR(2003, "非法二维码数量！"),
    REDPACK_HAS_BEEN_RECEVIED(2004, "该红包已被领取过！"),
    FAKE__PSEUDO_CODE(2004, "假的防伪码！"),

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
