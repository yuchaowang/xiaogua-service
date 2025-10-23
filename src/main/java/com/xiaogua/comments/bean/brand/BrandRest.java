package com.xiaogua.comments.bean.brand;

import com.xiaogua.comments.bean.comments.CommentsInfoRest;
import com.xiaogua.comments.dal.entity.Brand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wangyc
 * @date 2020-11-17 17:16
 */
@ApiModel("品牌信息")
public class BrandRest extends BrandSimpleRest {

    /**
     * 点赞最高评论
     */
    @ApiModelProperty("点赞最高评论")
    private CommentsInfoRest commentsInfoRest;

    public CommentsInfoRest getCommentsInfoRest() {
        return commentsInfoRest;
    }

    public void setCommentsInfoRest(CommentsInfoRest commentsInfoRest) {
        this.commentsInfoRest = commentsInfoRest;
    }

    public BrandRest() {
        super();
    }

    public BrandRest(Brand brand) {
        super(brand);
    }
}
