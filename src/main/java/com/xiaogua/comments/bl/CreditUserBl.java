package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.CreditUser;
import com.xiaogua.comments.dal.mapper.CreditUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户积分 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:36
 */
@Service
public class CreditUserBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditUserBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CreditUserMapper creditUserMapper;

    /**
     * 初始化用户积分信息
     * @param userCode
     */
    public void initCreditUser(String userCode) {
        CreditUser creditUser = creditUserMapper.selectByUserCode(userCode);

        if (creditUser == null) {
            creditUser = init(userCode);
            creditUserMapper.insertSelective(creditUser);
        }
    }

    /**
     * 初始化
     * @param userCode
     * @return
     */
    private CreditUser init(String userCode) {
        CreditUser creditUser = new CreditUser();
        creditUser.setCode(BizCodeUtil.genLongBizCode(TableCode.T_CREDIT_USER.getCode()));
        creditUser.setUserCode(userCode);
        creditUser.setTotal(0);
        creditUser.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        creditUser.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        return creditUser;
    }
}
