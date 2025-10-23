package com.xiaogua.comments.bean.knowledge;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * admin-干货分页信息
 */
@ApiModel("admin-干货分页信息")
public class KnowledgeAdminPageInfo extends KnowledgePageInfo {

    /**
     * 上传人用户编号
     */
    @ApiModelProperty("上传人用户编号")
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}