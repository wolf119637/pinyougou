package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Result;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by kim
 * on 2019/5/22.
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 查询所有的条数
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }

    /**
     * 传入当前页数，每页的数量
     *
     * @param pageNum
     * @param size
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int pageNum, int size) {
        return brandService.findPage(pageNum, size);
    }

    /**
     * 增加
     *
     * @param brand
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand brand) {
        try {
            brandService.add(brand);

            return new Result(true, "增加成功");
        } catch (Exception e) {

            return new Result(false, "增加失败");
        }

    }

    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand) {

        try {
            brandService.update(brand);
            return new Result(true, "修改成功");
        } catch (Exception e) {

            e.printStackTrace();
            return new Result(false, "修改失败");
        }


    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(long[] ids) {

        try {
            brandService.delete(ids);

            return new Result(true, "删除成功");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }

    }

    /**
     * 根据id获取实体bean
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id) {

        return brandService.findOne(id);
    }

    /**
     * 查询+分页
     * @param tbBrand
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand tbBrand, int pageNum, int rows) {

            return brandService.findPage(tbBrand,pageNum,rows);
    }


    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList() {
        return brandService.selectOptionList();
    }

}
