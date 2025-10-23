package com.xiaogua.comments.bean.user;

import com.xiaogua.comments.bean.file.FileRest;
import com.xiaogua.comments.dal.entity.UserTypeFile;

/**
 * 用户类型文件信息表Rest
 */
public class UserTypeFileRest {

    /**
     * 用户code
     */
    private String userCode;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 文件链接
     */
    private FileRest fileRest;

    /**
     * 文件序号
     */
    private Integer fileDetno;

    /**
     * 创建时间（业务）
     */
    private String createDate;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public FileRest getFileRest() {
        return fileRest;
    }

    public void setFileRest(FileRest fileRest) {
        this.fileRest = fileRest;
    }

    public Integer getFileDetno() {
        return fileDetno;
    }

    public void setFileDetno(Integer fileDetno) {
        this.fileDetno = fileDetno;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public UserTypeFileRest() {
    }

    public UserTypeFileRest(UserTypeFile userTypeFile, FileRest fileRest) {
        this.userCode   = userTypeFile.getUserCode();
        this.type       = userTypeFile.getType();
        this.fileRest   = fileRest;
        this.fileDetno  = userTypeFile.getFileDetno();
        this.createDate = userTypeFile.getCreateDate();
    }
}