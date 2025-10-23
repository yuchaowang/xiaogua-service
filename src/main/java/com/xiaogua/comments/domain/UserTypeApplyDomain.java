package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.message.SubscribeMessageDto;
import com.xiaogua.comments.bean.user.UserRest;
import com.xiaogua.comments.bean.user.UserTypeApplyPage;
import com.xiaogua.comments.bean.user.UserTypeApplyPageInfo;
import com.xiaogua.comments.bean.user.UserTypeApplyRest;
import com.xiaogua.comments.bean.user.UserTypeApplySubmit;
import com.xiaogua.comments.bean.user.UserTypeRest;
import com.xiaogua.comments.bl.CommentsBl;
import com.xiaogua.comments.bl.MessageBl;
import com.xiaogua.comments.bl.UserBl;
import com.xiaogua.comments.bl.UserTypeApplyBl;
import com.xiaogua.comments.common.constant.MessageType;
import com.xiaogua.comments.common.constant.NotifyKeyConstant;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.common.constant.TemplateConstant;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.entity.UserTypeApply;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.RedisCache;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户类型申请 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class UserTypeApplyDomain {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserTypeApplyDomain.class);

    @Autowired
    private UserTypeApplyBl userTypeApplyBl;

    @Autowired
    private UserDomain userDomain;

    @Autowired
    private UserTypeDomain userTypeDomain;

    @Autowired
    private UserBl userBl;

    @Autowired
    private MessageBl messageBl;

    /**
     * 分页获取用户申请信息
     *
     * @param pageInfo 分页信息
     * @return
     */
    public ResponsePageBuilder adminGetApplysPage(UserTypeApplyPageInfo pageInfo) {
        UserTypeApplyPage aplyPage = userTypeApplyBl.getApplyPage(pageInfo);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换用户申请信息
        if (aplyPage.getPagingInfo().getTotalCount() > 0) {
            builder.setData(convertToUserTypeApplyRests(aplyPage.getUserTypeApplyList()));
        }

        builder.setPageInfo(aplyPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * admin-获取用户类型申请信息详情
     *
     * @param code code
     * @return
     */
    public ResponseBuilder adminGetApply(String code) {
        UserTypeApply apply = userTypeApplyBl.getApply(code);
        if (apply == null) {
            throw new CommentsRuntimeException(-1, "用户类型申请不存在");
        }

        UserTypeApplyRest applyRest = convertToUserTypeApplyRest(apply, false);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setData(applyRest);
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 获取用户类型申请信息详情
     *
     * @param userTypeCode userTypeCode
     * @return
     */
    public UserTypeApplyRest getSimpleApplyRest(String userTypeCode) {
        UserTypeApply apply = userTypeApplyBl.getApplyByUserTypeCode(userTypeCode);
        UserTypeApplyRest applyRest = null;
        if (apply != null) {
            applyRest = convertToUserTypeApplyRest(apply, true);
        }

        return applyRest;
    }

    /**
     * 审核通过用户类型申请信息
     * @param submit 用户类型申请审核信息
     * @param userCode 用户编号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder auditSuccess(UserTypeApplySubmit submit, String userCode) {
        VerifyParamsUtil.hasText(submit.getCode(), "申请编号不可为空");

        UserTypeApply apply = userTypeApplyBl.getApply(submit.getCode());
        if (apply == null) {
            throw new CommentsRuntimeException(-1, "用户类型申请不存在");
        }
        if (apply.getStatus().intValue() != StatusCode.USER_TYPE_STATUS_UNADUIT.getCode()) {
            throw new CommentsRuntimeException(-1, "用户类型申请已审核");
        }

        // 更新用户类型申请信息
        userTypeApplyBl.updateApply(submit.getCode(), StatusCode.USER_TYPE_STATUS_ADUIT.getCode(), userCode, submit.getOpinions());
        // 更新用户类型信息
        userTypeDomain.updateAfterApplyAuditSuccess(apply.getUserCode(), apply.getUserTypeCode());

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());

        //审核通过提醒
        messageBl.sendUserTypeSuccessMessage(submit, apply);
        return builder;
    }


    /**
     * 审核不通过用户类型申请信息
     * @param submit 用户类型申请审核信息
     * @param userCode 用户编号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder auditFailed(UserTypeApplySubmit submit, String userCode) {
        VerifyParamsUtil.hasText(submit.getCode(), "申请编号不可为空");

        UserTypeApply apply = userTypeApplyBl.getApply(submit.getCode());
        if (apply == null) {
            throw new CommentsRuntimeException(-1, "用户类型申请不存在");
        }
        if (apply.getStatus().intValue() != StatusCode.USER_TYPE_STATUS_UNADUIT.getCode()) {
            throw new CommentsRuntimeException(-1, "用户类型申请已审核");
        }

        // 更新用户类型申请信息
        userTypeApplyBl.updateApply(submit.getCode(), StatusCode.USER_TYPE_STATUS_ADUIT_FAILED.getCode(), userCode, submit.getOpinions());

        // 更新用户类型信息
        userTypeDomain.updateAfterApplyAuditFailed(apply.getUserCode(), apply.getUserTypeCode());

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());

        //审核通过提醒
        messageBl.sendUserTypeFileMessage(submit, apply);
        return builder;
    }

    /**
     * 批量转换用户申请信息Rest
     *
     * @param applyList 用户申请信息
     * @return
     */
    private List<UserTypeApplyRest> convertToUserTypeApplyRests(List<UserTypeApply> applyList) {
        List<UserTypeApplyRest> applyRests = new ArrayList<>();
        for (UserTypeApply apply : applyList) {
            applyRests.add(convertToUserTypeApplyRest(apply, false));
        }

        return applyRests;
    }

    /**
     * 转换用户申请信息Rest
     *
     * @param apply 用户申请信息
     * @param isSimple 是否简单model
     * @return
     */
    private UserTypeApplyRest convertToUserTypeApplyRest(UserTypeApply apply, boolean isSimple) {
        UserTypeApplyRest applyRest = new UserTypeApplyRest(apply);

        // 非简单model，补充用户类型信息
        if (!isSimple) {
            UserTypeRest userTypeRest = userTypeDomain.getUserTypeRest(apply.getUserTypeCode());
            applyRest.setUserTypeRest(userTypeRest);

            UserRest userRest = userDomain.getUserRest(apply.getUserCode());
            applyRest.setUserRest(userRest);
        }
        return applyRest;
    }

}


