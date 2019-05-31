package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.PageResult;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbItemCatExample;
import com.pinyougou.sellergoods.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by kim on 2019/5/27.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    /**
     * 根据上级ID查询列表
     * @param parentId
     * @return
     */
    @Override
    public List<TbItemCat> findByParentId(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return tbItemCatMapper.selectByExample(example);
    }



    @Override
    public List<TbItemCat> findAll() {

        return tbItemCatMapper.selectByExample(null);

    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbItemCat> page=   (Page<TbItemCat>) tbItemCatMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbItemCat itemCat) {
        tbItemCatMapper.insert(itemCat);
    }

    @Override
    public void update(TbItemCat itemCat) {
        tbItemCatMapper.updateByPrimaryKey(itemCat);
    }

    @Override
    public TbItemCat findOne(Long id) {
        return tbItemCatMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            tbItemCatMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findPage(TbItemCat itemCat, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbItemCatExample example=new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();

        if(itemCat!=null){
            if(itemCat.getName()!=null && itemCat.getName().length()>0){
                criteria.andNameLike("%"+itemCat.getName()+"%");
            }

        }

        Page<TbItemCat> page= (Page<TbItemCat>)tbItemCatMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }


}
