package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bl.CreditBl;
import com.xiaogua.comments.dal.entity.CreditUser;
import com.xiaogua.comments.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 积分 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class CreditDomain {

    @Autowired
    CreditBl creditBl;

    /**
     * 获取当前用户积分
     *
     * @param userCode userCode
     * @return
     */
    public ResponseBuilder getMyCredit(String userCode) {
        CreditUser creditUser = creditBl.getCreditUser(userCode);
        ResponseBuilder builder = new ResponseBuilder();

        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(creditUser);
        return builder;
    }

}


