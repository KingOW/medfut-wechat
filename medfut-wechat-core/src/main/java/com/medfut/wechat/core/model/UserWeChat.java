package com.medfut.wechat.core.model;

import com.medfut.common.beans.BaseBean;
import com.medfut.wechat.core.constants.WeChatAccountType;
import lombok.Data;

/**
 * 微信号
 */
@Data
public class UserWeChat extends WeChatAccount {


    /**
     * 微信号
     */
    private String weChatNo;

    /**
     * open id 和 unionId
     */
    private String openId;
    private String unionId;

    /**
     * 公众号及名称
     */
    private String pubWeChatNo;
    private String pubWeChatName;

    /**
     * 账户类型
     */
    //private  final int accountType = WeChatAccountType.PERSONAL.getCode();
    //private  final String accountTypeName = WeChatAccountType.PERSONAL.getName();

    public int getAccountTypeCode() {
        return WeChatAccountType.PERSONAL.getCode();
    }

    public String getAccountTypeName() {
        return WeChatAccountType.PERSONAL.getName();
    }

    public void setAccountType(int accountType) {
        super.setAccountTypeCode(WeChatAccountType.PERSONAL.getCode());
    }

    public void setAccountTypeName(String accountTypeName) {
        super.setAccountTypeName(WeChatAccountType.PERSONAL.getName());
    }
}
