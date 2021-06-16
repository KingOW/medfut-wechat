package com.medfut.wechat.dao.dto;

import com.medfut.common.beans.BaseBean;
import com.medfut.wechat.core.constants.WeChatAccountStatus;
import com.medfut.wechat.core.constants.WeChatAccountType;
import lombok.Data;

/**
 * 微信公众号
 */
@Data
public class PubWeChatDto extends BaseBean {


    /**
     * 账号id
     */
    private String accountId;

    /**
     * 微信公众号
     */
    private String pubWeChatNo;

    /**
     * 微信公众号名称
     */
    private String pubWeChatName;


    /**
     * 账号状态
     */
    private int accountStatus = WeChatAccountStatus.ENABLE.getCode();
    /**
     * 默认为服务号
     */
    private int accountTypeCode = WeChatAccountType.SERVICE.getCode();
    private String accountTypeName = WeChatAccountType.SERVICE.getName();

    /**
     * 备注
     */
    private String remark;

}
