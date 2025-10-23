package com.xiaogua.comments.bean.file;

import com.xiaogua.comments.dal.entity.FileInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 文件记录表Rest
 */
@ApiModel("文件信息")
public class FileRest {

    /**
     * 文件编号
     */
    @ApiModelProperty("文件编号")
    private String code;

    /**
     * 文件名称
     */
    @ApiModelProperty("文件名称")
    private String name;

    /**
     * 文件类型
     */
    @ApiModelProperty("文件类型")
    private String contentType;

    /**
     * 文件大小
     */
    @ApiModelProperty("文件大小")
    private Integer size;

    /**
     * 文件地址
     */
    @ApiModelProperty("文件地址")
    private String url;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public FileRest() {
    }

    public FileRest(FileInfo fileInfo) {
        this.code = fileInfo.getCode();
        this.name = fileInfo.getName();
        this.contentType = fileInfo.getContentType();
        this.size = fileInfo.getSize();
        this.url = fileInfo.getUrl();
    }
}