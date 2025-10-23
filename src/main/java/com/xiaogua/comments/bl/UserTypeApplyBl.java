package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.comments.CommentsPage;
import com.xiaogua.comments.bean.user.UserTypeApplyPage;
import com.xiaogua.comments.bean.user.UserTypeApplyPageInfo;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.CommentsInfo;
import com.xiaogua.comments.dal.entity.UserType;
import com.xiaogua.comments.dal.entity.UserTypeApply;
import com.xiaogua.comments.dal.mapper.UserTypeApplyMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.PagingUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 用户类型申请 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:36
 */
@Service
public class UserTypeApplyBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTypeApplyBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserTypeApplyMapper userTypeApplyMapper;


    /**
     * 新增用户类型申请
     * @param userType 用户类型
     */
    public void addUserTypeApply(UserType userType) {
        UserTypeApply apply = new UserTypeApply();
        apply.setCode(BizCodeUtil.genLongBizCode(TableCode.T_USER_TYPE_APPLY.getCode()));
        apply.setStatus(StatusCode.USER_TYPE_STATUS_UNADUIT.getCode());
        apply.setType(userType.getType());
        apply.setUserCode(userType.getUserCode());
        apply.setUserTypeCode(userType.getCode());
        apply.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        apply.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        userTypeApplyMapper.insertSelective(apply);
    }


    /**
     * 分页获取用户申请信息
     *
     * @param pageInfo 分页信息
     * @return
     */
    public UserTypeApplyPage getApplyPage(UserTypeApplyPageInfo pageInfo) {
        int count = userTypeApplyMapper.count((pageInfo.getStatus() == null || pageInfo.getStatus() == 0) ? null : pageInfo.getStatus());

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<UserTypeApply> applyList = userTypeApplyMapper
            .findPage(pagingInfo.getIndex(), pagingInfo.getPageSize(),
                (pageInfo.getStatus() == null || pageInfo.getStatus() == 0) ? null : pageInfo.getStatus());

        UserTypeApplyPage applyPage = new UserTypeApplyPage();

        if (!CollectionUtils.isEmpty(applyList)) {
            applyPage.setUserTypeApplyList(applyList);
        }
        applyPage.setPagingInfo(pagingInfo);
        return applyPage;
    }

    /**
     * 获取用户类型申请信息详情
     * @param code 户类型申请code
     * @return
     */
    public UserTypeApply getApply(String code) {
        VerifyParamsUtil.hasText(code, "用户类型申请编号不存在");

        UserTypeApply userTypeApply = userTypeApplyMapper.selectByCode(code);
        return userTypeApply;
    }

    /**
     * 获取用户类型申请信息详情
     * @param userTypeCode 用户类型code
     * @return
     */
    public UserTypeApply getApplyByUserTypeCode(String userTypeCode) {
        UserTypeApply userTypeApply = userTypeApplyMapper.selectByUserTypeCode(userTypeCode);
        return userTypeApply;
    }

    /**
     * 更新用户类型申请信息
     * @param code
     * @param status
     * @param auditUserCode
     * @param opinions
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateApply(String code, Integer status, String auditUserCode, String opinions) {
        UserTypeApply apply = userTypeApplyMapper.selectByCode(code);

        if (apply == null) {
            throw new CommentsRuntimeException(-1, "用户类型申请信息不存在");
        }
        apply.setStatus(status);
        apply.setAuditUserCode(auditUserCode);
        apply.setOpinions(opinions);
        apply.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        userTypeApplyMapper.updateByPrimaryKeySelective(apply);
    }
}
