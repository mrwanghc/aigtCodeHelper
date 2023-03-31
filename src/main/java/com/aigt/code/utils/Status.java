package com.aigt.code.utils;

import java.io.Serializable;

/**
 * 生效状态 包括状态和失效原因
 *
 * @author liuyiyang Email:liuyiyang@co-mall.com
 * @version 3.2.1
 * @company 北京科码先锋互联网技术股份有限公司@版权所有
 * @since 2016年12月27日 16:08
 */
public class Status implements Serializable {

    private static final long serialVersionUID = -7774112129777095481L;
    /**
     * 是否有效，true 有效，false 失效
     */
    private final boolean validate;
    /**
     * 失效原因
     */
    private final String reason;

    /**
     * 构造购物项有效状态
     */
    public Status() {
        this.validate = true;
        this.reason = null;
    }
    /**
     * 构造购物项失效状态
     */
    public Status(String reason) {
        this.validate = false;
        this.reason = reason;
    }

    /**
     * 获取状态 true 有效， false 失效
     * @return
     */
    public boolean isValidate() {
        return validate;
    }

    /**
     * 获取失效原因
     * @return
     */
    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "BasketItemStatus{" +
                "validate=" + validate +
                ", reason='" + reason + '\'' +
                '}';
    }
}
