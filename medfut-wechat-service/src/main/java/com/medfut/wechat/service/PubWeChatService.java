package com.medfut.wechat.service;

import com.medfut.common.beans.ResultBean;
import com.medfut.wechat.core.exceptions.WeChatException;
import com.medfut.wechat.core.model.PubWeChat;

/**
 * 微信公众号相关服务
 */
public interface PubWeChatService {

    /**
     * 保存微信公众号
     * @param pubWeChat
     * @return
     * @throws WeChatException
     */
    ResultBean<String> savePubWeChat(PubWeChat pubWeChat)throws WeChatException;

    /**
     * 查询微信公众号信息：精确查询，根据账号id，微信号查询
     * @param weChatKey
     * @return
     * @throws WeChatException
     */
    ResultBean<PubWeChat> queryPubWeChatByKey(String weChatKey)throws WeChatException;

    /**
     * 启用公众号
     * @param weChatKey
     * @return
     * @throws WeChatException
     */
    ResultBean<String> enablePubWeChat(String weChatKey)throws WeChatException;

    /**
     * 禁用公众号
     * @param weChatKey
     * @return
     * @throws WeChatException
     */
    ResultBean<String> disablePubWeChat(String weChatKey)throws WeChatException;

    /**
     * 删除公众号
     * @param weChatKey
     * @return
     * @throws WeChatException
     */
    ResultBean<String> deletePubWeChat(String weChatKey)throws WeChatException;
}
