package com.medfut.wechat.dao.mapper;

import com.medfut.wechat.dao.dto.PubWeChatDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository(value = "pubWeChatMapper")
public interface PubWeChatMapper {

    /**
     * 插入公众号
     * @param pubWeChatDto
     * @return
     * @throws Exception
     */
    int insertPubWeChat(@Param(value = "pubWeChat")PubWeChatDto pubWeChatDto)throws Exception;

    /**
     * 查询公众号信息：根据账号id查询
     * @param accountId
     * @return
     * @throws Exception
     */
    PubWeChatDto selectPubWeChatById(@Param(value = "accountId")String accountId)throws Exception;

    /**
     * 查询公众号信息：根据微信号查询
     * @param pubWeChatNo
     * @return
     * @throws Exception
     */
    PubWeChatDto selectPubWeChatByNo(@Param(value = "pubWeChatNo")String pubWeChatNo)throws Exception;


    /**
     * 查询公众号信息：根据账号id，和微信号查询
     * @param weChatKey
     * @return
     * @throws Exception
     */
    PubWeChatDto selectPubWeChatByKey(@Param(value = "weChatKey")String weChatKey)throws Exception;


    /**
     * 更新公众号状态
     * @param weChatKey
     * @param accountStatus
     * @param updateUser
     * @param updateTime
     * @return
     * @throws Exception
     */
    int updatePubWeChatStatus(@Param(value = "weChatKey")String weChatKey,
                              @Param(value = "accountStatus")int accountStatus,
                              @Param(value = "updateUser")String updateUser,
                              @Param(value = "updateTime") LocalDateTime updateTime
                              )throws Exception;


}
