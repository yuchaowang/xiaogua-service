package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.activity.ActivityUserPage;
import com.xiaogua.comments.bean.activity.ActivityUserPageInfo;
import com.xiaogua.comments.bean.activity.ActivityUserSubmit;
import com.xiaogua.comments.bean.brand.BrandPage;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.ActivityUser;
import com.xiaogua.comments.dal.entity.Brand;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.mapper.ActivityUserMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.PagingUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 活动用户 bl
 *
 * @author wangyc
 * @date 2021-5-16
 */
@Service
public class ActivityUserBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityUserBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ActivityUserMapper activityUserMapper;


    /**
     * 保存活动用户信息
     *
     * @param submit 活动用户提交信息
     * @param user 用户信息
     * @return
     */
    public ActivityUser save(ActivityUserSubmit submit, User user) {
        ActivityUser existUser = activityUserMapper.selectByActivityCodeAndUserCode(submit.getActivityCode(), user.getCode());
        if (existUser != null) {
            throw new CommentsRuntimeException(-1, "您已报名成功，无需重复报名");
        }

        ActivityUser activityUser = mappingActivityUser(submit, user);
        activityUserMapper.insert(activityUser);
        return activityUser;
    }

    /**
     * 验证活动用户是否存在
     *
     * @param activityCode 活动编号
     * @param userCode 用户编号
     * @return
     */
    public ActivityUser validateExist(String activityCode, String userCode) {
        ActivityUser existUser = activityUserMapper.selectByActivityCodeAndUserCode(activityCode, userCode);
        return existUser;
    }

    /**
     * 转换
     * @param submit 活动用户提交信息
     * @param user 用户信息
     * @return
     */
    private ActivityUser mappingActivityUser(ActivityUserSubmit submit, User user) {
        ActivityUser activityUser = new ActivityUser();
        activityUser.setCode(BizCodeUtil.genLongBizCode(TableCode.T_ACTIVITY_USER.getCode()));
        activityUser.setActivityCode(submit.getActivityCode());
        activityUser.setUserCode(user.getCode());
        activityUser.setCompany(submit.getCompany());
        activityUser.setName(submit.getName());
        activityUser.setMobile(StringUtils.isEmpty(submit.getMobile()) ? user.getMobile() : submit.getMobile());
        activityUser.setEmail(StringUtils.isEmpty(submit.getEmail()) ? user.getEmail() : submit.getEmail());
        activityUser.setPosition(submit.getPosition());
        activityUser.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        activityUser.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        return activityUser;
    }

    /**
     * 分页获取活动用户信息
     *
     * @param pageInfo 分页信息
     * @return
     */
    public ActivityUserPage getPage(ActivityUserPageInfo pageInfo) {
        int count = activityUserMapper.count(pageInfo.getActivityCode(), pageInfo.getUserCode());;

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<ActivityUser> activityUsers = activityUserMapper.findPage(pagingInfo.getIndex(),
            pagingInfo.getPageSize(),
            pageInfo.getActivityCode(),
            pageInfo.getUserCode());

        ActivityUserPage activityUserPage = new ActivityUserPage();

        if (!CollectionUtils.isEmpty(activityUsers)) {
            activityUserPage.setActivityUsers(activityUsers);
        }
        activityUserPage.setPagingInfo(pagingInfo);
        return activityUserPage;
    }

    /**
     * 获取参加活动全部用户信息
     * @param activityCode 活动编号
     * @return
     */
    public List<ActivityUser> getAllUsers(String activityCode) {
        List<ActivityUser> activityUsers = activityUserMapper.findByActivityCode(activityCode);

        return activityUsers;
    }

    /**
     * 获取单个活动用户信息
     * @param activityUserCode 活动用户编号
     * @return
     */
    public ActivityUser get(String activityUserCode) {
        ActivityUser activityUser = activityUserMapper.selectByCode(activityUserCode);

        return activityUser;
    }
}
