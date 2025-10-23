package com.xiaogua.comments.bean.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户类型文件信息表Submit
 */
@ApiModel("用户类型文件信息表Submit")
public class UserTypeFileSubmit {

    /**
     * 文件编号
     */
    @ApiModelProperty("文件编号")
    private String fileCode;

    /**
     * 文件序号
     */
    @ApiModelProperty("文件序号")
    private Integer fileDetno;

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public Integer getFileDetno() {
        return fileDetno;
    }

    public void setFileDetno(Integer fileDetno) {
        this.fileDetno = fileDetno;
    }
}