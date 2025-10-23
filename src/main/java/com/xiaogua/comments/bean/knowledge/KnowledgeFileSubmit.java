package com.xiaogua.comments.bean.knowledge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 干货文件提交信息
 */
@ApiModel(value = "干货文件提交信息")
public class KnowledgeFileSubmit {

    /**
     * 文件code
     */
    @ApiModelProperty("文件code")
    private String fileCode;

    /**
     * 序号
     */
    @ApiModelProperty("序号")
    private Integer detno;

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public Integer getDetno() {
        return detno;
    }

    public void setDetno(Integer detno) {
        this.detno = detno;
    }
}