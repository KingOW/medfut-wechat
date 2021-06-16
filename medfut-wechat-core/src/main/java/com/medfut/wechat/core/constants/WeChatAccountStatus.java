package com.medfut.wechat.core.constants;

import java.util.Arrays;
import java.util.List;

public enum WeChatAccountStatus {

    ENABLE(2,"启用","启用/激活"),
    DISABLE(3,"禁用","禁用"),
    DELETE(99,"删除","删除"),

    ;


    private final int code;
    private final String name;
    private final String desc;

    WeChatAccountStatus(int code, String name, String desc) {
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

    private static List<WeChatAccountStatus> ALL_DATAS = Arrays.asList(values());

    public static WeChatAccountStatus WeChatAccountStatusByCode(int code) {
        return ALL_DATAS.stream().filter(item -> item.code == code).findFirst().get();
    }
}
