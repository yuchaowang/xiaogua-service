package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {
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
    int insert(Brand record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(Brand record);

    /**
     * selectByPrimaryKey
     *
     * @param id
     * @return Brand
     */
    Brand selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Brand record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Brand record);

    /**
     * selectByCode
     *
     * @param code
     * @return Brand
     */
    Brand selectByCode(String code);

    /**
     * 统计品牌数量(过滤删除)
     *
     * @param initals 首字母
     * @param keyword 关键词
     * @return
     */
    int count(@Param("initals") List<String> initals, @Param("keyword") String keyword);

    /**
     * 统计品牌数量(含删除)
     *
     * @param initals 首字母
     * @param keyword 关键词
     * @return
     */
    int countWithDel(@Param("initals") List<String> initals, @Param("keyword") String keyword);

    /**
     * 分页获取品牌信息(过滤删除)
     *
     * @param index    起始
     * @param pageSize 页大小
     * @param initals  首字母
     * @param keyword  关键词
     * @param sort     排序（默认英文名正序，createTime：创建时间倒序）
     * @return
     */
    List<Brand> findPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize,
        @Param("initals") List<String> initals, @Param("keyword") String keyword, @Param("sort") String sort);

    /**
     * 分页获取品牌信息(含删除)
     *
     * @param index    起始
     * @param pageSize 页大小
     * @param initals  首字母
     * @param keyword  关键词
     * @param sort     排序（默认英文名正序，createTime：创建时间倒序）
     * @return
     */
    List<Brand> findPageWithDel(@Param("index") Integer index, @Param("pageSize") Integer pageSize,
        @Param("initals") List<String> initals, @Param("keyword") String keyword, @Param("sort") String sort);

    /**
     * 通过编号获取品牌信息（过滤删除）
     *
     * @param code 编号
     * @return
     */
    Brand selectByCodeWithoutDel(String code);

    /**
     * 通过品牌名获取品牌（中英文全匹配）
     *
     * @param name
     * @return
     */
    List<Brand> selectByName(@Param("name") String name);

    /**
     * 通过品牌编号获取品牌信息（遵循原排序）
     *
     * @param codes
     * @return
     */
    List<Brand> selectBrandByBrandCodesOrder(@Param("codes") List<String> codes);
}