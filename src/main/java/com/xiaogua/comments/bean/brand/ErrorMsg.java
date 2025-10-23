package com.xiaogua.comments.bean.brand;

/**
 * 品牌错误信息
 *
 * @author wangyc
 * @date 2020-11-19 15:30
 */
public class ErrorMsg {

    /**
     * 属性名
     */
    private String name;

    /**
     * 错误信息
     */
    private String msg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ErrorMsg() {
    }

    public ErrorMsg(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }
}
