package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.user.LoginUser;
import com.xiaogua.comments.bean.user.RegisterUser;
import com.xiaogua.comments.bean.user.UserPage;
import com.xiaogua.comments.bean.user.UserPageInfo;
import com.xiaogua.comments.common.constant.CacheKeyConstant;
import com.xiaogua.comments.common.constant.MessageType;
import com.xiaogua.comments.common.constant.RegexConstant;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.Message;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.dal.mapper.UserMapper;
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
 * 用户 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:36
 */
@Service
public class UserBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserMapper userMapper;

    @Autowired
    private UserLoginStateBl userLoginStateBl;

    @Autowired
    private MessageBl messageBl;

    /**
     * 通过用户编号获取用户信息
     *
     * @param code 用户编号
     * @return
     */
    public User getUserByCode(String code) {
        VerifyParamsUtil.hasText(code, "用户编号不可为空");
        User user = userMapper.selectByCode(code);
        return user;
    }

    /**
     * 通过微信openId获取用户信息
     *
     * @param openId 微信编号
     * @return
     */
    public User getUserByOpenId(String openId) {
        VerifyParamsUtil.hasText(openId, "用户编号不可为空");
        User user = userMapper.selectByOpenId(openId);
        return user;
    }

    /**
     * 更新用户
     *
     * @param user user
     */
    public void updateUser(User user) {
        User existUser = userMapper.selectByCode(user.getCode());
        if (existUser == null) {
            throw new CommentsRuntimeException("用户不存在");
        }

        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 更新用户
     *
     * @param user user
     */
    public void updateByCodeSelective(User user) {
        User existUser = userMapper.selectByCode(user.getCode());
        if (existUser == null) {
            throw new CommentsRuntimeException("用户不存在");
        }

        userMapper.updateByCodeSelective(user);
    }

    /**
     * 账号密码注册
     *
     * @param registerUser 注册用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public User userRegister(RegisterUser registerUser) {
        VerifyParamsUtil.hasText(registerUser.getMobile(), "手机号不能为空");
        VerifyParamsUtil.hasText(registerUser.getPassword(), "密码不能为空");
        VerifyParamsUtil.isTrue(Pattern.matches(RegexConstant.REGEX_MOBILE, registerUser.getMobile()), "手机号格式不正确");
        User user = userMapper.selectByMobile(registerUser.getMobile());
        VerifyParamsUtil.isTrue(user == null, "手机号已注册");
        User newUser = createUser(registerUser.getMobile(), registerUser.getPassword());

        userMapper.insertSelective(newUser);
        return newUser;
    }

    /**
     * 登录
     *
     * @param loginUser 登录用户信息
     * @param request   request
     * @param response  response
     * @return
     */
    public Map<String, String> login(LoginUser loginUser, HttpServletRequest request, HttpServletResponse response) {
        VerifyParamsUtil.hasText(loginUser.getMobile(), "手机号不能为空");
        VerifyParamsUtil.hasText(loginUser.getPassword(), "密码不能为空");
        User user = getUserInfoByUserLoginParams(loginUser);
        //获取openId "0"无 "1"有
        String isOpenId = "0";
        if (user == null || StringUtils.isEmpty(user.getOpenId())) {
            isOpenId = "0";
        } else {
            isOpenId = "1";
        }

        Map<String, String> map = new HashMap<>();
        map.put("isOpenId", isOpenId);

        VerifyParamsUtil.notNull(user, "找不到用户信息");

        String token = createToken(user.getCode(), CacheKeyConstant.VERIFY_USERLOGINSTATE);
        map.put("token", token);
        return map;
    }

    /**
     * 登出
     *
     * @param token token
     */
    public void loginout(String token, String userCode) {
        UserLoginState userLoginState = userLoginStateBl.getUserLoginStateByCodeAndCacheKey(userCode, token);
        if (userLoginState == null) {
            throw new CommentsRuntimeException("用户登录状态不存在");
        }
        userLoginStateBl.delete(userLoginState);
    }

    /**
     * 通过登录参数获取用户信息
     *
     * @param loginUser 登录用户信息
     * @return
     */
    private User getUserInfoByUserLoginParams(LoginUser loginUser) {
        User user = getSsoUserInfoByPasswordLogin(loginUser);
        return user;
    }

    /**
     * 创建Token到Redis
     *
     * @param code     用户编号
     * @param cacheKey 用户登录类型
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String createToken(String code, String cacheKey) {
        UserLoginState userLoginState = userLoginStateBl.getMaxUserLoginState(code, cacheKey);

        long now = System.currentTimeMillis();
        Date nowDate = new Date(now);
        // 2小时
        long expir = now + (2 * 60 * 60000);

        String token = "";

        if (userLoginState == null || userLoginState.getExpir().before(nowDate)) {
            if (userLoginState != null) {
                userLoginStateBl.delete(userLoginState);
            }
            UserLoginState newUserLoginState = new UserLoginState();
            token = CacheKeyConstant.VERIFY_USERLOGINSTATE + UUID.randomUUID().toString();
            newUserLoginState.setCachekey(CacheKeyConstant.VERIFY_USERLOGINSTATE);
            newUserLoginState.setToken(token);
            newUserLoginState.setCode(code);
            newUserLoginState.setExpir(new Date(expir));

            userLoginStateBl.insert(newUserLoginState);
        } else {
            token = userLoginState.getToken();
            userLoginState.setExpir(new Date(expir));

            userLoginStateBl.update(userLoginState);
        }

        return token;
    }

    /**
     * 通过账号密码登录获取用户信息
     *
     * @param loginUser 登录用户信息
     * @return
     */
    private User getSsoUserInfoByPasswordLogin(LoginUser loginUser) {
        VerifyParamsUtil.notNull(loginUser, "账号密码登录参数不能为空");
        VerifyParamsUtil.hasText(loginUser.getMobile(), "手机号不可为空");
        VerifyParamsUtil.hasText(loginUser.getPassword(), "密码不可为空");
        User user = null;
        String account = loginUser.getMobile().trim();
        String password = loginUser.getPassword();

        //如果为手机
        //        if (Pattern.matches(RegexConstant.REGEX_MOBILE, account)) {
        user = userMapper.selectByMobile(account);
        //        }
        VerifyParamsUtil.notNull(user, "找不到用户信息");

        User userPassword = userMapper
            .selectByCodeAndPassword(user.getCode(), MD5.getEncryPassword(password, user.getSalt().toString()));
        if (userPassword == null) {
            throw new CommentsRuntimeException(-1, "账号密码错误");
        }
        return user;
    }

    /**
     * 创建个人用户
     *
     * @param mobile   手机号
     * @param password 密碼
     * @return
     */
    public User createUser(String mobile, String password) {
        // 用户数据组装 用户名 = 手机号
        User user = new User();
        user.setName(StringRandom.getStringRandom(6));
        user.setCode(BizCodeUtil.genLongBizCode(TableCode.T_USER.getCode()));
        user.setSalt(CheckCodeUtil.getRandomNumber(6));
        user.setPassword(createPassword(password, user.getSalt()));
        user.setMobile(mobile);
        user.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        user.setUpdateDate(user.getCreateDate());

        return user;
    }

    /**
     * 创建没有手机号码的临时账户
     *
     * @param name     用户昵称
     * @param password 密碼
     * @param headimgurl 头像
     * @return
     */
    public User createNoMobileUser(String name, String headimgurl, String password) {

        // 用户数据组装 用户名 = 手机号
        User user = new User();
        user.setName(name);
        user.setCode(BizCodeUtil.genLongBizCode(TableCode.T_USER.getCode()));
        user.setSalt(CheckCodeUtil.getRandomNumber(6));
        user.setPassword(createPassword(password, user.getSalt()));
        user.setAvatar(headimgurl);
        user.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        user.setUpdateDate(user.getCreateDate());

        return user;
    }

    /**
     * 创建用户密码
     *
     * @param password 密码
     * @param salt     盐值
     */
    private String createPassword(String password, Integer salt) {
        String userPassword = MD5.getEncryPassword(password, salt.toString());
        return userPassword;
    }

    /**
     * 重置密码
     *
     * @param password
     * @param userCode
     */
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(String password, String userCode) {
        User user = userMapper.selectByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户不存在");
        }

        user.setPassword(createPassword(password, user.getSalt()));
        userMapper.updateByPrimaryKeySelective(user);
        //消息通知
        MessageType messageType = MessageType.EDIT_PASSWORD;
        messageBl.createMessage(user.getCode(), String.format(messageType.getMessage(), password), messageType.getCode(), null);
    }

    /**
     * 分页获取用户信息
     * @param pageInfo 分页信息
     * @return
     */
    public UserPage getPage(UserPageInfo pageInfo) {
        int count = userMapper.count(StringUtils.isEmpty(pageInfo.getKeyword()) ? null : pageInfo.getKeyword(),
                StringUtils.isEmpty(pageInfo.getSex()) ? null : pageInfo.getSex(),
                StringUtils.isEmpty(pageInfo.getProvince()) ? null : pageInfo.getProvince(),
                StringUtils.isEmpty(pageInfo.getCity()) ? null : pageInfo.getCity(),
            StringUtils.isEmpty(pageInfo.getRegisterStartDate()) ? null : pageInfo.getRegisterStartDate(),
            StringUtils.isEmpty(pageInfo.getRegisterEndDate()) ? null : pageInfo.getRegisterEndDate());

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<User> users = userMapper.findPage(pagingInfo.getIndex(), pagingInfo.getPageSize(),
                StringUtils.isEmpty(pageInfo.getKeyword()) ? null : pageInfo.getKeyword(),
                StringUtils.isEmpty(pageInfo.getSex()) ? null : pageInfo.getSex(),
                StringUtils.isEmpty(pageInfo.getProvince()) ? null : pageInfo.getProvince(),
                StringUtils.isEmpty(pageInfo.getCity()) ? null : pageInfo.getCity(),
            StringUtils.isEmpty(pageInfo.getRegisterStartDate()) ? null : pageInfo.getRegisterStartDate(),
            StringUtils.isEmpty(pageInfo.getRegisterEndDate()) ? null : pageInfo.getRegisterEndDate());

        UserPage userPage = new UserPage();
        if (!CollectionUtils.isEmpty(users)) {
            userPage.setUsers(users);
        }
        userPage.setPagingInfo(pagingInfo);

        return userPage;
    }
}
