package com.xiaogua.comments.bean.user;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.User;

import java.util.List;

/**
 * 分页用户信息
 *
 * @author wangyc
 * @date 2021-02-19 17:54:43
 */
public class UserPage {

    PagingInfo pagingInfo;

    List<User> users;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
