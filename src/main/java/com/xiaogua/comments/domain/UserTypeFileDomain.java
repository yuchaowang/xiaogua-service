package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.file.FileRest;
import com.xiaogua.comments.bean.user.UserTypeFileRest;
import com.xiaogua.comments.bl.UserTypeFileBl;
import com.xiaogua.comments.dal.entity.UserTypeFile;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author wangyc
 * @date 2020-11-28 18:38
 */
@Service
public class UserTypeFileDomain {

    @Autowired
    UserTypeFileBl userTypeFileBl;

    @Autowired
    FileDomain fileDomain;

    /**
     * 获取用户类型文件信息
     * @param userCode
     * @param type
     * @return
     */
    public List<UserTypeFileRest> getUserTypeFileRests(String userCode, Integer type) {
        List<UserTypeFile> userTypeFiles =
            userTypeFileBl.getByUserCodeAndType(userCode, type);

        List<UserTypeFileRest> userTypeFileRests = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userTypeFiles)) {
            for (UserTypeFile userTypeFile : userTypeFiles) {
                userTypeFileRests.add(converUserTypeFileRest(userTypeFile));
            }
        }
        return userTypeFileRests;
    }

    /**
     * 转换用户类型文件信息
     * @param userTypeFile 用户类型文件信息
     * @return
     */
    private UserTypeFileRest converUserTypeFileRest(UserTypeFile userTypeFile) {
        FileRest fileRest = null;
        if (StringUtils.isNotEmpty(userTypeFile.getFileCode())) {
            fileRest = fileDomain.getFileRest(userTypeFile.getFileCode());
        }
        UserTypeFileRest userTypeFileRest = new UserTypeFileRest(userTypeFile, fileRest);

        return userTypeFileRest;
    }
}
