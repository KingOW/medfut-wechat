package com.medfut.wechat.service.impl;

import com.medfut.common.beans.ResultBean;
import com.medfut.common.enums.ResultStatus;
import com.medfut.common.utils.StringExtUtils;
import com.medfut.common.utils.UUIDUtils;
import com.medfut.wechat.core.exceptions.WeChatException;
import com.medfut.wechat.core.model.PubWeChat;
import com.medfut.wechat.dao.dto.PubWeChatDto;
import com.medfut.wechat.dao.mapper.PubWeChatMapper;
import com.medfut.wechat.service.PubWeChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 微信公众平台：公众号，小程序，企业微信等相关的服务
 */
@Service
@Slf4j
public class PubWeChatServiceImpl implements PubWeChatService {

    @Resource(name = "pubWeChatMapper")
    private PubWeChatMapper pubWeChatMapper;

    @Override
    public ResultBean<String> savePubWeChat(PubWeChat pubWeChat) throws WeChatException {
        log.info("添加公众号信息:校验参数是否符合要求.");
        ResultBean<String> checkResult = this.checkSavePubWeChat(pubWeChat);
        if (checkResult.isNotSuccess()) {
            log.warn(checkResult.getMessage());
            return checkResult;
        }
        log.info("添加公众号信息:封装数据，并保存数据.");
        pubWeChat.AccountTypeName();
        String accountId = "WCA_"+pubWeChat.getAccountType()+ UUIDUtils.getUUID("");
        PubWeChatDto pubWeChatDto = new PubWeChatDto();
        BeanUtils.copyProperties(pubWeChat,pubWeChatDto);
        try {
            this.pubWeChatMapper.insertPubWeChat(pubWeChatDto);
        } catch (Exception e) {
           log.error("添加公众号失败：服务器出现异常.",e);
           throw new WeChatException("添加公众号失败：服务器出现异常.",e);
        }
        return new ResultBean<String>(true, ResultStatus.SUCCESS,accountId);
    }

    @Override
    public ResultBean<PubWeChat> queryPubWeChatByKey(String weChatKey) throws WeChatException {
        return null;
    }

    private ResultBean<String> checkSavePubWeChat(PubWeChat pubWeChat) {
        if (StringExtUtils.isEmpty(pubWeChat.getPubWeChatNo())) {

        }
        if (StringExtUtils.isEmpty(pubWeChat.getPubWeChatName())) {

        }
        return ResultBean.DEFAULT_SUCCESS_RESULT;
    }
}
