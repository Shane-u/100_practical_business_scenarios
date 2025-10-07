package com.shane.common;

import lombok.Data;

/**
 * @Author: Shane
 * @Date: 2025/10/07/11:50
 * @Description:
 */
@Data
public class Result<T> {

    /**
     * 请求是否处理成功？
     */
    private boolean success;
    /**
     * 数据，泛型类型，后端需要返回给前端的业务数据可以放到这个里面
     */
    public T data;
    /**
     * 提示消息，如success为false的时给用户的提示信息
     */
    private String msg;
    /**
     * 错误编码，某些情况下，后端可以给前端提供详细的错误编码，前端可以根据不同的编码做一些不同的操作
     */
    private String code;


    public Result(boolean success, T data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    public Result(boolean success, T data, String code, String msg) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
