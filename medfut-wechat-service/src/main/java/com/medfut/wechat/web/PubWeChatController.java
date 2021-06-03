package com.medfut.wechat.web;

import com.medfut.common.beans.ResultBean;
import com.medfut.common.enums.ResultStatus;
import com.medfut.wechat.core.exceptions.WeChatException;
import com.medfut.wechat.core.model.PubWeChat;
import com.medfut.wechat.service.PubWeChatService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 公众号
 */
@RequestMapping("/pub")
@RestController
public class PubWeChatController {

    /**
     * 微信公众平台账号服务
     */
    @Resource(name = "pubWeChatService")
    private PubWeChatService pubWeChatService;

    @PostMapping("/")
    public ResultBean<String> addPubWeChat(@RequestBody PubWeChat pubWeChat) {
        try {
            return this.pubWeChatService.savePubWeChat(pubWeChat);
        } catch (WeChatException e) {
            return new ResultBean<>(false, ResultStatus.SERVER_ERROR, "添加微信公众平台账号失败:服务器出现异常.");
        }
    }

    /**
     * 查询微信公众账号信息：精确查询
     * @param weChatKey
     * @return
     */
    @GetMapping("/{weChatKey}")
    public ResultBean<String> queryPubWeChat(@PathVariable(value = "weChatKey") String weChatKey) {

        return null;
    }
}
