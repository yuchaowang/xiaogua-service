package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.user.UserTypeApplyRest;
import com.xiaogua.comments.bean.user.UserTypeFileRest;
import com.xiaogua.comments.bean.user.UserTypeRest;
import com.xiaogua.comments.bean.user.UserTypeSubmit;
import com.xiaogua.comments.bl.UserTypeBl;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.dal.entity.UserType;
import com.xiaogua.comments.dal.mapper.UserTypeMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户类型 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class UserTypeDomain {

    @Autowired
    private UserTypeBl userTypeBl;

    @Autowired
    UserTypeFileDomain userTypeFileDomain;

    @Autowired
    UserTypeApplyDomain userTypeApplyDomain;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    UserTypeMapper userTypeMapper;

    @Autowired
    UserDomain userDomain;

    /**
     * 更新用户类型
     *
     * @param submit
     * @param userCode
     * @return
     */
    public ResponseBuilder update(UserTypeSubmit submit, String userCode) {
        userTypeBl.addUserType(userCode, submit);

        userDomain.creditBycompleteUser(userCode);
        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 转换用户类型信息Rest
     *
     * @param code 用户类型code
     * @return
     */
    public UserTypeRest getUserTypeRest(String code) {
        UserType userType = userTypeBl.getUserType(code);
        UserTypeRest userTypeRest = convertUserTypeRest(userType);
        return userTypeRest;
    }

    /**
     * 审核成功后更新用户类型信息
     *
     * @param userCode
     * @param userTypeCode
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateAfterApplyAuditSuccess(String userCode, String userTypeCode) {
        List<UserType> userTypes = userTypeBl.getUserTypeList(userCode);

        if (CollectionUtils.isEmpty(userTypes)) {
            throw new CommentsRuntimeException(-1, "没有发现用户类型信息");
        }

        boolean hasUnaduitUserType = false;
        for (UserType userType : userTypes) {
            if (userType.getStatus().intValue() == StatusCode.USER_TYPE_STATUS_UNADUIT.getCode() && userType.getCode()
                                                                                                            .equals(
                                                                                                                userTypeCode)) {
                hasUnaduitUserType = true;

                userType.setStatus(StatusCode.USER_TYPE_STATUS_ADUIT.getCode());
                userTypeMapper.updateByPrimaryKeySelective(userType);
            } else {
                userType.setDeleted(true);
                userTypeMapper.updateByPrimaryKeySelective(userType);
            }
        }
        if (!hasUnaduitUserType) {
            throw new CommentsRuntimeException(-1, "同步用户类型信息失败:没有待审核的对应用户类型信息");
        }
    }

    /**
     * 审核成功后更新用户类型信息
     *
     * @param userCode
     * @param userTypeCode
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateAfterApplyAuditFailed(String userCode, String userTypeCode) {
        List<UserType> userTypes = userTypeBl.getUserTypeList(userCode);

        if (CollectionUtils.isEmpty(userTypes)) {
            throw new CommentsRuntimeException(-1, "没有发现用户类型信息");
        }

        boolean hasUnaduitUserType = false;
        for (UserType userType : userTypes) {
            if (userType.getStatus().intValue() == StatusCode.USER_TYPE_STATUS_UNADUIT.getCode() && userType.getCode()
                                                                                                            .equals(
                                                                                                                userTypeCode)) {
                hasUnaduitUserType = true;

                userType.setStatus(StatusCode.USER_TYPE_STATUS_ADUIT_FAILED.getCode());
                userTypeMapper.updateByPrimaryKeySelective(userType);
            }
        }
        if (!hasUnaduitUserType) {
            throw new CommentsRuntimeException(-1, "同步用户类型信息失败:没有待审核的对应用户类型信息");
        }
    }

    /**
     * 转换用户类型信息Rest
     *
     * @param userCode 用户code
     * @return
     */
    public List<UserTypeRest> getUserTypeRests(String userCode) {
        List<UserType> userTypes = userTypeBl.getUserTypeList(userCode);

        List<UserTypeRest> userTypeRests = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userTypes)) {
            userTypeRests = convertUserTypeRests(userTypes);
        }
        return userTypeRests;
    }

    /**
     * 批量转换用户类型信息
     *
     * @param userTypes userTypes
     * @return
     */
    private List<UserTypeRest> convertUserTypeRests(List<UserType> userTypes) {
        List<UserTypeRest> userTypeRests = new ArrayList<>();
        for (UserType userType : userTypes) {
            userTypeRests.add(convertUserTypeRest(userType));
        }

        return userTypeRests;
    }

    /**
     * 转换userType
     *
     * @param userType userType
     * @return
     */
    private UserTypeRest convertUserTypeRest(UserType userType) {
        UserTypeRest userTypeRest = new UserTypeRest(userType);

        List<UserTypeFileRest> userTypeFiles =
            userTypeFileDomain.getUserTypeFileRests(userType.getUserCode(), userType.getType());
        if (!CollectionUtils.isEmpty(userTypeFiles)) {
            userTypeRest.setUserTypeFiles(userTypeFiles);
        }

        // 审核不通过，补充申请信息
        if (userType.getStatus().intValue() == StatusCode.USER_TYPE_STATUS_ADUIT_FAILED.getCode()) {
            UserTypeApplyRest userTypeApplyRest = userTypeApplyDomain.getSimpleApplyRest(userType.getCode());
            userTypeRest.setUserTypeApplyRest(userTypeApplyRest);
        }
        return userTypeRest;
    }
}


