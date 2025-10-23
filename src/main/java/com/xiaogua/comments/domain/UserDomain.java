package com.xiaogua.comments.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.user.LoginUser;
import com.xiaogua.comments.bean.user.MyUser;
import com.xiaogua.comments.bean.user.RegisterUser;
import com.xiaogua.comments.bean.user.UserAdminSimpleRest;
import com.xiaogua.comments.bean.user.UserPage;
import com.xiaogua.comments.bean.user.UserPageInfo;
import com.xiaogua.comments.bean.user.UserRest;
import com.xiaogua.comments.bean.user.UserSubmit;
import com.xiaogua.comments.bean.user.UserTypeRest;
import com.xiaogua.comments.bl.*;
import com.xiaogua.comments.common.constant.CacheKeyConstant;
import com.xiaogua.comments.common.constant.MessageType;
import com.xiaogua.comments.common.constant.SmsCodeConstant;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.config.CommentsConfig;
import com.xiaogua.comments.config.WXConfig;
import com.xiaogua.comments.dal.entity.*;
import com.xiaogua.comments.dal.mapper.CreateUserMapper;
import com.xiaogua.comments.dal.mapper.UserMapper;
import com.xiaogua.comments.utils.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class UserDomain {

    public static final Map<String, Object> ATTRIBUTES = new HashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDomain.class);

    /**
     * 用户登录态标识
     */
    private static String USER_COOKIE_NAME = "token";

    /**
     * cookie默认浏览器关闭失效
     */
    private static int DEFAULT_MAX_AGE = -1;

    @Autowired
    private UserBl userBl;

    @Autowired
    private UserLoginStateBl userLoginStateBl;

    @Autowired
    private UserTypeBl userTypeBl;

    @Autowired
    private UserTypeDomain userTypeDomain;

    @Autowired
    private FileDomain fileDomain;

    @Autowired
    private CreditUserBl creditUserBl;

    @Autowired
    private CreditBl creditBl;

    @Autowired
    private CheckCodeBl checkCodeBl;

    @Autowired
    private UserTypeFileBl userTypeFileBl;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    UserMapper userMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    CreateUserMapper createUserMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CommentsConfig commentsConfig;

    @Autowired
    SmsBl smsBl;

    @Autowired
    WxUtil wxUtil;

    @Autowired
    OpenWxUtil openWxUtil;

    @Autowired
    WXConfig wxConfig;

    @Autowired
    private MessageBl messageBl;

    /**
     * 账号密码注册
     *
     * @param registerUser 注册用户信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder userRegister(RegisterUser registerUser) {
        User user = register(registerUser, false);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(user.getCode());
        return builder;
    }

    /**
     * 受邀新用户注册
     *
     * @param registerUser 注册用户信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder invitedRegister(RegisterUser registerUser) {
        User user = register(registerUser, true);
        String userCode = user.getCode();
        String name = user.getName();
        // invite user add credit
        invitedUserAddCredit(registerUser.getInvitedCode(), userCode, name);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(userCode);
        return builder;
    }

    /**
     * 小程序一键登录注册
     *
     * @param phone
     * @return
     */
    private User miniProgramRegister(String phone, String openid) {
        User user;
        user = userBl.createUser(phone, StringRandom.getStringRandom(8));
        user.setOpenId(openid);
        userMapper.insertSelective(user);
        String userCode = user.getCode();
        String name = user.getName();
        initUser(userCode, name, false);
        // send success sms
        //sendSuccessSms(user.getMobile(), user.getPassword());
        return user;
    }

    /**
     * 注册用户
     *
     * @param registerUser
     * @param invite 是否为邀请注册
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public User register(RegisterUser registerUser, boolean invite) {
        VerifyParamsUtil.notNull(registerUser, "注册用户信息不可为空");
        VerifyParamsUtil.hasText(registerUser.getMobile(), "手机号不可为空");
        VerifyParamsUtil.hasText(registerUser.getPassword(), "密码不可为空");
        VerifyParamsUtil.isTrue(registerUser.getIsAgree() == 1, "请同意服务条款");
        VerifyParamsUtil.hasText(registerUser.getSmsCheckCode(), "短信验证码不可为空");

        // check sms checkCode
        checkCodeBl.checkCheckCode(registerUser.getMobile(), registerUser.getSmsCheckCode(),
                CacheKeyConstant.VERIFY_REGISTER_CHECKCODE);

        User user = userBl.userRegister(registerUser);
        String userCode = user.getCode();
        String name = user.getName();
        // 初始化用户信息
        initUser(userCode, name, invite);

        // checkCode unavailable
        checkCodeBl.unavailableCheckCode(registerUser.getMobile(), CacheKeyConstant.VERIFY_REGISTER_CHECKCODE);

        // send success sms
        //sendSuccessSms(registerUser.getMobile(), registerUser.getPassword());
        return user;
    }

    /**
     * 发送用户注册成功的短信
     * @param mobile
     * @param password
     */
    private void sendSuccessSms(String mobile, String password) {
        String[] phoneList = {"+86" + mobile};
        String[] params = {mobile, password};
        smsBl.sendSms(String.valueOf(SmsCodeConstant.REGISTER_SUCCESS), phoneList, params);
    }

    /**
     * 初始化用户信息
     *
     * @param userCode
     * @param name
     * @param invite 是否为邀请注册
     */
    private void initUser(String userCode, String name, boolean invite) {
        // init userType
        userTypeBl.initUserType(userCode);

        // init creditUser
        creditUserBl.initCreditUser(userCode);

        // register success add credit
        creditBl.registerUser(userCode);

        //发送消息通知给注册人
        //非邀请注册进行通知 邀请注册通知在另一个地方已经处理
        if (!invite) {
            MessageType messageType = MessageType.REGISTER_USER;
            messageBl.createMessage(userCode, String.format(messageType.getMessage(), name), messageType.getCode(), null);
        }
    }

    /**
     * 重置密码
     *
     * @param registerUser 用户信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder resetPassword(RegisterUser registerUser) {
        VerifyParamsUtil.notNull(registerUser, "注册用户信息不可为空");
        VerifyParamsUtil.hasText(registerUser.getMobile(), "手机号不可为空");
        VerifyParamsUtil.hasText(registerUser.getPassword(), "密码不可为空");
        VerifyParamsUtil.hasText(registerUser.getEnsurePassword(), "确认密码不可为空");
        VerifyParamsUtil.isTrue(registerUser.getPassword().equals(registerUser.getEnsurePassword()), "两次密码不一致");
        VerifyParamsUtil.hasText(registerUser.getSmsCheckCode(), "短信验证码不可为空");

        User user = userMapper.selectByMobile(registerUser.getMobile());
        VerifyParamsUtil.isTrue(user != null, "手机号未注册");

        // check sms checkCode
        checkCodeBl.checkCheckCode(user.getCode(), registerUser.getSmsCheckCode(),
                                   CacheKeyConstant.VERIFY_FORGOT_PASSWORD_CHECKCODE);

        // reset password
        userBl.resetPassword(registerUser.getPassword(), user.getCode());

        // checkCode unavailable
        checkCodeBl.unavailableCheckCode(user.getCode(), CacheKeyConstant.VERIFY_FORGOT_PASSWORD_CHECKCODE);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 邀请人加分
     *
     * @param invitedCode     邀请人用户编号
     * @param invitedUserCode 被邀请人用户编号
     * @param invitedUserName 被邀请人用户名称
     */
    @Transactional(rollbackFor = Exception.class)
    protected void invitedUserAddCredit(String invitedCode, String invitedUserCode, String invitedUserName) {
        if (StringUtils.isNotEmpty(invitedCode)) {
            try {
                invitedCode = URLDecoder.decode(invitedCode, "UTF-8");
                String userCode = DES.decode(invitedCode);

                User user = userBl.getUserByCode(userCode);
                if (user != null) {
                    UserType userType = userTypeBl.getCurrentUserType(userCode);
                    // 专家加分不受限
                    if (StatusCode.USER_TYPE_EXPERT.getCode() == userType.getType().intValue()) {
                        JSONObject jsonObject = creditBl.registerUserByExpertInvited(userCode, invitedUserCode);
                        Integer score = jsonObject.getInteger("score");
                        //发送消息通知给邀请人
                        MessageType messageType = MessageType.INVITE_EXPERT;
                        messageBl.createMessage(invitedCode, String.format(messageType.getMessage(), invitedUserName, score), messageType.getCode(), null);
                        // 其他用户加分受限
                    } else {
                        JSONObject jsonObject = creditBl.registerUserByInvited(userCode, invitedUserCode);
                        Integer score = jsonObject.getInteger("score");
                        //发送消息通知给邀请人
                        MessageType messageType = MessageType.INVITE_USER;
                        messageBl.createMessage(invitedCode, String.format(messageType.getMessage(), invitedUserName, score), messageType.getCode(), null);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("邀请码解密失败");
            }
        }
    }

    /**
     * 登录
     *
     * @param loginUser 登录用户信息
     * @param request   request
     * @param response  response
     * @return
     */
    public ResponseBuilder login(LoginUser loginUser, HttpServletRequest request, HttpServletResponse response) {
        VerifyParamsUtil.notNull(loginUser, "登录信息不可为空");

        Map<String, String> map = userBl.login(loginUser, request, response);
        String token = map.get("token");
        setUserCookies(request, response, token, DEFAULT_MAX_AGE);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setData(map);
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 微信扫描登录
     *
     * @param code
     * @param url
     * @param request
     * @param response
     * @return
     */
    public void wechatLogin(String code, String url, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject userInfo = openWxUtil.getUserInfo(code);
        String openid = userInfo.getString("openid");
        String name = userInfo.getString("nickname");
        if (StringUtils.isEmpty(name)) {
            name = StringRandom.getStringRandom(6);
        }
        String headimgurl = StringUtils.isNotEmpty(userInfo.getString("headimgurl")) ? userInfo.getString("headimgurl") : null;

        User user = userMapper.selectByWxOpenid(openid);
        boolean isMobile = false;
        if (user != null) {
            if (StringUtils.isNotEmpty(user.getMobile())) {
                isMobile = true;
            }
        } else {
            //新增用户
            //todo 找到增加头像的方案
            user = userBl.createNoMobileUser(name, null, StringRandom.getStringRandom(8));
            user.setWxOpenId(openid);
            userMapper.insertSelective(user);
        }

        //执行登录操作
        String token = userBl.createToken(user.getCode(), CacheKeyConstant.VERIFY_USERLOGINSTATE);
        LOGGER.info("获取的用户登录token为" + token);
        setUserCookies(request, response, token, DEFAULT_MAX_AGE);

        String urlDecode = URLDecoder.decode(url, "utf-8");
        LOGGER.info("URL解密后生成的url为" + urlDecode);
        String parameter = "?token=" + token + "&isMobile=" + isMobile + "&openid=" + URLEncoder.encode(DES.encode(openid), "utf-8");
        urlDecode += parameter;
        LOGGER.info("重定向地址为：" + urlDecode);
        response.sendRedirect(urlDecode);
    }

    /**
     * 微信扫码绑定手机号码
     *
     * @param openid
     * @param mobile
     * @return
     */
    public ResponseBuilder bindPhone(String openid, String mobile, String smsCheckCode, HttpServletRequest request, HttpServletResponse response) {
        // check sms checkCode
        checkCodeBl.checkCheckCode(mobile, smsCheckCode, CacheKeyConstant.VERIFY_BIND_PHONE_CHECKCODE);
        // 查找是否有该用户
        User user = userMapper.selectByMobile(mobile);
        // 微信openid对应的用户
        User userDB = userMapper.selectByWxOpenid(openid);
        if (userDB == null) {
            throw new CommentsRuntimeException(-1, "找不到对应的用户信息");
        }
        String token = "";
        if (user != null) {
            try {
                //注销原有用户
                loginout(userDB.getCode(), request, response);
            } catch (Exception e) {
                LOGGER.warn("注销用户失败！");
            }
            //删除之前创建的用户记录
            userMapper.deleteByWxOpenid(openid);
            //更新用户信息
            user.setWxOpenId(openid);
            userMapper.updateByPrimaryKeySelective(user);
            //重新返回token
            //执行登录操作
            token = userBl.createToken(user.getCode(), CacheKeyConstant.VERIFY_USERLOGINSTATE);
            setUserCookies(request, response, token, DEFAULT_MAX_AGE);
        } else {
            userDB.setMobile(mobile);
            initUser(userDB.getCode(), userDB.getName(), false);
            userMapper.updateByPrimaryKeySelective(userDB);
        }

        // checkCode unavailable
        checkCodeBl.unavailableCheckCode(mobile, CacheKeyConstant.VERIFY_BIND_PHONE_CHECKCODE);
        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        builder.setData(map);
        return builder;
    }

    /**
     * 登出
     *
     * @param request  request
     * @param response response
     * @return
     */
    public ResponseBuilder loginout(String userCode, HttpServletRequest request, HttpServletResponse response) {
        String token = getToken(request, USER_COOKIE_NAME);
        userBl.loginout(token, userCode);

        // 退出清除cookies token
        clearCookies(request, response);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 小程序一键登录
     *
     * @param phone
     * @param openid
     */
    public ResponseBuilder miniProgramLogin(String phone, String openid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userMapper.selectByMobile(phone);
        if (user != null) {
            if (StringUtils.isEmpty(user.getOpenId())) {
                user.setOpenId(openid);
                //更新用户信息
                userMapper.updateByPrimaryKey(user);
            }
        } else {
            //新增用户并执行登录操作
            user = miniProgramRegister(phone, openid);
        }

        //执行登录操作
        String token = userBl.createToken(user.getCode(), CacheKeyConstant.VERIFY_USERLOGINSTATE);
        setUserCookies(request, response, token, DEFAULT_MAX_AGE);
        ResponseBuilder builder = new ResponseBuilder();
        builder.setData(token);
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 更新用户
     *
     * @param userSubmit
     * @param userCode
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder update(UserSubmit userSubmit, String userCode) {
        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException("用户不存在");
        }

        VerifyParamsUtil.notNull(userSubmit, "提交用户信息不可为空");
        VerifyParamsUtil.hasText(userSubmit.getMobile(), "手机号不可为空");

        // 微信验证姓名合法性
        if (StringUtils.isNotEmpty(userSubmit.getName())) {
            wxUtil.validateContent(userSubmit.getName());
        }

        user = updateMappingUser(user, userSubmit);

        userBl.updateUser(user);

        // 个人信息完善加分
        creditBycompleteUser(userCode);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 完善用户信息加分
     *
     * @param userCode
     * @return
     */
    public void creditBycompleteUser(String userCode) {
        User user = userMapper.selectByCode(userCode);
        UserType userType = userTypeBl.getCurrentUserType(userCode);
        List<UserTypeFile> userTypeFiles = userTypeFileBl.getByUserCodeAndType(userCode, userType.getType());
        if (StringUtils.isNotEmpty(user.getAvatar())
                && StringUtils.isNotEmpty(user.getName())
                && StringUtils.isNotEmpty(user.getSex())
                && StringUtils.isNotEmpty(user.getMobile())
                && StringUtils.isNotEmpty(user.getEmail())
                && StringUtils.isNotEmpty(user.getProvince())
                && StringUtils.isNotEmpty(user.getCity())
                && StringUtils.isNotEmpty(user.getDistrict())
                && isUserTypeCompleted(userType, userTypeFiles)) {
            creditBl.completeUser(user.getCode());
        }
    }

    /**
     * 用户身份信息是否完善
     *
     * @return
     */
    private boolean isUserTypeCompleted(UserType userType, List<UserTypeFile> userTypeFiles) {
        if (userType != null) {
            int type = userType.getType().intValue();
            if (type == StatusCode.USER_TYPE_EXPERT.getCode()) {
                if (StringUtils.isNotBlank(userType.getName()) && StringUtils.isNotBlank(userType.getBrief())
                    && !CollectionUtils.isEmpty(userTypeFiles)) {
                    return true;
                }
            } else if (type == StatusCode.USER_TYPE_DEVELOPER.getCode()) {
                if (StringUtils.isNotBlank(userType.getName()) && StringUtils.isNotBlank(userType.getCompany())
                    && StringUtils.isNotBlank(userType.getPosition()) && !CollectionUtils.isEmpty(userTypeFiles)) {
                    return true;
                }
            } else if (type == StatusCode.USER_TYPE_CONSUMER.getCode()) {
                if (StringUtils.isNotBlank(userType.getUsedBrand()) && StringUtils
                    .isNotBlank(userType.getUsedProduct())) {
                    return true;
                }
            } else if (type == StatusCode.USER_TYPE_AMATEUR.getCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取当前用户信息
     *
     * @param code 当前用户编号
     * @return
     */
    public ResponseBuilder getMyUser(String code) {
        User user = userBl.getUserByCode(code);
        if (user == null) {
            throw new CommentsRuntimeException("当前用户不存在");
        }
        MyUser myUser = new MyUser(user);
        if (StringUtils.isNotEmpty(user.getAvatar())) {
            myUser.setAvatarFile(fileDomain.getFileRest(user.getAvatar()));
        }

        List<UserTypeRest> userTypeRests = userTypeDomain.getUserTypeRests(code);
        if (!CollectionUtils.isEmpty(userTypeRests)) {
            myUser.setUserTypeRests(userTypeRests);
        }

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(myUser);
        return builder;
    }

    /**
     * 获取邀请新用户链接
     *
     * @param userCode
     * @return
     */
    public ResponseBuilder getInviteUrl(String userCode) throws UnsupportedEncodingException {
        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "当前用户不存在");
        }

        String inviteCode = null;
        try {
            inviteCode = DES.encode(userCode);
        } catch (Exception e) {
            throw new CommentsRuntimeException(-1, "生成邀请码失败");
        }

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(commentsConfig.getInviteUrl() + URLEncoder.encode(inviteCode, "UTF-8"));
        return builder;
    }

    /**
     * 转换user
     *
     * @param user       user
     * @param userSubmit userSubmit
     * @return
     */
    private User updateMappingUser(User user, UserSubmit userSubmit) {
        if (!user.getMobile().equals(userSubmit.getMobile())) {
            user.setMobile(userSubmit.getMobile());
        }
        if (!user.getName().equals(userSubmit.getName())) {
            user.setName(userSubmit.getName());
        }
        if (!user.getBirthday().equals(userSubmit.getBirthday())) {
            user.setBirthday(userSubmit.getBirthday());
        }
        if (!user.getAvatar().equals(userSubmit.getAvatar())) {
            user.setAvatar(userSubmit.getAvatar());
        }
        if (!user.getEmail().equals(userSubmit.getEmail())) {
            user.setEmail(userSubmit.getEmail());
        }
        if (!user.getSex().equals(userSubmit.getSex())) {
            user.setSex(userSubmit.getSex());
        }
        if (!user.getProvince().equals(userSubmit.getProvince())) {
            user.setProvince(userSubmit.getProvince());
        }
        if (!user.getCity().equals(userSubmit.getCity())) {
            user.setCity(userSubmit.getCity());
        }
        if (!user.getDistrict().equals(userSubmit.getDistrict())) {
            user.setDistrict(userSubmit.getDistrict());
        }

        user.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        return user;
    }

    /**
     * 登录设置cookie
     *
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param token               token
     * @param maxAge              maxAge
     */
    public void setUserCookies(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        String token, int maxAge) {
        // 先取到token
        if (StringUtils.isBlank(token)) {
            token = getToken(httpServletRequest, USER_COOKIE_NAME);
        }
        // 拿登录态
        UserLoginState userLoginState = userLoginStateBl.checkLogin(token);

        // 取用户信息
        User user = userBl.getUserByCode(userLoginState.getCode());
        if (user == null) {
            throw new CommentsRuntimeException("找不到用户信息");
        }

        // 设置cookie
        String domain = DomainUtil.getFirstDomain(httpServletRequest);
        httpServletResponse.addCookie(setTokenCookie(token, domain, maxAge));
    }

    /**
     * 设置账号服务登录态
     *
     * @param token  token
     * @param domain domain
     * @param maxAge maxAge
     * @return
     */
    private Cookie setTokenCookie(String token, String domain, int maxAge) {
        Cookie tokenCookie = new Cookie(USER_COOKIE_NAME, token);
        tokenCookie.setDomain(domain);
        tokenCookie.setPath("/");
        tokenCookie.setHttpOnly(true);
        tokenCookie.setMaxAge(maxAge);
        return tokenCookie;
    }

    /**
     * 清除登录态
     *
     * @param request  request
     * @param response response
     */
    public void clearCookies(HttpServletRequest request, HttpServletResponse response) {
        String domain = DomainUtil.getFirstDomain(request);
        String token = getToken(request, USER_COOKIE_NAME);
        response.addCookie(setTokenCookie(token, domain, 0));
    }

    /**
     * 从请求中拿到cookie
     *
     * @param request   request
     * @param tokenName token名称
     * @return
     */
    public String getToken(HttpServletRequest request, String tokenName) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(tokenName)) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

    /**
     * 获取用户rest信息
     *
     * @param code 用户编号
     * @return
     */
    public UserRest getUserRest(String code) {
        User user = userBl.getUserByCode(code);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户信息不存在");
        }

        UserRest userRest = convertUserRest(user);
        return userRest;
    }

    /**
     * 转换用户rest信息
     *
     * @param user 用户信息
     * @return
     */
    private UserRest convertUserRest(User user) {
        UserRest userRest = new UserRest(user);

        if (StringUtils.isNotEmpty(user.getAvatar())) {
            userRest.setAvatarFile(fileDomain.getFileRest(user.getAvatar()));
        }

        return userRest;
    }

    /**
     * 管理后台-分页获取用户列表
     * @param pageInfo
     * @return
     */
    public ResponsePageBuilder getAdminPage(UserPageInfo pageInfo) {
        UserPage userPage = userBl.getPage(pageInfo);

        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换评论信息
        if (!CollectionUtils.isEmpty(userPage.getUsers())) {
            builder.setData(convertUserAdminSimpleRests(userPage.getUsers()));
        }

        builder.setPageInfo(userPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 批量转换-用户简易信息-管理后台
     * @param users
     * @return
     */
    private List<UserAdminSimpleRest> convertUserAdminSimpleRests(List<User> users) {
        List<UserAdminSimpleRest> rests = new ArrayList<>();
        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                rests.add(convertUserAdminSimpleRest(user));
            }
        }
        return rests;
    }

    /**
     * 转换-用户简易信息-管理后台
     * @param user
     * @return
     */
    private UserAdminSimpleRest convertUserAdminSimpleRest(User user) {
        UserAdminSimpleRest rest = new UserAdminSimpleRest(user);

        if (StringUtils.isNotEmpty(rest.getAvatarUrl())) {
            rest.setAvatarUrl(fileDomain.getFileUrl(rest.getAvatarUrl()));
        }
        return rest;
    }
}


