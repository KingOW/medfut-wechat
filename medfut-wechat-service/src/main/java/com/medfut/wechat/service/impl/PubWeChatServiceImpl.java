package com.medfut.wechat.service.impl;

import com.medfut.common.beans.ResultBean;
import com.medfut.common.enums.ResultStatus;
import com.medfut.common.utils.DateUtils;
import com.medfut.common.utils.StringExtUtils;
import com.medfut.common.utils.UUIDUtils;
import com.medfut.wechat.core.constants.WeChatAccountStatus;
import com.medfut.wechat.core.exceptions.WeChatException;
import com.medfut.wechat.core.model.PubWeChat;
import com.medfut.wechat.dao.dto.PubWeChatDto;
import com.medfut.wechat.dao.mapper.PubWeChatMapper;
import com.medfut.wechat.service.PubWeChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 微信公众平台：公众号，小程序，企业微信等相关的服务
 */
@Service(value = "pubWeChatService")
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
        String accountId = "WCA_" + pubWeChat.getAccountTypeCode() + UUIDUtils.getUUID("");
        pubWeChat.setAccountId(accountId);
        pubWeChat.setAccountStatus(WeChatAccountStatus.ENABLE.getCode());
        PubWeChatDto pubWeChatDto = this.BuildDtoByModel(pubWeChat);
        try {
            this.pubWeChatMapper.insertPubWeChat(pubWeChatDto);
        } catch (Exception e) {
            log.error("添加公众号失败：服务器出现异常.", e);
            throw new WeChatException("添加公众号失败：服务器出现异常.", e);
        }
        return new ResultBean<String>(true, ResultStatus.SUCCESS, accountId);
    }

    private PubWeChatDto BuildDtoByModel(PubWeChat pubWeChat) {
        PubWeChatDto pubWeChatDto = new PubWeChatDto();
        BeanUtils.copyProperties(pubWeChat, pubWeChatDto);
        pubWeChatDto.setCreateUser("admin");
        pubWeChatDto.setCreateTime(LocalDateTime.now());
        pubWeChatDto.setUpdateUser("admin");
        pubWeChatDto.setUpdateTime(LocalDateTime.now());
        return pubWeChatDto;
    }

    private PubWeChat BuildModelByDto(PubWeChatDto pubWeChatDto) {
        PubWeChat pubWeChat = new PubWeChat();
        BeanUtils.copyProperties(pubWeChatDto, pubWeChat);
        pubWeChat.AccountStatusName();
        return pubWeChat;
    }

    @Override
    public ResultBean<PubWeChat> queryPubWeChatByKey(String weChatKey) throws WeChatException {
        log.info("查询公众号信息：检查参数是否复合要求");
        if (StringExtUtils.isEmpty(weChatKey)) {
            log.warn("查询公众号信息失败：weChatKey为空.");
            return new ResultBean<>(false, ResultStatus.PARAM_ERROR.getCode(), "查询公众号信息失败：没有提供公众号信息");
        }
        try {
            PubWeChatDto pubWeChatDto = this.pubWeChatMapper.selectPubWeChatByKey(StringExtUtils.trim(weChatKey));
            if (pubWeChatDto != null) {
                PubWeChat pubWeChat = this.BuildModelByDto(pubWeChatDto);
                return new ResultBean<>(true, ResultStatus.SUCCESS, pubWeChat);
            }
            return new ResultBean<>(false, ResultStatus.DATA_ERROR.getCode(), "查询公众号信息失败：没有该公众号信息.");
        } catch (Exception e) {
            log.error("查询公众号信息失败：服务器出现异常.", e);
            throw new WeChatException("查询公众号信息失败：服务器出现异常.", e);
        }
    }

    @Override
    public ResultBean<String> enablePubWeChat(String weChatKey) throws WeChatException {
        log.info("启用公众号:检查参数是否复合要求.");
        try {
            this.pubWeChatMapper.updatePubWeChatStatus(StringExtUtils.trim(weChatKey), WeChatAccountStatus.ENABLE.getCode(), "", LocalDateTime.now());
        } catch (Exception e) {
            log.error("启用公众号失败：服务器出现异常.", e);
            throw new WeChatException("启用公众号失败：服务器出现异常.", e);
        }
        return ResultBean.DEFAULT_SUCCESS_RESULT;
    }

    @Override
    public ResultBean<String> disablePubWeChat(String weChatKey) throws WeChatException {
        log.info("禁用公众号:检查参数是否复合要求.");
        try {
            this.pubWeChatMapper.updatePubWeChatStatus(StringExtUtils.trim(weChatKey), WeChatAccountStatus.DISABLE.getCode(), "", LocalDateTime.now());
        } catch (Exception e) {
            log.error("启用公众号失败：服务器出现异常.", e);
            throw new WeChatException("启用公众号失败：服务器出现异常.", e);
        }
        return ResultBean.DEFAULT_SUCCESS_RESULT;
    }

    @Override
    public ResultBean<String> deletePubWeChat(String weChatKey) throws WeChatException {
        log.info("删除公众号:检查参数是否复合要求.");
        try {
            this.pubWeChatMapper.updatePubWeChatStatus(StringExtUtils.trim(weChatKey), WeChatAccountStatus.DELETE.getCode(), "", LocalDateTime.now());
        } catch (Exception e) {
            log.error("删除公众号失败：服务器出现异常.", e);
            throw new WeChatException("删除公众号失败：服务器出现异常.", e);
        }
        return ResultBean.DEFAULT_SUCCESS_RESULT;
    }

    private ResultBean<String> checkSavePubWeChat(PubWeChat pubWeChat) {
        if (StringExtUtils.isEmpty(pubWeChat.getPubWeChatNo())) {
            return new ResultBean<>(false, ResultStatus.PARAM_ERROR.getCode(), "新增失败:公众号编码为空.");
        }
        if (StringExtUtils.isEmpty(pubWeChat.getPubWeChatName())) {
            return new ResultBean<>(false, ResultStatus.PARAM_ERROR.getCode(), "新增失败:公众号名称为空.");
        }
        try {
            PubWeChatDto pubWeChatDto = this.pubWeChatMapper.selectPubWeChatByNo(StringExtUtils.trim(pubWeChat.getPubWeChatNo()));
            if(pubWeChatDto != null){
                return new ResultBean<>(false, ResultStatus.DATA_ERROR.getCode(), "新增失败:公众号编码已经存在.");
            }
        } catch (Exception e) {
            log.error("新增失败:服务器出现异常.",e);
            return new ResultBean<>(false, ResultStatus.SERVER_ERROR.getCode(), "新增失败:服务器出现异常.");
        }
        return ResultBean.DEFAULT_SUCCESS_RESULT;
    }
}
