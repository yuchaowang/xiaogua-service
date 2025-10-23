package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.bean.user.UserTypeSubmit;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.UserType;
import com.xiaogua.comments.dal.mapper.UserTypeMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户类型 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:36
 */
@Service
public class UserTypeBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTypeBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserTypeMapper userTypeMapper;

    @Autowired
    private UserTypeFileBl userTypeFileBl;

    @Autowired
    private UserTypeApplyBl userTypeApplyBl;

    /**
     * 初始化用户类型
     *
     * @param code 用户编号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public UserType initUserType(String code) {
        VerifyParamsUtil.hasText(code, "用户编号不可为空");

        UserType userType = init(code);

        userTypeMapper.insertSelective(userType);
        return userType;
    }

    /**
     * 添加用户类型
     *
     * @param userCode       userCode
     * @param userTypeSubmit userTypeSubmit
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUserType(String userCode, UserTypeSubmit userTypeSubmit) {
        if (userTypeSubmit.getType() == null || (
            userTypeSubmit.getType().intValue() != StatusCode.USER_TYPE_AMATEUR.getCode()
                && userTypeSubmit.getType().intValue() != StatusCode.USER_TYPE_EXPERT.getCode()
                && userTypeSubmit.getType().intValue() != StatusCode.USER_TYPE_DEVELOPER.getCode()
                && userTypeSubmit.getType().intValue() != StatusCode.USER_TYPE_CONSUMER.getCode())) {
            throw new CommentsRuntimeException(-1, "用户类型错误");
        }

        List<UserType> unAduitUserTypes =
            userTypeMapper.selectByUserCodeAndStatus(userCode, StatusCode.USER_TYPE_STATUS_UNADUIT.getCode());
        if (!CollectionUtils.isEmpty(unAduitUserTypes)) {
            throw new CommentsRuntimeException(-1, "您还有类型申请未审核，请待审核完成后继续操作");
        }

        // 清空原来审核失败
        clearUserTypeAuditFailed(userCode);

        UserType userType = userTypeMapper.selectByUserCodeAndType(userCode, userTypeSubmit.getType());
        if (userType != null) {
            // 更新
            if (StatusCode.USER_TYPE_STATUS_UNADUIT.getCode() == userType.getStatus()) {
                throw new CommentsRuntimeException(-1, "此类型您已申请，请待审核完成后继续操作");
            } else if (userType.getType().intValue() == StatusCode.USER_TYPE_EXPERT.getCode()) {
                // 专家需要审核所以新增申请
                userType = createUserType(userCode, userTypeSubmit);
                userTypeApplyBl.addUserTypeApply(userType);
                userTypeMapper.insertSelective(userType);
            } else {
                userType.setBrief(userTypeSubmit.getBrief());
                userType.setStatus(StatusCode.USER_TYPE_STATUS_UNADUIT.getCode());
                userType.setCompany(userTypeSubmit.getCompany());
                userType.setName(userTypeSubmit.getName());
                userType.setPosition(userTypeSubmit.getPosition());
                userType.setUsedBrand(userTypeSubmit.getUsedBrand());
                userType.setUsedProduct(userTypeSubmit.getUsedProduct());

                // 不是专家就直接审核通过
                if (StatusCode.USER_TYPE_EXPERT.getCode() != userType.getType().intValue()) {
                    userType.setStatus(StatusCode.USER_TYPE_STATUS_ADUIT.getCode());
                } else {
                    userTypeApplyBl.addUserTypeApply(userType);
                }
                userTypeMapper.updateByPrimaryKeySelective(userType);
            }
        } else {
            // 新增
            userType = createUserType(userCode, userTypeSubmit);

            // 不是专家就直接审核通过
            if (StatusCode.USER_TYPE_EXPERT.getCode() != userType.getType().intValue()) {
                userType.setStatus(StatusCode.USER_TYPE_STATUS_ADUIT.getCode());
                // 处理其他类型，保证只有一个生效
                updateOtherType(userCode, userType.getType());
            } else {
                userTypeApplyBl.addUserTypeApply(userType);
            }
            userTypeMapper.insertSelective(userType);
        }

        // 更新文件信息
        if (!CollectionUtils.isEmpty(userTypeSubmit.getUserTypeFileSubmits())) {
            userTypeFileBl.deleteByUserAndType(userCode, userType.getType());
            userTypeFileBl.save(userCode, userType.getType(), userTypeSubmit.getUserTypeFileSubmits());
        }
    }

    /**
     * 处理其他用户类型信息
     *
     * @param userCode
     * @param currentType
     */
    @Transactional(rollbackFor = Exception.class)
    protected void updateOtherType(String userCode, Integer currentType) {
        List<UserType> userTypes = userTypeMapper.selectByUserCode(userCode);
        if (!CollectionUtils.isEmpty(userTypes)) {
            for (UserType userType : userTypes) {
                if (userType.getType().intValue() != currentType.intValue()) {
                    userType.setDeleted(true);
                    userTypeMapper.updateByPrimaryKeySelective(userType);
                }
            }
        }
    }

    /**
     * 处理其他用户类型信息
     *
     * @param userCode userCode
     */
    @Transactional(rollbackFor = Exception.class)
    protected void clearUserTypeAuditFailed(String userCode) {
        List<UserType> auditFailedUserTypes =
            userTypeMapper.selectByUserCodeAndStatus(userCode, StatusCode.USER_TYPE_STATUS_ADUIT_FAILED.getCode());

        if (!CollectionUtils.isEmpty(auditFailedUserTypes)) {
            for (UserType userType : auditFailedUserTypes) {
                userType.setDeleted(true);
                userTypeMapper.updateByPrimaryKeySelective(userType);
            }
        }
    }

    /**
     * 获取现有用户类型列表
     *
     * @param userCode 用户编号
     * @return
     */
    public List<UserType> getUserTypeList(String userCode) {
        List<UserType> userTypes = userTypeMapper.selectByUserCode(userCode);
        return userTypes;
    }

    /**
     * 获取用户类型
     *
     * @param code 用户类型编号
     * @return
     */
    public UserType getUserType(String code) {
        UserType userType = userTypeMapper.selectByCode(code);
        if (userType == null) {
            throw new CommentsRuntimeException("用户类型不存在");
        }
        return userType;
    }

    /**
     * 获取当前用户类型
     *
     * @param userCode
     * @return
     */
    public UserType getCurrentUserType(String userCode) {
        List<UserType> userTypes =
            userTypeMapper.selectByUserCodeAndStatus(userCode, StatusCode.USER_TYPE_STATUS_ADUIT.getCode());
        if (CollectionUtils.isEmpty(userTypes)) {
            throw new CommentsRuntimeException("用户类型不存在");
        }
        return userTypes.get(0);
    }

    /**
     * 创建新用户类型
     *
     * @param userCode userCode
     * @param submit   submit
     * @return
     */
    private UserType createUserType(String userCode, UserTypeSubmit submit) {
        UserType userType = new UserType();
        userType.setCode(BizCodeUtil.genLongBizCode(TableCode.T_USER_TYPE.getCode()));
        userType.setStatus(StatusCode.USER_TYPE_STATUS_UNADUIT.getCode());
        userType.setType(submit.getType());
        userType.setUserCode(userCode);
        userType.setName(submit.getName());
        userType.setBrief(submit.getBrief());
        userType.setCompany(submit.getCompany());
        userType.setPosition(submit.getPosition());
        userType.setUsedBrand(submit.getUsedBrand());
        userType.setUsedProduct(submit.getUsedProduct());
        userType.setDeleted(false);
        userType.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        return userType;
    }

    /**
     * 初始化用户类型
     *
     * @param code 用户编号
     * @return
     */
    private UserType init(String code) {
        UserType userType = new UserType();
        userType.setCode(BizCodeUtil.genLongBizCode(TableCode.T_USER_TYPE.getCode()));
        userType.setStatus(StatusCode.USER_TYPE_STATUS_ADUIT.getCode());
        userType.setType(StatusCode.USER_TYPE_AMATEUR.getCode());
        userType.setUserCode(code);
        userType.setDeleted(false);
        userType.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        return userType;
    }
}
