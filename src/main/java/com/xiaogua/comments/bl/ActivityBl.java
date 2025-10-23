package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.activity.ActivityPage;
import com.xiaogua.comments.bean.activity.ActivityPageCommonInfo;
import com.xiaogua.comments.bean.activity.ActivitySubmit;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.Activity;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.mapper.ActivityMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.PagingUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 活动 bl
 *
 * @author wangyc
 * @date 2021-6-2
 */
@Service
public class ActivityBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ActivityMapper activityMapper;


    /**
     * 保存活动用户信息
     *
     * @param submit 活动用户提交信息
     * @param user 用户信息
     * @return
     */
    public Activity save(ActivitySubmit submit, User user) {
        Activity saveActivity = null;
        // 更新
        if (!StringUtils.isEmpty(submit.getCode())) {
            saveActivity = updateActivity(submit, user.getCode());
        } else {
            // 新增
            List<Activity> existActivity = activityMapper.selectByName(submit.getName());
            if (!CollectionUtils.isEmpty(existActivity)) {
                throw new CommentsRuntimeException(-1, "此活动名称已存在");
            }
            saveActivity = mappingActivity(submit, user);
            activityMapper.insertSelective(saveActivity);
        }

        return saveActivity;
    }

    /**
     * 更新活动
     * @param activity 活动提交信息
     * @param userCode 更新人
     * @return
     */
    private Activity updateActivity(ActivitySubmit activity, String userCode) {
        List<Activity> existActivities = activityMapper.selectByName(activity.getName());
        if (!CollectionUtils.isEmpty(existActivities)) {
            List<Activity> sameNameActivities = existActivities.stream().filter(a -> !activity.getCode().equals(a.getCode())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(sameNameActivities)) {
                throw new CommentsRuntimeException(-1, "此活动名称已存在");
            }
        }

        Activity existActivity = activityMapper.selectByCode(activity.getCode());
        if (existActivity == null) {
            throw new CommentsRuntimeException(-1, "活动信息不存在");
        }

        if (StatusCode.ACTIVITY_SUCCESS.getCode() == existActivity.getStatus()) {
            throw new CommentsRuntimeException(-1, "活动已结束，不可更新");
        }

        if (!existActivity.getTitlePageCode().equals(activity.getTitlePageCode())) {
            existActivity.setTitlePageCode(activity.getTitlePageCode());
        }

        if (!existActivity.getAddress().equals(activity.getAddress())) {
            existActivity.setAddress(activity.getAddress());
        }

        if (!existActivity.getCredit().equals(activity.getCredit())) {
            existActivity.setCredit(activity.getCredit());
        }

        if (!existActivity.getDescription().equals(activity.getDescription())) {
            existActivity.setDescription(activity.getDescription());
        }

        if (!existActivity.getStreamingUrl().equals(activity.getStreamingUrl())) {
            existActivity.setStreamingUrl(activity.getStreamingUrl());
        }

        if (!existActivity.getStreamingId().equals(activity.getStreamingId())) {
            existActivity.setStreamingId(activity.getStreamingId());
        }

        if (!existActivity.getName().equals(activity.getName())) {
            existActivity.setName(activity.getName());
        }

        if (!existActivity.getOrganizer().equals(activity.getOrganizer())) {
            existActivity.setOrganizer(activity.getOrganizer());
        }

        if (!existActivity.getContractor().equals(activity.getContractor())) {
            existActivity.setContractor(activity.getContractor());
        }

        if (!existActivity.getSupporter().equals(activity.getSupporter())) {
            existActivity.setSupporter(activity.getSupporter());
        }

        if (!existActivity.getStartTime().equals(activity.getStartTime())) {
            existActivity.setStartTime(activity.getStartTime());
        }

        if (!existActivity.getEndTime().equals(activity.getEndTime())) {
            existActivity.setEndTime(activity.getEndTime());
        }

        existActivity.setUserCode(userCode);
        existActivity.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        activityMapper.updateByPrimaryKeySelective(existActivity);
        return existActivity;
    }

    /**
     * 查询活动信息
     *
     * @param activityCode 活动编号
     * @return
     */
    public Activity selectByCode(String activityCode) {
        Activity activity = activityMapper.selectByCode(activityCode);
        return activity;
    }

    /**
     * 更新活动状态
     *
     * @param activityCode 活动编号
     * @param status 状态
     * @return
     */
    public void updateStatus(String activityCode, Integer status) {
        Activity activity = activityMapper.selectByCode(activityCode);
        if (activity == null) {
            throw new CommentsRuntimeException(-1, "活动信息不存在");
        }
        activity.setStatus(status);
        activity.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        activityMapper.updateByPrimaryKeySelective(activity);
    }

    /**
     * 更新直播地址
     * @param code 活动编号
     * @param streamingUrl 直播地址
     */
    public void updateStreamingUrl(String code, String streamingUrl) {
        Activity activity = activityMapper.selectByCode(code);
        if (activity == null) {
            throw new CommentsRuntimeException(-1, "活动信息不存在");
        }
        activity.setStreamingUrl(streamingUrl);
        activity.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        activityMapper.updateByPrimaryKeySelective(activity);
    }

    /**
     * 更新小程序直播id
     * @param code 活动编号
     * @param streamingId 小程序直播id
     */
    public void updateStreamingId(String code, String streamingId) {
        Activity activity = activityMapper.selectByCode(code);
        if (activity == null) {
            throw new CommentsRuntimeException(-1, "活动信息不存在");
        }
        activity.setStreamingId(streamingId);
        activity.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        activityMapper.updateByPrimaryKeySelective(activity);
    }

    /**
     * 转换
     * @param submit 活动提交信息
     * @param user 用户信息
     * @return
     */
    private Activity mappingActivity(ActivitySubmit submit, User user) {
        Activity activity = new Activity();
        activity.setCode(BizCodeUtil.genLongBizCode(TableCode.T_ACTIVITY.getCode()));
        activity.setName(submit.getName());
        activity.setUserCode(user.getCode());
        activity.setAddress(submit.getAddress());
        activity.setCredit(submit.getCredit());
        activity.setDescription(submit.getDescription());
        activity.setStreamingUrl(submit.getStreamingUrl());
        activity.setStreamingId(submit.getStreamingId());
        activity.setOrganizer(submit.getOrganizer());
        activity.setContractor(submit.getContractor());
        activity.setSupporter(submit.getSupporter());
        activity.setStartTime(submit.getStartTime());
        activity.setEndTime(submit.getEndTime());
        activity.setTitlePageCode(submit.getTitlePageCode());
        activity.setStatus(StatusCode.ACTIVITY_UNREADY.getCode());
        activity.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        activity.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        return activity;
    }

    /**
     * 分页获取活动用户信息
     *
     * @param pageInfo 分页信息
     * @return
     */
    public ActivityPage getPage(ActivityPageCommonInfo pageInfo) {
        int count = activityMapper.count(pageInfo.getName(), pageInfo.getIsFree(), pageInfo.getStatusList());;

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<Activity> activities = activityMapper.findPage(pagingInfo.getIndex(),
            pagingInfo.getPageSize(),
            pageInfo.getName(),
            pageInfo.getIsFree(),
            pageInfo.getStatusList());

        ActivityPage activityPage = new ActivityPage();

        if (!CollectionUtils.isEmpty(activities)) {
            activityPage.setActivities(activities);
        }
        activityPage.setPagingInfo(pagingInfo);
        return activityPage;
    }

}
