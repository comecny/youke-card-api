package com.youke.common.result;

public class Result<T> extends BaseAPIExcution<T> {

    public Result(T t, MsgCode msgCode) {
        this.data = t;
        this.status = msgCode.isStatus();
        this.msg = msgCode.getMsg();
    }

}
