package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.user.LoginUser;
import com.xiaogua.comments.bean.user.RegisterUser;
import com.xiaogua.comments.bean.user.UserPage;
import com.xiaogua.comments.bean.user.UserPageInfo;
import com.xiaogua.comments.common.constant.CacheKeyConstant;
import com.xiaogua.comments.common.constant.RegexConstant;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.dal.entity.Visitor;
import com.xiaogua.comments.dal.mapper.UserMapper;
import com.xiaogua.comments.dal.mapper.VisitorMapper;
import com.xiaogua.comments.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 游客 bl
 *
 */
@Service
public class VisitorBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisitorBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private VisitorMapper visitorMapper;

    /**
     * 根据openId查询游客
     *
     * @param openId 微信openId
     * @return
     */
    public Integer selectByOpenId(String openId) {
        return visitorMapper.selectByOpenId(openId);
    }

    /**
     * 创建游客
     *
     * @param openId 微信openId
     * @return
     */
    public void createVisitor(String openId) {
        Visitor visitor = new Visitor();
        visitor.setCode(BizCodeUtil.genLongBizCode(TableCode.T_VISITOR.getCode()));
        visitor.setOpenId(openId);
        visitor.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        visitor.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        visitorMapper.insertSelective(visitor);
    }
}
