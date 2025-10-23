package com.xiaogua.comments.bean.user;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.UserTypeApply;
import java.util.List;

/**
 * 分页用户类型申请
 *
 * @author wangyc
 * @date 2020-11-17 17:09
 */
public class UserTypeApplyPage {

    PagingInfo pagingInfo;

    List<UserTypeApply> userTypeApplyList;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<UserTypeApply> getUserTypeApplyList() {
        return userTypeApplyList;
    }

    public void setUserTypeApplyList(List<UserTypeApply> userTypeApplyList) {
        this.userTypeApplyList = userTypeApplyList;
    }
}
