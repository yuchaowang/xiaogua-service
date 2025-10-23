package com.xiaogua.comments.bean.activity;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.Activity;
import java.util.List;
import lombok.Data;

/**
 * 分页活动用户信息
 *
 * @author wangyc
 * @date 2020-11-17 17:09
 */
@Data
public class ActivityPage {

    PagingInfo pagingInfo;

    List<Activity> activities;

}
