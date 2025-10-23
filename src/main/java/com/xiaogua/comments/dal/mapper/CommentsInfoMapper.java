package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.CommentsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsInfoMapper {
    /**
     * deleteByPrimaryKey
     *
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert
     *
     * @param record
     * @return int
     */
    int insert(CommentsInfo record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(CommentsInfo record);

    /**
     * selectByPrimaryKey
     *
     * @param id
     * @return CommentsInfo
     */
    CommentsInfo selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(CommentsInfo record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(CommentsInfo record);

    /**
     * selectByCode
     *
     * @param code
     * @return CommentsInfo
     */
    CommentsInfo selectByCode(String code);

    /**
     * 通过编号获取评论信息（不含删除）
     *
     * @param code
     * @return
     */
    CommentsInfo selectByCodeWithoutDel(String code);

    /**
     * 统计评论数量
     *
     * @param toCode      被评论code
     * @param userCode    评论用户code
     * @param showDeleted 是否展示删除数据
     * @return
     */
    int count(@Param("toCode") String toCode, @Param("userCode") String userCode,
        @Param("showDeleted") Boolean showDeleted, @Param("userType") Integer userType);

    /**
     * 分页获取评论信息
     *
     * @param index       起始
     * @param pageSize    页大小
     * @param sort        排序（默认创建时间倒序）
     * @param toCode      被评论code
     * @param userCode    评论用户code
     * @param showDeleted 是否展示删除数据
     * @return
     */
    List<CommentsInfo> findPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize,
        @Param("sort") String sort, @Param("toCode") String toCode, @Param("userCode") String userCode,
        @Param("showDeleted") Boolean showDeleted, @Param("userType") Integer userType);

    /**
     * 按评论发布时间排序获取去重品牌编号
     *
     * @param limit
     * @return
     */
    List<String> selectDistinctBrandCodeOrderByCommentCreateDate(@Param("limit") Integer limit);

    /**
     * 通过品牌编号获取评论点赞数
     *
     * @param brandCode
     * @return
     */
    Integer sumLikeByBrand(@Param("brandCode") String brandCode);

    /**
     * 通过品牌编号获取最高点赞数的评论
     *
     * @param brandCode
     * @return
     */
    CommentsInfo selectMaxLikeNumCommentsInfoByBrand(@Param("brandCode") String brandCode);
}