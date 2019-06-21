package com.it.demosso.pojo;

import lombok.Data;

/**
 * 返回的通用对象
 *
 * @author rcl
 * @date 2018/6/9 23:58
 */
@Data
public class OptResult {

    /**
     * 操作结果 1:成功，0：失败
     */
    private int state;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据对象
     */
    private Object data;

    public OptResult(int state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public OptResult() {

    }
}
