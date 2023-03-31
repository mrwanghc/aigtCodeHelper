package com.aigt.code.utils;


/**
 * 分页类参数异常
 */
public class PaginationResultParamException extends BaseRuntimeException {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    public PaginationResultParamException() {
        super();
    }

    public PaginationResultParamException(String message){
        super(message);
    }
}
