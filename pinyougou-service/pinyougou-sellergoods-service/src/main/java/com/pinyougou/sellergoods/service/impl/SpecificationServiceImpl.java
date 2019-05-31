package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.PageResult;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import com.pinyougou.pojo.TbSpecificationOptionExample;

import java.util.List;
import java.util.Map;

/**
 * Created by kim
 * on 2019/5/24.
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;

    @Autowired
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;

    @Override
    public List<TbSpecification> findAll() {


        return specificationMapper.selectByExample(null);
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     *
     * @param specification
     */
    @Override
    public void add(Specification specification) {

        System.out.println(specification.getSpecification().getSpecName());
        //获取规格实体
        TbSpecification tbSpecification = specification.getSpecification();
        specificationMapper.insert(tbSpecification);

        //获取规格选项集合
        List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();

        for (TbSpecificationOption option : specificationOptionList) {
            option.setSpecId(tbSpecification.getId());//设置规格id
            tbSpecificationOptionMapper.insert(option);
        }

    }

    /**
     * 修改
     *
     * @param specification
     */
    @Override
    public void update(Specification specification) {

        //获取规格实体
        TbSpecification tbSpecification = specification.getSpecification();

        specificationMapper.updateByPrimaryKey(tbSpecification);

        //删除原来规格对应的规格选项

        TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();

        TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();

        criteria.andSpecIdEqualTo(tbSpecification.getId());

        tbSpecificationOptionMapper.deleteByExample(tbSpecificationOptionExample);

        //获取规格选项集合

        List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();

        for (TbSpecificationOption option : specificationOptionList
                ) {

            option.setSpecId(tbSpecification.getId());
            tbSpecificationOptionMapper.insert(option);

        }


    }

    @Override
    public Specification findOne(Long id) {

        Specification specification=new Specification();

        //获取规格实体

        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);

        specification.setSpecification(tbSpecification);

        //获取规格选项列表

        TbSpecificationOptionExample example = new TbSpecificationOptionExample();

        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();

        criteria.andSpecIdEqualTo(id);

        List<TbSpecificationOption> specificationOptionList = tbSpecificationOptionMapper.selectByExample(example);

        specification.setSpecificationOptionList(specificationOptionList);

        return specification; //组合实体类



    }

    @Override
    public void delete(Long[] ids) {

        for (Long id:ids){

            System.out.println("进行删除");

            specificationMapper.deleteByPrimaryKey(id);

            TbSpecificationOptionExample example = new TbSpecificationOptionExample();

            TbSpecificationOptionExample.Criteria criteria1 = example.createCriteria();

            criteria1.andSpecIdEqualTo(id);

            tbSpecificationOptionMapper.deleteByExample(example);


        }

    }

    @Override
    public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbSpecificationExample example=new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();

        if(specification!=null){
            if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
                criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
            }

        }

        Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<Map> selectOptionList() {

        return specificationMapper.selectOptionList();
    }
}
