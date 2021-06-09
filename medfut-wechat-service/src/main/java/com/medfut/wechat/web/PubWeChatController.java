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
            return new ResultBean<>(false, ResultStatus.SERVER_ERROR.getCode(), "添加微信公众平台账号失败:服务器出现异常.");
        }
    }


    /**
     * 启用微信公众号
     * @param weChatKey
     * @return
     */
    @PutMapping("/{weChatKey}/en")
    public ResultBean<String> enablePubWeChat(@PathVariable(value = "weChatKey") String weChatKey){

        return null;

    }

    /**
     * 禁用微信公众号
     * @param weChatKey
     * @return
     */
    @PutMapping("/{weChatKey}/dis")
    public ResultBean<String> disablePubWeChat(@PathVariable(value = "weChatKey") String weChatKey){

        return null;

    }

    @DeleteMapping("/{weChatKey}")
    public ResultBean<String> deletePubWeChat(@PathVariable(value = "weChatKey") String weChatKey){

        return null;

    }

    /**
     * 查询微信公众账号信息：精确查询
     *
     * @param weChatKey
     * @return
     */
    @GetMapping("/{weChatKey}")
    public ResultBean<PubWeChat> queryPubWeChat(@PathVariable(value = "weChatKey") String weChatKey) {
        try {
            return this.pubWeChatService.queryPubWeChatByKey(weChatKey);
        } catch (WeChatException e) {
            return new ResultBean<PubWeChat>(false, ResultStatus.SERVER_ERROR.getCode(), "查询微信公众账号失败:服务器出现异常.");
        }
    }
}
