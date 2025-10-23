package com.xiaogua.comments.bl;

import com.xiaogua.comments.dal.entity.Administrator;
import com.xiaogua.comments.dal.mapper.AdministratorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员 bl
 *
 * @author wangyc
 * @date 2021-02-19 14:58:35
 */
@Service
public class AdministratorBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private AdministratorMapper administratorMapper;

    /**
     * 验证管理员
     * @param userCode 用户编号
     * @return
     */
    public boolean check(String userCode) {
        boolean isAdmin = false;
        Administrator administrator = administratorMapper.selectByPrimaryKey(userCode);
        if (administrator != null) {
            isAdmin = true;
        }
        return isAdmin;
    }


}
