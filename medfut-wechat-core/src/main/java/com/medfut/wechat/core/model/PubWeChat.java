package com.medfut.wechat.core.model;

import com.medfut.wechat.core.constants.WeChatAccountType;
import lombok.Data;

/**
 * 微信公众号：订阅号，服务号
 */
@Data
public class PubWeChat extends WeChatAccount {

    /**
     * 微信公众号
     */
    private String pubWeChatNo;

    /**
     * 微信公众号名称
     */
    private String pubWeChatName;


    /**
     * 默认为服务号
     */
    private int accountTypeCode = WeChatAccountType.SERVICE.getCode();
    private String accountTypeName = WeChatAccountType.SERVICE.getName();

}
