package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 活动信息表
 * 表名 : t_activity
 * </pre>
 * @author: Mybatis Generator
 */
public class Activity {
    /**
     * <pre>
     * id
     * 表字段 : t_activity.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 活动编号
     * 表字段 : t_activity.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 创建用户编号
     * 表字段 : t_activity.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 活动名称
     * 表字段 : t_activity.name
     * </pre>
     */
    private String name;

    /**
     * <pre>
     * 活动开始时间
     * 表字段 : t_activity.start_time
     * </pre>
     */
    private String startTime;

    /**
     * <pre>
     * 活动结束时间
     * 表字段 : t_activity.end_time
     * </pre>
     */
    private String endTime;

    /**
     * <pre>
     * 活动地址
     * 表字段 : t_activity.address
     * </pre>
     */
    private String address;

    /**
     * <pre>
     * 主办单位
     * 表字段 : t_activity.organizer
     * </pre>
     */
    private String organizer;

    /**
     * <pre>
     * 承办单位
     * 表字段 : t_activity.contractor
     * </pre>
     */
    private String contractor;

    /**
     * <pre>
     * 协办单位
     * 表字段 : t_activity.supporter
     * </pre>
     */
    private String supporter;

    /**
     * <pre>
     * 消费积分
     * 表字段 : t_activity.credit
     * </pre>
     */
    private Integer credit;

    /**
     * <pre>
     * 状态
     * 表字段 : t_activity.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 活动封面图片编号
     * 表字段 : t_activity.title_page_code
     * </pre>
     */
    private String titlePageCode;

    /**
     * <pre>
     * 直播地址
     * 表字段 : t_activity.streaming_url
     * </pre>
     */
    private String streamingUrl;

    /**
     * <pre>
     * 小程序直播id
     * 表字段 : t_activity.streaming_id
     * </pre>
     */
    private String streamingId;

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_activity.update_date
     * </pre>
     */
    private String updateDate;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_activity.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * 活动详情
     * 表字段 : t_activity.description
     * </pre>
     */
    private String description;

    /**
     * <pre>
     * id
     * 表字段 : t_activity.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_activity.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 活动编号
     * 表字段 : t_activity.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 活动编号
     * 表字段 : t_activity.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 创建用户编号
     * 表字段 : t_activity.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 创建用户编号
     * 表字段 : t_activity.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 活动名称
     * 表字段 : t_activity.name
     * </pre>
     */
    public String getName() {
        return name;
    }

    /**
     * <pre>
     * 活动名称
     * 表字段 : t_activity.name
     * </pre>
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * <pre>
     * 活动开始时间
     * 表字段 : t_activity.start_time
     * </pre>
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * <pre>
     * 活动开始时间
     * 表字段 : t_activity.start_time
     * </pre>
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    /**
     * <pre>
     * 活动结束时间
     * 表字段 : t_activity.end_time
     * </pre>
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * <pre>
     * 活动结束时间
     * 表字段 : t_activity.end_time
     * </pre>
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    /**
     * <pre>
     * 活动地址
     * 表字段 : t_activity.address
     * </pre>
     */
    public String getAddress() {
        return address;
    }

    /**
     * <pre>
     * 活动地址
     * 表字段 : t_activity.address
     * </pre>
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * <pre>
     * 主办单位
     * 表字段 : t_activity.organizer
     * </pre>
     */
    public String getOrganizer() {
        return organizer;
    }

    /**
     * <pre>
     * 主办单位
     * 表字段 : t_activity.organizer
     * </pre>
     */
    public void setOrganizer(String organizer) {
        this.organizer = organizer == null ? null : organizer.trim();
    }

    /**
     * <pre>
     * 承办单位
     * 表字段 : t_activity.contractor
     * </pre>
     */
    public String getContractor() {
        return contractor;
    }

    /**
     * <pre>
     * 承办单位
     * 表字段 : t_activity.contractor
     * </pre>
     */
    public void setContractor(String contractor) {
        this.contractor = contractor == null ? null : contractor.trim();
    }

    /**
     * <pre>
     * 协办单位
     * 表字段 : t_activity.supporter
     * </pre>
     */
    public String getSupporter() {
        return supporter;
    }

    /**
     * <pre>
     * 协办单位
     * 表字段 : t_activity.supporter
     * </pre>
     */
    public void setSupporter(String supporter) {
        this.supporter = supporter == null ? null : supporter.trim();
    }

    /**
     * <pre>
     * 消费积分
     * 表字段 : t_activity.credit
     * </pre>
     */
    public Integer getCredit() {
        return credit;
    }

    /**
     * <pre>
     * 消费积分
     * 表字段 : t_activity.credit
     * </pre>
     */
    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    /**
     * <pre>
     * 状态
     * 表字段 : t_activity.status
     * </pre>
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 状态
     * 表字段 : t_activity.status
     * </pre>
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <pre>
     * 活动封面图片编号
     * 表字段 : t_activity.title_page_code
     * </pre>
     */
    public String getTitlePageCode() {
        return titlePageCode;
    }

    /**
     * <pre>
     * 活动封面图片编号
     * 表字段 : t_activity.title_page_code
     * </pre>
     */
    public void setTitlePageCode(String titlePageCode) {
        this.titlePageCode = titlePageCode == null ? null : titlePageCode.trim();
    }

    /**
     * <pre>
     * 直播地址
     * 表字段 : t_activity.streaming_url
     * </pre>
     */
    public String getStreamingUrl() {
        return streamingUrl;
    }

    /**
     * <pre>
     * 直播地址
     * 表字段 : t_activity.streaming_url
     * </pre>
     */
    public void setStreamingUrl(String streamingUrl) {
        this.streamingUrl = streamingUrl == null ? null : streamingUrl.trim();
    }

    /**
     * <pre>
     * 小程序直播id
     * 表字段 : t_activity.streaming_id
     * </pre>
     */
    public String getStreamingId() {
        return streamingId;
    }

    /**
     * <pre>
     * 小程序直播id
     * 表字段 : t_activity.streaming_id
     * </pre>
     */
    public void setStreamingId(String streamingId) {
        this.streamingId = streamingId == null ? null : streamingId.trim();
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_activity.update_date
     * </pre>
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_activity.update_date
     * </pre>
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_activity.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_activity.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    /**
     * <pre>
     * 活动详情
     * 表字段 : t_activity.description
     * </pre>
     */
    public String getDescription() {
        return description;
    }

    /**
     * <pre>
     * 活动详情
     * 表字段 : t_activity.description
     * </pre>
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}