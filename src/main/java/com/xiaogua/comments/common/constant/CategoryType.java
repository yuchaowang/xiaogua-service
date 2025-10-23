package com.xiaogua.comments.common.constant;

/**
 * 干货分类
 *
 */
public enum CategoryType {

    /**
     * 所有
     */
    ALL(100100,"所有"),

    /**
     * 智慧家庭
     */
    WISDOM_FAMILY(100101,"智慧家庭"),

    /**
     * 智慧社区
     */
    WISDOM_COMMUNITY(100102, "智慧社区"),

    /**
     * 智慧生态
     */
    WISDOM_ZOOLOGY(100103, "智慧生态"),

    /**
     * 相关领域
     */
    CORRELATION_DOMAIN(100104, "相关领域");

    private int code;

    private String categoryName;

    CategoryType(int code, String categoryName) {
        this.code = code;
        this.categoryName = categoryName;
    }

    public int getCode() {
        return code;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public static String getCategoryName(Integer code){
        if(code == null){
            return "";
        }
        for(CategoryType e : values()){
            if(code.equals(e.getCode())){
                return e.getCategoryName();
            }
        }
        return "";
    }
}
