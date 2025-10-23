package com.xiaogua.comments.bean.file;

/**
 * @author wangyc
 * @date 2020-11-24 3:29
 */
public class FileInput {

    private String name;

    private String contentType;

    private byte[] stream;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getStream() {
        return stream;
    }

    public void setStream(byte[] stream) {
        this.stream = stream;
    }
}
