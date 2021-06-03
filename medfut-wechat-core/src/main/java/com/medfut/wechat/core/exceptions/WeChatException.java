package com.medfut.wechat.core.exceptions;

import com.medfut.common.exceptions.BaseException;

public class WeChatException extends BaseException {
    public WeChatException(String message) {
        super(message);
    }

    public WeChatException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeChatException(Throwable cause) {
        super(cause);
    }
}
