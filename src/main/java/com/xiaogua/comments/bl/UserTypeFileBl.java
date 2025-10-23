package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.bean.user.UserTypeFileSubmit;
import com.xiaogua.comments.dal.entity.UserTypeFile;
import com.xiaogua.comments.dal.mapper.UserTypeFileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户类型文件 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:36
 */
@Service
public class UserTypeFileBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTypeFileBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserTypeFileMapper userTypeFileMapper;

    /**
     * 批量保存用户类型文件
     *
     * @param submits submits
     * @return
     */
    public void save(String userCode, Integer userType, List<UserTypeFileSubmit> submits) {
        for (UserTypeFileSubmit fileSubmit : submits) {
            UserTypeFile userTypeFile = new UserTypeFile();
            userTypeFile.setUserCode(userCode);
            userTypeFile.setType(userType);
            userTypeFile.setFileCode(fileSubmit.getFileCode());
            userTypeFile.setFileDetno(fileSubmit.getFileDetno());
            userTypeFile.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

            userTypeFileMapper.insertSelective(userTypeFile);
        }
    }

    /**
     * 批量删除用户类型文件
     *
     * @param userCode userCode
     * @param userType userType
     * @return
     */
    public void deleteByUserAndType(String userCode, Integer userType) {
        List<UserTypeFile> userTypeFiles = userTypeFileMapper.selectByUserCodeAndUserType(userCode, userType);
        if (!CollectionUtils.isEmpty(userTypeFiles)) {
            userTypeFileMapper.deleteByUserCodeAndUserType(userCode, userType);
        }
    }

    /**
     * 批量获取用户类型文件
     *
     * @param userCode userCode
     * @param userType userType
     * @return
     */
    public List<UserTypeFile> getByUserCodeAndType(String userCode, Integer userType) {
        List<UserTypeFile> userTypeFiles = userTypeFileMapper.selectByUserCodeAndUserType(userCode, userType);
        return userTypeFiles;
    }

}
