package com.medfut.wechat.core.model;

import com.medfut.common.beans.BaseBean;
import com.medfut.wechat.core.constants.WeChatAccountStatus;
import com.medfut.wechat.core.constants.WeChatAccountType;
import lombok.Data;

/**
 * 微信账号
 */
@Data
public class WeChatAccount extends BaseBean {

    /**
     * 账号ID:系统自己生成的账号id
     */
    private String accountId;

    /**
     * 账号状态
     */
    private int accountStatus = WeChatAccountStatus.ENABLE.getCode();
    private String accountStatusName = WeChatAccountStatus.ENABLE.getName();
    /**
     * 微信公众号类型:默认为个人账号
     */
    private int accountTypeCode = WeChatAccountType.PERSONAL.getCode();
    private String accountTypeName = WeChatAccountType.PERSONAL.getName();

    public void AccountTypeName(){
       WeChatAccountType type = WeChatAccountType.WeChatAccountTypeByCode(this.getAccountTypeCode());
       if(type != null){
         this.setAccountTypeName(type.getName());
       }
    }

    public void AccountStatusName(){
        WeChatAccountStatus status = WeChatAccountStatus.WeChatAccountStatusByCode(this.getAccountStatus());
        if(status != null){
            this.setAccountStatusName(status.getName());
        }
    }

}
