package com.pinyougou.sellergoods.service;

/**
 * Created by kim
 * on 2019/5/22.
 */

import com.pinyougou.entity.PageResult;
import com.pinyougou.pojo.TbBrand;

import java.util.List;

/**
 * 品牌接口
 */
public interface BrandService {


    /**
     * 查询所有的条数
     * @return
     */
    public List<TbBrand> findAll();

    /**
     * 品牌分页
     * @param pageNum 当前页
     * @param pageSize 每页的数量
     * @return
     */
    public PageResult findPage(int pageNum,int pageSize);

    /**
     * 增加品牌
     */
    public void add(TbBrand brand);

    /**
     * 修改
     */
    public void update(TbBrand brand);

    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    public TbBrand findOne(Long id);

    /**
     * 批量删除
     * @param ids
     */
    public void delete(long[] ids);

    //条件查询
    /**
     * 分页
     * @param brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(TbBrand brand,int pageNum,int pageSize);










}
