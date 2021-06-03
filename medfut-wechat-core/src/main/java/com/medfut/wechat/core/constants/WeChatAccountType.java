package com.medfut.wechat.core.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 微信账号类型
 */
public enum WeChatAccountType {
    MERCHANT(1, "商户号", "商户号"),
    SERVICE(2, "服务号", "服务号"),
    SUBSCRIPTION(3, "订阅号", "订阅号"),
    MINI_PROGRAM(4, "小程序", "小程序"),
    ENTERPRISE(5, "企业微信", "企业微信"),
    PERSONAL(99, "个人微信", "个人微信"),


    ;

    private final int code;
    private final String name;
    private final String desc;

    WeChatAccountType(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    private static List<WeChatAccountType> ALL_DATAS = Arrays.asList(values());

    public static WeChatAccountType WeChatAccountTypeByCode(int code) {
        return ALL_DATAS.stream().filter(item -> item.code == code).findFirst().get();
    }
}
