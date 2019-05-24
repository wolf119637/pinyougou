package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.PageResult;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kim on 2019/5/22.
 */
@Service
public class BrandServiceImpl implements BrandService {


    @Resource
    private TbBrandMapper tbBrandMapper;
    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {

        //使用分页插件 ，一般使用在1对1的 情况使用，多对一或一对多 不保证一定正确
        PageHelper.startPage(pageNum,pageSize);
        //对 第一条 sql语句生效
//       只要你可以保证在 PageHelper 方法调用后紧跟 MyBatis 查询方法，这就是安全的。
//      因为 PageHelper 在 finally 代码段中自动清除了 ThreadLocal 存储的对象。
        Page<TbBrand> page = (Page<TbBrand>)tbBrandMapper.selectByExample(null);

        return  new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(TbBrand brand) {

        tbBrandMapper.insert(brand);

    }

    /**
     * 修改
     * @param brand
     */

    @Override
    public void update(TbBrand brand) {
        tbBrandMapper.updateByPrimaryKey(brand);

    }

    /**
     * 根据id 获取实体
     * @param id
     * @return
     */
    @Override
    public TbBrand findOne(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除
     * @param ids
     */
    @Override
    public void delete(long[] ids) {

        for (long id :ids){

            tbBrandMapper.deleteByPrimaryKey(id);
        }



    }

    /**
     * 品牌条件查询
     * @param brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum,pageSize);

        TbBrandExample tbBrandExample = new TbBrandExample();
        TbBrandExample.Criteria criteria = tbBrandExample.createCriteria();
        if(brand!=null){

            if(brand.getName()!=null&& brand.getName().length()>0){

                criteria.andNameLike("%"+brand.getName()+"%");
            }
        }

        if(brand.getFirstChar()!=null&& brand.getFirstChar().length()>0){

            criteria.andFirstCharEqualTo(brand.getFirstChar());
        }


        Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(tbBrandExample);

        return new PageResult(page.getTotal(),page.getResult());
    }
}
